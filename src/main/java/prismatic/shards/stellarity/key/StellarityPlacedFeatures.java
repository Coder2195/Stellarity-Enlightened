package prismatic.shards.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityPlacedFeatures {
	ResourceKey<PlacedFeature> NOTHING = id("nothing");
	ResourceKey<PlacedFeature> GLOBAL_STALACTITES = id("global/stalactites");
	ResourceKey<PlacedFeature> GLOBAL_FOSSILS = id("global/fossils");
	ResourceKey<PlacedFeature> GLOBAL_DUNGEONS = id("global/dungeons");
	ResourceKey<PlacedFeature> GLOBAL_FREEZE_WATER = id("global/freeze_water");

	ResourceKey<PlacedFeature> MAIN_ISLAND_RING = id("main_island/ring");
	ResourceKey<PlacedFeature> MAIN_ISLAND_PORTAL_PLATFORM = id("main_island/portal_platform");
	ResourceKey<PlacedFeature> MAIN_ISLAND_HILLS = id("main_island/hills");
	ResourceKey<PlacedFeature> MAIN_ISLAND_OBSIDIAN = id("main_island/obsidian");
	ResourceKey<PlacedFeature> MAIN_ISLAND_PATCHES = id("main_island/patches");
	ResourceKey<PlacedFeature> MAIN_ISLAND_CHORUS_PLANTS = id("main_island/chorus_plants");

	ResourceKey<PlacedFeature> END_BARRENS_HILLS = id("end_barrens/hills");
	ResourceKey<PlacedFeature> END_BARRENS_STALACTITES = id("end_barrens/stalactites");

	ResourceKey<PlacedFeature> END_MIDLANDS_OBSIDIAN_SPIKES = id("end_midlands/obsidian_spikes");
	ResourceKey<PlacedFeature> END_MIDLANDS_ROCKS = id("end_midlands/rocks");
	ResourceKey<PlacedFeature> END_MIDLANDS_VEGETATION = id("end_midlands/vegetation");
	ResourceKey<PlacedFeature> END_MIDLANDS_CHORUS_PLANTS = id("end_midlands/chorus_plants");

	ResourceKey<PlacedFeature> END_HIGHLANDS_SMALL_DIRT_PATCHES = id("end_highlands/small_dirt_patches");
	ResourceKey<PlacedFeature> END_HIGHLANDS_LARGE_DIRT_PATCHES = id("end_highlands/large_dirt_patches");
	ResourceKey<PlacedFeature> END_HIGHLANDS_CHORUS_BUDS = id("end_highlands/chorus_buds");
	ResourceKey<PlacedFeature> END_HIGHLANDS_CHORUS_PLANTS = id("end_highlands/chorus_plants");
	ResourceKey<PlacedFeature> END_HIGHLANDS_PITCHER_PLANTS = id("end_highlands/pitcher_plants");
	ResourceKey<PlacedFeature> END_HIGHLANDS_GRASS = id("end_highlands/grass");
	ResourceKey<PlacedFeature> END_HIGHLANDS_ROOTS = id("end_highlands/roots");
	ResourceKey<PlacedFeature> END_HIGHLANDS_CHORUS_LEAVES = id("end_highlands/chorus_leaves");
	ResourceKey<PlacedFeature> END_HIGHLANDS_BUSHES = id("end_highlands/bushes");

	ResourceKey<PlacedFeature> AMETHYST_FOREST_CALCITE_BOTTOM = id("amethyst_forest/calcite_bottom");
	ResourceKey<PlacedFeature> AMETHYST_FOREST_AMETHYST_GEODES = id("amethyst_forest/amethyst_geodes");
	ResourceKey<PlacedFeature> AMETHYST_FOREST_TUFF_ROCKS = id("amethyst_forest/tuff_rocks");
	ResourceKey<PlacedFeature> AMETHYST_FOREST_OBSIDIAN = id("amethyst_forest/obsidian");
	ResourceKey<PlacedFeature> AMETHYST_FOREST_DIRT = id("amethyst_forest/dirt");
	ResourceKey<PlacedFeature> AMETHYST_FOREST_TREES = id("amethyst_forest/trees");
	ResourceKey<PlacedFeature> AMETHYST_FOREST_CRYSTAL_GRASS = id("amethyst_forest/crystal_grass");
	ResourceKey<PlacedFeature> AMETHYST_FOREST_FLOWERS = id("amethyst_forest/flowers");
	ResourceKey<PlacedFeature> AMETHYST_FOREST_ROOTS = id("amethyst_forest/roots");

	ResourceKey<PlacedFeature> ASHFALL_DELTAS_WATER_DELTAS = id("ashfall_deltas/water_deltas");
	ResourceKey<PlacedFeature> ASHFALL_DELTAS_GRASS_DELTAS = id("ashfall_deltas/grass_deltas");
	ResourceKey<PlacedFeature> ASHFALL_DELTAS_BASALT_COLUMNS = id("ashfall_deltas/basalt_columns");
	ResourceKey<PlacedFeature> ASHFALL_DELTAS_SEAGRASS = id("ashfall_deltas/seagrass");
	ResourceKey<PlacedFeature> ASHFALL_DELTAS_VEGETATION = id("ashfall_deltas/vegetation");
	ResourceKey<PlacedFeature> ASHFALL_DELTAS_TREES = id("ashfall_deltas/trees");
	ResourceKey<PlacedFeature> ASHFALL_DELTAS_GRASS = id("ashfall_deltas/grass");
	ResourceKey<PlacedFeature> ASHFALL_DELTAS_MAGMA = id("ashfall_deltas/magma");
	ResourceKey<PlacedFeature> ASHFALL_DELTAS_ASH_PILES = id("ashfall_deltas/ash_piles");

	ResourceKey<PlacedFeature> CRYSTAL_CRAGS_HILLS = id("crystal_crags/hills");
	ResourceKey<PlacedFeature> CRYSTAL_CRAGS_CRYSTAL_ROOTS = id("crystal_crags/crystal_roots");
	ResourceKey<PlacedFeature> CRYSTAL_CRAGS_AMETHYST_CRYSTALS = id("crystal_crags/amethyst_crystals");
	ResourceKey<PlacedFeature> CRYSTAL_CRAGS_AMETHYST_DELTAS = id("crystal_crags/amethyst_deltas");
	ResourceKey<PlacedFeature> CRYSTAL_CRAGS_GRASS_DELTAS = id("crystal_crags/grass_deltas");
	ResourceKey<PlacedFeature> CRYSTAL_CRAGS_BUDDING_AMETHYST_ORE = id("crystal_crags/budding_amethyst_ore");
	ResourceKey<PlacedFeature> CRYSTAL_CRAGS_CHORUS_PLANTS = id("crystal_crags/chorus_plants");
	ResourceKey<PlacedFeature> CRYSTAL_CRAGS_CRYSTAL_GRASS = id("crystal_crags/crystal_grass");
	ResourceKey<PlacedFeature> CRYSTAL_CRAGS_GRASS = id("crystal_crags/grass");

	ResourceKey<PlacedFeature> END_SHRUBLAND_GRASS = id("end_shrubland/grass");
	ResourceKey<PlacedFeature> END_SHRUBLAND_SHRUBS = id("end_shrubland/shrubs");

	ResourceKey<PlacedFeature> END_WILDS_DIRT = id("end_wilds/dirt");
	ResourceKey<PlacedFeature> END_WILDS_ROOTS = id("end_wilds/roots");
	ResourceKey<PlacedFeature> END_WILDS_TREES = id("end_wilds/trees");
	ResourceKey<PlacedFeature> END_WILDS_FOREST = id("end_wilds/forest");
	ResourceKey<PlacedFeature> END_WILDS_CHORUS_PLANTS = id("end_wilds/chorus_plants");
	ResourceKey<PlacedFeature> END_WILDS_GRASS = id("end_wilds/grass");
	ResourceKey<PlacedFeature> END_WILDS_BUSHES = id("end_wilds/bushes");

	ResourceKey<PlacedFeature> ENDER_WASTES_CHORUS_PLANTS = id("ender_wastes/chorus_plants");
	ResourceKey<PlacedFeature> ENDER_WASTES_HILLS = id("ender_wastes/hills");

	ResourceKey<PlacedFeature> ENDLESS_DUNES_SAND_DELTAS = id("endless_dunes/sand_deltas");
	ResourceKey<PlacedFeature> ENDLESS_DUNES_VEGETATION = id("endless_dunes/vegetation");
	ResourceKey<PlacedFeature> ENDLESS_DUNES_CHORUS_PLANTS = id("endless_dunes/chorus_plants");
	ResourceKey<PlacedFeature> ENDLESS_DUNES_GRASS = id("endless_dunes/grass");
	ResourceKey<PlacedFeature> ENDLESS_DUNES_OASIS = id("endless_dunes/oasis");

	ResourceKey<PlacedFeature> FIERY_HILLS_HILLS = id("fiery_hills/hills");
	ResourceKey<PlacedFeature> FIERY_HILLS_BLACKSTONE_HILLS = id("fiery_hills/blackstone_hills");
	ResourceKey<PlacedFeature> FIERY_HILLS_BASALT_HILLS = id("fiery_hills/basalt_hills");
	ResourceKey<PlacedFeature> FIERY_HILLS_LAVA_DELTAS = id("fiery_hills/lava_deltas");
	ResourceKey<PlacedFeature> FIERY_HILLS_SAND_DELTAS = id("fiery_hills/sand_deltas");
	ResourceKey<PlacedFeature> FIERY_HILLS_GOLD_ORE = id("fiery_hills/gold_ore");
	ResourceKey<PlacedFeature> FIERY_HILLS_MAGMA_ORE = id("fiery_hills/magma_ore");
	ResourceKey<PlacedFeature> FIERY_HILLS_SAND = id("fiery_hills/sand");
	ResourceKey<PlacedFeature> FIERY_HILLS_VENTS = id("fiery_hills/vents");
	ResourceKey<PlacedFeature> FIERY_HILLS_FIRE = id("fiery_hills/fire");
	ResourceKey<PlacedFeature> FIERY_HILLS_TREES = id("fiery_hills/trees");
	ResourceKey<PlacedFeature> FIERY_HILLS_VEGETATION = id("fiery_hills/vegetation");

	ResourceKey<PlacedFeature> FLESH_TUNDRA_STALACTITES = id("flesh_tundra/stalactites");
	ResourceKey<PlacedFeature> FLESH_TUNDRA_NETHERRACK_BOTTOM = id("flesh_tundra/netherrack_bottom");
	ResourceKey<PlacedFeature> FLESH_TUNDRA_CRIMSON_DELTAS = id("flesh_tundra/crimson_deltas");
	ResourceKey<PlacedFeature> FLESH_TUNDRA_BONE_CEILING = id("flesh_tundra/bone_ceiling");
	ResourceKey<PlacedFeature> FLESH_TUNDRA_TREES = id("flesh_tundra/trees");
	ResourceKey<PlacedFeature> FLESH_TUNDRA_VEGETATION = id("flesh_tundra/vegetation");
	ResourceKey<PlacedFeature> FLESH_TUNDRA_VINES = id("flesh_tundra/vines");
	ResourceKey<PlacedFeature> FLESH_TUNDRA_ROOTS = id("flesh_tundra/roots");

	ResourceKey<PlacedFeature> FROSTED_VALLEY_HILLS = id("frosted_valley/hills");

	ResourceKey<PlacedFeature> FROZEN_MARSH_PONDS = id("frozen_marsh/ponds");
	ResourceKey<PlacedFeature> FROZEN_MARSH_VEGETATION = id("frozen_marsh/vegetation");

	ResourceKey<PlacedFeature> FROZEN_SHRUBLAND_DIRT = id("frozen_shrubland/dirt");
	ResourceKey<PlacedFeature> FROZEN_SHRUBLAND_CHORUS_PLANTS = id("frozen_shrubland/chorus_plants");
	ResourceKey<PlacedFeature> FROZEN_SHRUBLAND_SHRUBS = id("frozen_shrubland/shrubs");

	ResourceKey<PlacedFeature> FROZEN_SPIKES_LARGE_DRIPSTONE = id("frozen_spikes/large_dripstone");
	ResourceKey<PlacedFeature> FROZEN_SPIKES_BLUE_ICE_ORE = id("frozen_spikes/blue_ice_ore");
	ResourceKey<PlacedFeature> FROZEN_SPIKES_HILLS = id("frozen_spikes/hills");
	ResourceKey<PlacedFeature> FROZEN_SPIKES_POWDER_SNOW_ORE = id("frozen_spikes/powder_snow_ore");
	ResourceKey<PlacedFeature> FROZEN_SPIKES_ICE_SPIKES = id("frozen_spikes/ice_spikes");

	ResourceKey<PlacedFeature> HALLOWED_TUNDRA_LAKE = id("hallowed_tundra/lake");
	ResourceKey<PlacedFeature> HALLOWED_TUNDRA_TREES = id("hallowed_tundra/trees");

	ResourceKey<PlacedFeature> THE_HALLOW_CRYSTAL_ROOTS = id("the_hallow/crystal_roots");
	ResourceKey<PlacedFeature> THE_HALLOW_ROCKS = id("the_hallow/rocks");
	ResourceKey<PlacedFeature> THE_HALLOW_LANTERNS = id("the_hallow/lanterns");
	ResourceKey<PlacedFeature> THE_HALLOW_DIORITE_BOTTOM = id("the_hallow/diorite_bottom");

	ResourceKey<PlacedFeature> PRISMARINE_FOREST_PONDS = id("prismarine_forest/ponds");
	ResourceKey<PlacedFeature> PRISMARINE_FOREST_TREES = id("prismarine_forest/trees");
	ResourceKey<PlacedFeature> PRISMARINE_FOREST_GRASS = id("prismarine_forest/grass");
	ResourceKey<PlacedFeature> PRISMARINE_FOREST_FLOWERS = id("prismarine_forest/flowers");

	ResourceKey<PlacedFeature> PRISMATIC_DUNES_DELTAS = id("prismatic_dunes/deltas");
	ResourceKey<PlacedFeature> PRISMATIC_DUNES_CRYSTAL_GRASS = id("prismatic_dunes/crystal_grass");
	ResourceKey<PlacedFeature> PRISMATIC_DUNES_GLASS_SPIKES = id("prismatic_dunes/glass_spikes");
	ResourceKey<PlacedFeature> PRISMATIC_DUNES_GRASS_PATCHES = id("prismatic_dunes/grass_patches");

	ResourceKey<PlacedFeature> THE_HALLOW_LAKES = id("the_hallow/lakes");
	ResourceKey<PlacedFeature> THE_HALLOW_TREES = id("the_hallow/trees");
	ResourceKey<PlacedFeature> THE_HALLOW_GRASS = id("the_hallow/grass");
	ResourceKey<PlacedFeature> THE_HALLOW_GROUND_FLOWERS = id("the_hallow/ground_flowers");
	ResourceKey<PlacedFeature> THE_HALLOW_FLOWERS = id("the_hallow/flowers");

	ResourceKey<PlacedFeature> THE_NEST_DEEPSLATE = id("the_nest/deepslate");
	ResourceKey<PlacedFeature> THE_NEST_TUFF = id("the_nest/tuff");
	ResourceKey<PlacedFeature> THE_NEST_DRAGON_EGGS = id("the_nest/dragon_eggs");
	ResourceKey<PlacedFeature> THE_NEST_DEAD_CORAL = id("the_nest/dead_coral");
	ResourceKey<PlacedFeature> THE_NEST_TRANSITION = id("the_nest/transition");

	ResourceKey<PlacedFeature> WARPED_MARSH_PONDS = id("warped_marsh/ponds");
	ResourceKey<PlacedFeature> WARPED_MARSH_WATER_VEGETATION = id("warped_marsh/water_vegetation");
	ResourceKey<PlacedFeature> WARPED_MARSH_VEGETATION = id("warped_marsh/vegetation");
	ResourceKey<PlacedFeature> WARPED_MARSH_WATER_TREES = id("warped_marsh/water_trees");
	ResourceKey<PlacedFeature> WARPED_MARSH_TREES = id("warped_marsh/trees");
	ResourceKey<PlacedFeature> WARPED_MARSH_SLIME = id("warped_marsh/slime");
	ResourceKey<PlacedFeature> WARPED_MARSH_HANGING_FROGLIGHTS = id("warped_marsh/hanging_froglights");

	static ResourceKey<PlacedFeature> id(String id) {
		return Stellarity.key(Registries.PLACED_FEATURE, id);
	}
}
