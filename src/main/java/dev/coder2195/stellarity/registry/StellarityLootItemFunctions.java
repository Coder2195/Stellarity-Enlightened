package dev.coder2195.stellarity.registry;

import com.mojang.serialization.MapCodec;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.loot_function.FunctionReference;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;

public interface StellarityLootItemFunctions {
	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Loot Item Functions");

		register("reference", FunctionReference.MAP_CODEC);
	}

	private static void register(String id, MapCodec<? extends LootItemFunction> codec) {
		Registry.register(BuiltInRegistries.LOOT_FUNCTION_TYPE, Stellarity.id(id), codec);
	}
}
