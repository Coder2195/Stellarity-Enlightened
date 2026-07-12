package dev.coder2195.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import dev.coder2195.stellarity.Stellarity;

public interface StellarityConfiguredCarvers {
	ResourceKey<ConfiguredWorldCarver<?>> RAVINE = id("ravine");
	ResourceKey<ConfiguredWorldCarver<?>> CAVE = id("cave");
	ResourceKey<ConfiguredWorldCarver<?>> CRACK = id("crack");

	static ResourceKey<ConfiguredWorldCarver<?>> id(String id) {
		return Stellarity.key(Registries.CONFIGURED_CARVER, id);
	}
}
