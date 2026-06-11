package prismatic.shards.stellarity.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import prismatic.shards.stellarity.registry.entity_sub_predicate.EntityAttributeModifiersPredicate;
import prismatic.shards.stellarity.registry.entity_sub_predicate.NbtNumberPredicate;

public interface StellarityEntitySubPredicates {
	static void init() {
		Registry.register(BuiltInRegistries.ENTITY_SUB_PREDICATE_TYPE, "nbt_number", NbtNumberPredicate.CODEC);
		Registry.register(BuiltInRegistries.ENTITY_SUB_PREDICATE_TYPE, "entity_attribute_modifiers", EntityAttributeModifiersPredicate.CODEC);
	}
}
