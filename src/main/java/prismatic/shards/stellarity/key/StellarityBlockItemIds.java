package prismatic.shards.stellarity.key;

import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityBlockItemIds {
	BlockItemId ENDER_DIRT = id("ender_dirt");
	BlockItemId ENDER_GRASS_BLOCK = id("ender_grass_block");
	BlockItemId ASHEN_FROGLIGHT = id("ashen_froglight");
	BlockItemId ROOTED_ENDER_DIRT = id("rooted_ender_dirt");
	BlockItemId ENDER_DIRT_PATH = id("ender_dirt_path");
	BlockItemId ALTAR_OF_THE_ACCURSED = id("altar_of_the_accursed");
	BlockItemId DUSKBERRY_BUSH = id("duskberry_bush", "duskberry");
	BlockItemId ENDERITE_BLOCK = id("enderite_block");
	BlockItemId COARSE_ENDER_DIRT = id("coarse_ender_dirt");
	BlockItemId ALTAR_OF_THE_SACRED = id("altar_of_the_sacred");

	static BlockItemId id(String id) {
		return BlockItemId.create(Stellarity.id(id), Stellarity.id(id));
	}

	static BlockItemId id(String blockId, String itemId) {
		return BlockItemId.create(Stellarity.id(blockId), Stellarity.id(itemId));
	}
}
