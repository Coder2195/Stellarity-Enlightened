package dev.coder2195.stellarity.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.BlockItemTagAppender;
import net.minecraft.references.ItemIds;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.NonNull;
import dev.coder2195.stellarity.tags.StellarityItemTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static dev.coder2195.stellarity.registry.StellarityItemIds.*;
import static net.minecraft.references.ItemIds.*;


public class ItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

	public ItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
		super(output, completableFuture);
	}

	@SafeVarargs
	public final BlockItemTagAppender<Item> addTags(TagKey<Item> tagKey, TagKey<Item>... tags) {
		var appender = builder(tagKey);
		for (var tag : tags) {
			appender.forceAddTag(tag);
		}
		return appender;
	}

	@Override
	public void addTags(HolderLookup.@NonNull Provider provider) {
		addTags(StellarityItemTags.FISHES).add(
			AMETHYST_BUDFISH,
			BUBBLEFISH,
			CRIMSON_TIGERFISH,
			ENDER_KOI,
			FLAREFIN_KOI,
			CRYSTAL_HEARTFISH,
			GOOSH,
			FLESHY_PIRANHA,
			OVERGROWN_COD,
			FROST_MINNOW,
			POTASSIFISH,
			PRISMITE
		);

		addTags(StellarityItemTags.BOWS).add(CALL_OF_THE_VOID, SHARANGA, SPECTRAL_FURY);

		addTags(ItemTags.HEAD_ARMOR).add(SHULKER_HELMET, CHAMPION_HELMET, HALLOWED_HELMET, FLORAL_HELMET);
		addTags(ItemTags.CHEST_ARMOR).add(SHULKER_CHESTPLATE, CHAMPION_CHESTPLATE, HALLOWED_CHESTPLATE, FLORAL_CHESTPLATE);
		addTags(ItemTags.LEG_ARMOR).add(SHULKER_LEGGINGS, CHAMPION_LEGGINGS, HALLOWED_LEGGINGS, FLORAL_LEGGINGS);
		addTags(ItemTags.FOOT_ARMOR).add(SHULKER_BOOTS, CHAMPION_BOOTS, HALLOWED_BOOTS, FLORAL_BOOTS);

		addTags(ItemTags.FISHES, StellarityItemTags.FISHES);
		addTags(StellarityItemTags.ELYTRA_ENCHANTABLE).add(ItemIds.ELYTRA);

		addTags(ItemTags.BOW_ENCHANTABLE, StellarityItemTags.BOWS);
		addTags(StellarityItemTags.RANGED_ENCHANTABLE, ItemTags.BOW_ENCHANTABLE, ItemTags.CROSSBOW_ENCHANTABLE);

		addTags(ItemTags.SWORDS).add(TAMARIS, STELLAR_STRIKER);

		// TODO: add shulker spear, and ensure all implementations complete
		var donator = addTags(StellarityItemTags.DONATOR);
		List.of(BELL_FLOWER, LOAF_OF_PLENTY, FLUFFY_HAMMER, SANDSTORM_TRIDENT, SHULKER_PICKAXE, SHULKER_SHOVEL, SHULKER_SWORD, SHULKER_AXE, LOAF_OF_PLENTY).forEach(donator::addOptional);

		// TODO: add beginning and the end, and ensure all implementations complete
		var developer = addTags(StellarityItemTags.DEVELOPER);
		List.of(TAMARIS, HARVESTER, STELLAR_STRIKER, POTASSIFISH).forEach(developer::addOptional);

		addTags(StellarityItemTags.REPAIRS_CHAMPION_ARMOR).add(CHORUS_PLATING);
		addTags(StellarityItemTags.REPAIRS_HALLOWED_ARMOR).add(HALLOWED_INGOT);
		addTags(StellarityItemTags.REPAIRS_REINFORCED_ARMOR).add(NETHERITE_INGOT);
		addTags(StellarityItemTags.REPAIRS_SHULKER_ARMOR).add(SHULKER_SHELL);
		addTags(StellarityItemTags.REPAIRS_FLORAL_ARMOR).add(NETHERITE_INGOT);

		addTags(StellarityItemTags.SHULKER_ARMOR).add(SHULKER_HELMET, SHULKER_CHESTPLATE, SHULKER_LEGGINGS, SHULKER_BOOTS);
		addTags(StellarityItemTags.FLORAL_ARMOR).add(FLORAL_HELMET, FLORAL_CHESTPLATE, FLORAL_LEGGINGS, FLORAL_BOOTS);
		addTags(StellarityItemTags.HALLOWED_ARMOR).add(HALLOWED_HELMET, HALLOWED_CHESTPLATE, HALLOWED_LEGGINGS, HALLOWED_BOOTS);
		addTags(StellarityItemTags.CHAMPION_ARMOR).add(CHAMPION_HELMET, CHAMPION_CHESTPLATE, CHAMPION_LEGGINGS, CHAMPION_BOOTS);

	}
}
