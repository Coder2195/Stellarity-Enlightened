package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.registry.feature.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.feature.*;
import dev.coder2195.stellarity.registry.feature.configuration.DungeonFeatureConfiguration;
import dev.coder2195.stellarity.registry.feature.configuration.FeatureSequenceConfiguration;
import dev.coder2195.stellarity.registry.feature.configuration.SpikeFeatureConfiguration;

public interface StellarityFeatures {
	DungeonFeature DUNGEON = register("dungeon", new DungeonFeature(DungeonFeatureConfiguration.CODEC));
	FreezeWaterFeature FREEZE_WATER = register("freeze_water", new FreezeWaterFeature());
	SpikeFeature SPIKE = register("spike", new SpikeFeature(SpikeFeatureConfiguration.CODEC));
	DragonEggFeature DRAGON_EGG = register("dragon_egg", new DragonEggFeature(SimpleBlockConfiguration.CODEC));
	FeatureSequenceFeature FEATURE_SEQUENCE = register("feature_sequence", new FeatureSequenceFeature(FeatureSequenceConfiguration.CODEC));

	static <T extends Feature<?>> T register(String id, T feature) {
		return Registry.register(BuiltInRegistries.FEATURE, Stellarity.id(id), feature);
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Features");
	}
}
