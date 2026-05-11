package prismatic.shards.stellarity.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityBlockTags {
	TagKey<Block> DUNE_SPEED_BLOCKS = id("dune_speed_blocks");
	TagKey<Block> DIRT = id("dirt");
	TagKey<Block> WORLDGEN_STALACTITE = id("worldgen/stalactite");
	TagKey<Block> WORLDGEN_END_STONE = id("worldgen/end_stone");
	TagKey<Block> WORLDGEN_AMETHYST_FOREST_BOTTOM = id("worldgen/amethyst_forest/bottom");
	TagKey<Block> WORLDGEN_GRASS_BLOCK = id("worldgen/grass_block");
	TagKey<Block> WORLDGEN_AMETHYST_GEODE_INVALID = id("worldgen/amethyst_geode_invalid");
	TagKey<Block> WORLDGEN_CARVER = id("worldgen/carver");
	TagKey<Block> WORLDGEN_ENDLESS_DUNES_DUNE = id("worldgen/endless_dunes/dune");
	TagKey<Block> WORLDGEN_ENDLESS_DUNES_OASIS = id("worldgen/endless_dunes/oasis");
	TagKey<Block> WORLDGEN_FIERY_HILLS_BASALT = id("worldgen/fiery_hills/basalt");
	TagKey<Block> WORLDGEN_FIERY_HILLS_BLACKSTONE = id("worldgen/fiery_hills/blackstone");
	TagKey<Block> WORLDGEN_FIERY_HILLS_END_STONE = id("worldgen/fiery_hills/end_stone");
	TagKey<Block> WORLDGEN_FIERY_HILLS_COMMON = id("worldgen/fiery_hills/common");

	static TagKey<Block> id(String id) {
		return TagKey.create(Registries.BLOCK, Stellarity.id(id));
	}


}
