package dev.coder2195.stellarity.mixin_helper;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;
import dev.coder2195.stellarity.registry.StellarityItems;
import dev.coder2195.stellarity.registry.StellaritySoundEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public interface ArmorEffectsHelper {
	Identifier CHAMPION_MODIFIER = Stellarity.id("champion_armor");
	long CHAMPION_BOOST_DURATION = 3 * 20;
	long HOLY_PROTECTION_DODGE_COOLDOWN = 25 * 20;
	long HOLY_PROTECTION_DODGE_DURATION = 5 * 20;
	long HOLY_PROTECTION_MOVEMENT_SPEED_DURATION = 6 * 20;

	static boolean isFullShulkerArmor(LivingEntity livingEntity) {
		return livingEntity.getItemBySlot(EquipmentSlot.HEAD).is(StellarityItems.SHULKER_HELMET) &&
			livingEntity.getItemBySlot(EquipmentSlot.CHEST).is(StellarityItems.SHULKER_CHESTPLATE) &&
			livingEntity.getItemBySlot(EquipmentSlot.LEGS).is(StellarityItems.SHULKER_LEGGINGS) &&
			livingEntity.getItemBySlot(EquipmentSlot.FEET).is(StellarityItems.SHULKER_BOOTS);
	}

	static boolean isFullChampionArmor(LivingEntity livingEntity) {
		return livingEntity.getItemBySlot(EquipmentSlot.HEAD).is(StellarityItems.CHAMPION_HELMET) &&
			livingEntity.getItemBySlot(EquipmentSlot.CHEST).is(StellarityItems.CHAMPION_CHESTPLATE) &&
			livingEntity.getItemBySlot(EquipmentSlot.LEGS).is(StellarityItems.CHAMPION_LEGGINGS) &&
			livingEntity.getItemBySlot(EquipmentSlot.FEET).is(StellarityItems.CHAMPION_BOOTS);
	}

	static boolean isFullHallowedArmor(LivingEntity livingEntity) {
		return livingEntity.getItemBySlot(EquipmentSlot.HEAD).is(StellarityItems.HALLOWED_HELMET) &&
			livingEntity.getItemBySlot(EquipmentSlot.CHEST).is(StellarityItems.HALLOWED_CHESTPLATE) &&
			livingEntity.getItemBySlot(EquipmentSlot.LEGS).is(StellarityItems.HALLOWED_LEGGINGS) &&
			livingEntity.getItemBySlot(EquipmentSlot.FEET).is(StellarityItems.HALLOWED_BOOTS);
	}

	static void championIncreaseDamage(LivingEntity livingEntity, ServerLevel serverLevel) {
		var attackDamage = livingEntity.getAttribute(Attributes.ATTACK_DAMAGE);
		if (!isFullChampionArmor(livingEntity) || attackDamage == null) return;

		var modifier = attackDamage.getModifier(CHAMPION_MODIFIER);
		var amount = modifier == null ? 0.06 : modifier.amount() + 0.06;
		attackDamage.addOrReplacePermanentModifier(new AttributeModifier(CHAMPION_MODIFIER, amount, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

		amount = Math.min(amount, 0.3);
		serverLevel.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), StellaritySoundEvents.CHAMPION_ARMOR_ADD_DAMAGE, SoundSource.NEUTRAL, (float) (0.4 + amount * 2), (float) (0.6 + amount * 1.5));

		livingEntity.setAttached(StellarityDataAttachments.CHAMPION_BOOST_UNTIL, serverLevel.getGameTime() + CHAMPION_BOOST_DURATION);
	}
}
