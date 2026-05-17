package prismatic.shards.stellarity.datagen.dynamic;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.key.StellarityConfiguredFeatures;
import prismatic.shards.stellarity.registry.StellarityFeatures;
import prismatic.shards.stellarity.util.ValueUtil;
import prismatic.shards.stellarity.util.tuple.Tuple2;
import prismatic.shards.stellarity.util.tuple.Tuple3;

import java.util.List;

import static net.minecraft.core.Holder.direct;
import static net.minecraft.world.level.block.Blocks.*;
import static prismatic.shards.stellarity.key.StellarityPlacedFeatures.*;
import static prismatic.shards.stellarity.registry.StellarityBlocks.*;
import static prismatic.shards.stellarity.util.ValueUtil.*;
import static prismatic.shards.stellarity.util.WorldgenUtil.*;


public interface PlacedFeatureProvider {
	static ResourceKey<ConfiguredFeature<?, ?>> mcConfig(String id) {
		return Stellarity.mcKey(Registries.CONFIGURED_FEATURE, id);
	}

	static void bootstrapEarly(BootstrapContext<PlacedFeature> context) {
		context.register(NOTHING, new PlacedFeature(Holder.direct(new ConfiguredFeature<>(Feature.NO_OP, NoneFeatureConfiguration.INSTANCE)), List.of()));
	}

	static void bootstrap(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configured = context.lookup(Registries.CONFIGURED_FEATURE);
		HolderGetter<PlacedFeature> placed = context.lookup(Registries.PLACED_FEATURE);

		final var chorusPlant = configured.getOrThrow(mcConfig("chorus_plant"));
		final var nothing = placed.getOrThrow(NOTHING);
		final var stalactites = configured.getOrThrow(StellarityConfiguredFeatures.GLOBAL_STALACTITES);
		final var aboveBelow0 = heightRange(height(aboveBottom(0), belowTop(0)));
		final var scanDownSolidAir32 = envScan(Direction.DOWN, solid(), matchBlocks(AIR), 32);
		final var scanUpSolidAir32 = envScan(Direction.UP, solid(), matchBlocks(AIR), 32);
		final var crystalCragsAmethystCrystal = configured.getOrThrow(StellarityConfiguredFeatures.CRYSTAL_CRAGS_AMETHYST_CRYSTAL);

		context.register(GLOBAL_STALACTITES, new PlacedFeature(stalactites, List.of(
			countPlace(ValueUtil.weightedInts(14, 100, 28, 50, 56, 25, 80, 1)), inSquare(), noiseCount(10, 55, 0),
			aboveBelow0, scanUpSolidAir32, biome()
		)));
		context.register(GLOBAL_FOSSILS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.GLOBAL_FOSSIL), List.of(
			rarity(40), inSquare(), heightRange(height(aboveBottom(8), absolute(140))), biome()
		)));
		context.register(GLOBAL_DUNGEONS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.GLOBAL_DUNGEON), List.of(
			rarity(10), heightRange(height(aboveBottom(8), absolute(140))), inSquare(), biome()
		)));
		context.register(GLOBAL_FREEZE_WATER, new PlacedFeature(direct(new ConfiguredFeature<>(StellarityFeatures.FREEZE_WATER, NoneFeatureConfiguration.INSTANCE)), List.of(biome())));

		context.register(MAIN_ISLAND_RING, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.MAIN_ISLAND_RING), List.of(biome())));
		context.register(MAIN_ISLAND_PORTAL_PLATFORM, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.MAIN_ISLAND_PORTAL_PLATFORM), List.of(biome())));
		context.register(MAIN_ISLAND_HILLS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.END_BARRENS_HILLS), List.of(
			countPlace(ValueUtil.weightedInts(30, 100, 35, 50, 40, 25, 45, 12)),
			inSquare(),
			noiseCount(3, 100, 0),
			aboveBelow0,
			scanDownSolidAir32,
			biome()
		)));
		context.register(MAIN_ISLAND_OBSIDIAN, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.MAIN_ISLAND_OBSIDIAN), List.of(
			biome(), countPlace(1), inSquare(), rarity(2), heightmap(Heightmap.Types.MOTION_BLOCKING)
		)));
		context.register(MAIN_ISLAND_PATCHES, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.MAIN_ISLAND_PATCH), List.of(
			biome(), everyLayer(num(2))
		)));
		context.register(MAIN_ISLAND_CHORUS_PLANTS, new PlacedFeature(chorusPlant, List.of(
			biome(), heightmap(Heightmap.Types.MOTION_BLOCKING), countPlace(ValueUtil.weightedInts(0, 9, 1, 1)), inSquare()
		)));

		context.register(END_BARRENS_HILLS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.END_BARRENS_HILLS), List.of(
			countPlace(num(20, 40)), inSquare(), noiseCount(37, 100, 0), aboveBelow0, envScan(
				Direction.DOWN, solid(), matchBlocks(AIR), 32
			), biome()
		)));
		context.register(END_BARRENS_STALACTITES, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.GLOBAL_STALACTITES), List.of(
			countPlace(ValueUtil.weightedInts(15, 100, 25, 50, 35, 25, 45, 1)), inSquare(), noiseCount(25, 55, 0.1), aboveBelow0,
			scanUpSolidAir32, biome()
		)));

		context.register(END_MIDLANDS_OBSIDIAN_SPIKES, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.END_MIDLANDS_OBSIDIAN_SPIKE), List.of(
			rarity(3), inSquare(), heightRange(height(aboveBottom(0), absolute(170))),
			envScan(Direction.UP, all(matchBlocks(vec(0, 1, 0), END_STONE, ENDER_GRASS_BLOCK, ENDER_DIRT), matchBlocks(vec(0, 2, 0), AIR, CAVE_AIR)), all(), 32), biome()
		)));
		context.register(END_MIDLANDS_ROCKS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.END_MIDLANDS_ROCK), List.of(
			everyLayer(1), rarity(7), biome()
		)));
		context.register(END_MIDLANDS_VEGETATION, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.END_MIDLANDS_VEGETATION), List.of(
			everyLayer(8), blockFilter(matchBlocks(vec(0, -1, 0), ENDER_GRASS_BLOCK)), biome(), countPlace(64),
			randOffset(trapezoid(-7, 7, 0), trapezoid(-3, 3, 0)), blockFilter(all(matchBlocks(AIR), wouldSurvive(SHORT_GRASS)))
		)));
		context.register(END_MIDLANDS_CHORUS_PLANTS, new PlacedFeature(chorusPlant, List.of(everyLayer(2), biome())));

		context.register(END_HIGHLANDS_LARGE_DIRT_PATCHES, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.END_HIGHLANDS_LARGE_DIRT_PATCH), List.of(
			rarity(5), inSquare(), heightmap(Heightmap.Types.WORLD_SURFACE_WG), countPlace(16), biome()
		)));
		context.register(END_HIGHLANDS_SMALL_DIRT_PATCHES, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.END_HIGHLANDS_SMALL_DIRT_PATCH), List.of(
			countPlace(10), inSquare(), aboveBelow0, envScan(Direction.DOWN, solid(), matchBlocks(AIR), 16), biome()
		)));
		context.register(END_HIGHLANDS_CHORUS_BUDS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.END_HIGHLANDS_CHORUS_BUD), List.of(
			rarity(10), inSquare(), heightmap(Heightmap.Types.WORLD_SURFACE_WG), biome()
		)));
		context.register(END_HIGHLANDS_CHORUS_PLANTS, new PlacedFeature(chorusPlant, List.of(
			noiseCount(80, 180, 0.7), inSquare(), aboveBelow0, envScan(
				Direction.DOWN, all(replaceable(), matchBlocks(vec(0, -1, 0), END_STONE)), all(), 32
			), rarity(1), biome()
		)));
		context.register(END_HIGHLANDS_PITCHER_PLANTS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.END_HIGHLANDS_PITCHER_PLANT), List.of(
			everyLayer(1), rarity(3), biome(), countPlace(96), randOffset(trapezoid(-7, 7, 0), trapezoid(-3, 3, 0)),
			blockFilter(all(matchBlocks(AIR), matchBlocks(vec(0, -1, 0), ENDER_GRASS_BLOCK, ROOTED_ENDER_DIRT, COARSE_DIRT, GRASS_BLOCK, DIRT)))
		)));
		context.register(END_HIGHLANDS_GRASS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.END_HIGHLANDS_GRASS), List.of(
			everyLayer(3), biome(), countPlace(96), randOffset(trapezoid(-7, 7, 0), trapezoid(-4, 4, 0)), blockFilter(all(wouldSurvive(SHORT_GRASS), matchBlocks(AIR)))
		)));
		context.register(END_HIGHLANDS_ROOTS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.END_HIGHLANDS_ROOTS), List.of(
			countPlace(87), inSquare(), aboveBelow0, envScan(Direction.UP, all(sturdyFace(Direction.DOWN), matchBlocks(DIRT, GRASS_BLOCK)), matchBlocks(AIR), 32),
			randOffset(num(0), num(-1)), biome(), countPlace(24), randOffset(trapezoid(-5, 5, 0), trapezoid(-5, 5, 0)),
			blockFilter(all(matchBlocks(AIR), matchBlocks(vec(0, 1, 0), ENDER_DIRT, ENDER_GRASS_BLOCK, ROOTED_ENDER_DIRT, COARSE_DIRT)))
		)));
		context.register(END_HIGHLANDS_CHORUS_LEAVES, new PlacedFeature(direct(new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
			new WeightedPlacedFeature(direct(new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.END_HIGHLANDS_CHORUS_LEAF), List.of())), 0.3f)), nothing
		))), List.of(
			noiseCount(40, 2, 1), inSquare(), aboveBelow0, envScan(Direction.DOWN, all(replaceable(), matchBlocks(vec(0, -1, 0), END_STONE)), all(), 32), rarity(1), biome()
		)));
		context.register(END_HIGHLANDS_BUSHES, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.END_HIGHLANDS_BUSH), List.of(
			everyLayer(1), rarity(8), blockFilter(matchBlocks(vec(0, -1, 0), ENDER_GRASS_BLOCK)), biome()
		)));


		context.register(AMETHYST_FOREST_CALCITE_BOTTOM, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.AMETHYST_FOREST_CALCITE_BOTTOM), List.of(
			countPlace(40), inSquare(), noiseCount(10, 20, 0), aboveBelow0,
			scanUpSolidAir32, biome()
		)));
		context.register(AMETHYST_FOREST_AMETHYST_GEODES, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.AMETHYST_FOREST_AMETHYST_GEODE), List.of(
			rarity(1), countPlace(1), inSquare(), heightRange(height(aboveBottom(12), belowTop(12))), biome()
		)));
		context.register(AMETHYST_FOREST_TUFF_ROCKS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.AMETHYST_FOREST_TUFF_ROCK), List.of(
			everyLayer(num(1)), rarity(2), biome()
		)));
		context.register(AMETHYST_FOREST_OBSIDIAN, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.AMETHYST_FOREST_OBSIDIAN), List.of(
			rarity(3), inSquare(), heightmap(Heightmap.Types.WORLD_SURFACE_WG), countPlace(16), biome()
		)));
		context.register(AMETHYST_FOREST_DIRT, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.AMETHYST_FOREST_DIRT), List.of(
			rarity(3), inSquare(), heightmap(Heightmap.Types.WORLD_SURFACE_WG), countPlace(16), biome()
		)));
		context.register(AMETHYST_FOREST_TREES, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.AMETHYST_FOREST_TREE), List.of(
			everyLayer(2), biome(), blockFilter(all(
				matchBlocks(AIR), wouldSurvive(DARK_OAK_SAPLING.defaultBlockState().setValue(BlockStateProperties.STAGE, 0))
			))
		)));
		context.register(AMETHYST_FOREST_CRYSTAL_GRASS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.AMETHYST_FOREST_CRYSTAL_GRASS), List.of(
			everyLayer(7), biome()
		)));
		context.register(AMETHYST_FOREST_FLOWERS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.AMETHYST_FOREST_FLOWER), List.of(
			everyLayer(1), biome(), countPlace(40), randOffset(trapezoid(-5, 5, 0), trapezoid(-2, 2, 0)), blockFilter(matchBlocks(AIR))
		)));
		context.register(AMETHYST_FOREST_ROOTS, new PlacedFeature(Holder.direct(new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(block(HANGING_ROOTS)))), List.of(
			countPlace(87), inSquare(), aboveBelow0, envScan(Direction.UP, all(
				sturdyFace(Direction.DOWN), matchBlocks(ENDER_DIRT, ENDER_GRASS_BLOCK)
			), matchBlocks(AIR), 32), randOffset(num(0), num(-1)), biome(), countPlace(24), randOffset(
				trapezoid(-5, 5, 0), trapezoid(-5, 5, 0)
			), blockFilter(all(matchBlocks(AIR), matchBlocks(vec(0, 1, 0), ENDER_DIRT, ENDER_GRASS_BLOCK)))
		)));

		context.register(ASHFALL_DELTAS_WATER_DELTAS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.ASHFALL_DELTAS_WATER_DELTA), List.of(
			everyLayer(35), biome()
		)));
		context.register(ASHFALL_DELTAS_GRASS_DELTAS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.ASHFALL_DELTAS_GRASS_DELTA), List.of(
			everyLayer(3), biome()
		)));
		context.register(ASHFALL_DELTAS_BASALT_COLUMNS, new PlacedFeature(direct(new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
			weightedPlaced(new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.ASHFALL_DELTAS_BASALT_COLUMNS), List.of()), 0.12f)
		), nothing))), List.of(
			everyLayer(2), biome()
		)));
		context.register(ASHFALL_DELTAS_SEAGRASS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.ASHFALL_DELTAS_SEAGRASS), List.of(
			everyLayer(150), blockFilter(all(matchBlocks(vec(0, -1, 0), BASALT, STONE, BLACKSTONE), matchBlocks(WATER), matchBlocks(vec(0, 1, 0), AIR, WATER))), biome()
		)));
		context.register(ASHFALL_DELTAS_VEGETATION, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.ASHFALL_DELTAS_VEGETATION), List.of(
			everyLayer(30), biome(), countPlace(2), randOffset(trapezoid(-5, 5, 0), trapezoid(-3, 3, 0)), blockFilter(BlockPredicate.allOf(matchBlocks(AIR), matchBlocks(vec(0, -1, 0), ENDER_GRASS_BLOCK, MUD)))
		)));
		context.register(ASHFALL_DELTAS_TREES, new PlacedFeature(Holder.direct(new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
			List.of(weightedPlaced(new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.ASHFALL_DELTAS_TREE), List.of()), 0.8f)), nothing
		))), List.of(everyLayer(2), biome(), rarity(5))));
		context.register(ASHFALL_DELTAS_GRASS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.ASHFALL_DELTAS_GRASS), List.of(
			everyLayer(30), biome(), countPlace(20), randOffset(trapezoid(-5, 5, 0), trapezoid(-3, 3, 0)), blockFilter(all(matchBlocks(AIR), wouldSurvive(SHORT_GRASS)))
		)));
		context.register(ASHFALL_DELTAS_MAGMA, new PlacedFeature(configured.getOrThrow(mcConfig("underwater_magma")), List.of(
			countPlace(200), inSquare(), aboveBelow0, surfaceRelative(Heightmap.Types.OCEAN_FLOOR), biome()
		)));
		context.register(ASHFALL_DELTAS_ASH_PILES, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.ASHFALL_DELTAS_ASH_PILE), List.of(
			countPlace(25), inSquare(), aboveBelow0, envScan(Direction.DOWN, solid(), matchBlocks(AIR), 12), biome()
		)));

		context.register(CRYSTAL_CRAGS_HILLS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.CRYSTAL_CRAGS_HILLS), List.of(
			countPlace(num(2, 5)), inSquare(), noiseCount(65, 80, 0), aboveBelow0, scanDownSolidAir32, biome()
		)));
		context.register(CRYSTAL_CRAGS_CRYSTAL_ROOTS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.CRYSTAL_CRAGS_CRYSTAL_ROOTS), List.of(
			rarity(2), inSquare(), heightRange(height(aboveBottom(0), absolute(170))), envScan(Direction.UP, all(matchBlocks(vec(0, 1, 0), END_STONE),
				matchBlocks(AIR, CAVE_AIR)), all(), 32), biome()
		)));
		context.register(CRYSTAL_CRAGS_AMETHYST_CRYSTALS, new PlacedFeature(crystalCragsAmethystCrystal, List.of(
			countPlace(200), inSquare(), heightmap(Heightmap.Types.OCEAN_FLOOR), biome(), countPlace(16), randOffset(trapezoid(-4, 4, 0), trapezoid(-4, 4, 0)),
			blockFilter(all(matchBlocks(AIR), any(matchBlocks(vec(0, 1, 0), AMETHYST_BLOCK), matchBlocks(vec(1, 0, 0), AMETHYST_BLOCK),
				matchBlocks(vec(-1, 0, 0), AMETHYST_BLOCK), matchBlocks(vec(0, 0, 1), AMETHYST_BLOCK), matchBlocks(vec(0, 0, -1), AMETHYST_BLOCK)
			)))
		)));
		context.register(CRYSTAL_CRAGS_GRASS_DELTAS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.CRYSTAL_CRAGS_GRASS_DELTA), List.of(everyLayer(6), biome())));
		context.register(CRYSTAL_CRAGS_AMETHYST_DELTAS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.CRYSTAL_CRAGS_AMETHYST_DELTA), List.of(everyLayer(15), biome())));
		context.register(CRYSTAL_CRAGS_BUDDING_AMETHYST_ORE, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.CRYSTAL_CRAGS_BUDDING_AMETHYST_ORE), List.of(
			countPlace(40), inSquare(), aboveBelow0, biome()
		)));
		context.register(CRYSTAL_CRAGS_CHORUS_PLANTS, new PlacedFeature(chorusPlant, List.of(everyLayer(num(0, 3)), biome(), rarity(2))));
		context.register(CRYSTAL_CRAGS_CRYSTAL_GRASS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.CRYSTAL_CRAGS_CRYSTAL_GRASS), List.of(
			everyLayer(4), biome(), countPlace(16), randOffset(trapezoid(-7, 7, 0), trapezoid(-3, 3, 0)), blockFilter(matchBlocks(AIR))
		)));
		context.register(CRYSTAL_CRAGS_GRASS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.CRYSTAL_CRAGS_GRASS), List.of(
			everyLayer(6), biome(), countPlace(64), randOffset(trapezoid(-7, 7, 0), trapezoid(-3, 3, 0)), blockFilter(all(matchBlocks(AIR), wouldSurvive(SHORT_GRASS)))
		)));

		context.register(END_SHRUBLAND_GRASS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.END_SHRUBLAND_GRASS), List.of(
			everyLayer(10), blockFilter(matchBlocks(vec(0, -1, 0), ENDER_GRASS_BLOCK)), biome(), countPlace(50),
			randOffset(trapezoid(-7, 7, 0), trapezoid(-3, 3, 0)), blockFilter(all(matchBlocks(AIR), wouldSurvive(SHORT_GRASS)))
		)));
		context.register(END_SHRUBLAND_SHRUBS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.END_SHRUBLAND_SHRUB), List.of(
			everyLayer(num(2, 4)), biome(), blockFilter(matchBlocks(vec(0, -1, 0), ENDER_GRASS_BLOCK))
		)));
		context.register(END_WILDS_DIRT, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.END_WILDS_DIRT), List.of(
			countPlace(40), inSquare(), noiseCount(40, 100, -0.22), aboveBelow0, envScan(Direction.DOWN, solid(), matchBlocks(AIR), 16), biome()
		)));
		context.register(END_WILDS_ROOTS, new PlacedFeature(direct(new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(block(HANGING_ROOTS)))), List.of(
			countPlace(64), inSquare(), aboveBelow0, envScan(Direction.UP, all(
				sturdyFace(Direction.DOWN), matchBlocks(ENDER_DIRT, ENDER_GRASS_BLOCK, ROOTED_ENDER_DIRT)
			), matchBlocks(AIR), 24), randOffset(num(0), num(-1)), biome(), countPlace(24), randOffset(trapezoid(-5, 5, 0), trapezoid(-4, 4, 0)),
			blockFilter(all(matchBlocks(AIR), matchBlocks(vec(0, 1, 0), ENDER_DIRT, ENDER_GRASS_BLOCK, ROOTED_ENDER_DIRT)))
		)));
		context.register(END_WILDS_TREES, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.END_WILDS_TREE), List.of(
			everyLayer(1), rarity(130), blockFilter(matchBlocks(vec(0, -1, 0), ENDER_GRASS_BLOCK)), biome(), countPlace(64), randOffset(trapezoid(0, 0, 0), trapezoid(0, 0, 0))
		)));
		context.register(END_WILDS_FOREST, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.END_WILDS_TREE), List.of(
			noiseCount(13, 1100, -0.3), inSquare(), aboveBelow0, envScan(Direction.DOWN, all(replaceable(), matchBlocks(vec(0, -1, 0), ENDER_GRASS_BLOCK)), all(), 32), rarity(1), biome(), countPlace(64), randOffset(trapezoid(0, 0, 0), trapezoid(0, 0, 0))
		)));
		context.register(END_WILDS_CHORUS_PLANTS, new PlacedFeature(chorusPlant, List.of(everyLayer(1), biome())));
		context.register(END_WILDS_GRASS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.END_WILDS_GRASS), List.of(
			everyLayer(10), blockFilter(matchBlocks(vec(0, -1, 0), ENDER_GRASS_BLOCK)), biome(), countPlace(50), randOffset(trapezoid(-7, 7, 0), trapezoid(-3, 3, 0)), blockFilter(all(matchBlocks(AIR), wouldSurvive(SHORT_GRASS)))
		)));
		context.register(END_WILDS_BUSHES, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.END_WILDS_BUSH), List.of(
			countPlace(80), inSquare(), noiseCount(30, 100, 0.1), noiseCount(300, 30, -0.1), aboveBelow0, envScan(Direction.DOWN, solid(), matchBlocks(AIR), 6), biome()
		)));

		context.register(ENDER_WASTES_HILLS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.ENDER_WASTES_HILLS), List.of(
			countPlace(num(2, 5)), inSquare(), noiseCount(37, 100, 0), aboveBelow0, scanDownSolidAir32, biome()
		)));
		context.register(ENDER_WASTES_CHORUS_PLANTS, new PlacedFeature(chorusPlant, List.of(everyLayer(1), biome(), rarity(2))));

		context.register(ENDLESS_DUNES_SAND_DELTAS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.ENDLESS_DUNES_SAND_DELTA), List.of(
			countPlace(40), blockFilter(matchBlocks(vec(0, -1, 0), END_STONE)), biome()
		)));
		context.register(ENDLESS_DUNES_VEGETATION, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.ENDLESS_DUNES_VEGETATION), List.of(everyLayer(num(1, 4)), rarity(5), biome())));
		context.register(ENDLESS_DUNES_CHORUS_PLANTS, new PlacedFeature(chorusPlant, List.of(everyLayer(num(2, 3)), rarity(2), biome())));
		context.register(ENDLESS_DUNES_GRASS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.ENDLESS_DUNES_GRASS), List.of(
			noiseCount(7, 90, -0.7), countPlace(4), inSquare(), heightmap(Heightmap.Types.WORLD_SURFACE), biome()
		)));
		context.register(ENDLESS_DUNES_OASIS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.ENDLESS_DUNES_OASIS), List.of(
			noiseCount(7, 130, -0.8), countPlace(64), heightmap(Heightmap.Types.WORLD_SURFACE), biome()
		)));

		for (var hill : List.of(
			new Tuple2<>(FIERY_HILLS_HILLS, StellarityConfiguredFeatures.FIERY_HILLS_HILLS),
			new Tuple2<>(FIERY_HILLS_BASALT_HILLS, StellarityConfiguredFeatures.FIERY_HILLS_BASALT_HILLS),
			new Tuple2<>(FIERY_HILLS_BLACKSTONE_HILLS, StellarityConfiguredFeatures.FIERY_HILLS_BLACKSTONE_HILLS)
		))
			context.register(hill._1(), new PlacedFeature(configured.getOrThrow(hill._2()), List.of(
				countPlace(30), inSquare(), noiseCount(25, 130, 0), aboveBelow0,
				scanDownSolidAir32, biome()
			)));
		for (var delta : List.of(
			new Tuple3<>(FIERY_HILLS_LAVA_DELTAS, StellarityConfiguredFeatures.FIERY_HILLS_LAVA_DELTA, new Block[]{END_STONE, BLACKSTONE, SMOOTH_BASALT, BASALT}),
			new Tuple3<>(FIERY_HILLS_SAND_DELTAS, StellarityConfiguredFeatures.FIERY_HILLS_SAND_DELTA, new Block[]{END_STONE})
		))
			context.register(delta._1(), new PlacedFeature(configured.getOrThrow(delta._2()), List.of(
				everyLayer(weightedInts(new IntProvider[]{num(20), num(10, 15), num(5)}, new int[]{1, 2, 3})),
				blockFilter(matchBlocks(vec(0, -1, 0), delta._3())), biome()
			)));
		for (var ore : List.of(
			new Tuple3<>(FIERY_HILLS_GOLD_ORE, StellarityConfiguredFeatures.FIERY_HILLS_GOLD_ORE, 50),
			new Tuple3<>(FIERY_HILLS_MAGMA_ORE, StellarityConfiguredFeatures.FIERY_HILLS_MAGMA_ORE, 20)
		))
			context.register(ore._1(), new PlacedFeature(configured.getOrThrow(ore._2()), List.of(
				countPlace(ore._3()), inSquare(), heightRange(height(aboveBottom(10), belowTop(10))), biome()
			)));
		context.register(FIERY_HILLS_SAND, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.FIERY_HILLS_SAND), List.of(
			countPlace(30), inSquare(), noiseCount(25, 130, 0), aboveBelow0, scanDownSolidAir32, biome()
		)));
		context.register(FIERY_HILLS_VENTS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.FIERY_HILLS_VENT), List.of(
			countPlace(50), inSquare(), heightRange(height(aboveBottom(25), belowTop(50))),
			envScan(Direction.DOWN, all(matchBlocks(vec(0, 1, 0), LAVA), matchBlocks(vec(0, 2, 0), AIR)), all(), 32),
			biome()
		)));
		context.register(FIERY_HILLS_FIRE, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.FIERY_HILLS_FIRE), List.of(
			countPlace(100), inSquare(), heightRange(height(aboveBottom(15), belowTop(9))), biome(), envScan(Direction.UP, all(solid(), matchBlocks(vec(0, 1, 0), AIR)), all(), 32)
		)));
		context.register(FIERY_HILLS_TREES, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.FIERY_HILLS_TREE), List.of(
			everyLayer(3), blockFilter(matchBlocks(vec(0, -1, 0), LAVA, MAGMA_BLOCK)), biome()
		)));
		context.register(FIERY_HILLS_VEGETATION, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.FIERY_HILLS_VEGETATION), List.of(
			everyLayer(4), biome(), countPlace(128), randOffset(trapezoid(-7, 7, 0), trapezoid(-3, 3, 0)),
			blockFilter(all(matchBlocks(AIR), matchBlocks(vec(0, -1, 0), NETHER_WART_BLOCK)))
		)));

		context.register(FLESH_TUNDRA_STALACTITES, new PlacedFeature(stalactites, List.of(
			countPlace(ValueUtil.weightedInts(7, 100, 14, 50, 28, 25, 40, 1)), inSquare(), noiseCount(5, 55, 0), aboveBelow0, scanUpSolidAir32, biome()
		)));
		context.register(FLESH_TUNDRA_NETHERRACK_BOTTOM, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.FLESH_TUNDRA_NETHERRACK_BOTTOM), List.of(
			countPlace(90), noiseCount(50, 51, 0.08), inSquare(), aboveBelow0, scanUpSolidAir32, biome()
		)));
		context.register(FLESH_TUNDRA_CRIMSON_DELTAS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.FLESH_TUNDRA_CRIMSON_DELTAS), List.of(
			everyLayer(40), biome()
		)));
		context.register(FLESH_TUNDRA_BONE_CEILING, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.FLESH_TUNDRA_BONE_CEILING), List.of(
			countPlace(40), inSquare(), aboveBelow0,
			envScan(Direction.UP, sturdyFace(Direction.DOWN), all(matchBlocks(AIR), matchBlocks(vec(0, 1, 0), NETHERRACK)), 12),
			randOffset(num(0), num(-1)), biome()
		)));
		context.register(FLESH_TUNDRA_TREES, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.FLESH_TUNDRA_TREE_PATCH), List.of(
			countPlace(120), inSquare(), aboveBelow0,
			envScan(Direction.DOWN, solid(), all(matchBlocks(AIR), matchBlocks(vec(0, -1, 0), NETHER_WART_BLOCK, END_STONE, CRIMSON_NYLIUM, END_STONE_BRICKS)), 24),
			biome()
		)));

		context.register(FLESH_TUNDRA_VEGETATION, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.FLESH_TUNDRA_VEGETATION), List.of(
			countPlace(200), noiseCount(150, 60, 0.1139), inSquare(), aboveBelow0, envScan(Direction.DOWN, solid(), all(matchBlocks(AIR)), 4), biome(),
			countPlace(4), randOffset(trapezoid(-1, 1, 0), trapezoid(-1, 1, 0))
		)));
		context.register(FLESH_TUNDRA_VINES, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.FLESH_TUNDRA_VINES), List.of(
			countPlace(70), inSquare(), aboveBelow0, envScan(Direction.UP, sturdyFace(Direction.DOWN), matchBlocks(AIR), 16), randOffset(num(0), num(-1)), biome()
		)));
		context.register(FLESH_TUNDRA_ROOTS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.FLESH_TUNDRA_ROOTS), List.of(
			everyLayer(16), biome(), countPlace(30), randOffset(trapezoid(-6, 6, 0), trapezoid(-2, 2, 0)), blockFilter(matchBlocks(AIR))
		)));

		context.register(FROSTED_VALLEY_HILLS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.FROSTED_VALLEY_HILLS), List.of(
			countPlace(num(5, 7)), inSquare(), noiseCount(25, 130, -0.4), aboveBelow0, scanDownSolidAir32, biome()
		)));

		context.register(FROZEN_MARSH_PONDS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.FROZEN_MARSH_POND), List.of(
			everyLayer(20), biome()
		)));
		context.register(FROZEN_MARSH_VEGETATION, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.FROZEN_MARSH_VEGETATION), List.of(
			everyLayer(3), biome(), countPlace(40), randOffset(trapezoid(-6, 6, 0), trapezoid(-3, 3, 0)),
			blockFilter(all(matchBlocks(vec(0, -1, 0), SNOW_BLOCK), matchBlocks(AIR)))
		)));

		context.register(FROZEN_SHRUBLAND_DIRT, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.FROZEN_SHRUBLANDS_DIRT), List.of(
			countPlace(40), inSquare(), noiseCount(40, 100, -0.22), aboveBelow0, envScan(Direction.DOWN, solid(), matchBlocks(AIR), 16), biome()
		)));
		context.register(FROZEN_SHRUBLAND_CHORUS_PLANTS, new PlacedFeature(chorusPlant, List.of(everyLayer(1), biome())));
		context.register(FROZEN_SHRUBLAND_SHRUBS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.FROZEN_SHRUBLANDS_SHRUB), List.of(
			everyLayer(num(1, 3)), biome(), blockFilter(matchBlocks(vec(0, -1, 0), SNOW_BLOCK))
		)));

		context.register(FROZEN_SPIKES_LARGE_DRIPSTONE, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.FROZEN_SPIKES_LARGE_DRIPSTONE), List.of(
			countPlace(num(1, 5)), randOffset(num(6, 12), num(0)), aboveBelow0, biome()
		)));
		context.register(FROZEN_SPIKES_BLUE_ICE_ORE, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.FROZEN_SPIKES_BLUE_ICE_ORE), List.of(
			countPlace(45), inSquare(), aboveBelow0, biome()
		)));
		context.register(FROZEN_SPIKES_HILLS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.FROZEN_SPIKES_HILLS), List.of(
			countPlace(num(5, 7)), inSquare(), noiseCount(30, 135, 0), aboveBelow0, scanDownSolidAir32, biome()
		)));
		context.register(FROZEN_SPIKES_POWDER_SNOW_ORE, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.FROZEN_SPIKES_POWDER_SNOW_ORE), List.of(
			countPlace(60), inSquare(), aboveBelow0, biome()
		)));
		context.register(FROZEN_SPIKES_ICE_SPIKES, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.FROZEN_SPIKES_ICE_SPIKE), List.of(
			countPlace(10), rarity(2), inSquare(), heightRange(height(aboveBottom(0), absolute(180))), envScan(Direction.UP, matchBlocks(AIR), solid(), 32), biome()
		)));

		context.register(HALLOWED_TUNDRA_LAKE, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.HALLOWED_TUNDRA_LAKE), List.of(
			rarity(10), biome(), heightmap(Heightmap.Types.WORLD_SURFACE)
		)));
		context.register(HALLOWED_TUNDRA_TREES, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.HALLOWED_TUNDRA_TREE), List.of(
			everyLayer(weightedInts(2, 5, 3, 4, 5, 1)), blockFilter(matchBlocks(vec(0, -1, 0), SNOW_BLOCK)), biome()
		)));

		context.register(THE_HALLOW_LANTERNS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.THE_HALLOW_LANTERN), List.of(
			countPlace(num(0, 4)), inSquare(), heightRange(height(aboveBottom(40), belowTop(170))),
			envScan(Direction.UP, all(sturdyFace(Direction.DOWN), matchBlocks(DIORITE, CALCITE, ENDER_DIRT, ENDER_GRASS_BLOCK)), matchBlocks(AIR), 24),
			randOffset(num(0), num(-1)), biome()
		)));
		context.register(THE_HALLOW_CRYSTAL_ROOTS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.THE_HALLOW_CRYSTAL_ROOTS), List.of(
			rarity(10), inSquare(), heightRange(height(aboveBottom(0), absolute(170))),
			envScan(Direction.UP, all(matchBlocks(vec(0, 1, 0), END_STONE, DIORITE), matchBlocks(AIR, CAVE_AIR)), all(), 32), biome()
		)));
		context.register(THE_HALLOW_ROCKS, new PlacedFeature(configured.getOrThrow(StellarityConfiguredFeatures.THE_HALLOW_ROCK), List.of(
			countPlace(1), inSquare(), biome(), rarity(3), aboveBelow0
		)));
	}
}
