package dev.coder2195.stellarity.registry.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import dev.coder2195.stellarity.registry.feature.configuration.FeatureSequenceConfiguration;

public class FeatureSequenceFeature extends Feature<FeatureSequenceConfiguration> {
	public FeatureSequenceFeature(Codec<FeatureSequenceConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<FeatureSequenceConfiguration> context) {
		var features = context.config().features();
		var level = context.level();
		var random = context.random();
		var generator = context.chunkGenerator();
		var origin = context.origin();
		features.forEach(feature -> feature.value().place(level, generator, random, origin));
		return false;
	}
}
