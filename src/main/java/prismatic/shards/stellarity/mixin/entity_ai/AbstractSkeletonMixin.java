package prismatic.shards.stellarity.mixin.entity_ai;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
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
}
