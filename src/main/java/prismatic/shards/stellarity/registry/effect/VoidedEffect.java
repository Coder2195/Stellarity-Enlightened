package prismatic.shards.stellarity.registry.effect;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.PowerParticleOption;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.Stellarity;


public class VoidedEffect extends MobEffect {
	public VoidedEffect() {
		super(MobEffectCategory.HARMFUL, 0x6a3885, PARTICLE);
		addAttributeModifier(Attributes.MAX_HEALTH, Stellarity.id("voided_effect"), -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
	}

	public static final ParticleOptions PARTICLE = PowerParticleOption.create(ParticleTypes.DRAGON_BREATH, 0);

	@Override
	public @NonNull ParticleOptions createParticleOptions(@NonNull MobEffectInstance mobEffectInstance) {
		return super.createParticleOptions(mobEffectInstance);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int i, int j) {
		return true;
	}

	@Override
	public boolean applyEffectTick(@NonNull ServerLevel level, LivingEntity livingEntity, int i) {
		if (livingEntity.getHealth() > livingEntity.getMaxHealth()) {
			livingEntity.setHealth(livingEntity.getMaxHealth());
		}

		return super.applyEffectTick(level, livingEntity, i);
	}

}
