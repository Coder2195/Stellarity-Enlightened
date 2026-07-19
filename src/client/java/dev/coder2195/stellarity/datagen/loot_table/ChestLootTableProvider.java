package dev.coder2195.stellarity.datagen.loot_table;

import dev.coder2195.stellarity.registry.StellarityDataComponents;
import dev.coder2195.stellarity.registry.StellarityPotions;
import dev.coder2195.stellarity.tags.StellarityStructureTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Unit;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.equipment.trim.ArmorTrim;
import net.minecraft.world.item.equipment.trim.TrimMaterials;
import net.minecraft.world.item.equipment.trim.TrimPatterns;
import net.minecraft.world.level.saveddata.maps.MapDecorationTypes;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.functions.SetNameFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static dev.coder2195.stellarity.registry.StellarityItems.*;
import static dev.coder2195.stellarity.registry.StellarityLootTables.*;
import static dev.coder2195.stellarity.util.LootUtil.*;
import static net.minecraft.world.item.Items.*;

public class ChestLootTableProvider extends SimpleFabricLootTableSubProvider {
	private final CompletableFuture<HolderLookup.Provider> registryLookup;

	public ChestLootTableProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup, LootContextParamSets.CHEST);
		this.registryLookup = registryLookup;

	}

	@Override
	public void generate(@NonNull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
		var lookup = registryLookup.join();
		var enchantments = lookup.lookupOrThrow(Registries.ENCHANTMENT);
		var trimMaterials = lookup.lookupOrThrow(Registries.TRIM_MATERIAL);
		var trimPatterns = lookup.lookupOrThrow(Registries.TRIM_PATTERN);

		consumer.accept(EXIT_PORTAL, lootTable()
			.withPool(pool().add(item(END_CRYSTAL).apply(count(num(4)))))
			.withPool(pool().add(item(BONE).apply(countAdd(num(2, 5)))))
			.withPool(pool().add(item(STRING).apply(countAdd(num(2, 5)))))
			.withPool(pool().add(item(GUNPOWDER).apply(countAdd(num(2, 5)))))
			.withPool(pool().setRolls(num(1, 2))
				.add(item(OBSIDIAN).setWeight(2).apply(countAdd(num(1, 3))))
				.add(item(CRYING_OBSIDIAN).setWeight(1).apply(countAdd(num(1, 3))))
			)
			.withPool(pool().add(item(ENCHANTED_BOOK)))
		);

		consumer.accept(DUNGEON, lootTable()
			.withPool(pool().add(item(ENDERITE_SHARD).apply(count(num(1, 2)))))
			.withPool(pool().setRolls(num(1, 2))
				.add(item(IRON_INGOT).setWeight(12).apply(count(num(1, 2))))
				.add(item(GOLD_INGOT).setWeight(9).apply(count(num(1, 2))))
			)
			.withPool(pool().setRolls(num(3))
				.add(item(END_CRYSTAL).setWeight(7))
				.add(item(ENDER_PEARL).setWeight(6).apply(count(num(1, 2))))
				.add(item(PHANTOM_ITEM_FRAME).setWeight(6).apply(count(num(1, 2))))
				.add(item(CHORUS_FRUIT).setWeight(8).apply(count(num(1, 2))))
				.add(item(BOOK).setWeight(5).apply(enchant(enchantments, 25, 35)))
				.add(empty().setWeight(2))
				.add(item(ENDER_EYE).setWeight(3).apply(count(num(1, 2))))
			)
			.withPool(pool().add(empty().setWeight(4)).add(item(SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE)))
			.withPool(pool().add(empty().setWeight(9)).add(item(ENDERITE_UPGRADE_SMITHING_TEMPLATE)))
		);

		consumer.accept(CAMPSITE_TRASH, lootTable().withPool(pool().setRolls(num(4, 7))
			.add(item(STICK).apply(count(num(1, 2))))
			.add(item(PAPER).apply(count(num(1, 2))))
			.add(item(FEATHER).apply(count(num(2, 4))))
			.add(item(GOLD_NUGGET).apply(count(num(2, 4))))
			.add(item(COARSE_ENDER_DIRT).apply(count(num(1, 2))))
			.add(item(BONE_MEAL).apply(count(num(2, 5))))
			.add(item(PITCHER_PLANT))
			.add(item(TORCHFLOWER))
			.add(item(END_STONE))
			.add(item(POPPED_CHORUS_FRUIT))
		));
		consumer.accept(CAMPSITE_FOOD, lootTable().withPool(pool().setRolls(num(4, 6))
			.add(item(WHEAT).setWeight(8).apply(count(num(1, 3))))
			.add(item(BEETROOT).setWeight(10).apply(count(num(1, 4))))
			.add(item(CARROT).setWeight(10).apply(count(num(1, 4))))
			.add(item(POTATO).setWeight(10).apply(count(num(1, 4))))
			.add(item(BREAD).setWeight(10).apply(count(num(1, 3))))
			.add(item(CHORUS_FRUIT).setWeight(8).apply(count(num(1, 3))))
		));
		final var vexTrim = trimPatterns.getOrThrow(TrimPatterns.VEX);
		final var campsiteTentEmeraldBooks = pool().setRolls(num(3, 4))
			.add(item(EMERALD_BLOCK).setQuality(1))
			.add(item(EMERALD).setWeight(8).apply(count(num(1, 2))))
			.add(item(BOOK).setWeight(10))
			.add(item(BOOK).setQuality(1).apply(enchant(enchantments, 30, 40)));
		final var campsiteTentArmor = pool().setRolls(num(1, 2))
			.add(item(IRON_HELMET))
			.add(item(IRON_CHESTPLATE))
			.add(item(IRON_LEGGINGS))
			.add(item(IRON_BOOTS))
			.apply(enchant(enchantments, 20, 32).when(randomChance(0.5f)))
			.apply(damage(0.4f, 0.88f))
			.apply(component(DataComponents.TRIM, new ArmorTrim(trimMaterials.getOrThrow(TrimMaterials.IRON), vexTrim)).when(randomChance(0.3333f)))
			.apply(component(DataComponents.TRIM, new ArmorTrim(trimMaterials.getOrThrow(TrimMaterials.GOLD), vexTrim)).when(randomChance(0.3333f)))
			.apply(component(DataComponents.TRIM, new ArmorTrim(trimMaterials.getOrThrow(TrimMaterials.EMERALD), vexTrim)).when(randomChance(0.3333f))
			);
		var campsiteTentTools = pool().setRolls(num(1, 2))
			.add(item(IRON_AXE))
			.add(item(IRON_SWORD))
			.add(item(IRON_SPEAR))
			.add(item(DIAMOND_AXE))
			.add(item(DIAMOND_SWORD))
			.add(item(DIAMOND_SPEAR))
			.add(item(CROSSBOW))
			.add(item(SHIELD))
			.apply(enchant(enchantments, 20, 32).when(randomChance(0.5f)))
			.apply(damage(0.4f, 0.88f));
		var campsiteTentArrows = pool().add(item(ARROW).apply(count(num(9, 18))));
		var campsiteTentFood = pool().setRolls(num(2, 3))
			.add(item(SPLASH_POTION).setWeight(8).apply(potion(Potions.LONG_REGENERATION)))
			.add(item(SPLASH_POTION).setWeight(8).apply(potion(Potions.STRONG_HEALING)))
			.add(item(CHORUS_PIE).setWeight(22).apply(count(num(1, 2))))
			.add(item(POTION).apply(potion(StellarityPotions.CHORUS_JUICE)))
			.add(alternatives(
				item(ENCHANTED_GOLDEN_APPLE).when(randomChance(0.1f)),
				item(GOLDEN_APPLE).setWeight(3)
			));
		consumer.accept(CAMPSITE_TENT, lootTable()
			.withPool(pool().add(item(ENDERITE_SHARD).apply(count(num(2, 5)))))
			.withPool(campsiteTentEmeraldBooks)
			.withPool(campsiteTentArmor)
			.withPool(pool().add(item(MAP)
				.apply(() -> explorationMap(MapDecorationTypes.WOODLAND_MANSION, StellarityStructureTags.EXPLORATION_MAP_VILLAGE, (byte) 3, 96, false))
				.apply(() -> setName(Component.translatable("filled_map.stellarity.end_village"), SetNameFunction.Target.CUSTOM_NAME))
				.apply(() -> setComponents(DataComponentPatch.builder().set(StellarityDataComponents.MARKED_ITEM, Unit.INSTANCE).set(DataComponents.RARITY, Rarity.RARE).build()))
			))
			.withPool(campsiteTentTools)
			.withPool(campsiteTentArrows)
			.withPool(campsiteTentFood)
		);
		consumer.accept(CAMPSITE_TENT_2, lootTable()
			.withPool(pool().add(item(ENDERITE_SHARD).apply(count(num(1, 3)))))
			.withPool(campsiteTentEmeraldBooks.setRolls(num(2)))
			.withPool(campsiteTentArmor.setRolls(num(1)))
			.withPool(campsiteTentTools)
			.withPool(campsiteTentArrows)
			.withPool(campsiteTentFood)
		);
	}
}
