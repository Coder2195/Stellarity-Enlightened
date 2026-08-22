package dev.coder2195.stellarity.mixin.interface_injections;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.coder2195.stellarity.interface_injection.ExtLargeDripstone;
import dev.coder2195.stellarity.interface_injection.ExtLargeDripstoneFeature;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.LargeDripstoneFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Function;

@Mixin(LargeDripstoneFeature.class)
public class LargeDripstoneFeatureMixin implements ExtLargeDripstone {
	@Unique
	private BlockState blockState;

	@Override
	public void stellarity$setBlockState(BlockState blockState) {
		this.blockState = blockState;
	}

	@Override
	public BlockState stellarity$blockState() {
		return blockState;
	}

	@ModifyExpressionValue(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/mojang/serialization/codecs/RecordCodecBuilder;mapCodec(Ljava/util/function/Function;)Lcom/mojang/serialization/MapCodec;"))
	private static MapCodec<LargeDripstoneFeature> extendCodec(MapCodec<LargeDripstoneFeature> original) {
		return RecordCodecBuilder.mapCodec(instance -> instance.group(
			original.forGetter(Function.identity()),
			BlockState.CODEC.fieldOf("block_state").forGetter(LargeDripstoneFeature::stellarity$blockState)
		).apply(instance, ExtLargeDripstoneFeature::apply));
	}
}
