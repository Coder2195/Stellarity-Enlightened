package dev.coder2195.stellarity.mixin.damage_util;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.coder2195.stellarity.util.DamageUtility;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	public LivingEntityMixin(EntityType<?> type, Level level) {
		super(type, level);
	}

	@WrapOperation(method = "getDamageAfterArmorAbsorb", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/damagesource/CombatRules;getDamageAfterAbsorb(Lnet/minecraft/world/entity/LivingEntity;FLnet/minecraft/world/damagesource/DamageSource;FF)F"))
	private float armorPiercing(LivingEntity victim, float damage, DamageSource source, float totalArmor, float armorToughness, Operation<Float> original) {
		var damageInfo = DamageUtility.DAMAGE_INFO.get(victim.getId());
		if (damageInfo == null || damageInfo.armorPiercingRatio() <= 0) return original.call(victim, damage, source, totalArmor, armorToughness);
		var piercingRatio = damageInfo.armorPiercingRatio();
		var nonPiercingRatio = 1 - piercingRatio;
		return damage * piercingRatio + nonPiercingRatio * original.call(victim, damage, source, totalArmor, armorToughness);
	}

	@WrapOperation(method = "getDamageAfterMagicAbsorb", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F"))
	private float magicPiercing(float a, float b, Operation<Float> original, @Local(name = "oldDamage") float oldDamage) {
		var damageInfo = DamageUtility.DAMAGE_INFO.get(getId());
		if (damageInfo == null || damageInfo.magicPiercingRatio() <= 0) return original.call(a, b);
		var piercingRatio = damageInfo.magicPiercingRatio();
		var nonPiercingRatio = 1 - piercingRatio;
		return piercingRatio * oldDamage + nonPiercingRatio * original.call(a, b);
	}

	@WrapOperation(method = "getDamageAfterMagicAbsorb", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/damagesource/CombatRules;getDamageAfterMagicAbsorb(FF)F"))
	private float armorPiercing2(float damage, float totalMagicArmor, Operation<Float> original) {
		var damageInfo = DamageUtility.DAMAGE_INFO.get(getId());
		if (damageInfo == null || damageInfo.armorPiercingRatio() <= 0) return original.call(damage, totalMagicArmor);
		var piercingRatio = damageInfo.armorPiercingRatio();
		var nonPiercingRatio = 1 - piercingRatio;
		return damage * piercingRatio + nonPiercingRatio * original.call(damage, totalMagicArmor);
	}
}
