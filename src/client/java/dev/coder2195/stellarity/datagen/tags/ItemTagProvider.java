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

	@SuppressWarnings("unchecked")
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

		addTags(ItemTags.HEAD_ARMOR).add(SHULKER_HELMET);
		addTags(ItemTags.CHEST_ARMOR).add(SHULKER_CHESTPLATE);
		addTags(ItemTags.LEG_ARMOR).add(SHULKER_LEGGINGS);
		addTags(ItemTags.FOOT_ARMOR).add(SHULKER_BOOTS);

		addTags(ItemTags.FISHES, StellarityItemTags.FISHES);
		addTags(StellarityItemTags.ELYTRA_ENCHANTABLE).add(ItemIds.ELYTRA);

		addTags(ItemTags.BOW_ENCHANTABLE, StellarityItemTags.BOWS);
		addTags(StellarityItemTags.RANGED_ENCHANTABLE, ItemTags.BOW_ENCHANTABLE, ItemTags.CROSSBOW_ENCHANTABLE);

		// TODO: add shulker spear, and ensure all implementations complete
		var donator = addTags(StellarityItemTags.DONATOR);
		List.of(BELL_FLOWER, LOAF_OF_PLENTY, FLUFFY_HAMMER, SANDSTORM_TRIDENT, SHULKER_PICKAXE, SHULKER_SHOVEL, SHULKER_SWORD, SHULKER_AXE, LOAF_OF_PLENTY).forEach(donator::addOptional);

		// TODO: add beginning and the end, and ensure all implementations complete
		var developer = addTags(StellarityItemTags.DEVELOPER);
		List.of(TAMARIS, HARVESTER, STELLAR_STRIKER, POTASSIFISH).forEach(developer::addOptional);

	}
}
