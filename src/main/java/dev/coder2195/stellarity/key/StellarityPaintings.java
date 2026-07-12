package dev.coder2195.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;
import dev.coder2195.stellarity.Stellarity;

public interface StellarityPaintings {
	ResourceKey<PaintingVariant> A_HOP_AND_A_SKIP_AWAY = id("a_hop_and_a_skip_away");
	ResourceKey<PaintingVariant> DRAGONBLADE = id("dragonblade");
	ResourceKey<PaintingVariant> END = id("end");
	ResourceKey<PaintingVariant> END_BLOSSOM = id("end_blossom");
	ResourceKey<PaintingVariant> HOURGLASS = id("hourglass");
	ResourceKey<PaintingVariant> MAJESTICAL_BREW = id("majestical_brew");
	ResourceKey<PaintingVariant> SCHEME = id("scheme");
	ResourceKey<PaintingVariant> SHEPHERDS_FEAST = id("shepherds_feast");
	ResourceKey<PaintingVariant> SNARE = id("snare");
	ResourceKey<PaintingVariant> SNATCH = id("snatch");
	ResourceKey<PaintingVariant> THE_OBSIDIAN_RELIQUARY = id("the_obsidian_reliquary");

	static ResourceKey<PaintingVariant> id(String id) {
		return ResourceKey.create(Registries.PAINTING_VARIANT, Stellarity.id(id));
	}
}
