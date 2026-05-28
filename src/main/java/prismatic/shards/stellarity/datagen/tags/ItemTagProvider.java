package prismatic.shards.stellarity.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.registry.StellarityItems;
import prismatic.shards.stellarity.tags.StellarityItemTags;

import java.util.concurrent.CompletableFuture;

import static prismatic.shards.stellarity.registry.StellarityItems.*;


public class ItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

	public ItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
		super(output, completableFuture);
	}

	@SafeVarargs
	public final TagAppender<Item, Item> addTags(TagKey<Item> tagKey, TagKey<Item>... tags) {
		var appender = valueLookupBuilder(tagKey);
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

		addTags(ItemTags.HEAD_ARMOR).add(SHULKER_HELMET);
		addTags(ItemTags.CHEST_ARMOR).add(SHULKER_CHESTPLATE);
		addTags(ItemTags.LEG_ARMOR).add(SHULKER_LEGGINGS);
		addTags(ItemTags.FOOT_ARMOR).add(SHULKER_BOOTS);

		addTags(ItemTags.FISHES, StellarityItemTags.FISHES);
		addTags(StellarityItemTags.ELYTRA_ENCHANTABLE).add(Items.ELYTRA);

		addTags(ItemTags.BOW_ENCHANTABLE, StellarityItemTags.BOWS);
		addTags(StellarityItemTags.RANGED_ENCHANTABLE, ItemTags.BOW_ENCHANTABLE, ItemTags.CROSSBOW_ENCHANTABLE);
	}
}
