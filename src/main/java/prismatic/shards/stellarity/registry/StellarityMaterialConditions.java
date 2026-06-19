package prismatic.shards.stellarity.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.material_condition.LegacyBiomeConditionSource;

public interface StellarityMaterialConditions {
	static void init() {
		Registry.register(BuiltInRegistries.MATERIAL_CONDITION, Stellarity.id("legacy_biome"), LegacyBiomeConditionSource.CODEC);
	}
}
