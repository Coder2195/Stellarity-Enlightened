package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.tags.StellarityItemTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import org.jspecify.annotations.NonNull;

import static net.minecraft.world.item.equipment.ArmorMaterials.makeDefense;

public interface StellarityArmorMaterials {
	ArmorMaterial SHULKER = new ArmorMaterial(
		37, makeDefense(3, 6, 8, 4, 20), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 4.0F, 0.2F, StellarityItemTags.REPAIRS_SHULKER_ARMOR, StellarityEquipmentAssets.SHULKER
	) {
		@Override
		public @NonNull ItemAttributeModifiers createAttributes(@NonNull ArmorType type) {
			return super.createAttributes(type).withModifierAdded(Attributes.MOVEMENT_SPEED, new AttributeModifier(
				Stellarity.id("armor." + type.getName()),
				-0.03,
				AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
			), EquipmentSlotGroup.bySlot(type.getSlot())).withModifierAdded(Attributes.ATTACK_SPEED, new AttributeModifier(
				Stellarity.id("armor." + type.getName()),
				-0.03,
				AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
			), EquipmentSlotGroup.bySlot(type.getSlot()));
		}
	};

	ArmorMaterial REINFORCED = new ArmorMaterial(
		37, makeDefense(3, 6, 8, 4, 20), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 6.0F, 0.2F, StellarityItemTags.REPAIRS_REINFORCED_ARMOR, StellarityEquipmentAssets.REINFORCED
	) {
		@Override
		public @NonNull ItemAttributeModifiers createAttributes(@NonNull ArmorType type) {
			return super.createAttributes(type).withModifierAdded(Attributes.MOVEMENT_SPEED, new AttributeModifier(
				Stellarity.id("armor." + type.getName()),
				0.1,
				AttributeModifier.Operation.ADD_MULTIPLIED_BASE
			), EquipmentSlotGroup.bySlot(type.getSlot())).withModifierAdded(Attributes.MOVEMENT_EFFICIENCY, new AttributeModifier(
				Stellarity.id("armor." + type.getName()),
				0.5,
				AttributeModifier.Operation.ADD_VALUE
			), EquipmentSlotGroup.bySlot(type.getSlot()));
		}
	};

	// durability doesn't matter, custom durability
	ArmorMaterial CHAMPION = new ArmorMaterial(30, makeDefense(3, 6, 8, 3, 20), 9, SoundEvents.ARMOR_EQUIP_NETHERITE, 2F, 0.08F, StellarityItemTags.REPAIRS_CHAMPION_ARMOR, StellarityEquipmentAssets.CHAMPION) {
		@Override
		public @NonNull ItemAttributeModifiers createAttributes(@NonNull ArmorType type) {
			return super.createAttributes(type).withModifierAdded(Attributes.ATTACK_DAMAGE, new AttributeModifier(
				Stellarity.id("armor." + type.getName()), 0.025, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
			), EquipmentSlotGroup.bySlot(type.getSlot()));
		}
	};

	ArmorMaterial HALLOWED = new ArmorMaterial(37, makeDefense(3, 6, 8, 3, 20), 9, SoundEvents.ARMOR_EQUIP_NETHERITE, 3, 0.1F, StellarityItemTags.REPAIRS_HALLOWED_ARMOR, StellarityEquipmentAssets.HALLOWED) {
		@Override
		public @NonNull ItemAttributeModifiers createAttributes(@NonNull ArmorType type) {
			var parentAttrs = super.createAttributes(type);
			var id =Stellarity.id("armor." + type.getName());
			if (type.equals(ArmorType.BOOTS)) return parentAttrs
				.withModifierAdded(Attributes.MOVEMENT_SPEED, new AttributeModifier(id, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.FEET)
				.withModifierAdded(Attributes.STEP_HEIGHT, new AttributeModifier(id, 0.5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.FEET);
			if (type.equals(ArmorType.LEGGINGS)) return parentAttrs
				.withModifierAdded(Attributes.JUMP_STRENGTH, new AttributeModifier(id, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.LEGS)
				.withModifierAdded(Attributes.SNEAKING_SPEED, new AttributeModifier(id, 0.25, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.LEGS);

			return parentAttrs.withModifierAdded(Attributes.SAFE_FALL_DISTANCE, new AttributeModifier(id, 1, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.bySlot(type.getSlot()));
		}
	};

}
