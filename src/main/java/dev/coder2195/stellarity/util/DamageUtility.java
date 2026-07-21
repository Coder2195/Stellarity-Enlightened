package dev.coder2195.stellarity.util;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

import java.util.HashMap;


public interface DamageUtility {
	record DamageInfo(float armorPiercingRatio, float magicPiercingRatio) {}

	HashMap<Integer, DamageInfo> DAMAGE_INFO = new HashMap<>();

	static void damageEntity(ServerLevel serverLevel, LivingEntity entity, DamageSource damageSource, float damage, float armorPiercingRatio, float magicPiercingRatio) {
		DAMAGE_INFO.put(entity.getId(), new DamageInfo( Mth.clamp(armorPiercingRatio, 0f, 1f), Mth.clamp(magicPiercingRatio, 0f, 1f)));
		entity.hurtServer(serverLevel, damageSource, damage);
		DAMAGE_INFO.remove(entity.getId());
	}
}