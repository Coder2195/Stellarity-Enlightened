package dev.coder2195.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import dev.coder2195.stellarity.Stellarity;

public interface StellarityConfiguredFeatures {

	ResourceKey<ConfiguredFeature<?, ?>> GLOBAL_STALACTITES = id("global/stalactites");
	ResourceKey<ConfiguredFeature<?, ?>> GLOBAL_FOSSIL = id("global/fossil");
	ResourceKey<ConfiguredFeature<?, ?>> GLOBAL_DUNGEON = id("global/dungeon");
	ResourceKey<ConfiguredFeature<?, ?>> GLOBAL_HANGING_ROOTS = id("end_highlands/roots");

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
	ResourceKey<ConfiguredFeature<?, ?>> ENDLESS_DUNES_OASIS_LAKE_VEGETATION = id("endless_dunes/oasis/lake_vegetation");
	ResourceKey<ConfiguredFeature<?, ?>> ENDLESS_DUNES_OASIS_MIDDLE_VEGETATION = id("endless_dunes/oasis/middle_vegetation");
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

	ResourceKey<ConfiguredFeature<?, ?>> FLESH_TUNDRA_NETHERRACK_BOTTOM = id("flesh_tundra/netherrack_bottom");
	ResourceKey<ConfiguredFeature<?, ?>> FLESH_TUNDRA_CRIMSON_DELTAS = id("flesh_tundra/crimson_deltas");
	ResourceKey<ConfiguredFeature<?, ?>> FLESH_TUNDRA_BONE_CEILING = id("flesh_tundra/bone_ceiling");
	ResourceKey<ConfiguredFeature<?, ?>> FLESH_TUNDRA_TREE = id("flesh_tundra/tree");
	ResourceKey<ConfiguredFeature<?, ?>> FLESH_TUNDRA_RIB = id("flesh_tundra/rib");
	ResourceKey<ConfiguredFeature<?, ?>> FLESH_TUNDRA_TREE_PATCH = id("flesh_tundra/tree_patch");
	ResourceKey<ConfiguredFeature<?, ?>> FLESH_TUNDRA_VEGETATION = id("flesh_tundra/vegetation");
	ResourceKey<ConfiguredFeature<?, ?>> FLESH_TUNDRA_VINES = id("flesh_tundra/vines");
	ResourceKey<ConfiguredFeature<?, ?>> FLESH_TUNDRA_ROOTS = id("flesh_tundra/roots");

	ResourceKey<ConfiguredFeature<?, ?>> FROSTED_VALLEY_HILLS = id("frosted_valley/hills");

	ResourceKey<ConfiguredFeature<?, ?>> FROZEN_MARSH_POND = id("frozen_marsh/pond");
	ResourceKey<ConfiguredFeature<?, ?>> FROZEN_MARSH_POND_VEGETATION = id("frozen_marsh/pond_vegetation");
	ResourceKey<ConfiguredFeature<?, ?>> FROZEN_MARSH_VEGETATION = id("frozen_marsh/vegetation");

	ResourceKey<ConfiguredFeature<?, ?>> FROZEN_SHRUBLANDS_DIRT = id("frozen_shrublands/dirt");
	ResourceKey<ConfiguredFeature<?, ?>> FROZEN_SHRUBLANDS_SHRUB = id("frozen_shrublands/shrub");

	ResourceKey<ConfiguredFeature<?, ?>> FROZEN_SPIKES_LARGE_DRIPSTONE = id("frozen_spikes/large_dripstone");
	ResourceKey<ConfiguredFeature<?, ?>> FROZEN_SPIKES_BLUE_ICE_ORE = id("frozen_spikes/blue_ice_ore");

	ResourceKey<ConfiguredFeature<?, ?>> FROZEN_SPIKES_HILLS = id("frozen_spikes/hills");
	ResourceKey<ConfiguredFeature<?, ?>> FROZEN_SPIKES_POWDER_SNOW_ORE = id("frozen_spikes/powder_snow_ore");
	ResourceKey<ConfiguredFeature<?, ?>> FROZEN_SPIKES_ICE_SPIKE = id("frozen_spikes/ice_spike");

	ResourceKey<ConfiguredFeature<?, ?>> HALLOWED_TUNDRA_LAKE = id("hallowed_tundra/lake");
	ResourceKey<ConfiguredFeature<?, ?>> HALLOWED_TUNDRA_PINE_TREE = id("hallowed_tundra/pine_tree");
	ResourceKey<ConfiguredFeature<?, ?>> HALLOWED_TUNDRA_TREE = id("hallowed_tundra/tree");

	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_CRYSTAL_ROOTS = id("the_hallow/crystal_roots");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_ROCK = id("the_hallow/rock");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_BUSH = id("the_hallow/bush");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_SCATTERED_BUSH = id("the_hallow/scattered_bush");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_OAK_TREE = id("the_hallow/oak_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_RED_REGULAR_TREE = id("the_hallow/red_regular_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_RED_JUNGLE_TREE = id("the_hallow/red_jungle_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_RED_PINE_TREE = id("the_hallow/red_pine_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_ORANGE_REGULAR_TREE = id("the_hallow/orange_regular_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_ORANGE_JUNGLE_TREE = id("the_hallow/orange_jungle_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_ORANGE_PINE_TREE = id("the_hallow/orange_pine_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_YELLOW_REGULAR_TREE = id("the_hallow/yellow_regular_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_YELLOW_JUNGLE_TREE = id("the_hallow/yellow_jungle_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_YELLOW_PINE_TREE = id("the_hallow/yellow_pine_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_LIME_REGULAR_TREE = id("the_hallow/lime_regular_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_LIME_JUNGLE_TREE = id("the_hallow/lime_jungle_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_LIME_PINE_TREE = id("the_hallow/lime_pine_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_GREEN_REGULAR_TREE = id("the_hallow/green_regular_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_GREEN_JUNGLE_TREE = id("the_hallow/green_jungle_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_GREEN_PINE_TREE = id("the_hallow/green_pine_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_CYAN_REGULAR_TREE = id("the_hallow/cyan_regular_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_CYAN_JUNGLE_TREE = id("the_hallow/cyan_jungle_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_CYAN_PINE_TREE = id("the_hallow/cyan_pine_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_LIGHT_BLUE_REGULAR_TREE = id("the_hallow/light_blue_regular_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_LIGHT_BLUE_JUNGLE_TREE = id("the_hallow/light_blue_jungle_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_LIGHT_BLUE_PINE_TREE = id("the_hallow/light_blue_pine_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_BLUE_REGULAR_TREE = id("the_hallow/blue_regular_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_BLUE_JUNGLE_TREE = id("the_hallow/blue_jungle_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_BLUE_PINE_TREE = id("the_hallow/blue_pine_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_PURPLE_REGULAR_TREE = id("the_hallow/purple_regular_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_PURPLE_JUNGLE_TREE = id("the_hallow/purple_jungle_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_PURPLE_PINE_TREE = id("the_hallow/purple_pine_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_MAGENTA_REGULAR_TREE = id("the_hallow/magenta_regular_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_MAGENTA_JUNGLE_TREE = id("the_hallow/magenta_jungle_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_MAGENTA_PINE_TREE = id("the_hallow/magenta_pine_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_PINK_REGULAR_TREE = id("the_hallow/pink_regular_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_PINK_JUNGLE_TREE = id("the_hallow/pink_jungle_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_PINK_PINE_TREE = id("the_hallow/pink_pine_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_WHITE_REGULAR_TREE = id("the_hallow/white_regular_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_WHITE_JUNGLE_TREE = id("the_hallow/white_jungle_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_WHITE_PINE_TREE = id("the_hallow/white_pine_tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_LANTERN = id("the_hallow/lantern");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_DIORITE_BOTTOM = id("the_hallow/diorite_bottom");

	ResourceKey<ConfiguredFeature<?, ?>> PRISMARINE_FOREST_POND = id("prismarine_forest/pond");
	ResourceKey<ConfiguredFeature<?, ?>> PRISMARINE_FOREST_FLOWER = id("prismarine_forest/flower");
	ResourceKey<ConfiguredFeature<?, ?>> PRISMARINE_FOREST_GRASS = id("prismarine_forest/grass");
	ResourceKey<ConfiguredFeature<?, ?>> PRISMARINE_FOREST_TREE = id("prismarine_forest/tree");

	ResourceKey<ConfiguredFeature<?, ?>> PRISMATIC_DUNES_DELTA = id("prismatic_dunes/delta");
	ResourceKey<ConfiguredFeature<?, ?>> PRISMATIC_DUNES_GLASS_SPIKE = id("prismatic_dunes/glass_spike");
	ResourceKey<ConfiguredFeature<?, ?>> PRISMATIC_DUNES_GRASS_PATCH = id("prismatic_dunes/grass_patch");

	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_TREE = id("the_hallow/tree");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_LAKE = id("the_hallow/lake");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_GROUND_FLOWER = id("the_hallow/ground_flower");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_FLOWER = id("the_hallow/flower");
	ResourceKey<ConfiguredFeature<?, ?>> THE_HALLOW_GRASS = id("the_hallow/grass");

	ResourceKey<ConfiguredFeature<?, ?>> THE_NEST_DEEPSLATE = id("the_nest/deepslate");
	ResourceKey<ConfiguredFeature<?, ?>> THE_NEST_TUFF = id("the_nest/tuff");
	ResourceKey<ConfiguredFeature<?, ?>> THE_NEST_DRAGON_EGG = id("the_nest/dragon_egg");
	ResourceKey<ConfiguredFeature<?, ?>> THE_NEST_DEAD_CORAL = id("the_nest/dead_coral");
	ResourceKey<ConfiguredFeature<?, ?>> THE_NEST_TRANSITION = id("the_nest/transition");

	ResourceKey<ConfiguredFeature<?, ?>> WARPED_MARSH_POND = id("warped_marsh/pond");
	ResourceKey<ConfiguredFeature<?, ?>> WARPED_MARSH_WATER_VEGETATION = id("warped_marsh/water_vegetation");
	ResourceKey<ConfiguredFeature<?, ?>> WARPED_MARSH_VEGETATION = id("warped_marsh/vegetation");
	ResourceKey<ConfiguredFeature<?, ?>> WARPED_MARSH_TREE = id("warped_marsh/tree");
	ResourceKey<ConfiguredFeature<?, ?>> WARPED_MARSH_WATER_TREE = id("warped_marsh/water_tree");
	ResourceKey<ConfiguredFeature<?, ?>> WARPED_MARSH_SLIME = id("warped_marsh/slime");
	ResourceKey<ConfiguredFeature<?, ?>> WARPED_MARSH_HANGING_FROGLIGHT = id("warped_marsh/hanging_froglight");


	static ResourceKey<ConfiguredFeature<?, ?>> id(String id) {
		return Stellarity.key(Registries.CONFIGURED_FEATURE, id);
	}
}
