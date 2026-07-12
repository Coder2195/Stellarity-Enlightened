package dev.coder2195.stellarity.client.registry;

import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.StellarityBlocks;
import dev.coder2195.stellarity.registry.block_entity.ColoredBlockEntity;

import java.util.List;

import static dev.coder2195.stellarity.registry.StellarityBlocks.ENDER_GRASS_BLOCK;

public interface StellarityBlockTintSources {
	static BlockTintSource coloredLeaves() {
		return new BlockTintSource() {
			@Override
			public int color(@NonNull BlockState state) {
				return 0;
			}

			@Override
			public int colorInWorld(@NonNull BlockState state, @NonNull BlockAndTintGetter level, @NonNull BlockPos pos) {
				if (state.hasBlockEntity() && level.getBlockEntity(pos) instanceof ColoredBlockEntity coloredBlockEntity)
					return coloredBlockEntity.getColor() | 0xff000000;
				return 0;
			}
		};


	}

	static void init() {
		BlockColorRegistry.register(List.of(BlockTintSources.grassBlock()), ENDER_GRASS_BLOCK);
		BlockColorRegistry.register(List.of(coloredLeaves()), StellarityBlocks.COLORED_LEAVES);

		Stellarity.LOGGER.info("Initialized Block Tint Sources");
	}
}
