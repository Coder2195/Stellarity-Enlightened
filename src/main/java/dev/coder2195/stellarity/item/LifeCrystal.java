package dev.coder2195.stellarity.item;

import dev.coder2195.stellarity.mixin.accessor.LivingEntityAccessor;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;
import dev.coder2195.stellarity.registry.StellarityMobEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class LifeCrystal extends Item {
	public LifeCrystal(Properties properties) {
		super(properties);
	}
	
	public static final Properties PROPERTIES = new Item.Properties().stacksTo(1).rarity(Rarity.RARE);
	public static final int LEVEL_DURATION = 2 * 20;

	@Override
	public void inventoryTick(@NonNull ItemStack itemStack, @NonNull ServerLevel level, @NonNull Entity owner, @Nullable EquipmentSlot slot) {
		super.inventoryTick(itemStack, level, owner, slot);

		// other logic in life_crystal.LivingEntityMixin
		if (slot == null || !(owner instanceof LivingEntity livingOwner && EquipmentSlotGroup.HAND.test(slot))) return;

		var heldAt = livingOwner.getAttached(StellarityDataAttachments.LIFE_CRYSTAL_HELD_AT);
		long gameTime = level.getGameTime();
		var effect = livingOwner.getEffect(StellarityMobEffects.LIFE_CRYSTAL_REGENERATION);

		if (heldAt == null) {
			livingOwner.setAttached(StellarityDataAttachments.LIFE_CRYSTAL_HELD_AT, gameTime);
			heldAt = gameTime;
		}

		if (gameTime - ((LivingEntityAccessor) livingOwner).stellarity$getLastDamageStamp() < 10) return;

		var durationSince = gameTime - heldAt - 10;
		if (durationSince < 0) return;
		var correctAmplifier = (int) Mth.clamp(durationSince / LEVEL_DURATION, 0, 3);

		if (effect == null || effect.getAmplifier() != correctAmplifier) livingOwner.addEffect(new MobEffectInstance(StellarityMobEffects.LIFE_CRYSTAL_REGENERATION, LEVEL_DURATION + 10, correctAmplifier));
	}
}
