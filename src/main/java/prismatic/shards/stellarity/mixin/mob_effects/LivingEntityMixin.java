package prismatic.shards.stellarity.mixin.mob_effects;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import prismatic.shards.stellarity.key.StellarityDamageTypes;
import prismatic.shards.stellarity.registry.StellarityMobEffects;
import prismatic.shards.stellarity.registry.StellaritySoundEvents;
import prismatic.shards.stellarity.util.DamageUtility;

import java.util.Collection;
import java.util.Map;


@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	@Shadow
	@Final
	private Map<Holder<MobEffect>, MobEffectInstance> activeEffects;

	@Unique
	private static DamageUtility damageUtility;

	@Unique
	private boolean appliedBrittle = false;

	public LivingEntityMixin(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}

	@WrapMethod(method = "hurtServer")
	private boolean applyBrittleEffect(ServerLevel level, DamageSource source, float damage, Operation<Boolean> original) {
		boolean hurt = original.call(level, source, damage);

		if (!activeEffects.containsKey(StellarityMobEffects.BRITTLE) || !hurt || appliedBrittle || source.is(StellarityDamageTypes.BRITTLE))
			return hurt;

		int amplifier = activeEffects.get(StellarityMobEffects.BRITTLE).getAmplifier();

		var sources = this.damageSources();

		if (damageUtility == null) damageUtility = DamageUtility.builder()
			.setDamageSource(sources.source(StellarityDamageTypes.BRITTLE))
			.setApDamageSource(sources.source(StellarityDamageTypes.BRITTLE))
			.setApRatio(0.4f)
			.build();

		setTicksFrozen(150);

		appliedBrittle = true;

		damageUtility.damageEntity((LivingEntity) (Object) this, (float) amplifier + 1);

		appliedBrittle = false;

		return true;
	}

	@Inject(method = "onEffectsRemoved", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffect;removeAttributeModifiers(Lnet/minecraft/world/entity/ai/attributes/AttributeMap;)V"))
	private void voidedRemoveSound(Collection<MobEffectInstance> effects, CallbackInfo ci, @Local(name = "effect") MobEffectInstance effect) {
		if (effect.is(StellarityMobEffects.VOIDED)) {
			level().playSound(null, blockPosition(), StellaritySoundEvents.VOIDED_DEACTIVATE, SoundSource.NEUTRAL);
		}
	}
}
