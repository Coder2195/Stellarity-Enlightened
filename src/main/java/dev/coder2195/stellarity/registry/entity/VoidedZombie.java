package dev.coder2195.stellarity.registry.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;
import dev.coder2195.stellarity.registry.StellarityEntityTypes;
import dev.coder2195.stellarity.registry.StellarityMobEffects;

public class VoidedZombie extends Zombie {

	public VoidedZombie(EntityType<? extends VoidedZombie> type, Level level) {
		super(type, level);
	}

	public VoidedZombie(Level level) {
		this(StellarityEntityTypes.VOIDED_ZOMBIE, level);
	}

	public static AttributeSupplier.@NonNull Builder createAttributes() {
		return Zombie.createAttributes();
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
