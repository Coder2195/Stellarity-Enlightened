package dev.coder2195.stellarity.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.coder2195.stellarity.interface_injection.ExtItemEntity;
import dev.coder2195.stellarity.registry.StellarityRecipeBookCategories;
import dev.coder2195.stellarity.registry.StellarityRecipeSerializers;
import dev.coder2195.stellarity.registry.StellarityRecipeTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public record ConsecrationRecipe(Ingredient item, ItemStackTemplate result) implements Recipe<ConsecrationRecipe.Input>{
	public static final MapCodec<ConsecrationRecipe> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
		Ingredient.CODEC.fieldOf("item").forGetter(ConsecrationRecipe::item),
		ItemStackTemplate.CODEC.fieldOf("result").forGetter(ConsecrationRecipe::result)
	).apply(i, ConsecrationRecipe::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, ConsecrationRecipe> STREAM_CODEC = StreamCodec.composite(
		Ingredient.CONTENTS_STREAM_CODEC, ConsecrationRecipe::item,
		ItemStackTemplate.STREAM_CODEC, ConsecrationRecipe::result,
		ConsecrationRecipe::new
	);

	public record ConsecrationData(ItemStack itemStack, long consecratesAt) {
		public static final int CONSECRATION_DURATION = 20 * 2;
		public static final Codec<ConsecrationData> CODEC = RecordCodecBuilder.create(i -> i.group(
			ItemStack.CODEC.fieldOf("item_stack").forGetter(ConsecrationData::itemStack),
			Codec.LONG.fieldOf("consecrates_at").forGetter(ConsecrationData::consecratesAt)
		).apply(i, ConsecrationData::new));

		public static final StreamCodec<RegistryFriendlyByteBuf, ConsecrationData> STREAM_CODEC = StreamCodec.composite(
			ItemStack.STREAM_CODEC, ConsecrationData::itemStack,
			ByteBufCodecs.LONG, ConsecrationData::consecratesAt,
			ConsecrationData::new
		);
	}

	public record Input(ItemStack itemStack) implements RecipeInput {

		@Override
		public ItemStack getItem(int index) {
			return itemStack;
		}

		@Override
		public int size() {
			return 1;
		}
	}

	@Override
	public String group() {
		return "";
	}

	@Override
	public RecipeSerializer<? extends Recipe<Input>> getSerializer() {
		return StellarityRecipeSerializers.CONSECRATION;
	}

	@Override
	public RecipeBookCategory recipeBookCategory() {
		return StellarityRecipeBookCategories.CONSECRATION;
	}

	@Override
	public boolean showNotification() {
		return true;
	}

	@Override
	public boolean matches(Input input, Level level) {
		return item.test(input.itemStack);
	}

	@Override
	public ItemStack assemble(Input input) {
		var itemStack = input.itemStack;

		return result.apply(itemStack.count(), itemStack.getComponentsPatch());
	}

	public void apply(ItemEntity itemEntity) {
		var input = new Input(itemEntity.getItem());
		itemEntity.stellarity$setConsecrationData(new ConsecrationData(assemble(input), itemEntity.level().getGameTime() + ConsecrationData.CONSECRATION_DURATION));
		itemEntity.stellarity$setItemMode(ExtItemEntity.ItemMode.CONSECRATING);
	}

	@Override
	public RecipeType<? extends Recipe<Input>> getType() {
		return StellarityRecipeTypes.CONSECRATION;
	}

	@Override
	public PlacementInfo placementInfo() {
		return PlacementInfo.NOT_PLACEABLE;
	}

}
