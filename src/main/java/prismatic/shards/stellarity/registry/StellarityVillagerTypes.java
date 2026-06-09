package prismatic.shards.stellarity.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.villager.VillagerType;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityVillagerTypes {
	Holder.Reference<VillagerType> END = register("end");

	private static Holder.Reference<VillagerType> register(String id) {
		ResourceKey<VillagerType> key = Stellarity.key(Registries.VILLAGER_TYPE, id);
		//noinspection InstantiationOfUtilityClass
		return Registry.registerForHolder(BuiltInRegistries.VILLAGER_TYPE, Stellarity.id(id), new VillagerType());

	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Villager Types");
	}
}
