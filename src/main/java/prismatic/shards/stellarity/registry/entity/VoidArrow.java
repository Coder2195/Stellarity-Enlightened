package prismatic.shards.stellarity.registry.entity;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import prismatic.shards.stellarity.key.StellarityDamageTypes;
import prismatic.shards.stellarity.networking.ClientboundVoidArrowHitPayload;
import prismatic.shards.stellarity.registry.StellarityEntityTypes;
import prismatic.shards.stellarity.registry.StellarityMobEffects;
import prismatic.shards.stellarity.registry.effect.VoidedEffect;

import java.util.ArrayList;
import java.util.List;

public class VoidArrow extends AbstractArrow {
	public VoidArrow(EntityType<VoidArrow> type, Level level) {
		super(type, level);
	}

	public VoidArrow(final Level level, final LivingEntity owner, final ItemStack pickupItemStack, final @Nullable ItemStack firedFromWeapon) {
		super(StellarityEntityTypes.VOID_ARROW, owner, level, pickupItemStack, firedFromWeapon);
	}

	@Override
	public void applyOnProjectileSpawned(ServerLevel serverLevel, ItemStack pickupItemStack) {
		super.applyOnProjectileSpawned(serverLevel, pickupItemStack);

		stellarity$addMobEffects(new MobEffectInstance(StellarityMobEffects.VOIDED, 160));
	}

	@Override
	public void tick() {
		var level = level();

		if (level.isClientSide()) {
			level.addAlwaysVisibleParticle(VoidedEffect.PARTICLE, true, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
		}

		super.tick();
	}

	@Override
	protected @NonNull SoundEvent getDefaultHitGroundSoundEvent() {
		return SoundEvents.GLASS_BREAK;
	}

	@Override
	protected void doPostHurtEffects(@NonNull LivingEntity mob) {
		super.doPostHurtEffects(mob);
		this.getPickupItemStackOrigin().getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY)
			.forEachEffect(mob::addEffect, this.getPickupItemStackOrigin().getOrDefault(DataComponents.POTION_DURATION_SCALE, 1.0F));
	}

	public VoidArrow(final Level level, final double x, final double y, final double z, final ItemStack pickupItemStack, final @Nullable ItemStack firedFromWeapon) {
		super(StellarityEntityTypes.VOID_ARROW, x, y, z, level, pickupItemStack, firedFromWeapon);
	}

	@Override
	public @NonNull ItemStack getDefaultPickupItem() {
		return ItemStack.EMPTY;
	}

	@Override
	protected void onHit(@NonNull HitResult hitResult) {
		super.onHit(hitResult);

		if (!(level() instanceof ServerLevel level)) return;

		var position = position();
		List<Vec3> raycasts = new ArrayList<>();
		int shrapnelCount = random.nextIntBetweenInclusive(9, 11);

		for (int i = 0; i < shrapnelCount; i++) {
			Vec3 raycast = Vec3.directionFromRotation(random.nextFloat() * 3600, random.nextFloat() * 1500 - 750).scale(4);

			var result = ProjectileUtil.getHitResult(position, this, entity -> entity instanceof LivingEntity, raycast, level, 0.05f, ClipContext.Block.COLLIDER);

			if (result.getType().equals(HitResult.Type.MISS)) {
				raycasts.add(raycast);
				continue;
			}

			if (result instanceof EntityHitResult entityHitResult) {
				entityHitResult.getEntity().hurtServer(level, damageSources().source(StellarityDamageTypes.VOID_ARROW_SHRAPNEL, getOwner()), 4);
			}

			raycasts.add(result.getLocation().subtract(position));
		}

		var payload = new ClientboundVoidArrowHitPayload(position, raycasts);
		for (var player : PlayerLookup.level(level)) {
			ServerPlayNetworking.send(player, payload);
		}

		discard();


	}
}
