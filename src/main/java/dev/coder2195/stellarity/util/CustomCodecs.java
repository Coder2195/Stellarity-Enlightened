package dev.coder2195.stellarity.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.PrimitiveCodec;

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
