package dev.coder2195.stellarity.util;

import dev.coder2195.stellarity.util.tuple.Tuple2;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.List;

public interface CustomStreamCodecs {
	static <T extends Enum<T>> StreamCodec<? super RegistryFriendlyByteBuf, T> enumName(Class<T> enumClass, T defaultValue) {
		return ByteBufCodecs.STRING_UTF8.map(s -> {
			try {
				return T.valueOf(enumClass, s.toUpperCase());
			} catch (Exception e) {
				return defaultValue;
			}
		}, Enum::name);
	}

	static HashMap<Ingredient, Integer> readIngredients(RegistryFriendlyByteBuf buf) {
		int size = buf.readInt();
		HashMap<Ingredient, Integer> ingredients = new HashMap<>();
		for (int i = 0; i < size; i++) {

			Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
			int count = buf.readInt();
			ingredients.put(ingredient, count);
		}
		return ingredients;
	}

	static void writeIngredients(RegistryFriendlyByteBuf buf, HashMap<Ingredient, Integer> ingredients) {
		buf.writeInt(ingredients.size());
		for (var entry : ingredients.entrySet()) {
			Ingredient.CONTENTS_STREAM_CODEC.encode(buf, entry.getKey());
			buf.writeInt(entry.getValue());
		}
	}

	StreamCodec<RegistryFriendlyByteBuf, HashMap<Ingredient, Integer>> INGREDIENTS_MAP = StreamCodec.of(CustomStreamCodecs::writeIngredients, CustomStreamCodecs::readIngredients);
	StreamCodec<RegistryFriendlyByteBuf, List<Tuple2<ItemStackTemplate, Vec3>>> ITEM_STACK_TEMPLATE_VEC_3_TUPLES = Tuple2.streamCodec(ItemStackTemplate.STREAM_CODEC, Vec3.STREAM_CODEC).apply(ByteBufCodecs.list());
}
