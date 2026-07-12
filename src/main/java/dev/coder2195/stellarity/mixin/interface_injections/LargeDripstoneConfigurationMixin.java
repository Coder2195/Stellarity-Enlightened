package dev.coder2195.stellarity.mixin.interface_injections;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.LargeDripstoneConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import dev.coder2195.stellarity.interface_injection.ExtLargeDripstoneConfiguration;
import dev.coder2195.stellarity.util.CodecExtender;

import static dev.coder2195.stellarity.util.ValueUtil.from;

@Mixin(LargeDripstoneConfiguration.class)
public class LargeDripstoneConfigurationMixin implements ExtLargeDripstoneConfiguration {
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

	@ModifyExpressionValue(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/mojang/serialization/codecs/RecordCodecBuilder;create(Ljava/util/function/Function;)Lcom/mojang/serialization/Codec;"))
	private static Codec<LargeDripstoneConfiguration> extendCodec(Codec<LargeDripstoneConfiguration> original) {
		return CodecExtender.buildExtensionCodec(original, (instance, wrapper) -> instance.group(wrapper,
			BlockState.CODEC.optionalFieldOf("stellarity:block_state", from(Blocks.DRIPSTONE_BLOCK)).forGetter(ExtLargeDripstoneConfiguration::stellarity$blockState)
		).apply(instance, ExtLargeDripstoneConfiguration::apply), ExtLargeDripstoneConfiguration::applyDefaults);
	}
}
