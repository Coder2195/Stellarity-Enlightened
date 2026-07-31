package dev.coder2195.stellarity.mixin.spellbook;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalDoubleRef;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.item.BookOfReturn;
import dev.coder2195.stellarity.item.BookOfUpdraft;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;
import dev.coder2195.stellarity.registry.StellarityItems;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	@Unique
	private Vec3 posOld;

	@Shadow
	protected abstract void updateFallFlying();

	@Shadow
	public abstract float getYHeadRot();

	@Shadow
	public abstract boolean isFallFlying();

	public LivingEntityMixin(EntityType<?> type, Level level) {
		super(type, level);
	}

	@Unique
	private Vec3 previousPos = null;
	@Unique
	private float rotation = 0;

	@Inject(method = "tick", at = @At("HEAD"))
	private void returnSpellTick(CallbackInfo ci) {
		var returnAt = getAttached(StellarityDataAttachments.RETURN_SPELL_AT);

		var here = this.position();
		if (returnAt == null) {
			previousPos = here;
			return;
		}

		var level = this.level();
		long remainingTime = Math.max(0, returnAt - level.getGameTime());


		rotation += 2f / (0.3f * remainingTime + 2.5f);

		if (level.isClientSide()) {
			for (Vec3 vec : BookOfReturn.PARTICLE_POSITIONS) {
				var transformed = vec.yRot(rotation).add(here);
				level.addParticle(BookOfReturn.RING_PARTICLE, transformed.x, transformed.y, transformed.z, 0, 0, 0);

			}

			double portalSpeed = 2.5 - remainingTime / (double) BookOfReturn.USE_DELAY;
			for (int i = 0; i < remainingTime / 35d; i++) {
				level.addParticle(ParticleTypes.PORTAL, here.x, here.y + 1.2, here.z, random.nextGaussian() * portalSpeed, random.nextGaussian() * portalSpeed, random.nextGaussian() * portalSpeed);
				level.addParticle(ParticleTypes.ENCHANT, here.x, here.y + 1.2, here.z, random.nextGaussian() * portalSpeed, random.nextGaussian() * portalSpeed, random.nextGaussian() * portalSpeed);
			}
		}

		if (remainingTime == 0) {
			removeAttached(StellarityDataAttachments.RETURN_SPELL_AT);
			if (((Object) this) instanceof Player player) {
				player.getCooldowns().addCooldown(new ItemStack(StellarityItems.BOOK_OF_RETURN), BookOfReturn.RECHARGE_TIME);

				if (player instanceof ServerPlayer serverPlayer) {
					serverPlayer.teleport(serverPlayer.findRespawnPositionAndUseSpawnBlock(false, _ ->
						serverPlayer.level().sendParticles(ColorParticleOption.create(ParticleTypes.FLASH, -1), serverPlayer.getX(), serverPlayer.getY() + 1.2, serverPlayer.getZ(), 1, 0, 0, 0, 0)
					));
				}
			}
		}

		if (previousPos != null && previousPos.distanceTo(here) > 0.1) {

			removeAttached(StellarityDataAttachments.RETURN_SPELL_AT);
		}

		previousPos = here;

	}

	@Definition(id = "hasEffect", method = "Lnet/minecraft/world/entity/LivingEntity;hasEffect(Lnet/minecraft/core/Holder;)Z")
	@Definition(id = "LEVITATION", field = "Lnet/minecraft/world/effect/MobEffects;LEVITATION:Lnet/minecraft/core/Holder;")
	@Expression("?.hasEffect(LEVITATION)")
	@WrapOperation(method = "aiStep", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
	private boolean updraftResetFall(LivingEntity instance, Holder<MobEffect> effect, Operation<Boolean> original) {
		if (original.call(instance, effect)) return true;
		var updraftLevitationUntil = instance.getAttached(StellarityDataAttachments.UPDRAFT_LEVITATION_UNTIL);
		var updraftSlowFalling = instance.getAttached(StellarityDataAttachments.UPDRAFT_SLOW_FALLING);
		return updraftLevitationUntil != null && instance.level().getGameTime() < updraftLevitationUntil || updraftSlowFalling != null;
	}

	@Definition(id = "hasEffect", method = "Lnet/minecraft/world/entity/LivingEntity;hasEffect(Lnet/minecraft/core/Holder;)Z")
	@Definition(id = "LEVITATION", field = "Lnet/minecraft/world/effect/MobEffects;LEVITATION:Lnet/minecraft/core/Holder;")
	@Expression("?.hasEffect(LEVITATION)")
	@WrapOperation(method = "canGlide", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
	private boolean preventGlide(LivingEntity instance, Holder<MobEffect> effect, Operation<Boolean> original) {
		if (original.call(instance, effect)) return true;
		var updraftLevitationUntil = instance.getAttached(StellarityDataAttachments.UPDRAFT_LEVITATION_UNTIL);
		return updraftLevitationUntil != null && instance.level().getGameTime() < updraftLevitationUntil;
	}

	@Definition(id = "LEVITATION", field = "Lnet/minecraft/world/effect/MobEffects;LEVITATION:Lnet/minecraft/core/Holder;")
	@Definition(id = "getEffect", method = "Lnet/minecraft/world/entity/LivingEntity;getEffect(Lnet/minecraft/core/Holder;)Lnet/minecraft/world/effect/MobEffectInstance;")
	@Expression("?.getEffect(LEVITATION)")
	@Inject(method = "travelInAir", at = @At("MIXINEXTRAS:EXPRESSION"))
	private void alterVelocity(Vec3 input, CallbackInfo ci, @Local(name = "movementY") LocalDoubleRef movementY) {
		var updraftLevitationUntil = getAttached(StellarityDataAttachments.UPDRAFT_LEVITATION_UNTIL);
		if (updraftLevitationUntil != null && level().getGameTime() < updraftLevitationUntil) movementY.set(movementY.get() + 0.5f);
	}

	@Unique
	private void drawTrail(Vec3 target, ParticleOptions particleOptions, double spacing) {

		var level = level();
		var delta = target.subtract(posOld);
		Stellarity.LOGGER.info("Trail drawn from {} to {} with delta {}", posOld, target, delta.length());
		var steps = (delta.length() / spacing);

		var stepVec = delta.scale(1 / steps);
		for (int i = 0; i < steps; i++) {
			level.addAlwaysVisibleParticle(particleOptions, true, posOld.x + i * stepVec.x, posOld.y + i * stepVec.y, posOld.z + i * stepVec.z, 0, 0, 0);
		}

		posOld = posOld.add(stepVec.scale((int) steps));
		Stellarity.LOGGER.info("stored {}", posOld);
	}

	@Unique
	private void drawCloudCircle(Vec3 position){
		var level = level();
		for (int i=0; i<50; i++) {
			var vec = position.add(new Vec3(2, 0, 0).yRot(i * Mth.TWO_PI / 50));
			level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, vec.x, vec.y, vec.z, 0, 0, 0);
		}
	}

	@Inject(method = "tick", at = @At("HEAD"))
	private void updraftSpellTick(CallbackInfo ci) {
		if (posOld == null) posOld = this.position();
		var level = level();
		var updraftGlidingUntil = getAttached(StellarityDataAttachments.UPDRAFT_GLIDING_UNTIL);
		var updraftLevitationUntil = getAttached(StellarityDataAttachments.UPDRAFT_LEVITATION_UNTIL);
		long gameTime = level.getGameTime();

		boolean drawedTrail = false;

		var updraftSlowFalling = getAttached(StellarityDataAttachments.UPDRAFT_SLOW_FALLING);
		if (updraftGlidingUntil != null) {
			if (gameTime >= updraftGlidingUntil) {
				removeAttached(StellarityDataAttachments.UPDRAFT_GLIDING_UNTIL);
				setAttached(StellarityDataAttachments.UPDRAFT_SLOW_FALLING, Unit.INSTANCE);
			} else if (level.isClientSide() ) {
				drawTrail(this.position(), ParticleTypes.END_ROD, 0.1);
				drawedTrail = true;
			}
		}
		if (updraftLevitationUntil != null) {
			var timeLeft =  updraftLevitationUntil - gameTime;
			if (timeLeft <= 0) {
				removeAttached(StellarityDataAttachments.UPDRAFT_LEVITATION_UNTIL);
				updateFallFlying();
				// 7 is fall flying tag
				setAttached(StellarityDataAttachments.UPDRAFT_GLIDING_UNTIL, gameTime + 7 * 20);
				this.setSharedFlag(7, true);
			} else if (level.isClientSide() && timeLeft % 2 == 1 && isFallFlying()) {
				drawCloudCircle(this.position());
			}
		}


		if (updraftSlowFalling != null) {
			if (onGround()) removeAttached(StellarityDataAttachments.UPDRAFT_SLOW_FALLING);
			else if (level.isClientSide()) {
				drawTrail(this.position(), ParticleTypes.CLOUD, 0.5);
				drawedTrail = true;
			}

			if ((((LivingEntity) (Object) this) instanceof Player player) && !level.isClientSide()) {
				var item = new ItemStack(StellarityItems.BOOK_OF_UPDRAFT);
				var cooldowns = player.getCooldowns();
				cooldowns.removeCooldown(cooldowns.getCooldownGroup(item));
				cooldowns.addCooldown(item, BookOfUpdraft.RECHARGE_TIME);
			}
		}
		if (!drawedTrail) posOld = this.position();
	}



	@Unique
	private boolean canUpdraftGlide() {
		var updraftGliding = getAttached(StellarityDataAttachments.UPDRAFT_GLIDING_UNTIL);
		return updraftGliding != null && level().getGameTime() < updraftGliding;
	}

	@ModifyExpressionValue(method = "canGlide", at= @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;canGlideUsing(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/EquipmentSlot;)Z"))
	private boolean addUpdraftGlide(boolean original) {
		return original || canUpdraftGlide();
	}

	@Definition(id = "checkFallFlyTicks", local = @Local(type = int.class, name = "checkFallFlyTicks"))
	@Expression("checkFallFlyTicks % 10 == 0")
	@ModifyExpressionValue(method = "updateFallFlying", at = @At("MIXINEXTRAS:EXPRESSION"))
	private boolean nonItemDurabilityGlide(boolean original) {
		return original && !canUpdraftGlide();
	}

	@ModifyExpressionValue(method = "getEffectiveGravity", at= @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hasEffect(Lnet/minecraft/core/Holder;)Z"))
	private boolean addUpdraftGravity(boolean original) {
		return original || getAttached(StellarityDataAttachments.UPDRAFT_SLOW_FALLING) != null;
	}
}
