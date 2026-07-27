package dev.coder2195.stellarity.entity;

import com.mojang.serialization.Codec;
import dev.coder2195.stellarity.registry.StellarityMobEffects;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class PotionCloud extends Entity {
	private static final EntityDataAccessor<List<ParticleOptions>> DATA_PARTICLES = SynchedEntityData.defineId(PotionCloud.class, EntityDataSerializers.PARTICLES);
	private static final EntityDataAccessor<Float> DATA_PARTICLE_DENSITY = SynchedEntityData.defineId(PotionCloud.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<Float> DATA_WIDTH = SynchedEntityData.defineId(PotionCloud.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<Float> DATA_HEIGHT = SynchedEntityData.defineId(PotionCloud.class, EntityDataSerializers.FLOAT);
	private int liveTime = 20 * 2;

	public PotionCloud(EntityType<?> type, Level level) {
		super(type, level);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NonNull Builder entityData) {
		entityData.define(DATA_PARTICLES, List.of());
		entityData.define(DATA_PARTICLE_DENSITY, 20f);
		entityData.define(DATA_WIDTH, 2f);
		entityData.define(DATA_HEIGHT, 2f);
	}

	public List<ParticleOptions> getParticles() {
		return entityData.get(DATA_PARTICLES);
	}

	public void setParticles(List<ParticleOptions> particles) {
		entityData.set(DATA_PARTICLES, particles);
	}

	public void setParticles(ParticleOptions... particles) {
		entityData.set(DATA_PARTICLES, List.of(particles));
	}

	public float getParticleDensity() {
		return entityData.get(DATA_PARTICLE_DENSITY);
	}

	public void setParticleDensity(float density) {
		entityData.set(DATA_PARTICLE_DENSITY, density);
	}

	public float getWidth() {
		return entityData.get(DATA_WIDTH);

	}

	public void setWidth(float width) {
		entityData.set(DATA_WIDTH, width);

		this.setBoundingBox(this.makeBoundingBox());
	}

	public float getHeight() {
		return entityData.get(DATA_HEIGHT);
	}

	public void setHeight(float height) {
		entityData.set(DATA_HEIGHT, height);

		this.setBoundingBox(this.makeBoundingBox());
	}

	@Override
	public void tick() {
		super.tick();
		var level = level();
		var box = getBoundingBox();
		if (level.isClientSide()) {
			double particles = box.getXsize() * box.getYsize() * box.getZsize() * getParticleDensity();
			int particlesListCount = getParticles().size();
			if (particlesListCount == 0) return;
			for (int i=0; i < particles; i++) {
				level.addAlwaysVisibleParticle(getParticles().get(random.nextInt(particlesListCount)),
					box.minX + random.nextDouble() * box.getXsize(),
					box.minY + random.nextDouble() * box.getYsize(),
					box.minZ + random.nextDouble() * box.getZsize(),
					0, 0, 0
				);
			}

			return;
		}

		this.move(MoverType.SELF, this.getDeltaMovement());

		for (var entity : level.getEntitiesOfClass(LivingEntity.class, box.inflate(1)))
			entity.addEffect(new MobEffectInstance(StellarityMobEffects.JINX, 20 * 10));

		if (--liveTime <= 0) discard();
	}

	private EntityDimensions getDimensions() {
		return EntityDimensions.scalable(this.getWidth(), this.getHeight());
	}

	@Override
	public @NonNull EntityDimensions getDimensions(final @NonNull Pose pose) {
		return this.getDimensions();
	}

	@Override
	protected @NonNull AABB makeBoundingBox(final @NonNull Vec3 position) {
		return this.getDimensions().makeBoundingBox(position);
	}

	@Override
	public boolean hurtServer(@NonNull ServerLevel level, @NonNull DamageSource source, float damage) {
		return false;
	}

	public int getLiveTime() {
		return liveTime;
	}

	public void setLiveTime(int liveTime) {
		this.liveTime = liveTime;
	}

	@Override
	protected void readAdditionalSaveData(@NonNull ValueInput input) {
		input.read("particles", ParticleTypes.CODEC.listOf()).ifPresent(this::setParticles);
		input.read("particle_density", Codec.FLOAT).ifPresent(this::setParticleDensity);
		input.read("width", Codec.FLOAT).ifPresent(this::setWidth);
		input.read("height", Codec.FLOAT).ifPresent(this::setHeight);
		input.read("live_time", Codec.INT).ifPresent(this::setLiveTime);
	}

	@Override
	protected void addAdditionalSaveData(@NonNull ValueOutput output) {
		output.store("particles", ParticleTypes.CODEC.listOf(), getParticles());
		output.store("particle_density", Codec.FLOAT, getParticleDensity());
		output.store("width", Codec.FLOAT, getWidth());
		output.store("height", Codec.FLOAT, getHeight());
		output.store("live_time", Codec.INT, liveTime);
	}
}
