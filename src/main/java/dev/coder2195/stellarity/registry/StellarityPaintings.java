package dev.coder2195.stellarity.registry;

import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;
import dev.coder2195.stellarity.Stellarity;

import java.util.Optional;

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

	static void bootstrap(BootstrapContext<PaintingVariant> context) {
		register(context, A_HOP_AND_A_SKIP_AWAY, 4, 4);
		register(context, DRAGONBLADE, 2, 2);
		register(context, END, 5, 5);
		register(context, END_BLOSSOM, 4, 4);
		register(context, HOURGLASS, 2, 1);
		register(context, MAJESTICAL_BREW, 3, 3);
		register(context, SCHEME, 3, 3);
		register(context, SHEPHERDS_FEAST, 2, 2);
		register(context, SNARE, 1, 1);
		register(context, SNATCH, 3, 2);
		register(context, THE_OBSIDIAN_RELIQUARY, 2, 2);
	}

	private static void register(final BootstrapContext<PaintingVariant> context, final ResourceKey<PaintingVariant> id, final int width, final int height) {
		context.register(id, new PaintingVariant(width, height, id.identifier(), Optional.of(Component.translatable(id.identifier().toLanguageKey("painting", "title")).withStyle(ChatFormatting.YELLOW)), Optional.of(Component.translatable(id.identifier().toLanguageKey("painting", "author")).withStyle(ChatFormatting.GRAY))));
	}

	static ResourceKey<PaintingVariant> id(String id) {
		return ResourceKey.create(Registries.PAINTING_VARIANT, Stellarity.id(id));
	}
}
