import os
import typing

import amulet_nbt
from amulet_nbt import CompoundTag

loot_tables = ["dungeon", "exit_portal", "campsite/trash", "campsite/food", "campsite/tent", "campsite/tent_2",
              "void_fishing/fish", "void_fishing/junk", "void_fishing/treasure", "void_fishing/fisher_of_voids", "void_fishing/event", "void_fishing/location/amethyst_biomes", "void_fishing/location/fiery_hills", "void_fishing/location/flesh_tundra", "void_fishing/location/frozen_spikes", "void_fishing/location/vanilla_biomes", "void_fishing/location/prismarine_forest", "void_fishing/location/the_hallow", "void_fishing/location/warped_marsh", "void_fishing/location/endless_dunes",
              "village/apiary", "village/archer_tower", "village/armorer", "village/butcher", "village/cartographer", "village/cartographer_shulker_box", "village/cleric", "village/fisherman", "village/fisherman_shulker_box", "village/fletcher", "village/fletcher_shulker_box", "village/leatherworker", "village/librarian", "village/market", "village/mason", "village/shepherd", "village/shepherd_shulker_box", "village/toolsmith", "village/tree_farm_1", "village/tree_farm_2", "village/weaponsmith",
              "village/house/bookworm", "village/house/common", "village/house/lush", "village/house/music", "village/house/regular", "village/house/regular_shulker_box", "village/house/warped", "village/center/aether", "village/center/town_hall", "village/center/market/baker", "village/center/market/enchants", "village/center/market/explorer"]

for (root, dirs, files) in os.walk("../src/main/resources/data/stellarity/structure/"):
	for file in files:
		resolved_path = os.path.join(root, file)
		# print("tested: " + str(resolved_path))

		tag = amulet_nbt.load(resolved_path).tag
		if "LootTable" in str(tag):
			for block in typing.cast(CompoundTag, tag).get_list("blocks"):

				if "nbt" not in block.keys(): continue

				nbt = block.get_compound("nbt")
				if "LootTable" not in nbt.keys(): continue
				loot_table = str(nbt.get_string("LootTable"))


				print(f"loot table in {resolved_path}: {loot_table}")
				if loot_table.replace("stellarity:", "") in loot_tables: continue

				print(f"broken loot table in {resolved_path}: {loot_table}")
