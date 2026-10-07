package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.loot_pool_entry.ItemStackEntry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public interface StellarityLootPoolEntries {
	static void init() {
		Registry.register(BuiltInRegistries.LOOT_POOL_ENTRY_TYPE, Stellarity.id("item_stack"), ItemStackEntry.CODEC);
	}
}
