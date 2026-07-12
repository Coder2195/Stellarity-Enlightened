package dev.coder2195.stellarity.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import dev.coder2195.stellarity.registry.entity_sub_predicate.EntityAttributeModifiersPredicate;
import dev.coder2195.stellarity.registry.entity_sub_predicate.NbtNumberPredicate;

public interface StellarityEntitySubPredicates {
	static void init() {
		Registry.register(BuiltInRegistries.ENTITY_SUB_PREDICATE_TYPE, "nbt_number", NbtNumberPredicate.CODEC);
		Registry.register(BuiltInRegistries.ENTITY_SUB_PREDICATE_TYPE, "entity_attribute_modifiers", EntityAttributeModifiersPredicate.CODEC);
	}
}
