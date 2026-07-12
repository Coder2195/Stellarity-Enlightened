package dev.coder2195.stellarity.mixin.bow_use;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RangedBowAttackGoal.class)
public abstract class RangedBowAttackGoalMixin<T extends Monster & RangedAttackMob> extends Goal {
	@Shadow
	@Final
	private T mob;

	@WrapMethod(method = "isHoldingBow")
	private boolean expandBowHolding(Operation<Boolean> original) {
		return mob.canUseNonMeleeWeapon(mob.getMainHandItem()) || mob.canUseNonMeleeWeapon(mob.getOffhandItem()) || original.call();
	}

	@ModifyExpressionValue(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/ProjectileUtil;getWeaponHoldingHand(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/Item;)Lnet/minecraft/world/InteractionHand;"))
	private InteractionHand modify(InteractionHand original) {
		if (mob.canUseNonMeleeWeapon(mob.getMainHandItem())) {
			return InteractionHand.MAIN_HAND;
		} else if (mob.canUseNonMeleeWeapon(mob.getOffhandItem())) {
			return InteractionHand.OFF_HAND;
		}
		return original;
	}
}
