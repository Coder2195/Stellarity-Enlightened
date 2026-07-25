package dev.coder2195.stellarity.registry.consume_effect;

import com.mojang.serialization.MapCodec;
import dev.coder2195.stellarity.registry.StellarityConsumeEffects;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class RemoveHarmfulStatusEffectsConsumeEffect implements ConsumeEffect {
	public static final RemoveHarmfulStatusEffectsConsumeEffect INSTANCE = new RemoveHarmfulStatusEffectsConsumeEffect();

	public static final MapCodec<RemoveHarmfulStatusEffectsConsumeEffect> CODEC = MapCodec.unit(INSTANCE);
	public static final StreamCodec<RegistryFriendlyByteBuf, RemoveHarmfulStatusEffectsConsumeEffect> STREAM_CODEC = StreamCodec.unit(INSTANCE);
	@Override
	public @NonNull Type<? extends ConsumeEffect> getType() {
		return StellarityConsumeEffects.REMOVE_HARMFUL_EFFECTS;
	}

	@Override
	public boolean apply(@NonNull Level level, @NonNull ItemStack stack, @NonNull LivingEntity user) {
		var effects = user.getActiveEffects().stream().toList();

		for (var effect : effects) {
			if (effect.getEffect().value().getCategory() == MobEffectCategory.HARMFUL) {
				user.removeEffect(effect.getEffect());
			}
		}

		return true;
	}
}
