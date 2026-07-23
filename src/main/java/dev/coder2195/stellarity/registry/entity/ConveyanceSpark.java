package dev.coder2195.stellarity.registry.entity;

import com.mojang.serialization.Codec;
import dev.coder2195.stellarity.registry.StellarityEntityTypes;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class ConveyanceSpark extends Projectile {
	private int liveTime = 5 * 20;
	private Vec3 posOld = null;
	private double speed = 1.5f;

	public ConveyanceSpark(EntityType<? extends ConveyanceSpark> type, Level level) {
		super(type, level);

		this.setNoGravity(true);
	}

	@Override
	public boolean shouldRenderAtSqrDistance(double distance) {
		return distance < 128 * 128;
	}

	public ConveyanceSpark(final Level level, final @Nullable LivingEntity owner) {
		this(StellarityEntityTypes.CONVEYANCE_SPARK, level);

		this.setOwner(owner);
		if (owner != null) this.setDeltaMovement(owner.getLookAngle().normalize().scale(speed));

	}

	public int getLiveTime() {
		return liveTime;
	}

	public void setLiveTime(int liveTime) {
		this.liveTime = liveTime;
	}


	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getSpeed() {
		return speed;
	}

	@Override
	protected void readAdditionalSaveData(@NonNull ValueInput input) {
		super.readAdditionalSaveData(input);
		input.read("live_time", Codec.INT).ifPresent(this::setLiveTime);
		input.read("speed", Codec.DOUBLE).ifPresent(this::setSpeed);
	}


	@Override
	protected void addAdditionalSaveData(@NonNull ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.store("live_time", Codec.INT, liveTime);
		output.store("speed", Codec.DOUBLE, speed);
	}

	@Override
	public void tick() {
		var level = level();
		if (posOld == null) posOld = position();
		var owner = getOwner();
		if (owner != null) setDeltaMovement(owner.getHeadLookAngle().normalize().scale(speed));

		HitResult result = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
		Vec3 newPosition;

		if (result.getType() != HitResult.Type.MISS) {
			newPosition = result.getLocation();

			if (owner != null) owner.teleportTo(newPosition.x, newPosition.y, newPosition.z);
			this.discard();
		} else {
			newPosition = this.position().add(this.getDeltaMovement());
		}

		this.setPos(newPosition);
		this.updateRotation();
		this.applyEffectsFromBlocks();
		super.tick();

		if (!level.isClientSide()) {
			liveTime--;
			if (liveTime <= 0)
				this.discard();


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

			level.addAlwaysVisibleParticle(new DustColorTransitionOptions(0xe51fff, 0x6f00ff, 1.05f), true, posOld.x + i * stepVec.x, posOld.y + i * stepVec.y, posOld.z + i * stepVec.z, 0, 0, 0);

			posOld = posOld.add(stepVec);
		}
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NonNull Builder entityData) {
	}

}
