package dev.coder2195.stellarity.registry;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ColorCollection;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.data_component.Color;

import java.util.List;
import java.util.function.Supplier;

import static net.minecraft.core.registries.BuiltInRegistries.CREATIVE_MODE_TAB;
import static dev.coder2195.stellarity.registry.StellarityItems.*;

public interface StellarityCreativeModeTabs {
	ItemLike[] BLOCKS_ITEMS = new ItemLike[]{
		ASHEN_FROGLIGHT,
		ENDER_DIRT,
		ENDER_GRASS_BLOCK,
		ROOTED_ENDER_DIRT,
		COARSE_ENDER_DIRT,
		ENDER_DIRT_PATH,
		ALTAR_OF_THE_ACCURSED,
		PHANTOM_ITEM_FRAME,
		ENDERITE_BLOCK
	};


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
		SHEPHERDS_PIE,
		CHORUS_PIE,
		PHO,
		ROYAL_JELLY,
		ROYAL_JELLY_II,
		DUSKBERRY,
		FROST_MINNOW,
		GOOSH,
		CHORUS_STEW,
		POTASSIFISH,
		LOAF_OF_PLENTY
	};


	List<Supplier<ItemStack>> FOOD_ITEMSTACKS = List.of(
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
		POSEIDONS_NECTAR_POTION,
		RED_POTION,
		REGENERAGA_POTION,
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
		PHANTOM_WINGS
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
		ENDERMANS_HAND,
		DRAGONS_EYE,
		LIFE_CRYSTAL
	};

	ItemLike[] SPAWN_EGGS_ITEMS = new ItemLike[]{
		// running mc now
		VOIDED_SILVERFISH_SPAWN_EGG,
		VOIDED_SKELETON_SPAWN_EGG,
		VOIDED_SLIME_SPAWN_EGG,
		VOIDED_ZOMBIE_SPAWN_EGG,
		FLESH_PIGLIN_SPAWN_EGG
	};


	List<Supplier<List<ItemStack>>> BLOCKS_BATCH_ITEMSTACKS = List.of(
		() -> ColorCollection.VALUES.asList().stream().map(color -> {
			var stack = new ItemStack(StellarityBlocks.COLORED_LEAVES);
			stack.set(StellarityDataComponents.COLOR, new Color(color.getTextureDiffuseColor() & 0x00FFFFFF));
			return stack;
		}).toList()
	);

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
		register(key("food"), FOOD, FOOD_ITEMS, FOOD_ITEMSTACKS);
		register(key("blocks"), BLOCKS, BLOCKS_ITEMS, List.of(), BLOCKS_BATCH_ITEMSTACKS);
		register(key("equipment"), EQUIPMENT, EQUIPMENT_ITEMS);
		register(key("ingredients"), INGREDIENTS, INGREDIENT_ITEMS);
		register(key("trinkets"), TRINKETS, TRINKET_ITEMS);
		register(key("spawn_eggs"), SPAWN_EGGS, SPAWN_EGGS_ITEMS);
		register(mc("spawn_eggs"), SPAWN_EGGS, SPAWN_EGGS_ITEMS);

		Stellarity.LOGGER.info("Registering Stellarity Creative Mode Tabs");

	}

	static void register(ResourceKey<CreativeModeTab> key, CreativeModeTab tab, ItemLike[] items, List<Supplier<ItemStack>> stacks, List<Supplier<List<ItemStack>>> batchStacks) {
		if (key.identifier().getNamespace().equals(Stellarity.MOD_ID))
			Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, tab);

		CreativeModeTabEvents.modifyOutputEvent(key).register(itemGroup -> {
			for (ItemLike item : items) {
				itemGroup.accept(item);
			}

			for (Supplier<ItemStack> stack : stacks) {
				itemGroup.accept(stack.get());
			}

			for (Supplier<List<ItemStack>> batch : batchStacks) {
				itemGroup.acceptAll(batch.get());
			}
		});


	}

	static void register(ResourceKey<CreativeModeTab> key, CreativeModeTab tab, ItemLike[] items, List<Supplier<ItemStack>> stacks) {
		register(key, tab, items, stacks, List.of());
	}

	static void register(ResourceKey<CreativeModeTab> key, CreativeModeTab tab, ItemLike[] items) {
		register(key, tab, items, List.of(), List.of());
	}

}
