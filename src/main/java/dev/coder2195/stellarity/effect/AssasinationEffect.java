package dev.coder2195.stellarity.effect;

import dev.coder2195.stellarity.Stellarity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class AssasinationEffect extends MobEffect {
	public AssasinationEffect(MobEffectCategory category, int color) {
		super(category, color);
		this
			.addAttributeModifier(Attributes.ATTACK_DAMAGE, Stellarity.id("effect.assasination"), 3, AttributeModifier.Operation.ADD_VALUE)
			.addAttributeModifier(Attributes.MOVEMENT_SPEED, Stellarity.id("effect.assasination"), 0.35f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
			.addAttributeModifier(Attributes.SWEEPING_DAMAGE_RATIO, Stellarity.id("effect.assasination"), 0.6f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
	}

	@Override
	public void onEffectStarted(LivingEntity mob, int amplifier) {
		super.onEffectStarted(mob, amplifier);

		mob.setAbsorptionAmount(Math.max(mob.getAbsorptionAmount(), (float)(4 * (1 + amplifier))));
	}
}
