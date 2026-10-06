package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static dev.coder2195.stellarity.registry.StellarityItems.*;
import static net.minecraft.core.registries.BuiltInRegistries.CREATIVE_MODE_TAB;

public interface StellarityCreativeModeTabs {
	ItemLike[] BLOCKS_ITEMS = Stream.concat(Stream.concat(Stream.<ItemLike>of(
		ASHEN_FROGLIGHT,
		ENDER_DIRT,
		ENDER_GRASS_BLOCK,
		ROOTED_ENDER_DIRT,
		COARSE_ENDER_DIRT,
		ENDER_DIRT_PATH,
		ENDERITE_ORE,
		ENDERITE_BLOCK,
		ALTAR_OF_THE_ACCURSED,
		PHANTOM_ITEM_FRAME,
		ENDERITE_BLOCK,
		AMETHYII_PLANKS, AMETHYII_SAPLING, AMETHYII_LOG, STRIPPED_AMETHYII_LOG, STRIPPED_AMETHYII_WOOD, AMETHYII_WOOD, AMETHYII_LEAVES, AMETHYII_SLAB, AMETHYII_SHELF, AMETHYII_FENCE, AMETHYII_STAIRS, AMETHYII_BUTTON, AMETHYII_PRESSURE_PLATE, AMETHYII_DOOR, AMETHYII_TRAPDOOR, AMETHYII_FENCE_GATE, AMETHYII_SIGN, AMETHYII_HANGING_SIGN,
		HALLOWED_PLANKS, HALLOWED_SAPLING, HALLOWED_LOG, STRIPPED_HALLOWED_LOG, STRIPPED_HALLOWED_WOOD, HALLOWED_WOOD, HALLOWED_LEAVES, HALLOWED_SLAB, HALLOWED_SHELF, HALLOWED_FENCE, HALLOWED_STAIRS, HALLOWED_BUTTON, HALLOWED_PRESSURE_PLATE, HALLOWED_DOOR, HALLOWED_TRAPDOOR, HALLOWED_FENCE_GATE, HALLOWED_SIGN, HALLOWED_HANGING_SIGN
	), StellarityItems.COLORED_HALLOWED_LEAVES.asList().stream()), Stream.<ItemLike>of(
		SHRUBBED_PLANKS, SHRUBBED_SAPLING, SHRUBBED_LOG, STRIPPED_SHRUBBED_LOG, STRIPPED_SHRUBBED_WOOD, SHRUBBED_WOOD, SHRUBBED_LEAVES, SHRUBBED_SLAB, SHRUBBED_SHELF, SHRUBBED_FENCE, SHRUBBED_STAIRS, SHRUBBED_BUTTON, SHRUBBED_PRESSURE_PLATE, SHRUBBED_DOOR, SHRUBBED_TRAPDOOR, SHRUBBED_FENCE_GATE, SHRUBBED_SIGN, SHRUBBED_HANGING_SIGN,
		PRISMATIC_PLANKS, PRISMATIC_SAPLING, PRISMATIC_LOG, STRIPPED_PRISMATIC_LOG, STRIPPED_PRISMATIC_WOOD, PRISMATIC_WOOD, PRISMATIC_LEAVES, PRISMATIC_SLAB, PRISMATIC_SHELF, PRISMATIC_FENCE, PRISMATIC_STAIRS, PRISMATIC_BUTTON, PRISMATIC_PRESSURE_PLATE, PRISMATIC_DOOR, PRISMATIC_TRAPDOOR, PRISMATIC_FENCE_GATE, PRISMATIC_SIGN, PRISMATIC_HANGING_SIGN,
		ASHEN_PLANKS, ASHEN_SAPLING, ASHEN_LOG, STRIPPED_ASHEN_LOG, STRIPPED_ASHEN_WOOD, ASHEN_WOOD, ASHEN_LEAVES, ASHEN_SLAB, ASHEN_SHELF, ASHEN_FENCE, ASHEN_STAIRS, ASHEN_BUTTON, ASHEN_PRESSURE_PLATE, ASHEN_DOOR, ASHEN_TRAPDOOR, ASHEN_FENCE_GATE, ASHEN_SIGN, ASHEN_HANGING_SIGN,
		INFERNO_PLANKS, INFERNO_SAPLING, INFERNO_LOG, STRIPPED_INFERNO_LOG, STRIPPED_INFERNO_WOOD, INFERNO_WOOD, INFERNO_LEAVES, INFERNO_SLAB, INFERNO_SHELF, INFERNO_FENCE, INFERNO_STAIRS, INFERNO_BUTTON, INFERNO_PRESSURE_PLATE, INFERNO_DOOR, INFERNO_TRAPDOOR, INFERNO_FENCE_GATE, INFERNO_SIGN, INFERNO_HANGING_SIGN,
		AMETHYII_BOAT, AMETHYII_CHEST_BOAT,
		HALLOWED_BOAT, HALLOWED_CHEST_BOAT,
		SHRUBBED_BOAT, SHRUBBED_CHEST_BOAT,
		PRISMATIC_BOAT, PRISMATIC_CHEST_BOAT,
		ASHEN_BOAT, ASHEN_CHEST_BOAT,
		INFERNO_BOAT, INFERNO_CHEST_BOAT
	)).toArray(ItemLike[]::new);


	ItemLike[] FOOD_ITEMS = new ItemLike[]{
		CRYSTAL_HEARTFISH,
		SUSHI,
		GOLDEN_CHORUS_FRUIT,
		FRIED_CHORUS_FRUIT,
		FROZEN_CARPACCIO,
		ENDERMAN_FLESH,
		GRILLED_ENDERMAN_FLESH,
		FLAREFIN_KOI,
		AMETHYST_BUDFISH,
		CRIMSON_TIGERFISH,
		ENDER_KOI,
		FLESHY_PIRANHA,
		BUBBLEFISH,
		PRISMITE,
		OVERGROWN_COD,
		SHULKER_BODY,
		PRISMATIC_SUSHI,
		SHEPHERD_PIE,
		CHORUS_PIE,
		PHO,
		ROYAL_JELLY,
		ROYAL_JELLY_II,
		DUSKBERRY,
		FROST_MINNOW,
		GOOSH,
		CHORUS_STEW,
		POTASSIFISH,
		LOAF_OF_PLENTY,
		CANDIED_CHORUS_FRUIT
	};


	List<ItemStackTemplate> FOOD_TEMPLATES = List.of(
		AMARENE_POTION,
		BLIND_RAGE_POTION,
		LONG_BLIND_RAGE_POTION,
		ENDURANCE_POTION,
		LONG_ENDURANCE_POTION,
		STRONG_ENDURANCE_POTION,
		ENTANGLEMENT_POTION,
		LONG_ENTANGLEMENT_POTION,
		STRONG_ENTANGLEMENT_POTION,
		FROST_CLOUD_POTION,
		HELLFIRE_TREADER_POTION,
		LONG_HELLFIRE_TREADER_POTION,
		STRONG_HELLFIRE_TREADER_POTION,
		LIFEFORCE_POTION,
		LONG_LIFEFORCE_POTION,
		STRONG_LIFEFORCE_POTION,
		SPELUNKER_POTION,
		LONG_SPELUNKER_POTION,
		STRONG_SPELUNKER_POTION,
    POSEIDON_NECTAR,
		RED_POTION,
		REGENERAGA,
		LONG_REGENERAGA_POTION,
		STRONG_REGENERAGA_POTION,
		LUCK_POTION,
		CHORUS_JUICE
	);


	ItemLike[] EQUIPMENT_ITEMS = new ItemLike[]{
		CALL_OF_THE_VOID,
		FISHER_OF_VOIDS,
		TAMARIS,
		SHULKER_HELMET,
		SHULKER_CHESTPLATE,
		SHULKER_LEGGINGS,
		SHULKER_BOOTS,
		SHARANGA,
		SPECTRAL_FURY,
		COPPER_ELEKTRA_SHIELD,
		PHANTOM_WINGS,
		REINFORCED_HORSE_ARMOR,
		STELLAR_STRIKER,
		CHAMPION_HELMET,
		CHAMPION_CHESTPLATE,
		CHAMPION_LEGGINGS,
		CHAMPION_BOOTS,
		HALLOWED_HELMET,
		HALLOWED_CHESTPLATE,
		HALLOWED_LEGGINGS,
		HALLOWED_BOOTS,
		FLORAL_HELMET,
		FLORAL_CHESTPLATE,
		FLORAL_LEGGINGS,
		FLORAL_BOOTS,
		DRAGON_WINGS,
		EMPRESS_WINGS,
		SHULKER_AXE,
		SHULKER_PICKAXE,
		SHULKER_SPEAR,
		SHULKER_SWORD,
		SHULKER_SHOVEL,
		SHULKER_HOE,
	};

	ItemLike[] INGREDIENT_ITEMS = new ItemLike[]{
		CHORUS_PLATING,
		ENDERITE_SHARD,
		ENDERITE_UPGRADE_SMITHING_TEMPLATE,
		HALLOWED_INGOT,
		SAND_RUNE,
		STARLIGHT_SOOT,
		GILDED_PURPUR_KEY,
		PURPUR_KEY,
		WINGED_KEY,
		MUSIC_DISC_DEVIANTS_LIGHT_MUSIC_BOX,
		MUSIC_DISC_FIRES_OF_HOKKAI,
		MUSIC_DISC_PRECIPICE_STEREO,
		ENDER_EGG
	};

	ItemLike[] TRINKET_ITEMS = new ItemLike[]{
		PRISMATIC_PEARL,
		ENDONOMICON,
		SATCHEL_OF_VOIDS,
		DUSKBERRY,
		ENDERMAN_HAND,
		DRAGON_EYE,
		LIFE_CRYSTAL,
		BOOK_OF_JINX,
		BOOK_OF_LIGHT,
		BOOK_OF_OBSTRUCT,
		BOOK_OF_UPDRAFT,
		BOOK_OF_CONVEYANCE,
		BOOK_OF_RETURN
	};

	ItemLike[] SPAWN_EGGS_ITEMS = new ItemLike[]{
		// running mc now
		VOIDED_SILVERFISH_SPAWN_EGG,
		VOIDED_SKELETON_SPAWN_EGG,
		VOIDED_SLIME_SPAWN_EGG,
		VOIDED_ZOMBIE_SPAWN_EGG,
		FLESH_PIGLIN_SPAWN_EGG
	};


	static ResourceKey<CreativeModeTab> key(String key) {
		return Stellarity.key(CREATIVE_MODE_TAB.key(), key);
	}

	static ResourceKey<CreativeModeTab> mc(String key) {
		return Stellarity.mcKey(CREATIVE_MODE_TAB.key(), key);
	}

	CreativeModeTab FOOD = FabricCreativeModeTab.builder()
		.icon(() -> new ItemStack(SUSHI))
		.title(Component.translatable("item_group.stellarity.food"))
		.build();
	CreativeModeTab BLOCKS = FabricCreativeModeTab.builder()
		.icon(() -> new ItemStack(ENDER_GRASS_BLOCK))
		.title(Component.translatable("item_group.stellarity.blocks"))
		.build();
	CreativeModeTab EQUIPMENT = FabricCreativeModeTab.builder()
		.icon(() -> new ItemStack(CALL_OF_THE_VOID))
		.title(Component.translatable("item_group.stellarity.equipment"))
		.build();

	CreativeModeTab INGREDIENTS = FabricCreativeModeTab.builder()
		.icon(() -> new ItemStack(ENDERITE_SHARD))
		.title(Component.translatable("item_group.stellarity.ingredients"))
		.build();

	CreativeModeTab TRINKETS = FabricCreativeModeTab.builder()
		.icon(() -> new ItemStack(PRISMATIC_PEARL))
		.title(Component.translatable("item_group.stellarity.trinkets"))
		.build();

	CreativeModeTab SPAWN_EGGS = FabricCreativeModeTab.builder()
		.icon(() -> new ItemStack(VOIDED_ZOMBIE_SPAWN_EGG))
		.title(Component.translatable("item_group.stellarity.spawn_eggs"))
		.build();

	static void init() {
		register(key("food"), FOOD, FOOD_ITEMS, FOOD_TEMPLATES);
		register(key("blocks"), BLOCKS, BLOCKS_ITEMS);
		register(key("equipment"), EQUIPMENT, EQUIPMENT_ITEMS);
		register(key("ingredients"), INGREDIENTS, INGREDIENT_ITEMS);
		register(key("trinkets"), TRINKETS, TRINKET_ITEMS);
		register(key("spawn_eggs"), SPAWN_EGGS, SPAWN_EGGS_ITEMS);
		register(mc("spawn_eggs"), SPAWN_EGGS, SPAWN_EGGS_ITEMS);

		Stellarity.LOGGER.info("Registering Stellarity Creative Mode Tabs");

	}

	static void register(ResourceKey<CreativeModeTab> key, CreativeModeTab tab, ItemLike[] items, List<ItemStackTemplate> stacks, List<Supplier<List<ItemStack>>> batchStacks) {
		if (key.identifier().getNamespace().equals(Stellarity.MOD_ID))
			Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, tab);

		CreativeModeTabEvents.modifyOutputEvent(key).register(itemGroup -> {
			for (ItemLike item : items) {
				itemGroup.accept(item);
			}

			for (ItemStackTemplate stack : stacks) {
				itemGroup.accept(stack.create());
			}

			for (Supplier<List<ItemStack>> batch : batchStacks) {
				itemGroup.acceptAll(batch.get());
			}
		});


	}

	static void register(ResourceKey<CreativeModeTab> key, CreativeModeTab tab, ItemLike[] items, List<ItemStackTemplate> stacks) {
		register(key, tab, items, stacks, List.of());
	}

	static void register(ResourceKey<CreativeModeTab> key, CreativeModeTab tab, ItemLike[] items) {
		register(key, tab, items, List.of(), List.of());
	}

}
