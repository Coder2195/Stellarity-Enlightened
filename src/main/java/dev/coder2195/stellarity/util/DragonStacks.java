package dev.coder2195.stellarity.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record DragonStacks(short stacks, long expiresAt) {
	public static final Codec<DragonStacks> CODEC = RecordCodecBuilder.create(instance -> instance.group(
		Codec.SHORT.fieldOf("stacks").forGetter(DragonStacks::stacks),
		Codec.LONG.fieldOf("expires_at").forGetter(DragonStacks::expiresAt)
	).apply(instance, DragonStacks::new));

	public static final StreamCodec<ByteBuf, DragonStacks> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.SHORT, DragonStacks::stacks, ByteBufCodecs.VAR_LONG, DragonStacks::expiresAt, DragonStacks::new);
	public static final DragonStacks EMPTY = new DragonStacks((short) 0, 0);
}
