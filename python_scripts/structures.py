import os
import typing

import amulet_nbt
from amulet_nbt import CompoundTag, ListTag, StringTag

find_target = "decorations_road"

for (root,dirs,files) in os.walk("../src/main/resources/data/stellarity/structure/village/"):
	for file in files:
		resolved_path = os.path.join(root, file)
		# print("tested: " + str(resolved_path))

		tag = amulet_nbt.load(resolved_path).tag
		if find_target in str(tag):
			print("found: " + str(resolved_path))
			for block in typing.cast(CompoundTag, tag).get_list("blocks"):

				if "nbt" not in block.keys(): continue

				print(block.get("nbt").get("pool"))

