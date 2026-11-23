package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.block_entity.AltarOfTheAccursedBlockEntity;
import dev.coder2195.stellarity.block_entity.AltarOfTheSacredBlockEntity;
import dev.coder2195.stellarity.block_entity.DragonBreathCauldronBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityTypes;

import java.util.List;

import static dev.coder2195.stellarity.registry.StellarityBlocks.*;


public interface StellarityBlockEntityTypes {
	BlockEntityType<AltarOfTheAccursedBlockEntity> ALTAR_OF_THE_ACCURSED = register("altar_of_the_accursed", AltarOfTheAccursedBlockEntity::new, StellarityBlocks.ALTAR_OF_THE_ACCURSED);
	BlockEntityType<DragonBreathCauldronBlockEntity> DRAGON_BREATH_CAULDRON = register("dragon_breath_cauldron", DragonBreathCauldronBlockEntity::new, StellarityBlocks.DRAGON_BREATH_CAULDRON);
	BlockEntityType<AltarOfTheSacredBlockEntity> ALTAR_OF_THE_SACRED = register("altar_of_the_sacred", AltarOfTheSacredBlockEntity::new, StellarityBlocks.ALTAR_OF_THE_SACRED);

	static <T extends BlockEntity> BlockEntityType<T> register(String id, FabricBlockEntityTypeBuilder.Factory<T> entityFactory, Block... blocks) {
		return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Stellarity.id(id), FabricBlockEntityTypeBuilder.create(entityFactory, blocks).build());
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Block Entities");

		for (var shelf: List.of(AMETHYII_SHELF, HALLOWED_SHELF, ASHEN_SHELF, INFERNO_SHELF, PRISMATIC_SHELF, SHRUBBED_SHELF))
			BlockEntityTypes.SHELF.addValidBlock(shelf);

		for (var sign : List.of(AMETHYII_SIGN, AMETHYII_WALL_SIGN, HALLOWED_SIGN, HALLOWED_WALL_SIGN, ASHEN_SIGN, ASHEN_WALL_SIGN, INFERNO_SIGN, INFERNO_WALL_SIGN, PRISMATIC_SIGN, PRISMATIC_WALL_SIGN, SHRUBBED_SIGN, SHRUBBED_WALL_SIGN))
			BlockEntityTypes.SIGN.addValidBlock(sign);

		for (var hangingSign : List.of(AMETHYII_HANGING_SIGN, AMETHYII_WALL_HANGING_SIGN, HALLOWED_HANGING_SIGN, HALLOWED_WALL_HANGING_SIGN, ASHEN_HANGING_SIGN, ASHEN_WALL_HANGING_SIGN, INFERNO_HANGING_SIGN, INFERNO_WALL_HANGING_SIGN, PRISMATIC_HANGING_SIGN, PRISMATIC_WALL_HANGING_SIGN, SHRUBBED_HANGING_SIGN, SHRUBBED_WALL_HANGING_SIGN))
			BlockEntityTypes.HANGING_SIGN.addValidBlock(hangingSign);
	}
}
