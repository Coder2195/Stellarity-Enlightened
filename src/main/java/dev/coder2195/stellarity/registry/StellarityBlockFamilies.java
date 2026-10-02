package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;

import static dev.coder2195.stellarity.registry.StellarityBlocks.*;

public interface StellarityBlockFamilies {
	BlockFamily AMETHYII_PLANKS = BlockFamilies.familyBuilder(StellarityBlocks.AMETHYII_PLANKS)
		.log(AMETHYII_LOG)
		.strippedLog(STRIPPED_AMETHYII_LOG)
		.button(AMETHYII_BUTTON)
		.fence(AMETHYII_FENCE)
		.fenceGate(AMETHYII_FENCE_GATE)
		.hangingSign(AMETHYII_HANGING_SIGN, AMETHYII_WALL_HANGING_SIGN)
		.pressurePlate(AMETHYII_PRESSURE_PLATE)
		.sign(AMETHYII_SIGN, AMETHYII_WALL_SIGN)
		.slab(AMETHYII_SLAB)
		.stairs(AMETHYII_STAIRS)
		.door(AMETHYII_DOOR)
		.trapdoor(AMETHYII_TRAPDOOR)
		.recipeGroupPrefix("wooden")
		.recipeUnlockedBy("has_planks")
		.getFamily();

	BlockFamily HALLOWED_PLANKS = BlockFamilies.familyBuilder(StellarityBlocks.HALLOWED_PLANKS)
		.log(HALLOWED_LOG)
		.strippedLog(STRIPPED_HALLOWED_LOG)
		.button(HALLOWED_BUTTON)
		.fence(HALLOWED_FENCE)
		.fenceGate(HALLOWED_FENCE_GATE)
		.hangingSign(HALLOWED_HANGING_SIGN, HALLOWED_WALL_HANGING_SIGN)
		.pressurePlate(HALLOWED_PRESSURE_PLATE)
		.sign(HALLOWED_SIGN, HALLOWED_WALL_SIGN)
		.slab(HALLOWED_SLAB)
		.stairs(HALLOWED_STAIRS)
		.door(HALLOWED_DOOR)
		.trapdoor(HALLOWED_TRAPDOOR)
		.recipeGroupPrefix("wooden")
		.recipeUnlockedBy("has_planks")
		.getFamily();

	BlockFamily SHRUBBED_PLANKS = BlockFamilies.familyBuilder(StellarityBlocks.SHRUBBED_PLANKS)
		.log(SHRUBBED_LOG)
		.strippedLog(STRIPPED_SHRUBBED_LOG)
		.button(SHRUBBED_BUTTON)
		.fence(SHRUBBED_FENCE)
		.fenceGate(SHRUBBED_FENCE_GATE)
		.hangingSign(SHRUBBED_HANGING_SIGN, SHRUBBED_WALL_HANGING_SIGN)
		.pressurePlate(SHRUBBED_PRESSURE_PLATE)
		.sign(SHRUBBED_SIGN, SHRUBBED_WALL_SIGN)
		.slab(SHRUBBED_SLAB)
		.stairs(SHRUBBED_STAIRS)
		.door(SHRUBBED_DOOR)
		.trapdoor(SHRUBBED_TRAPDOOR)
		.recipeGroupPrefix("wooden")
		.recipeUnlockedBy("has_planks")
		.getFamily();

	BlockFamily PRISMATIC_PLANKS = BlockFamilies.familyBuilder(StellarityBlocks.PRISMATIC_PLANKS)
		.log(PRISMATIC_LOG)
		.strippedLog(STRIPPED_PRISMATIC_LOG)
		.button(PRISMATIC_BUTTON)
		.fence(PRISMATIC_FENCE)
		.fenceGate(PRISMATIC_FENCE_GATE)
		.hangingSign(PRISMATIC_HANGING_SIGN, PRISMATIC_WALL_HANGING_SIGN)
		.pressurePlate(PRISMATIC_PRESSURE_PLATE)
		.sign(PRISMATIC_SIGN, PRISMATIC_WALL_SIGN)
		.slab(PRISMATIC_SLAB)
		.stairs(PRISMATIC_STAIRS)
		.door(PRISMATIC_DOOR)
		.trapdoor(PRISMATIC_TRAPDOOR)
		.recipeGroupPrefix("wooden")
		.recipeUnlockedBy("has_planks")
		.getFamily();

	BlockFamily ASHEN_PLANKS = BlockFamilies.familyBuilder(StellarityBlocks.ASHEN_PLANKS)
		.log(ASHEN_LOG)
		.strippedLog(STRIPPED_ASHEN_LOG)
		.button(ASHEN_BUTTON)
		.fence(ASHEN_FENCE)
		.fenceGate(ASHEN_FENCE_GATE)
		.hangingSign(ASHEN_HANGING_SIGN, ASHEN_WALL_HANGING_SIGN)
		.pressurePlate(ASHEN_PRESSURE_PLATE)
		.sign(ASHEN_SIGN, ASHEN_WALL_SIGN)
		.slab(ASHEN_SLAB)
		.stairs(ASHEN_STAIRS)
		.door(ASHEN_DOOR)
		.trapdoor(ASHEN_TRAPDOOR)
		.recipeGroupPrefix("wooden")
		.recipeUnlockedBy("has_planks")
		.getFamily();

	BlockFamily INFERNO_PLANKS = BlockFamilies.familyBuilder(StellarityBlocks.INFERNO_PLANKS)
		.log(INFERNO_LOG)
		.strippedLog(STRIPPED_INFERNO_LOG)
		.button(INFERNO_BUTTON)
		.fence(INFERNO_FENCE)
		.fenceGate(INFERNO_FENCE_GATE)
		.hangingSign(INFERNO_HANGING_SIGN, INFERNO_WALL_HANGING_SIGN)
		.pressurePlate(INFERNO_PRESSURE_PLATE)
		.sign(INFERNO_SIGN, INFERNO_WALL_SIGN)
		.slab(INFERNO_SLAB)
		.stairs(INFERNO_STAIRS)
		.door(INFERNO_DOOR)
		.trapdoor(INFERNO_TRAPDOOR)
		.recipeGroupPrefix("wooden")
		.recipeUnlockedBy("has_planks")
		.getFamily();

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Block Families");
	}

}
