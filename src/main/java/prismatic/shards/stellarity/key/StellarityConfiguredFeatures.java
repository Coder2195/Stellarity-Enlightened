package prismatic.shards.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityConfiguredFeatures {

	ResourceKey<ConfiguredFeature<?, ?>> GLOBAL_STALACTITES = id("global/stalactites");
	ResourceKey<ConfiguredFeature<?, ?>> GLOBAL_FOSSIL = id("global/fossil");
	ResourceKey<ConfiguredFeature<?, ?>> GLOBAL_DUNGEON = id("global/dungeon");

	ResourceKey<ConfiguredFeature<?, ?>> MAIN_ISLAND_RING = id("main_island/ring");
	ResourceKey<ConfiguredFeature<?, ?>> MAIN_ISLAND_PORTAL_PLATFORM = id("main_island/portal_platform");
	ResourceKey<ConfiguredFeature<?, ?>> MAIN_ISLAND_OBSIDIAN = id("main_island/obsidian");
	ResourceKey<ConfiguredFeature<?, ?>> MAIN_ISLAND_PATCH = id("main_island/patch");

	ResourceKey<ConfiguredFeature<?, ?>> END_BARRENS_HILLS = id("end_barrens/hills");

	ResourceKey<ConfiguredFeature<?, ?>> END_MIDLANDS_OBSIDIAN_SPIKE = id("end_midlands/obsidian_spike");
	ResourceKey<ConfiguredFeature<?, ?>> END_MIDLANDS_ROCK = id("end_midlands/rock");
	ResourceKey<ConfiguredFeature<?, ?>> END_MIDLANDS_VEGETATION = id("end_midlands/vegetation");

	ResourceKey<ConfiguredFeature<?, ?>> END_HIGHLANDS_LARGE_DIRT_PATCH = id("end_highlands/large_dirt_patch");
	ResourceKey<ConfiguredFeature<?, ?>> END_HIGHLANDS_SMALL_DIRT_PATCH = id("end_highlands/small_dirt_patch");
	ResourceKey<ConfiguredFeature<?, ?>> END_HIGHLANDS_CHORUS_BUD = id("end_highlands/chorus_bud");
	ResourceKey<ConfiguredFeature<?, ?>> END_HIGHLANDS_PITCHER_PLANT = id("end_highlands/pitcher_plant");
	ResourceKey<ConfiguredFeature<?, ?>> END_HIGHLANDS_GRASS = id("end_highlands/grass");
	ResourceKey<ConfiguredFeature<?, ?>> END_HIGHLANDS_ROOTS = id("end_highlands/roots");
	ResourceKey<ConfiguredFeature<?, ?>> END_HIGHLANDS_CHORUS_LEAF = id("end_highlands/chorus_leaf");
	ResourceKey<ConfiguredFeature<?, ?>> END_HIGHLANDS_BUSH = id("end_highlands/bush");

	ResourceKey<ConfiguredFeature<?, ?>> AMETHYST_FOREST_CALCITE_BOTTOM = id("amethyst_forest/calcite_bottom");
	ResourceKey<ConfiguredFeature<?, ?>> AMETHYST_FOREST_AMETHYST_GEODE = id("amethyst_forest/amethyst_geode");
	ResourceKey<ConfiguredFeature<?, ?>> AMETHYST_FOREST_TUFF_ROCK = id("amethyst_forest/tuff_rock");
	ResourceKey<ConfiguredFeature<?, ?>> AMETHYST_FOREST_OBSIDIAN = id("amethyst_forest/obsidian");
	ResourceKey<ConfiguredFeature<?, ?>> AMETHYST_FOREST_DIRT = id("amethyst_forest/dirt");
	ResourceKey<ConfiguredFeature<?, ?>> AMETHYST_FOREST_TREE = id("amethyst_forest/tree");
	ResourceKey<ConfiguredFeature<?, ?>> AMETHYST_FOREST_CRYSTAL_GRASS = id("amethyst_forest/crystal_grass");
	ResourceKey<ConfiguredFeature<?, ?>> AMETHYST_FOREST_FLOWER = id("amethyst_forest/flower");

	ResourceKey<ConfiguredFeature<?, ?>> ASHFALL_DELTAS_WATER_DELTA = id("ashfall_deltas/water_delta");
	ResourceKey<ConfiguredFeature<?, ?>> ASHFALL_DELTAS_GRASS_DELTA = id("ashfall_deltas/grass_delta");
	ResourceKey<ConfiguredFeature<?, ?>> ASHFALL_DELTAS_BASALT_COLUMNS = id("ashfall_deltas/basalt_columns");
	ResourceKey<ConfiguredFeature<?, ?>> ASHFALL_DELTAS_SEAGRASS = id("ashfall_deltas/sea_grass");
	ResourceKey<ConfiguredFeature<?, ?>> ASHFALL_DELTAS_TREE = id("ashfall_deltas/tree");
	ResourceKey<ConfiguredFeature<?, ?>> ASHFALL_DELTAS_VEGETATION = id("ashfall_deltas/vegetation");
	ResourceKey<ConfiguredFeature<?, ?>> ASHFALL_DELTAS_GRASS = id("ashfall_deltas/grass");
	ResourceKey<ConfiguredFeature<?, ?>> ASHFALL_DELTAS_ASH_PILE = id("ashfall_deltas/ash_pile");

	ResourceKey<ConfiguredFeature<?, ?>> CRYSTAL_CRAGS_HILLS = id("crystal_crags/hills");
	ResourceKey<ConfiguredFeature<?, ?>> CRYSTAL_CRAGS_CRYSTAL_ROOTS = id("crystal_crags/crystal_roots");
	ResourceKey<ConfiguredFeature<?, ?>> CRYSTAL_CRAGS_AMETHYST_CRYSTAL = id("crystal_crags/amethyst_crystal");
	ResourceKey<ConfiguredFeature<?, ?>> CRYSTAL_CRAGS_AMETHYST_DELTA = id("crystal_crags/amethyst_delta");
	ResourceKey<ConfiguredFeature<?, ?>> CRYSTAL_CRAGS_GRASS_DELTA = id("crystal_crags/grass_delta");
	ResourceKey<ConfiguredFeature<?, ?>> CRYSTAL_CRAGS_BUDDING_AMETHYST_ORE = id("crystal_crags/budding_amethyst_ore");
	ResourceKey<ConfiguredFeature<?, ?>> CRYSTAL_CRAGS_CRYSTAL_GRASS = id("crystal_crags/crystal_grass");
	ResourceKey<ConfiguredFeature<?, ?>> CRYSTAL_CRAGS_GRASS = id("crystal_crags/grass");

	ResourceKey<ConfiguredFeature<?, ?>> END_SHRUBLAND_GRASS = id("end_shrubland/grass");
	ResourceKey<ConfiguredFeature<?, ?>> END_SHRUBLAND_SHRUB = id("end_shrubland/shrub");

	ResourceKey<ConfiguredFeature<?, ?>> END_WILDS_DIRT = id("end_wilds/dirt");
	ResourceKey<ConfiguredFeature<?, ?>> END_WILDS_TREE = id("end_wilds/trees");
	ResourceKey<ConfiguredFeature<?, ?>> END_WILDS_GRASS = id("end_wilds/grass");
	ResourceKey<ConfiguredFeature<?, ?>> END_WILDS_BUSH = id("end_wilds/bush");

	ResourceKey<ConfiguredFeature<?, ?>> ENDER_WASTES_HILLS = id("ender_wastes/hills");

	ResourceKey<ConfiguredFeature<?, ?>> ENDLESS_DUNES_SAND_DELTA = id("endless_dunes/sand_delta");
	ResourceKey<ConfiguredFeature<?, ?>> ENDLESS_DUNES_VEGETATION = id("endless_dunes/vegetation");
	ResourceKey<ConfiguredFeature<?, ?>> ENDLESS_DUNES_GRASS = id("endless_dunes/grass");
	ResourceKey<ConfiguredFeature<?, ?>> ENDLESS_DUNES_OASIS = id("endless_dunes/oasis");
	ResourceKey<ConfiguredFeature<?, ?>> ENDLESS_DUNES_OASIS_PALM_TREE = id("endless_dunes/oasis/palm_tree");
	ResourceKey<ConfiguredFeature<?, ?>> ENDLESS_DUNES_OASIS_LAKE = id("endless_dunes/oasis/lake");
	ResourceKey<ConfiguredFeature<?, ?>> ENDLESS_DUNES_OASIS_VEGETATION_LAKE = id("endless_dunes/oasis/vegetation_lake");
	ResourceKey<ConfiguredFeature<?, ?>> ENDLESS_DUNES_OASIS_VEGETATION_MIDDLE = id("endless_dunes/oasis/vegetation_middle");
	ResourceKey<ConfiguredFeature<?, ?>> ENDLESS_DUNES_OASIS_ROCK = id("endless_dunes/oasis/rock");
	ResourceKey<ConfiguredFeature<?, ?>> ENDLESS_DUNES_OASIS_DIRT = id("endless_dunes/oasis/dirt");
	ResourceKey<ConfiguredFeature<?, ?>> ENDLESS_DUNES_OASIS_VEGETATION = id("endless_dunes/oasis/vegetation");

	ResourceKey<ConfiguredFeature<?, ?>> FIERY_HILLS_HILLS = id("fiery_hills/hills");
	ResourceKey<ConfiguredFeature<?, ?>> FIERY_HILLS_BLACKSTONE_HILLS = id("fiery_hills/blackstone_hills");
	ResourceKey<ConfiguredFeature<?, ?>> FIERY_HILLS_BASALT_HILLS = id("fiery_hills/basalt_hills");
	ResourceKey<ConfiguredFeature<?, ?>> FIERY_HILLS_SAND_DELTA = id("fiery_hills/sand_delta");
	ResourceKey<ConfiguredFeature<?, ?>> FIERY_HILLS_LAVA_DELTA = id("fiery_hills/lava_delta");
	ResourceKey<ConfiguredFeature<?, ?>> FIERY_HILLS_GOLD_ORE = id("fiery_hills/gold_ore");
	ResourceKey<ConfiguredFeature<?, ?>> FIERY_HILLS_MAGMA_ORE = id("fiery_hills/magma_ore");
	ResourceKey<ConfiguredFeature<?, ?>> FIERY_HILLS_SAND = id("fiery_hills/sand");
	ResourceKey<ConfiguredFeature<?, ?>> FIERY_HILLS_VENT = id("fiery_hills/vent");
	ResourceKey<ConfiguredFeature<?, ?>> FIERY_HILLS_FIRE = id("fiery_hills/fire");
	ResourceKey<ConfiguredFeature<?, ?>> FIERY_HILLS_TREE = id("fiery_hills/tree");
	ResourceKey<ConfiguredFeature<?, ?>> FIERY_HILLS_VEGETATION = id("fiery_hills/vegetation");


	static ResourceKey<ConfiguredFeature<?, ?>> id(String name) {
		return Stellarity.key(Registries.CONFIGURED_FEATURE, name);
	}
}
