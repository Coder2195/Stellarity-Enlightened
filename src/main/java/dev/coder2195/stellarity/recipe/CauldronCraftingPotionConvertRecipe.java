package dev.coder2195.stellarity.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.coder2195.stellarity.registry.StellarityRecipeSerializers;
import dev.coder2195.stellarity.util.CustomCodecs;
import dev.coder2195.stellarity.util.CustomStreamCodecs;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.codec.HolderSetCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;

public record CauldronCraftingPotionConvertRecipe(Ingredient potionItem, HolderSet<Potion> originalPotion, HashMap<Ingredient, Integer> ingredients, Holder<Potion> resultPotion) implements CauldronCraftingRecipe {
	public static final StreamCodec<RegistryFriendlyByteBuf, CauldronCraftingPotionConvertRecipe> STREAM_CODEC = StreamCodec.composite(
		Ingredient.CONTENTS_STREAM_CODEC, CauldronCraftingPotionConvertRecipe::potionItem,
		ByteBufCodecs.holderSet(Registries.POTION), CauldronCraftingPotionConvertRecipe::originalPotion,
		CustomStreamCodecs.INGREDIENTS_MAP, CauldronCraftingPotionConvertRecipe::ingredients,
		Potion.STREAM_CODEC, CauldronCraftingPotionConvertRecipe::resultPotion,
		CauldronCraftingPotionConvertRecipe::new
	);

	public static final MapCodec<CauldronCraftingPotionConvertRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
		Ingredient.CODEC.fieldOf("potion_item").forGetter(CauldronCraftingPotionConvertRecipe::potionItem),
		HolderSetCodec.create(Registries.POTION, Potion.CODEC, false).fieldOf("original_potion").forGetter(CauldronCraftingPotionConvertRecipe::originalPotion),
		CustomCodecs.INGREDIENT_MAP_CODEC.fieldOf("ingredients").forGetter(CauldronCraftingPotionConvertRecipe::ingredients),
		Potion.CODEC.fieldOf("result_potion").forGetter(CauldronCraftingPotionConvertRecipe::resultPotion)
	).apply(instance, CauldronCraftingPotionConvertRecipe::new));

	public CauldronCraftingPotionConvertRecipe(Ingredient potionItem, Holder<Potion> originalPotion, HashMap<Ingredient, Integer> ingredients, Holder<Potion> resultPotion) {
		this(potionItem, HolderSet.direct(originalPotion), ingredients, resultPotion);
	}

	public @Nullable ItemStack findPotion(ItemListInput input) {
		ItemStack targetPotion = null;
		for (int i = input.size() - 1; i >= 0; i--) {
			var testItem = input.getItem(i);
			var potionContents = testItem.get(DataComponents.POTION_CONTENTS);
			if (potionContents == null) continue;
			if (potionItem.test(testItem) && potionContents.potion().map(originalPotion::contains).orElse(false)) {
				targetPotion = input.remove(i);
				break;
			}
		}
		return targetPotion;
	}

	@Override
	public boolean matches(ItemListInput input, Level level) {

		if (findPotion(input) == null) return false;
		return CauldronCraftingRecipe.fulfillsIngredients(new HashMap<>(ingredients), input);
	}

	@Override
	public ItemStack assemble(ItemListInput input) {
		var targetStack = findPotion(input);
		if (targetStack == null) return ItemStack.EMPTY;

		targetStack.set(DataComponents.POTION_CONTENTS, new PotionContents(resultPotion));
		return targetStack;
	}

	@Override
	public RecipeSerializer<? extends Recipe<ItemListInput>> getSerializer() {
		return StellarityRecipeSerializers.CAULDRON_CRAFTING_POTION_CONVERT;
	}

}
