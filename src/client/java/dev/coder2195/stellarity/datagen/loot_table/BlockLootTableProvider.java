package dev.coder2195.stellarity.datagen.loot_table;

import dev.coder2195.stellarity.registry.StellarityBlocks;
import dev.coder2195.stellarity.registry.StellarityItems;
import dev.coder2195.stellarity.util.tuple.Tuple2;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static dev.coder2195.stellarity.registry.StellarityBlocks.*;
import static dev.coder2195.stellarity.registry.StellarityItems.ENDERITE_SHARD;
import static dev.coder2195.stellarity.util.LootUtil.*;

public class BlockLootTableProvider extends FabricBlockLootSubProvider {

	public BlockLootTableProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, registryLookup);
	}

	private static final Block[] DROP_SELF = {
		ASHEN_FROGLIGHT,
		ENDER_DIRT,
		ROOTED_ENDER_DIRT,
		ENDERITE_BLOCK,

		AMETHYII_PLANKS, AMETHYII_SAPLING, AMETHYII_LOG, STRIPPED_AMETHYII_LOG, STRIPPED_AMETHYII_WOOD, AMETHYII_WOOD, AMETHYII_LEAVES, AMETHYII_SHELF, AMETHYII_FENCE, AMETHYII_STAIRS, AMETHYII_BUTTON, AMETHYII_PRESSURE_PLATE, AMETHYII_TRAPDOOR, AMETHYII_FENCE_GATE, AMETHYII_SIGN, AMETHYII_HANGING_SIGN,
		HALLOWED_PLANKS, HALLOWED_SAPLING, HALLOWED_LOG, STRIPPED_HALLOWED_LOG, STRIPPED_HALLOWED_WOOD, HALLOWED_WOOD, HALLOWED_LEAVES, HALLOWED_SHELF, HALLOWED_FENCE, HALLOWED_STAIRS, HALLOWED_BUTTON, HALLOWED_PRESSURE_PLATE, HALLOWED_TRAPDOOR, HALLOWED_FENCE_GATE, HALLOWED_SIGN, HALLOWED_HANGING_SIGN,
		SHRUBBED_PLANKS, SHRUBBED_SAPLING, SHRUBBED_LOG, STRIPPED_SHRUBBED_LOG, STRIPPED_SHRUBBED_WOOD, SHRUBBED_WOOD, SHRUBBED_LEAVES, SHRUBBED_SHELF, SHRUBBED_FENCE, SHRUBBED_STAIRS, SHRUBBED_BUTTON, SHRUBBED_PRESSURE_PLATE, SHRUBBED_TRAPDOOR, SHRUBBED_FENCE_GATE, SHRUBBED_SIGN, SHRUBBED_HANGING_SIGN,
		PRISMATIC_PLANKS, PRISMATIC_SAPLING, PRISMATIC_LOG, STRIPPED_PRISMATIC_LOG, STRIPPED_PRISMATIC_WOOD, PRISMATIC_WOOD, PRISMATIC_LEAVES, PRISMATIC_SHELF, PRISMATIC_FENCE, PRISMATIC_STAIRS, PRISMATIC_BUTTON, PRISMATIC_PRESSURE_PLATE, PRISMATIC_TRAPDOOR, PRISMATIC_FENCE_GATE, PRISMATIC_SIGN, PRISMATIC_HANGING_SIGN,
		ASHEN_PLANKS, ASHEN_SAPLING, ASHEN_LOG, STRIPPED_ASHEN_LOG, STRIPPED_ASHEN_WOOD, ASHEN_WOOD, ASHEN_LEAVES, ASHEN_SHELF, ASHEN_FENCE, ASHEN_STAIRS, ASHEN_BUTTON, ASHEN_PRESSURE_PLATE, ASHEN_TRAPDOOR, ASHEN_FENCE_GATE, ASHEN_SIGN, ASHEN_HANGING_SIGN,
		INFERNO_PLANKS, INFERNO_SAPLING, INFERNO_LOG, STRIPPED_INFERNO_LOG, STRIPPED_INFERNO_WOOD, INFERNO_WOOD, INFERNO_LEAVES, INFERNO_SHELF, INFERNO_FENCE, INFERNO_STAIRS, INFERNO_BUTTON, INFERNO_PRESSURE_PLATE, INFERNO_TRAPDOOR, INFERNO_FENCE_GATE, INFERNO_SIGN, INFERNO_HANGING_SIGN

	};

	private static final Block[] DROP_POTTED = {
		POTTED_AMETHYII_SAPLING,
		POTTED_ASHEN_SAPLING,
		POTTED_HALLOWED_SAPLING,
		POTTED_SHRUBBED_SAPLING,
		POTTED_PRISMATIC_SAPLING,
		POTTED_INFERNO_SAPLING,
	};

	private static final Block[] DROP_SLAB = {
		ASHEN_SLAB,
		AMETHYII_SLAB,
		HALLOWED_SLAB,
		SHRUBBED_SLAB,
		PRISMATIC_SLAB,
		INFERNO_SLAB,
	};

	private static final Block[] DROP_DOORS = {
		ASHEN_DOOR,
		AMETHYII_DOOR,
		HALLOWED_DOOR,
		SHRUBBED_DOOR,
		PRISMATIC_DOOR,
		INFERNO_DOOR,
	};

	private static final List<Tuple2<Block, Block>> LEAVES = List.of(
		new Tuple2<>(StellarityBlocks.AMETHYII_LEAVES, StellarityBlocks.AMETHYII_SAPLING),
		new Tuple2<>(StellarityBlocks.HALLOWED_LEAVES, StellarityBlocks.HALLOWED_SAPLING),
		new Tuple2<>(StellarityBlocks.SHRUBBED_LEAVES, StellarityBlocks.SHRUBBED_SAPLING),
		new Tuple2<>(StellarityBlocks.PRISMATIC_LEAVES, StellarityBlocks.PRISMATIC_SAPLING),
		new Tuple2<>(StellarityBlocks.ASHEN_LEAVES, StellarityBlocks.ASHEN_SAPLING),
		new Tuple2<>(StellarityBlocks.INFERNO_LEAVES, StellarityBlocks.INFERNO_SAPLING)
	);

	@Override
	public void generate() {
		add(ENDERITE_ORE, createOreDrop(ENDERITE_ORE, ENDERITE_SHARD));

		for (Block block : DROP_SELF) dropSelf(block);
		for (Block block : DROP_DOORS) add(block, this::createDoorTable);
		for (Block block : DROP_POTTED) dropPottedContents(block);
		for (Block block : DROP_SLAB) add(block, this::createSlabItemTable);
		for (Tuple2<Block, Block> leaves : LEAVES) add(leaves._1(), (block) -> createLeavesDrops(block, leaves._2(), NORMAL_LEAVES_SAPLING_CHANCES));


		dropOther(ENDER_DIRT_PATH, ENDER_DIRT);
		dropOther(DRAGON_BREATH_CAULDRON, Blocks.CAULDRON);
		dropOther(DUSKBERRY_BUSH, StellarityItems.DUSKBERRY);

		add(ENDER_GRASS_BLOCK, lootTable().withPool(pool().add(
			AlternativesEntry.alternatives(
				item(ENDER_GRASS_BLOCK).when(hasSilkTouch()),
				applyExplosionCondition(ENDER_DIRT, item(ENDER_DIRT))
			)
		)));

		add(ALTAR_OF_THE_ACCURSED, lootTable()
			.withPool(pool().add(item(Items.CRYING_OBSIDIAN)))
			.withPool(pool().add(item(StellarityItems.SATCHEL_OF_VOIDS)))
		);
	}
}
