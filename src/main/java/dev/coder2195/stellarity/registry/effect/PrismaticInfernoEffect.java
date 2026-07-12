package dev.coder2195.stellarity.registry.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class PrismaticInfernoEffect extends MobEffect {
	public PrismaticInfernoEffect() {
		super(MobEffectCategory.HARMFUL, 0xff4d00);
	}


	@Override

	public boolean applyEffectTick(@NonNull ServerLevel serverLevel, LivingEntity livingEntity, int amplifier) {

		Level level = livingEntity.level();
		if (level.isClientSide()) return false;
		boolean isInDaylight = level.canSeeSky(livingEntity.blockPosition()) &&
			level.dimension() == Level.OVERWORLD &&
			(!level.isRaining() && !level.isThundering() && level.isBrightOutside());

		boolean isUndead = livingEntity.is(EntityTypeTags.UNDEAD);

		float damage = 1f;
		if (isUndead) damage *= 2;
		if (isInDaylight) damage *= 2;

		livingEntity.hurtServer(serverLevel, livingEntity.damageSources().inFire(), damage);
		livingEntity.setRemainingFireTicks(25);
		return true;
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return duration % 20 == 0;
	}
}