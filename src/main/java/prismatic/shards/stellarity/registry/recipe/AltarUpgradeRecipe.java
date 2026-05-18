package prismatic.shards.stellarity.registry.recipe;


import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.StellarityRecipeSerializers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public record AltarUpgradeRecipe(Ingredient equipment,
                                 HashMap<Ingredient, Integer> ingredients,
                                 ItemStackTemplate result) implements AltarRecipe {

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

		var returning = result.create();
		var patched = availableEquipment.getComponentsPatch();
		for (var component : patched.entrySet()) {
			var value = component.getValue();
			if (value.isEmpty()) continue;

			returning.set((DataComponentType<Object>) component.getKey(), component.getValue().get());
		}

		return new Output(available, returning);

	}


	@Override
	public @NonNull RecipeSerializer<? extends Recipe<Input>> getSerializer() {
		return StellarityRecipeSerializers.ALTAR_UPGRADE;
	}

	public static final StreamCodec<RegistryFriendlyByteBuf, AltarUpgradeRecipe> STREAM_CODEC = StreamCodec.of(AltarUpgradeRecipe::toNetwork, AltarUpgradeRecipe::fromNetwork);

	public static final MapCodec<AltarUpgradeRecipe> CODEC = RecordCodecBuilder.mapCodec(

		instance -> instance.group(
			INGREDIENT_CODEC.codec().listOf().fieldOf("ingredients").forGetter((recipe) ->
				recipe.ingredients.entrySet().stream().toList()
			),
			Ingredient.CODEC.fieldOf("equipment").forGetter(AltarUpgradeRecipe::equipment),
			ItemStackTemplate.CODEC.fieldOf("result").forGetter(AltarUpgradeRecipe::result)
		).apply(instance, (ingredients, equipment, result) -> {
			HashMap<Ingredient, Integer> ingredientMap = new HashMap<>();

			for (var ingredient : ingredients) {
				ingredientMap.put(ingredient.getKey(), ingredient.getValue());
			}
			return new AltarUpgradeRecipe(equipment, ingredientMap, result);
		}));

	public static AltarUpgradeRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
		var ingredients = AltarRecipe.readIngredients(buf);
		Ingredient equipment = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
		ItemStackTemplate itemStack = ItemStackTemplate.STREAM_CODEC.decode(buf);
		return new AltarUpgradeRecipe(equipment, ingredients, itemStack);
	}

	public static void toNetwork(RegistryFriendlyByteBuf buf, AltarUpgradeRecipe recipe) {
		recipe.writeIngredients(buf);
		Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.equipment);
		ItemStackTemplate.STREAM_CODEC.encode(buf, recipe.result);
	}


}
