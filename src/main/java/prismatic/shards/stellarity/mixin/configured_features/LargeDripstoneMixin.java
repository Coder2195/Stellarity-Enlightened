package prismatic.shards.stellarity.mixin.configured_features;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.LargeDripstoneFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import prismatic.shards.stellarity.interface_injection.ExtLargeDripstone;

@Mixin(LargeDripstoneFeature.LargeDripstone.class)
public class LargeDripstoneMixin implements ExtLargeDripstone {
	@ModifyExpressionValue(method = "placeBlocks", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/Block;defaultBlockState()Lnet/minecraft/world/level/block/state/BlockState;"))
	private BlockState customBlockPlacement(BlockState original) {
		return stellarity$blockState();
	}
}
