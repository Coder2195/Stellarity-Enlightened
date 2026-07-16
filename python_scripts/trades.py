import csv

trades_file = open("trades.tsv")
trades_data = csv.reader(trades_file, delimiter="\t", quotechar='"')

curr_profession = ""
curr_level = ""
professions: dict[str, dict[str, dict[str, str]]] = {}
for row in trades_data:
	if row[0] == "Type": continue
	if row[0].strip() != "":
		curr_profession = row[0].strip()
		if curr_profession not in professions:
			professions[curr_profession] = {}
	if row[1].strip() != "":
		curr_level = row[1].strip()
		professions[curr_profession][curr_level] = {}
	if row[2].strip() != "":
		[group, count] = row[2].split("-")
		group = group.strip()
		count = count.strip()

		professions[curr_profession][curr_level][group] = count

print(professions)


def generate_tradesets():
	registry = input("What registry? TradeSet default")
	if registry == "": registry = "TradeSet"

	lines = []
	for [profession, levels] in professions.items():
		for [level, trade_groups] in levels.items():
			for [trade_group] in trade_groups:
				lines.append(
					f"\tResourceKey<{registry}> {profession.upper()}_LEVEL_{level}{"_" + trade_group if int(trade_group) > 1 else ""} = id(\"{profession.lower()}/level_{level}{"_" + trade_group if int(trade_group) > 1 else ""}\");")
		lines.append("")

	print("\n".join(lines))

def generate_trade_tags():

	lines = []
	for [profession, levels] in professions.items():
		for [level, trade_groups] in levels.items():
			for [trade_group] in trade_groups:
				lines.append(
					f"\taddTags({profession.upper()}_LEVEL_{level}{"_" + trade_group if int(trade_group) > 1 else ""});")
		lines.append("")

	print("\n".join(lines))


def generate_trade_entries():
	lines = ""
	for [profession, levels] in professions.items():
		for [level, trade_groups] in levels.items():
			for [trade_group, amount] in trade_groups.items():
				constant = f"{profession.upper()}_LEVEL_{level}{"_" + trade_group if int(trade_group) > 1 else ""}"
				lines += f"\tnew Tuple3<>({constant}, StellarityVillagerTradeTags.{constant}, {amount}),\n"
		lines += "\n"

	print(lines)


def generate_extra_tradesets():
	lines = ""
	for [profession, levels] in professions.items():
		lines += f"\tif (profession.is({profession.upper()}.key())) {{\n"
		for [level, trade_groups] in levels.items():
			extra_trade_groups = [f"{profession.upper()}_LEVEL_{level}_{trade_group}" for trade_group in trade_groups.keys()
			                      if trade_group != "1"]

			if len(extra_trade_groups) == 0: continue

			lines += f"""\t\tif (level == {level}) return List.of({", ".join(extra_trade_groups)});\n"""
		lines += f"""		return null;
	}}\n"""
	print(lines)

# generate_trade_tags()
generate_extra_tradesets()
# generate_trade_entries()
# generate_tradesets()
