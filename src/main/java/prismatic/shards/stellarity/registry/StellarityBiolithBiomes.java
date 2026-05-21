package prismatic.shards.stellarity.registry;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import net.minecraft.core.Direction;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.key.StellarityNoises;

import java.util.List;

import static net.minecraft.world.level.biome.Biomes.*;
import static net.minecraft.world.level.biome.Climate.Parameter.point;
import static net.minecraft.world.level.biome.Climate.Parameter.span;
import static net.minecraft.world.level.block.Blocks.*;
import static net.minecraft.world.level.levelgen.SurfaceRules.*;
import static prismatic.shards.stellarity.key.StellarityBiomes.*;
import static prismatic.shards.stellarity.registry.StellarityBlocks.*;
import static prismatic.shards.stellarity.util.WorldgenUtil.state;
import static net.minecraft.world.level.levelgen.SurfaceRules.state;

public interface StellarityBiolithBiomes {
	static void init() {


		BiomePlacement.addEnd(HALLOWED_TUNDRA, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, 1f),
			span(-1.2f, -0.725f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(THE_HALLOW, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-1f, 1f),
			span(-1.2f, -0.725f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(PRISMATIC_DUNES, new Climate.ParameterPoint(
			span(0.48f, 1f),
			span(-1f, 1f),
			span(-1.2f, -0.725f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(FROZEN_SPIKES, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, 1f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, -0.05f),
			0
		));
		BiomePlacement.addEnd(END_BARRENS, new Climate.ParameterPoint(
			span(-0.405f, -0.15f),
			span(-1f, 0.265f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(THE_NEST, new Climate.ParameterPoint(
			span(-0.405f, 0.2f),
			span(0.265f, 1f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, -0.05f),
			0
		));
		BiomePlacement.addEnd(END_BARRENS, new Climate.ParameterPoint(
			span(-0.15f, 0.2f),
			span(-1f, -0.47000000000000003f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(ENDER_WASTES, new Climate.ParameterPoint(
			span(-0.15f, 0.2f),
			span(-0.47000000000000003f, 0.265f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(END_BARRENS, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(-1f, -0.47000000000000003f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, -0.05f),
			0
		));
		BiomePlacement.addEnd(ENDER_WASTES, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(-0.47000000000000003f, -0.19f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(ENDER_WASTES, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(-0.19f, 0.265f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, -0.05f),
			0
		));
		BiomePlacement.addEnd(CRYSTAL_CRAGS, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(0.265f, 0.685f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(THE_NEST, new Climate.ParameterPoint(
			span(0.2f, 1f),
			span(0.685f, 1f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(FIERY_HILLS, new Climate.ParameterPoint(
			span(0.48f, 1f),
			span(-1f, 0.265f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(THE_NEST, new Climate.ParameterPoint(
			span(0.48f, 1f),
			span(0.265f, 1f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(FROZEN_SHRUBLAND, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, 0.045f),
			span(-0.15f, 1f),
			span(-1f, 0.545f),
			span(0f, 0f),
			span(-1f, -0.05f),
			0
		));
		BiomePlacement.addEnd(FROZEN_SHRUBLAND, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(0.045f, 0.265f),
			span(-0.15f, 1f),
			span(-1f, -0.065f),
			span(0f, 0f),
			span(-1f, -0.26666668f),
			0
		));
		BiomePlacement.addEnd(FROSTED_VALLEY, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(0.265f, 1f),
			span(-0.15f, 1f),
			span(-1f, 0.545f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(END_HIGHLANDS, new Climate.ParameterPoint(
			span(-0.405f, 0.2f),
			span(-1f, 0.265f),
			span(-0.15f, 1f),
			span(-1f, -0.065f),
			span(0f, 0f),
			span(-1f, -0.26666668f),
			0
		));
		BiomePlacement.addEnd(AMETHYST_FOREST, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.265f, 1f),
			span(-0.15f, 1f),
			span(-1f, -0.065f),
			span(0f, 0f),
			span(-1f, -0.26666668f),
			0
		));
		BiomePlacement.addEnd(END_HIGHLANDS, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(-1f, 0.045f),
			span(-0.15f, 1f),
			span(-1f, -0.065f),
			span(0f, 0f),
			span(-1f, -0.26666668f),
			0
		));
		BiomePlacement.addEnd(AMETHYST_FOREST, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(0.045f, 1f),
			span(-0.15f, 1f),
			span(-1f, -0.065f),
			span(0f, 0f),
			span(-1f, -0.26666668f),
			0
		));
		BiomePlacement.addEnd(ENDLESS_DUNES, new Climate.ParameterPoint(
			span(0.48f, 1f),
			span(-1f, 1f),
			span(-0.15f, 1f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(FROSTED_VALLEY, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(0.045f, 1f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(END_WILDS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-1f, -0.19f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(END_WILDS, new Climate.ParameterPoint(
			span(-0.405f, 0.2f),
			span(-0.19f, 0.045f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, -0.05f),
			0
		));
		BiomePlacement.addEnd(END_MIDLANDS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.045f, 0.265f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(THE_NEST, new Climate.ParameterPoint(
			span(-0.405f, -0.15f),
			span(0.265f, 1f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, -0.05f),
			0
		));
		BiomePlacement.addEnd(END_MIDLANDS, new Climate.ParameterPoint(
			span(-0.15f, 0.48f),
			span(0.265f, 0.685f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(THE_NEST, new Climate.ParameterPoint(
			span(-0.15f, 0.2f),
			span(0.685f, 1f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(END_SHRUBLAND, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(-0.19f, 0.045f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(END_MIDLANDS, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(0.685f, 1f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(FROZEN_MARSH, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, 1f),
			span(-0.15f, 1f),
			span(0.545f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(ASHFALL_DELTAS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-1f, -0.19f),
			span(-0.15f, 1f),
			span(0.545f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(WARPED_MARSH, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-0.19f, 0.265f),
			span(-0.15f, 1f),
			span(0.545f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(WARPED_MARSH, new Climate.ParameterPoint(
			span(-0.405f, -0.15f),
			span(0.265f, 0.685f),
			span(-0.15f, 1f),
			span(0.545f, 1f),
			span(0f, 0f),
			span(-1f, -0.05f),
			0
		));
		BiomePlacement.addEnd(PRISMARINE_FOREST, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.685f, 1f),
			span(-0.15f, 1f),
			span(0.545f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(PRISMARINE_FOREST, new Climate.ParameterPoint(
			span(-0.15f, 0.48f),
			span(0.265f, 1f),
			span(-0.15f, 1f),
			span(0.545f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		));
		BiomePlacement.addEnd(FROZEN_SHRUBLAND, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(0.045f, 0.265f),
			span(-0.15f, 1f),
			span(-1f, -0.375f),
			span(0f, 0f),
			span(-0.26666668f, -0.05f),
			0
		));
		BiomePlacement.addEnd(END_HIGHLANDS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-1f, 0.045f),
			span(-0.15f, 1f),
			span(-1f, -0.375f),
			span(0f, 0f),
			span(-0.26666668f, 1f),
			0
		));
		BiomePlacement.addEnd(END_HIGHLANDS, new Climate.ParameterPoint(
			span(-0.405f, 0.2f),
			span(0.045f, 0.265f),
			span(-0.15f, 1f),
			span(-1f, -0.375f),
			span(0f, 0f),
			span(-0.26666668f, -0.05f),
			0
		));
		BiomePlacement.addEnd(AMETHYST_FOREST, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.265f, 1f),
			span(-0.15f, 1f),
			span(-1f, -0.375f),
			span(0f, 0f),
			span(-0.26666668f, 1f),
			0
		));
		BiomePlacement.addEnd(AMETHYST_FOREST, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(0.045f, 1f),
			span(-0.15f, 1f),
			span(-1f, -0.375f),
			span(0f, 0f),
			span(-0.26666668f, 1f),
			0
		));
		BiomePlacement.addEnd(FROSTED_VALLEY, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(0.045f, 1f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, 1f),
			0
		));
		BiomePlacement.addEnd(END_WILDS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-1f, -0.19f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, 0.05f),
			0
		));
		BiomePlacement.addEnd(END_WILDS, new Climate.ParameterPoint(
			span(-0.405f, 0.2f),
			span(-0.19f, 0.045f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, -0.05f),
			0
		));
		BiomePlacement.addEnd(END_MIDLANDS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.045f, 0.265f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, 0.05f),
			0
		));
		BiomePlacement.addEnd(THE_NEST, new Climate.ParameterPoint(
			span(-0.405f, -0.15f),
			span(0.265f, 1f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, -0.05f),
			0
		));
		BiomePlacement.addEnd(END_MIDLANDS, new Climate.ParameterPoint(
			span(-0.15f, 0.48f),
			span(0.265f, 0.685f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, 0.05f),
			0
		));
		BiomePlacement.addEnd(THE_NEST, new Climate.ParameterPoint(
			span(-0.15f, 0.2f),
			span(0.685f, 1f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, 0.05f),
			0
		));
		BiomePlacement.addEnd(END_SHRUBLAND, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(-0.19f, 0.045f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, 0.05f),
			0
		));
		BiomePlacement.addEnd(END_MIDLANDS, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(0.685f, 1f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, 0.05f),
			0
		));
		BiomePlacement.addEnd(FLESH_TUNDRA, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, -0.19f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(FROZEN_SPIKES, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-0.19f, 1f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(END_BARRENS, new Climate.ParameterPoint(
			span(-0.405f, -0.15f),
			span(0.265f, 0.685f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(THE_NEST, new Climate.ParameterPoint(
			span(-0.405f, -0.15f),
			span(0.685f, 1f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(CRYSTAL_CRAGS, new Climate.ParameterPoint(
			span(-0.15f, 0.2f),
			span(0.265f, 1f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(ENDER_WASTES, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(-1f, -0.19f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(CRYSTAL_CRAGS, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(-0.19f, 0.685f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(FROZEN_SHRUBLAND, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, 0.045f),
			span(-0.15f, 1f),
			span(-1f, -0.375f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(FROSTED_VALLEY, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(0.045f, 1f),
			span(-0.15f, 1f),
			span(-1f, 0.545f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(AMETHYST_FOREST, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.045f, 1f),
			span(-0.15f, 1f),
			span(-1f, -0.375f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(FLESH_TUNDRA, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, -0.19f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.05f, 0.05f),
			0
		));
		BiomePlacement.addEnd(FROSTED_VALLEY, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-0.19f, 1f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.05f, 0.05f),
			0
		));
		BiomePlacement.addEnd(END_SHRUBLAND, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-0.19f, 0.045f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.05f, 0.05f),
			0
		));
		BiomePlacement.addEnd(END_MIDLANDS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.265f, 0.685f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.05f, 0.05f),
			0
		));
		BiomePlacement.addEnd(THE_NEST, new Climate.ParameterPoint(
			span(-0.405f, 0.2f),
			span(0.685f, 1f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.05f, 0.05f),
			0
		));
		BiomePlacement.addEnd(PRISMARINE_FOREST, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.265f, 1f),
			span(-0.15f, 1f),
			span(0.545f, 1f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(FROZEN_SHRUBLAND, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, 0.045f),
			span(-0.15f, 1f),
			span(-0.375f, -0.065f),
			span(0f, 0f),
			span(0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(END_HIGHLANDS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-1f, 0.045f),
			span(-0.15f, 1f),
			span(-0.375f, -0.065f),
			span(0f, 0f),
			span(0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(AMETHYST_FOREST, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.045f, 1f),
			span(-0.15f, 1f),
			span(-0.375f, -0.065f),
			span(0f, 0f),
			span(0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(FLESH_TUNDRA, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, -0.19f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(FROSTED_VALLEY, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-0.19f, 1f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(END_SHRUBLAND, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-0.19f, 0.045f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(END_MIDLANDS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.265f, 0.685f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(THE_NEST, new Climate.ParameterPoint(
			span(-0.405f, 0.2f),
			span(0.685f, 1f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(0.05f, 1f),
			0
		));
		BiomePlacement.addEnd(THE_END, new Climate.ParameterPoint(
			point(0),
			point(0),
			point(-2),
			point(-2),
			span(-2, 2),
			point(0),
			0
		));

		BiomePlacement.removeEnd(SMALL_END_ISLANDS);

		final var wildsDirtSequence = sequence(
			ifTrue(stoneDepthCheck(1, false, 0, CaveSurface.FLOOR),
				ifTrue(noiseCondition(StellarityNoises.SURFACE, -1, 0.197555555),
					sequence(
						ifTrue(stoneDepthCheck(0, false, 0, CaveSurface.FLOOR), state(ENDER_GRASS_BLOCK)),
						state(ENDER_DIRT)
					)
				)
			)
		);

		final var forestDirtSequence = new RuleSource[]{ifTrue(stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
			state(ENDER_GRASS_BLOCK)
		),
			state(ENDER_DIRT)
		};

		final var yAxisBasalt = BASALT.defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Y);


		SurfaceGeneration.addEndSurfaceRules(Stellarity.id("rules/end"), sequence(
			ifTrue(isBiome(END_WILDS, END_SHRUBLAND), wildsDirtSequence),
			ifTrue(isBiome(FROZEN_SHRUBLAND),
				ifTrue(stoneDepthCheck(1, false, 0, CaveSurface.FLOOR),
					ifTrue(noiseCondition(StellarityNoises.SURFACE, -1, 0.197555555), sequence(
						state(SNOW_BLOCK)
					))
				)
			),
			ifTrue(isBiome(AMETHYST_FOREST),
				ifTrue(stoneDepthCheck(1, false, 6, CaveSurface.FLOOR),
					ifTrue(noiseCondition(StellarityNoises.SURFACE, 0.3, 0.37),
						state(AMETHYST_BLOCK)
					)
				)
			),
			ifTrue(isBiome(FIERY_HILLS), sequence(
				ifTrue(noiseCondition(StellarityNoises.SURFACE_4X, 0.1, 0.125),
					ifTrue(stoneDepthCheck(4, true, 1, CaveSurface.FLOOR),
						state(NETHER_WART_BLOCK)
					)
				),
				ifTrue(noiseCondition(StellarityNoises.SURFACE_2X, -0.25, -0.175),
					ifTrue(stoneDepthCheck(4, true, 1, CaveSurface.FLOOR),
						state(NETHER_WART_BLOCK)
					)
				),
				ifTrue(noiseCondition(StellarityNoises.SURFACE, 0.2, 0.4),
					ifTrue(stoneDepthCheck(4, true, 2, CaveSurface.FLOOR), sequence(
						ifTrue(noiseCondition(StellarityNoises.SURFACE, 0.25, 0.35),
							state(yAxisBasalt)
						),
						state(SMOOTH_BASALT)
					))
				),
				ifTrue(noiseCondition(StellarityNoises.SURFACE, -0.15, 0.25),
					ifTrue(stoneDepthCheck(0, true, 2, CaveSurface.FLOOR),
						state(BLACKSTONE)
					)
				)
			)),
			ifTrue(isBiome(WARPED_MARSH),
				ifTrue(stoneDepthCheck(2, false, 6, CaveSurface.FLOOR), sequence(
					ifTrue(noiseCondition(StellarityNoises.SURFACE, -0.037, 0.025),
						state(WARPED_WART_BLOCK)
					),
					state(MOSS_BLOCK)
				))
			),
			ifTrue(isBiome(ASHFALL_DELTAS),
				ifTrue(stoneDepthCheck(2, true, 6, CaveSurface.FLOOR),
					state(BLACKSTONE)
				)
			),
			ifTrue(isBiome(AMETHYST_FOREST, PRISMARINE_FOREST, THE_HALLOW, HALLOWED_TUNDRA),
				ifTrue(stoneDepthCheck(0, true, 6, CaveSurface.FLOOR), sequence(
					ifTrue(isBiome(HALLOWED_TUNDRA),
						state(SNOW_BLOCK)
					),
					forestDirtSequence[0],
					forestDirtSequence[1]
				))
			),
			ifTrue(isBiome(FLESH_TUNDRA),
				ifTrue(stoneDepthCheck(2, false, 6, CaveSurface.FLOOR), sequence(
					ifTrue(noiseCondition(StellarityNoises.SURFACE, -0.03, 0.02),
						state(yAxisBasalt)
					),
					ifTrue(noiseCondition(StellarityNoises.SURFACE, -0.05, 0.04),
						state(SMOOTH_BASALT)
					)
				))
			),
			ifTrue(isBiome(FROZEN_SPIKES, FROZEN_MARSH, FROSTED_VALLEY),
				ifTrue(stoneDepthCheck(0, true, 4, CaveSurface.FLOOR), sequence(
					ifTrue(isBiome(FROZEN_MARSH),
						ifTrue(stoneDepthCheck(2, false, 6, CaveSurface.FLOOR),
							ifTrue(noiseCondition(StellarityNoises.SURFACE, -0.037, 0.025),
								state(WARPED_WART_BLOCK)
							)
						)
					),
					ifTrue(stoneDepthCheck(2, false, 0, CaveSurface.FLOOR), sequence(
						ifTrue(noiseCondition(StellarityNoises.SURFACE_2X, -0.4, -0.3),
							state(ICE)
						),
						ifTrue(noiseCondition(StellarityNoises.SURFACE_2X, 0.8, 2),
							state(END_STONE)
						)
					)),
					state(SNOW_BLOCK)
				))
			),
			ifTrue(isBiome(ENDLESS_DUNES),
				ifTrue(stoneDepthCheck(0, true, 6, CaveSurface.FLOOR), sequence(
					ifTrue(noiseCondition(StellarityNoises.SURFACE_4X, -0.31, -0.3),
						ifTrue(stoneDepthCheck(2, true, 0, CaveSurface.FLOOR),
							state(COARSE_ENDER_DIRT)
						)
					),
					ifTrue(noiseCondition(StellarityNoises.SURFACE_2X, -0.5, 2), sequence(
						ifTrue(stoneDepthCheck(0, false, 0, CaveSurface.CEILING),
							state(SMOOTH_SANDSTONE)
						),
						ifTrue(stoneDepthCheck(0, true, 4, CaveSurface.FLOOR),
							state(SAND)
						)
					))
				))
			),
			ifTrue(isBiome(PRISMATIC_DUNES),
				ifTrue(stoneDepthCheck(0, true, 6, CaveSurface.FLOOR), sequence(
					ifTrue(noiseCondition(StellarityNoises.SURFACE_4X, -0.305, -0.3),
						ifTrue(stoneDepthCheck(2, true, 0, CaveSurface.FLOOR),
							state(AMETHYST_BLOCK)
						)
					),
					ifTrue(noiseCondition(StellarityNoises.SURFACE_2X, -0.5, 2), sequence(
						ifTrue(stoneDepthCheck(0, false, 0, CaveSurface.CEILING),
							state(CALCITE)
						),
						ifTrue(stoneDepthCheck(0, true, 4, CaveSurface.FLOOR),
							state(WHITE_CONCRETE_POWDER)
						)
					))
				))
			),
			ifTrue(isBiome(CRYSTAL_CRAGS),
				ifTrue(stoneDepthCheck(1, true, 0, CaveSurface.FLOOR), sequence(
					ifTrue(noiseCondition(StellarityNoises.SURFACE_2X, -2, -0.05),
						ifTrue(noiseCondition(StellarityNoises.SURFACE, -0.037, 0.015),
							state(AMETHYST_BLOCK)
						)
					),
					ifTrue(noiseCondition(StellarityNoises.SURFACE, -0.05, 0.5),
						state(BLACKSTONE)
					),
					ifTrue(noiseCondition(StellarityNoises.SURFACE_2X, 0.05, 0.1),
						state(AMETHYST_BLOCK)
					)
				))
			),
			ifTrue(isBiome(THE_HALLOW, PRISMATIC_DUNES, HALLOWED_TUNDRA),
				state(DIORITE)
			),
			ifTrue(isBiome(AMETHYST_FOREST, PRISMARINE_FOREST),
				state(CALCITE)
			)
		));

		SurfaceGeneration.addEndSurfaceRules(Stellarity.mcId("rules/stellarity_end"), sequence(
			ifTrue(isBiome(END_MIDLANDS), wildsDirtSequence),
			ifTrue(isBiome(THE_END),
				ifTrue(stoneDepthCheck(2, false, 6, CaveSurface.FLOOR), sequence(
					ifTrue(noiseCondition(StellarityNoises.SURFACE, -0.03, 0.02),
						state(yAxisBasalt)
					),
					ifTrue(noiseCondition(StellarityNoises.SURFACE, -0.05, 0.04),
						state(SMOOTH_BASALT)
					)
				))
			),
			ifTrue(isBiome(END_HIGHLANDS),
				ifTrue(stoneDepthCheck(0, true, 6, CaveSurface.FLOOR), sequence(
					ifTrue(noiseCondition(StellarityNoises.SURFACE, 0.2, 1),
						state(END_STONE)
					),
					ifTrue(noiseCondition(StellarityNoises.SURFACE_4X, 0, 0.05),
						state(COARSE_ENDER_DIRT)
					),
					forestDirtSequence[0],
					forestDirtSequence[1]
				))
			)
		));
	}
}
