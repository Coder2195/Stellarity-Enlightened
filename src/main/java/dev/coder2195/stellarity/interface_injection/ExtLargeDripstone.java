package dev.coder2195.stellarity.interface_injection;

import net.minecraft.world.level.block.state.BlockState;

public interface ExtLargeDripstone {
	default BlockState stellarity$blockState() {
		throw new AssertionError("Not transformed!");
	}

	default void stellarity$setBlockState(BlockState blockState) {
		throw new AssertionError("Not transformed!");
	}
}
