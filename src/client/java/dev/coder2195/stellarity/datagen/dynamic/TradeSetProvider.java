package dev.coder2195.stellarity.datagen.dynamic;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.TradeSet;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import org.jspecify.annotations.Nullable;
import dev.coder2195.stellarity.tags.StellarityVillagerTradeTags;
import dev.coder2195.stellarity.util.LootUtil;

import java.util.List;
import java.util.Optional;

import static dev.coder2195.stellarity.key.StellarityVillagerTradeSets.*;

public interface TradeSetProvider {
	record Entry(ResourceKey<TradeSet> tradeSet, TagKey<VillagerTrade> tradeTag, NumberProvider num,
	             boolean allowDuplicates, @Nullable
							 Identifier random) {
		public Entry(ResourceKey<TradeSet> tradeSet, TagKey<VillagerTrade> tradeTag, int num, boolean allowDuplicates) {
			this(tradeSet, tradeTag, LootUtil.num(num), allowDuplicates, null);
		}
	}

	static void bootstrap(BootstrapContext<TradeSet> context) {

		HolderGetter<VillagerTrade> tradeTags = context.lookup(Registries.VILLAGER_TRADE);

		for (var entry : List.of(
			new Entry(ARMORER_LEVEL_1, StellarityVillagerTradeTags.ARMORER_LEVEL_1, 1, false),
			new Entry(ARMORER_LEVEL_1_2, StellarityVillagerTradeTags.ARMORER_LEVEL_1_2, 1, false),
			new Entry(ARMORER_LEVEL_2, StellarityVillagerTradeTags.ARMORER_LEVEL_2, 1, false),
			new Entry(ARMORER_LEVEL_2_2, StellarityVillagerTradeTags.ARMORER_LEVEL_2_2, 1, false),
			new Entry(ARMORER_LEVEL_3, StellarityVillagerTradeTags.ARMORER_LEVEL_3, 2, false),
			new Entry(ARMORER_LEVEL_4, StellarityVillagerTradeTags.ARMORER_LEVEL_4, 3, false),
			new Entry(ARMORER_LEVEL_5, StellarityVillagerTradeTags.ARMORER_LEVEL_5, 2, false),
			new Entry(BUTCHER_LEVEL_1, StellarityVillagerTradeTags.BUTCHER_LEVEL_1, 1, false),
			new Entry(BUTCHER_LEVEL_2, StellarityVillagerTradeTags.BUTCHER_LEVEL_2, 1, false),
			new Entry(BUTCHER_LEVEL_2_2, StellarityVillagerTradeTags.BUTCHER_LEVEL_2_2, 1, false),
			new Entry(BUTCHER_LEVEL_3, StellarityVillagerTradeTags.BUTCHER_LEVEL_3, 1, false),
			new Entry(BUTCHER_LEVEL_4, StellarityVillagerTradeTags.BUTCHER_LEVEL_4, 1, false),
			new Entry(BUTCHER_LEVEL_5, StellarityVillagerTradeTags.BUTCHER_LEVEL_5, 1, false)
		)) {
			context.register(entry.tradeSet, new TradeSet(
				tradeTags.getOrThrow(entry.tradeTag),
				entry.num,
				entry.allowDuplicates,
				Optional.ofNullable(entry.random)
			));
		}
	}
}
