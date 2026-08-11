package dev.coder2195.stellarity.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

// attached to arrows, it adds
// attached to living entities it does the explode thingy
public record FloralBloom(float damage, long explodeAt) {
	public static final Codec<FloralBloom> CODEC = RecordCodecBuilder.create(instance -> instance.group(
		Codec.FLOAT.fieldOf("damage").forGetter(FloralBloom::damage),
		Codec.LONG.fieldOf("explode_at").forGetter(FloralBloom::explodeAt)
	).apply(instance, FloralBloom::new));
	public static final StreamCodec<RegistryFriendlyByteBuf, FloralBloom> STREAM_CODEC = StreamCodec.composite(
		ByteBufCodecs.FLOAT, FloralBloom::damage,
		ByteBufCodecs.VAR_LONG, FloralBloom::explodeAt,
		FloralBloom::new
	);

	public FloralBloom withDamage(float damage) {
		return new FloralBloom(damage, explodeAt);
	}

	public FloralBloom withExplodeTime(long explodeTime) {
		return new FloralBloom(damage, explodeTime);
	}

	public record Applier(float baseDamage, float damageStack, long baseExplodeDelay, long explodeDelayStack) {
		public static final Codec<Applier> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			Codec.FLOAT.fieldOf("base_damage").forGetter(Applier::baseDamage),
			Codec.FLOAT.fieldOf("damage_stack").forGetter(Applier::damageStack),
			Codec.LONG.fieldOf("base_explode_delay").forGetter(Applier::baseExplodeDelay),
			Codec.LONG.fieldOf("explode_delay_stack").forGetter(Applier::explodeDelayStack)
		).apply(instance, Applier::new));
	}
}
