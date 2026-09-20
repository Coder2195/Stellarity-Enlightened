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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public record AltarOfTheAccursedUpgradeRecipe(Ingredient equipment,
                                              HashMap<Ingredient, Integer> ingredients,
                                              ItemStackTemplate result) implements AltarOfTheAccursedRecipe {

	public @Nullable Output craft(List<ItemStack> itemStacks) {
		HashMap<Ingredient, Integer> required = new HashMap<>(ingredients);
		HashMap<ItemStack, Integer> available = new HashMap<>();

		ItemStack availableEquipment = null;
		List<ItemStack> temp = new ArrayList<>(itemStacks.size());
		for (ItemStack stack : itemStacks) {
			if (availableEquipment == null && equipment.test(stack)) {
				availableEquipment = stack;
				int count = availableEquipment.getCount();
				if (count > 1) available.put(availableEquipment, count - 1);
				continue;
			}

			temp.add(stack);

		}

		if (availableEquipment == null) return null;

		itemStacks = temp;

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

		return new Output(available, result.apply(availableEquipment.getComponentsPatch()));
	}


	@Override
	public RecipeSerializer<? extends Recipe<ItemListInput>> getSerializer() {
		return StellarityRecipeSerializers.ALTAR_OF_THE_ACCURSED_UPGRADE;
	}

	public static final StreamCodec<RegistryFriendlyByteBuf, AltarOfTheAccursedUpgradeRecipe> STREAM_CODEC = StreamCodec.composite(
		Ingredient.CONTENTS_STREAM_CODEC, AltarOfTheAccursedUpgradeRecipe::equipment,
		CustomStreamCodecs.INGREDIENTS_MAP, AltarOfTheAccursedUpgradeRecipe::ingredients,
		ItemStackTemplate.STREAM_CODEC, AltarOfTheAccursedUpgradeRecipe::result,
		AltarOfTheAccursedUpgradeRecipe::new
	);

	public static final MapCodec<AltarOfTheAccursedUpgradeRecipe> CODEC = RecordCodecBuilder.mapCodec(

		instance -> instance.group(
			CustomCodecs.INGREDIENT_MAP_CODEC.codec().listOf().fieldOf("ingredients").forGetter((recipe) ->
				recipe.ingredients.entrySet().stream().toList()
			),
			Ingredient.CODEC.fieldOf("equipment").forGetter(AltarOfTheAccursedUpgradeRecipe::equipment),
			ItemStackTemplate.CODEC.fieldOf("result").forGetter(AltarOfTheAccursedUpgradeRecipe::result)
		).apply(instance, (ingredients, equipment, result) -> {
			HashMap<Ingredient, Integer> ingredientMap = new HashMap<>();

			for (var ingredient : ingredients) {
				ingredientMap.put(ingredient.getKey(), ingredient.getValue());
			}
			return new AltarOfTheAccursedUpgradeRecipe(equipment, ingredientMap, result);
		}));



}
