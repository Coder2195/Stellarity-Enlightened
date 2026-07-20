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
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Unit;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantments;
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

	@SuppressWarnings("DuplicatedCode")
	@Override
	public void generate(@NonNull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
		var lookup = registryLookup.join();
		var enchantments = lookup.lookupOrThrow(Registries.ENCHANTMENT);
		var trimMaterials = lookup.lookupOrThrow(Registries.TRIM_MATERIAL);
		var trimPatterns = lookup.lookupOrThrow(Registries.TRIM_PATTERN);

		consumer.accept(EXIT_PORTAL, lootTable()
			.withPool(pool().add(item(END_CRYSTAL).apply(count(4))))
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
			.withPool(pool().add(item(ENDERITE_SHARD).apply(count(1, 2))))
			.withPool(pool().setRolls(num(1, 2))
				.add(item(IRON_INGOT).setWeight(12).apply(count(1, 2)))
				.add(item(GOLD_INGOT).setWeight(9).apply(count(1, 2)))
			)
			.withPool(pool().setRolls(num(3))
				.add(item(END_CRYSTAL).setWeight(7))
				.add(item(ENDER_PEARL).setWeight(6).apply(count(1, 2)))
				.add(item(PHANTOM_ITEM_FRAME).setWeight(6).apply(count(1, 2)))
				.add(item(CHORUS_FRUIT).setWeight(8).apply(count(1, 2)))
				.add(item(BOOK).setWeight(5).apply(enchant(enchantments, 25, 35)))
				.add(empty().setWeight(2))
				.add(item(ENDER_EYE).setWeight(3).apply(count(1, 2)))
			)
			.withPool(pool().add(empty().setWeight(4)).add(item(SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE)))
			.withPool(pool().add(empty().setWeight(9)).add(item(ENDERITE_UPGRADE_SMITHING_TEMPLATE)))
		);

		consumer.accept(CAMPSITE_TRASH, lootTable().withPool(pool().setRolls(num(4, 7))
			.add(item(STICK).apply(count(1, 2)))
			.add(item(PAPER).apply(count(1, 2)))
			.add(item(FEATHER).apply(count(2, 4)))
			.add(item(GOLD_NUGGET).apply(count(2, 4)))
			.add(item(COARSE_ENDER_DIRT).apply(count(1, 2)))
			.add(item(BONE_MEAL).apply(count(2, 5)))
			.add(item(PITCHER_PLANT))
			.add(item(TORCHFLOWER))
			.add(item(END_STONE))
			.add(item(POPPED_CHORUS_FRUIT))
		));
		consumer.accept(CAMPSITE_FOOD, lootTable().withPool(pool().setRolls(num(4, 6))
			.add(item(WHEAT).setWeight(8).apply(count(1, 3)))
			.add(item(BEETROOT).setWeight(10).apply(count(1, 4)))
			.add(item(CARROT).setWeight(10).apply(count(1, 4)))
			.add(item(POTATO).setWeight(10).apply(count(1, 4)))
			.add(item(BREAD).setWeight(10).apply(count(1, 3)))
			.add(item(CHORUS_FRUIT).setWeight(8).apply(count(1, 3)))
		));
		final var vexTrim = trimPatterns.getOrThrow(TrimPatterns.VEX);
		final var campsiteTentEmeraldBooks = pool().setRolls(num(3, 4))
			.add(item(EMERALD_BLOCK).setQuality(1))
			.add(item(EMERALD).setWeight(8).apply(count(1, 2)))
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
		var campsiteTentArrows = pool().add(item(ARROW).apply(count(9, 18)));
		var campsiteTentFood = pool().setRolls(num(2, 3))
			.add(item(SPLASH_POTION).setWeight(8).apply(potion(Potions.LONG_REGENERATION)))
			.add(item(SPLASH_POTION).setWeight(8).apply(potion(Potions.STRONG_HEALING)))
			.add(item(CHORUS_PIE).setWeight(22).apply(count(1, 2)))
			.add(item(POTION).apply(potion(StellarityPotions.CHORUS_JUICE)))
			.add(alternatives(
				item(ENCHANTED_GOLDEN_APPLE).when(randomChance(0.1f)),
				item(GOLDEN_APPLE).setWeight(3)
			));
		consumer.accept(CAMPSITE_TENT, lootTable()
			.withPool(pool().add(item(ENDERITE_SHARD).apply(count(2, 5))))
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
			.withPool(pool().add(item(ENDERITE_SHARD).apply(count(1, 3))))
			.withPool(campsiteTentEmeraldBooks.setRolls(num(2)))
			.withPool(campsiteTentArmor.setRolls(num(1)))
			.withPool(campsiteTentTools)
			.withPool(campsiteTentArrows)
			.withPool(campsiteTentFood)
		);

		consumer.accept(VILLAGE_APIARY, lootTable().withPool(pool().setRolls(num(4, 6))
			.add(item(HONEYCOMB_BLOCK).apply(count(1, 2)))
			.add(item(HONEYCOMB).setWeight(3).apply(count(2, 3)))
			.add(item(HONEY_BOTTLE).setWeight(2))
			.add(item(HONEY_BLOCK).apply(count(1, 2)))
		));
		consumer.accept(VILLAGE_ARCHER_TOWER, lootTable()
			.withPool(pool().setRolls(num(2, 4)).add(item(ARROW).apply(count(binomial(14, 0.44f)))))
			.withPool(pool().add(item(BOW).apply(damage(0.1f, 0.77f)).apply(enchant(enchantments, 30, 30))))
		);
		consumer.accept(VILLAGE_ARMORER, lootTable()
			.withPool(pool().setRolls(num(1, 2))
				.add(item(IRON_CHESTPLATE).apply(enchant(enchantments, 29, 39)))
				.add(item(IRON_HELMET).apply(enchant(enchantments, 29, 39)))
				.add(item(IRON_LEGGINGS).apply(enchant(enchantments, 29, 39)))
				.add(item(IRON_BOOTS).apply(enchant(enchantments, 29, 39)))
				.add(item(DIAMOND_CHESTPLATE).apply(enchant(enchantments, 29, 39)))
				.add(item(DIAMOND_HELMET).apply(enchant(enchantments, 29, 39)))
				.add(item(DIAMOND_LEGGINGS).apply(enchant(enchantments, 29, 39)))
				.add(item(DIAMOND_BOOTS).apply(enchant(enchantments, 29, 39)))
			).withPool(pool().setRolls(num(5, 7))
				.add(item(COAL).setWeight(10).apply(count(2, 3)))
				.add(group(item(GOLD_INGOT).setWeight(8), item(IRON_INGOT).setWeight(8)))
			)
		);
		consumer.accept(VILLAGE_BUTCHER, lootTable()
			.withPool(pool().setRolls(num(5, 7)).add(item(BEEF)).add(item(MUTTON)).add(item(PORKCHOP)).add(item(RABBIT)))
			.withPool(pool().setRolls(num(7)).add(item(ICE)).add(item(SNOWBALL)).apply(count(1, 2)))
		);
		consumer.accept(VILLAGE_CARTOGRAPHER, lootTable()
			.withPool(pool().setRolls(num(4, 6))
				.add(item(MAP).setWeight(7))
				.add(item(PAPER).setWeight(15).apply(count(2, 3)))
				.add(item(COMPASS).setWeight(2))
				.add(item(SHEARS))
				.add(item(GLASS_PANE).setWeight(4).apply(count(1, 2)))
			).withPool(pool().setRolls(num(-1, 1)).add(item(MAP)
				.apply(() -> explorationMap(MapDecorationTypes.PURPLE_BANNER, StellarityStructureTags.EXPLORATION_MAP_END_CITY, (byte) 3, 96, true))
				.apply(() -> setName(Component.translatable("filled_map.stellarity.end_city").setStyle(Style.EMPTY.withItalic(false)), SetNameFunction.Target.CUSTOM_NAME))
				.apply(() -> setComponents(DataComponentPatch.builder().set(DataComponents.RARITY, Rarity.RARE).set(StellarityDataComponents.MARKED_ITEM, Unit.INSTANCE).build()))
			))
		);
		consumer.accept(VILLAGE_CARTOGRAPHER_SHULKER_BOX, lootTable().withPool(pool().setRolls(num(2, 3))
			.add(item(PAPER).apply(count(1, 3))).add(item(GLASS_PANE).apply(count(1, 2)))
		));
		consumer.accept(VILLAGE_CLERIC, lootTable().withPool(pool().setRolls(num(6, 7))
			.add(item(NETHER_WART).setWeight(6).apply(count(2, 4)))
			.add(item(BLAZE_POWDER).setWeight(6).apply(count(1, 2)))
			.add(item(POTION).setWeight(4).apply(potion(Potions.WATER)))
			.add(item(GOLD_INGOT).setWeight(2).apply(count(1, 2)))
			.add(item(ROTTEN_FLESH).setWeight(4).setQuality(-1).apply(count(2, 3)))
			.add(item(EXPERIENCE_BOTTLE).setQuality(1).setWeight(2).apply(count(1, 2)))
		));
		consumer.accept(VILLAGE_FISHERMAN, lootTable()
			.withPool(pool().setRolls(num(5, 7)).add(item(SALMON)).add(item(COD)))
			.withPool(pool().setRolls(num(7)).add(group(item(ICE), item(SNOWBALL))).apply(count(1, 2)))
		);
		consumer.accept(VILLAGE_FISHERMAN_SHULKER_BOX, lootTable()
			.withPool(pool().setRolls(num(-1, 1)).add(item(FISHING_ROD).apply(damage(0.2f, 0.85f))))
			.withPool(pool().setRolls(num(0, 1)).add(item(PRISMARINE_CRYSTALS).apply(count(2, 4))))
			.withPool(pool().setRolls(num(0, 2)).add(item(LILY_PAD)).add(item(STICK)).add(alternatives(
				item(GLOW_INK_SAC).when(randomChance(0.5f)),
				item(INK_SAC)
			)))
		);
		consumer.accept(VILLAGE_FLETCHER, lootTable().withPool(pool().setRolls(num(5, 7))
			.add(item(ARROW).setWeight(9).apply(count(2, 5)))
			.add(item(FEATHER).setWeight(6).apply(count(1, 2)))
			.add(item(FLINT).setWeight(6).apply(count(1, 2)))
			.add(item(STICK).setWeight(6).apply(count(1, 2)))
			.add(item(BOW).setWeight(3).apply(damage(0.4f, 0.9f)))
			.add(item(BOOK).setQuality(1).setWeight(4).apply(
				enchant(enchantments, Enchantments.POWER, Enchantments.PUNCH, Enchantments.FLAME, Enchantments.INFINITY, Enchantments.MENDING)
			))
		));
		consumer.accept(VILLAGE_FLETCHER_SHULKER_BOX, lootTable().withPool(pool().setRolls(num(2, 3))
			.add(item(FEATHER).setWeight(6).apply(count(1, 2)))
			.add(item(FLINT).setWeight(6).apply(count(1, 2)))
			.add(item(STICK).setWeight(6).apply(count(1, 2)))
		));
		consumer.accept(VILLAGE_LEATHERWORKER, lootTable().withPool(pool().setRolls(num(3, 5))
			.add(item(LEATHER).setWeight(9).apply(count(4, 6)))
			.add(item(LEATHER_CHESTPLATE).setWeight(4).apply(enchant()))
			.add(item(LEATHER_BOOTS).setWeight(4).apply(enchant()))
			.add(item(LEATHER_HELMET).setWeight(4).apply(enchant()))
			.add(item(LEATHER_LEGGINGS).setWeight(4).apply(enchant()))
			.add(item(SADDLE).setWeight(3).setQuality(1))
			.add(item(LEATHER_HORSE_ARMOR))
		));
		consumer.accept(VILLAGE_LIBRARIAN, lootTable()
			.withPool(pool().setRolls(num(5, 7))
				.add(item(BOOK).setQuality(-1).setWeight(7).apply(count(1, 2)))
				.add(item(PAPER).setWeight(6).setQuality(-1).apply(count(1, 2)))
				.add(item(BOOK).setWeight(5).setQuality(1).apply(enchant(enchantments, 25, 35)))
				.add(item(FEATHER).setQuality(-1).setWeight(4).apply(count(0, 2)))
				.add(item(INK_SAC).setWeight(3).setQuality(-1).apply(count(0, 2)))
			).withPool(pool().setRolls(num(0, 1)).add(item(COMPASS)))
		);
		consumer.accept(VILLAGE_MARKET, lootTable().withPool(pool().setRolls(num(4, 6))
			.add(item(GOLD_NUGGET).apply(count(1, 3)))
			.add(item(IRON_NUGGET).apply(count(1, 3)))
			.add(item(COPPER_NUGGET).apply(count(1, 3)))
			.add(item(BOOK)).add(item(FEATHER)).add(item(STRING)).add(item(LANTERN)).add(item(EMERALD))
		));
		consumer.accept(VILLAGE_MASON, lootTable().withPool(pool().setRolls(num(5, 7))
			.add(item(BRICK).setWeight(2).apply(count(2, 3)))
			.add(item(CLAY_BALL).setWeight(3).apply(count(2, 4)))
			.add(item(END_STONE).setWeight(2).apply(countAdd(2, 3)))
			.add(item(END_STONE_BRICKS).setWeight(4).apply(count(3, 4)))
			.add(item(GLAZED_TERRACOTTA.magenta())).add(item(GLAZED_TERRACOTTA.purple())).add(item(GLAZED_TERRACOTTA.pink()))
		));
		consumer.accept(VILLAGE_SHEPHERD, lootTable().withPool(pool().setRolls(num(3, 5))
			.add(item(WOOL.white()).setWeight(6).apply(countAdd(1, 3)))
			.add(item(WOOL.purple()).setWeight(3).apply(countAdd(1, 3)))
			.add(item(WOOL.magenta()).setWeight(2).apply(countAdd(1, 3)))
			.add(item(WOOL.pink()).setWeight(2).apply(countAdd(1, 3)))
			.add(item(SHEARS).setQuality(1)).add(item(PAINTING).setWeight(4))
		));
		consumer.accept(VILLAGE_SHEPHERD_SHULKER_BOX, lootTable().withPool(pool().setRolls(num(0, 2)).add(item(WHEAT).apply(count(1, 2)))));
		consumer.accept(VILLAGE_TOOLSMITH, lootTable()
			.withPool(pool().setRolls(num(1, 2))
				.add(item(DIAMOND_AXE).apply(enchant(enchantments, 29, 39)))
				.add(item(DIAMOND_PICKAXE).apply(enchant(enchantments, 29, 39)))
				.add(item(DIAMOND_SHOVEL).apply(enchant(enchantments, 29, 39)))
				.add(item(DIAMOND_HOE).apply(enchant(enchantments, 29, 39)))
				.add(item(IRON_AXE).apply(enchant(enchantments, 29, 39)))
				.add(item(IRON_PICKAXE).apply(enchant(enchantments, 29, 39)))
				.add(item(IRON_SHOVEL).apply(enchant(enchantments, 29, 39)))
				.add(item(IRON_HOE).apply(enchant(enchantments, 29, 39)))
			).withPool(pool().setRolls(num(5, 7))
				.add(item(COAL).setWeight(10).apply(count(2, 3)))
				.add(group(item(GOLD_INGOT).setWeight(8), item(IRON_INGOT).setWeight(8)))
			).apply(count(1, 2))
		);
		consumer.accept(VILLAGE_TREE_FARM_1, lootTable()
			.withPool(pool().setRolls(num(1, 2))
				.add(item(OAK_LOG).apply(count(2, 3)))
				.add(item(OAK_SAPLING).apply(count(1, 2)))
				.add(item(OAK_LEAVES).apply(count(3, 5)))
			).withPool(pool().setRolls(num(1, 2))
				.add(item(BIRCH_LOG).apply(count(2, 3)))
				.add(item(BIRCH_SAPLING).apply(count(1, 2)))
				.add(item(BIRCH_LEAVES).apply(count(3, 5)))
			).withPool(pool().setRolls(num(1, 2))
				.add(item(DARK_OAK_LOG).apply(count(2, 3)))
				.add(item(DARK_OAK_SAPLING).apply(count(1, 2)))
				.add(item(DARK_OAK_LEAVES).apply(count(3, 5)))
			).withPool(pool().setRolls(num(1, 2))
				.add(item(PALE_OAK_LOG).apply(count(2, 3)))
				.add(item(PALE_OAK_SAPLING).apply(count(1, 2)))
				.add(item(PALE_OAK_LEAVES).apply(count(3, 5)))
			)
		);
		consumer.accept(VILLAGE_TREE_FARM_2, lootTable()
			.withPool(pool().setRolls(num(1, 2))
				.add(item(ACACIA_LOG).apply(count(2, 3)))
				.add(item(ACACIA_SAPLING).apply(count(1, 2)))
				.add(item(ACACIA_LEAVES).apply(count(3, 5)))
			).withPool(pool().setRolls(num(1, 2))
				.add(item(SPRUCE_LOG).apply(count(2, 3)))
				.add(item(SPRUCE_SAPLING).apply(count(1, 2)))
				.add(item(SPRUCE_LEAVES).apply(count(3, 5)))
			).withPool(pool().setRolls(num(1, 2))
				.add(item(JUNGLE_LOG).apply(count(2, 3)))
				.add(item(JUNGLE_SAPLING).apply(count(1, 2)))
				.add(item(JUNGLE_LEAVES).apply(count(3, 5)))
				.add(item(COCOA_BEANS).apply(count(1, 2)))
			)
		);
		consumer.accept(VILLAGE_WEAPONSMITH, lootTable()
			.withPool(pool().setRolls(num(1, 2))
				.add(item(IRON_SWORD).apply(enchant(enchantments, 29, 39)))
				.add(item(IRON_SPEAR).apply(enchant(enchantments, 29, 39)))
				.add(item(GOLDEN_SWORD).apply(enchant(enchantments, 29, 39)))
				.add(item(GOLDEN_SPEAR).apply(enchant(enchantments, 29, 39)))
				.add(item(DIAMOND_SWORD).apply(enchant(enchantments, 29, 39)))
				.add(item(DIAMOND_SPEAR).apply(enchant(enchantments, 29, 39)))
			).withPool(pool().setRolls(num(5, 7))
				.add(item(COAL).setWeight(10).apply(count(2, 3)))
				.add(group(item(GOLD_INGOT).setWeight(8), item(IRON_INGOT).setWeight(8)))
			).apply(count(1, 3))
		);
		consumer.accept(VILLAGE_HOUSE_BOOKWORM, lootTable()
			.withPool(pool().setRolls(num(3)).add(item(BOOK).apply(count(1, 2))))
			.withPool(pool().setRolls(num(1)).add(lootTable(VILLAGE_HOUSE_COMMON)))
		);
		consumer.accept(VILLAGE_HOUSE_COMMON, lootTable().withPool(pool().setRolls(num(3, 4))
			.add(item(GOLD_NUGGET).apply(count(1, 3)))
			.add(item(DANDELION).setWeight(2)).add(item(POPPY))
			.add(item(POTATO).setWeight(10).apply(count(1, 3)))
			.add(item(BREAD).setWeight(10).apply(count(1, 4)))
			.add(item(APPLE).setWeight(10).apply(count(1, 3)))
			.add(item(BOOK)).add(item(FEATHER)).add(item(FEATHER).setWeight(2).apply(count(1, 3)))
		));
		consumer.accept(VILLAGE_HOUSE_LUSH, lootTable()
			.withPool(pool().setRolls(num(3))
				.add(item(MOSS_BLOCK).apply(count(1, 2)))
				.add(item(PALE_MOSS_BLOCK).apply(count(1, 2)))
				.add(item(GLOW_BERRIES).apply(count(2, 3)))
			).withPool(pool().setRolls(num(1)).add(lootTable(VILLAGE_HOUSE_COMMON)))
		);
		consumer.accept(VILLAGE_HOUSE_MUSIC, lootTable()
			.withPool(pool().setRolls(num(3))
				.add(item(MUSIC_DISC_STRAD)).add(item(MUSIC_DISC_CAT)).add(item(MUSIC_DISC_WAIT)).add(item(MUSIC_DISC_MALL)).add(empty().setWeight(3))
			).withPool(pool().setRolls(num(1)).add(lootTable(VILLAGE_HOUSE_COMMON)))
		);
		consumer.accept(VILLAGE_HOUSE_REGULAR, lootTable().withPool(pool().setRolls(num(1)).add(lootTable(VILLAGE_HOUSE_COMMON))));
		consumer.accept(VILLAGE_HOUSE_REGULAR_SHULKER_BOX, lootTable().withPool(pool().setRolls(num(2))
			.add(lootTable(VILLAGE_HOUSE_COMMON).setWeight(37))
			.add(item(EMERALD).setWeight(2).apply(count(1, 3)))
		));
		consumer.accept(VILLAGE_HOUSE_WARPED, lootTable()
			.withPool(pool().setRolls(num(3))
				.add(item(WARPED_WART_BLOCK).apply(count(1, 2))).add(item(WARPED_FUNGUS).apply(count(2, 3)))
			).withPool(pool().setRolls(num(1)).add(lootTable(VILLAGE_HOUSE_COMMON)))
		);
		consumer.accept(VILLAGE_CENTER_AETHER, lootTable()
			.withPool(pool().setRolls(num(4, 6)).add(item(WOOL.lightBlue()).apply(count(1, 3))).add(item(WARPED_FENCE)))
			.withPool(pool().setRolls(num(1)).add(item(GLOWSTONE).apply(count(2, 5))))
			.withPool(pool().setRolls(num(0, 1)).add(item(WATER_BUCKET)))
		);
		consumer.accept(VILLAGE_CENTER_TOWN_HALL, lootTable()
			.withPool(pool().setRolls(num(3)).setBonusRolls(binomial(1, 0.25f))
				.add(item(IRON_INGOT).setWeight(10).apply(count(1, 2)))
				.add(item(GOLD_INGOT).setWeight(7).apply(count(1, 2)))
				.add(item(DIAMOND).setWeight(2))
			).withPool(pool().setRolls(num(3))
				.add(item(WHEAT).apply(count(2)))
				.add(item(APPLE).apply(count(1, 2)))
				.add(item(BREAD).apply(count(2)))
			).withPool(pool().setRolls(num(0, 3))
				.add(item(LEATHER).apply(count(1, 3))).add(item(WOOL.white()).apply(count(1, 2)))
			).withPool(pool().setRolls(num(2))
				.add(item(IRON_NUGGET).apply(count(3, 6))).add(item(GOLD_NUGGET).apply(count(3, 6)))
			)
		);
		consumer.accept(VILLAGE_CENTER_MARKET_BAKER, lootTable().withPool(pool().setRolls(num(5, 7))
			.add(item(BREAD).setWeight(2))
			.add(item(COOKIE).setWeight(1).apply(count(1, 2)))
			.add(item(CAKE).setQuality(1))
		));
		consumer.accept(VILLAGE_CENTER_MARKET_ENCHANTS, lootTable().withPool(pool().setRolls(num(5, 7))
			.add(item(BOOK).setWeight(12).setQuality(-2).apply(countAdd(1, 2)))
			.add(item(PAPER).setQuality(-4).setWeight(9).apply(countAdd(num(1, 4))))
			.add(item(ENCHANTED_BOOK).setWeight(4).setQuality(3).apply(enchant(enchantments, 20, 30)))
			.add(item(OBSIDIAN).setWeight(5).apply(count(1, 3)))
			.add(item(AMETHYST_SHARD).setWeight(8).apply(count(2, 3)))
		));
		consumer.accept(VILLAGE_CENTER_MARKET_EXPLORER, lootTable().withPool(pool().setRolls(num(5, 7))
			.add(item(MAP).setWeight(10).setQuality(-1).apply(count(1, 2)))
			.add(item(PAPER).setQuality(-2).setWeight(15).apply(count(1, 3)))
			.add(item(COMPASS).setWeight(5))
		));

	}
}
