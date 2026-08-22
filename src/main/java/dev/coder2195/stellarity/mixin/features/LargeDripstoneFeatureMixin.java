package dev.coder2195.stellarity.mixin.features;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.coder2195.stellarity.interface_injection.ExtLargeDripstone;
import net.minecraft.world.level.levelgen.feature.LargeDripstoneFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LargeDripstoneFeature.class)
public class LargeDripstoneFeatureMixin implements ExtLargeDripstone {
	@ModifyExpressionValue(method = "place", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/feature/LargeDripstoneFeature;makeDripstone(Lnet/minecraft/core/BlockPos;ZLnet/minecraft/util/RandomSource;ILnet/minecraft/util/valueproviders/FloatProvider;Lnet/minecraft/util/valueproviders/FloatProvider;)Lnet/minecraft/world/level/levelgen/feature/LargeDripstoneFeature$LargeDripstone;"))
	private LargeDripstoneFeature.LargeDripstone passDownBlockState(LargeDripstoneFeature.LargeDripstone original) {
		original.stellarity$setBlockState(this.stellarity$blockState());
		return original;
	}
}
