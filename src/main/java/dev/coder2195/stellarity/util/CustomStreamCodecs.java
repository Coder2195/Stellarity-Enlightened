package dev.coder2195.stellarity.util;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public interface CustomStreamCodecs {
	static <T extends Enum<T>> StreamCodec<? super RegistryFriendlyByteBuf, T> enumName(Class<T> enumClass, T defaultValue) {
		return ByteBufCodecs.STRING_UTF8.map(s -> T.valueOf(enumClass, s.toUpperCase()), Enum::name);
	}
}
