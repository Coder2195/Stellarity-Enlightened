package prismatic.shards.stellarity.registry.item;


import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.fox.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.StellarityBlocks;
import prismatic.shards.stellarity.registry.StellarityItems;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Duskberry extends BlockItem {

	public Duskberry(Properties properties) {
		super(StellarityBlocks.DUSKBERRY_BUSH, properties);
	}

	public static MobEffectInstance[] debuffs(int level) {
		if (level == 0) return new MobEffectInstance[0];
		return Stream.of(MobEffects.DARKNESS, MobEffects.SLOWNESS, MobEffects.MINING_FATIGUE, MobEffects.WEAKNESS, MobEffects.NAUSEA).map((e) -> new MobEffectInstance(e, level * 12 * 20)).toArray(MobEffectInstance[]::new);
	}


	@Override
	public void inventoryTick(@NonNull ItemStack itemStack, @NonNull ServerLevel serverLevel, @NonNull Entity entity, @Nullable EquipmentSlot equipmentSlot) {
		inventoryTick(itemStack, serverLevel, entity);
	}


	public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity entity) {
		if (!(entity instanceof Player player)) return;

		var effect = player.getEffect(MobEffects.NIGHT_VISION);
		if (effect != null && !effect.endsWithin(11 * 20)) return;

		for (ItemStack held : new ItemStack[]{player.getMainHandItem(), player.getOffhandItem()}) {
			if (!held.is(this)) continue;

			player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 15 * 20));
			return;
		}
	}


	public static final Properties PROPERTIES = StellarityItems.foodProperties(2, 0.4f, Arrays.stream(debuffs(3)).map(StellarityItems.EffectChance::new).toArray(StellarityItems.EffectChance[]::new))
		.attributes(new ItemAttributeModifiers(List.of(
			new ItemAttributeModifiers.Entry(
				Attributes.ATTACK_DAMAGE, new AttributeModifier(Stellarity.id("duskberry_attack_damage"), 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.HAND
			),
			new ItemAttributeModifiers.Entry(
				Attributes.MOVEMENT_SPEED, new AttributeModifier(Stellarity.id("duskberry_movement_speed"), 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.HAND
			),
			new ItemAttributeModifiers.Entry(
				Attributes.ATTACK_SPEED, new AttributeModifier(Stellarity.id("duskberry_attack_speed"), 0.12, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.HAND
			),
			new ItemAttributeModifiers.Entry(
				Attributes.SWEEPING_DAMAGE_RATIO, new AttributeModifier(Stellarity.id("duskberry_sweeping_damage"), 0.2, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HAND
			),
			new ItemAttributeModifiers.Entry(
				Attributes.MOVEMENT_EFFICIENCY, new AttributeModifier(Stellarity.id("duskberry_movement_efficiency"), 0.25, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.HAND
			)
		)))
		.component(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT.withHidden(DataComponents.ATTRIBUTE_MODIFIERS, true))
		.rarity(Rarity.EPIC);

	@Override
	public @NonNull InteractionResult interactLivingEntity(@NonNull ItemStack itemStack, @NonNull Player player, @NonNull LivingEntity livingEntity, @NonNull InteractionHand interactionHand) {
		if (livingEntity instanceof Fox fox) {
			fox.addEffect(new MobEffectInstance(MobEffects.POISON, 20 * 20), player);
			return InteractionResult.SUCCESS;
		}
		return super.interactLivingEntity(itemStack, player, livingEntity, interactionHand);
	}
}
