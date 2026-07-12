package dev.coder2195.stellarity.mixin.interface_injections;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.LargeDripstoneFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import dev.coder2195.stellarity.interface_injection.ExtLargeDripstone;

@Mixin(LargeDripstoneFeature.LargeDripstone.class)
public class LargeDripstoneMixin implements ExtLargeDripstone {
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
}
