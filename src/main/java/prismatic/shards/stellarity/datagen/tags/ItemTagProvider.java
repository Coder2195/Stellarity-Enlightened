package prismatic.shards.stellarity.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.tags.StellarityItemTags;

import java.util.concurrent.CompletableFuture;

import static prismatic.shards.stellarity.registry.StellarityItems.*;


public class ItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

	public ItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
		super(output, completableFuture);
	}


	@Override
	public void addTags(HolderLookup.@NonNull Provider provider) {
		valueLookupBuilder(StellarityItemTags.FISHES).add(
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

		valueLookupBuilder(ItemTags.HEAD_ARMOR).add(SHULKER_HELMET);
		valueLookupBuilder(ItemTags.CHEST_ARMOR).add(SHULKER_CHESTPLATE);
		valueLookupBuilder(ItemTags.LEG_ARMOR).add(SHULKER_LEGGINGS);
		valueLookupBuilder(ItemTags.FOOT_ARMOR).add(SHULKER_BOOTS);


		valueLookupBuilder(ItemTags.FISHES).addTag(StellarityItemTags.FISHES);
		valueLookupBuilder(StellarityItemTags.ELYTRA_ENCHANTABLE).add(Items.ELYTRA);

		valueLookupBuilder(StellarityItemTags.RANGED_ENCHANTABLE)
			.forceAddTag(ItemTags.BOW_ENCHANTABLE)
			.forceAddTag(ItemTags.CROSSBOW_ENCHANTABLE);
	}
}
