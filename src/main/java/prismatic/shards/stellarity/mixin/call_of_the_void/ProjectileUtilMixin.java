package prismatic.shards.stellarity.mixin.call_of_the_void;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import prismatic.shards.stellarity.registry.StellarityItems;
import prismatic.shards.stellarity.registry.entity.VoidArrow;

@Mixin(ProjectileUtil.class)
public class ProjectileUtilMixin {
	@WrapOperation(method = "getMobArrow", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ArrowItem;createArrow(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/entity/projectile/arrow/AbstractArrow;"))
	private static AbstractArrow createVoidedArrow(ArrowItem instance, Level level, ItemStack itemStack, LivingEntity owner, ItemStack firedFromWeapon, Operation<AbstractArrow> original) {
		if (firedFromWeapon.is(StellarityItems.CALL_OF_THE_VOID))
			return new VoidArrow(level, owner, itemStack, firedFromWeapon);
		return original.call(instance, level, itemStack, owner, firedFromWeapon);
	}
}
