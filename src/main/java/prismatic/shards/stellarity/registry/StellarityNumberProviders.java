package prismatic.shards.stellarity.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.number_provider.Multiply;
import prismatic.shards.stellarity.registry.number_provider.NbtNumberValue;

public interface StellarityNumberProviders {
	static void init() {
		Registry.register(BuiltInRegistries.LOOT_NUMBER_PROVIDER_TYPE, Stellarity.id("nbt_number"), NbtNumberValue.CODEC);
		Registry.register(BuiltInRegistries.LOOT_NUMBER_PROVIDER_TYPE, Stellarity.id("multiply"), Multiply.CODEC);
	}

}
