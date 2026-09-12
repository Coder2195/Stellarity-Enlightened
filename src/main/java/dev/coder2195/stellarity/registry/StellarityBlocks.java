package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.block.*;
import net.fabricmc.fabric.api.item.v1.BlockTransformerHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.component.BlockTransformer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

import java.util.function.Function;

public interface StellarityBlocks {
	// IMPORTANT: all solid blocks must be registered in block motion in datagen or risk having hard to locate bugs
	Block ENDER_DIRT = register(StellarityBlockItemIds.ENDER_DIRT, BlockBehaviour.Properties.of()
		.mapColor(MapColor.DIRT)
		.strength(0.5F)
		.sound(SoundType.ROOTED_DIRT));
	Block ENDER_GRASS_BLOCK = register(StellarityBlockItemIds.ENDER_GRASS_BLOCK, EnderGrassBlock::new, EnderGrassBlock.PROPERTIES);
	Block ASHEN_FROGLIGHT = register(StellarityBlockItemIds.ASHEN_FROGLIGHT, RotatedPillarBlock::new, BlockBehaviour.Properties.of()
		.mapColor(MapColor.SAND)
		.strength(0.3F)
		.lightLevel((_) -> 15)
		.sound(SoundType.FROGLIGHT));
	Block ROOTED_ENDER_DIRT = register(StellarityBlockItemIds.ROOTED_ENDER_DIRT, RootedDirtBlock::new, BlockBehaviour.Properties.of()
		.mapColor(MapColor.DIRT)
		.strength(0.5F)
		.sound(SoundType.ROOTED_DIRT));
	Block ENDER_DIRT_PATH = register(StellarityBlockItemIds.ENDER_DIRT_PATH, EnderDirtPath::new, EnderDirtPath.PROPERTIES);
	Block ALTAR_OF_THE_ACCURSED = register(StellarityBlockItemIds.ALTAR_OF_THE_ACCURSED, AltarOfTheAccursed::new, AltarOfTheAccursed.PROPERTIES);
	Block DUSKBERRY_BUSH = register(StellarityBlockItemIds.DUSKBERRY_BUSH, DuskberryBush::new, DuskberryBush.PROPERTIES);
	Block ENDERITE_BLOCK = register(StellarityBlockItemIds.ENDERITE_BLOCK, BlockBehaviour.Properties.of()
		.mapColor(MapColor.COLOR_PURPLE)
		.instrument(NoteBlockInstrument.BIT)
		.requiresCorrectToolForDrops()
		.strength(5.0F, 6.0F)
		.sound(SoundType.METAL));
	Block COARSE_ENDER_DIRT = register(StellarityBlockItemIds.COARSE_ENDER_DIRT, BlockBehaviour.Properties.of()
		.mapColor(MapColor.DIRT)
		.strength(0.5F)
		.sound(SoundType.GRAVEL));
	Block COLORED_LEAVES = register(StellarityBlockItemIds.COLORED_LEAVES, ColoredLeavesBlock::new, Blocks.leavesProperties(SoundType.GRASS));


	static Block register(BlockItemId key, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings) {
		var blockKey = key.block();
		settings = settings.setId(blockKey);

		Block block = blockFactory.apply(settings);
		Registry.register(BuiltInRegistries.BLOCK, blockKey, block);

		return block;
	}

	static Block register(BlockItemId key, BlockBehaviour.Properties settings) {
		return register(key, Block::new, settings);
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Blocks");
		BlockTransformerHelper.registerHoe(BlockTransformer.BlockTransformData.builder(BlockPredicate.matchesBlocks(ROOTED_ENDER_DIRT), Blocks.ROOTED_DIRT).sound(SoundEvents.HOE_TILL).loot(BuiltInLootTables.TILL_ROOTED_DIRT).dropStrategy(BlockTransformer.DropStrategy.CLICKED_FACE).build());
		BlockTransformerHelper.registerTilling(COARSE_ENDER_DIRT, ENDER_DIRT);
		BlockTransformerHelper.registerTilling(new Block[]{ENDER_DIRT, ENDER_GRASS_BLOCK, COARSE_ENDER_DIRT}, ENDER_DIRT_PATH);
	}
}
