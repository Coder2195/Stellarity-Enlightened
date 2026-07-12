package dev.coder2195.stellarity.registry;

import net.minecraft.advancements.triggers.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.criterion_trigger.DashTrigger;
import dev.coder2195.stellarity.registry.criterion_trigger.SpecialCraftTrigger;
import dev.coder2195.stellarity.registry.criterion_trigger.VoidFishedTrigger;

public interface StellarityCriteriaTriggers {
	VoidFishedTrigger VOID_FISHED = register("void_fished", new VoidFishedTrigger());
	SpecialCraftTrigger SPECIAL_CRAFT = register("special_craft", new SpecialCraftTrigger());
	DashTrigger DASH = register("dash", new DashTrigger());


	static <T extends CriterionTrigger<?>> T register(String id, T trigger) {
		return Registry.register(BuiltInRegistries.TRIGGER_TYPES, Stellarity.id(id), trigger);
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Criteria Triggers");
	}
}
