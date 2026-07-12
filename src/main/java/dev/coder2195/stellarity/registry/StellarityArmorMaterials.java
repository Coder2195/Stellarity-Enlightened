package dev.coder2195.stellarity.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import dev.coder2195.stellarity.Stellarity;

import static net.minecraft.world.item.equipment.ArmorMaterials.makeDefense;

public interface StellarityArmorMaterials {
	ArmorMaterial SHULKER = new ArmorMaterial(
		37, makeDefense(3, 6, 8, 4, 20), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 4.0F, 0.2F, ItemTags.REPAIRS_NETHERITE_ARMOR, StellarityEquipmentAssets.SHULKER
	);

	static ItemAttributeModifiers createShulkerAttributes(ArmorMaterial material, final ArmorType type) {
		return material.createAttributes(type).withModifierAdded(Attributes.MOVEMENT_SPEED, new AttributeModifier(
			Stellarity.mcId("armor." + type.getName()),
			-0.03,
			AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
		), EquipmentSlotGroup.bySlot(type.getSlot())).withModifierAdded(Attributes.ATTACK_SPEED, new AttributeModifier(
			Stellarity.mcId("armor." + type.getName()),
			-0.03,
			AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
		), EquipmentSlotGroup.bySlot(type.getSlot()));
	}


}
