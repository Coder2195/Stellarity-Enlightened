package dev.coder2195.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.trading.VillagerTrade;
import dev.coder2195.stellarity.Stellarity;

public interface StellarityVillagerTrades {
	ResourceKey<VillagerTrade> ARMORER_1_COAL_ENDERITE_SHARD = id("armorer/1/coal_enderite_shard");
	ResourceKey<VillagerTrade> ARMORER_1_CHARCOAL_ENDERITE_SHARD = id("armorer/1/charcoal_enderite_shard");
	ResourceKey<VillagerTrade> ARMORER_1_BLAZE_ROD_ENDERITE_SHARD = id("armorer/1/blaze_rod_enderite_shard");
	ResourceKey<VillagerTrade> ARMORER_1_ENDERITE_SHARD_SPECIAL_IRON_CHESTPLATE = id("armorer/1/enderite_shard_special_iron_chestplate");
	ResourceKey<VillagerTrade> ARMORER_1_ENDERITE_SHARD_SPECIAL_IRON_HELMET = id("armorer/1/enderite_shard_special_iron_helmet");
	ResourceKey<VillagerTrade> ARMORER_2_ENDERITE_SHARD_SPECIAL_IRON_LEGGINGS = id("armorer/2/enderite_shard_special_iron_leggings");
	ResourceKey<VillagerTrade> ARMORER_2_ENDERITE_SHARD_SPECIAL_IRON_BOOTS = id("armorer/2/enderite_shard_special_iron_boots");
	ResourceKey<VillagerTrade> ARMORER_2_ENDERITE_SHARD_HALLOWED_INGOT = id("armorer/2/enderite_shard_hallowed_ingot");
	ResourceKey<VillagerTrade> ARMORER_2_ENDERITE_SHARD_CHROUS_PLATING = id("armorer/2/enderite_shard_chorus_plating");
	ResourceKey<VillagerTrade> ARMORER_3_DIAMOND_ENDERITE_SHARD = id("armorer/3/diamond_enderite_shard");
	ResourceKey<VillagerTrade> ARMORER_3_ENDERITE_SHARD_SHIELD_COPPER_ELEKTRA_SHIELD = id("armorer/3/enderite_shard_shield_copper_elektra_shield");
	ResourceKey<VillagerTrade> ARMORER_4_PURPUR_BLOCK_ENDERITE_SHARD_ENDERITE_UPGRADE_SMITHING_TEMPLATE = id("armorer/4/purpur_block_enderite_shard_enderite_upgrade_smithing_template");
	ResourceKey<VillagerTrade> ARMORER_4_ENDERITE_SHARD_SPECIAL_DIAMOND_LEGGINGS = id("armorer/4/enderite_shard_special_diamond_leggings");
	ResourceKey<VillagerTrade> ARMORER_4_ENDERITE_SHARD_SPECIAL_DIAMOND_BOOTS = id("armorer/4/enderite_shard_special_diamond_boots");
	ResourceKey<VillagerTrade> ARMORER_5_ENDERITE_SHARD_SPECIAL_DIAMOND_CHESTPLATE = id("armorer/5/enderite_shard_special_diamond_chestplate");
	ResourceKey<VillagerTrade> ARMORER_5_ENDERITE_SHARD_SPECIAL_DIAMOND_HELMET = id("armorer/5/enderite_shard_special_diamond_helmet");

	ResourceKey<VillagerTrade> BUTCHER_1_ENDERMAN_FLESH_ENDERITE_SHARD = id("butcher/1/enderman_flesh_enderite_shard");
	ResourceKey<VillagerTrade> BUTCHER_2_COAL_ENDERITE_SHARD = id("butcher/2/coal_enderite_shard");
	ResourceKey<VillagerTrade> BUTCHER_2_CHARCOAL_ENDERITE_SHARD = id("butcher/2/charcoal_enderite_shard");
	ResourceKey<VillagerTrade> BUTCHER_2_ENDERMAN_FLESH_ENDERITE_SHARD_GRILLED_ENDERMAN_FLESH = id("butcher/2/enderman_flesh_enderite_shard_grilled_enderman_flesh");
	ResourceKey<VillagerTrade> BUTCHER_2_ENDERMAN_FLESH_ENDERITE_SHARD_FROZEN_CARPACCIO = id("butcher/2/enderman_flesh_enderite_shard_frozen_carpaccio");
	ResourceKey<VillagerTrade> BUTCHER_3_SHULKER_BODY_ENDERITE_SHARD = id("butcher/3/shulker_body_enderite_shard");
	ResourceKey<VillagerTrade> BUTCHER_4_ENDERITE_SHARD_DRIED_KELP_BLOCK = id("butcher/4/enderite_shard_dried_kelp_block");
	ResourceKey<VillagerTrade> BUTCHER_5_ENDERITE_SHARD_SHEPHERDS_PIE = id("butcher/5/enderite_shard_shepherds_pie");

	static ResourceKey<VillagerTrade> id(String id) {
		return Stellarity.key(Registries.VILLAGER_TRADE, id);
	}
}
