package prismatic.shards.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityLootTables {
	ResourceKey<LootTable> DUNGEON = id("dungeon");
	ResourceKey<LootTable> EXIT_PORTAL = id("exit_portal");
	ResourceKey<LootTable> CAMPSITE_TRASH = id("campsite/trash");
	ResourceKey<LootTable> CAMPSITE_FOOD = id("campsite/food");
	ResourceKey<LootTable> CAMPSITE_TENT = id("campsite/tent");
	ResourceKey<LootTable> CAMPSITE_TENT_2 = id("campsite/tent_2");


	ResourceKey<LootTable> VOID_FISHING_FISH = id("void_fishing/fish");
	ResourceKey<LootTable> VOID_FISHING_JUNK = id("void_fishing/junk");
	ResourceKey<LootTable> VOID_FISHING_TREASURE = id("void_fishing/treasure");
	ResourceKey<LootTable> VOID_FISHING_FISHER_OF_VOIDS = id("void_fishing/fisher_of_voids");
	ResourceKey<LootTable> VOID_FISHING_EVENT = id("void_fishing/event");
	ResourceKey<LootTable> VOID_FISHING_LOCATION_AMETHYST_BIOMES = id("void_fishing/location/amethyst_biomes");
	ResourceKey<LootTable> VOID_FISHING_LOCATION_FIERY_HILLS = id("void_fishing/location/fiery_hills");
	ResourceKey<LootTable> VOID_FISHING_LOCATION_FLESH_TUNDRA = id("void_fishing/location/flesh_tundra");
	ResourceKey<LootTable> VOID_FISHING_LOCATION_FROZEN_SPIKES = id("void_fishing/location/frozen_spikes");
	ResourceKey<LootTable> VOID_FISHING_LOCATION_VANILLA_BIOMES = id("void_fishing/location/vanilla_biomes");
	ResourceKey<LootTable> VOID_FISHING_LOCATION_PRISMARINE_FOREST = id("void_fishing/location/prismarine_forest");
	ResourceKey<LootTable> VOID_FISHING_LOCATION_THE_HALLOW = id("void_fishing/location/the_hallow");
	ResourceKey<LootTable> VOID_FISHING_LOCATION_WARPED_MARSH = id("void_fishing/location/warped_marsh");
	ResourceKey<LootTable> VOID_FISHING_LOCATION_ENDLESS_DUNES = id("void_fishing/location/endless_dunes");

	static ResourceKey<LootTable> id(String id) {
		return Stellarity.key(Registries.LOOT_TABLE, id);
	}
}
