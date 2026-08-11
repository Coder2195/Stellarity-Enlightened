package dev.coder2195.stellarity.mixin.interface_injections;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import dev.coder2195.stellarity.interface_injection.ExtAbstractArrow;


@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin extends Projectile implements ExtAbstractArrow {
	public AbstractArrowMixin(EntityType<? extends Projectile> entityType, Level level) {
		super(entityType, level);
	}

	@Inject(method = "doPostHurtEffects", at = @At("TAIL"))
	private void applyEffects(CallbackInfo ci, @Local(argsOnly = true, name = "mob") LivingEntity mob) {
		var effects = stellarity$mobEffects();
		for (var effect : effects) {
			mob.addEffect(effect, getOwner());
		}
	}

	@ModifyExpressionValue(method = "onHitEntity", at= @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;modifyDamage(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;F)F"))
	private float applyMultiplier(float original) {
		return (float) (original * stellarity$getDamageMultiplier());
	}
}
