package dev.coder2195.stellarity.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.coder2195.stellarity.registry.StellarityRecipeBookCategories;
import dev.coder2195.stellarity.registry.StellarityRecipeSerializers;
import dev.coder2195.stellarity.registry.StellarityRecipeTypes;
import dev.coder2195.stellarity.util.CustomCodecs;
import dev.coder2195.stellarity.util.CustomStreamCodecs;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.HashMap;

public record CauldronCraftingRecipe(HashMap<Ingredient, Integer> ingredients,
																		 ItemStackTemplate result) implements Recipe<ItemListInput> {
	
	@Override
	public boolean matches(ItemListInput input, Level level) {
		var requirements = ingredients.keySet();
		HashMap<Ingredient, Integer> ingredientsCopy = new HashMap<>(ingredients);

		for (var itemStack: input) {
			boolean found = false;
			for (Ingredient requirement: requirements) {
				if (requirement.test(itemStack)) {
					int existingCount = ingredientsCopy.getOrDefault(requirement, 0);
					if (existingCount == 0) continue;
					found = true;
					ingredientsCopy.put(requirement, existingCount - 1);
					break;
				}
			}
			if (!found) return false;
		}

		return ingredientsCopy.values().stream().noneMatch(integer -> integer != 0);
	}

	@Override
	public ItemStack assemble(ItemListInput input) {
		return result.create();
	}

	@Override
	public boolean showNotification() {
		return false;
	}

	@Override
	public String group() {
		return "";
	}

	@Override
	public RecipeSerializer<? extends Recipe<ItemListInput>> getSerializer() {
		return StellarityRecipeSerializers.CAULDRON_CRAFTING;
	}

	@Override
	public RecipeType<? extends Recipe<ItemListInput>> getType() {
		return StellarityRecipeTypes.CAULDRON_CRAFTING;
	}

	@Override
	public PlacementInfo placementInfo() {
		return PlacementInfo.NOT_PLACEABLE;
	}

	@Override
	public RecipeBookCategory recipeBookCategory() {
		return StellarityRecipeBookCategories.CAULDRON_CRAFTING;
	}

	public static final StreamCodec<RegistryFriendlyByteBuf, CauldronCraftingRecipe> STREAM_CODEC = StreamCodec.composite(CustomStreamCodecs.INGREDIENTS_MAP, CauldronCraftingRecipe::ingredients, ItemStackTemplate.STREAM_CODEC, CauldronCraftingRecipe::result, CauldronCraftingRecipe::new);


	public static final MapCodec<CauldronCraftingRecipe> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			CustomCodecs.INGREDIENT_MAP_CODEC.codec().listOf().fieldOf("ingredients").forGetter((recipe) ->
				recipe.ingredients.entrySet().stream().toList()
			),
			ItemStackTemplate.CODEC.fieldOf("result").forGetter(CauldronCraftingRecipe::result)
		).apply(instance, (ingredients, result) -> {
			HashMap<Ingredient, Integer> ingredientMap = new HashMap<>();

			for (var ingredient : ingredients) {
				ingredientMap.put(ingredient.getKey(), ingredient.getValue());
			}
			return new CauldronCraftingRecipe(ingredientMap, result);
		}));
}
