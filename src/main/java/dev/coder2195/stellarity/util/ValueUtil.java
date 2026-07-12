package dev.coder2195.stellarity.util;

import net.minecraft.core.Vec3i;
import net.minecraft.util.InclusiveRange;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.Vec3;

import java.util.Arrays;

public interface ValueUtil {
	static ConstantInt num(int value) {
		return ConstantInt.of(value);
	}

	/**
	 * DATA THEN WEIGHT I MADE A JAVADOC CUZ OF THIS REASON
	 *
	 * @param values DATA THEN WEIGHT
	 * @return weightedlist thingy
	 */
	static WeightedListInt weightedInts(int... values) {
		if (values.length % 2 != 0) throw new IllegalArgumentException("Must be even number of values (value, weight)");
		@SuppressWarnings("unchecked") Weighted<IntProvider>[] weights = new Weighted[values.length / 2];

		for (int i = 0; i < weights.length; i++) {
			weights[i] = new Weighted<>(num(values[2 * i]), values[2 * i + 1]);
		}

		return new WeightedListInt(WeightedList.of(weights));
	}

	static WeightedListInt weightedInts(IntProvider[] ints, int[] weights) {
		return new WeightedListInt(weighted(ints, weights));
	}

	static UniformInt num(int min, int max) {
		return UniformInt.of(min, max);
	}

	static BiasedToBottomInt biasBottom(int min, int max) {
		return BiasedToBottomInt.of(min, max);
	}

	static ConstantFloat numf(float num) {
		return ConstantFloat.of(num);
	}

	static UniformFloat numf(float min, float max) {
		return UniformFloat.of(min, max);
	}


	static <T> WeightedList<T> weighted(T[] values, int[] weights) {
		if (values.length != weights.length)
			throw new IllegalArgumentException("Blockstates must correspond to weights. %s %s".formatted(Arrays.toString(values), Arrays.toString(weights)));
		@SuppressWarnings("unchecked") Weighted<T>[] weightList = new Weighted[values.length];
		for (int i = 0; i < values.length; i++) {
			weightList[i] = new Weighted<>(values[i], weights[i]);
		}

		return WeightedList.of(weightList);
	}

	@SafeVarargs
	static <T> WeightedList<T> weightedEven(T... values) {
		//noinspection unchecked
		return WeightedList.of(Arrays.stream(values).map(value -> new Weighted<>(value, 1)).toArray(Weighted[]::new));
	}

	static TrapezoidFloat trapezoidf(float min, float max, float plateau) {
		return new TrapezoidFloat(min, max, plateau);
	}

	static TrapezoidInt trapezoid(int min, int max, int plateau) {
		return new TrapezoidInt(min, max, plateau);
	}

	static <T extends Comparable<T>> InclusiveRange<T> range(T min, T max) {
		return new InclusiveRange<>(min, max);
	}

	static <T extends Comparable<T>> InclusiveRange<T> range(T constant) {
		return new InclusiveRange<>(constant, constant);
	}

	static BlockState from(Block block) {
		return block.defaultBlockState();
	}

	static <T extends Comparable<T>> BlockState property(BlockState state, Property<T> key, T value) {
		return state.setValue(key, value);
	}

	static <T extends Comparable<T>> BlockState property(Block block, Property<T> key, T value) {
		return property(block.defaultBlockState(), key, value);
	}

	static ClampedNormalFloat normalf(float mean, float deviation, float min, float max) {
		return ClampedNormalFloat.of(mean, deviation, min, max);
	}

	static ClampedNormalInt normal(float mean, float deviation, int min, int max) {
		return ClampedNormalInt.of(mean, deviation, min, max);
	}

	static Vec3i vec(int x, int y, int z) {
		return new Vec3i(x, y, z);
	}

	static Vec3 vec(double x, double y, double z) {
		return new Vec3(x, y, z);
	}

	static <T extends Comparable<T>> InclusiveRange<T> inclusive(T first, T second) {
		return new InclusiveRange<>(first, second);
	}


}
