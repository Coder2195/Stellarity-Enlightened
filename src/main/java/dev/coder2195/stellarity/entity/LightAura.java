package dev.coder2195.stellarity.entity;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

public class LightAura extends Entity {
	private int liveTime = 17 * 20;
	private double rotation = 0;
	public static final EntityDataAccessor<Float> DATA_RADIUS = SynchedEntityData.defineId(LightAura.class, EntityDataSerializers.FLOAT);

	public LightAura(EntityType<?> type, Level level) {
		super(type, level);
	}

	public void setLiveTime(int liveTime) {
		this.liveTime = liveTime;
	}

	public int getLiveTime() {
		return liveTime;
	}

	public void setRadius(float radius) {
		entityData.set(DATA_RADIUS, radius);
	}

	public float getRadius() {
		return entityData.get(DATA_RADIUS);
	}

	@Override
	public void tick() {
		super.tick();
		var level = level();
		var position = position().add(0, 0.3, 0);
		var radius = getRadius();
		boolean pulse = liveTime % 20 == 0;
		liveTime--;
		double rotatingSpeed = 0.2 / (radius+0.5);

		if (level.isClientSide()) {
			for (int i = 0; i < 3; i++) {
				Vec3 particlePos = position.add(new Vec3(radius + 0.5, 0, 0).yRot((float) (rotation + i * Math.PI * 2 / 3)));
				level.addParticle(ParticleTypes.END_ROD, particlePos.x, particlePos.y, particlePos.z, 0, 0, 0);
				for (int j = 0; j < 2; j++)
					level.addAlwaysVisibleParticle(new DustParticleOptions(0xffdd00, 1.25f), true, particlePos.x, particlePos.y, particlePos.z, 0.1, 0.1, 0.1);
			}

			rotation += rotatingSpeed;

			if (!pulse) return;

			level.playLocalSound(position.x, position.y, position.z, SoundEvents.BEACON_AMBIENT, SoundSource.AMBIENT, 1, 1, false);
			float particleCount = radius * 18;
			for (int i = 0; i < particleCount; i++) {
				var velocity = new Vec3(radius / 12, 0, 0).yRot((float) (Math.PI * i * 2 / particleCount));
				level.addParticle(ParticleTypes.END_ROD, position.x, position.y + 0.1, position.z, velocity.x, velocity.y, velocity.z);
			}

			for (int i = 0; i < 3; i++) {
				Vec3 particlePos = position.add(new Vec3(Math.sqrt(random.nextDouble()) * radius, 0.3, 0).yRot(random.nextFloat() * 2 * Mth.PI));
				level.addParticle(new DustParticleOptions(0xffdd00, 0.8f), particlePos.x, particlePos.y, particlePos.z, 0, 0, 0);
			}

			for (int i = 0; i < 4; i++) {
				Vec3 particlePos = position.add(new Vec3(Math.sqrt(random.nextDouble()) * radius, 0.3, 0).yRot(random.nextFloat() * 2 * Mth.PI));
				level.addParticle(ParticleTypes.TRIAL_SPAWNER_DETECTED_PLAYER, particlePos.x, particlePos.y, particlePos.z, 0, 0, 0);
			}

			return;
		}

		AABB bounds = new AABB(position, position).inflate(radius + 1);

		if (liveTime % 10 != 0) return;

		var entities = level.getEntitiesOfClass(LivingEntity.class, bounds, e -> e.distanceToSqr(position) < radius * radius && Math.abs(e.getY() - position.y) < 2);
		for (var entity : entities) {
			entity.removeEffect(MobEffects.WEAKNESS);
			entity.removeEffect(MobEffects.SLOWNESS);
			entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 5 * 20));
			entity.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 5 * 20));
		}


		if (liveTime <= 0) {
			this.discard();
		}
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NonNull Builder entityData) {
		entityData.define(DATA_RADIUS, 5.5f);
	}

	@Override
	public boolean hurtServer(@NonNull ServerLevel level, @NonNull DamageSource source, float damage) {
		return false;
	}

	@Override
	protected void readAdditionalSaveData(@NonNull ValueInput input) {
		input.read("live_time", Codec.INT).ifPresent(this::setLiveTime);
		input.read("radius", Codec.FLOAT).ifPresent(this::setRadius);
	}

	@Override
	protected void addAdditionalSaveData(@NonNull ValueOutput output) {
		output.store("live_time", Codec.INT, liveTime);
		output.store("radius", Codec.FLOAT, getRadius());
	}
}
