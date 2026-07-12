package dev.coder2195.stellarity.datagen.loot_table;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import dev.coder2195.stellarity.registry.StellarityBlocks;
import dev.coder2195.stellarity.registry.StellarityDataComponents;
import dev.coder2195.stellarity.registry.StellarityItems;

import java.util.concurrent.CompletableFuture;

import static dev.coder2195.stellarity.util.LootUtil.*;

public class BlockLootTableProvider extends FabricBlockLootSubProvider {

	public BlockLootTableProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, registryLookup);
	}

	private static final Block[] DROP_SELF = {
		StellarityBlocks.ASHEN_FROGLIGHT,
		StellarityBlocks.ENDER_DIRT,
		StellarityBlocks.ROOTED_ENDER_DIRT,
		StellarityBlocks.ENDERITE_BLOCK
	};

	@Override
	public void generate() {
		for (Block block : DROP_SELF) {
			dropSelf(block);
		}

		dropOther(StellarityBlocks.ENDER_DIRT_PATH, StellarityBlocks.ENDER_DIRT);


		add(StellarityBlocks.ENDER_GRASS_BLOCK, lootTable().withPool(pool().add(
			AlternativesEntry.alternatives(
				item(StellarityBlocks.ENDER_GRASS_BLOCK).when(hasSilkTouch()),
				applyExplosionCondition(StellarityBlocks.ENDER_DIRT, item(StellarityBlocks.ENDER_DIRT))
			)
		)));

		add(StellarityBlocks.ALTAR_OF_THE_ACCURSED, lootTable()
			.withPool(pool().add(item(Items.CRYING_OBSIDIAN)))
			.withPool(pool().add(item(StellarityItems.SATCHEL_OF_VOIDS)))
		);

		add(StellarityBlocks.DUSKBERRY_BUSH, lootTable().withPool(pool().add(
			item(StellarityItems.DUSKBERRY).apply(count(num(1)))
		)));

		// todo: actual saplings
		add(StellarityBlocks.COLORED_LEAVES, createLeavesDrops(StellarityBlocks.COLORED_LEAVES, StellarityBlocks.COLORED_LEAVES, NORMAL_LEAVES_SAPLING_CHANCES)
			.apply(copyBlockEntity().include(StellarityDataComponents.COLOR).build()));
	}
}
