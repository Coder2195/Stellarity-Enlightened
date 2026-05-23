package prismatic.shards.stellarity.mixin.call_of_the_void;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import prismatic.shards.stellarity.tags.StellarityItemTags;

@Mixin(AbstractSkeleton.class)
public abstract class AbstractSkeletonMixin extends Monster {
	@Shadow
	public abstract boolean canUseNonMeleeWeapon(@NonNull ItemStack item);

	protected AbstractSkeletonMixin(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	@ModifyExpressionValue(method = "reassessWeaponGoal", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
	private boolean useCanUseNonMeleeWeapon(boolean original) {
		return canUseNonMeleeWeapon(getItemInHand(InteractionHand.MAIN_HAND)) || canUseNonMeleeWeapon(getItemInHand(InteractionHand.OFF_HAND)) || original;
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

	@WrapMethod(method = "canUseNonMeleeWeapon")
	public boolean canUseStellarityBows(ItemStack item, Operation<Boolean> original) {
		return item.is(StellarityItemTags.BOWS) || original.call(item);
	}

}
