package dev.coder2195.stellarity.registry.tree_decorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.jspecify.annotations.NonNull;
import dev.coder2195.stellarity.registry.StellarityTreeDecorators;
import dev.coder2195.stellarity.util.tuple.Tuple2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class HangingColumnDecorator extends TreeDecorator {
	public static final MapCodec<HangingColumnDecorator> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
		Codec.FLOAT.fieldOf("chance").forGetter(HangingColumnDecorator::chance),
		Codec.INT.fieldOf("min_size").forGetter(HangingColumnDecorator::minSize),
		Codec.BOOL.fieldOf("place_under_trunk").forGetter(HangingColumnDecorator::placeUnderTrunk),
		Codec.BOOL.fieldOf("place_under_leaves").forGetter(HangingColumnDecorator::placeUnderLeaves),
		BlockColumnConfiguration.Layer.CODEC.listOf().fieldOf("layers").forGetter(HangingColumnDecorator::layers),
		Codec.BOOL.fieldOf("prioritize_tip").forGetter(HangingColumnDecorator::prioritizeTip)
	).apply(instance, HangingColumnDecorator::new));

	private final float chance;
	private final int minSize;
	private final boolean placeUnderTrunk;
	private final boolean placeUnderLeaves;
	private final List<BlockColumnConfiguration.Layer> layers;
	private final boolean prioritizeTip;

	public HangingColumnDecorator(float chance, int minSize, boolean placeUnderTrunk, boolean placeUnderLeaves,
	                              List<BlockColumnConfiguration.Layer> layers, boolean prioritizeTip) {
		super();
		this.chance = chance;
		this.minSize = minSize;
		this.placeUnderTrunk = placeUnderTrunk;
		this.placeUnderLeaves = placeUnderLeaves;
		this.layers = layers;
		this.prioritizeTip = prioritizeTip;
	}

	public float chance() {
		return chance;
	}

	public int minSize() {
		return minSize;
	}

	public boolean placeUnderTrunk() {
		return placeUnderTrunk;
	}

	public boolean placeUnderLeaves() {
		return placeUnderLeaves;
	}

	public List<BlockColumnConfiguration.Layer> layers() {
		return layers;
	}

	public boolean prioritizeTip() {
		return prioritizeTip;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (HangingColumnDecorator) obj;
		return Float.floatToIntBits(this.chance) == Float.floatToIntBits(that.chance) &&
			this.minSize == that.minSize &&
			this.placeUnderTrunk == that.placeUnderTrunk &&
			this.placeUnderLeaves == that.placeUnderLeaves &&
			Objects.equals(this.layers, that.layers) &&
			this.prioritizeTip == that.prioritizeTip;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chance, minSize, placeUnderTrunk, placeUnderLeaves, layers, prioritizeTip);
	}

	@Override
	public String toString() {
		return "HangingColumnDecorator[" +
			"chance=" + chance + ", " +
			"minSize=" + minSize + ", " +
			"placeUnderTrunk=" + placeUnderTrunk + ", " +
			"placeUnderLeaves=" + placeUnderLeaves + ", " +
			"layers=" + layers + ", " +
			"prioritizeTip=" + prioritizeTip + ']';
	}


	@Override
	protected @NonNull TreeDecoratorType<?> type() {
		return StellarityTreeDecorators.HANGING_COLUMN;
	}

	@Override
	public void place(@NonNull Context context) {
		var leaves = context.leaves();
		var logs = context.logs();
		var level = context.level();
		var random = context.random();


		HashSet<BlockPos> invalid = new HashSet<>();

		Stream<BlockPos> combined = Stream.concat(placeUnderLeaves ? leaves.stream() : Stream.empty(), placeUnderTrunk ? logs.stream() : Stream.empty());
		invalid.addAll(leaves);
		invalid.addAll(logs);

		int minY = level.getMinY();
		for (var toPlace : (Iterable<BlockPos>) combined::iterator) {
			BlockPos pointer = toPlace;
			ArrayList<BlockPos> belowCandidates = new ArrayList<>();
			while (true) {
				pointer = pointer.below();
				if (invalid.contains(pointer) || !level.getBlockState(pointer).isAir()) {
					invalid.add(pointer);
					break;
				}
				belowCandidates.add(pointer);
				if (pointer.getY() <= minY) break;
			}

			int columnSize = belowCandidates.size();

			if (columnSize < minSize) {
				invalid.addAll(belowCandidates);
				continue;
			}

			if (context.random().nextFloat() > chance) continue;

			var resolvedLayers = (prioritizeTip ? layers.reversed() : layers).stream().map(layer -> new Tuple2<>(layer.height().sample(random), layer.state())).toList();
			var resolvedColumn = prioritizeTip ? belowCandidates.reversed() : belowCandidates;

			int currentIdx = 0;
			if (prioritizeTip) {
				int resolvedLayersSize = 0;
				for (var resolvedLayer : resolvedLayers) {
					resolvedLayersSize += resolvedLayer._1();
				}
				if (resolvedLayersSize < columnSize) currentIdx = columnSize - resolvedLayersSize;
			}
			for (var layer : resolvedLayers) {
				int layerSize = layer._1();
				boolean toBreak = false;
				for (int i = 0; i < layerSize; i++) {
					var pos = resolvedColumn.get(currentIdx);
					level.setBlock(pos, layer._2().getState(level, random, pos), Block.UPDATE_CLIENTS);

					currentIdx++;
					if (currentIdx >= columnSize) {
						toBreak = true;
						break;
					}

				}
				if (toBreak) break;
			}
		}
	}
}
