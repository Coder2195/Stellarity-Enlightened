package dev.coder2195.stellarity.datagen.loot_table;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.StellarityEnchantments;
import dev.coder2195.stellarity.registry.StellarityLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static dev.coder2195.stellarity.registry.StellarityItems.*;
import static dev.coder2195.stellarity.util.LootUtil.*;
import static net.minecraft.world.item.Items.*;

@SuppressWarnings("DuplicatedCode")
public class ArchaeologyLootTableProvider extends SimpleFabricLootTableSubProvider {
	private final CompletableFuture<HolderLookup.Provider> registryLookup;

	public ArchaeologyLootTableProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup, LootContextParamSets.ARCHAEOLOGY);
		this.registryLookup = registryLookup;
	}

	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
		var lookup = registryLookup.join();
		var enchantments = lookup.lookupOrThrow(Registries.ENCHANTMENT);

		consumer.accept(StellarityLootTables.END_SHIPWRECK_ARCHAEOLOGY_DUNES_COMMON, lootTable().withPool(pool()
				.add(item(GOLD_INGOT).setWeight(2))
				.add(item(CHORUS_FRUIT).setWeight(2))
				.add(item(DYE.magenta()).setWeight(2))
				.add(item(DYE.purple()).setWeight(2))
				.add(item(DYE.black()).setWeight(2))
				.add(item(DYE.white()).setWeight(2))
				.add(item(DYE.pink()).setWeight(2))
				.add(item(DYED_CANDLE.magenta()).setWeight(2))
				.add(item(DYED_CANDLE.purple()).setWeight(2))
				.add(item(DYED_CANDLE.pink()).setWeight(2))
				.add(item(DYED_CANDLE.black()).setWeight(2))
				.add(item(COAL))
				.add(item(PITCHER_POD))
				.add(item(DEAD_BUSH))
				.add(item(FLOWER_POT))
				.add(item(STRING))
				.add(item(LEAD))
				.add(item(AZALEA))
				.add(item(TERRACOTTA))
				.add(item(ENDERITE_SHARD).setWeight(5))
			));

		consumer.accept(StellarityLootTables.END_SHIPWRECK_ARCHAEOLOGY_DUNES_RARE, lootTable().withPool(pool()
			.add(item(SKULL_POTTERY_SHERD).setWeight(2))
			.add(item(PRIZE_POTTERY_SHERD).setWeight(2))
			.add(item(EXPLORER_POTTERY_SHERD).setWeight(2))
			.add(item(FRIEND_POTTERY_SHERD).setWeight(2))
			.add(item(ENDERITE_UPGRADE_SMITHING_TEMPLATE).setWeight(2))
			.add(item(MUSIC_DISC_FIRES_OF_HOKKAI).setWeight(2))
			// todo: add void pendant
			.add(item(BOOK).apply(enchant(enchantments, StellarityEnchantments.DUNE_SPEED)))
		));
		
		consumer.accept(StellarityLootTables.END_SHIPWRECK_ARCHAEOLOGY_NORMAL_COMMON, lootTable().withPool(pool()
			.add(item(EMERALD).setWeight(2))
			.add(item(CHORUS_FRUIT).setWeight(2))
			.add(item(DYE.magenta()).setWeight(2))
			.add(item(DYE.purple()).setWeight(2))
			.add(item(DYE.black()).setWeight(2))
			.add(item(DYE.white()).setWeight(2))
			.add(item(DYE.pink()).setWeight(2))
			.add(item(DYED_CANDLE.magenta()).setWeight(2))
			.add(item(DYED_CANDLE.purple()).setWeight(2))
			.add(item(DYED_CANDLE.pink()).setWeight(2))
			.add(item(DYED_CANDLE.black()).setWeight(2))
			.add(item(STAINED_GLASS_PANE.magenta()))
			.add(item(STAINED_GLASS_PANE.white()))
			.add(item(STAINED_GLASS_PANE.purple()))
			.add(item(STAINED_GLASS_PANE.magenta()))
			.add(item(STAINED_GLASS_PANE.black()))
			.add(item(STAINED_GLASS_PANE.pink()))
			.add(item(GLASS_PANE))
			.add(item(WARPED_HANGING_SIGN))
			.add(item(CRIMSON_HANGING_SIGN))
			.add(item(GOLD_NUGGET))
			.add(item(COAL))
			.add(item(TORCHFLOWER_SEEDS))
			.add(item(PITCHER_POD))
			.add(item(DEAD_BUSH))
			.add(item(FLOWER_POT))
			.add(item(STRING))
			.add(item(LEAD))
			.add(item(ENDERITE_SHARD).setWeight(4))
		));

		consumer.accept(StellarityLootTables.END_SHIPWRECK_ARCHAEOLOGY_NORMAL_RARE, lootTable().withPool(pool()
			.add(item(SKULL_POTTERY_SHERD).setWeight(2))
			.add(item(PRIZE_POTTERY_SHERD).setWeight(2))
			.add(item(EXPLORER_POTTERY_SHERD).setWeight(2))
			.add(item(FRIEND_POTTERY_SHERD).setWeight(2))
			.add(item(ENDERITE_UPGRADE_SMITHING_TEMPLATE).setWeight(2))
			.add(item(MUSIC_DISC_FIRES_OF_HOKKAI).setWeight(2))
			// todo: add void pendant
		));
	}

	@Override
	public void run() {
		Stellarity.LOGGER.info("What do i do here fabric team?");
	}
}
