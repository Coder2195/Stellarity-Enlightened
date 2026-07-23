package dev.coder2195.stellarity.registry.entity;

import com.mojang.serialization.Codec;
import dev.coder2195.stellarity.registry.StellarityDamageTypes;
import dev.coder2195.stellarity.registry.StellarityEntityTypes;
import dev.coder2195.stellarity.util.DamageUtility;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.*;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class StrikerStar extends Projectile {
	private int liveTime = 4 * 20;
	private Vec3 posOld = null;

	public StrikerStar(EntityType<? extends StrikerStar> type, Level level) {
		super(type, level);
	}

	public StrikerStar(final Level level, final LivingEntity owner) {
		super(StellarityEntityTypes.STRIKER_STAR, level);
		this.setOwner(owner);
		this.setNoGravity(true);
	}

	public int getLiveTime() {
		return liveTime;
	}

	public void setLiveTime(int liveTime) {
		this.liveTime = liveTime;
	}

	@Override
	protected void readAdditionalSaveData(@NonNull ValueInput input) {
		super.readAdditionalSaveData(input);
		input.read("live_time", Codec.INT).ifPresent(this::setLiveTime);
	}

	@Override
	protected void addAdditionalSaveData(@NonNull ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.store("live_time", Codec.INT, liveTime);
	}

	@Override
	public void tick() {
		var level = level();
		if (posOld == null) posOld = position();
		HitResult result = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
		Vec3 newPosition;

		if (result.getType() != HitResult.Type.MISS) {
			newPosition = result.getLocation();
		} else {
			newPosition = this.position().add(this.getDeltaMovement());
		}

		this.setPos(newPosition);
		this.updateRotation();
		this.applyEffectsFromBlocks();
		super.tick();
		if (result.getType() != HitResult.Type.MISS && this.isAlive()) {
			this.hitTargetOrDeflectSelf(result);
		}

		if (!level.isClientSide()) {
			liveTime--;
			if (liveTime <= 0) {
				explode(null, newPosition);
				this.discard();
			}

			return;
		}
		drawTrail(newPosition);
	}

	private void drawTrail(Vec3 target) {
		var level = level();
		var delta = target.subtract(posOld);
		var steps = delta.length() / 0.1;
		var stepVec = delta.scale(1 / steps);
		for (int i = 0; i < steps; i++) {

			level.addAlwaysVisibleParticle(new DustColorTransitionOptions(0xffffa8, 0xec9a00, 0.7f), true, posOld.x + i * stepVec.x, posOld.y + i * stepVec.y, posOld.z + i * stepVec.z, 0, 0, 0);

			posOld = posOld.add(stepVec);
		}
	}

	@Override
	protected void onHitBlock(@NonNull BlockHitResult hitResult) {
		explode(null, hitResult.getLocation());
		discard();
	}

	@Override
	protected void onHitEntity(@NonNull EntityHitResult hitResult) {
		explode(hitResult.getEntity() instanceof LivingEntity living ? living : null, hitResult.getLocation());
		discard();
	}

	public void explode(@Nullable LivingEntity primaryHit, Vec3 impactLocation) {
		if (!(this.level() instanceof ServerLevel serverLevel)) return;
		var damageSource = damageSources().source(StellarityDamageTypes.STRIKER_STAR, getOwner());
		if (primaryHit != null) DamageUtility.damageEntity(serverLevel, primaryHit, damageSource, 9, 0.5f, 0f);

		for (var entity : serverLevel.getEntities(
			EntityTypeTest.forClass(LivingEntity.class),
			new AABB(impactLocation, impactLocation).inflate(5),
			entity -> entity.getBoundingBox().distanceToSqr(impactLocation) < 16
		)) {
			DamageUtility.damageEntity(serverLevel, entity, damageSource, 6, 0.5f, 0f);
		}
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NonNull Builder entityData) {
	}

	@Override
	public void onClientRemoval() {

		if (posOld == null) posOld = position();
		drawTrail(position().add(getDeltaMovement()));
		var level = level();
		var random = RandomSource.create();
		for (int i = 0; i < 22; i++) {
			level.addAlwaysVisibleParticle(ParticleTypes.END_ROD, true, getX(), getY(), getZ(), (random.nextDouble() - 0.5) * 0.5, (random.nextDouble() - 0.5) * 0.5, (random.nextDouble() - 0.5) * 0.5);
		}

		level.addAlwaysVisibleParticle(ParticleTypes.GUST, true, 0, 0, 0, 0, 0, 0);
		level.addAlwaysVisibleParticle(ParticleTypes.POOF, true, 0, 0, 0, 0, 1, 0);
		super.onClientRemoval();
	}
}
