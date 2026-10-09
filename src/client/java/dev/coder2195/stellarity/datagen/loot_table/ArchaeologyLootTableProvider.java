package dev.coder2195.stellarity.datagen.loot_table;

import dev.coder2195.stellarity.Stellarity;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ArchaeologyLootTableProvider extends SimpleFabricLootTableSubProvider {
	private final CompletableFuture<HolderLookup.Provider> registryLookup;

	public ArchaeologyLootTableProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup, LootContextParamSets.ARCHAEOLOGY);
		this.registryLookup = registryLookup;
	}

	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> biConsumer) {
		var lookup = registryLookup.join();
	}

	@Override
	public void run() {
		Stellarity.LOGGER.info("What do i do here fabric team?");
	}
}
