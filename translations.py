import os
import json
from natsort import natsorted

translations = {}

# in translations/enlightened
for file in os.listdir("translations/enlightened"):
	print(file)
	with open(f"translations/enlightened/{file}", "r", encoding="utf-8") as f:
		data = json.load(f)
		translations[file] = data

done = False
while not done:
	print("Choose action:")
	print("1. Add new key")
	print("2. Remove key")
	print("3. Rename key")
	print("4. Add new key with custom value")
	print("5. Exit (or just syncing from weblate)")
	action = input("Input action (1/2/3/4/5): ")

	if action == "1" or action == "4":
		key = input("Enter new key: ")
		value = input("Enter value for the new key: ")

		translations["en_us.json"][key] = value

	elif action == "2":
		key = input("Enter key to remove: ")

		del translations["en_us.json"][key]
	elif action == "3":
		old_key = input("Enter key to rename: ")
		new_key = input("Enter new key name: ")

		translations["en_us.json"][new_key] = translations["en_us.json"].pop(old_key)

	elif action == "5":
		done = True

for lang in translations:
	for key in translations["en_us.json"]:
		if key not in translations[lang] or translations[lang][key] == key:
			if "en_us" in lang:
				print("Warning: Key " + key + " is missing")
			else:
				translations[lang][key] = translations["en_us.json"][key]

	final = dict(natsorted(translations[lang].items()))

	with open("translations/enlightened/en_us.json", "w", encoding="utf-8") as f:
		json.dump(dict(natsorted(translations["en_us.json"].items())), f, ensure_ascii=False, indent=4)

	# final["LEGACY_TRANSLATIONS_THIS_IS_NOT_A_KEY"] = "ALL LEGACY TRANSLATIONS BELOW, TRY NOT TO EDIT."

	# with open(f"translations/enlightened_legacy/{lang}", "r", encoding="utf-8") as f:
	#	 data = json.load(f)
	#	 for key in data:
	#		 if key not in final:
	#			 final[key] = data[key]

	with open(f"src/main/resources/assets/stellarity/lang/{lang}", "w+", encoding="utf-8") as f:
		json.dump(final, f, ensure_ascii=False, indent=4)
