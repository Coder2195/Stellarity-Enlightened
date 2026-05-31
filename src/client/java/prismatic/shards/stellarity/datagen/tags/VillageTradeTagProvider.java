package prismatic.shards.stellarity.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.VillagerTrade;
import org.jspecify.annotations.NonNull;

import static prismatic.shards.stellarity.tags.StellarityVillagerTradeTags.*;

import java.util.concurrent.CompletableFuture;

import static prismatic.shards.stellarity.key.StellarityVillagerTrades.*;

public class VillageTradeTagProvider extends FabricTagsProvider<VillagerTrade> {
	public VillageTradeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
		super(output, Registries.VILLAGER_TRADE, registryLookupFuture);
	}

	@SafeVarargs
	private void register(TagKey<VillagerTrade> tag, ResourceKey<VillagerTrade>... trades) {
		var builder = getOrCreateRawBuilder(tag);
		for (var trade : trades) {
			builder.addElement(trade.identifier());
		}
	}

	@Override
	protected void addTags(HolderLookup.@NonNull Provider provider) {
		register(ARMORER_LEVEL_1, ARMORER_1_COAL_ENDERITE_SHARD, ARMORER_1_CHARCOAL_ENDERITE_SHARD, ARMORER_1_BLAZE_ROD_ENDERITE_SHARD);
		register(ARMORER_LEVEL_1_2, ARMORER_1_ENDERITE_SHARD_SPECIAL_IRON_CHESTPLATE, ARMORER_1_ENDERITE_SHARD_SPECIAL_IRON_HELMET);
		register(ARMORER_LEVEL_2, ARMORER_2_ENDERITE_SHARD_SPECIAL_IRON_BOOTS, ARMORER_2_ENDERITE_SHARD_SPECIAL_IRON_LEGGINGS);
		register(ARMORER_LEVEL_2_2, ARMORER_2_ENDERITE_SHARD_HALLOWED_INGOT, ARMORER_2_ENDERITE_SHARD_CHROUS_PLATING);
		register(ARMORER_LEVEL_3, ARMORER_3_DIAMOND_ENDERITE_SHARD, ARMORER_3_ENDERITE_SHARD_SHIELD_COPPER_ELEKTRA_SHIELD);
		register(ARMORER_LEVEL_4, ARMORER_4_PURPUR_BLOCK_ENDERITE_SHARD_ENDERITE_UPGRADE_SMITHING_TEMPLATE, ARMORER_4_ENDERITE_SHARD_SPECIAL_DIAMOND_LEGGINGS, ARMORER_4_ENDERITE_SHARD_SPECIAL_DIAMOND_BOOTS);
		register(ARMORER_LEVEL_5, ARMORER_5_ENDERITE_SHARD_SPECIAL_DIAMOND_CHESTPLATE, ARMORER_5_ENDERITE_SHARD_SPECIAL_DIAMOND_HELMET);

		register(BUTCHER_LEVEL_1, BUTCHER_1_ENDERMAN_FLESH_ENDERITE_SHARD);
		register(BUTCHER_LEVEL_2, BUTCHER_2_CHARCOAL_ENDERITE_SHARD, BUTCHER_2_CHARCOAL_ENDERITE_SHARD);
		register(BUTCHER_LEVEL_2_2, BUTCHER_2_ENDERMAN_FLESH_ENDERITE_SHARD_FROZEN_CARPACCIO, BUTCHER_2_ENDERMAN_FLESH_ENDERITE_SHARD_GRILLED_ENDERMAN_FLESH);
		register(BUTCHER_LEVEL_3, BUTCHER_3_SHULKER_BODY_ENDERITE_SHARD);
		register(BUTCHER_LEVEL_4, BUTCHER_4_ENDERITE_SHARD_DRIED_KELP_BLOCK);
		register(BUTCHER_LEVEL_5, BUTCHER_5_ENDERITE_SHARD_SHEPHERDS_PIE);
	}
}
