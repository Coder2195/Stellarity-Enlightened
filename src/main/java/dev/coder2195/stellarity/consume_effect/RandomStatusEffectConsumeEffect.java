package dev.coder2195.stellarity.consume_effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.coder2195.stellarity.registry.StellarityConsumeEffects;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.List;

public record RandomStatusEffectConsumeEffect(List<MobEffectInstance> effects) implements ConsumeEffect {

	public static final MapCodec<RandomStatusEffectConsumeEffect> CODEC = RecordCodecBuilder.mapCodec(i -> i
		.group(MobEffectInstance.CODEC.listOf().fieldOf("effects").forGetter(RandomStatusEffectConsumeEffect::effects))
		.apply(i, RandomStatusEffectConsumeEffect::new)
	);

	public static final StreamCodec<RegistryFriendlyByteBuf, RandomStatusEffectConsumeEffect> STREAM_CODEC = StreamCodec.composite(
		MobEffectInstance.STREAM_CODEC.apply(ByteBufCodecs.list()), RandomStatusEffectConsumeEffect::effects,
		RandomStatusEffectConsumeEffect::new
	);

	@Override
	public @NonNull Type<? extends ConsumeEffect> getType() {
		return StellarityConsumeEffects.RANDOM_EFFECT;
	}

	@Override
	public boolean apply(@NonNull Level level, @NonNull ItemStack stack, LivingEntity user) {
		RandomSource random = RandomSource.create();

		return user.addEffect(effects.get(random.nextInt(effects.size())));
	}
}
