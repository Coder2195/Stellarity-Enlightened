package dev.coder2195.stellarity.registry.entity;

import com.mojang.serialization.Codec;
import dev.coder2195.stellarity.registry.StellarityDamageTypes;
import dev.coder2195.stellarity.registry.StellarityEntityTypes;
import dev.coder2195.stellarity.util.DamageUtility;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class StrikerStar extends AbstractArrow {
	private int liveTime = 4 * 20;
	private Vec3 posOld = null;

	public StrikerStar(EntityType<? extends AbstractArrow> type, Level level) {
		super(type, level);
	}

	public StrikerStar(final Level level, final LivingEntity owner, final ItemStack pickupItemStack, final @Nullable ItemStack firedFromWeapon) {
		super(StellarityEntityTypes.STRIKER_STAR, owner, level, pickupItemStack, firedFromWeapon);

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
	protected float getAirDrag() {
		return 1;
	}

	@Override
	protected float getWaterInertia() {
		return 1;
	}

	@Override
	protected @NonNull ItemStack getDefaultPickupItem() {
		return ItemStack.EMPTY;
	}

	@Override
	public void tick() {
		var level = level();
		if (posOld == null) posOld = position();
		super.tick();
		if (!level.isClientSide()) {
			liveTime--;
			if (liveTime <= 0) {
				explode(null, position());
				this.discard();
			}

			return;
		}
		drawTrail(position());
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
