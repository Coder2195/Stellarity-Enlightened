package dev.coder2195.stellarity.client.registry;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.StellarityBlocks;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.minecraft.client.color.block.BlockTintSources;

import java.util.List;

import static dev.coder2195.stellarity.registry.StellarityBlocks.ENDER_GRASS_BLOCK;

public interface StellarityBlockTintSources {
	static void init() {
		BlockColorRegistry.register(List.of(BlockTintSources.grassBlock()), ENDER_GRASS_BLOCK);
		BlockColorRegistry.register(List.of(BlockTintSources.foliage()), StellarityBlocks.TINTED_LEAVES);

		Stellarity.LOGGER.info("Initialized Block Tint Sources");
	}
}
