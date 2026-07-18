package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.tags.StellarityStructureTags;
import dev.coder2195.stellarity.util.tuple.Tuple2;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Unit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.decoration.painting.PaintingVariants;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.equipment.trim.ArmorTrim;
import net.minecraft.world.item.equipment.trim.TrimMaterials;
import net.minecraft.world.item.equipment.trim.TrimPatterns;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.block.ColorCollection;
import net.minecraft.world.level.saveddata.maps.MapDecorationTypes;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;

import java.util.List;
import java.util.Optional;

import static dev.coder2195.stellarity.registry.StellarityItems.*;
import static dev.coder2195.stellarity.util.LootUtil.*;
import static net.minecraft.world.item.Items.*;

public interface StellarityVillagerTrades {
	ResourceKey<VillagerTrade> ARMORER_1_COAL_ENDERITE_SHARD = id("armorer/1/coal_enderite_shard");
	ResourceKey<VillagerTrade> ARMORER_1_CHARCOAL_ENDERITE_SHARD = id("armorer/1/charcoal_enderite_shard");
	ResourceKey<VillagerTrade> ARMORER_1_BLAZE_ROD_ENDERITE_SHARD = id("armorer/1/blaze_rod_enderite_shard");
	ResourceKey<VillagerTrade> ARMORER_1_ENDERITE_SHARD_IRON_CHESTPLATE = id("armorer/1/enderite_shard_iron_chestplate");
	ResourceKey<VillagerTrade> ARMORER_1_ENDERITE_SHARD_IRON_HELMET = id("armorer/1/enderite_shard_iron_helmet");
	ResourceKey<VillagerTrade> ARMORER_2_ENDERITE_SHARD_IRON_LEGGINGS = id("armorer/2/enderite_shard_iron_leggings");
	ResourceKey<VillagerTrade> ARMORER_2_ENDERITE_SHARD_IRON_BOOTS = id("armorer/2/enderite_shard_iron_boots");
	ResourceKey<VillagerTrade> ARMORER_2_ENDERITE_SHARD_HALLOWED_INGOT = id("armorer/2/enderite_shard_hallowed_ingot");
	ResourceKey<VillagerTrade> ARMORER_2_ENDERITE_SHARD_CHROUS_PLATING = id("armorer/2/enderite_shard_chorus_plating");
	ResourceKey<VillagerTrade> ARMORER_3_DIAMOND_ENDERITE_SHARD = id("armorer/3/diamond_enderite_shard");
	ResourceKey<VillagerTrade> ARMORER_3_ENDERITE_SHARD_SHIELD_COPPER_ELEKTRA_SHIELD = id("armorer/3/enderite_shard_shield_copper_elektra_shield");
	ResourceKey<VillagerTrade> ARMORER_4_PURPUR_BLOCK_ENDERITE_SHARD_ENDERITE_UPGRADE_SMITHING_TEMPLATE = id("armorer/4/purpur_block_enderite_shard_enderite_upgrade_smithing_template");
	ResourceKey<VillagerTrade> ARMORER_4_ENDERITE_SHARD_DIAMOND_LEGGINGS = id("armorer/4/enderite_shard_diamond_leggings");
	ResourceKey<VillagerTrade> ARMORER_4_ENDERITE_SHARD_DIAMOND_BOOTS = id("armorer/4/enderite_shard_diamond_boots");
	ResourceKey<VillagerTrade> ARMORER_5_ENDERITE_SHARD_DIAMOND_CHESTPLATE = id("armorer/5/enderite_shard_diamond_chestplate");
	ResourceKey<VillagerTrade> ARMORER_5_ENDERITE_SHARD_DIAMOND_HELMET = id("armorer/5/enderite_shard_diamond_helmet");

	ResourceKey<VillagerTrade> BUTCHER_1_ENDERMAN_FLESH_ENDERITE_SHARD = id("butcher/1/enderman_flesh_enderite_shard");
	ResourceKey<VillagerTrade> BUTCHER_1_PHANTOM_MEMBRANE_ENDERITE_SHARD = id("butcher/1/phantom_membrane_enderite_shard");
	ResourceKey<VillagerTrade> BUTCHER_2_COAL_ENDERITE_SHARD = id("butcher/2/coal_enderite_shard");
	ResourceKey<VillagerTrade> BUTCHER_2_CHARCOAL_ENDERITE_SHARD = id("butcher/2/charcoal_enderite_shard");
	ResourceKey<VillagerTrade> BUTCHER_2_ENDERMAN_FLESH_ENDERITE_SHARD_GRILLED_ENDERMAN_FLESH = id("butcher/2/enderman_flesh_enderite_shard_grilled_enderman_flesh");
	ResourceKey<VillagerTrade> BUTCHER_2_ENDERMAN_FLESH_ENDERITE_SHARD_FROZEN_CARPACCIO = id("butcher/2/enderman_flesh_enderite_shard_frozen_carpaccio");
	ResourceKey<VillagerTrade> BUTCHER_3_SHULKER_BODY_ENDERITE_SHARD = id("butcher/3/shulker_body_enderite_shard");
	ResourceKey<VillagerTrade> BUTCHER_3_ENDERITE_SHARD_SHULKER_BODY = id("butcher/3/enderite_shard_shulker_body");
	ResourceKey<VillagerTrade> BUTCHER_3_ENDERITE_SHARD_IRON_AXE = id("butcher/3/enderite_shard/iron_axe");
	ResourceKey<VillagerTrade> BUTCHER_4_BEEF_LEATHER_ENDERITE_SHARD = id("butcher/4/beef_leather_enderite_shard");
	ResourceKey<VillagerTrade> BUTCHER_4_MUTTON_WOOL_ENDERITE_SHARD = id("butcher/4/mutton_wool_enderite_shard");
	ResourceKey<VillagerTrade> BUTCHER_4_CHICKEN_FEATHER_ENDERITE_SHARD = id("butcher/4/chicken_feather_enderite_shard");
	ResourceKey<VillagerTrade> BUTCHER_4_PORKCHOP_ENDERITE_SHARD = id("butcher/4/porkchop_enderite_shard");
	ResourceKey<VillagerTrade> BUTCHER_4_ENDERITE_SHARD_PHO = id("butcher/4/enderite_shard_pho");
	ResourceKey<VillagerTrade> BUTCHER_5_ENDERITE_SHARD_SHEPHERDS_PIE = id("butcher/5/enderite_shard_shepherds_pie");
	ResourceKey<VillagerTrade> BUTCHER_5_ENDERITE_SHARD_DRIED_KELP_BLOCK = id("butcher/5/enderite_shard_dried_kelp_block");

	ResourceKey<VillagerTrade> CARTOGRAPHER_1_PAPER_ENDERITE_SHARD = id("cartographer/1/paper_enderite_shard");
	ResourceKey<VillagerTrade> CARTOGRAPHER_1_ENDERITE_SHARD_MAP = id("cartographer/1/enderite_shard_map");
	ResourceKey<VillagerTrade> CARTOGRAPHER_2_GLASS_PANE_ENDERITE_SHARD = id("cartographer/2/glass_pane_enderite_shard");
	ResourceKey<VillagerTrade> CARTOGRAPHER_2_MAP_ENDERITE_SHARD_END_CITY_EXPLORER_MAP = id("cartographer/2/map_enderite_shard_end_city_explorer_map");
	ResourceKey<VillagerTrade> CARTOGRAPHER_3_ENDERITE_SHARD_ITEM_FRAME = id("cartographer/3/enderite_shard_item_frame");
	ResourceKey<VillagerTrade> CARTOGRAPHER_3_MAP_ENDERITE_SHARD_CHAPEL_OF_LIGHT_MAP = id("cartographer/3/map_enderite_shard_chapel_of_light_map");
	ResourceKey<VillagerTrade> CARTOGRAPHER_4_ENDERITE_SHARD_GLOW_ITEM_FRAME = id("cartographer/4/enderite_shard_glow_item_frame");
	ResourceKey<VillagerTrade> CARTOGRAPHER_4_ENDERITE_SHARD_PHANTOM_ITEM_FRAME = id("cartographer/4/enderite_shard_phantom_item_frame");
	ResourceKey<VillagerTrade> CARTOGRAPHER_5_ENDERITE_SHARD_MOJANG_BANNER_PATTERN = id("cartographer/5/enderite_shard_mojang_banner_pattern");

	ResourceKey<VillagerTrade> CLERIC_1_PHANTOM_MEMBRANE_ENDERITE_SHARD = id("cleric/phantom_membrane_enderite_shard");
	ResourceKey<VillagerTrade> CLERIC_1_BONE_ENDERITE_SHARD = id("cleric/bone_enderite_shard");
	ResourceKey<VillagerTrade> CLERIC_1_ENDERMANS_HAND_ENDERITE_SHARD = id("cleric/endermans_hand_enderite_shard");
	ResourceKey<VillagerTrade> CLERIC_1_ENDERITE_SHARD_REDSTONE = id("cleric/enderite_shard_redstone");
	ResourceKey<VillagerTrade> CLERIC_1_ENDERITE_SHARD_BLAZE_ROD = id("cleric/enderite_shard_blaze_rod");
	ResourceKey<VillagerTrade> CLERIC_2_GOLD_INGOT_ENDERITE_SHARD = id("cleric/gold_ingot_enderite_shard");
	ResourceKey<VillagerTrade> CLERIC_2_ENDERITE_SHARD_LAPIS_LAZULI = id("cleric/enderite_shard_lapis_lazuli");
	ResourceKey<VillagerTrade> CLERIC_2_ENDERITE_SHARD_NETHER_WART = id("cleric/enderite_shard_nether_wart");
	ResourceKey<VillagerTrade> CLERIC_3_ENDERITE_SHARD_LEVEL_3_POTION = id("cleric/enderite_shard_level_3_potion");
	ResourceKey<VillagerTrade> CLERIC_3_ENDERITE_SHARD_GLOWSTONE = id("cleric/enderite_shard_glowstone");
	ResourceKey<VillagerTrade> CLERIC_4_ENDER_PEARL_ENDERITE_SHARD = id("cleric/ender_pearl_enderite_shard");
	ResourceKey<VillagerTrade> CLERIC_4_ENDERITE_SHARD_LEVEL_4_POTION = id("cleric/enderite_shard_level_4_potion");
	ResourceKey<VillagerTrade> CLERIC_5_ENDERITE_SHARD_LEVEL_5_POTION = id("cleric/enderite_shard_level_5_potion");
	ResourceKey<VillagerTrade> CLERIC_5_DRAGONS_BREATH_ENDERITE_SHARD = id("cleric/dragons_breath_enderite_shard");

	ResourceKey<VillagerTrade> FARMER_1_WHEAT_ENDERITE_SHARD = id("farmer/1/wheat_enderite_shard");
	ResourceKey<VillagerTrade> FARMER_1_POTATO_ENDERITE_SHARD = id("farmer/1/potato_enderite_shard");
	ResourceKey<VillagerTrade> FARMER_1_CARROT_ENDERITE_SHARD = id("farmer/1/carrot_enderite_shard");
	ResourceKey<VillagerTrade> FARMER_1_BEETROOT_ENDERITE_SHARD = id("farmer/1/beetroot_enderite_shard");
	ResourceKey<VillagerTrade> FARMER_1_ENDERITE_SHARD_BREAD = id("farmer/1/enderite_shard_bread");
	ResourceKey<VillagerTrade> FARMER_1_ENDERITE_SHARD_CAKE = id("farmer/1/enderite_shard_cake");
	ResourceKey<VillagerTrade> FARMER_2_CHORUS_FRUIT_ENDERITE_SHARD = id("farmer/2/chorus_fruit_enderite_shard");
	ResourceKey<VillagerTrade> FARMER_2_CHORUS_FLOWER_ENDERITE_SHARD = id("farmer/2/chorus_flower_enderite_shard");
	ResourceKey<VillagerTrade> FARMER_2_ENDERITE_SHARD_CHORUS_PIE = id("farmer/2/enderite_shard_chorus_pie");
	ResourceKey<VillagerTrade> FARMER_2_ENDERITE_SHARD_SHROOMLIGHT = id("farmer/2/enderite_shard_shroomlight");
	ResourceKey<VillagerTrade> FARMER_3_ENDERITE_SHARD_CANDIED_CHORUS_FRUIT = id("farmer/3/enderite_shard_candied_chorus_fruit");
	ResourceKey<VillagerTrade> FARMER_3_WHEAT_SEEDS_ENDERITE_SHARD_TORCHFLOWER_SEEDS = id("farmer/3/wheat_seeds_enderite_shard_torchflower_seeds");
	ResourceKey<VillagerTrade> FARMER_3_WHEAT_SEEDS_ENDERITE_SHARD_PITCHER_POD = id("farmer/3/wheat_seeds_enderite_shard_pitcher_pod");
	ResourceKey<VillagerTrade> FARMER_4_ENDERITE_SHARD_CHORUS_JUICE = id("farmer/4/enderite_shard_chorus_juice");
	ResourceKey<VillagerTrade> FARMER_4_ENDERITE_SHARD_CHORUS_STEW = id("farmer/4/enderite_shard_chorus_stew");
	ResourceKey<VillagerTrade> FARMER_4_ENDERITE_SHARD_FRIED_CHORUS_FRUIT = id("farmer/4/enderite_shard_fried_chorus_fruit");
	ResourceKey<VillagerTrade> FARMER_5_ENDERITE_SHARD_PHO = id("farmer/5/enderite_shard_pho");
	ResourceKey<VillagerTrade> FARMER_5_BREAD_ENDERITE_SHARD_LOAF_OF_PLENTY = id("farmer/5/bread_enderite_shard_loaf_of_plenty");

	ResourceKey<VillagerTrade> FISHERMAN_1_FISHING_ROD_ENDERITE_SHARD = id("fisherman/1/fishing_rod_enderite_shard");
	ResourceKey<VillagerTrade> FISHERMAN_1_ENDERITE_SHARD_FISHING_ROD = id("fisherman/1/enderite_shard_fishing_rod");
	ResourceKey<VillagerTrade> FISHERMAN_1_STRING_ENDERITE_SHARD = id("fisherman/1/string_enderite_shard");
	ResourceKey<VillagerTrade> FISHERMAN_1_COAL_ENDERITE_SHARD = id("fisherman/1/coal_enderite_shard");
	ResourceKey<VillagerTrade> FISHERMAN_2_ENDER_KOI_ENDERITE_SHARD = id("fisherman/2/ender_koi_enderite_shard");
	ResourceKey<VillagerTrade> FISHERMAN_2_OVERGROWN_COD_ENDERITE_SHARD = id("fisherman/2/overgrown_cod_enderite_shard");
	ResourceKey<VillagerTrade> FISHERMAN_2_FROST_MINNOW_ENDERITE_SHARD = id("fisherman/2/frost_minnow_enderite_shard");
	ResourceKey<VillagerTrade> FISHERMAN_2_GOOSH_ENDERITE_SHARD = id("fisherman/2/goosh_enderite_shard");
	ResourceKey<VillagerTrade> FISHERMAN_2_ENDERITE_SHARD_CAMPFIRE = id("fisherman/2/enderite_shard_campfire");
	ResourceKey<VillagerTrade> FISHERMAN_2_ENDERITE_SHARD_SOUL_CAMPFIRE = id("fisherman/2/enderite_shard_soul_campfire");
	ResourceKey<VillagerTrade> FISHERMAN_3_ENDERITE_SHARD_FISHING_ROD = id("fisherman/3/enderite_shard_fishing_rod");
	ResourceKey<VillagerTrade> FISHERMAN_3_CRIMSON_TIGERFISH_ENDERITE_SHARD = id("fisherman/3/crimson_tigerfish_enderite_shard");
	ResourceKey<VillagerTrade> FISHERMAN_3_FLESHY_PIRANHA_ENDERITE_SHARD = id("fisherman/3/fleshy_piranha_enderite_shard");
	ResourceKey<VillagerTrade> FISHERMAN_3_FLAREFIN_KOI_ENDERITE_SHARD = id("fisherman/3/flarefin_koi_enderite_shard");
	ResourceKey<VillagerTrade> FISHERMAN_3_POTASSIFISH_ENDERITE_SHARD = id("fisherman/3/potassifish_enderite_shard");
	ResourceKey<VillagerTrade> FISHERMAN_4_ENDERITE_SHARD_LURE_BOOK = id("fisherman/4/enderite_shard_lure_book");
	ResourceKey<VillagerTrade> FISHERMAN_4_ENDERITE_SHARD_LUCK_OF_THE_SEA_BOOK = id("fisherman/4/enderite_shard_luck_of_the_sea_book");
	ResourceKey<VillagerTrade> FISHERMAN_4_ENDERITE_SHARD_FISHER_OF_VOIDS = id("fisherman/4/enderite_shard_fisher_of_voids");
	ResourceKey<VillagerTrade> FISHERMAN_5_CRYSTAL_HEARTFISH_ENDERITE_SHARD = id("fisherman/5/crystal_heartfish_enderite_shard");
	ResourceKey<VillagerTrade> FISHERMAN_5_ENDERITE_SHARD_PRISMATIC_SUSHI = id("fisherman/5/enderite_shard_prismatic_sushi");

	ResourceKey<VillagerTrade> FLETCHER_1_ENDERITE_SHARD_SPECTRAL_ARROW = id("fletcher/1/enderite_shard_spectral_arrow");
	ResourceKey<VillagerTrade> FLETCHER_1_ENDERITE_SHARD_ARROW = id("fletcher/1/enderite_shard_arrow");
	ResourceKey<VillagerTrade> FLETCHER_1_STICK_ENDERITE_SHARD = id("fletcher/1/stick_enderite_shard");
	ResourceKey<VillagerTrade> FLETCHER_2_FLINT_ENDERITE_SHARD = id("fletcher/2/flint_enderite_shard");
	ResourceKey<VillagerTrade> FLETCHER_2_ENDERITE_SHARD_BOW = id("fletcher/2/enderite_shard_bow");
	ResourceKey<VillagerTrade> FLETCHER_3_STRING_ENDERITE_SHARD = id("fletcher/3/string_enderite_shard");
	ResourceKey<VillagerTrade> FLETCHER_3_FEATHER_ENDERITE_SHARD = id("fletcher/3/feather_enderite_shard");
	ResourceKey<VillagerTrade> FLETCHER_3_ENDERITE_SHARD_CROSSBOW = id("fletcher/3/enderite_shard_crossbow");
	ResourceKey<VillagerTrade> FLETCHER_4_ARROW_ENDERITE_SHARD_LEVITATION_TIPPED_ARROW = id("fletcher/4/arrow_enderite_shard_levitation_tipped_arrow");
	ResourceKey<VillagerTrade> FLETCHER_4_ARROW_ENDERITE_SHARD_LUCK_TIPPED_ARROW = id("fletcher/4/arrow_enderite_shard_luck_tipped_arrow");
	ResourceKey<VillagerTrade> FLETCHER_5_BOW_ENDERITE_SHARD_SHARANGA = id("fletcher/5/bow_enderite_shard_sharanga");

	ResourceKey<VillagerTrade> LEATHERWORKER_1_LEATHER_ENDERITE_SHARD = id("leatherworker/1/leather_enderite_shard");
	ResourceKey<VillagerTrade> LEATHERWORKER_1_FLINT_ENDERITE_SHARD = id("leatherworker/1/flint_enderite_shard");
	ResourceKey<VillagerTrade> LEATHERWORKER_2_ENDERITE_SHARD_SADDLE = id("leatherworker/2/enderite_shard_saddle");
	ResourceKey<VillagerTrade> LEATHERWORKER_2_ENDERITE_SHARD_LEATHER = id("leatherworker/2/enderite_shard_leather");
	ResourceKey<VillagerTrade> LEATHERWORKER_3_ENDERITE_SHARD_CAULDRON = id("leatherworker/3/enderite_shard_cauldron");
	ResourceKey<VillagerTrade> LEATHERWORKER_3_RABBIT_HIDE_ENDERITE_SHARD = id("leatherworker/3/rabbit_hide_enderite_shard");
	ResourceKey<VillagerTrade> LEATHERWORKER_4_ITEM_FRAME_ENDERITE_SHARD_GLOW_ITEM_FRAME = id("leatherworker/4/item_frame_enderite_shard_glow_item_frame");
	ResourceKey<VillagerTrade> LEATHERWORKER_4_ENDERITE_SHARD_TURTLE_SCUTE = id("leatherworker/4/enderite_shard_turtle_scute");
	ResourceKey<VillagerTrade> LEATHERWORKER_4_ENDERITE_SHARD_ARMADILLO_SCUTE = id("leatherworker/4/enderite_shard_armadillo_scute");
	ResourceKey<VillagerTrade> LEATHERWORKER_5_DIAMOND_HORSE_ARMOR_ENDERITE_SHARD_REINFORCED_HORSE_ARMOR = id("leatherworker/5/diamond_horse_armor_enderite_shard_reinforced_horse_armor");

	ResourceKey<VillagerTrade> LIBRARIAN_1_PAPER_ENDERITE_SHARD = id("librarian/1/paper_enderite_shard");
	ResourceKey<VillagerTrade> LIBRARIAN_1_BOOK_ENDERITE_SHARD = id("librarian/1/book_enderite_shard");
	ResourceKey<VillagerTrade> LIBRARIAN_1_ENDERITE_SHARD_BOOKSHELF = id("librarian/1/enderite_shard_bookshelf");
	ResourceKey<VillagerTrade> LIBRARIAN_1_BOOK_ENDERITE_SHARD_LEVEL_1_ENCHANTED_BOOK = id("librarian/1/book_enderite_shard_level_1_enchanted_book");
	ResourceKey<VillagerTrade> LIBRARIAN_2_ENDERITE_SHARD_LANTERN = id("librarian/2/enderite_shard_lantern");
	ResourceKey<VillagerTrade> LIBRARIAN_2_BOOK_ENDERITE_SHARD_LEVEL_2_ENCHANTED_BOOK = id("librarian/2/book_enderite_shard_level_2_enchanted_book");
	ResourceKey<VillagerTrade> LIBRARIAN_3_ENDERITE_SHARD_INK_SAC = id("librarian/3/enderite_shard_ink_sac");
	ResourceKey<VillagerTrade> LIBRARIAN_3_ENDERITE_SHARD_GLOW_INK_SAC = id("librarian/3/enderite_shard_glow_ink_sac");
	ResourceKey<VillagerTrade> LIBRARIAN_3_ENDERITE_SHARD_GLASS = id("librarian/3/enderite_shard_glass");
	ResourceKey<VillagerTrade> LIBRARIAN_4_BOOK_ENDERITE_SHARD_LEVEL_4_ENCHANTED_BOOK = id("librarian/4/book_enderite_shard_level_4_enchanted_book");
	ResourceKey<VillagerTrade> LIBRARIAN_4_ENDERITE_SHARD_WRITABLE_BOOK = id("librarian/4/enderite_shard_writable_book");
	ResourceKey<VillagerTrade> LIBRARIAN_4_ENDERITE_SHARD_NAME_TAG = id("librarian/4/enderite_shard_name_tag");
	ResourceKey<VillagerTrade> LIBRARIAN_5_BOOK_ENDERITE_SHARD_BOOK_OF_UPDRAFT = id("librarian/5/book_enderite_shard_book_of_updraft");
	ResourceKey<VillagerTrade> LIBRARIAN_5_BOOK_ENDERITE_SHARD_BOOK_OF_LIGHT = id("librarian/5/book_enderite_shard_of_light");
	ResourceKey<VillagerTrade> LIBRARIAN_5_BOOK_ENDERITE_SHARD_BOOK_OF_OBSTRUCT = id("librarian/5/book_enderite_shard_of_obstruct");

	ResourceKey<VillagerTrade> MASON_1_ENDERITE_SHARD_PURPUR_BLOCK = id("mason/1/enderite_shard_purpur_block");
	ResourceKey<VillagerTrade> MASON_1_ENDERITE_SHARD_END_STONE_BRICKS = id("mason/1/enderite_shard_end_stone_bricks");
	ResourceKey<VillagerTrade> MASON_2_AMETHYST_BUDFISH_AMETHYST_SHARD = id("mason/2/amethyst_budfish_amethyst_shard");
	ResourceKey<VillagerTrade> MASON_2_AMETHYST_BUDFISH_QUARTZ = id("mason/2/amethyst_budfish_quartz");
	ResourceKey<VillagerTrade> MASON_2_ENDERITE_SHARD_OBSIDIAN = id("mason/2/enderite_shard_obsidian");
	ResourceKey<VillagerTrade> MASON_2_ENDERITE_SHARD_SMOOTH_QUARTZ = id("mason/2/enderite_shard_smooth_quartz");
	ResourceKey<VillagerTrade> MASON_3_ENDERITE_SHARD_POLISHED_BLACKSTONE = id("mason/3/enderite_shard_polished_blackstone");
	ResourceKey<VillagerTrade> MASON_3_ENDERITE_SHARD_POLISHED_DEEPSLATE = id("mason/3/enderite_shard_polished_deepslate");
	ResourceKey<VillagerTrade> MASON_3_COBBLED_DEEPSLATE_ENDERITE_SHARD = id("mason/3/cobbled_deepslate_enderite_shard");
	ResourceKey<VillagerTrade> MASON_3_BLACKSTONE_ENDERITE_SHARD = id("mason/3/blackstone_enderite_shard");
	ResourceKey<VillagerTrade> MASON_3_ENDERITE_SHARD_QUARTZ_BLOCK = id("mason/3/enderite_shard_quartz_block");
	ResourceKey<VillagerTrade> MASON_3_ENDERITE_SHARD_QUARTZ_PILLAR = id("mason/3/enderite_shard_quartz_pillar");
	ColorCollection<ResourceKey<VillagerTrade>> MASON_4_ENDERITE_SHARD_GLAZED_TERRACOTA = ColorCollection.NAMES.map(color -> id("mason/4/enderite_shard_" + color + "_glazed_terracota"));
	ColorCollection<ResourceKey<VillagerTrade>> MASON_4_ENDERITE_SHARD_TERRACOTA = ColorCollection.NAMES.map(color -> id("mason/4/enderite_shard_" + color + "_terracota"));
	ResourceKey<VillagerTrade> MASON_5_ENDERITE_SHARD_CRYING_OBSIDIAN = id("mason/5/enderite_shard_crying_obsidian");
	ResourceKey<VillagerTrade> MASON_5_ENDERITE_SHARD_PURPUR_PILLAR = id("mason/5/enderite_shard_purpur_pillar");
	ResourceKey<VillagerTrade> MASON_5_ENDERITE_SHARD_CHISELED_QUARTZ_BLOCK = id("mason/5/enderite_shard_chiseled_quartz_block");


	ResourceKey<VillagerTrade> SHEPHERD_1_ENDERITE_SHARD_SHEARS = id("shepherd/1/enderite_shard_shears");
	ResourceKey<VillagerTrade> SHEPHERD_1_WHITE_WOOL_ENDERITE_SHARD = id("shepherd/1/white_wool_enderite_shard");
	ColorCollection<ResourceKey<VillagerTrade>> SHEPHERD_2_ENDERITE_SHARD_DYE = ColorCollection.NAMES.map(color -> id("shepherd/2/enderite_shard_" + color + "_dye"));
	ColorCollection<ResourceKey<VillagerTrade>> SHEPHERD_2_ENDERITE_SHARD_WOOL = ColorCollection.NAMES.map(color -> id("shepherd/2/enderite_shard_" + color + "_wool"));
	ColorCollection<ResourceKey<VillagerTrade>> SHEPHERD_3_ENDERITE_SHARD_CARPET = ColorCollection.NAMES.map(color -> id("shepherd/3/enderite_shard_" + color + "_carpet"));
	ColorCollection<ResourceKey<VillagerTrade>> SHEPHERD_3_ENDERITE_SHARD_WOOL = ColorCollection.NAMES.map(color -> id("shepherd/3/enderite_shard_" + color + "_wool"));
	ColorCollection<ResourceKey<VillagerTrade>> SHEPHERD_3_ENDERITE_SHARD_BED = ColorCollection.NAMES.map(color -> id("shepherd/3/enderite_shard_" + color + "_bed"));
	ColorCollection<ResourceKey<VillagerTrade>> SHEPHERD_4_ENDERITE_SHARD_BANNER = ColorCollection.NAMES.map(color -> id("shepherd/4/enderite_shard_" + color + "_banner"));
	ColorCollection<ResourceKey<VillagerTrade>> SHEPHERD_4_ENDERITE_SHARD_WOOL = ColorCollection.NAMES.map(color -> id("shepherd/4/enderite_shard_" + color + "_wool"));
	ColorCollection<ResourceKey<VillagerTrade>> SHEPHERD_4_ENDERITE_SHARD_DYE = ColorCollection.NAMES.map(color -> id("shepherd/4/enderite_shard_" + color + "_dye"));
	ResourceKey<VillagerTrade> SHEPHERD_5_ENDERITE_SHARD_FIRE_PAINTING = id("shepherd/5/enderite_shard_fire_painting");
	ResourceKey<VillagerTrade> SHEPHERD_5_ENDERITE_SHARD_WIND_PAINTING = id("shepherd/5/enderite_shard_wind_painting");
	ResourceKey<VillagerTrade> SHEPHERD_5_ENDERITE_SHARD_EARTH_PAINTING = id("shepherd/5/enderite_shard_earth_painting");
	ResourceKey<VillagerTrade> SHEPHERD_5_ENDERITE_SHARD_WATER_PAINTING = id("shepherd/5/enderite_shard_water_painting");
	ResourceKey<VillagerTrade> SHEPHERD_5_ENDERITE_SHARD_A_HOP_AND_A_SKIP_AWAY_PAINTING = id("shepherd/5/enderite_shard_a_hop_and_a_skip_away_painting");
	ResourceKey<VillagerTrade> SHEPHERD_5_ENDERITE_SHARD_DRAGONBLADE_PAINTING = id("shepherd/5/enderite_shard_dragonblade_painting");
	ResourceKey<VillagerTrade> SHEPHERD_5_ENDERITE_SHARD_END_PAINTING = id("shepherd/5/enderite_shard_end_painting");
	ResourceKey<VillagerTrade> SHEPHERD_5_ENDERITE_SHARD_END_BLOSSOM_PAINTING = id("shepherd/5/enderite_shard_end_blossom_painting");
	ResourceKey<VillagerTrade> SHEPHERD_5_ENDERITE_SHARD_HOURGLASS_PAINTING = id("shepherd/5/enderite_shard_hourglass_painting");
	ResourceKey<VillagerTrade> SHEPHERD_5_ENDERITE_SHARD_MAJESTICAL_BREW_PAINTING = id("shepherd/5/enderite_shard_majestical_brew_painting");
	ResourceKey<VillagerTrade> SHEPHERD_5_ENDERITE_SHARD_SCHEME_PAINTING = id("shepherd/5/enderite_shard_scheme_painting");
	ResourceKey<VillagerTrade> SHEPHERD_5_ENDERITE_SHARD_SHEPHERDS_FEAST_PAINTING = id("shepherd/5/enderite_shard_shepherds_feast_painting");
	ResourceKey<VillagerTrade> SHEPHERD_5_ENDERITE_SHARD_SNARE_PAINTING = id("shepherd/5/enderite_shard_snare_painting");
	ResourceKey<VillagerTrade> SHEPHERD_5_ENDERITE_SHARD_SNATCH_PAINTING = id("shepherd/5/enderite_shard_snatch_painting");
	ResourceKey<VillagerTrade> SHEPHERD_5_ENDERITE_SHARD_THE_OBSIDIAN_RELIQUARY_PAINTING = id("shepherd/5/enderite_shard_the_obsidian_reliquary_painting");


	static void bootstrap(BootstrapContext<VillagerTrade> context) {
		var trimMaterials = context.lookup(Registries.TRIM_MATERIAL);
		var trimPatterns = context.lookup(Registries.TRIM_PATTERN);
		var enchants = context.lookup(Registries.ENCHANTMENT);
		var paintings = context.lookup(Registries.PAINTING_VARIANT);

		List<LootItemFunction> ironArmorModifier = List.of(
			component(DataComponents.TRIM, new ArmorTrim(trimMaterials.getOrThrow(TrimMaterials.EMERALD), trimPatterns.getOrThrow(TrimPatterns.SPIRE))).when(chance(0.5f)).build(),
			enchant().when(chance(0.5f)).build()
		);
		List<LootItemFunction> diamondArmorModifier = List.of(
			component(DataComponents.TRIM, new ArmorTrim(trimMaterials.getOrThrow(TrimMaterials.EMERALD), trimPatterns.getOrThrow(TrimPatterns.EYE))).when(chance(0.5f)).build(),
			component(DataComponents.TRIM, new ArmorTrim(trimMaterials.getOrThrow(TrimMaterials.AMETHYST), trimPatterns.getOrThrow(TrimPatterns.EYE))).when(chance(0.5f)).build(),
			enchant(enchants, 15, 31).build()
		);

		context.register(ARMORER_1_COAL_ENDERITE_SHARD, simpleToShard(COAL, num(17, 20), 1, 2, 12, 0.05f));
		context.register(ARMORER_1_CHARCOAL_ENDERITE_SHARD, simpleToShard(CHARCOAL, num(20, 24), 1, 2, 10, 0.05f));
		context.register(ARMORER_1_BLAZE_ROD_ENDERITE_SHARD, simpleToShard(BLAZE_ROD, num(2, 4), 1, 1, 10, 0.05f));
		context.register(ARMORER_1_ENDERITE_SHARD_IRON_CHESTPLATE, shardToModifierItem(num(8, 12), IRON_CHESTPLATE, ironArmorModifier, 1, 2, 6, 0.2f));
		context.register(ARMORER_1_ENDERITE_SHARD_IRON_HELMET, shardToModifierItem(num(7, 11), IRON_HELMET, ironArmorModifier, 1, 2, 6, 0.2f));
		context.register(ARMORER_2_ENDERITE_SHARD_IRON_LEGGINGS, shardToModifierItem(num(8, 12), IRON_LEGGINGS, ironArmorModifier, 1, 8, 6, 0.2f));
		context.register(ARMORER_2_ENDERITE_SHARD_IRON_BOOTS, shardToModifierItem(num(5, 8), IRON_BOOTS, ironArmorModifier, 1, 8, 6, 0.2f));
		context.register(ARMORER_2_ENDERITE_SHARD_HALLOWED_INGOT, shardToSimple(num(4, 6), HALLOWED_INGOT, 1, 10, 8, 0.2f));
		context.register(ARMORER_2_ENDERITE_SHARD_CHROUS_PLATING, shardToSimple(num(4, 6), CHORUS_PLATING, 1, 10, 8, 0.2f));
		context.register(ARMORER_3_DIAMOND_ENDERITE_SHARD, simpleToShard(DIAMOND, 1, 2, 12, 8, 0.05f));
		context.register(ARMORER_3_ENDERITE_SHARD_SHIELD_COPPER_ELEKTRA_SHIELD, simpleShardToSimple(SHIELD, num(1), num(45, 64), COPPER_ELEKTRA_SHIELD, 1, 40, 2, 0.2f));

		context.register(ARMORER_4_PURPUR_BLOCK_ENDERITE_SHARD_ENDERITE_UPGRADE_SMITHING_TEMPLATE, simpleShardToSimple(PURPUR_BLOCK, num(20, 30), num(28, 40), ENDERITE_UPGRADE_SMITHING_TEMPLATE, 1, 10, 2, 0.2f));
		context.register(ARMORER_4_ENDERITE_SHARD_DIAMOND_LEGGINGS, shardToModifierItem(num(28, 35), DIAMOND_LEGGINGS, diamondArmorModifier, 1, 15, 3, 0.2f));
		context.register(ARMORER_4_ENDERITE_SHARD_DIAMOND_BOOTS, shardToModifierItem(num(21, 24), DIAMOND_BOOTS, diamondArmorModifier, 1, 15, 3, 0.2f));
		context.register(ARMORER_5_ENDERITE_SHARD_DIAMOND_HELMET, shardToModifierItem(num(23, 32), DIAMOND_HELMET, diamondArmorModifier, 1, 15, 3, 0.2f));
		context.register(ARMORER_5_ENDERITE_SHARD_DIAMOND_CHESTPLATE, shardToModifierItem(num(32, 41), DIAMOND_CHESTPLATE, diamondArmorModifier, 1, 15, 3, 0.2f));


		context.register(BUTCHER_1_ENDERMAN_FLESH_ENDERITE_SHARD, simpleToShard(ENDERMAN_FLESH, num(20, 30), 1, 1, 14, 0.05f));
		context.register(BUTCHER_1_PHANTOM_MEMBRANE_ENDERITE_SHARD, simpleToShard(PHANTOM_MEMBRANE, num(8, 16), 1, 1, 14, 0.05f));

		context.register(BUTCHER_2_COAL_ENDERITE_SHARD, simpleToShard(COAL, 18, 1, 3, 12, 0.05f));
		context.register(BUTCHER_2_CHARCOAL_ENDERITE_SHARD, simpleToShard(CHARCOAL, 24, 1, 3, 12, 0.05f));
		context.register(BUTCHER_2_ENDERMAN_FLESH_ENDERITE_SHARD_GRILLED_ENDERMAN_FLESH, simpleShardToSimple(ENDERMAN_FLESH, 2, 2, GRILLED_ENDERMAN_FLESH, 2, 5, 8, 0.05f));
		context.register(BUTCHER_2_ENDERMAN_FLESH_ENDERITE_SHARD_FROZEN_CARPACCIO, simpleShardToSimple(ENDERMAN_FLESH, 2, 2, FROZEN_CARPACCIO, 2, 5, 8, 0.05f));
		context.register(BUTCHER_3_SHULKER_BODY_ENDERITE_SHARD, simpleToShard(SHULKER_BODY, 2, 1, 12, 14, 0.05f));
		context.register(BUTCHER_3_ENDERITE_SHARD_SHULKER_BODY, shardToSimple(1, SHULKER_BODY, 1, 12, 14, 0.05f));
		context.register(BUTCHER_3_ENDERITE_SHARD_IRON_AXE, shardToModifierItem(num(16, 24), IRON_AXE, List.of(
			new SetEnchantmentsFunction.Builder().withEnchantment(enchants.getOrThrow(Enchantments.LOOTING), num(2)).build()
		), 1, 15, 3, 0.2f));
		context.register(BUTCHER_4_BEEF_LEATHER_ENDERITE_SHARD, simpleSimpleToShard(BEEF, num(5, 10), LEATHER, num(5, 10), 1, 20, 8, 0.05f));
		context.register(BUTCHER_4_MUTTON_WOOL_ENDERITE_SHARD, simpleSimpleToShard(MUTTON, num(5, 10), WOOL.white(), num(5, 10), 1, 20, 8, 0.05f));
		context.register(BUTCHER_4_CHICKEN_FEATHER_ENDERITE_SHARD, simpleSimpleToShard(CHICKEN, num(5, 10), FEATHER, num(5, 10), 1, 20, 8, 0.05f));
		context.register(BUTCHER_4_PORKCHOP_ENDERITE_SHARD, simpleToShard(PORKCHOP, num(8, 15), 1, 20, 8, 0.05f));
		context.register(BUTCHER_4_ENDERITE_SHARD_PHO, shardToSimple(num(16, 23), PHO, 1, 25, 3, 0.05f));
		context.register(BUTCHER_5_ENDERITE_SHARD_SHEPHERDS_PIE, shardToSimple(44, SHEPHERDS_PIE, 1, 50, 2, 0.2f));
		context.register(BUTCHER_5_ENDERITE_SHARD_DRIED_KELP_BLOCK, shardToSimple(5, DRIED_KELP_BLOCK, 2, 20, 8, 0.05f));


		context.register(CARTOGRAPHER_1_PAPER_ENDERITE_SHARD, simpleToShard(PAPER, num(24, 30), 1, 2, 12, 0.05f));
		context.register(CARTOGRAPHER_1_ENDERITE_SHARD_MAP, shardToSimple(num(6, 8), MAP, 1, 1, 10, 0.05f));
		context.register(CARTOGRAPHER_2_GLASS_PANE_ENDERITE_SHARD, simpleToShard(GLASS_PANE, num(14, 20), 1, 8, 14, 0.05f));
		context.register(CARTOGRAPHER_2_MAP_ENDERITE_SHARD_END_CITY_EXPLORER_MAP, simpleShardToModifierItem(MAP, num(1), num(40, 50), MAP, List.of(
			ExplorationMapFunction.makeExplorationMap().setMapDecoration(MapDecorationTypes.PURPLE_BANNER).setZoom((byte) 3).setSearchRadius(96).setSkipKnownStructures(true).setDestination(StellarityStructureTags.EXPLORATION_MAP_END_CITY).build(),
			SetNameFunction.setName(Component.translatable("filled_map.stellarity.end_city").setStyle(Style.EMPTY.withItalic(false)), SetNameFunction.Target.CUSTOM_NAME).build(),
			new SetComponentsFunction(List.of(), DataComponentPatch.builder().set(DataComponents.RARITY, Rarity.RARE).set(StellarityDataComponents.MARKED_ITEM, Unit.INSTANCE).build())
		), 1, 40, 1, 0.2f));
		context.register(CARTOGRAPHER_3_ENDERITE_SHARD_ITEM_FRAME, shardToSimple(2, ITEM_FRAME, 4, 12, 8, 0.05f));
		//TODO: Update with actual chapel of light
		context.register(CARTOGRAPHER_3_MAP_ENDERITE_SHARD_CHAPEL_OF_LIGHT_MAP, simpleShardToModifierItem(MAP, num(1), num(50, 60), MAP, List.of(
			ExplorationMapFunction.makeExplorationMap().setMapDecoration(MapDecorationTypes.PURPLE_BANNER).setZoom((byte) 3).setSearchRadius(96).setSkipKnownStructures(false).setDestination(StellarityStructureTags.EXPLORATION_MAP_END_CITY).build(),
			SetNameFunction.setName(Component.translatable("filled_map.stellarity.chapel_of_light").setStyle(Style.EMPTY.withItalic(false)), SetNameFunction.Target.CUSTOM_NAME).build(),
			new SetComponentsFunction(List.of(), DataComponentPatch.builder().set(DataComponents.RARITY, Rarity.RARE).set(StellarityDataComponents.MARKED_ITEM, Unit.INSTANCE).build())
		), 1, 40, 1, 0.2f));
		context.register(CARTOGRAPHER_4_ENDERITE_SHARD_GLOW_ITEM_FRAME, shardToSimple(3, GLOW_ITEM_FRAME, 2, 18, 8, 0.05f));
		context.register(CARTOGRAPHER_4_ENDERITE_SHARD_PHANTOM_ITEM_FRAME, shardToSimple(num(4, 6), PHANTOM_ITEM_FRAME, 3, 20, 4, 0.05f));
		context.register(CARTOGRAPHER_5_ENDERITE_SHARD_MOJANG_BANNER_PATTERN, shardToSimple(num(15, 23), MOJANG_BANNER_PATTERN, 1, 30, 8, 0.05f));


		context.register(CLERIC_1_PHANTOM_MEMBRANE_ENDERITE_SHARD, simpleToShard(PHANTOM_MEMBRANE, num(15, 18), 1, 3, 8, 0.05F));
		context.register(CLERIC_1_BONE_ENDERITE_SHARD, simpleToShard(BONE, num(24, 33), 1, 2, 12, 0.05f));
		context.register(CLERIC_1_ENDERMANS_HAND_ENDERITE_SHARD, simpleToShard(ENDERMANS_HAND, 1, 2, 3, 12, 0.05f));
		context.register(CLERIC_1_ENDERITE_SHARD_REDSTONE, shardToSimple(1, REDSTONE, 4, 1, 16, 0.05f));
		context.register(CLERIC_1_ENDERITE_SHARD_BLAZE_ROD, shardToSimple(2, BLAZE_ROD, 1, 2, 8, 0.05f));
		context.register(CLERIC_2_GOLD_INGOT_ENDERITE_SHARD, simpleToShard(GOLD_INGOT, 3, 1, 5, 10, 0.05f));
		context.register(CLERIC_2_ENDERITE_SHARD_LAPIS_LAZULI, shardToSimple(1, LAPIS_LAZULI, 3, 5, 10, 0.05f));
		context.register(CLERIC_2_ENDERITE_SHARD_NETHER_WART, shardToSimple(1, NETHER_WART, 2, 3, 12, 0.05f));
		context.register(CLERIC_3_ENDERITE_SHARD_LEVEL_3_POTION, shardToModifierItem(num(7, 11), POTION, List.of(
			SetRandomPotionFunction.fromTagKey(HolderSet.direct(Potions.HEALING, Potions.SWIFTNESS, Potions.LEAPING)).build()
		), 1, 10, 3, 0.05f));
		context.register(CLERIC_3_ENDERITE_SHARD_GLOWSTONE, shardToSimple(1, GLOWSTONE, 1, 5, 12, 0.05f));
		context.register(CLERIC_4_ENDER_PEARL_ENDERITE_SHARD, simpleSimpleToShard(ENDER_PEARL, num(16), ENDER_PEARL, num(1, 16), 1, 6, 12, 0.05f));
		context.register(CLERIC_4_ENDERITE_SHARD_LEVEL_4_POTION, shardToModifierItem(num(7, 11), POTION, List.of(
			SetRandomPotionFunction.fromTagKey(HolderSet.direct(Potions.STRENGTH, StellarityPotions.ENDURANCE)).build()
		), 1, 15, 3, 0.2f));
		context.register(CLERIC_5_ENDERITE_SHARD_LEVEL_5_POTION, shardToModifierItem(num(11, 18), POTION, List.of(
			SetRandomPotionFunction.fromTagKey(HolderSet.direct(StellarityPotions.RED, StellarityPotions.LIFEFORCE)).build()
		), 1, 25, 2, 0.2f));
		context.register(CLERIC_5_DRAGONS_BREATH_ENDERITE_SHARD, simpleToShard(DRAGON_BREATH, 3, 1, 15, 10, 0.05f));


		context.register(FARMER_1_WHEAT_ENDERITE_SHARD, simpleToShard(WHEAT, num(15, 30), 1, 2, 8, 0.05f));
		context.register(FARMER_1_POTATO_ENDERITE_SHARD, simpleToShard(POTATO, num(20, 35), 1, 3, 8, 0.05f));
		context.register(FARMER_1_CARROT_ENDERITE_SHARD, simpleToShard(CARROT, num(20, 35), 1, 3, 8, 0.05f));
		context.register(FARMER_1_BEETROOT_ENDERITE_SHARD, simpleToShard(BEETROOT, num(15, 25), 1, 2, 10, 0.05f));
		context.register(FARMER_1_ENDERITE_SHARD_BREAD, shardToSimple(1, BREAD, 6, 2, 12, 0.05f));
		context.register(FARMER_1_ENDERITE_SHARD_CAKE, shardToSimple(1, CAKE, 1, 2, 10, 0.05f));
		context.register(FARMER_2_CHORUS_FRUIT_ENDERITE_SHARD, simpleToShard(CHORUS_FRUIT, num(20, 28), 1, 5, 6, 0.05f));
		context.register(FARMER_2_CHORUS_FLOWER_ENDERITE_SHARD, simpleToShard(CHORUS_FLOWER, num(5, 7), 1, 8, 12, 0.05f));
		context.register(FARMER_2_ENDERITE_SHARD_CHORUS_PIE, shardToSimple(1, CHORUS_PIE, 2, 15, 6, 0.2f));
		context.register(FARMER_2_ENDERITE_SHARD_SHROOMLIGHT, shardToSimple(1, SHROOMLIGHT, 2, 4, 8, 0.05f));
		context.register(FARMER_3_ENDERITE_SHARD_CANDIED_CHORUS_FRUIT, shardToSimple(2, CANDIED_CHORUS_FRUIT, 1, 20, 3, 0.2f));
		context.register(FARMER_3_WHEAT_SEEDS_ENDERITE_SHARD_TORCHFLOWER_SEEDS, simpleShardToSimple(WHEAT_SEEDS, num(12, 16), num(1), TORCHFLOWER_SEEDS, 4, 5, 12, 0.05f));
		context.register(FARMER_3_WHEAT_SEEDS_ENDERITE_SHARD_PITCHER_POD, simpleShardToSimple(WHEAT_SEEDS, num(8, 11), num(1), PITCHER_POD, 3, 5, 8, 0.05f));
		context.register(FARMER_4_ENDERITE_SHARD_CHORUS_JUICE, shardToModifierItem(3, POTION, List.of(
			SetPotionFunction.setPotion(StellarityPotions.CHORUS_JUICE).build()
		), 1, 15, 8, 0.2f));
		context.register(FARMER_4_ENDERITE_SHARD_CHORUS_STEW, shardToSimple(4, CHORUS_STEW, 1, 15, 6, 0.2f));
		context.register(FARMER_4_ENDERITE_SHARD_FRIED_CHORUS_FRUIT, shardToSimple(num(5, 6), FRIED_CHORUS_FRUIT, 2, 15, 8, 0.2f));
		context.register(FARMER_5_ENDERITE_SHARD_PHO, shardToSimple(num(12, 18), PHO, 1, 25, 3, 0.2f));
		context.register(FARMER_5_BREAD_ENDERITE_SHARD_LOAF_OF_PLENTY, simpleShardToSimplePredicate(BREAD, 10, 64, LOAF_OF_PLENTY, 1, 50, 2, 0.2f, LootItemRandomChanceCondition.randomChance(0.2f).build()));

		context.register(FISHERMAN_1_FISHING_ROD_ENDERITE_SHARD, simpleToShard(FISHING_ROD, 1, 2, 10, 1, 0.05f));
		context.register(FISHERMAN_1_ENDERITE_SHARD_FISHING_ROD, shardToSimple(3, FISHING_ROD, 1, 4, 2, 0.05f));
		context.register(FISHERMAN_1_STRING_ENDERITE_SHARD, simpleToShard(STRING, num(20, 32), 1, 5, 6, 0.05f));
		context.register(FISHERMAN_1_COAL_ENDERITE_SHARD, simpleToShard(COAL, num(20, 26), 1, 5, 8, 0.05f));
		context.register(FISHERMAN_2_ENDERITE_SHARD_CAMPFIRE, shardToSimple(1, CAMPFIRE, 1, 5, 8, 0.05f));
		context.register(FISHERMAN_2_ENDERITE_SHARD_SOUL_CAMPFIRE, shardToSimple(2, SOUL_CAMPFIRE, 1, 8, 6, 0.05f));
		for (var fishTrade : List.of(
			new Tuple2<>(FISHERMAN_2_ENDER_KOI_ENDERITE_SHARD, ENDER_KOI),
			new Tuple2<>(FISHERMAN_2_OVERGROWN_COD_ENDERITE_SHARD, OVERGROWN_COD),
			new Tuple2<>(FISHERMAN_2_FROST_MINNOW_ENDERITE_SHARD, FROST_MINNOW),
			new Tuple2<>(FISHERMAN_2_GOOSH_ENDERITE_SHARD, GOOSH),
			new Tuple2<>(FISHERMAN_3_CRIMSON_TIGERFISH_ENDERITE_SHARD, CRIMSON_TIGERFISH),
			new Tuple2<>(FISHERMAN_3_FLESHY_PIRANHA_ENDERITE_SHARD, FLESHY_PIRANHA),
			new Tuple2<>(FISHERMAN_3_FLAREFIN_KOI_ENDERITE_SHARD, FLAREFIN_KOI),
			new Tuple2<>(FISHERMAN_3_POTASSIFISH_ENDERITE_SHARD, POTASSIFISH)
		))
			context.register(fishTrade._1(), simpleToShard(fishTrade._2(), num(4, 6), 1, 4, 8, 0.05f));
		context.register(FISHERMAN_3_ENDERITE_SHARD_FISHING_ROD, shardToModifierItem(num(5, 8), FISHING_ROD, List.of(
			enchant(enchants, 15, 29).build()
		), 1, 4, 2, 0.05f));
		context.register(FISHERMAN_4_ENDERITE_SHARD_LURE_BOOK, shardToModifierItem(num(10, 14), BOOK, List.of(
			enchant(enchants, Enchantments.LURE).build()
		), 1, 10, 2, 0.2f));
		context.register(FISHERMAN_4_ENDERITE_SHARD_LUCK_OF_THE_SEA_BOOK, shardToModifierItem(num(10, 14), BOOK, List.of(
			enchant(enchants, Enchantments.LUCK_OF_THE_SEA).build()
		), 1, 10, 2, 0.2f));
		context.register(FISHERMAN_4_ENDERITE_SHARD_FISHER_OF_VOIDS, shardToSimple(num(14, 20), FISHER_OF_VOIDS, 1, 15, 6, 0.2f));
		context.register(FISHERMAN_5_CRYSTAL_HEARTFISH_ENDERITE_SHARD, simpleToShard(CRYSTAL_HEARTFISH, 1, 5, 15, 3, 0.2f));
		context.register(FISHERMAN_5_ENDERITE_SHARD_PRISMATIC_SUSHI, shardToSimple(num(14, 20), PRISMATIC_SUSHI, 1, 15, 4, 0.2f));


		context.register(FLETCHER_1_ENDERITE_SHARD_SPECTRAL_ARROW, shardToSimple(2, SPECTRAL_ARROW, 10, 8, 12, 0.05f));
		context.register(FLETCHER_1_ENDERITE_SHARD_ARROW, shardToSimple(1, ARROW, 16, 6, 8, 0.05f));
		context.register(FLETCHER_1_STICK_ENDERITE_SHARD, simpleToShard(STICK, num(24, 36), 1, 2, 12, 0.05f));
		context.register(FLETCHER_2_FLINT_ENDERITE_SHARD, simpleToShard(FLINT, num(10, 20), 1, 2, 12, 0.05f));
		context.register(FLETCHER_2_ENDERITE_SHARD_BOW, shardToModifierItem(num(8, 15), BOW, List.of(
			enchant(enchants, 15, 29).build()
		), 1, 10, 8, 0.05f));
		context.register(FLETCHER_3_STRING_ENDERITE_SHARD, simpleToShard(STRING, num(12, 20), 1, 4, 12, 0.05f));
		context.register(FLETCHER_3_FEATHER_ENDERITE_SHARD, simpleToShard(FEATHER, num(7, 15), 1, 6, 6, 0.05f));
		context.register(FLETCHER_3_ENDERITE_SHARD_CROSSBOW, shardToModifierItem(num(10, 17), CROSSBOW, List.of(
			enchant(enchants, 15, 29).build()
		), 1, 12, 2, 0.2f));
		context.register(FLETCHER_4_ARROW_ENDERITE_SHARD_LEVITATION_TIPPED_ARROW, simpleShardToModifierItem(ARROW, num(8), num(9, 17), TIPPED_ARROW, List.of(
			component(DataComponents.POTION_CONTENTS, new PotionContents(Optional.empty(), Optional.empty(), List.of(new MobEffectInstance(MobEffects.LEVITATION, 90 * 20)), Optional.empty())).build()
		), 8, 8, 6, 0.05f));
		context.register(FLETCHER_4_ARROW_ENDERITE_SHARD_LUCK_TIPPED_ARROW, simpleShardToModifierItem(ARROW, 8, 64, TIPPED_ARROW, List.of(
			potion(Potions.LUCK).build()
		), 8, 8, 5, 0.05f));
		context.register(FLETCHER_5_BOW_ENDERITE_SHARD_SHARANGA, simpleShardToSimple(BOW, 1, 64, SHARANGA, 1, 25, 2, 0.2f));


		context.register(LEATHERWORKER_1_LEATHER_ENDERITE_SHARD, simpleToShard(LEATHER, num(8, 15), 1, 3, 8, 0.05f));
		context.register(LEATHERWORKER_1_FLINT_ENDERITE_SHARD, simpleToShard(FLINT, num(10, 20), 1, 3, 6, 0.05f));
		context.register(LEATHERWORKER_2_ENDERITE_SHARD_SADDLE, shardToSimple(num(5, 10), SADDLE, 1, 5, 3, 0.05f));
		context.register(LEATHERWORKER_2_ENDERITE_SHARD_LEATHER, shardToSimple(1, LEATHER, 3, 4, 6, 0.05f));
		context.register(LEATHERWORKER_3_ENDERITE_SHARD_CAULDRON, shardToSimple(1, CAULDRON, 1, 8, 6, 0.05f));
		context.register(LEATHERWORKER_3_RABBIT_HIDE_ENDERITE_SHARD, simpleToShard(RABBIT_HIDE, num(4, 6), 1, 6, 8, 0.05f));
		context.register(LEATHERWORKER_4_ITEM_FRAME_ENDERITE_SHARD_GLOW_ITEM_FRAME, simpleShardToSimple(ITEM_FRAME, 2, 1, GLOW_ITEM_FRAME, 2, 8, 4, 0.05f));
		context.register(LEATHERWORKER_4_ENDERITE_SHARD_TURTLE_SCUTE, shardToSimple(num(5, 10), TURTLE_SCUTE, 1, 6, 8, 0.05f));
		context.register(LEATHERWORKER_4_ENDERITE_SHARD_ARMADILLO_SCUTE, shardToSimple(num(7, 12), ARMADILLO_SCUTE, 1, 6, 8, 0.05f));
		context.register(LEATHERWORKER_5_DIAMOND_HORSE_ARMOR_ENDERITE_SHARD_REINFORCED_HORSE_ARMOR, simpleShardToSimple(DIAMOND_HORSE_ARMOR, num(1), num(24, 32), REINFORCED_HORSE_ARMOR, 1, 20, 2, 0.2f));

		context.register(LIBRARIAN_1_PAPER_ENDERITE_SHARD, simpleToShard(PAPER, num(24, 36), 1, 2, 6, 0.05f));
		context.register(LIBRARIAN_1_BOOK_ENDERITE_SHARD, simpleToShard(BOOK, 3, 1, 3, 8, 0.05f));
		context.register(LIBRARIAN_1_ENDERITE_SHARD_BOOKSHELF, shardToSimple(num(4, 6), BOOKSHELF, 1, 4, 8, 0.05f));
		context.register(LIBRARIAN_1_BOOK_ENDERITE_SHARD_LEVEL_1_ENCHANTED_BOOK, simpleShardToModifierItem(BOOK, num(1), num(15, 24), BOOK, List.of(
			enchant(enchants, Enchantments.FIRE_ASPECT, Enchantments.FLAME, Enchantments.KNOCKBACK, Enchantments.AQUA_AFFINITY).build()
		), 1, 8, 3, 0.2f));
		context.register(LIBRARIAN_2_ENDERITE_SHARD_LANTERN, shardToSimple(1, LANTERN, 3, 4, 8, 0.05f));
		context.register(LIBRARIAN_2_BOOK_ENDERITE_SHARD_LEVEL_2_ENCHANTED_BOOK, simpleShardToModifierItem(BOOK, num(1), num(15, 24), BOOK, List.of(
			enchant(enchants, Enchantments.PROTECTION, Enchantments.FIRE_PROTECTION, Enchantments.BLAST_PROTECTION, Enchantments.SILK_TOUCH, Enchantments.FEATHER_FALLING, Enchantments.UNBREAKING).build()
		), 1, 15, 3, 0.2f));
		context.register(LIBRARIAN_3_ENDERITE_SHARD_INK_SAC, shardToSimple(1, INK_SAC, 2, 4, 8, 0.05f));
		context.register(LIBRARIAN_3_ENDERITE_SHARD_GLOW_INK_SAC, shardToSimple(1, GLOW_INK_SAC, 1, 5, 6, 0.05f));
		context.register(LIBRARIAN_3_ENDERITE_SHARD_GLASS, shardToSimple(1, GLASS, 6, 3, 8, 0.05f));
		context.register(LIBRARIAN_4_BOOK_ENDERITE_SHARD_LEVEL_4_ENCHANTED_BOOK, simpleShardToModifierItem(BOOK, num(1), num(15, 24), BOOK, List.of(
			enchant(enchants, Enchantments.MENDING, Enchantments.FORTUNE, Enchantments.KNOCKBACK, Enchantments.DEPTH_STRIDER, Enchantments.BREACH, Enchantments.DENSITY, Enchantments.SHARPNESS).build()
		), 1, 20, 2, 0.2f));
		context.register(LIBRARIAN_4_ENDERITE_SHARD_WRITABLE_BOOK, shardToSimple(1, WRITABLE_BOOK, 1, 6, 6, 0.05f));
		context.register(LIBRARIAN_4_ENDERITE_SHARD_NAME_TAG, shardToSimple(num(7, 15), NAME_TAG, 1, 6, 3, 0.05f));
		context.register(LIBRARIAN_5_BOOK_ENDERITE_SHARD_BOOK_OF_UPDRAFT, simpleShardToSimple(BOOK, 1, 64, BOOK_OF_UPDRAFT, 1, 20, 1, 0.2f));
		context.register(LIBRARIAN_5_BOOK_ENDERITE_SHARD_BOOK_OF_LIGHT, simpleShardToSimple(BOOK, 1, 64, BOOK_OF_LIGHT, 1, 20, 1, 0.2f));
		context.register(LIBRARIAN_5_BOOK_ENDERITE_SHARD_BOOK_OF_OBSTRUCT, simpleShardToSimple(BOOK, 1, 64, BOOK_OF_OBSTRUCT, 1, 20, 1, 0.2f));


		context.register(MASON_1_ENDERITE_SHARD_PURPUR_BLOCK, shardToSimple(1, PURPUR_BLOCK, 4, 2, 8, 0.05f));
		context.register(MASON_1_ENDERITE_SHARD_END_STONE_BRICKS, shardToSimple(1, END_STONE_BRICKS, 4, 3, 8, 0.05f));
		context.register(MASON_2_AMETHYST_BUDFISH_AMETHYST_SHARD, simpleToSimple(AMETHYST_BUDFISH, 1, AMETHYST_SHARD, 4, 4, 6, 0.05f));
		context.register(MASON_2_AMETHYST_BUDFISH_QUARTZ, simpleToSimple(AMETHYST_BUDFISH, 1, QUARTZ, 8, 5, 6, 0.05f));
		context.register(MASON_2_ENDERITE_SHARD_OBSIDIAN, shardToSimple(2, OBSIDIAN, 3, 6, 8, 0.05f));
		context.register(MASON_2_ENDERITE_SHARD_SMOOTH_QUARTZ, shardToSimple(2, SMOOTH_QUARTZ, 4, 6, 6, 0.05f));
		context.register(MASON_3_ENDERITE_SHARD_POLISHED_BLACKSTONE, shardToSimple(1, POLISHED_BLACKSTONE, 4, 6, 8, 0.05f));
		context.register(MASON_3_ENDERITE_SHARD_POLISHED_DEEPSLATE, shardToSimple(1, POLISHED_DEEPSLATE, 4, 6, 8, 0.05f));
		context.register(MASON_3_COBBLED_DEEPSLATE_ENDERITE_SHARD, simpleToShard(COBBLED_DEEPSLATE, num(15, 25), 1, 6, 6, 0.05f));
		context.register(MASON_3_BLACKSTONE_ENDERITE_SHARD, simpleToShard(BLACKSTONE, num(15, 25), 1, 6, 6, 0.05f));
		context.register(MASON_3_ENDERITE_SHARD_QUARTZ_BLOCK, shardToSimple(1, QUARTZ_BLOCK, 4, 8, 6, 0.05f));
		context.register(MASON_3_ENDERITE_SHARD_QUARTZ_PILLAR, shardToSimple(1, QUARTZ_PILLAR, 3, 8, 6, 0.05f));
		ColorCollection.zipApply(GLAZED_TERRACOTTA, MASON_4_ENDERITE_SHARD_GLAZED_TERRACOTA, (item, trade) ->
			context.register(trade, shardToSimple(1, item, 2, 12, 6, 0.05f))
		);
		ColorCollection.zipApply(DYED_TERRACOTTA, MASON_4_ENDERITE_SHARD_TERRACOTA, (item, trade) ->
			context.register(trade, shardToSimple(1, item, 2, 12, 6, 0.05f))
		);
		context.register(MASON_5_ENDERITE_SHARD_CRYING_OBSIDIAN, shardToSimple(num(4, 6), CRYING_OBSIDIAN, 2, 16, 4, 0.05f));
		context.register(MASON_5_ENDERITE_SHARD_PURPUR_PILLAR, shardToSimple(1, PURPUR_PILLAR, 4, 12, 6, 0.05f));
		context.register(MASON_5_ENDERITE_SHARD_CHISELED_QUARTZ_BLOCK, shardToSimple(1, CHISELED_QUARTZ_BLOCK, 2, 12, 6, 0.05f));


		context.register(SHEPHERD_1_ENDERITE_SHARD_SHEARS, shardToSimple(1, SHEARS, 1, 2, 4, 0.05f));
		context.register(SHEPHERD_1_WHITE_WOOL_ENDERITE_SHARD, simpleToShard(WOOL.white(), num(8, 16), 1, 3, 6, 0.05f));
		ColorCollection.zipApply(DYE, SHEPHERD_2_ENDERITE_SHARD_DYE, (item, trade) ->
			context.register(trade, shardToSimple(1, item, 3, 4, 8, 0.05f))
		);
		ColorCollection.zipApply(WOOL, SHEPHERD_2_ENDERITE_SHARD_WOOL, (item, trade) ->
			context.register(trade, shardToSimple(1, item, 2, 4, 8, 0.05f))
		);
		ColorCollection.zipApply(CARPET, SHEPHERD_3_ENDERITE_SHARD_CARPET, (item, trade) ->
			context.register(trade, shardToSimple(1, item, 5, 6, 6, 0.05f))
		);
		ColorCollection.zipApply(WOOL, SHEPHERD_3_ENDERITE_SHARD_WOOL, (item, trade) ->
			context.register(trade, shardToSimple(1, item, 2, 6, 8, 0.05f))
		);
		ColorCollection.zipApply(BED, SHEPHERD_3_ENDERITE_SHARD_BED, (item, trade) ->
			context.register(trade, shardToSimple(1, item, 1, 10, 4, 0.05f))
		);
		ColorCollection.zipApply(BANNER, SHEPHERD_4_ENDERITE_SHARD_BANNER, (item, trade) ->
			context.register(trade, shardToSimple(1, item, 1, 8, 6, 0.05f))
		);
		ColorCollection.zipApply(WOOL, SHEPHERD_4_ENDERITE_SHARD_WOOL, (item, trade) ->
			context.register(trade, shardToSimple(1, item, 2, 6, 6, 0.05f))
		);
		ColorCollection.zipApply(DYE, SHEPHERD_4_ENDERITE_SHARD_DYE, (item, trade) ->
			context.register(trade, shardToSimple(1, item, 3, 6, 6, 0.05f))
		);
		for (var paintingTrade : List.of(
			new Tuple2<>(SHEPHERD_5_ENDERITE_SHARD_FIRE_PAINTING, PaintingVariants.FIRE),
			new Tuple2<>(SHEPHERD_5_ENDERITE_SHARD_WIND_PAINTING, PaintingVariants.WIND),
			new Tuple2<>(SHEPHERD_5_ENDERITE_SHARD_EARTH_PAINTING, PaintingVariants.EARTH),
			new Tuple2<>(SHEPHERD_5_ENDERITE_SHARD_WATER_PAINTING, PaintingVariants.WATER),
			new Tuple2<>(SHEPHERD_5_ENDERITE_SHARD_A_HOP_AND_A_SKIP_AWAY_PAINTING, StellarityPaintings.A_HOP_AND_A_SKIP_AWAY),
			new Tuple2<>(SHEPHERD_5_ENDERITE_SHARD_DRAGONBLADE_PAINTING, StellarityPaintings.DRAGONBLADE),
			new Tuple2<>(SHEPHERD_5_ENDERITE_SHARD_END_PAINTING, StellarityPaintings.END),
			new Tuple2<>(SHEPHERD_5_ENDERITE_SHARD_END_BLOSSOM_PAINTING, StellarityPaintings.END_BLOSSOM),
			new Tuple2<>(SHEPHERD_5_ENDERITE_SHARD_HOURGLASS_PAINTING, StellarityPaintings.HOURGLASS),
			new Tuple2<>(SHEPHERD_5_ENDERITE_SHARD_MAJESTICAL_BREW_PAINTING, StellarityPaintings.MAJESTICAL_BREW),
			new Tuple2<>(SHEPHERD_5_ENDERITE_SHARD_SCHEME_PAINTING, StellarityPaintings.SCHEME),
			new Tuple2<>(SHEPHERD_5_ENDERITE_SHARD_SHEPHERDS_FEAST_PAINTING, StellarityPaintings.SHEPHERDS_FEAST),
			new Tuple2<>(SHEPHERD_5_ENDERITE_SHARD_SNARE_PAINTING, StellarityPaintings.SNARE),
			new Tuple2<>(SHEPHERD_5_ENDERITE_SHARD_SNATCH_PAINTING, StellarityPaintings.SNATCH),
			new Tuple2<>(SHEPHERD_5_ENDERITE_SHARD_THE_OBSIDIAN_RELIQUARY_PAINTING, StellarityPaintings.THE_OBSIDIAN_RELIQUARY)
		))
			context.register(paintingTrade._1(), shardToModifierItem(num(4, 6), PAINTING, List.of(
				SetComponentsFunction.setComponent(DataComponents.PAINTING_VARIANT, paintings.getOrThrow(paintingTrade._2())).build()
			), 1, 20, 2, 0.2f));


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

	static VillagerTrade simpleShardToModifierItem(Item buy, int buyCount, int shards, Item item, List<LootItemFunction> modifiers, int count, int xp, int maxUses, float repDiscount) {
		return simpleShardToModifierItem(buy, num(buyCount), num(shards), item, modifiers, count, xp, maxUses, repDiscount);
	}

	static VillagerTrade simpleShardToModifierItem(Item buy, NumberProvider buyCount, NumberProvider shards, Item item, List<LootItemFunction> modifiers, int count, int xp, int maxUses, float repDiscount) {
		return new VillagerTrade(
			new TradeCost(buy, buyCount),
			Optional.of(new TradeCost(ENDERITE_SHARD, shards)),
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

	static VillagerTrade simpleShardToSimplePredicate(Item item, int count, int shards, Item result, int resultCount, int xp, int maxUses, float repDiscount, LootItemCondition predicate) {
		return simpleShardToSimplePredicate(item, num(count), num(shards), result, resultCount, xp, maxUses, repDiscount, predicate);
	}

	static VillagerTrade simpleShardToSimplePredicate(Item item, NumberProvider count, NumberProvider shards, Item result, int resultCount, int xp, int maxUses, float repDiscount, LootItemCondition predicate) {
		return new VillagerTrade(
			new TradeCost(item, count),
			Optional.of(new TradeCost(ENDERITE_SHARD, shards)),
			new ItemStackTemplate(result, resultCount),
			maxUses,
			xp,
			repDiscount,
			Optional.of(predicate),
			List.of()
		);
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

	static VillagerTrade simpleToSimple(Item item, int count, Item result, int resultCount, int xp, int maxUses, float repDiscount) {
		return simpleToSimple(item, num(count), result, resultCount, xp, maxUses, repDiscount);
	}

	static VillagerTrade simpleToSimple(Item item, NumberProvider count, Item result, int resultCount, int xp, int maxUses, float repDiscount) {
		return new VillagerTrade(
			new TradeCost(item, count),
			Optional.empty(),
			new ItemStackTemplate(result, resultCount),
			maxUses,
			xp,
			repDiscount,
			Optional.empty(),
			List.of()
		);
	}

	static VillagerTrade simpleSimpleToShard(Item item, int count, Item item2, int count2, int shards, int xp, int maxUses, float repDiscount) {
		return simpleSimpleToShard(item, num(count), item2, num(count2), shards, xp, maxUses, repDiscount);
	}

	static VillagerTrade simpleSimpleToShard(Item item, NumberProvider count, Item item2, NumberProvider count2, int shards, int xp, int maxUses, float repDiscount) {
		return new VillagerTrade(
			new TradeCost(item, count),
			Optional.of(new TradeCost(item2, count2)),
			new ItemStackTemplate(ENDERITE_SHARD, shards),
			maxUses,
			xp,
			repDiscount,
			Optional.empty(),
			List.of()
		);
	}

	private static ResourceKey<VillagerTrade> id(String id) {
		return Stellarity.key(Registries.VILLAGER_TRADE, id);
	}
}
