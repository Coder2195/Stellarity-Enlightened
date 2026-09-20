package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeBookCategory;

public interface StellarityRecipeBookCategories {

	RecipeBookCategory ALTAR_OF_THE_ACCURSED = register("altar_of_the_accursed");
	RecipeBookCategory CONSECRATION = register("consecration");
	RecipeBookCategory CAULDRON_CRAFTING = register("cauldron_crafting");

	private static RecipeBookCategory register(final String id) {
		return Registry.register(BuiltInRegistries.RECIPE_BOOK_CATEGORY, Stellarity.id(id), new RecipeBookCategory());
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Recipe Book Categories");
	}
}
