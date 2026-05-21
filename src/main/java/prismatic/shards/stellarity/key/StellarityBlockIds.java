package prismatic.shards.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityBlockIds {
	ResourceKey<Block> ENDER_DIRT = id("ender_dirt");
	ResourceKey<Block> ENDER_GRASS_BLOCK = id("ender_grass_block");
	ResourceKey<Block> ASHEN_FROGLIGHT = id("ashen_froglight");
	ResourceKey<Block> ROOTED_ENDER_DIRT = id("rooted_ender_dirt");
	ResourceKey<Block> ENDER_DIRT_PATH = id("ender_dirt_path");
	ResourceKey<Block> ALTAR_OF_THE_ACCURSED = id("altar_of_the_accursed");
	ResourceKey<Block> DUSKBERRY_BUSH = id("duskberry_bush");
	ResourceKey<Block> ENDERITE_BLOCK = id("enderite_block");
	ResourceKey<Block> COARSE_ENDER_DIRT = id("coarse_ender_dirt");

	static ResourceKey<Block> id(String id) {
		return Stellarity.key(Registries.BLOCK, id);
	}
}
