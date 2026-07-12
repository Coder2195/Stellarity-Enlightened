package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.registry.block.*;
import net.fabricmc.fabric.api.registry.FlattenableBlockRegistry;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import dev.coder2195.stellarity.Stellarity;

import java.util.List;
import java.util.function.Function;

public interface StellarityBlocks {
	Block ENDER_DIRT = register(StellarityBlockItemIds.ENDER_DIRT, BlockBehaviour.Properties.of()
		.mapColor(MapColor.DIRT)
		.strength(0.5F)
		.sound(SoundType.ROOTED_DIRT));
	Block ENDER_GRASS_BLOCK = register(StellarityBlockItemIds.ENDER_GRASS_BLOCK, EnderGrassBlock::new, EnderGrassBlock.PROPERTIES);
	Block ASHEN_FROGLIGHT = register(StellarityBlockItemIds.ASHEN_FROGLIGHT, RotatedPillarBlock::new, BlockBehaviour.Properties.of()
		.mapColor(MapColor.SAND)
		.strength(0.3F)
		.lightLevel((state) -> 15)
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
		TillableBlockRegistry.register(ROOTED_ENDER_DIRT, (unused) -> true, HoeItem.changeIntoStateAndDropItem(StellarityBlocks.ENDER_DIRT.defaultBlockState(), Items.HANGING_ROOTS));
		TillableBlockRegistry.register(COARSE_ENDER_DIRT, (unused) -> true, HoeItem.changeIntoState(StellarityBlocks.ENDER_DIRT.defaultBlockState()));
		for (var dirt : List.of(ENDER_DIRT, ENDER_GRASS_BLOCK, COARSE_ENDER_DIRT))
			FlattenableBlockRegistry.register(dirt, ENDER_DIRT_PATH.defaultBlockState());

	}
}
