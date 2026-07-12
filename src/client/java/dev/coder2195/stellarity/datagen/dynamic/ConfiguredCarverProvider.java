package dev.coder2195.stellarity.datagen.dynamic;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.carver.*;
import dev.coder2195.stellarity.tags.StellarityBlockTags;

import static net.minecraft.world.level.block.Blocks.*;
import static dev.coder2195.stellarity.key.StellarityConfiguredCarvers.*;
import static dev.coder2195.stellarity.util.ValueUtil.*;
import static dev.coder2195.stellarity.util.WorldgenUtil.*;

public interface ConfiguredCarverProvider {
	static void bootstrap(BootstrapContext<ConfiguredWorldCarver<?>> context) {
		var blocks = context.lookup(Registries.BLOCK);
		final var orangeGlass = from(STAINED_GLASS.orange());
		final var candle = property(property(CANDLE, BlockStateProperties.LIT, false), BlockStateProperties.CANDLES, 1);
		final var whiteGlass = from(STAINED_GLASS.white());
		final var glass = from(GLASS);
		final var carverDebug = CarverDebugSettings.of(whiteGlass, candle, orangeGlass, glass);
		final var replaceable = blocks.getOrThrow(StellarityBlockTags.WORLDGEN_CARVER_REPLACEABLE);

		context.register(CAVE, new ConfiguredWorldCarver<>(WorldCarver.CAVE, new CaveCarverConfiguration(
			0.15f, height(aboveBottom(8), belowTop(8)), numf(0.4f, 1.5f), absolute(0), carverDebug, replaceable, numf(1, 2.2f), numf(1, 2.2f), numf(-1, -0.4f)
		)));

		context.register(CRACK, new ConfiguredWorldCarver<>(WorldCarver.CANYON, new CanyonCarverConfiguration(0.066f, height(aboveBottom(16), belowTop(16)), numf(6, 8), absolute(0), carverDebug, replaceable, numf(-0.125f, 0.125f), new CanyonCarverConfiguration.CanyonShapeConfiguration(
			numf(0.5f, 1), numf(0, 1), 6, numf(0.25f, 1), 0, 5
		))));

		context.register(RAVINE, new ConfiguredWorldCarver<>(WorldCarver.CANYON, new CanyonCarverConfiguration(0.02f, height(aboveBottom(16), belowTop(16)), numf(4), absolute(0), carverDebug, replaceable, numf(-0.125f, 0.125f), new CanyonCarverConfiguration.CanyonShapeConfiguration(
			numf(0.75f, 1), trapezoidf(0f, 4f, 2f), 3, numf(0.5f, 0.75f), 1, 0
		))));
	}
}
