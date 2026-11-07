package dev.coder2195.stellarity.datagen.loot_table;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static dev.coder2195.stellarity.registry.StellarityLootTables.*;
import static dev.coder2195.stellarity.util.LootUtil.*;
import static dev.coder2195.stellarity.util.ValueUtil.num;
import static net.minecraft.world.item.Items.*;

public class GenericLootTableProvider extends SimpleFabricLootTableSubProvider {
	private final CompletableFuture<HolderLookup.Provider> registryLookup;

	public GenericLootTableProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup, LootContextParamSets.ALL_PARAMS);
		this.registryLookup = registryLookup;
	}

	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
		var lookup = registryLookup.join();
		var enchantments = lookup.lookupOrThrow(Registries.ENCHANTMENT);

		consumer.accept(CHAPEL_OF_LIGHT_EQUIPMENT, lootTable()
			.withPool(pool().setRolls(num(3, 5))
				.add(item(TORCHFLOWER_SEEDS))
				.add(item(PINK_PETALS).apply(count(1, 2)))
				.add(item(PITCHER_POD))
			).withPool(pool().setRolls(num(0, 1))
				.add(item(DIAMOND_HOE).apply(damage(0.3f, 0.79f)))
				// TODO: starless scythe
				.add(item(GOLDEN_AXE).apply(damage(0.3f, 0.79f)))
			).withPool(pool().add(item(SHEARS).apply(damage(0.33f, 0.7f))))
			.withPool(pool().setRolls(num(-2, 1)).add(item(BOOK).apply(enchant(enchantments, Enchantments.FEATHER_FALLING, Enchantments.EFFICIENCY))))
		);

		consumer.accept(CHAPEL_OF_LIGHT_GARDEN, lootTable().withPool(pool().setRolls(num(6, 10))
			.add(item(CHERRY_SAPLING).apply(count(1, 2)))
			.add(item(PINK_TULIP).apply(count(2, 3)))
			.add(item(WHITE_TULIP).apply(count(2, 3)))
			.add(item(ALLIUM).apply(count(2, 3)))
			.add(item(POPPY).apply(count(2, 3)))
			.add(item(DANDELION).apply(count(2, 3)))
			.add(item(BLUE_ORCHID).apply(count(2, 3)))
			.add(item(PINK_PETALS).apply(count(2, 3)))
			.add(item(TORCHFLOWER).apply(count(1, 2)))
			.add(item(PITCHER_PLANT).apply(count(1, 2)))
		));

		consumer.accept(CHAPEL_OF_LIGHT_POT, lootTable().withPool(pool()
			.add(item(PINK_PETALS).setWeight(3))
			.add(item(HANGING_ROOTS).setWeight(6))
			.add(item(GLOW_BERRIES))
		));

	}
}
