package dev.coder2195.stellarity.effect;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.StellaritySoundEvents;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.PowerParticleOption;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;


public class VoidedEffect extends MobEffect {
	public VoidedEffect() {
		super(MobEffectCategory.HARMFUL, 0x6a3885, PARTICLE);
		addAttributeModifier(Attributes.MAX_HEALTH, Stellarity.id("voided_effect"), -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
		withSoundOnAdded(StellaritySoundEvents.VOIDED_ACTIVATE);
	}

	public static final ParticleOptions PARTICLE = PowerParticleOption.create(ParticleTypes.DRAGON_BREATH, 0);

	@Override
	public ParticleOptions createParticleOptions(MobEffectInstance mobEffectInstance) {
		return super.createParticleOptions(mobEffectInstance);
	}

	@Override
	public void onEffectAdded(LivingEntity mob, int amplifier) {
		super.onEffectAdded(mob, amplifier);

		if (mob.getHealth() > mob.getMaxHealth()) {
			mob.setHealth(mob.getMaxHealth());
		}
	}

	@Override
	public void onEffectRemoved(MobEffectInstance effectInstance, LivingEntity entity) {
		super.onEffectRemoved(effectInstance, entity);
		entity.level().playSound(null, entity.blockPosition(), StellaritySoundEvents.VOIDED_DEACTIVATE, entity.getSoundSource(), 1, 1);
	}

}
