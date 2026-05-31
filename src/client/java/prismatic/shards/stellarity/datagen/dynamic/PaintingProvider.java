package prismatic.shards.stellarity.datagen.dynamic;

import net.minecraft.ChatFormatting;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;

import java.util.Optional;

import static prismatic.shards.stellarity.key.StellarityPaintings.*;

public interface PaintingProvider {
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
}
