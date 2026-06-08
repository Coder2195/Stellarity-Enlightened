package prismatic.shards.stellarity.registry.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.registry.StellarityDataComponents;
import prismatic.shards.stellarity.registry.StellarityRecipeSerializers;
import prismatic.shards.stellarity.registry.data_component.Color;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public record AltarDyeRecipe(Ingredient item) implements AltarRecipe {

	public static final StreamCodec<RegistryFriendlyByteBuf, AltarDyeRecipe> STREAM_CODEC = StreamCodec.composite(Ingredient.CONTENTS_STREAM_CODEC, AltarDyeRecipe::item, AltarDyeRecipe::new);
	public static final MapCodec<AltarDyeRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
		Ingredient.CODEC.fieldOf("item").forGetter(AltarDyeRecipe::item)
	).apply(instance, AltarDyeRecipe::new));

	@Override
	public @Nullable Output craft(List<ItemStack> itemStacks) {
		if (itemStacks.size() < 2) return null;

		LinkedList<DyeColor> dyes = new LinkedList<>();
		ItemStack target = null;
		boolean waterBucket = false;

		for (ItemStack itemStack : itemStacks) {
			if (itemStack.is(Items.WATER_BUCKET)) {
				if (itemStack.count() != 1) return null;
				waterBucket = true;
				continue;
			}

			@Nullable DyeColor dye = itemStack.get(DataComponents.DYE);
			if (dye != null) {
				for (int i = 0; i < itemStack.count(); i++) dyes.add(dye);
				continue;
			}

			if (target != null || !item.test(itemStack) || itemStack.count() != 1) return null;
			target = itemStack.copy();
		}

		if (waterBucket) {
			if (!dyes.isEmpty() || target == null || !target.has(StellarityDataComponents.COLOR)) return null;

			target.remove(StellarityDataComponents.COLOR);

			return new Output(new HashMap<>(), target, new ItemStack(Items.BUCKET));
		}

		if (target == null || dyes.isEmpty()) return null;

		Color dyedItemColor = target.get(StellarityDataComponents.COLOR);
		if (dyedItemColor == null) dyedItemColor = new Color(dyes.removeFirst().getTextureDiffuseColor());

		target.set(StellarityDataComponents.COLOR, Color.applyDyes(dyedItemColor, dyes));

		return new Output(new HashMap<>(), target);
	}

	@Override
	public HashMap<Ingredient, Integer> ingredients() {
		return new HashMap<>();
	}

	@Override
	public @NonNull RecipeSerializer<? extends Recipe<Input>> getSerializer() {
		return StellarityRecipeSerializers.ALTAR_DYE;
	}
}
