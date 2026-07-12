package dev.coder2195.stellarity.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.material_condition.LegacyBiomeConditionSource;

public interface StellarityMaterialConditions {
	static void init() {
		Registry.register(BuiltInRegistries.MATERIAL_CONDITION, Stellarity.id("legacy_biome"), LegacyBiomeConditionSource.CODEC);
	}
}
