package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.tags.StellarityBlockTags;
import dev.coder2195.stellarity.tags.StellarityItemTags;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public interface StellarityToolMaterials {
	ToolMaterial SHULKER = new ToolMaterial(StellarityBlockTags.INCORRECT_FOR_SHULKER_TOOL, 2701, 14.4f, 4.5f, 15, StellarityItemTags.SHULKER_TOOL_MATERIALS) {
		@Override
		protected ItemAttributeModifiers createSwordAttributes(float attackDamageBaseline, float attackSpeedBaseline) {
			return super.createSwordAttributes(attackDamageBaseline, attackSpeedBaseline).withModifierAdded(Attributes.ATTACK_SPEED, new AttributeModifier(Stellarity.id("shulker_tool"), 0.1F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.HAND);
		}

		@Override
		protected ItemAttributeModifiers createToolAttributes(float attackDamageBaseline, float attackSpeedBaseline) {
			return super.createToolAttributes(attackDamageBaseline, attackSpeedBaseline).withModifierAdded(Attributes.ATTACK_SPEED, new AttributeModifier(Stellarity.id("shulker_tool"), 0.1F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.HAND);
		}
	};

	ToolMaterial THE_BEGINNING = new ToolMaterial(StellarityBlockTags.EMPTY, 496, 1, 1, 10, StellarityItemTags.EMPTY) {
		@Override
		protected ItemAttributeModifiers createSwordAttributes(float attackDamageBaseline, float attackSpeedBaseline) {
			return super.createSwordAttributes(attackDamageBaseline, attackSpeedBaseline).withModifierAdded(Attributes.ATTACK_DAMAGE, new AttributeModifier(Stellarity.id("the_beginning"), 1, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.OFFHAND);
		}
	};

	ToolMaterial THE_END = new ToolMaterial(StellarityBlockTags.EMPTY, 496, 1, 1, 10, StellarityItemTags.EMPTY) {
		@Override
		protected ItemAttributeModifiers createSwordAttributes(float attackDamageBaseline, float attackSpeedBaseline) {
			return super.createSwordAttributes(attackDamageBaseline, attackSpeedBaseline).withModifierAdded(Attributes.MOVEMENT_SPEED, new AttributeModifier(Stellarity.id("the_end"), 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.OFFHAND);
		}
	};

	ToolMaterial DRAGONBLADE = new ToolMaterial(StellarityBlockTags.EMPTY, 999, 1, 5, 15,StellarityItemTags.EMPTY) {
		@Override
		protected ItemAttributeModifiers createSwordAttributes(float attackDamageBaseline, float attackSpeedBaseline) {
			return super.createSwordAttributes(attackDamageBaseline, attackSpeedBaseline)
				.withModifierAdded(Attributes.SWEEPING_DAMAGE_RATIO, new AttributeModifier(Stellarity.id("dragonblade"), 0.5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
				.withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(Stellarity.id("dragonblade"), 0.3, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
		}
	};
}
