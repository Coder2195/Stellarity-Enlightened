package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.block_entity.AltarOfTheAccursedBlockEntity;
import dev.coder2195.stellarity.block_entity.ColoredBlockEntity;
import dev.coder2195.stellarity.block_entity.DragonsBreathCauldronBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;


public interface StellarityBlockEntityTypes {
	BlockEntityType<AltarOfTheAccursedBlockEntity> ALTAR_OF_THE_ACCURSED = register("altar_of_the_accursed", AltarOfTheAccursedBlockEntity::new, StellarityBlocks.ALTAR_OF_THE_ACCURSED);
	BlockEntityType<ColoredBlockEntity> COLORED_BLOCK = register("colored_block", ColoredBlockEntity::new, StellarityBlocks.COLORED_LEAVES);
	BlockEntityType<DragonsBreathCauldronBlockEntity> DRAGONS_BREATH_CAULDRON = register("dragon_breath_cauldron", DragonsBreathCauldronBlockEntity::new, StellarityBlocks.DRAGONS_BREATH_CAULDRON);

	static <T extends BlockEntity> BlockEntityType<T> register(String id, FabricBlockEntityTypeBuilder.Factory<T> entityFactory, Block... blocks) {
		return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Stellarity.id(id), FabricBlockEntityTypeBuilder.create(entityFactory, blocks).build());
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Block Entities");
	}
}
