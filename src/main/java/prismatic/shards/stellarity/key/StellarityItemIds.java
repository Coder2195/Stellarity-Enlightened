package prismatic.shards.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityItemIds {
	ResourceKey<Item> ENDER_DIRT = id("ender_dirt");
	ResourceKey<Item> ENDER_GRASS_BLOCK = id("ender_grass_block");
	ResourceKey<Item> ASHEN_FROGLIGHT = id("ashen_froglight");
	ResourceKey<Item> ROOTED_ENDER_DIRT = id("rooted_ender_dirt");
	ResourceKey<Item> ENDER_DIRT_PATH = id("ender_dirt_path");
	ResourceKey<Item> ALTAR_OF_THE_ACCURSED = id("altar_of_the_accursed");
	ResourceKey<Item> ENDERITE_BLOCK = id("enderite_block");
	ResourceKey<Item> COARSE_ENDER_DIRT = id("coarse_ender_dirt");

	ResourceKey<Item> CALL_OF_THE_VOID = id("call_of_the_void");
	ResourceKey<Item> FISHER_OF_VOIDS = id("fisher_of_voids");
	ResourceKey<Item> SUSHI = id("sushi");
	ResourceKey<Item> GOLDEN_CHORUS_FRUIT = id("golden_chorus_fruit");
	ResourceKey<Item> FRIED_CHORUS_FRUIT = id("fried_chorus_fruit");
	ResourceKey<Item> FROZEN_CARPACCIO = id("frozen_carpaccio");
	ResourceKey<Item> ENDERMAN_FLESH = id("enderman_flesh");
	ResourceKey<Item> CRYSTAL_HEARTFISH = id("crystal_heartfish");
	ResourceKey<Item> GRILLED_ENDERMAN_FLESH = id("grilled_enderman_flesh");
	ResourceKey<Item> FLAREFIN_KOI = id("flarefin_koi");
	ResourceKey<Item> AMETHYST_BUDFISH = id("amethyst_budfish");
	ResourceKey<Item> CRIMSON_TIGERFISH = id("crimson_tigerfish");
	ResourceKey<Item> ENDER_KOI = id("ender_koi");
	ResourceKey<Item> FLESHY_PIRANHA = id("fleshy_piranha");
	ResourceKey<Item> BUBBLEFISH = id("bubblefish");
	ResourceKey<Item> PRISMITE = id("prismite");
	ResourceKey<Item> OVERGROWN_COD = id("overgrown_cod");
	ResourceKey<Item> SHULKER_BODY = id("shulker_body");
	ResourceKey<Item> PRISMATIC_SUSHI = id("prismatic_sushi");
	ResourceKey<Item> SHEPHERDS_PIE = id("shepherds_pie");
	ResourceKey<Item> CHORUS_PIE = id("chorus_pie");
	ResourceKey<Item> PHANTOM_ITEM_FRAME = id("phantom_item_frame");
	ResourceKey<Item> PHO = id("pho");
	ResourceKey<Item> FROST_MINNOW = id("frost_minnow");
	ResourceKey<Item> GOOSH = id("goosh");
	ResourceKey<Item> CHORUS_STEW = id("chorus_stew");
	ResourceKey<Item> TAMARIS = id("tamaris");
	ResourceKey<Item> CHORUS_PLATING = id("chorus_plating");
	ResourceKey<Item> ENDERITE_SHARD = id("enderite_shard");
	ResourceKey<Item> ENDERITE_UPGRADE_SMITHING_TEMPLATE = id("enderite_upgrade_smithing_template");
	ResourceKey<Item> HALLOWED_INGOT = id("hallowed_ingot");
	ResourceKey<Item> SAND_RUNE = id("sand_rune");
	ResourceKey<Item> STARLIGHT_SOOT = id("starlight_soot");
	ResourceKey<Item> GILDED_PURPUR_KEY = id("gilded_purpur_key");
	ResourceKey<Item> PURPUR_KEY = id("purpur_key");
	ResourceKey<Item> WINGED_KEY = id("winged_key");
	ResourceKey<Item> PRISMATIC_PEARL = id("prismatic_pearl");
	ResourceKey<Item> ENDONOMICON = id("endonomicon");
	ResourceKey<Item> MUSIC_DISC_DEVIANTS_LIGHT_MUSIC_BOX = id("music_disc_deviants_light_music_box");
	ResourceKey<Item> MUSIC_DISC_FIRES_OF_HOKKAI = id("music_disc_fires_of_hokkai");
	ResourceKey<Item> MUSIC_DISC_PRECIPICE_STEREO = id("music_disc_precipice_stereo");
	ResourceKey<Item> ROYAL_JELLY = id("royal_jelly");
	ResourceKey<Item> ROYAL_JELLY_II = id("royal_jelly_ii");
	ResourceKey<Item> SATCHEL_OF_VOIDS = id("satchel_of_voids");
	ResourceKey<Item> DUSKBERRY = id("duskberry");
	ResourceKey<Item> SHULKER_HELMET = id("shulker_helmet");
	ResourceKey<Item> SHULKER_CHESTPLATE = id("shulker_chestplate");
	ResourceKey<Item> SHULKER_LEGGINGS = id("shulker_leggings");
	ResourceKey<Item> SHULKER_BOOTS = id("shulker_boots");
	ResourceKey<Item> ENDER_EGG = id("ender_egg");

	ResourceKey<Item> VOIDED_ZOMBIE_SPAWN_EGG = id(StellarityEntityIds.VOIDED_ZOMBIE);
	ResourceKey<Item> VOIDED_SKELETON_SPAWN_EGG = id(StellarityEntityIds.VOIDED_SKELETON);
	ResourceKey<Item> VOIDED_SILVERFISH_SPAWN_EGG = id(StellarityEntityIds.VOIDED_SILVERFISH);
	ResourceKey<Item> VOIDED_SLIME_SPAWN_EGG = id(StellarityEntityIds.VOIDED_SLIME);
	ResourceKey<Item> FLESH_PIGLIN_SPAWN_EGG = id(StellarityEntityIds.FLESH_PIGLIN);

	ResourceKey<Item> POTASSIFISH = id("potassifish");

	static ResourceKey<Item> id(String id) {
		return Stellarity.key(Registries.ITEM, id);
	}

	static ResourceKey<Item> id(ResourceKey<EntityType<?>> entityId) {
		return id(entityId.identifier().getPath() + "_spawn_egg");
	}
}
