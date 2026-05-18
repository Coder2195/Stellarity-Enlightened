package prismatic.shards.stellarity.registry.recipe;


import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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

import java.util.HashMap;
import java.util.List;


public record AltarSimpleRecipe(HashMap<Ingredient, Integer> ingredients,
                                ItemStackTemplate result) implements AltarRecipe {

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
	public @NonNull RecipeSerializer<? extends Recipe<Input>> getSerializer() {
		return StellarityRecipeSerializers.ALTAR_SIMPLE;
	}

	public static final StreamCodec<RegistryFriendlyByteBuf, AltarSimpleRecipe> STREAM_CODEC = StreamCodec.of(AltarSimpleRecipe::toNetwork, AltarSimpleRecipe::fromNetwork);
	public static final MapCodec<AltarSimpleRecipe> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			INGREDIENT_CODEC.codec().listOf().fieldOf("ingredients").forGetter((recipe) ->
				recipe.ingredients.entrySet().stream().toList()
			),
			ItemStackTemplate.CODEC.fieldOf("result").forGetter(AltarSimpleRecipe::result)
		).apply(instance, (ingredients, result) -> {
			HashMap<Ingredient, Integer> ingredientMap = new HashMap<>();

			for (var ingredient : ingredients) {
				ingredientMap.put(ingredient.getKey(), ingredient.getValue());
			}
			return new AltarSimpleRecipe(ingredientMap, result);
		}));

	public static AltarSimpleRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
		var ingredients = AltarRecipe.readIngredients(buf);
		ItemStackTemplate itemStack = ItemStackTemplate.STREAM_CODEC.decode(buf);
		return new AltarSimpleRecipe(ingredients, itemStack);
	}

	public static void toNetwork(RegistryFriendlyByteBuf buf, AltarSimpleRecipe recipe) {
		recipe.writeIngredients(buf);
		ItemStackTemplate.STREAM_CODEC.encode(buf, recipe.result);
	}


}
