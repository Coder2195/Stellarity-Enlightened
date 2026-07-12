package dev.coder2195.stellarity.registry.entity;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import dev.coder2195.stellarity.registry.StellarityEntityTypes;
import dev.coder2195.stellarity.tags.StellarityEntityTypeTags;

import java.util.List;

public class SpectralWisp extends AbstractArrow {
	private List<LivingEntity> targets = List.of();
	private float damage;
	private int mobsRemaining = 3;
	private @Nullable EntityReference<LivingEntity> lastHitEntity;
	private @Nullable EntityReference<LivingEntity> currentTarget;
	private int exploreTicks = 1;
	private int liveTime = 20 * 7;
	private double speed = 0.5;

	public SpectralWisp(EntityType<? extends AbstractArrow> type, Level level) {
		super(type, level);
	}

	public SpectralWisp(@NonNull Level level, @NonNull LivingEntity shooter, ItemStack itemStack, @NonNull ItemStack weapon) {
		super(StellarityEntityTypes.SPECTRAL_WISP, shooter, level, itemStack, weapon);
		this.setPos(shooter.getX(), shooter.getEyeY() - 0.1f, shooter.getZ());
		var power = EnchantmentHelper.getItemEnchantmentLevel(registryAccess().getOrThrow(Enchantments.POWER), weapon);
		damage = 5 + (1.5f * (2 + (power > 0 ? 0.5f : 0) + power * 0.5f));
		setDeltaMovement(shooter.getLookAngle().normalize().scale(speed));
	}

	@Override
	protected void onHitEntity(@NonNull EntityHitResult hitResult) {
		var level = level();
		if (!(level instanceof ServerLevel serverLevel)) return;
		var entity = hitResult.getEntity();
		if (!entity.canBeHitByProjectile()) return;
		//noinspection DataFlowIssue: it does accept null
		if (entity instanceof LivingEntity livingEntity && !livingEntity.hurtServer(serverLevel, this.damageSources().mobAttack(this.getOwner() instanceof LivingEntity attacker ? attacker : null), damage))
			return;

		mobsRemaining--;
		lastHitEntity = currentTarget;
		currentTarget = null;
		exploreTicks = 10;
		liveTime = 7 * 20;
		damage *= 0.7f;


		setDeltaMovement(getDeltaMovement().scale(-1));

		if (mobsRemaining == 0) this.discard();
	}

	@Override
	protected void onHitBlock(@NonNull BlockHitResult hitResult) {
		super.onHitBlock(hitResult);
		this.discard();
	}


	private void getCandidates() {
		var pos = this.position();
		var x = pos.x();
		var y = pos.y();
		var z = pos.z();
		targets = this.level().getEntitiesOfClass(LivingEntity.class, new AABB(x - 16, y - 16, z - 16, x + 16, y + 16, z + 16), (entity) -> {
			if (this.is(entity) || !canHitEntity(entity) || this.owner != null && this.owner.matches(entity)) return false;
			return level().clip(new ClipContext(pos, entity.position(), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this)).getType().equals(HitResult.Type.MISS);
		});
	}

	@Override
	public void tick() {
		this.setNoGravity(true);

		var level = level();
		var position = position();
		if (level.isClientSide()) {
			level.addAlwaysVisibleParticle(ParticleTypes.END_ROD, true, position.x, position.y, position.z, 0, 0, 0);
			super.tick();
			return;
		}


		liveTime--;
		if (liveTime <= 0) {
			this.discard();
			return;
		}

		var targetEntity = currentTarget == null ? null : currentTarget.getEntity(level, LivingEntity.class);
		if (targetEntity == null) currentTarget = null;
		else {
			this.setDeltaMovement(targetEntity.position().add(0, targetEntity.getBbHeight() / 2, 0).subtract(this.position()).normalize().scale(speed));
		}


		if (exploreTicks > 0) {
			exploreTicks--;
			setDeltaMovement(getDeltaMovement().normalize().scale(speed));
			super.tick();
			return;
		}

		if (targets.isEmpty() && currentTarget == null) {
			getCandidates();

			if (targets.isEmpty()) exploreTicks = 10;
		}

		if (!targets.isEmpty() && currentTarget == null) {

			var targetSize = targets.size();
			int bestMatchIndex = -1;
			double bestDistance = 1000;
			for (int i = 0; i < targetSize; i++) {
				var candidate = targets.get(i);
				var candidatePos = candidate.position().add(0, candidate.getBbHeight() / 2, 0);
				var distance = position.distanceTo(candidatePos);
				if (distance > bestDistance) continue;

				if (level().clip(new ClipContext(position, candidatePos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this)).getType().equals(HitResult.Type.MISS)) {

					bestMatchIndex = i;
					bestDistance = distance;
				}
			}

			if (bestMatchIndex != -1) currentTarget = EntityReference.of(targets.remove(bestMatchIndex));
			else
				currentTarget = lastHitEntity == null || lastHitEntity.getEntity(level, LivingEntity.class) == null ? null : lastHitEntity;
		}

		targetEntity = currentTarget == null ? null : currentTarget.getEntity(level, LivingEntity.class);
		if (targetEntity == null) currentTarget = null;
		else {
			this.setDeltaMovement(targetEntity.position().add(0, targetEntity.getBbHeight() / 2, 0).subtract(this.position()).normalize().scale(speed));
		}


		super.tick();
	}

	@Override
	protected @NonNull ItemStack getDefaultPickupItem() {
		return ItemStack.EMPTY;
	}


	@Override
	protected void addAdditionalSaveData(@NonNull ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.store("damage", Codec.FLOAT, damage);
		output.store("mobs_remaining", Codec.INT, mobsRemaining);
		output.storeNullable("last_hit_entity", EntityReference.codec(), lastHitEntity);
		output.storeNullable("current_target", EntityReference.codec(), currentTarget);
		output.store("speed", Codec.DOUBLE, speed);
		output.store("live_time", Codec.INT, liveTime);
	}


	@Override
	protected void readAdditionalSaveData(@NonNull ValueInput input) {
		super.readAdditionalSaveData(input);
		input.read("damage", Codec.FLOAT).ifPresent(this::setDamage);
		input.read("mobs_remaining", Codec.INT).ifPresent(this::setMobsRemaining);
		input.read("last_hit_entity", EntityReference.<LivingEntity>codec()).ifPresent(ref -> this.lastHitEntity = ref);
		input.read("current_target", EntityReference.<LivingEntity>codec()).ifPresent(ref -> this.currentTarget = ref);
		input.read("speed", Codec.DOUBLE).ifPresent(value -> this.speed = value);
		input.read("live_time", Codec.INT).ifPresent(value -> this.liveTime = value);
	}


	@Override
	protected boolean canHitEntity(@NonNull Entity entity) {
		return super.canHitEntity(entity) && entity.canBeHitByProjectile() && !entity.is(StellarityEntityTypeTags.INVALID_TARGETS);
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	public int getMobsRemaining() {
		return mobsRemaining;
	}

	public void setMobsRemaining(int mobsRemaining) {
		this.mobsRemaining = mobsRemaining;
	}
}
