package prismatic.shards.stellarity.interface_injection;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.LargeDripstoneConfiguration;

import static prismatic.shards.stellarity.util.ValueUtil.from;

public interface ExtLargeDripstoneConfiguration extends ExtLargeDripstone {
	static LargeDripstoneConfiguration apply(LargeDripstoneConfiguration config, BlockState blockState) {
		config.stellarity$setBlockState(blockState);
		return config;
	}

	static LargeDripstoneConfiguration applyDefaults(LargeDripstoneConfiguration config) {
		return apply(config, from(Blocks.DRIPSTONE_BLOCK));
	}
}
