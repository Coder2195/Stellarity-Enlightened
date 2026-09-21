package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.recipe.CauldronCraftingSimpleRecipe;
import dev.coder2195.stellarity.recipe.ConsecrationRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.recipe.AltarOfTheAccursedRecipe;

public interface StellarityRecipeTypes {
	RecipeType<AltarOfTheAccursedRecipe> ALTAR_OF_THE_ACCURSED = register("altar_of_the_accursed");
	RecipeType<ConsecrationRecipe> CONSECRATION = register("consecration");
	RecipeType<CauldronCraftingSimpleRecipe> CAULDRON_CRAFTING = register("cauldron_crafting");


	private static <T extends Recipe<?>> RecipeType<T> register(final String id) {
		final var path = Stellarity.id(id);
		final var string = path.toString();
		return Registry.register(BuiltInRegistries.RECIPE_TYPE, path, new RecipeType<T>() {
			public String toString() {
				return string;
			}
		});
	}


	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Recipe Types");
	}
}
