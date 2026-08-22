package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Optional;

@Mixin(VillagerTrade.class)
public interface VillagerTradeAccessor {
	@Invoker("<init>")
	static VillagerTrade create(final TradeCost wants,
	                            final Optional<TradeCost> additionalWants,
	                            final ItemStackTemplate gives,
	                            final Holder<NumberProvider> maxUses,
	                            final Holder<NumberProvider> xp,
	                            final Holder<NumberProvider> reputationDiscount,
	                            final Optional<Holder<LootItemCondition>> merchantPredicate,
	                            final Optional<Holder<LootItemFunction>> givenItemModifier,
	                            final Optional<HolderSet<Enchantment>> doubleTradePriceEnchantments) {
		throw new AssertionError("Not transformed!");
	};
}
