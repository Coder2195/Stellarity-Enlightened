package prismatic.shards.stellarity.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityBlockTags {
	TagKey<Block> DUNE_SPEED_BLOCKS = id("dune_speed_blocks");
	TagKey<Block> DIRT = id("dirt");
	TagKey<Block> WORLDGEN_STALACTITE_REPLACEABLE = id("worldgen/stalactite_replaceable");
	TagKey<Block> WORLDGEN_END_STONE = id("worldgen/end_stone");
	TagKey<Block> WORLDGEN_AMETHYST_FOREST_BOTTOM = id("worldgen/amethyst_forest/bottom");
	TagKey<Block> WORLDGEN_GRASS_BLOCK = id("worldgen/grass_block");
	TagKey<Block> WORLDGEN_DIRT = id("worldgen/dirt");
	TagKey<Block> WORLDGEN_AMETHYST_GEODE_INVALID = id("worldgen/amethyst_geode_invalid");
	TagKey<Block> WORLDGEN_CARVER_REPLACEABLE = id("worldgen/carver_replaceable");
	TagKey<Block> WORLDGEN_ENDLESS_DUNES_DUNE_REPLACEABLE = id("worldgen/endless_dunes/dune_replaceable");
	TagKey<Block> WORLDGEN_ENDLESS_DUNES_OASIS_REPLACEABLE = id("worldgen/endless_dunes/oasis_replaceable");
	TagKey<Block> WORLDGEN_FIERY_HILLS_BASALT = id("worldgen/fiery_hills/basalt");
	TagKey<Block> WORLDGEN_FIERY_HILLS_BLACKSTONE = id("worldgen/fiery_hills/blackstone");
	TagKey<Block> WORLDGEN_FIERY_HILLS_END_STONE = id("worldgen/fiery_hills/end_stone");
	TagKey<Block> WORLDGEN_FIERY_HILLS_COMMON = id("worldgen/fiery_hills/common");
	TagKey<Block> WORLDGEN_FLESH_TUNDRA_SURFACE = id("worldgen/flesh_tundra/surface");
	TagKey<Block> WORLDGEN_FROZEN_SPIKES_SURFACE = id("worldgen/frozen_spikes/surface");
	TagKey<Block> WORLDGEN_FROZEN_MARSH_POND_REPLACEABLE = id("worldgen/frozen_marsh/pond_replaceable");
	TagKey<Block> WORLDGEN_SNOW_BLOCK = id("worldgen/snow_block");
	TagKey<Block> WORLDGEN_PRISMATIC_DUNES_SURFACE = id("worldgen/prismatic_dunes/surface");
	TagKey<Block> WORLDGEN_THE_NEST_SURFACE = id("worldgen/the_nest/surface");
	TagKey<Block> WORLDGEN_WARPED_MARSH_POND_REPLACEABLE = id("worldgen/warped_marsh/pond_replaceable");

	static TagKey<Block> id(String id) {
		return TagKey.create(Registries.BLOCK, Stellarity.id(id));
	}


}
