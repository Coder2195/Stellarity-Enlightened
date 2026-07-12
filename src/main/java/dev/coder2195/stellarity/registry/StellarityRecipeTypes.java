package dev.coder2195.stellarity.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.recipe.AltarRecipe;

public interface StellarityRecipeTypes {
	RecipeType<AltarRecipe> ALTAR_RECIPE = register("altar_of_the_accursed");


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
