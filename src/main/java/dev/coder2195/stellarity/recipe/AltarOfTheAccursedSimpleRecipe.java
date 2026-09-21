package dev.coder2195.stellarity.recipe;


import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.coder2195.stellarity.util.CustomCodecs;
import dev.coder2195.stellarity.util.CustomStreamCodecs;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import dev.coder2195.stellarity.registry.StellarityRecipeSerializers;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.List;


public record AltarOfTheAccursedSimpleRecipe(HashMap<Ingredient, Integer> ingredients,
                                             ItemStackTemplate result) implements AltarOfTheAccursedRecipe {

	public @Nullable Output craft(List<ItemStack> itemStacks) {
		HashMap<Ingredient, Integer> required = new HashMap<>(ingredients);
		HashMap<ItemStack, Integer> available = new HashMap<>();

		for (var itemStack : itemStacks) {
			int availableCount = itemStack.getCount();

			available.put(itemStack, itemStack.getCount());

			boolean exists = false;

			for (var requirement : ingredients.keySet()) {
				Integer requiredCount = required.get(requirement);

				if (!requirement.test(itemStack)) continue;
				exists = true;
				if (requiredCount == 0) break;
				if (availableCount == requiredCount) {
					required.put(requirement, 0);
					available.remove(itemStack);
					break;
				}

				if (availableCount > requiredCount) {
					required.put(requirement, 0);
					available.put(itemStack, availableCount - requiredCount);

					break;
				}

				required.put(requirement, requiredCount - availableCount);
				available.remove(itemStack);
			}

			if (!exists) return null;
		}

		for (var counts : required.values()) {
			if (counts > 0) return null;
		}

		return new Output(available, result.create());

	}


	@Override
	public RecipeSerializer<? extends Recipe<ItemListInput>> getSerializer() {
		return StellarityRecipeSerializers.ALTAR_OF_THE_ACCURSED_SIMPLE;
	}

	public static final StreamCodec<RegistryFriendlyByteBuf, AltarOfTheAccursedSimpleRecipe> STREAM_CODEC = StreamCodec.composite(CustomStreamCodecs.INGREDIENTS_MAP, AltarOfTheAccursedSimpleRecipe::ingredients, ItemStackTemplate.STREAM_CODEC, AltarOfTheAccursedSimpleRecipe::result, AltarOfTheAccursedSimpleRecipe::new);

	public static final MapCodec<AltarOfTheAccursedSimpleRecipe> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			CustomCodecs.INGREDIENT_MAP_ENTRY_CODEC.codec().listOf().fieldOf("ingredients").forGetter((recipe) ->
				recipe.ingredients.entrySet().stream().toList()
			),
			ItemStackTemplate.CODEC.fieldOf("result").forGetter(AltarOfTheAccursedSimpleRecipe::result)
		).apply(instance, (ingredients, result) -> {
			HashMap<Ingredient, Integer> ingredientMap = new HashMap<>();

			for (var ingredient : ingredients) {
				ingredientMap.put(ingredient.getKey(), ingredient.getValue());
			}
			return new AltarOfTheAccursedSimpleRecipe(ingredientMap, result);
		}));


}
