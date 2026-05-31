package prismatic.shards.stellarity.datagen.dynamic;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.equipment.trim.ArmorTrim;
import net.minecraft.world.item.equipment.trim.TrimMaterials;
import net.minecraft.world.item.equipment.trim.TrimPatterns;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;

import static prismatic.shards.stellarity.key.StellarityVillagerTrades.*;


import java.util.List;
import java.util.Optional;

import static net.minecraft.world.item.Items.*;
import static prismatic.shards.stellarity.registry.StellarityItems.*;
import static prismatic.shards.stellarity.util.LootUtil.*;

public interface VillagerTradeProvider {
	static void bootstrap(BootstrapContext<VillagerTrade> context) {
		var trimMaterials = context.lookup(Registries.TRIM_MATERIAL);
		var trimPatterns = context.lookup(Registries.TRIM_PATTERN);
		var enchants = context.lookup(Registries.ENCHANTMENT);

		List<LootItemFunction> ironArmorModifier = List.of(
			component(DataComponents.TRIM, new ArmorTrim(trimMaterials.getOrThrow(TrimMaterials.EMERALD), trimPatterns.getOrThrow(TrimPatterns.SPIRE))).when(chance(0.5f)).build(),
			enchant().when(chance(0.5f)).build()
		);
		List<LootItemFunction> diamondArmorModifier = List.of(
			component(DataComponents.TRIM, new ArmorTrim(trimMaterials.getOrThrow(TrimMaterials.EMERALD), trimPatterns.getOrThrow(TrimPatterns.EYE))).when(chance(0.5f)).build(),
			component(DataComponents.TRIM, new ArmorTrim(trimMaterials.getOrThrow(TrimMaterials.AMETHYST), trimPatterns.getOrThrow(TrimPatterns.EYE))).when(chance(0.5f)).build(),
			enchant(enchants, 15, 31).build()
		);

		context.register(ARMORER_1_COAL_ENDERITE_SHARD, simpleToShard(COAL, 19, 1, 2, 12, 0.05f));
		context.register(ARMORER_1_CHARCOAL_ENDERITE_SHARD, simpleToShard(CHARCOAL, 24, 1, 2, 10, 0.05f));
		context.register(ARMORER_1_BLAZE_ROD_ENDERITE_SHARD, simpleToShard(BLAZE_ROD, 3, 1, 1, 10, 0.05f));
		context.register(ARMORER_1_ENDERITE_SHARD_SPECIAL_IRON_CHESTPLATE, shardToModifierItem(9, IRON_CHESTPLATE, ironArmorModifier, 1, 2, 6, 0.2f));
		context.register(ARMORER_1_ENDERITE_SHARD_SPECIAL_IRON_HELMET, shardToModifierItem(7, IRON_HELMET, ironArmorModifier, 1, 2, 6, 0.2f));

		context.register(ARMORER_2_ENDERITE_SHARD_SPECIAL_IRON_LEGGINGS, shardToModifierItem(7, IRON_LEGGINGS, ironArmorModifier, 1, 8, 6, 0.2f));
		context.register(ARMORER_2_ENDERITE_SHARD_SPECIAL_IRON_BOOTS, shardToModifierItem(6, IRON_BOOTS, ironArmorModifier, 1, 8, 6, 0.2f));
		context.register(ARMORER_2_ENDERITE_SHARD_HALLOWED_INGOT, shardToSimple(5, HALLOWED_INGOT, 1, 10, 8, 0.2f));
		context.register(ARMORER_2_ENDERITE_SHARD_CHROUS_PLATING, shardToSimple(5, CHORUS_PLATING, 1, 10, 8, 0.2f));
		// TODO: update with correct shard based on vote
		context.register(ARMORER_3_DIAMOND_ENDERITE_SHARD, simpleToShard(DIAMOND, 1, 2, 12, 8, 0.05f));
		// TODO: update with elektra shield when done
		context.register(ARMORER_3_ENDERITE_SHARD_SHIELD_COPPER_ELEKTRA_SHIELD, simpleShardToSimple(SHIELD, 1, 1, SHIELD, 1, 40, 2, 0.2f));
		context.register(ARMORER_4_PURPUR_BLOCK_ENDERITE_SHARD_ENDERITE_UPGRADE_SMITHING_TEMPLATE, simpleShardToSimple(PURPUR_BLOCK, 24, 32, ENDERITE_UPGRADE_SMITHING_TEMPLATE, 1, 10, 2, 0.2f));
		context.register(ARMORER_4_ENDERITE_SHARD_SPECIAL_DIAMOND_LEGGINGS, shardToModifierItem(27, DIAMOND_LEGGINGS, diamondArmorModifier, 1, 15, 3, 0.2f));
		context.register(ARMORER_4_ENDERITE_SHARD_SPECIAL_DIAMOND_BOOTS, shardToModifierItem(21, DIAMOND_BOOTS, diamondArmorModifier, 1, 15, 3, 0.2f));
		context.register(ARMORER_5_ENDERITE_SHARD_SPECIAL_DIAMOND_CHESTPLATE, shardToModifierItem(30, DIAMOND_CHESTPLATE, diamondArmorModifier, 1, 15, 3, 0.2f));
		context.register(ARMORER_5_ENDERITE_SHARD_SPECIAL_DIAMOND_HELMET, shardToModifierItem(24, DIAMOND_HELMET, diamondArmorModifier, 1, 15, 3, 0.2f));
		context.register(BUTCHER_1_ENDERMAN_FLESH_ENDERITE_SHARD, simpleToShard(ENDERMAN_FLESH, 12, 1, 1, 14, 0.05f));
		context.register(BUTCHER_2_COAL_ENDERITE_SHARD, simpleToShard(COAL, 18, 1, 3, 12, 0.05f));
		context.register(BUTCHER_2_CHARCOAL_ENDERITE_SHARD, simpleToShard(CHARCOAL, 24, 1, 3, 12, 0.05f));
		context.register(BUTCHER_2_ENDERMAN_FLESH_ENDERITE_SHARD_GRILLED_ENDERMAN_FLESH, simpleShardToSimple(ENDERMAN_FLESH, 3, 2, GRILLED_ENDERMAN_FLESH, 3, 5, 8, 0.05f));
		context.register(BUTCHER_2_ENDERMAN_FLESH_ENDERITE_SHARD_FROZEN_CARPACCIO, simpleShardToSimple(ENDERMAN_FLESH, 3, 2, FROZEN_CARPACCIO, 3, 5, 8, 0.05f));
		context.register(BUTCHER_3_SHULKER_BODY_ENDERITE_SHARD, simpleToShard(SHULKER_BODY, 2, 1, 12, 14, 0.05f));
		context.register(BUTCHER_4_ENDERITE_SHARD_DRIED_KELP_BLOCK, shardToSimple(5, DRIED_KELP_BLOCK, 2, 20, 8, 0.05f));
		context.register(BUTCHER_5_ENDERITE_SHARD_SHEPHERDS_PIE, shardToSimple(44, SHEPHERDS_PIE, 1, 50, 2, 0.2f));
	}

	static VillagerTrade simpleToShard(Item item, int count, int shards, int xp, int maxUses, float repDiscount) {
		return simpleToShard(item, num(count), shards, xp, maxUses, repDiscount);
	}

	static VillagerTrade simpleToShard(Item item, NumberProvider count, int shards, int xp, int maxUses, float repDiscount) {
		return new VillagerTrade(
			new TradeCost(item, count),
			new ItemStackTemplate(ENDERITE_SHARD, shards),
			maxUses,
			xp,
			repDiscount,
			Optional.empty(),
			List.of()

		);
	}

	static VillagerTrade shardToSimple(int shards, Item item, int count, int xp, int maxUses, float repDiscount) {
		return shardToSimple(num(shards), item, count, xp, maxUses, repDiscount);
	}

	static VillagerTrade shardToSimple(NumberProvider shards, Item item, int count, int xp, int maxUses, float repDiscount) {
		return new VillagerTrade(
			new TradeCost(ENDERITE_SHARD, shards),
			new ItemStackTemplate(item, count),
			maxUses,
			xp,
			repDiscount,
			Optional.empty(),
			List.of()
		);
	}

	static VillagerTrade shardToModifierItem(int shards, Item item, List<LootItemFunction> modifiers, int count, int xp, int maxUses, float repDiscount) {
		return shardToModifierItem(num(shards), item, modifiers, count, xp, maxUses, repDiscount);
	}

	static VillagerTrade shardToModifierItem(NumberProvider shards, Item item, List<LootItemFunction> modifiers, int count, int xp, int maxUses, float repDiscount) {
		return new VillagerTrade(
			new TradeCost(ENDERITE_SHARD, shards),
			new ItemStackTemplate(item, count),
			maxUses,
			xp,
			repDiscount,
			Optional.empty(),
			modifiers
		);
	}

	static VillagerTrade simpleShardToSimple(Item item, int count, int shards, Item result, int resultCount, int xp, int maxUses, float repDiscount) {
		return simpleShardToSimple(item, num(count), num(shards), result, resultCount, xp, maxUses, repDiscount);
	}

	static VillagerTrade simpleShardToSimple(Item item, NumberProvider count, NumberProvider shards, Item result, int resultCount, int xp, int maxUses, float repDiscount) {
		return new VillagerTrade(
			new TradeCost(item, count),
			Optional.of(new TradeCost(ENDERITE_SHARD, shards)),
			new ItemStackTemplate(result, resultCount),
			maxUses,
			xp,
			repDiscount,
			Optional.empty(),
			List.of()
		);
	}
}