package dev.coder2195.stellarity.mixin.bow_use;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.illager.AbstractIllager;
import net.minecraft.world.entity.monster.illager.Illusioner;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Illusioner.class)
public abstract class IllusionerMixin extends AbstractIllager {

	protected IllusionerMixin(EntityType<? extends AbstractIllager> type, Level level) {
		super(type, level);
	}

	@ModifyExpressionValue(method = "performRangedAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/ProjectileUtil;getWeaponHoldingHand(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/Item;)Lnet/minecraft/world/InteractionHand;"))
	private InteractionHand modify(InteractionHand original) {
		if (canUseNonMeleeWeapon(getMainHandItem())) {
			return InteractionHand.MAIN_HAND;
		} else if (canUseNonMeleeWeapon(getOffhandItem())) {
			return InteractionHand.OFF_HAND;
		}
		return original;
	}


}
