package dev.coder2195.stellarity.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import dev.coder2195.stellarity.Stellarity;

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

	ResourceKey<LootTable> END_VILLAGE_APIARY = id("end_village/apiary");
	ResourceKey<LootTable> END_VILLAGE_ARCHER_TOWER = id("end_village/archer_tower");
	ResourceKey<LootTable> END_VILLAGE_ARMORER = id("end_village/armorer");
	ResourceKey<LootTable> END_VILLAGE_BUTCHER = id("end_village/butcher");
	ResourceKey<LootTable> END_VILLAGE_CARTOGRAPHER = id("end_village/cartographer");
	ResourceKey<LootTable> END_VILLAGE_CARTOGRAPHER_SHULKER_BOX = id("end_village/cartographer_shulker_box");
	ResourceKey<LootTable> END_VILLAGE_CLERIC = id("end_village/cleric");
	ResourceKey<LootTable> END_VILLAGE_FISHERMAN = id("end_village/fisherman");
	ResourceKey<LootTable> END_VILLAGE_FISHERMAN_SHULKER_BOX = id("end_village/fisherman_shulker_box");
	ResourceKey<LootTable> END_VILLAGE_FLETCHER = id("end_village/fletcher");
	ResourceKey<LootTable> END_VILLAGE_FLETCHER_SHULKER_BOX = id("end_village/fletcher_shulker_box");
	ResourceKey<LootTable> END_VILLAGE_LEATHERWORKER = id("end_village/leatherworker");
	ResourceKey<LootTable> END_VILLAGE_LIBRARIAN = id("end_village/librarian");
	ResourceKey<LootTable> END_VILLAGE_MARKET = id("end_village/market");
	ResourceKey<LootTable> END_VILLAGE_MASON = id("end_village/mason");
	ResourceKey<LootTable> END_VILLAGE_SHEPHERD = id("end_village/shepherd");
	ResourceKey<LootTable> END_VILLAGE_SHEPHERD_SHULKER_BOX = id("end_village/shepherd_shulker_box");
	ResourceKey<LootTable> END_VILLAGE_TOOLSMITH = id("end_village/toolsmith");
	ResourceKey<LootTable> END_VILLAGE_TREE_FARM_1 = id("end_village/tree_farm_1");
	ResourceKey<LootTable> END_VILLAGE_TREE_FARM_2 = id("end_village/tree_farm_2");
	ResourceKey<LootTable> END_VILLAGE_WEAPONSMITH = id("end_village/weaponsmith");
	ResourceKey<LootTable> END_VILLAGE_HOUSE_BOOKWORM = id("end_village/house/bookworm");
	ResourceKey<LootTable> END_VILLAGE_HOUSE_COMMON = id("end_village/house/common");
	ResourceKey<LootTable> END_VILLAGE_HOUSE_LUSH = id("end_village/house/lush");
	ResourceKey<LootTable> END_VILLAGE_HOUSE_MUSIC = id("end_village/house/music");
	ResourceKey<LootTable> END_VILLAGE_HOUSE_REGULAR = id("end_village/house/regular");
	ResourceKey<LootTable> END_VILLAGE_HOUSE_REGULAR_SHULKER_BOX = id("end_village/house/regular_shulker_box");
	ResourceKey<LootTable> END_VILLAGE_HOUSE_WARPED = id("end_village/house/warped");
	ResourceKey<LootTable> END_VILLAGE_CENTER_AETHER = id("end_village/center/aether");
	ResourceKey<LootTable> END_VILLAGE_CENTER_TOWN_HALL = id("end_village/center/town_hall");
	ResourceKey<LootTable> END_VILLAGE_CENTER_MARKET_BAKER = id("end_village/center/market/baker");
	ResourceKey<LootTable> END_VILLAGE_CENTER_MARKET_ENCHANTS = id("end_village/center/market/enchants");
	ResourceKey<LootTable> END_VILLAGE_CENTER_MARKET_EXPLORER = id("end_village/center/market/explorer");

	static ResourceKey<LootTable> id(String id) {
		return Stellarity.key(Registries.LOOT_TABLE, id);
	}
}
