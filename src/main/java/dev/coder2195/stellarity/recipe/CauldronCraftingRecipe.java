package dev.coder2195.stellarity.recipe;

import dev.coder2195.stellarity.registry.StellarityRecipeBookCategories;
import dev.coder2195.stellarity.registry.StellarityRecipeTypes;
import net.minecraft.world.item.crafting.*;

import java.util.HashMap;

public interface CauldronCraftingRecipe extends Recipe<ItemListInput> {

	@Override
	default RecipeType<? extends Recipe<ItemListInput>> getType() {
		return StellarityRecipeTypes.CAULDRON_CRAFTING;
	}

	@Override
	default PlacementInfo placementInfo() {
		return PlacementInfo.NOT_PLACEABLE;
	}

	@Override
	default RecipeBookCategory recipeBookCategory() {
		return StellarityRecipeBookCategories.CAULDRON_CRAFTING;
	}

	@Override
	default boolean showNotification() {
		return false;
	}

	@Override
	default String group() {
		return "";
	}

	static boolean fulfillsIngredients(HashMap<Ingredient, Integer> ingredients, ItemListInput input) {
		var requirements = ingredients.keySet();

		for (var itemStack: input) {
			boolean found = false;
			for (Ingredient requirement: requirements) {
				if (requirement.test(itemStack)) {
					int existingCount = ingredients.getOrDefault(requirement, 0);
					if (existingCount == 0) continue;
					found = true;
					ingredients.put(requirement, existingCount - 1);
					break;
				}
			}
			if (!found) return false;
		}

		return ingredients.values().stream().allMatch(integer -> integer == 0);
	}
}
