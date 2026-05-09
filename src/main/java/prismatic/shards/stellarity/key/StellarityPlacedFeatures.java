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

	static ResourceKey<PlacedFeature> id(String name) {
		return Stellarity.key(Registries.PLACED_FEATURE, name);
	}
}
