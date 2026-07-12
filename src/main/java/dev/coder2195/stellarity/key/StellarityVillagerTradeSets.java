package dev.coder2195.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.trading.TradeSet;
import dev.coder2195.stellarity.Stellarity;

public interface StellarityVillagerTradeSets {
	ResourceKey<TradeSet> ARMORER_LEVEL_1 = id("armorer/level_1");
	ResourceKey<TradeSet> ARMORER_LEVEL_1_2 = id("armorer/level_1_2");
	ResourceKey<TradeSet> ARMORER_LEVEL_2 = id("armorer/level_2");
	ResourceKey<TradeSet> ARMORER_LEVEL_2_2 = id("armorer/level_2_2");
	ResourceKey<TradeSet> ARMORER_LEVEL_3 = id("armorer/level_3");
	ResourceKey<TradeSet> ARMORER_LEVEL_4 = id("armorer/level_4");
	ResourceKey<TradeSet> ARMORER_LEVEL_5 = id("armorer/level_5");

	ResourceKey<TradeSet> BUTCHER_LEVEL_1 = id("butcher/level_1");
	ResourceKey<TradeSet> BUTCHER_LEVEL_2 = id("butcher/level_2");
	ResourceKey<TradeSet> BUTCHER_LEVEL_2_2 = id("butcher/level_2_2");
	ResourceKey<TradeSet> BUTCHER_LEVEL_3 = id("butcher/level_3");
	ResourceKey<TradeSet> BUTCHER_LEVEL_4 = id("butcher/level_4");
	ResourceKey<TradeSet> BUTCHER_LEVEL_5 = id("butcher/level_5");

	ResourceKey<TradeSet> CARTOGRAPHER_LEVEL_1 = id("cartographer/level_1");
	ResourceKey<TradeSet> CARTOGRAPHER_LEVEL_2 = id("cartographer/level_2");
	ResourceKey<TradeSet> CARTOGRAPHER_LEVEL_3 = id("cartographer/level_3");
	ResourceKey<TradeSet> CARTOGRAPHER_LEVEL_4 = id("cartographer/level_4");
	ResourceKey<TradeSet> CARTOGRAPHER_LEVEL_5 = id("cartographer/level_5");

	ResourceKey<TradeSet> CLERIC_LEVEL_1 = id("cleric/level_1");
	ResourceKey<TradeSet> CLERIC_LEVEL_2 = id("cleric/level_2");
	ResourceKey<TradeSet> CLERIC_LEVEL_3 = id("cleric/level_3");
	ResourceKey<TradeSet> CLERIC_LEVEL_4 = id("cleric/level_4");
	ResourceKey<TradeSet> CLERIC_LEVEL_5 = id("cleric/level_5");

	ResourceKey<TradeSet> FARMER_LEVEL_1 = id("farmer/level_1");
	ResourceKey<TradeSet> FARMER_LEVEL_2 = id("farmer/level_2");
	ResourceKey<TradeSet> FARMER_LEVEL_3 = id("farmer/level_3");
	ResourceKey<TradeSet> FARMER_LEVEL_4 = id("farmer/level_4");
	ResourceKey<TradeSet> FARMER_LEVEL_5 = id("farmer/level_5");

	ResourceKey<TradeSet> FISHERMAN_LEVEL_1 = id("fisherman/level_1");
	ResourceKey<TradeSet> FISHERMAN_LEVEL_2 = id("fisherman/level_2");
	ResourceKey<TradeSet> FISHERMAN_LEVEL_3 = id("fisherman/level_3");
	ResourceKey<TradeSet> FISHERMAN_LEVEL_4 = id("fisherman/level_4");
	ResourceKey<TradeSet> FISHERMAN_LEVEL_5 = id("fisherman/level_5");

	ResourceKey<TradeSet> FLETCHER_LEVEL_1 = id("fletcher/level_1");
	ResourceKey<TradeSet> FLETCHER_LEVEL_2 = id("fletcher/level_2");
	ResourceKey<TradeSet> FLETCHER_LEVEL_3 = id("fletcher/level_3");
	ResourceKey<TradeSet> FLETCHER_LEVEL_4 = id("fletcher/level_4");
	ResourceKey<TradeSet> FLETCHER_LEVEL_5 = id("fletcher/level_5");

	ResourceKey<TradeSet> LEATHERWORKER_LEVEL_1 = id("leatherworker/level_1");
	ResourceKey<TradeSet> LEATHERWORKER_LEVEL_2 = id("leatherworker/level_2");
	ResourceKey<TradeSet> LEATHERWORKER_LEVEL_3 = id("leatherworker/level_3");
	ResourceKey<TradeSet> LEATHERWORKER_LEVEL_4 = id("leatherworker/level_4");
	ResourceKey<TradeSet> LEATHERWORKER_LEVEL_5 = id("leatherworker/level_5");

	ResourceKey<TradeSet> LIBRARIAN_LEVEL_1 = id("librarian/level_1");
	ResourceKey<TradeSet> LIBRARIAN_LEVEL_2 = id("librarian/level_2");
	ResourceKey<TradeSet> LIBRARIAN_LEVEL_3 = id("librarian/level_3");
	ResourceKey<TradeSet> LIBRARIAN_LEVEL_4 = id("librarian/level_4");
	ResourceKey<TradeSet> LIBRARIAN_LEVEL_5 = id("librarian/level_5");

	ResourceKey<TradeSet> MASON_LEVEL_1 = id("mason/level_1");
	ResourceKey<TradeSet> MASON_LEVEL_2 = id("mason/level_2");
	ResourceKey<TradeSet> MASON_LEVEL_3 = id("mason/level_3");
	ResourceKey<TradeSet> MASON_LEVEL_4 = id("mason/level_4");
	ResourceKey<TradeSet> MASON_LEVEL_5 = id("mason/level_5");

	ResourceKey<TradeSet> SHEPHERD_LEVEL_1 = id("shepherd/level_1");
	ResourceKey<TradeSet> SHEPHERD_LEVEL_2 = id("shepherd/level_2");
	ResourceKey<TradeSet> SHEPHERD_LEVEL_3 = id("shepherd/level_3");
	ResourceKey<TradeSet> SHEPHERD_LEVEL_4 = id("shepherd/level_4");
	ResourceKey<TradeSet> SHEPHERD_LEVEL_5 = id("shepherd/level_5");

	ResourceKey<TradeSet> TOOLSMITH_LEVEL_1 = id("toolsmith/level_1");
	ResourceKey<TradeSet> TOOLSMITH_LEVEL_2 = id("toolsmith/level_2");
	ResourceKey<TradeSet> TOOLSMITH_LEVEL_3 = id("toolsmith/level_3");
	ResourceKey<TradeSet> TOOLSMITH_LEVEL_4 = id("toolsmith/level_4");
	ResourceKey<TradeSet> TOOLSMITH_LEVEL_5 = id("toolsmith/level_5");

	ResourceKey<TradeSet> WEAPONSMITH_LEVEL_1 = id("weaponsmith/level_1");
	ResourceKey<TradeSet> WEAPONSMITH_LEVEL_2 = id("weaponsmith/level_2");
	ResourceKey<TradeSet> WEAPONSMITH_LEVEL_3 = id("weaponsmith/level_3");
	ResourceKey<TradeSet> WEAPONSMITH_LEVEL_4 = id("weaponsmith/level_4");
	ResourceKey<TradeSet> WEAPONSMITH_LEVEL_5 = id("weaponsmith/level_5");

	static ResourceKey<TradeSet> id(String id) {
		return Stellarity.key(Registries.TRADE_SET, id);
	}
}
