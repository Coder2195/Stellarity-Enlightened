package dev.coder2195.stellarity.feature;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderSet;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.jspecify.annotations.NonNull;

public record FeatureSequenceFeature(HolderSet<PlacedFeature> features) implements Feature {
	public static final MapCodec<FeatureSequenceFeature> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
		PlacedFeature.LIST_CODEC.fieldOf("features").forGetter(FeatureSequenceFeature::features)
	).apply(i, FeatureSequenceFeature::new));

	@Override
	public @NonNull MapCodec<? extends Feature> codec() {
		return CODEC;
	}

	@Override
	public boolean place(@NonNull WorldGenLevel level, @NonNull ChunkGenerator generator, @NonNull RandomSource random, @NonNull BlockPos origin) {
		features.forEach(feature -> feature.value().place(level, generator, random, origin));
		return false;
	}
}
