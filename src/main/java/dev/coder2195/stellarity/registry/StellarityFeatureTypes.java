package dev.coder2195.stellarity.registry;

import com.mojang.serialization.MapCodec;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.feature.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;

public interface StellarityFeatureTypes {
	static void register(String id, MapCodec<? extends Feature> feature) {
		Registry.register(BuiltInRegistries.FEATURE_TYPE, Stellarity.id(id), feature);
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Features");

		register("dragon_egg", DragonEggFeature.CODEC);
		register("dungeon", DungeonFeature.CODEC);
		register("feature_sequence", FeatureSequenceFeature.CODEC);
		register("freeze_water", FreezeWaterFeature.CODEC);
		register("spike", SpikeFeature.CODEC);

	}
}
