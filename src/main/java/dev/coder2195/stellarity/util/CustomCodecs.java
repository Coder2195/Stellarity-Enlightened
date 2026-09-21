package dev.coder2195.stellarity.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.PrimitiveCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public interface CustomCodecs {
	PrimitiveCodec<UUID> UUID = new PrimitiveCodec<>() {
		@Override
		public <T> DataResult<UUID> read(DynamicOps<T> ops, T input) {
			return ops.getStringValue(input).map(java.util.UUID::fromString);
		}

		@Override
		public <T> T write(DynamicOps<T> ops, UUID value) {
			return ops.createString(value.toString());
		}
	};

	MapCodec<Map.Entry<Ingredient, Integer>> INGREDIENT_MAP_ENTRY_CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			Ingredient.CODEC.fieldOf("ingredient").forGetter(Map.Entry::getKey),
			Codec.INT.optionalFieldOf("count", 1).forGetter(Map.Entry::getValue)
		).apply(instance, Map::entry)
	);

	Codec<HashMap<Ingredient, Integer>> INGREDIENT_MAP_CODEC =  INGREDIENT_MAP_ENTRY_CODEC.codec().listOf().xmap(
		(entries) -> {
			HashMap<Ingredient, Integer> hashMap = new HashMap<>();
			entries.forEach(e -> hashMap.put(e.getKey(), e.getValue()));
			return hashMap;
		}, (map) -> {
			return map.entrySet().stream().toList();
		}
	);

	static <T extends Enum<T>> Codec<T> enumName(Class<T> enumClass, T defaultValue) {
		return PrimitiveCodec.STRING.xmap(name -> {
			try {
				return T.valueOf(enumClass, name.toUpperCase());
			} catch (IllegalArgumentException e) {
				return defaultValue;
			}
		}, Enum::name);
	}
}
