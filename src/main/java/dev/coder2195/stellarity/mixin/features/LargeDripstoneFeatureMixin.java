package dev.coder2195.stellarity.mixin.features;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.level.levelgen.feature.LargeDripstoneFeature;
import net.minecraft.world.level.levelgen.feature.configurations.LargeDripstoneConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LargeDripstoneFeature.class)
public class LargeDripstoneFeatureMixin {
	@ModifyExpressionValue(method = "place", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/feature/LargeDripstoneFeature;makeDripstone(Lnet/minecraft/core/BlockPos;ZLnet/minecraft/util/RandomSource;ILnet/minecraft/util/valueproviders/FloatProvider;Lnet/minecraft/util/valueproviders/FloatProvider;)Lnet/minecraft/world/level/levelgen/feature/LargeDripstoneFeature$LargeDripstone;"))
	private LargeDripstoneFeature.LargeDripstone passDownBlockState(LargeDripstoneFeature.LargeDripstone original, @Local(name = "config") LargeDripstoneConfiguration config) {
		original.stellarity$setBlockState(config.stellarity$blockState());
		return original;
	}

}
