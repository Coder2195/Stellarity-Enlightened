package prismatic.shards.stellarity.registry;

import net.minecraft.advancements.triggers.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.criterion_trigger.DashTrigger;
import prismatic.shards.stellarity.registry.criterion_trigger.SpecialCraftTrigger;
import prismatic.shards.stellarity.registry.criterion_trigger.VoidFishedTrigger;

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
