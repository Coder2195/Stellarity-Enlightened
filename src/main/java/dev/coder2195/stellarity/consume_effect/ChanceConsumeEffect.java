package dev.coder2195.stellarity.consume_effect;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.coder2195.stellarity.registry.StellarityConsumeEffects;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.List;

public record ChanceConsumeEffect(List<ConsumeEffect> effects, double chance) implements ConsumeEffect {
	public static final MapCodec<ChanceConsumeEffect> CODEC = RecordCodecBuilder.mapCodec(i -> i
		.group(ConsumeEffect.CODEC.listOf().fieldOf("effects").forGetter(ChanceConsumeEffect::effects), Codec.DOUBLE.fieldOf("chance").forGetter(ChanceConsumeEffect::chance))
		.apply(i, ChanceConsumeEffect::new)
	);

	public static final StreamCodec<RegistryFriendlyByteBuf, ChanceConsumeEffect> STREAM_CODEC = StreamCodec.composite(
		ConsumeEffect.STREAM_CODEC.apply(ByteBufCodecs.list()), ChanceConsumeEffect::effects,
		ByteBufCodecs.DOUBLE, ChanceConsumeEffect::chance,
		ChanceConsumeEffect::new
	);

	public ChanceConsumeEffect(ConsumeEffect effect, double chance) {
		this(List.of(effect), chance);
	}

	@Override
	public @NonNull Type<? extends ConsumeEffect> getType() {
		return StellarityConsumeEffects.CHANCE;
	}

	@Override
	public boolean apply(@NonNull Level level, @NonNull ItemStack stack, @NonNull LivingEntity user) {
		RandomSource random = RandomSource.create();
		if (random.nextDouble() > chance) return false;

		for (var effect : effects) {
			effect.apply(level, stack, user);
		}
		return true;
	}
}
