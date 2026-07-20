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

	ResourceKey<LootTable> VILLAGE_APIARY = id("village/apiary");
	ResourceKey<LootTable> VILLAGE_ARCHER_TOWER = id("village/archer_tower");
	ResourceKey<LootTable> VILLAGE_ARMORER = id("village/armorer");
	ResourceKey<LootTable> VILLAGE_BUTCHER = id("village/butcher");
	ResourceKey<LootTable> VILLAGE_CARTOGRAPHER = id("village/cartographer");
	ResourceKey<LootTable> VILLAGE_CARTOGRAPHER_SHULKER_BOX = id("village/cartographer_shulker_box");
	ResourceKey<LootTable> VILLAGE_CLERIC = id("village/cleric");
	ResourceKey<LootTable> VILLAGE_FISHERMAN = id("village/fisherman");
	ResourceKey<LootTable> VILLAGE_FISHERMAN_SHULKER_BOX = id("village/fisherman_shulker_box");
	ResourceKey<LootTable> VILLAGE_FLETCHER = id("village/fletcher");
	ResourceKey<LootTable> VILLAGE_FLETCHER_SHULKER_BOX = id("village/fletcher_shulker_box");
	ResourceKey<LootTable> VILLAGE_LEATHERWORKER = id("village/leatherworker");
	ResourceKey<LootTable> VILLAGE_LIBRARIAN = id("village/librarian");
	ResourceKey<LootTable> VILLAGE_MARKET = id("village/market");
	ResourceKey<LootTable> VILLAGE_MASON = id("village/mason");
	ResourceKey<LootTable> VILLAGE_SHEPHERD = id("village/shepherd");
	ResourceKey<LootTable> VILLAGE_SHEPHERD_SHULKER_BOX = id("village/shepherd_shulker_box");
	ResourceKey<LootTable> VILLAGE_TOOLSMITH = id("village/toolsmith");
	ResourceKey<LootTable> VILLAGE_TREE_FARM_1 = id("village/tree_farm_1");
	ResourceKey<LootTable> VILLAGE_TREE_FARM_2 = id("village/tree_farm_2");
	ResourceKey<LootTable> VILLAGE_WEAPONSMITH = id("village/weaponsmith");
	ResourceKey<LootTable> VILLAGE_HOUSE_BOOKWORM = id("village/house/bookworm");
	ResourceKey<LootTable> VILLAGE_HOUSE_COMMON = id("village/house/common");
	ResourceKey<LootTable> VILLAGE_HOUSE_LUSH = id("village/house/lush");
	ResourceKey<LootTable> VILLAGE_HOUSE_MUSIC = id("village/house/music");
	ResourceKey<LootTable> VILLAGE_HOUSE_REGULAR = id("village/house/regular");
	ResourceKey<LootTable> VILLAGE_HOUSE_REGULAR_SHULKER_BOX = id("village/house/regular_shulker_box");
	ResourceKey<LootTable> VILLAGE_HOUSE_WARPED = id("village/house/warped");
	ResourceKey<LootTable> VILLAGE_CENTER_AETHER = id("village/center/aether");
	ResourceKey<LootTable> VILLAGE_CENTER_TOWN_HALL = id("village/center/town_hall");
	ResourceKey<LootTable> VILLAGE_CENTER_MARKET_BAKER = id("village/center/market/baker");
	ResourceKey<LootTable> VILLAGE_CENTER_MARKET_ENCHANTS = id("village/center/market/enchants");
	ResourceKey<LootTable> VILLAGE_CENTER_MARKET_EXPLORER = id("village/center/market/explorer");

	static ResourceKey<LootTable> id(String id) {
		return Stellarity.key(Registries.LOOT_TABLE, id);
	}
}
