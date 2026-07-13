package dev.coder2195.stellarity.registry;

import com.mojang.serialization.MapCodec;
import dev.coder2195.stellarity.registry.consume_effect.LoafOfPlentyConsumeEffect;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.consume_effects.ConsumeEffect;

public interface StellarityConsumeEffects {
	ConsumeEffect.Type<LoafOfPlentyConsumeEffect> LOAF_OF_PLENTY = register("loaf_of_plenty", LoafOfPlentyConsumeEffect.CODEC, LoafOfPlentyConsumeEffect.STREAM_CODEC);

	static <T extends ConsumeEffect> ConsumeEffect.Type<T> register(final String name, final MapCodec<T> codec, final StreamCodec<RegistryFriendlyByteBuf, T> streamCodec) {
		return Registry.register(BuiltInRegistries.CONSUME_EFFECT_TYPE, name, new ConsumeEffect.Type<>(codec, streamCodec));
	}
}
