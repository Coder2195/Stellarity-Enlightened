package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.block.*;
import dev.coder2195.stellarity.mixin.accessor.BlocksAccessor;
import dev.coder2195.stellarity.util.tuple.Tuple2;
import net.fabricmc.fabric.api.item.v1.BlockTransformerHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.component.BlockTransformer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

import java.util.List;
import java.util.function.Function;

import static net.minecraft.world.level.block.Blocks.flowerPotProperties;
import static net.minecraft.world.level.block.Blocks.leavesProperties;

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
	Block ENDERITE_ORE = register(StellarityBlockItemIds.ENDERITE_ORE, BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.5F, 9.0F));
	Block COARSE_ENDER_DIRT = register(StellarityBlockItemIds.COARSE_ENDER_DIRT, BlockBehaviour.Properties.of()
		.mapColor(MapColor.DIRT)
		.strength(0.5F)
		.sound(SoundType.GRAVEL));
	Block DRAGON_BREATH_CAULDRON = register(StellarityBlockItemIds.DRAGON_BREATH_CAULDRON, DragonBreathCauldron::new, DragonBreathCauldron.PROPERTIES);

	Block AMETHYII_PLANKS = register(StellarityBlockItemIds.AMETHYII_PLANKS, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava());
	Block AMETHYII_SAPLING = register(StellarityBlockItemIds.AMETHYII_SAPLING, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.POPPED));
	Block AMETHYII_LOG = register(StellarityBlockItemIds.AMETHYII_LOG, RotatedPillarBlock::new, Blocks.logProperties(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
	Block STRIPPED_AMETHYII_LOG = register(StellarityBlockItemIds.STRIPPED_AMETHYII_LOG, RotatedPillarBlock::new, Blocks.logProperties(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
	Block STRIPPED_AMETHYII_WOOD = register(StellarityBlockItemIds.STRIPPED_AMETHYII_WOOD, RotatedPillarBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
	Block AMETHYII_WOOD = register(StellarityBlockItemIds.AMETHYII_WOOD, RotatedPillarBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
	Block AMETHYII_LEAVES = register(StellarityBlockItemIds.AMETHYII_LEAVES, p -> new TintedParticleLeavesBlock(0.01F, p), leavesProperties(SoundType.GRASS));
	Block AMETHYII_SLAB = BlocksAccessor.registerSlab(StellarityBlockItemIds.AMETHYII_SLAB, AMETHYII_PLANKS);
	Block AMETHYII_SHELF = register(StellarityBlockItemIds.AMETHYII_SHELF, ShelfBlock::new,
		BlockBehaviour.Properties.of()
			.mapColor(AMETHYII_PLANKS.defaultMapColor())
			.instrument(NoteBlockInstrument.BASS)
			.sound(SoundType.SHELF)
			.ignitedByLava()
			.strength(2.0F, 3.0F));
	Block AMETHYII_FENCE = register(StellarityBlockItemIds.AMETHYII_FENCE, FenceBlock::new, BlockBehaviour.Properties.of()
		.mapColor(AMETHYII_PLANKS.defaultMapColor())
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.strength(2.0F, 3.0F)
		.sound(SoundType.WOOD)
		.ignitedByLava());
	Block AMETHYII_STAIRS = BlocksAccessor.registerStair(StellarityBlockItemIds.AMETHYII_STAIRS, AMETHYII_PLANKS);
	Block AMETHYII_BUTTON = register(StellarityBlockItemIds.AMETHYII_BUTTON, p -> new ButtonBlock(StellarityBlockSetTypes.AMETHYII, 30, p), Blocks.buttonProperties());
	Block AMETHYII_PRESSURE_PLATE = register(StellarityBlockItemIds.AMETHYII_PRESSURE_PLATE, p -> new PressurePlateBlock(StellarityBlockSetTypes.AMETHYII, p), BlockBehaviour.Properties.of()
		.mapColor(AMETHYII_PLANKS.defaultMapColor())
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.noCollision()
		.strength(0.5F)
		.ignitedByLava()
		.pushReaction(PushReaction.POPPED));
	Block AMETHYII_DOOR = register(StellarityBlockItemIds.AMETHYII_DOOR, p -> new DoorBlock(StellarityBlockSetTypes.AMETHYII, p),
		BlockBehaviour.Properties.of()
			.mapColor(AMETHYII_PLANKS.defaultMapColor())
			.instrument(NoteBlockInstrument.BASS)
			.strength(3.0F)
			.noOcclusion()
			.ignitedByLava()
			.pushReaction(PushReaction.POPPED));
	Block AMETHYII_TRAPDOOR = register(StellarityBlockItemIds.AMETHYII_TRAPDOOR, p -> new TrapDoorBlock(StellarityBlockSetTypes.AMETHYII, p), BlockBehaviour.Properties.of()
		.mapColor(AMETHYII_PLANKS.defaultMapColor())
		.instrument(NoteBlockInstrument.BASS)
		.strength(3.0F)
		.noOcclusion()
		.isValidSpawn(Blocks::never)
		.ignitedByLava());
	Block AMETHYII_FENCE_GATE = register(StellarityBlockItemIds.AMETHYII_FENCE_GATE, p -> new FenceGateBlock(StellarityWoodTypes.AMETHYII, p), BlockBehaviour.Properties.of()
		.mapColor(AMETHYII_PLANKS.defaultMapColor())
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.strength(2.0F, 3.0F)
		.ignitedByLava());
	Block AMETHYII_SIGN = register(StellarityBlockItemIds.AMETHYII_SIGN, p -> new StandingSignBlock(StellarityWoodTypes.AMETHYII, p), BlockBehaviour.Properties.of()
		.mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava()
	);
	Block AMETHYII_HANGING_SIGN = register(StellarityBlockItemIds.AMETHYII_HANGING_SIGN, p -> new CeilingHangingSignBlock(StellarityWoodTypes.AMETHYII, p), BlockBehaviour.Properties.of()
		.mapColor(MapColor.WOOD)
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.noCollision()
		.strength(1.0F)
		.ignitedByLava()
	);
	Block AMETHYII_WALL_SIGN = register(StellarityBlockIds.AMETHYII_WALL_SIGN, p -> new WallSignBlock(StellarityWoodTypes.AMETHYII, p),
		BlocksAccessor.wallVariant(AMETHYII_SIGN, true).mapColor(AMETHYII_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).forceSolidOn().noCollision().strength(1.0F).ignitedByLava()
	);
	Block AMETHYII_WALL_HANGING_SIGN = register(StellarityBlockIds.AMETHYII_WALL_HANGING_SIGN, p -> new WallHangingSignBlock(StellarityWoodTypes.AMETHYII, p),
		BlocksAccessor.wallVariant(AMETHYII_HANGING_SIGN, true).mapColor(AMETHYII_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).forceSolidOn().noCollision().strength(1.0F).ignitedByLava()
	);
	Block POTTED_AMETHYII_SAPLING = register(StellarityBlockIds.POTTED_AMETHYII_SAPLING, p -> new FlowerPotBlock(AMETHYII_SAPLING, p), flowerPotProperties());

	Block HALLOWED_PLANKS = register(StellarityBlockItemIds.HALLOWED_PLANKS, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava());
	Block HALLOWED_SAPLING = register(StellarityBlockItemIds.HALLOWED_SAPLING, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.POPPED));
	Block HALLOWED_LOG = register(StellarityBlockItemIds.HALLOWED_LOG, RotatedPillarBlock::new, Blocks.logProperties(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
	Block STRIPPED_HALLOWED_LOG = register(StellarityBlockItemIds.STRIPPED_HALLOWED_LOG, RotatedPillarBlock::new, Blocks.logProperties(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
	Block STRIPPED_HALLOWED_WOOD = register(StellarityBlockItemIds.STRIPPED_HALLOWED_WOOD, RotatedPillarBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
	Block HALLOWED_WOOD = register(StellarityBlockItemIds.HALLOWED_WOOD, RotatedPillarBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
	Block HALLOWED_LEAVES = register(StellarityBlockItemIds.HALLOWED_LEAVES, p -> new TintedParticleLeavesBlock(0.01F, p), leavesProperties(SoundType.GRASS));
	ColorCollection<Block> COLORED_HALLOWED_LEAVES = StellarityBlockItemIds.COLORED_HALLOWED_LEAVES.map(id -> register(id, p -> new TintedParticleLeavesBlock(0.01F, p), leavesProperties(SoundType.GRASS)));
	Block HALLOWED_SLAB = BlocksAccessor.registerSlab(StellarityBlockItemIds.HALLOWED_SLAB, HALLOWED_PLANKS);
	Block HALLOWED_SHELF = register(StellarityBlockItemIds.HALLOWED_SHELF, ShelfBlock::new,
		BlockBehaviour.Properties.of()
			.mapColor(HALLOWED_PLANKS.defaultMapColor())
			.instrument(NoteBlockInstrument.BASS)
			.sound(SoundType.SHELF)
			.ignitedByLava()
			.strength(2.0F, 3.0F));
	Block HALLOWED_FENCE = register(StellarityBlockItemIds.HALLOWED_FENCE, FenceBlock::new, BlockBehaviour.Properties.of()
		.mapColor(HALLOWED_PLANKS.defaultMapColor())
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.strength(2.0F, 3.0F)
		.sound(SoundType.WOOD)
		.ignitedByLava());
	Block HALLOWED_STAIRS = BlocksAccessor.registerStair(StellarityBlockItemIds.HALLOWED_STAIRS, HALLOWED_PLANKS);
	Block HALLOWED_BUTTON = register(StellarityBlockItemIds.HALLOWED_BUTTON, p -> new ButtonBlock(StellarityBlockSetTypes.HALLOWED, 30, p), Blocks.buttonProperties());
	Block HALLOWED_PRESSURE_PLATE = register(StellarityBlockItemIds.HALLOWED_PRESSURE_PLATE, p -> new PressurePlateBlock(StellarityBlockSetTypes.HALLOWED, p), BlockBehaviour.Properties.of()
		.mapColor(HALLOWED_PLANKS.defaultMapColor())
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.noCollision()
		.strength(0.5F)
		.ignitedByLava()
		.pushReaction(PushReaction.POPPED));
	Block HALLOWED_DOOR = register(StellarityBlockItemIds.HALLOWED_DOOR, p -> new DoorBlock(StellarityBlockSetTypes.HALLOWED, p), BlockBehaviour.Properties.of()
		.mapColor(HALLOWED_PLANKS.defaultMapColor())
		.instrument(NoteBlockInstrument.BASS)
		.strength(3.0F)
		.noOcclusion()
		.ignitedByLava()
		.pushReaction(PushReaction.POPPED));
	Block HALLOWED_TRAPDOOR = register(StellarityBlockItemIds.HALLOWED_TRAPDOOR, p -> new TrapDoorBlock(StellarityBlockSetTypes.HALLOWED, p), BlockBehaviour.Properties.of()
		.mapColor(HALLOWED_PLANKS.defaultMapColor())
		.instrument(NoteBlockInstrument.BASS)
		.strength(3.0F)
		.noOcclusion()
		.isValidSpawn(Blocks::never)
		.ignitedByLava());
	Block HALLOWED_FENCE_GATE = register(StellarityBlockItemIds.HALLOWED_FENCE_GATE, p -> new FenceGateBlock(StellarityWoodTypes.HALLOWED, p), BlockBehaviour.Properties.of()
		.mapColor(HALLOWED_PLANKS.defaultMapColor())
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.strength(2.0F, 3.0F)
		.ignitedByLava());
	Block HALLOWED_SIGN = register(StellarityBlockItemIds.HALLOWED_SIGN, p -> new StandingSignBlock(StellarityWoodTypes.HALLOWED, p), BlockBehaviour.Properties.of()
		.mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava()
	);
	Block HALLOWED_HANGING_SIGN = register(StellarityBlockItemIds.HALLOWED_HANGING_SIGN, p -> new CeilingHangingSignBlock(StellarityWoodTypes.HALLOWED, p), BlockBehaviour.Properties.of()
		.mapColor(MapColor.WOOD)
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.noCollision()
		.strength(1.0F)
		.ignitedByLava()
	);
	Block HALLOWED_WALL_SIGN = register(StellarityBlockIds.HALLOWED_WALL_SIGN, p -> new WallSignBlock(StellarityWoodTypes.HALLOWED, p),
		BlocksAccessor.wallVariant(HALLOWED_SIGN, true).mapColor(HALLOWED_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).forceSolidOn().noCollision().strength(1.0F).ignitedByLava()
	);
	Block HALLOWED_WALL_HANGING_SIGN = register(StellarityBlockIds.HALLOWED_WALL_HANGING_SIGN, p -> new WallHangingSignBlock(StellarityWoodTypes.HALLOWED, p),
		BlocksAccessor.wallVariant(HALLOWED_HANGING_SIGN, true).mapColor(HALLOWED_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).forceSolidOn().noCollision().strength(1.0F).ignitedByLava()
	);
	Block POTTED_HALLOWED_SAPLING = register(StellarityBlockIds.POTTED_HALLOWED_SAPLING, p -> new FlowerPotBlock(HALLOWED_SAPLING, p), flowerPotProperties());

	Block SHRUBBED_PLANKS = register(StellarityBlockItemIds.SHRUBBED_PLANKS, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava());
	Block SHRUBBED_SAPLING = register(StellarityBlockItemIds.SHRUBBED_SAPLING, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.POPPED));
	Block SHRUBBED_LOG = register(StellarityBlockItemIds.SHRUBBED_LOG, RotatedPillarBlock::new, Blocks.logProperties(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
	Block STRIPPED_SHRUBBED_LOG = register(StellarityBlockItemIds.STRIPPED_SHRUBBED_LOG, RotatedPillarBlock::new, Blocks.logProperties(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
	Block STRIPPED_SHRUBBED_WOOD = register(StellarityBlockItemIds.STRIPPED_SHRUBBED_WOOD, RotatedPillarBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
	Block SHRUBBED_WOOD = register(StellarityBlockItemIds.SHRUBBED_WOOD, RotatedPillarBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
	Block SHRUBBED_LEAVES = register(StellarityBlockItemIds.SHRUBBED_LEAVES, p -> new TintedParticleLeavesBlock(0.01F, p), leavesProperties(SoundType.GRASS));
	Block SHRUBBED_SLAB = BlocksAccessor.registerSlab(StellarityBlockItemIds.SHRUBBED_SLAB, SHRUBBED_PLANKS);
	Block SHRUBBED_SHELF = register(StellarityBlockItemIds.SHRUBBED_SHELF, ShelfBlock::new,
		BlockBehaviour.Properties.of()
			.mapColor(SHRUBBED_PLANKS.defaultMapColor())
			.instrument(NoteBlockInstrument.BASS)
			.sound(SoundType.SHELF)
			.ignitedByLava()
			.strength(2.0F, 3.0F));
	Block SHRUBBED_FENCE = register(StellarityBlockItemIds.SHRUBBED_FENCE, FenceBlock::new, BlockBehaviour.Properties.of()
		.mapColor(SHRUBBED_PLANKS.defaultMapColor())
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.strength(2.0F, 3.0F)
		.sound(SoundType.WOOD)
		.ignitedByLava());
	Block SHRUBBED_STAIRS = BlocksAccessor.registerStair(StellarityBlockItemIds.SHRUBBED_STAIRS, SHRUBBED_PLANKS);
	Block SHRUBBED_BUTTON = register(StellarityBlockItemIds.SHRUBBED_BUTTON, p -> new ButtonBlock(StellarityBlockSetTypes.SHRUBBED, 30, p), Blocks.buttonProperties());
	Block SHRUBBED_PRESSURE_PLATE = register(StellarityBlockItemIds.SHRUBBED_PRESSURE_PLATE, p -> new PressurePlateBlock(StellarityBlockSetTypes.SHRUBBED, p), BlockBehaviour.Properties.of()
		.mapColor(SHRUBBED_PLANKS.defaultMapColor())
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.noCollision()
		.strength(0.5F)
		.ignitedByLava()
		.pushReaction(PushReaction.POPPED));
	Block SHRUBBED_DOOR = register(StellarityBlockItemIds.SHRUBBED_DOOR, p -> new DoorBlock(StellarityBlockSetTypes.SHRUBBED, p), BlockBehaviour.Properties.of()
		.mapColor(SHRUBBED_PLANKS.defaultMapColor())
		.instrument(NoteBlockInstrument.BASS)
		.strength(3.0F)
		.noOcclusion()
		.ignitedByLava()
		.pushReaction(PushReaction.POPPED));
	Block SHRUBBED_TRAPDOOR = register(StellarityBlockItemIds.SHRUBBED_TRAPDOOR, p -> new TrapDoorBlock(StellarityBlockSetTypes.SHRUBBED, p), BlockBehaviour.Properties.of()
		.mapColor(SHRUBBED_PLANKS.defaultMapColor())
		.instrument(NoteBlockInstrument.BASS)
		.strength(3.0F)
		.noOcclusion()
		.isValidSpawn(Blocks::never)
		.ignitedByLava());
	Block SHRUBBED_FENCE_GATE = register(StellarityBlockItemIds.SHRUBBED_FENCE_GATE, p -> new FenceGateBlock(StellarityWoodTypes.SHRUBBED, p), BlockBehaviour.Properties.of()
		.mapColor(SHRUBBED_PLANKS.defaultMapColor())
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.strength(2.0F, 3.0F)
		.ignitedByLava());
	Block SHRUBBED_SIGN = register(StellarityBlockItemIds.SHRUBBED_SIGN, p -> new StandingSignBlock(StellarityWoodTypes.SHRUBBED, p), BlockBehaviour.Properties.of()
		.mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava()
	);
	Block SHRUBBED_HANGING_SIGN = register(StellarityBlockItemIds.SHRUBBED_HANGING_SIGN, p -> new CeilingHangingSignBlock(StellarityWoodTypes.SHRUBBED, p), BlockBehaviour.Properties.of()
		.mapColor(MapColor.WOOD)
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.noCollision()
		.strength(1.0F)
		.ignitedByLava()
	);
	Block SHRUBBED_WALL_SIGN = register(StellarityBlockIds.SHRUBBED_WALL_SIGN, p -> new WallSignBlock(StellarityWoodTypes.SHRUBBED, p),
		BlocksAccessor.wallVariant(SHRUBBED_SIGN, true).mapColor(SHRUBBED_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).forceSolidOn().noCollision().strength(1.0F).ignitedByLava()
	);
	Block SHRUBBED_WALL_HANGING_SIGN = register(StellarityBlockIds.SHRUBBED_WALL_HANGING_SIGN, p -> new WallHangingSignBlock(StellarityWoodTypes.SHRUBBED, p),
		BlocksAccessor.wallVariant(SHRUBBED_HANGING_SIGN, true).mapColor(SHRUBBED_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).forceSolidOn().noCollision().strength(1.0F).ignitedByLava()
	);
	Block POTTED_SHRUBBED_SAPLING = register(StellarityBlockIds.POTTED_SHRUBBED_SAPLING, p -> new FlowerPotBlock(SHRUBBED_SAPLING, p), flowerPotProperties());

	Block PRISMATIC_PLANKS = register(StellarityBlockItemIds.PRISMATIC_PLANKS, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava());
	Block PRISMATIC_SAPLING = register(StellarityBlockItemIds.PRISMATIC_SAPLING, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.POPPED));
	Block PRISMATIC_LOG = register(StellarityBlockItemIds.PRISMATIC_LOG, RotatedPillarBlock::new, Blocks.logProperties(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
	Block STRIPPED_PRISMATIC_LOG = register(StellarityBlockItemIds.STRIPPED_PRISMATIC_LOG, RotatedPillarBlock::new, Blocks.logProperties(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
	Block STRIPPED_PRISMATIC_WOOD = register(StellarityBlockItemIds.STRIPPED_PRISMATIC_WOOD, RotatedPillarBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
	Block PRISMATIC_WOOD = register(StellarityBlockItemIds.PRISMATIC_WOOD, RotatedPillarBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
	Block PRISMATIC_LEAVES = register(StellarityBlockItemIds.PRISMATIC_LEAVES, p -> new TintedParticleLeavesBlock(0.01F, p), leavesProperties(SoundType.GRASS));
	Block PRISMATIC_SLAB = BlocksAccessor.registerSlab(StellarityBlockItemIds.PRISMATIC_SLAB, PRISMATIC_PLANKS);
	Block PRISMATIC_SHELF = register(StellarityBlockItemIds.PRISMATIC_SHELF, ShelfBlock::new,
		BlockBehaviour.Properties.of()
			.mapColor(PRISMATIC_PLANKS.defaultMapColor())
			.instrument(NoteBlockInstrument.BASS)
			.sound(SoundType.SHELF)
			.ignitedByLava()
			.strength(2.0F, 3.0F));
	Block PRISMATIC_FENCE = register(StellarityBlockItemIds.PRISMATIC_FENCE, FenceBlock::new, BlockBehaviour.Properties.of()
		.mapColor(PRISMATIC_PLANKS.defaultMapColor())
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.strength(2.0F, 3.0F)
		.sound(SoundType.WOOD)
		.ignitedByLava());
	Block PRISMATIC_STAIRS = BlocksAccessor.registerStair(StellarityBlockItemIds.PRISMATIC_STAIRS, PRISMATIC_PLANKS);
	Block PRISMATIC_BUTTON = register(StellarityBlockItemIds.PRISMATIC_BUTTON, p -> new ButtonBlock(StellarityBlockSetTypes.PRISMATIC, 30, p), Blocks.buttonProperties());
	Block PRISMATIC_PRESSURE_PLATE = register(StellarityBlockItemIds.PRISMATIC_PRESSURE_PLATE, p -> new PressurePlateBlock(StellarityBlockSetTypes.PRISMATIC, p), BlockBehaviour.Properties.of()
		.mapColor(PRISMATIC_PLANKS.defaultMapColor())
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.noCollision()
		.strength(0.5F)
		.ignitedByLava()
		.pushReaction(PushReaction.POPPED));
	Block PRISMATIC_DOOR = register(StellarityBlockItemIds.PRISMATIC_DOOR, p -> new DoorBlock(StellarityBlockSetTypes.PRISMATIC, p), BlockBehaviour.Properties.of()
		.mapColor(PRISMATIC_PLANKS.defaultMapColor())
		.instrument(NoteBlockInstrument.BASS)
		.strength(3.0F)
		.noOcclusion()
		.ignitedByLava()
		.pushReaction(PushReaction.POPPED));
	Block PRISMATIC_TRAPDOOR = register(StellarityBlockItemIds.PRISMATIC_TRAPDOOR, p -> new TrapDoorBlock(StellarityBlockSetTypes.PRISMATIC, p), BlockBehaviour.Properties.of()
		.mapColor(PRISMATIC_PLANKS.defaultMapColor())
		.instrument(NoteBlockInstrument.BASS)
		.strength(3.0F)
		.noOcclusion()
		.isValidSpawn(Blocks::never)
		.ignitedByLava());
	Block PRISMATIC_FENCE_GATE = register(StellarityBlockItemIds.PRISMATIC_FENCE_GATE, p -> new FenceGateBlock(StellarityWoodTypes.PRISMATIC, p), BlockBehaviour.Properties.of()
		.mapColor(PRISMATIC_PLANKS.defaultMapColor())
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.strength(2.0F, 3.0F)
		.ignitedByLava());
	Block PRISMATIC_SIGN = register(StellarityBlockItemIds.PRISMATIC_SIGN, p -> new StandingSignBlock(StellarityWoodTypes.PRISMATIC, p), BlockBehaviour.Properties.of()
		.mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava()
	);
	Block PRISMATIC_HANGING_SIGN = register(StellarityBlockItemIds.PRISMATIC_HANGING_SIGN, p -> new CeilingHangingSignBlock(StellarityWoodTypes.PRISMATIC, p), BlockBehaviour.Properties.of()
		.mapColor(MapColor.WOOD)
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.noCollision()
		.strength(1.0F)
		.ignitedByLava()
	);
	Block PRISMATIC_WALL_SIGN = register(StellarityBlockIds.PRISMATIC_WALL_SIGN, p -> new WallSignBlock(StellarityWoodTypes.PRISMATIC, p),
		BlocksAccessor.wallVariant(PRISMATIC_SIGN, true).mapColor(PRISMATIC_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).forceSolidOn().noCollision().strength(1.0F).ignitedByLava()
	);
	Block PRISMATIC_WALL_HANGING_SIGN = register(StellarityBlockIds.PRISMATIC_WALL_HANGING_SIGN, p -> new WallHangingSignBlock(StellarityWoodTypes.PRISMATIC, p),
		BlocksAccessor.wallVariant(PRISMATIC_HANGING_SIGN, true).mapColor(PRISMATIC_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).forceSolidOn().noCollision().strength(1.0F).ignitedByLava()
	);
	Block POTTED_PRISMATIC_SAPLING = register(StellarityBlockIds.POTTED_PRISMATIC_SAPLING, p -> new FlowerPotBlock(PRISMATIC_SAPLING, p), flowerPotProperties());

	Block ASHEN_PLANKS = register(StellarityBlockItemIds.ASHEN_PLANKS, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava());
	Block ASHEN_SAPLING = register(StellarityBlockItemIds.ASHEN_SAPLING, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.POPPED));
	Block ASHEN_LOG = register(StellarityBlockItemIds.ASHEN_LOG, RotatedPillarBlock::new, Blocks.logProperties(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
	Block STRIPPED_ASHEN_LOG = register(StellarityBlockItemIds.STRIPPED_ASHEN_LOG, RotatedPillarBlock::new, Blocks.logProperties(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
	Block STRIPPED_ASHEN_WOOD = register(StellarityBlockItemIds.STRIPPED_ASHEN_WOOD, RotatedPillarBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
	Block ASHEN_WOOD = register(StellarityBlockItemIds.ASHEN_WOOD, RotatedPillarBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
	Block ASHEN_LEAVES = register(StellarityBlockItemIds.ASHEN_LEAVES, p -> new TintedParticleLeavesBlock(0.01F, p), leavesProperties(SoundType.GRASS));
	Block ASHEN_SLAB = BlocksAccessor.registerSlab(StellarityBlockItemIds.ASHEN_SLAB, ASHEN_PLANKS);
	Block ASHEN_SHELF = register(StellarityBlockItemIds.ASHEN_SHELF, ShelfBlock::new,
		BlockBehaviour.Properties.of()
			.mapColor(ASHEN_PLANKS.defaultMapColor())
			.instrument(NoteBlockInstrument.BASS)
			.sound(SoundType.SHELF)
			.ignitedByLava()
			.strength(2.0F, 3.0F));
	Block ASHEN_FENCE = register(StellarityBlockItemIds.ASHEN_FENCE, FenceBlock::new, BlockBehaviour.Properties.of()
		.mapColor(ASHEN_PLANKS.defaultMapColor())
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.strength(2.0F, 3.0F)
		.sound(SoundType.WOOD)
		.ignitedByLava());
	Block ASHEN_STAIRS = BlocksAccessor.registerStair(StellarityBlockItemIds.ASHEN_STAIRS, ASHEN_PLANKS);
	Block ASHEN_BUTTON = register(StellarityBlockItemIds.ASHEN_BUTTON, p -> new ButtonBlock(StellarityBlockSetTypes.ASHEN, 30, p), Blocks.buttonProperties());
	Block ASHEN_PRESSURE_PLATE = register(StellarityBlockItemIds.ASHEN_PRESSURE_PLATE, p -> new PressurePlateBlock(StellarityBlockSetTypes.ASHEN, p), BlockBehaviour.Properties.of()
		.mapColor(ASHEN_PLANKS.defaultMapColor())
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.noCollision()
		.strength(0.5F)
		.ignitedByLava()
		.pushReaction(PushReaction.POPPED));
	Block ASHEN_DOOR = register(StellarityBlockItemIds.ASHEN_DOOR, p -> new DoorBlock(StellarityBlockSetTypes.ASHEN, p), BlockBehaviour.Properties.of()
		.mapColor(ASHEN_PLANKS.defaultMapColor())
		.instrument(NoteBlockInstrument.BASS)
		.strength(3.0F)
		.noOcclusion()
		.ignitedByLava()
		.pushReaction(PushReaction.POPPED));
	Block ASHEN_TRAPDOOR = register(StellarityBlockItemIds.ASHEN_TRAPDOOR, p -> new TrapDoorBlock(StellarityBlockSetTypes.ASHEN, p), BlockBehaviour.Properties.of()
		.mapColor(ASHEN_PLANKS.defaultMapColor())
		.instrument(NoteBlockInstrument.BASS)
		.strength(3.0F)
		.noOcclusion()
		.isValidSpawn(Blocks::never)
		.ignitedByLava());
	Block ASHEN_FENCE_GATE = register(StellarityBlockItemIds.ASHEN_FENCE_GATE, p -> new FenceGateBlock(StellarityWoodTypes.ASHEN, p), BlockBehaviour.Properties.of()
		.mapColor(ASHEN_PLANKS.defaultMapColor())
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.strength(2.0F, 3.0F)
		.ignitedByLava());
	Block ASHEN_SIGN = register(StellarityBlockItemIds.ASHEN_SIGN, p -> new StandingSignBlock(StellarityWoodTypes.ASHEN, p), BlockBehaviour.Properties.of()
		.mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava()
	);
	Block ASHEN_HANGING_SIGN = register(StellarityBlockItemIds.ASHEN_HANGING_SIGN, p -> new CeilingHangingSignBlock(StellarityWoodTypes.ASHEN, p), BlockBehaviour.Properties.of()
		.mapColor(MapColor.WOOD)
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.noCollision()
		.strength(1.0F)
		.ignitedByLava()
	);
	Block ASHEN_WALL_SIGN = register(StellarityBlockIds.ASHEN_WALL_SIGN, p -> new WallSignBlock(StellarityWoodTypes.ASHEN, p),
		BlocksAccessor.wallVariant(ASHEN_SIGN, true).mapColor(ASHEN_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).forceSolidOn().noCollision().strength(1.0F).ignitedByLava()
	);
	Block ASHEN_WALL_HANGING_SIGN = register(StellarityBlockIds.ASHEN_WALL_HANGING_SIGN, p -> new WallHangingSignBlock(StellarityWoodTypes.ASHEN, p),
		BlocksAccessor.wallVariant(ASHEN_HANGING_SIGN, true).mapColor(ASHEN_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).forceSolidOn().noCollision().strength(1.0F).ignitedByLava()
	);
	Block POTTED_ASHEN_SAPLING = register(StellarityBlockIds.POTTED_ASHEN_SAPLING, p -> new FlowerPotBlock(ASHEN_SAPLING, p), flowerPotProperties());

	Block INFERNO_PLANKS = register(StellarityBlockItemIds.INFERNO_PLANKS, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava());
	Block INFERNO_SAPLING = register(StellarityBlockItemIds.INFERNO_SAPLING, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.POPPED));
	Block INFERNO_LOG = register(StellarityBlockItemIds.INFERNO_LOG, RotatedPillarBlock::new, Blocks.logProperties(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
	Block STRIPPED_INFERNO_LOG = register(StellarityBlockItemIds.STRIPPED_INFERNO_LOG, RotatedPillarBlock::new, Blocks.logProperties(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
	Block STRIPPED_INFERNO_WOOD = register(StellarityBlockItemIds.STRIPPED_INFERNO_WOOD, RotatedPillarBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
	Block INFERNO_WOOD = register(StellarityBlockItemIds.INFERNO_WOOD, RotatedPillarBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
	Block INFERNO_LEAVES = register(StellarityBlockItemIds.INFERNO_LEAVES, p -> new TintedParticleLeavesBlock(0.01F, p), leavesProperties(SoundType.GRASS));
	Block INFERNO_SLAB = BlocksAccessor.registerSlab(StellarityBlockItemIds.INFERNO_SLAB, INFERNO_PLANKS);
	Block INFERNO_SHELF = register(StellarityBlockItemIds.INFERNO_SHELF, ShelfBlock::new,
		BlockBehaviour.Properties.of()
			.mapColor(INFERNO_PLANKS.defaultMapColor())
			.instrument(NoteBlockInstrument.BASS)
			.sound(SoundType.SHELF)
			.ignitedByLava()
			.strength(2.0F, 3.0F));
	Block INFERNO_FENCE = register(StellarityBlockItemIds.INFERNO_FENCE, FenceBlock::new, BlockBehaviour.Properties.of()
		.mapColor(INFERNO_PLANKS.defaultMapColor())
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.strength(2.0F, 3.0F)
		.sound(SoundType.WOOD)
		.ignitedByLava());
	Block INFERNO_STAIRS = BlocksAccessor.registerStair(StellarityBlockItemIds.INFERNO_STAIRS, INFERNO_PLANKS);
	Block INFERNO_BUTTON = register(StellarityBlockItemIds.INFERNO_BUTTON, p -> new ButtonBlock(StellarityBlockSetTypes.INFERNO, 30, p), Blocks.buttonProperties());
	Block INFERNO_PRESSURE_PLATE = register(StellarityBlockItemIds.INFERNO_PRESSURE_PLATE, p -> new PressurePlateBlock(StellarityBlockSetTypes.INFERNO, p), BlockBehaviour.Properties.of()
		.mapColor(INFERNO_PLANKS.defaultMapColor())
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.noCollision()
		.strength(0.5F)
		.ignitedByLava()
		.pushReaction(PushReaction.POPPED));
	Block INFERNO_DOOR = register(StellarityBlockItemIds.INFERNO_DOOR, p -> new DoorBlock(StellarityBlockSetTypes.INFERNO, p), BlockBehaviour.Properties.of()
		.mapColor(INFERNO_PLANKS.defaultMapColor())
		.instrument(NoteBlockInstrument.BASS)
		.strength(3.0F)
		.noOcclusion()
		.ignitedByLava()
		.pushReaction(PushReaction.POPPED));
	Block INFERNO_TRAPDOOR = register(StellarityBlockItemIds.INFERNO_TRAPDOOR, p -> new TrapDoorBlock(StellarityBlockSetTypes.INFERNO, p), BlockBehaviour.Properties.of()
		.mapColor(INFERNO_PLANKS.defaultMapColor())
		.instrument(NoteBlockInstrument.BASS)
		.strength(3.0F)
		.noOcclusion()
		.isValidSpawn(Blocks::never)
		.ignitedByLava());
	Block INFERNO_FENCE_GATE = register(StellarityBlockItemIds.INFERNO_FENCE_GATE, p -> new FenceGateBlock(StellarityWoodTypes.INFERNO, p), BlockBehaviour.Properties.of()
		.mapColor(INFERNO_PLANKS.defaultMapColor())
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.strength(2.0F, 3.0F)
		.ignitedByLava());
	Block INFERNO_SIGN = register(StellarityBlockItemIds.INFERNO_SIGN, p -> new StandingSignBlock(StellarityWoodTypes.INFERNO, p), BlockBehaviour.Properties.of()
		.mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava()
	);
	Block INFERNO_HANGING_SIGN = register(StellarityBlockItemIds.INFERNO_HANGING_SIGN, p -> new CeilingHangingSignBlock(StellarityWoodTypes.INFERNO, p), BlockBehaviour.Properties.of()
		.mapColor(MapColor.WOOD)
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.noCollision()
		.strength(1.0F)
		.ignitedByLava()
	);
	Block INFERNO_WALL_SIGN = register(StellarityBlockIds.INFERNO_WALL_SIGN, p -> new WallSignBlock(StellarityWoodTypes.INFERNO, p),
		BlocksAccessor.wallVariant(INFERNO_SIGN, true).mapColor(INFERNO_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).forceSolidOn().noCollision().strength(1.0F).ignitedByLava()
	);
	Block INFERNO_WALL_HANGING_SIGN = register(StellarityBlockIds.INFERNO_WALL_HANGING_SIGN, p -> new WallHangingSignBlock(StellarityWoodTypes.INFERNO, p),
		BlocksAccessor.wallVariant(INFERNO_HANGING_SIGN, true).mapColor(INFERNO_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).forceSolidOn().noCollision().strength(1.0F).ignitedByLava()
	);
	Block POTTED_INFERNO_SAPLING = register(StellarityBlockIds.POTTED_INFERNO_SAPLING, p -> new FlowerPotBlock(INFERNO_SAPLING, p), flowerPotProperties());

	static Block register(BlockItemId id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings) {
		return register(id.block(), blockFactory, settings);
	}

	static Block register(BlockItemId id, BlockBehaviour.Properties settings) {
		return register(id.block(), settings);
	}

	static Block register(ResourceKey<Block> blockKey, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings) {

		settings = settings.setId(blockKey);

		Block block = blockFactory.apply(settings);
		Registry.register(BuiltInRegistries.BLOCK, blockKey, block);

		return block;
	}

	static Block register(ResourceKey<Block> blockKey, BlockBehaviour.Properties settings) {
		return register(blockKey, Block::new, settings);
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Blocks");
		BlockTransformerHelper.registerHoe(BlockTransformer.BlockTransformData.builder(BlockPredicate.matchesBlocks(ROOTED_ENDER_DIRT), Blocks.ROOTED_DIRT).sound(SoundEvents.HOE_TILL).loot(BuiltInLootTables.TILL_ROOTED_DIRT).dropStrategy(BlockTransformer.DropStrategy.CLICKED_FACE).build());
		BlockTransformerHelper.registerTilling(COARSE_ENDER_DIRT, ENDER_DIRT);
		BlockTransformerHelper.registerTilling(new Block[]{ENDER_DIRT, ENDER_GRASS_BLOCK, COARSE_ENDER_DIRT}, ENDER_DIRT_PATH);

		for (var pair : List.of(
			new Tuple2<>(AMETHYII_LOG, STRIPPED_AMETHYII_LOG),
			new Tuple2<>(AMETHYII_WOOD, STRIPPED_AMETHYII_WOOD),
			new Tuple2<>(HALLOWED_LOG, STRIPPED_HALLOWED_LOG),
			new Tuple2<>(HALLOWED_WOOD, STRIPPED_HALLOWED_WOOD),
			new Tuple2<>(SHRUBBED_LOG, STRIPPED_SHRUBBED_LOG),
			new Tuple2<>(SHRUBBED_WOOD, STRIPPED_SHRUBBED_WOOD),
			new Tuple2<>(PRISMATIC_LOG, STRIPPED_PRISMATIC_LOG),
			new Tuple2<>(PRISMATIC_WOOD, STRIPPED_PRISMATIC_WOOD),
			new Tuple2<>(ASHEN_LOG, STRIPPED_ASHEN_LOG),
			new Tuple2<>(ASHEN_WOOD, STRIPPED_ASHEN_WOOD),
			new Tuple2<>(INFERNO_LOG, STRIPPED_INFERNO_LOG),
			new Tuple2<>(INFERNO_WOOD, STRIPPED_INFERNO_WOOD))
		)
			BlockTransformerHelper.registerStripping(pair._1(), pair._2());
	}
}
