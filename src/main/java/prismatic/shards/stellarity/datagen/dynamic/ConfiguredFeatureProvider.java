package prismatic.shards.stellarity.datagen.dynamic;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.WeepingVinesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.foliageplacers.*;
import net.minecraft.world.level.levelgen.feature.rootplacers.AboveRootPlacement;
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacement;
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacer;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.*;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.interface_injection.ExtLargeDripstoneConfiguration;
import prismatic.shards.stellarity.key.StellarityPlacedFeatures;
import prismatic.shards.stellarity.registry.StellarityFeatures;
import prismatic.shards.stellarity.registry.feature.configuration.DungeonFeatureConfiguration;
import prismatic.shards.stellarity.util.Constants;
import prismatic.shards.stellarity.util.ValueUtil;
import prismatic.shards.stellarity.util.tuple.Tuple3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static net.minecraft.core.Holder.direct;
import static net.minecraft.world.level.block.Blocks.*;
import static prismatic.shards.stellarity.key.StellarityConfiguredFeatures.*;
import static prismatic.shards.stellarity.registry.StellarityBlocks.*;
import static prismatic.shards.stellarity.tags.StellarityBlockTags.*;
import static prismatic.shards.stellarity.util.ValueUtil.*;
import static prismatic.shards.stellarity.util.WorldgenUtil.*;

public interface ConfiguredFeatureProvider {
	static void bootstrapEarly(BootstrapContext<ConfiguredFeature<?, ?>> context) {

	}

	/**
	 * <h1>ABSOLUTELY DO NOT I REPEAT DO NOT USE RANDOM SELECTORS UNLESS YOU ARE PUTTING IT AS MULTIPLE CHOICES. IF A FEATURE HAS A CERTAIN CHANCE TO SPAWN, INLINE
	 * THE RANDOM SELECTOR IN <code>StellarityPlacedFeatures.java</code></h1>
	 * Why? It's shit for debugging via <code>/place feature</code> when your configured feature has a 1% chance to spawn.
	 *
	 * @param context context for registering stuff
	 */
	static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		var placed = context.lookup(Registries.PLACED_FEATURE);
		var configured = context.lookup(Registries.CONFIGURED_FEATURE);
		var blocksGetter = context.lookup(Registries.BLOCK);
		var processors = context.lookup(Registries.PROCESSOR_LIST);

		final var nothing = placed.getOrThrow(StellarityPlacedFeatures.NOTHING);
		final var upAmethystCluster = property(AMETHYST_CLUSTER, BlockStateProperties.FACING, Direction.UP);
		final var amethystCrystalsUp = Stream.of(AMETHYST_CLUSTER, LARGE_AMETHYST_BUD, MEDIUM_AMETHYST_BUD, SMALL_AMETHYST_BUD).map(b -> property(b, BlockStateProperties.FACING, Direction.UP)).toArray(BlockState[]::new);
		final var persistAzaleaLeaves = property(AZALEA_LEAVES, BlockStateProperties.PERSISTENT, true);
		final var muddyBlocks = HolderSet.direct(
			blocksGetter.getOrThrow(Stellarity.mcKey(Registries.BLOCK, "mud")),
			blocksGetter.getOrThrow(Stellarity.mcKey(Registries.BLOCK, "muddy_mangrove_roots"))
		);

		context.register(GLOBAL_STALACTITES, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_STALACTITE, block(END_STONE),
			direct(new PlacedFeature(
				direct(new ConfiguredFeature<>(Feature.BLOCK_COLUMN, blockColumns(
					Direction.DOWN, matchBlocks(vec(0, 1, 0), AIR), true,
					columnLayer(num(1, 2), block(END_STONE))
				))), List.of()
			)),
			CaveSurface.CEILING, num(1), 0, 10, 1, num(3, 6), 0.5f
		)));
		context.register(GLOBAL_FOSSIL, new ConfiguredFeature<>(Feature.FOSSIL, new FossilFeatureConfiguration(
			List.of(Stellarity.id("fossil/phantom")), List.of(Stellarity.id("fossil/phantom_overlay")),
			processors.getOrThrow(Stellarity.mcKey(Registries.PROCESSOR_LIST, "fossil_rot")), processors.getOrThrow(Stellarity.mcKey(Registries.PROCESSOR_LIST, "fossil_coal")),
			2
		)));
		context.register(GLOBAL_DUNGEON, new ConfiguredFeature<>(StellarityFeatures.DUNGEON, new DungeonFeatureConfiguration()));

		context.register(MAIN_ISLAND_RING, new ConfiguredFeature<>(Feature.END_SPIKE, new EndSpikeConfiguration(
			false, Constants.OBSIDIAN_SPIKES, null
		)));
		var platform = new EndSpikeFeature.EndSpike(
			0, 0, 16, 60, false
		);
		platform.stellarity$setHasCrystal(false);
		context.register(MAIN_ISLAND_PORTAL_PLATFORM, new ConfiguredFeature<>(Feature.END_SPIKE, new EndSpikeConfiguration(
			true, List.of(platform), null
		)));
		context.register(MAIN_ISLAND_OBSIDIAN, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_END_STONE, weightedBlocks(new Block[]{END_STONE, CRYING_OBSIDIAN, OBSIDIAN}, new int[]{5, 1, 3}),
			nothing,
			CaveSurface.FLOOR, num(1), 0, 1, 0.1f, num(0, 5), 0.1f
		)));
		context.register(MAIN_ISLAND_PATCH, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_END_STONE, weightedBlocks(new Block[]{END_STONE, COBBLESTONE, ANDESITE, TUFF}, new int[]{77, 3, 1, 2}),
			direct(new PlacedFeature(
				direct(new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
					List.of(weightedPlaced(new PlacedFeature(
						direct(new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
							block(BONE_BLOCK),
							new ForkingTrunkPlacer(6, 1, 8),
							block(AIR),
							new BlobFoliagePlacer(num(0), num(0), 0),
							Optional.empty(),
							twoLayersSize(),
							block(BONE_BLOCK)
						).build())),
						List.of()
					), 0.0033f)),
					direct(new PlacedFeature(direct(new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(blocks(
						Arrays.stream(new Block[]{DEAD_FIRE_CORAL, DEAD_HORN_CORAL, DEAD_BUBBLE_CORAL, DEAD_BRAIN_CORAL, DEAD_TUBE_CORAL}).map(c -> property(c, BlockStateProperties.WATERLOGGED, false)).toArray(BlockState[]::new)
					)))), List.of()))
				))), List.of()
			)),
			CaveSurface.FLOOR, num(3), 0.334f, 10, 0.03f, num(5, 7), 0.556f
		)));

		context.register(END_BARRENS_HILLS, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_STALACTITE, block(END_STONE),
			direct(new PlacedFeature(direct(new ConfiguredFeature<>(Feature.BLOCK_COLUMN, blockColumns(
				Direction.UP, matchBlocks(AIR), true,
				columnLayer(num(0), block(END_STONE)), columnLayer(num(1), block(END_STONE))
			))), List.of()
			)),
			CaveSurface.FLOOR, num(1), 0, 10, 1, num(3, 6), 0.5f
		)));

		context.register(END_MIDLANDS_OBSIDIAN_SPIKE, new ConfiguredFeature<>(Feature.ROOT_SYSTEM, new RootSystemConfiguration(direct(
			new PlacedFeature(direct(new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(weightedBlocks(new Block[]{OBSIDIAN, CRYING_OBSIDIAN}, new int[]{14, 1})))), List.of(
				countPlace(num(128, 256)), countPlace(num(6, 8)),
				randOffset(num(-1, 1), num(0)), randOffset(num(-1, 1), num(0)), randOffset(num(-1, 1), num(0)), randOffset(num(-1, 1), num(0)), randOffset(num(-1, 1), num(0)),
				randOffset(normal(0, 0.65f, -1, 1), num(0)), randOffset(normal(0, 0.65f, -1, 1), num(0)),
				envScan(Direction.DOWN, not(replaceable()), all(), 32), envScan(Direction.UP, replaceable(), all(), 32),
				blockFilter(not(any(
					matchBlocks(vec(-1, -4, 0), AIR, WATER), matchBlocks(vec(1, -4, 0), AIR, WATER), matchBlocks(vec(0, -4, 1), AIR, WATER), matchBlocks(vec(0, -4, -1), AIR, WATER)
				)))))
		), 1, 3, WORLDGEN_STALACTITE, block(OBSIDIAN), 20, 100,
			3, 2, block(CRYING_OBSIDIAN), 15, 1, all()
		)));
		context.register(END_MIDLANDS_ROCK, new ConfiguredFeature<>(Feature.BLOCK_BLOB, new BlockBlobConfiguration(from(SMOOTH_BASALT), all())));
		context.register(END_MIDLANDS_VEGETATION, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(weightedBlocks(
			new Block[]{TALL_GRASS, SHORT_GRASS},
			new int[]{1, 14}))));

		context.register(END_HIGHLANDS_LARGE_DIRT_PATCH, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_GRASS_BLOCK, weightedBlocks(new Block[]{ENDER_GRASS_BLOCK, ROOTED_ENDER_DIRT}, new int[]{39, 5}), nothing, CaveSurface.FLOOR, num(1),
			0, 1, 0.1f, num(0, 5), 0.1f
		)));
		context.register(END_HIGHLANDS_SMALL_DIRT_PATCH, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_GRASS_BLOCK, weightedBlocks(new Block[]{ENDER_GRASS_BLOCK, ROOTED_ENDER_DIRT}, new int[]{11, 2}), nothing, CaveSurface.FLOOR, num(1),
			0.25f, 5, 0f, num(3, 5), 0.15f
		)));
		context.register(END_HIGHLANDS_CHORUS_BUD, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration(
			block(CHERRY_WOOD), new StraightTrunkPlacer(2, 0, 0),
			weightedBlocks(new Block[]{ROOTED_ENDER_DIRT, COARSE_DIRT}, new int[]{7, 4}),
			new BushFoliagePlacer(num(0), num(0), 6),
			Optional.empty(), twoLayersSize(),
			List.of(new LeaveVineDecorator(0.15f), new AttachedToLeavesDecorator(
				0.35f, 0, 0,
				block(property(property(ACACIA_LEAVES, BlockStateProperties.PERSISTENT, true), BlockStateProperties.DISTANCE, 1)), 1, List.of(Direction.UP, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)
			)), false, block(CHERRY_WOOD)
		)));
		context.register(END_HIGHLANDS_PITCHER_PLANT, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(block(PITCHER_PLANT))));
		context.register(END_HIGHLANDS_GRASS, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(weightedBlocks(
			new Block[]{SHORT_GRASS, TALL_GRASS, FERN, LARGE_FERN},
			new int[]{28, 18, 8, 4}
		))));
		context.register(END_HIGHLANDS_ROOTS, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(block(HANGING_ROOTS))));
		context.register(END_HIGHLANDS_CHORUS_LEAF, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration(
			block(PEARLESCENT_FROGLIGHT), new ForkingTrunkPlacer(10, 0, 0),
			block(AIR), new BlobFoliagePlacer(num(1), num(1), 0),
			Optional.of(new MangroveRootPlacer(num(0), block(END_STONE), Optional.of(new AboveRootPlacement(block(END_STONE), 0.33f)), new MangroveRootPlacement(
				blocksGetter.getOrThrow(WORLDGEN_GRASS_BLOCK), HolderSet.direct(blocksGetter.getOrThrow(Stellarity.key(Registries.BLOCK, "ender_dirt"))), block(STONE), 1, 3, 0.1f
			))), twoLayersSize(), List.of(), false, block(END_STONE)
		)));
		context.register(END_HIGHLANDS_BUSH, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration(
			block(CHERRY_WOOD), new StraightTrunkPlacer(1, 0, 0),
			block(OAK_LEAVES), new BushFoliagePlacer(num(2), num(1), 2),
			Optional.empty(), twoLayersSize(0, 0, 0), List.of(), false, block(ENDER_DIRT)
		)));

		context.register(AMETHYST_FOREST_CALCITE_BOTTOM, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_END_STONE, weightedBlocks(new Block[]{CALCITE, DIORITE}, new int[]{2, 1}),
			direct(new PlacedFeature(
				direct(new ConfiguredFeature<>(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
					List.of(new BlockColumnConfiguration.Layer(num(1), weightedBlocks(new Block[]{CALCITE, DIORITE}, new int[]{2, 1}))),
					Direction.DOWN, matchBlocks(AIR), true
				))),
				List.of()
			)),
			CaveSurface.CEILING, num(3, 8), 0.5f, 20, 1, num(3, 8), 0.5f
		)));
		context.register(AMETHYST_FOREST_AMETHYST_GEODE, new ConfiguredFeature<>(Feature.GEODE, new GeodeConfiguration(
			new GeodeBlockSettings(block(AIR), block(AMETHYST_BLOCK), block(BUDDING_AMETHYST),
				blocks(CALCITE, POLISHED_DIORITE, DIORITE),
				blocks(CALCITE, POLISHED_DIORITE, DIORITE),
				List.of(amethystCrystalsUp), WORLDGEN_AMETHYST_GEODE_INVALID, WORLDGEN_AMETHYST_GEODE_INVALID
			),
			new GeodeLayerSettings(1.7, 2.2, 3.2, 3.2),
			new GeodeCrackSettings(0.65, 1.5, 2),
			0.2, 0.083, false, num(4, 6), num(3, 4), num(1, 2), -8, 8, 0.05, 1
		)));
		context.register(AMETHYST_FOREST_TUFF_ROCK, new ConfiguredFeature<>(Feature.BLOCK_BLOB, new BlockBlobConfiguration(from(TUFF), all())));
		context.register(AMETHYST_FOREST_OBSIDIAN, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_GRASS_BLOCK, weightedBlocks(new Block[]{ENDER_GRASS_BLOCK, CRYING_OBSIDIAN, OBSIDIAN}, new int[]{20, 1, 5}), nothing,
			CaveSurface.FLOOR, num(1), 0, 1, 0.1f, num(0, 5), 0.1f
		)));
		context.register(AMETHYST_FOREST_DIRT, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_GRASS_BLOCK, weightedBlocks(new Block[]{ENDER_GRASS_BLOCK, ROOTED_ENDER_DIRT}, new int[]{21, 1}), nothing,
			CaveSurface.FLOOR, num(1), 0, 1, 0.1f, num(0, 5), 0.1f
		)));
		context.register(AMETHYST_FOREST_TREE, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration(
			block(property(CHERRY_LOG, BlockStateProperties.AXIS, Direction.Axis.Y)), new MegaJungleTrunkPlacer(10, 6, 12),
			weightedBlocks(new BlockState[]{property(DARK_OAK_LEAVES, BlockStateProperties.DISTANCE, 1).setValue(BlockStateProperties.PERSISTENT, true), from(GLOWSTONE)}, new int[]{64, 1}),
			new RandomSpreadFoliagePlacer(num(3, 4), num(0, 6), num(10, 13), 256),
			Optional.empty(), threeLayersSize(), List.of(), false, block(Blocks.DIRT)
		)));
		context.register(AMETHYST_FOREST_CRYSTAL_GRASS, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(weightedBlocks(amethystCrystalsUp, new int[]{1, 3, 3, 3}))));
		context.register(AMETHYST_FOREST_FLOWER, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(noiseBlocks(
			12345, new NormalNoise.NoiseParameters(1, 1, 1), 1f,
			from(ALLIUM), upAmethystCluster, from(PINK_TULIP), from(PEONY), from(PINK_TULIP), from(ALLIUM), from(LILAC), from(ALLIUM), upAmethystCluster
		))));

		context.register(ASHFALL_DELTAS_WATER_DELTA, new ConfiguredFeature<>(Feature.DELTA_FEATURE, new DeltaFeatureConfiguration(from(WATER), from(MUD), num(8, 10), num(1, 4))));
		context.register(ASHFALL_DELTAS_GRASS_DELTA, new ConfiguredFeature<>(Feature.DELTA_FEATURE, new DeltaFeatureConfiguration(from(ENDER_GRASS_BLOCK), from(MUD), num(8, 10), num(0))));
		context.register(ASHFALL_DELTAS_BASALT_COLUMNS, new ConfiguredFeature<>(Feature.BASALT_COLUMNS, new ColumnFeatureConfiguration(num(2, 3), num(3, 6))));
		context.register(ASHFALL_DELTAS_SEAGRASS, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(weightedBlocks(
			new BlockState[]{SEA_PICKLE.defaultBlockState().setValue(BlockStateProperties.PICKLES, 2), from(SEA_PICKLE), from(SEAGRASS)},
			new int[]{2, 3, 100}
		))));
		{
			final var TUBE = property(DEAD_BUBBLE_CORAL, BlockStateProperties.WATERLOGGED, false);
			final var FIRE = property(DEAD_FIRE_CORAL, BlockStateProperties.WATERLOGGED, false);
			final var BUBBLE = property(DEAD_BUBBLE_CORAL, BlockStateProperties.WATERLOGGED, false);
			final var BUSH = from(DEAD_BUSH);
			final var ROSE = from(WITHER_ROSE);

			context.register(ASHFALL_DELTAS_VEGETATION, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(noiseBlocks(
				range(1), new NormalNoise.NoiseParameters(-7, 1, 0, 2), 1, 1234567, new NormalNoise.NoiseParameters(-7, 1, 0, 2), 0.75f,
				TUBE, BUSH, ROSE, FIRE, BUBBLE, FIRE, ROSE, BUBBLE, BUSH, TUBE, ROSE
			))));
		}
		context.register(ASHFALL_DELTAS_TREE, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration(
			block(ACACIA_WOOD), new CherryTrunkPlacer(5, 3, 6, ValueUtil.weightedInts(1, 1, 2, 1, 3, 1), num(3, 5), num(-4, -3), num(-1, 0)),
			block(property(OAK_LEAVES, BlockStateProperties.WATERLOGGED, false)), new CherryFoliagePlacer(num(4), num(0), num(5), 0.25f, 0.25f, 0.16666667f, 0.33333334f),
			Optional.of(new MangroveRootPlacer(num(0, 5), block(ACACIA_WOOD), Optional.of(new AboveRootPlacement(block(AIR), 0.5f)), new MangroveRootPlacement(
				blocksGetter.getOrThrow(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH),
				muddyBlocks, block(ACACIA_WOOD), 8, 15, 0.2f
			))),
			twoLayersSize(3, 0, 2), List.of(new LeaveVineDecorator(0.125f)), true, block(ACACIA_WOOD)
		)));
		context.register(ASHFALL_DELTAS_GRASS, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(weightedBlocks(new Block[]{SHORT_GRASS, FERN}, new int[]{16, 1}))));
		context.register(ASHFALL_DELTAS_ASH_PILE, new ConfiguredFeature<>(Feature.BLOCK_PILE, new BlockPileConfiguration(block(LIGHT_GRAY_CONCRETE_POWDER))));


		context.register(CRYSTAL_CRAGS_HILLS, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_STALACTITE, block(BASALT),
			direct(new PlacedFeature(direct(new ConfiguredFeature<>(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
				List.of(new BlockColumnConfiguration.Layer(num(1), block(BASALT))), Direction.UP, matchBlocks(AIR), true
			))), List.of())), CaveSurface.FLOOR, num(1), 0, 10, 1, num(3, 6), 0.5f
		)));
		context.register(CRYSTAL_CRAGS_CRYSTAL_ROOTS, new ConfiguredFeature<>(Feature.ROOT_SYSTEM, new RootSystemConfiguration(direct(new PlacedFeature(
			direct(new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(block(AMETHYST_BLOCK)))), List.of()
		)), 1, 3, WORLDGEN_STALACTITE, block(AMETHYST_BLOCK), 20, 100, 3, 2, block(AMETHYST_CLUSTER), 15, 1, all())));
		context.register(CRYSTAL_CRAGS_AMETHYST_CRYSTAL, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
			blocks(Stream.of(AMETHYST_CLUSTER, LARGE_AMETHYST_BUD, MEDIUM_AMETHYST_BUD, SMALL_AMETHYST_BUD).flatMap(b ->
				Stream.of(Direction.UP, Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST).map(d -> property(b, BlockStateProperties.FACING, d))
			).toArray(BlockState[]::new))
		)));
		context.register(CRYSTAL_CRAGS_AMETHYST_DELTA, new ConfiguredFeature<>(Feature.DELTA_FEATURE, new DeltaFeatureConfiguration(from(AMETHYST_BLOCK), from(SMOOTH_BASALT), num(0, 7), num(1, 3))));
		context.register(CRYSTAL_CRAGS_GRASS_DELTA, new ConfiguredFeature<>(Feature.DELTA_FEATURE, new DeltaFeatureConfiguration(from(ENDER_GRASS_BLOCK), from(ENDER_GRASS_BLOCK), num(4, 7), num(1, 3))));
		context.register(CRYSTAL_CRAGS_BUDDING_AMETHYST_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(
			List.of(OreConfiguration.target(new BlockMatchTest(AMETHYST_BLOCK), from(BUDDING_AMETHYST))),
			4, 0
		)));
		context.register(CRYSTAL_CRAGS_CRYSTAL_GRASS, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(blocks(amethystCrystalsUp))));
		context.register(CRYSTAL_CRAGS_GRASS, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(weightedBlocks(new Block[]{SHORT_GRASS, TALL_GRASS}, new int[]{4, 1}))));

		context.register(END_SHRUBLAND_GRASS, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(weightedBlocks(
			new BlockState[]{from(FERN), from(SHORT_GRASS), from(TALL_GRASS), from(TALL_GRASS), property(ACACIA_LEAVES, BlockStateProperties.PERSISTENT, true)},
			new int[]{2, 11, 2, 2, 2}
		))));
		context.register(END_SHRUBLAND_SHRUB, new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
			List.of(
				new WeightedPlacedFeature(direct(new PlacedFeature(direct(new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration(
					block(OAK_LOG), new StraightTrunkPlacer(1, 0, 0),
					block(OAK_LEAVES), new BushFoliagePlacer(num(2), num(1), 2),
					Optional.empty(), twoLayersSize(0, 0, 0), List.of(), false, block(ROOTED_ENDER_DIRT)
				))), List.of())), 0.1f),
				new WeightedPlacedFeature(direct(new PlacedFeature(direct(new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration(
					block(OAK_LOG), new StraightTrunkPlacer(2, 0, 0),
					block(OAK_LEAVES), new BlobFoliagePlacer(num(2), num(0), 1),
					Optional.empty(), twoLayersSize(0, 0, 0), List.of(), false, block(ROOTED_ENDER_DIRT)
				))), List.of())), 0.25f)
			),
			direct(new PlacedFeature(direct(new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration(
				block(OAK_LOG), new StraightTrunkPlacer(1, 0, 0),
				block(OAK_LEAVES), new BlobFoliagePlacer(num(2), num(0), 1),
				Optional.empty(), twoLayersSize(0, 0, 0), List.of(), false, block(ROOTED_ENDER_DIRT)
			))), List.of()))
		)));

		context.register(END_WILDS_DIRT, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_GRASS_BLOCK, weightedBlocks(new Block[]{ROOTED_ENDER_DIRT, ENDER_GRASS_BLOCK}, new int[]{6, 50}), nothing,
			CaveSurface.FLOOR, num(2), 0, 5, 0, num(1, 3), 0.5f
		)));
		context.register(END_WILDS_TREE, new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
			new WeightedPlacedFeature(direct(new PlacedFeature(direct(
				new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration(
					block(OAK_WOOD), new StraightTrunkPlacer(1, 0, 0),
					block(AIR), new BlobFoliagePlacer(num(0), num(0), 0),
					Optional.of(new MangroveRootPlacer(
						num(2, 4), block(OAK_WOOD),
						Optional.of(new AboveRootPlacement(
							block(OAK_WOOD), 0.25f
						)),
						new MangroveRootPlacement(blocksGetter.getOrThrow(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH), HolderSet.empty(), block(COBBLESTONE), 4, 15, 0.15f)
					)), twoLayersSize(0, 0, 0), List.of(), true, block(OAK_LOG)
				))
			), List.of(
				countPlace(50), randOffset(trapezoid(-2, 2, 0), trapezoid(0, 0, 0))
			))), 0.6f),
			new WeightedPlacedFeature(direct(new PlacedFeature(direct(
				new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration(
					block(OAK_WOOD), new StraightTrunkPlacer(5, 0, 2),
					block(AIR), new BlobFoliagePlacer(num(0), num(0), 0),
					Optional.empty(), twoLayersSize(), List.of(), true, block(OAK_WOOD)
				))
			), List.of(
				countPlace(4), randOffset(trapezoid(-2, 2, 0), trapezoid(0, 0, 0)), randOffset(num(0), num(3))
			))), 0.6f),
			new WeightedPlacedFeature(direct(new PlacedFeature(direct(
				new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration(
					block(OAK_LOG), new ForkingTrunkPlacer(9, 0, 3),
					block(property(ACACIA_LEAVES, BlockStateProperties.DISTANCE, 1)), new RandomSpreadFoliagePlacer(num(4), num(0),
					num(2), 200), Optional.empty(), twoLayersSize(), List.of(), true, block(OAK_WOOD)
				))
			), List.of(
				countPlace(3), randOffset(trapezoid(-2, 2, 0), trapezoid(0, 0, 0)), randOffset(num(0), num(9))
			))), 0.6f)
		), nothing)));
		context.register(END_WILDS_GRASS, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(weightedBlocks(
			new Block[]{FERN, SHORT_GRASS, TALL_GRASS, LARGE_FERN}, new int[]{2, 11, 2, 2}
		))));
		context.register(END_WILDS_BUSH, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_END_STONE, block(ENDER_GRASS_BLOCK),
			direct(new PlacedFeature(
				direct(new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(block(property(OAK_LEAVES, BlockStateProperties.PERSISTENT, true))))),
				List.of(countPlace(3), randOffset(trapezoid(-1, 1, 0), trapezoid(-1, 1, 0)), blockFilter(all()))
			)), CaveSurface.FLOOR, num(1), 0, 1, 1, num(0), 0
		)));

		context.register(ENDER_WASTES_HILLS, new ConfiguredFeature<>(Feature.VEGETATION_PATCH,
			new VegetationPatchConfiguration(
				WORLDGEN_STALACTITE, block(END_STONE), direct(new PlacedFeature(direct(
				new ConfiguredFeature<>(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
					List.of(new BlockColumnConfiguration.Layer(num(1), block(END_STONE))),
					Direction.UP, matchBlocks(AIR), true
				))
			), List.of())), CaveSurface.FLOOR, num(1), 0, 11, 1, num(3, 6), 0.5f
			)
		));

		context.register(ENDLESS_DUNES_SAND_DELTA, new ConfiguredFeature<>(Feature.DELTA_FEATURE, new DeltaFeatureConfiguration(from(SAND), from(COARSE_DIRT), num(4, 16), num(1, 4))));
		context.register(ENDLESS_DUNES_VEGETATION, new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
			new WeightedPlacedFeature(direct(new PlacedFeature(direct(new ConfiguredFeature<>(
				Feature.BLOCK_COLUMN,
				new BlockColumnConfiguration(List.of(
					new BlockColumnConfiguration.Layer(num(1), block(END_STONE)),
					new BlockColumnConfiguration.Layer(num(0, 3), block(property(property(CHORUS_PLANT, BlockStateProperties.UP, true), BlockStateProperties.DOWN, true))),
					new BlockColumnConfiguration.Layer(num(1), block(CHORUS_FLOWER))
				), Direction.UP, all(), true))),
				List.of(countPlace(16), randOffset(trapezoid(-6, 6, 0), trapezoid(-4, 4, 0)), blockFilter(all(matchBlocks(vec(0, 1, 0), AIR), matchBlocks(SAND, END_STONE))))
			)), 0.1f)), direct(new PlacedFeature(
			direct(new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(weightedBlocks(new Block[]{DEAD_BUSH, CACTUS_FLOWER}, new int[]{9, 1})))),
			List.of(countPlace(24), randOffset(trapezoid(-7, 7, 0), trapezoid(-4, 4, 0)), blockFilter(all(matchBlocks(AIR), wouldSurvive(DEAD_BUSH))))
		)))));
		context.register(ENDLESS_DUNES_GRASS, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			BlockTags.SAND, weightedBlocks(new Block[]{ENDER_GRASS_BLOCK, SAND, MOSS_BLOCK}, new int[]{3, 10, 1}),
			direct(new PlacedFeature(direct(new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
				weightedBlocks(new Block[]{SHORT_GRASS, MOSS_CARPET}, new int[]{12, 7})))
			), List.of(blockFilter(matchBlocks(vec(0, -1, 0), ENDER_GRASS_BLOCK, MOSS_BLOCK))))),
			CaveSurface.FLOOR, num(1), 0, 3, 0.5f, num(2, 3), 0.3f
		)));
		var endlessDunesOasisDirt = context.register(ENDLESS_DUNES_OASIS_DIRT, new ConfiguredFeature<>(Feature.DISK, new DiskConfiguration(
			weightedBlocks(new Block[]{COARSE_DIRT, ROOTED_ENDER_DIRT}, new int[]{3, 2}),
			matchTag(WORLDGEN_ENDLESS_DUNES_DUNE), num(6, 8), 1
		)));
		var endlessDunesOasisVegetation = context.register(ENDLESS_DUNES_OASIS_VEGETATION, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_ENDLESS_DUNES_DUNE, block(ENDER_GRASS_BLOCK),
			direct(new PlacedFeature(direct(
				new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(weightedBlocks(
					new BlockState[]{from(TALL_GRASS), from(SHORT_GRASS), property(ACACIA_LEAVES, BlockStateProperties.PERSISTENT, true), persistAzaleaLeaves},
					new int[]{5, 24, 2, 1}
				)))
			), List.of())), CaveSurface.FLOOR, num(1), 0, 6, 0.15f, num(4), 0.33f
		)));
		var endlessDunesOasisPalmTree = context.register(ENDLESS_DUNES_OASIS_PALM_TREE, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration(
			block(JUNGLE_LOG), new ForkingTrunkPlacer(5, 3, 2),
			block(JUNGLE_LEAVES), new RandomSpreadFoliagePlacer(num(3, 4), num(0), num(2, 3), 48),
			Optional.empty(), twoLayersSize(), List.of(new LeaveVineDecorator(0.08f), new AttachedToLeavesDecorator(0.1f, 2, 1, block(SHROOMLIGHT), 1, List.of(Direction.DOWN))),
			false, block(ROOTED_ENDER_DIRT)
		)));
		var endlessDunesOasisVegetationMiddle = context.register(ENDLESS_DUNES_OASIS_VEGETATION_MIDDLE, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(weightedBlocks(
			new BlockState[]{property(ACACIA_LEAVES, BlockStateProperties.PERSISTENT, true), persistAzaleaLeaves, from(ORANGE_TULIP), from(PINK_TULIP), from(WHITE_TULIP), from(LILAC), from(PITCHER_PLANT)},
			new int[]{2, 2, 2, 2, 2, 2, 1}
		))));
		var endlessDunesOasisVegetationLake = context.register(ENDLESS_DUNES_OASIS_VEGETATION_LAKE, new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
			new WeightedPlacedFeature(direct(new PlacedFeature(
				direct(new ConfiguredFeature<>(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
					List.of(new BlockColumnConfiguration.Layer(num(1), block(ENDER_GRASS_BLOCK)), new BlockColumnConfiguration.Layer(num(1, 4), randIntState(SUGAR_CANE, BlockStateProperties.AGE_15, num(1, 12)))), Direction.UP, all(), false
				))), List.of()
			)), 0.1f),
			new WeightedPlacedFeature(direct(new PlacedFeature(
				direct(new ConfiguredFeature<>(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
					List.of(new BlockColumnConfiguration.Layer(biasBottom(2, 3), block(persistAzaleaLeaves))), Direction.UP, matchBlocks(vec(0, 1, 0), AIR), false
				))), List.of()
			)), 0.2f),
			new WeightedPlacedFeature(direct(new PlacedFeature(
				direct(new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(weightedBlocks(
					Stream.concat(
						Stream.of(from(SEAGRASS), persistAzaleaLeaves, property(FLOWERING_AZALEA_LEAVES, BlockStateProperties.PERSISTENT, true)),
						Direction.Plane.HORIZONTAL.stream().map(d -> property(SMALL_DRIPLEAF, BlockStateProperties.HORIZONTAL_FACING, d))
					).toArray(BlockState[]::new),
					new int[]{5, 5, 14, 1, 1, 1, 1}
				)))), List.of()
			)), 0.25f),
			new WeightedPlacedFeature(direct(new PlacedFeature(
				direct(new ConfiguredFeature<>(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
					List.of(new BlockColumnConfiguration.Layer(num(1), block(WATER)), new BlockColumnConfiguration.Layer(num(1), block(LILY_PAD))),
					Direction.UP, all(), false
				))), List.of())), 0.35f)
		), nothing)));
		var endlessDunesOasisLake = context.register(ENDLESS_DUNES_OASIS_LAKE, new ConfiguredFeature<>(Feature.WATERLOGGED_VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_ENDLESS_DUNES_OASIS, block(CLAY),
			direct(new PlacedFeature(endlessDunesOasisVegetationLake, List.of(
				countPlace(12), randOffset(trapezoid(-6, 6, 0), num(0)), blockFilter(matchBlocks(WATER)))
			)), CaveSurface.FLOOR, num(2), 0.8f, 5, 0.1f, num(5, 8), 0.7f
		)));
		var endlessDunesOasisRock = context.register(ENDLESS_DUNES_OASIS_ROCK, new ConfiguredFeature<>(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
			direct(new PlacedFeature(direct(new ConfiguredFeature<>(Feature.BLOCK_BLOB, new BlockBlobConfiguration(
				from(COBBLESTONE), all()
			))), List.of())),
			direct(new PlacedFeature(direct(new ConfiguredFeature<>(Feature.BLOCK_BLOB, new BlockBlobConfiguration(
				from(MOSSY_COBBLESTONE), all()
			))), List.of()))
		))));
		context.register(ENDLESS_DUNES_OASIS, new ConfiguredFeature<>(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
			direct(new PlacedFeature(endlessDunesOasisDirt, List.of())),
			direct(new PlacedFeature(endlessDunesOasisVegetation, List.of(
				countPlace(10), randOffset(trapezoid(-12, 12, 0), trapezoid(-2, 2, 0))
			))),
			direct(new PlacedFeature(endlessDunesOasisPalmTree, List.of(
				countPlace(1), randOffset(trapezoid(-11, 11, 0), trapezoid(-4, 4, 0)), envScan(Direction.DOWN, solid(), all(), 10)
			))),
			direct(new PlacedFeature(endlessDunesOasisVegetationMiddle, List.of(
				countPlace(12), randOffset(trapezoid(-8, 8, 0), trapezoid(-2, 2, 0)), blockFilter(all(matchBlocks(AIR, SHORT_GRASS), wouldSurvive(SHORT_GRASS)))
			))),
			direct(new PlacedFeature(endlessDunesOasisLake, List.of(blockFilter(matchTag(vec(0, -1, 0), WORLDGEN_ENDLESS_DUNES_OASIS))))),
			direct(new PlacedFeature(endlessDunesOasisRock, List.of(
					countPlace(1), rarity(3), randOffset(trapezoid(-11, 11, 0), trapezoid(-4, 4, 0)), blockFilter(matchBlocks(vec(0, -1, 0), ENDER_GRASS_BLOCK))
				))
			)
		))));

		for (var hill : List.of(
			new Tuple3<>(FIERY_HILLS_HILLS, WORLDGEN_FIERY_HILLS_END_STONE, END_STONE),
			new Tuple3<>(FIERY_HILLS_BASALT_HILLS, WORLDGEN_FIERY_HILLS_BASALT, BASALT),
			new Tuple3<>(FIERY_HILLS_BLACKSTONE_HILLS, WORLDGEN_FIERY_HILLS_BLACKSTONE, BLACKSTONE)
		))
			context.register(hill._1(), new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
				hill._2(), block(hill._3()), direct(new PlacedFeature(direct(
				new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(block(hill._3()))
				)), List.of())), CaveSurface.FLOOR, num(1), 0, 15, 1, num(5, 6), 0.5f
			)));
		for (var delta : List.of(
			new Tuple3<>(FIERY_HILLS_LAVA_DELTA, LAVA, MAGMA_BLOCK),
			new Tuple3<>(FIERY_HILLS_SAND_DELTA, RED_SAND, NETHER_WART_BLOCK)
		))
			context.register(delta._1(), new ConfiguredFeature<>(Feature.DELTA_FEATURE, new DeltaFeatureConfiguration(from(delta._2()), from(delta._3()), num(5, 9), num(0))));
		context.register(FIERY_HILLS_GOLD_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(
			List.of(OreConfiguration.target(new BlockMatchTest(BLACKSTONE), from(GILDED_BLACKSTONE))),
			10, 0
		)));
		context.register(FIERY_HILLS_MAGMA_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(
			Stream.of(END_STONE, RED_SAND, BASALT, SMOOTH_BASALT, BLACKSTONE).map(b -> OreConfiguration.target(new BlockMatchTest(b), from(MAGMA_BLOCK))).toList(),
			33, 0
		)));
		context.register(FIERY_HILLS_SAND, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_FIERY_HILLS_END_STONE, block(SAND), nothing, CaveSurface.FLOOR, num(7), 0.15f, 15, 0, num(4, 5), 0.15f
		)));
		context.register(FIERY_HILLS_VENT, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(weightedBlocks(
			Stream.of(false, true).map(b -> property(CAMPFIRE, BlockStateProperties.SIGNAL_FIRE, b)).toArray(BlockState[]::new), new int[]{6, 1}
		))));
		context.register(FIERY_HILLS_FIRE, new ConfiguredFeature<>(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
			List.of(
				new BlockColumnConfiguration.Layer(num(1), blocks(NETHERRACK, MAGMA_BLOCK, OBSIDIAN, CRYING_OBSIDIAN)),
				new BlockColumnConfiguration.Layer(num(1), block(FIRE))
			), Direction.UP, all(), false
		)));
		context.register(FIERY_HILLS_TREE, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration(
			block(CRIMSON_HYPHAE), new ForkingTrunkPlacer(5, 2, 0),
			block(OAK_LEAVES), new CherryFoliagePlacer(num(4), num(0), num(5), 0.25f, 0.8f, 0.16666667f, 0.33333334f),
			Optional.of(new MangroveRootPlacer(
				num(3, 7), block(CRIMSON_HYPHAE), Optional.of(new AboveRootPlacement(block(AIR), 0.5f)),
				new MangroveRootPlacement(blocksGetter.getOrThrow(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH), muddyBlocks, block(CRIMSON_HYPHAE), 8, 15, 0.2f)
			)),
			twoLayersSize(3, 0, 2), List.of(new AttachedToLeavesDecorator(0.1f, 3, 2, block(SHROOMLIGHT), 1, List.of(Direction.DOWN))),
			true, block(CRIMSON_HYPHAE)
		)));
		context.register(FIERY_HILLS_VEGETATION, new ConfiguredFeature<>(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
			List.of(new BlockColumnConfiguration.Layer(num(1), blocks(CRIMSON_ROOTS, CRIMSON_FUNGUS)), new BlockColumnConfiguration.Layer(num(1), block(CRIMSON_NYLIUM))),
			Direction.DOWN, all(), true
		)));

		context.register(FLESH_TUNDRA_NETHERRACK_BOTTOM, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_END_STONE, block(NETHERRACK),
			direct(new PlacedFeature(direct(new ConfiguredFeature<>(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
				List.of(new BlockColumnConfiguration.Layer(ValueUtil.weightedInts(1, 1, 0, 14), block(NETHERRACK)), new BlockColumnConfiguration.Layer(num(1), block(NETHERRACK))),
				Direction.DOWN, matchBlocks(AIR), true
			))), List.of(envScan(Direction.UP, solid(), all(), 32)))),
			CaveSurface.CEILING, num(4), 0, 25, 1, num(2, 4), 0.5f
		)));
		context.register(FLESH_TUNDRA_CRIMSON_DELTAS, new ConfiguredFeature<>(Feature.DELTA_FEATURE, new DeltaFeatureConfiguration(from(CRIMSON_NYLIUM), from(NETHER_WART_BLOCK), num(6, 7), num(0, 6))));
		context.register(FLESH_TUNDRA_BONE_CEILING, new ConfiguredFeature<>(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
			List.of(new BlockColumnConfiguration.Layer(num(9, 24), block(BONE_BLOCK)), new BlockColumnConfiguration.Layer(ValueUtil.weightedInts(0, 40, 1, 1), block(property(SOUL_LANTERN, BlockStateProperties.HANGING, true)))),
			Direction.DOWN, matchBlocks(AIR), true
		)));
		var fleshTundraTree = context.register(FLESH_TUNDRA_TREE, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration(
			block(BONE_BLOCK), new FancyTrunkPlacer(12, 8, 6),
			block(AIR), new DarkOakFoliagePlacer(num(0), num(0)),
			Optional.empty(), threeLayersSize(3, 1, 4, 5, 3), List.of(), true, block(BONE_BLOCK)
		)));
		var fleshTundraRib = context.register(FLESH_TUNDRA_RIB, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration(
			block(BONE_BLOCK), new BendingTrunkPlacer(4, 3, 0, 1, num(1, 3)),
			block(AIR), new BlobFoliagePlacer(num(0), num(0), 0),
			Optional.empty(), threeLayersSize(1, 1, 0, 1, 2), List.of(), true, block(BONE_BLOCK)
		)));
		context.register(FLESH_TUNDRA_TREE_PATCH, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_FLESH_TUNDRA_SURFACE, block(BONE_BLOCK),
			direct(new PlacedFeature(direct(new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
				new WeightedPlacedFeature(direct(new PlacedFeature(fleshTundraRib, List.of())), 0.66f),
				new WeightedPlacedFeature(direct(new PlacedFeature(fleshTundraTree, List.of())), 0.44f)
			), nothing))), List.of())), CaveSurface.FLOOR, num(1), 0, 1, 1, num(0), 0
		)));
		context.register(FLESH_TUNDRA_VEGETATION, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(weightedBlocks(
			new BlockState[]{property(OAK_LEAVES, BlockStateProperties.PERSISTENT, true), from(NETHER_WART_BLOCK)}, new int[]{1, 6}
		))));
		var vines = new ArrayList<>(Stream.of(VineBlock.NORTH, VineBlock.SOUTH, VineBlock.EAST, VineBlock.WEST)
			.map(b -> {
				var vine = block(property(VINE, b, true));
				return new WeightedPlacedFeature(direct(new PlacedFeature(direct(new ConfiguredFeature<>(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
					List.of(new BlockColumnConfiguration.Layer(num(1), vine), new BlockColumnConfiguration.Layer(num(12, 33), vine)),
					Direction.DOWN, matchBlocks(vec(0, 1, 0), AIR), true
				))), List.of())), 0.1f);
			}).toList());
		vines.add(new WeightedPlacedFeature(direct(new PlacedFeature(direct(new ConfiguredFeature<>(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(
			new BlockColumnConfiguration.Layer(weightedInts(
				new IntProvider[]{num(1, 3), num(4, 7), num(8, 15), num(16, 25)}, new int[]{10, 7, 3, 1}
			), block(WEEPING_VINES_PLANT)),
			new BlockColumnConfiguration.Layer(num(1), randIntState(WEEPING_VINES, WeepingVinesBlock.AGE, num(23, 25)))
		), Direction.DOWN, matchBlocks(AIR), true))), List.of())), 0.44f));
		context.register(FLESH_TUNDRA_VINES, new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
			vines, nothing
		)));
		context.register(FLESH_TUNDRA_ROOTS, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(weightedBlocks(
			new Block[]{CRIMSON_ROOTS, CRIMSON_FUNGUS}, new int[]{5, 1}
		))));

		context.register(FROSTED_VALLEY_HILLS, new ConfiguredFeature<>(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
			WORLDGEN_FROZEN_SPIKES_SURFACE, block(SNOW_BLOCK),
			direct(new PlacedFeature(direct(new ConfiguredFeature<>(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
				List.of(new BlockColumnConfiguration.Layer(num(1), block(SNOW_BLOCK))), Direction.UP, matchBlocks(AIR), true
			))), List.of())),
			CaveSurface.FLOOR, num(1), 0, 10, 1, num(4, 6), 0.5f
		)));

		context.register(FROZEN_SPIKES_LARGE_DRIPSTONE, new ConfiguredFeature<>(Feature.LARGE_DRIPSTONE, ExtLargeDripstoneConfiguration.apply(new LargeDripstoneConfiguration(
			70, num(4, 8), numf(0.4f, 3), 0.2f, numf(0.1f, 0.4f), numf(0.1f, 0.3f), trapezoidf(0, 0.06f, 0), 0, 0.25f
		), from(PACKED_ICE))));
		context.register(FROZEN_SPIKES_BLUE_ICE_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(
			List.of(OreConfiguration.target(new BlockMatchTest(PACKED_ICE), from(BLUE_ICE))), 40, 0
		)));
	}
}
