package dev.coder2195.stellarity.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.coder2195.stellarity.registry.StellarityRecipeSerializers;
import dev.coder2195.stellarity.util.CustomCodecs;
import dev.coder2195.stellarity.util.CustomStreamCodecs;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import java.util.HashMap;

public record CauldronCraftingSimpleRecipe(HashMap<Ingredient, Integer> ingredients,
																					 ItemStackTemplate result) implements CauldronCraftingRecipe {
	
	@Override
	public boolean matches(ItemListInput input, Level level) {
		return CauldronCraftingRecipe.fulfillsIngredients(new HashMap<>(ingredients), input);
	}

	@Override
	public ItemStack assemble(ItemListInput input) {
		return result.create();
	}

	@Override
	public RecipeSerializer<? extends Recipe<ItemListInput>> getSerializer() {
		return StellarityRecipeSerializers.CAULDRON_CRAFTING_SIMPLE;
	}

	public static final StreamCodec<RegistryFriendlyByteBuf, CauldronCraftingSimpleRecipe> STREAM_CODEC = StreamCodec.composite(CustomStreamCodecs.INGREDIENTS_MAP, CauldronCraftingSimpleRecipe::ingredients, ItemStackTemplate.STREAM_CODEC, CauldronCraftingSimpleRecipe::result, CauldronCraftingSimpleRecipe::new);


	public static final MapCodec<CauldronCraftingSimpleRecipe> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			CustomCodecs.INGREDIENT_MAP_CODEC.fieldOf("ingredients").forGetter(CauldronCraftingSimpleRecipe::ingredients),
			ItemStackTemplate.CODEC.fieldOf("result").forGetter(CauldronCraftingSimpleRecipe::result)
		).apply(instance, CauldronCraftingSimpleRecipe::new));
}
