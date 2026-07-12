package dev.coder2195.stellarity.registry.feature.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public record FeatureSequenceConfiguration(HolderSet<PlacedFeature> features) implements FeatureConfiguration {
	public static final Codec<FeatureSequenceConfiguration> CODEC = RecordCodecBuilder.create(i -> i.group(
		PlacedFeature.LIST_CODEC.fieldOf("features").forGetter(FeatureSequenceConfiguration::features)
	).apply(i, FeatureSequenceConfiguration::new));
}
