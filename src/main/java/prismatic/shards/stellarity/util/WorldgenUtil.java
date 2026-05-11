package prismatic.shards.stellarity.util;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.TagKey;
import net.minecraft.util.InclusiveRange;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.*;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.*;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

import static prismatic.shards.stellarity.util.ValueUtil.*;

public interface WorldgenUtil {
	static SimpleStateProvider block(Block block) {
		return BlockStateProvider.simple(block);
	}

	static SimpleStateProvider block(BlockState blockState) {
		return BlockStateProvider.simple(blockState);
	}

	static CountPlacement countPlace(IntProvider count) {
		return CountPlacement.of(count);
	}

	static CountPlacement countPlace(int count) {
		return countPlace(num(count));
	}

	static InSquarePlacement inSquare() {
		return InSquarePlacement.spread();
	}

	static NoiseBasedCountPlacement noiseCount(final int noiseToCountRatio, final double noiseFactor, final double noiseOffset) {
		return NoiseBasedCountPlacement.of(noiseToCountRatio, noiseFactor, noiseOffset);
	}

	static HeightRangePlacement heightRange(HeightProvider heightProvider) {
		return HeightRangePlacement.of(heightProvider);
	}

	static UniformHeight height(VerticalAnchor min, VerticalAnchor max) {
		return UniformHeight.of(min, max);
	}

	static VerticalAnchor aboveBottom(int offset) {
		return VerticalAnchor.aboveBottom(offset);
	}

	static VerticalAnchor belowTop(int offset) {
		return VerticalAnchor.belowTop(offset);
	}

	static EnvironmentScanPlacement envScan(final Direction directionOfSearch, final BlockPredicate targetCondition, final BlockPredicate allowedSearchCondition, final int maxSteps) {
		return EnvironmentScanPlacement.scanningFor(directionOfSearch, targetCondition, allowedSearchCondition, maxSteps);
	}

	static BlockPredicate matchBlocks(Block... blocks) {
		return BlockPredicate.matchesBlocks(blocks);
	}

	static BlockPredicate matchBlocks(Vec3i offset, Block... blocks) {
		return BlockPredicate.matchesBlocks(offset, blocks);
	}

	static BlockPredicate solid() {
		return BlockPredicate.solid();
	}

	static BiomeFilter biome() {
		return BiomeFilter.biome();
	}

	static BlockColumnConfiguration.Layer columnLayer(IntProvider intProvider, BlockStateProvider state) {
		return new BlockColumnConfiguration.Layer(intProvider, state);
	}

	static BlockColumnConfiguration blockColumns(Direction direction, BlockPredicate allowedPlacement, boolean prioritizeTip, BlockColumnConfiguration.Layer... layers) {
		return new BlockColumnConfiguration(List.of(layers), direction, allowedPlacement, prioritizeTip);
	}

	static WeightedStateProvider weightedBlocks(BlockState[] states, int[] weights) {
		return new WeightedStateProvider(weighted(states, weights));
	}

	static WeightedStateProvider weightedBlocks(Block[] states, int[] weights) {
		return new WeightedStateProvider(weighted(Arrays.stream(states).map(Block::defaultBlockState).toArray(BlockState[]::new), weights));
	}

	static WeightedStateProvider blocks(Block... states) {
		return new WeightedStateProvider(weightedEven(Arrays.stream(states).map(Block::defaultBlockState).toArray(BlockState[]::new)));
	}

	static WeightedStateProvider blocks(BlockState... states) {
		return new WeightedStateProvider(weightedEven(states));
	}

	static BlockPredicate all() {
		return BlockPredicate.alwaysTrue();
	}

	static RarityFilter rarity(int onAverageEvery) {
		return RarityFilter.onAverageOnceEvery(onAverageEvery);
	}

	static HeightmapPlacement heightmap(Heightmap.Types heightmap) {
		return HeightmapPlacement.onHeightmap(heightmap);
	}

	static WeightedPlacedFeature weightedPlaced(PlacedFeature placedFeature, float chance) {
		return new WeightedPlacedFeature(Holder.direct(placedFeature), chance);
	}

	static TwoLayersFeatureSize twoLayersSize() {
		return new TwoLayersFeatureSize(1, 0, 1);
	}

	static TwoLayersFeatureSize twoLayersSize(int limit, int lowerSize, int upperSize) {
		return new TwoLayersFeatureSize(limit, lowerSize, upperSize);
	}

	@SuppressWarnings("deprecation")
	static CountOnEveryLayerPlacement everyLayer(IntProvider provider) {
		return CountOnEveryLayerPlacement.of(provider);
	}

	@SuppressWarnings("deprecation")
	static CountOnEveryLayerPlacement everyLayer(int count) {
		return everyLayer(num(count));
	}

	static SurfaceRules.RuleSource state(final Block state) {
		return SurfaceRules.state(state.defaultBlockState());
	}

	static ThreeLayersFeatureSize threeLayersSize() {
		return new ThreeLayersFeatureSize(1, 1, 0, 1, 1, OptionalInt.empty());
	}

	static BlockPredicateFilter blockFilter(BlockPredicate predicate) {
		return BlockPredicateFilter.forPredicate(predicate);
	}


	static BlockPredicate all(BlockPredicate... predicates) {
		return BlockPredicate.allOf(List.of(predicates));
	}

	static BlockPredicate any(BlockPredicate... predicates) {
		return BlockPredicate.anyOf(List.of(predicates));
	}

	static BlockPredicate wouldSurvive(BlockState state) {
		return BlockPredicate.wouldSurvive(state, Vec3i.ZERO);
	}

	static BlockPredicate wouldSurvive(Block block) {
		return wouldSurvive(block.defaultBlockState());
	}

	static NoiseProvider noiseBlocks(int seed, NormalNoise.NoiseParameters noise, float scale, BlockState... blockStates) {
		return new NoiseProvider(seed, noise, scale, List.of(blockStates));
	}

	static NoiseProvider noiseBlocks(int seed, NormalNoise.NoiseParameters noise, float scale, Block... blocks) {
		return new NoiseProvider(seed, noise, scale, Arrays.stream(blocks).map(Block::defaultBlockState).toList());
	}

	static DualNoiseProvider noiseBlocks(InclusiveRange<Integer> variety, NormalNoise.NoiseParameters slowNoiseParameters, float slowScale, long seed, NormalNoise.NoiseParameters parameters, float scale, BlockState... blockStates) {
		return new DualNoiseProvider(variety, slowNoiseParameters, slowScale, seed, parameters, scale, List.of(blockStates));
	}

	static DualNoiseProvider noiseBlocks(InclusiveRange<Integer> variety, NormalNoise.NoiseParameters slowNoiseParameters, float slowScale, long seed, NormalNoise.NoiseParameters parameters, float scale, Block... blocks) {
		return new DualNoiseProvider(variety, slowNoiseParameters, slowScale, seed, parameters, scale, Arrays.stream(blocks).map(Block::defaultBlockState).toList());
	}

	static RandomOffsetPlacement randOffset(IntProvider xzSpread, IntProvider ySpread) {
		return RandomOffsetPlacement.of(xzSpread, ySpread);
	}

	static HasSturdyFacePredicate sturdyFace(Direction direction) {
		return new HasSturdyFacePredicate(Vec3i.ZERO, direction);
	}

	static HasSturdyFacePredicate sturdyFace(Vec3i offset, Direction direction) {
		return new HasSturdyFacePredicate(offset, direction);
	}

	static SurfaceRelativeThresholdFilter surfaceRelative(Heightmap.Types heightmap, int min, int max) {
		return SurfaceRelativeThresholdFilter.of(heightmap, min, max);
	}

	static SurfaceRelativeThresholdFilter surfaceRelative(Heightmap.Types heightmap) {
		return surfaceRelative(heightmap, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	static VerticalAnchor.Absolute absolute(int y) {
		return new VerticalAnchor.Absolute(y);
	}

	static BlockPredicate not(BlockPredicate predicate) {
		return BlockPredicate.not(predicate);
	}

	static BlockPredicate replaceable() {
		return BlockPredicate.replaceable();
	}

	static BlockPredicate replaceable(Vec3i pos) {
		return BlockPredicate.replaceable(pos);
	}

	static BlockPredicate matchTag(TagKey<Block> tag) {
		return BlockPredicate.matchesTag(tag);
	}

	static BlockPredicate matchTag(Vec3i pos, TagKey<Block> tag) {
		return BlockPredicate.matchesTag(pos, tag);
	}

	static RandomizedIntStateProvider randIntState(BlockStateProvider block, IntegerProperty property, IntProvider num) {
		return new RandomizedIntStateProvider(block, property, num);
	}

	static RandomizedIntStateProvider randIntState(BlockState block, IntegerProperty property, IntProvider num) {
		return new RandomizedIntStateProvider(block(block), property, num);
	}

	static RandomizedIntStateProvider randIntState(Block block, IntegerProperty property, IntProvider num) {
		return new RandomizedIntStateProvider(block(block), property, num);
	}

	static SimpleStateProvider randIntState(Block block) {
		return RandomizedIntStateProvider.simple(block);
	}

	static SimpleStateProvider randIntState(BlockState block) {
		return RandomizedIntStateProvider.simple(block);
	}
}
