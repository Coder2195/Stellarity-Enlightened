import os
import shutil

for root, dirs, files in os.walk("../minecraft-assets/assets/minecraft/textures"):
	for file in files:
		if "birch" in file:
			for wood_name in ["amethyii", "ashen", "hallowed", "shrubbed", "inferno", "prismatic"]:
				old_path = os.path.join(root, file)
				new_path = os.path.join(root.replace("../minecraft-assets/assets/minecraft/textures", "../src/main/resources/assets/stellarity/textures"), file.replace("birch", wood_name))
				print(old_path, new_path)
				shutil.copy(old_path, new_path)