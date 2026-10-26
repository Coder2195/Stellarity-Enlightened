package dev.coder2195.stellarity.util;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.util.tuple.Tuple2;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.Direction;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public interface CustomStreamCodecs {
	static <T extends Enum<T>> StreamCodec<ByteBuf, T> enumName(Class<T> clazz, T defaultValue) {
		return enumName(clazz, defaultValue, Enum::name, string -> Enum.valueOf(clazz, string.toUpperCase()));
	}

	static <T extends Enum<T>> StreamCodec<ByteBuf, T> enumName(Class<T> clazz, T defaultValue, Function<T, String> from, Function<String, @Nullable T> to) {
		return ByteBufCodecs.STRING_UTF8.map(s -> {
			try {
				var applied =  to.apply(s);
				return applied == null ? defaultValue : applied;
			} catch (Exception e) {
				Stellarity.LOGGER.error("encountered an error while trying to decode \"{}\" falling back to \"{}\": {}", s, defaultValue, e);
				return defaultValue;
			}
		}, from);
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
	StreamCodec<ByteBuf, Direction.Axis> AXIS = enumName(Direction.Axis.class, Direction.Axis.X, Direction.Axis::getName, Direction.Axis::byName);
}
