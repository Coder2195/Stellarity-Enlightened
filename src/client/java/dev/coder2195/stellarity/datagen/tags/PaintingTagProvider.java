package dev.coder2195.stellarity.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;

import java.util.concurrent.CompletableFuture;

import static dev.coder2195.stellarity.registry.StellarityPaintings.*;

public class PaintingTagProvider extends FabricTagsProvider<PaintingVariant>  {
	public PaintingTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
		super(output, Registries.PAINTING_VARIANT, registryLookupFuture);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.Provider registries) {
		builder(PaintingVariantTags.PLACEABLE).add(DRAGONBLADE, HOURGLASS, THE_OBSIDIAN_RELIQUARY, SNATCH, END, SNARE, SCHEME, MAJESTICAL_BREW, A_HOP_AND_A_SKIP_AWAY, SHEPHERDS_FEAST, END_BLOSSOM);
	}
}
