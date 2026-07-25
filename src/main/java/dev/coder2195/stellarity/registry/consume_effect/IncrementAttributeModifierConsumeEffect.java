package dev.coder2195.stellarity.registry.consume_effect;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.coder2195.stellarity.registry.StellarityConsumeEffects;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public record IncrementAttributeModifierConsumeEffect(Holder<Attribute> attributeHolder, Identifier modifier, AttributeModifier.Operation operation, double incrementAmount, Optional<Double> maxAmount) implements ConsumeEffect {
	public static final MapCodec<IncrementAttributeModifierConsumeEffect> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
		Attribute.CODEC.fieldOf("attribute").forGetter(IncrementAttributeModifierConsumeEffect::attributeHolder),
		Identifier.CODEC.fieldOf("modifier").forGetter(IncrementAttributeModifierConsumeEffect::modifier),
		AttributeModifier.Operation.CODEC.optionalFieldOf("operation", AttributeModifier.Operation.ADD_VALUE).forGetter(IncrementAttributeModifierConsumeEffect::operation),
		Codec.DOUBLE.optionalFieldOf("increment_amount", 1d).forGetter(IncrementAttributeModifierConsumeEffect::incrementAmount),
		Codec.DOUBLE.optionalFieldOf("max_amount").forGetter(IncrementAttributeModifierConsumeEffect::maxAmount)
	).apply(i, IncrementAttributeModifierConsumeEffect::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, IncrementAttributeModifierConsumeEffect> STREAM_CODEC = StreamCodec.composite(
		Attribute.STREAM_CODEC, IncrementAttributeModifierConsumeEffect::attributeHolder,
		Identifier.STREAM_CODEC, IncrementAttributeModifierConsumeEffect::modifier,
		AttributeModifier.Operation.STREAM_CODEC, IncrementAttributeModifierConsumeEffect::operation,
		ByteBufCodecs.DOUBLE, IncrementAttributeModifierConsumeEffect::incrementAmount,
		ByteBufCodecs.optional(ByteBufCodecs.DOUBLE), IncrementAttributeModifierConsumeEffect::maxAmount,
		IncrementAttributeModifierConsumeEffect::new
	);

	@Override
	public @NonNull Type<? extends ConsumeEffect> getType() {
		return StellarityConsumeEffects.INCREMENT_ATTRIBUTE_MODIFIER;
	}

	@Override
	public boolean apply(@NonNull Level level, @NonNull ItemStack stack, LivingEntity user) {
		AttributeInstance maxHPAttribute = user.getAttributes().getInstance(attributeHolder);
		if (maxHPAttribute == null) return false;

		AttributeModifier oldModifier = maxHPAttribute.getModifier(modifier);

		double amount = (oldModifier == null ? 0 : oldModifier.amount()) + incrementAmount;

		if (maxAmount.isPresent() && amount > maxAmount.get()) return false;

		AttributeModifier newModifier = new AttributeModifier(modifier, amount, operation);

		if (oldModifier != null) maxHPAttribute.removeModifier(oldModifier);

		maxHPAttribute.addPermanentModifier(newModifier);

		return true;
	}
}
