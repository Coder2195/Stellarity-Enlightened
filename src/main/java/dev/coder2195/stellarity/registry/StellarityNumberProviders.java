package dev.coder2195.stellarity.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.number_provider.Multiply;
import dev.coder2195.stellarity.registry.number_provider.NbtNumberValue;

public interface StellarityNumberProviders {
	static void init() {
		Registry.register(BuiltInRegistries.LOOT_NUMBER_PROVIDER_TYPE, Stellarity.id("nbt_number"), NbtNumberValue.CODEC);
		Registry.register(BuiltInRegistries.LOOT_NUMBER_PROVIDER_TYPE, Stellarity.id("multiply"), Multiply.CODEC);
	}

}
