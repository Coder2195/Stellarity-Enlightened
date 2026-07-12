package dev.coder2195.stellarity.registry.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;
import dev.coder2195.stellarity.registry.StellarityEntityTypes;
import dev.coder2195.stellarity.registry.StellarityMobEffects;

public class VoidedSilverfish extends Silverfish {
	public VoidedSilverfish(EntityType<? extends VoidedSilverfish> type, Level level) {
		super(type, level);
	}

	public VoidedSilverfish(Level level) {
		this(StellarityEntityTypes.VOIDED_SILVERFISH, level);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Silverfish.createAttributes();
	}

	@Override
	public boolean doHurtTarget(@NonNull ServerLevel level, @NonNull Entity target) {
		if (!super.doHurtTarget(level, target)) return false;

		if (target instanceof LivingEntity entity) {
			entity.addEffect(new MobEffectInstance(StellarityMobEffects.VOIDED, 10 * 20));
		}

		return true;
	}
}
