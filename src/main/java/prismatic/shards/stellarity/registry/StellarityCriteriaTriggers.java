package prismatic.shards.stellarity.registry;

import net.minecraft.advancements.triggers.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.advancement_criterion.SpecialCraftTrigger;
import prismatic.shards.stellarity.registry.advancement_criterion.VoidFishedTrigger;

public interface StellarityCriteriaTriggers {
	VoidFishedTrigger VOID_FISHED = register(
		"void_fished",
		new VoidFishedTrigger()
	);

	SpecialCraftTrigger SPECIAL_CRAFT = register(
		"special_craft",
		new SpecialCraftTrigger()
	);

	static <T extends CriterionTrigger<?>> T register(String id, T trigger) {
		return Registry.register(BuiltInRegistries.TRIGGER_TYPES, Stellarity.id(id), trigger);
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Criteria Triggers");
	}
}
