package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.recipe.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import dev.coder2195.stellarity.Stellarity;

public interface StellarityRecipeSerializers {
	RecipeSerializer<AltarOfTheAccursedSimpleRecipe> ALTAR_OF_THE_ACCURSED_SIMPLE = registerSerializer("altar_of_the_accursed_simple", new RecipeSerializer<>(AltarOfTheAccursedSimpleRecipe.CODEC, AltarOfTheAccursedSimpleRecipe.STREAM_CODEC));
	RecipeSerializer<AltarOfTheAccursedUpgradeRecipe> ALTAR_OF_THE_ACCURSED_UPGRADE = registerSerializer("altar_of_the_accursed_upgrade", new RecipeSerializer<>(AltarOfTheAccursedUpgradeRecipe.CODEC, AltarOfTheAccursedUpgradeRecipe.STREAM_CODEC));
	RecipeSerializer<AltarOfTheAccursedDyeRecipe> ALTAR_OF_THE_ACCURSED_DYE = registerSerializer("altar_of_the_accursed_dye", new RecipeSerializer<>(AltarOfTheAccursedDyeRecipe.CODEC, AltarOfTheAccursedDyeRecipe.STREAM_CODEC));
	RecipeSerializer<ConsecrationRecipe> CONSECRATION = registerSerializer("consecration", new RecipeSerializer<>(ConsecrationRecipe.CODEC, ConsecrationRecipe.STREAM_CODEC));
	RecipeSerializer<CauldronCraftingSimpleRecipe> CAULDRON_CRAFTING_SIMPLE = registerSerializer("cauldron_crafting_simple", new RecipeSerializer<>(CauldronCraftingSimpleRecipe.CODEC, CauldronCraftingSimpleRecipe.STREAM_CODEC));
	RecipeSerializer<CauldronCraftingPotionConvertRecipe> CAULDRON_CRAFTING_POTION_CONVERT = registerSerializer("cauldron_crafting_potion_convert", new RecipeSerializer<>(CauldronCraftingPotionConvertRecipe.CODEC, CauldronCraftingPotionConvertRecipe.STREAM_CODEC));

	private static <T extends Recipe<?>> RecipeSerializer<T> registerSerializer(final String id, RecipeSerializer<T> serializer) {
		return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Stellarity.id(id), serializer);
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Recipe Serializers");
	}
}
