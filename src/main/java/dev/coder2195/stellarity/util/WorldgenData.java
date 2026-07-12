package dev.coder2195.stellarity.util;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import org.jetbrains.annotations.Nullable;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.key.StellarityNoises;
import dev.coder2195.stellarity.registry.material_condition.LegacyBiomeConditionSource;
import dev.coder2195.stellarity.util.tuple.Tuple2;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.world.level.biome.Biomes.*;
import static net.minecraft.world.level.biome.Climate.Parameter.point;
import static net.minecraft.world.level.biome.Climate.Parameter.span;
import static net.minecraft.world.level.block.Blocks.*;
import static net.minecraft.world.level.levelgen.SurfaceRules.*;
import static dev.coder2195.stellarity.key.StellarityBiomes.*;
import static dev.coder2195.stellarity.registry.StellarityBlocks.*;
import static dev.coder2195.stellarity.util.ValueUtil.from;
import static dev.coder2195.stellarity.util.WorldgenUtil.state;

public interface WorldgenData {
	static ResourceKey<Biome> nullscapeBiome(String id) {
		return ResourceKey.create(Registries.BIOME, Stellarity.id("nullscape", id));
	}

	List<Tuple2<ResourceKey<Biome>, Climate.ParameterPoint>> PARAMETER_POINTS = List.<Tuple2<ResourceKey<Biome>, Climate.ParameterPoint>>of(new Tuple2<>(HALLOWED_TUNDRA, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, 1f),
			span(-1.2f, -0.725f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(THE_HALLOW, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-1f, 1f),
			span(-1.2f, -0.725f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(PRISMATIC_DUNES, new Climate.ParameterPoint(
			span(0.48f, 1f),
			span(-1f, 1f),
			span(-1.2f, -0.725f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(FROZEN_SPIKES, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, 1f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, -0.05f),
			0
		)),
		new Tuple2<>(END_BARRENS, new Climate.ParameterPoint(
			span(-0.405f, -0.15f),
			span(-1f, 0.265f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(THE_NEST, new Climate.ParameterPoint(
			span(-0.405f, 0.2f),
			span(0.265f, 1f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, -0.05f),
			0
		)),
		new Tuple2<>(END_BARRENS, new Climate.ParameterPoint(
			span(-0.15f, 0.2f),
			span(-1f, -0.47000000000000003f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(ENDER_WASTES, new Climate.ParameterPoint(
			span(-0.15f, 0.2f),
			span(-0.47000000000000003f, 0.265f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(END_BARRENS, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(-1f, -0.47000000000000003f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, -0.05f),
			0
		)),
		new Tuple2<>(ENDER_WASTES, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(-0.47000000000000003f, -0.19f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(ENDER_WASTES, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(-0.19f, 0.265f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, -0.05f),
			0
		)),
		new Tuple2<>(CRYSTAL_CRAGS, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(0.265f, 0.685f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(THE_NEST, new Climate.ParameterPoint(
			span(0.2f, 1f),
			span(0.685f, 1f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(FIERY_HILLS, new Climate.ParameterPoint(
			span(0.48f, 1f),
			span(-1f, 0.265f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(THE_NEST, new Climate.ParameterPoint(
			span(0.48f, 1f),
			span(0.265f, 1f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(FROZEN_SHRUBLAND, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, 0.045f),
			span(-0.15f, 1f),
			span(-1f, 0.545f),
			span(0f, 0f),
			span(-1f, -0.05f),
			0
		)),
		new Tuple2<>(FROZEN_SHRUBLAND, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(0.045f, 0.265f),
			span(-0.15f, 1f),
			span(-1f, -0.065f),
			span(0f, 0f),
			span(-1f, -0.26666668f),
			0
		)),
		new Tuple2<>(FROSTED_VALLEY, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(0.265f, 1f),
			span(-0.15f, 1f),
			span(-1f, 0.545f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(END_HIGHLANDS, new Climate.ParameterPoint(
			span(-0.405f, 0.2f),
			span(-1f, 0.265f),
			span(-0.15f, 1f),
			span(-1f, -0.065f),
			span(0f, 0f),
			span(-1f, -0.26666668f),
			0
		)),
		new Tuple2<>(AMETHYST_FOREST, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.265f, 1f),
			span(-0.15f, 1f),
			span(-1f, -0.065f),
			span(0f, 0f),
			span(-1f, -0.26666668f),
			0
		)),
		new Tuple2<>(END_HIGHLANDS, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(-1f, 0.045f),
			span(-0.15f, 1f),
			span(-1f, -0.065f),
			span(0f, 0f),
			span(-1f, -0.26666668f),
			0
		)),
		new Tuple2<>(AMETHYST_FOREST, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(0.045f, 1f),
			span(-0.15f, 1f),
			span(-1f, -0.065f),
			span(0f, 0f),
			span(-1f, -0.26666668f),
			0
		)),
		new Tuple2<>(ENDLESS_DUNES, new Climate.ParameterPoint(
			span(0.48f, 1f),
			span(-1f, 1f),
			span(-0.15f, 1f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(FROSTED_VALLEY, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(0.045f, 1f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(END_WILDS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-1f, -0.19f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(END_WILDS, new Climate.ParameterPoint(
			span(-0.405f, 0.2f),
			span(-0.19f, 0.045f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, -0.05f),
			0
		)),
		new Tuple2<>(END_MIDLANDS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.045f, 0.265f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(THE_NEST, new Climate.ParameterPoint(
			span(-0.405f, -0.15f),
			span(0.265f, 1f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, -0.05f),
			0
		)),
		new Tuple2<>(END_MIDLANDS, new Climate.ParameterPoint(
			span(-0.15f, 0.48f),
			span(0.265f, 0.685f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(THE_NEST, new Climate.ParameterPoint(
			span(-0.15f, 0.2f),
			span(0.685f, 1f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(END_SHRUBLAND, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(-0.19f, 0.045f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(END_MIDLANDS, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(0.685f, 1f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(FROZEN_MARSH, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, 1f),
			span(-0.15f, 1f),
			span(0.545f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(ASHFALL_DELTAS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-1f, -0.19f),
			span(-0.15f, 1f),
			span(0.545f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(WARPED_MARSH, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-0.19f, 0.265f),
			span(-0.15f, 1f),
			span(0.545f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(WARPED_MARSH, new Climate.ParameterPoint(
			span(-0.405f, -0.15f),
			span(0.265f, 0.685f),
			span(-0.15f, 1f),
			span(0.545f, 1f),
			span(0f, 0f),
			span(-1f, -0.05f),
			0
		)),
		new Tuple2<>(PRISMARINE_FOREST, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.685f, 1f),
			span(-0.15f, 1f),
			span(0.545f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(PRISMARINE_FOREST, new Climate.ParameterPoint(
			span(-0.15f, 0.48f),
			span(0.265f, 1f),
			span(-0.15f, 1f),
			span(0.545f, 1f),
			span(0f, 0f),
			span(-1f, 1f),
			0
		)),
		new Tuple2<>(FROZEN_SHRUBLAND, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(0.045f, 0.265f),
			span(-0.15f, 1f),
			span(-1f, -0.375f),
			span(0f, 0f),
			span(-0.26666668f, -0.05f),
			0
		)),
		new Tuple2<>(END_HIGHLANDS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-1f, 0.045f),
			span(-0.15f, 1f),
			span(-1f, -0.375f),
			span(0f, 0f),
			span(-0.26666668f, 1f),
			0
		)),
		new Tuple2<>(END_HIGHLANDS, new Climate.ParameterPoint(
			span(-0.405f, 0.2f),
			span(0.045f, 0.265f),
			span(-0.15f, 1f),
			span(-1f, -0.375f),
			span(0f, 0f),
			span(-0.26666668f, -0.05f),
			0
		)),
		new Tuple2<>(AMETHYST_FOREST, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.265f, 1f),
			span(-0.15f, 1f),
			span(-1f, -0.375f),
			span(0f, 0f),
			span(-0.26666668f, 1f),
			0
		)),
		new Tuple2<>(AMETHYST_FOREST, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(0.045f, 1f),
			span(-0.15f, 1f),
			span(-1f, -0.375f),
			span(0f, 0f),
			span(-0.26666668f, 1f),
			0
		)),
		new Tuple2<>(FROSTED_VALLEY, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(0.045f, 1f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, 1f),
			0
		)),
		new Tuple2<>(END_WILDS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-1f, -0.19f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, 0.05f),
			0
		)),
		new Tuple2<>(END_WILDS, new Climate.ParameterPoint(
			span(-0.405f, 0.2f),
			span(-0.19f, 0.045f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, -0.05f),
			0
		)),
		new Tuple2<>(END_MIDLANDS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.045f, 0.265f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, 0.05f),
			0
		)),
		new Tuple2<>(THE_NEST, new Climate.ParameterPoint(
			span(-0.405f, -0.15f),
			span(0.265f, 1f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, -0.05f),
			0
		)),
		new Tuple2<>(END_MIDLANDS, new Climate.ParameterPoint(
			span(-0.15f, 0.48f),
			span(0.265f, 0.685f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, 0.05f),
			0
		)),
		new Tuple2<>(THE_NEST, new Climate.ParameterPoint(
			span(-0.15f, 0.2f),
			span(0.685f, 1f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, 0.05f),
			0
		)),
		new Tuple2<>(END_SHRUBLAND, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(-0.19f, 0.045f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, 0.05f),
			0
		)),
		new Tuple2<>(END_MIDLANDS, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(0.685f, 1f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.26666668f, 0.05f),
			0
		)),
		new Tuple2<>(FLESH_TUNDRA, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, -0.19f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		)),
		new Tuple2<>(FROZEN_SPIKES, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-0.19f, 1f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		)),
		new Tuple2<>(END_BARRENS, new Climate.ParameterPoint(
			span(-0.405f, -0.15f),
			span(0.265f, 0.685f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		)),
		new Tuple2<>(THE_NEST, new Climate.ParameterPoint(
			span(-0.405f, -0.15f),
			span(0.685f, 1f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		)),
		new Tuple2<>(CRYSTAL_CRAGS, new Climate.ParameterPoint(
			span(-0.15f, 0.2f),
			span(0.265f, 1f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		)),
		new Tuple2<>(ENDER_WASTES, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(-1f, -0.19f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		)),
		new Tuple2<>(CRYSTAL_CRAGS, new Climate.ParameterPoint(
			span(0.2f, 0.48f),
			span(-0.19f, 0.685f),
			span(-0.725f, -0.15f),
			span(-1f, 1f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		)),
		new Tuple2<>(FROZEN_SHRUBLAND, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, 0.045f),
			span(-0.15f, 1f),
			span(-1f, -0.375f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		)),
		new Tuple2<>(FROSTED_VALLEY, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(0.045f, 1f),
			span(-0.15f, 1f),
			span(-1f, 0.545f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		)),
		new Tuple2<>(AMETHYST_FOREST, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.045f, 1f),
			span(-0.15f, 1f),
			span(-1f, -0.375f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		)),
		new Tuple2<>(FLESH_TUNDRA, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, -0.19f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.05f, 0.05f),
			0
		)),
		new Tuple2<>(FROSTED_VALLEY, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-0.19f, 1f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.05f, 0.05f),
			0
		)),
		new Tuple2<>(END_SHRUBLAND, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-0.19f, 0.045f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.05f, 0.05f),
			0
		)),
		new Tuple2<>(END_MIDLANDS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.265f, 0.685f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.05f, 0.05f),
			0
		)),
		new Tuple2<>(THE_NEST, new Climate.ParameterPoint(
			span(-0.405f, 0.2f),
			span(0.685f, 1f),
			span(-0.15f, 1f),
			span(-0.375f, 0.545f),
			span(0f, 0f),
			span(-0.05f, 0.05f),
			0
		)),
		new Tuple2<>(PRISMARINE_FOREST, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.265f, 1f),
			span(-0.15f, 1f),
			span(0.545f, 1f),
			span(0f, 0f),
			span(-0.05f, 1f),
			0
		)),
		new Tuple2<>(FROZEN_SHRUBLAND, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, 0.045f),
			span(-0.15f, 1f),
			span(-0.375f, -0.065f),
			span(0f, 0f),
			span(0.05f, 1f),
			0
		)),
		new Tuple2<>(END_HIGHLANDS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-1f, 0.045f),
			span(-0.15f, 1f),
			span(-0.375f, -0.065f),
			span(0f, 0f),
			span(0.05f, 1f),
			0
		)),
		new Tuple2<>(AMETHYST_FOREST, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.045f, 1f),
			span(-0.15f, 1f),
			span(-0.375f, -0.065f),
			span(0f, 0f),
			span(0.05f, 1f),
			0
		)),
		new Tuple2<>(FLESH_TUNDRA, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-1f, -0.19f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(0.05f, 1f),
			0
		)),
		new Tuple2<>(FROSTED_VALLEY, new Climate.ParameterPoint(
			span(-1f, -0.405f),
			span(-0.19f, 1f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(0.05f, 1f),
			0
		)),
		new Tuple2<>(END_SHRUBLAND, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(-0.19f, 0.045f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(0.05f, 1f),
			0
		)),
		new Tuple2<>(END_MIDLANDS, new Climate.ParameterPoint(
			span(-0.405f, 0.48f),
			span(0.265f, 0.685f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(0.05f, 1f),
			0
		)),
		new Tuple2<>(THE_NEST, new Climate.ParameterPoint(
			span(-0.405f, 0.2f),
			span(0.685f, 1f),
			span(-0.15f, 1f),
			span(-0.065f, 0.545f),
			span(0f, 0f),
			span(0.05f, 1f),
			0
		)),
		new Tuple2<>(THE_END, new Climate.ParameterPoint(
			point(0),
			point(0),
			point(-2),
			point(-2),
			span(-2, 2),
			point(0),
			0
		))
	);

	RuleSource WILDS_DIRT_SEQUENCE = sequence(
		ifTrue(stoneDepthCheck(1, false, 0, CaveSurface.FLOOR),
			ifTrue(noiseCondition2d(StellarityNoises.SURFACE, -1, 0.197555555),
				sequence(
					ifTrue(stoneDepthCheck(0, false, 0, CaveSurface.FLOOR), state(ENDER_GRASS_BLOCK)),
					state(ENDER_DIRT)
				)
			)
		)
	);

	RuleSource[] FOREST_DIRT_SEQUENCE = new RuleSource[]{ifTrue(stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
		state(ENDER_GRASS_BLOCK)
	),
		state(ENDER_DIRT)
	};

	@SafeVarargs
	static SurfaceRules.ConditionSource biomeTest(@Nullable HolderGetter<Biome> biomes, ResourceKey<Biome>... target) {
		if (biomes == null) return LegacyBiomeConditionSource.isBiome(target);
		return isBiome(biomes, target);
	}

	static RuleSource stellaritySurfaceRules(@Nullable HolderGetter<Biome> biomes) {
		// for when biolith doesn't have support
		return sequence(
			ifTrue(biomeTest(biomes, END_WILDS, END_SHRUBLAND), WILDS_DIRT_SEQUENCE),
			ifTrue(biomeTest(biomes, FROZEN_SHRUBLAND),
				ifTrue(stoneDepthCheck(1, false, 0, CaveSurface.FLOOR),
					ifTrue(noiseCondition2d(StellarityNoises.SURFACE, -1, 0.197555555), sequence(
						state(SNOW_BLOCK)
					))
				)
			),
			ifTrue(biomeTest(biomes, AMETHYST_FOREST),
				ifTrue(stoneDepthCheck(1, false, 6, CaveSurface.FLOOR),
					ifTrue(noiseCondition2d(StellarityNoises.SURFACE, 0.3, 0.37),
						state(AMETHYST_BLOCK)
					)
				)
			),
			ifTrue(biomeTest(biomes, FIERY_HILLS), sequence(
				ifTrue(noiseCondition2d(StellarityNoises.SURFACE_4X, 0.1, 0.125),
					ifTrue(stoneDepthCheck(4, true, 1, CaveSurface.FLOOR),
						state(NETHER_WART_BLOCK)
					)
				),
				ifTrue(noiseCondition2d(StellarityNoises.SURFACE_2X, -0.25, -0.175),
					ifTrue(stoneDepthCheck(4, true, 1, CaveSurface.FLOOR),
						state(NETHER_WART_BLOCK)
					)
				),
				ifTrue(noiseCondition2d(StellarityNoises.SURFACE, 0.2, 0.4),
					ifTrue(stoneDepthCheck(4, true, 2, CaveSurface.FLOOR), sequence(
						ifTrue(noiseCondition2d(StellarityNoises.SURFACE, 0.25, 0.35),
							SurfaceRules.state(from(BASALT))
						),
						state(SMOOTH_BASALT)
					))
				),
				ifTrue(noiseCondition2d(StellarityNoises.SURFACE, -0.15, 0.25),
					ifTrue(stoneDepthCheck(0, true, 2, CaveSurface.FLOOR),
						state(BLACKSTONE)
					)
				)
			)),
			ifTrue(biomeTest(biomes, WARPED_MARSH),
				ifTrue(stoneDepthCheck(2, false, 6, CaveSurface.FLOOR), sequence(
					ifTrue(noiseCondition2d(StellarityNoises.SURFACE, -0.037, 0.025),
						state(WARPED_WART_BLOCK)
					),
					state(MOSS_BLOCK)
				))
			),
			ifTrue(biomeTest(biomes, ASHFALL_DELTAS),
				ifTrue(stoneDepthCheck(2, true, 6, CaveSurface.FLOOR),
					state(BLACKSTONE)
				)
			),
			ifTrue(biomeTest(biomes, AMETHYST_FOREST, PRISMARINE_FOREST, THE_HALLOW, HALLOWED_TUNDRA),
				ifTrue(stoneDepthCheck(0, true, 6, CaveSurface.FLOOR), sequence(
					ifTrue(biomeTest(biomes, HALLOWED_TUNDRA),
						state(SNOW_BLOCK)
					),
					FOREST_DIRT_SEQUENCE[0],
					FOREST_DIRT_SEQUENCE[1]
				))
			),
			ifTrue(biomeTest(biomes, FLESH_TUNDRA),
				ifTrue(stoneDepthCheck(2, false, 6, CaveSurface.FLOOR), sequence(
					ifTrue(noiseCondition2d(StellarityNoises.SURFACE, -0.03, 0.02),
						SurfaceRules.state(from(BASALT))
					),
					ifTrue(noiseCondition2d(StellarityNoises.SURFACE, -0.05, 0.04),
						state(SMOOTH_BASALT)
					)
				))
			),
			ifTrue(biomeTest(biomes, FROZEN_SPIKES, FROZEN_MARSH, FROSTED_VALLEY),
				ifTrue(stoneDepthCheck(0, true, 4, CaveSurface.FLOOR), sequence(
					ifTrue(biomeTest(biomes, FROZEN_MARSH),
						ifTrue(stoneDepthCheck(2, false, 6, CaveSurface.FLOOR),
							ifTrue(noiseCondition2d(StellarityNoises.SURFACE, -0.037, 0.025),
								state(WARPED_WART_BLOCK)
							)
						)
					),
					ifTrue(stoneDepthCheck(2, false, 0, CaveSurface.FLOOR), sequence(
						ifTrue(noiseCondition2d(StellarityNoises.SURFACE_2X, -0.4, -0.3),
							state(ICE)
						),
						ifTrue(noiseCondition2d(StellarityNoises.SURFACE_2X, 0.8, 2),
							state(END_STONE)
						)
					)),
					state(SNOW_BLOCK)
				))
			),
			ifTrue(biomeTest(biomes, ENDLESS_DUNES),
				ifTrue(stoneDepthCheck(0, true, 6, CaveSurface.FLOOR), sequence(
					ifTrue(noiseCondition2d(StellarityNoises.SURFACE_4X, -0.31, -0.3),
						ifTrue(stoneDepthCheck(2, true, 0, CaveSurface.FLOOR),
							state(COARSE_ENDER_DIRT)
						)
					),
					ifTrue(noiseCondition2d(StellarityNoises.SURFACE_2X, -0.5, 2), sequence(
						ifTrue(stoneDepthCheck(0, false, 0, CaveSurface.CEILING),
							state(SMOOTH_SANDSTONE)
						),
						ifTrue(stoneDepthCheck(0, true, 4, CaveSurface.FLOOR),
							state(SAND)
						)
					))
				))
			),
			ifTrue(biomeTest(biomes, PRISMATIC_DUNES),
				ifTrue(stoneDepthCheck(0, true, 6, CaveSurface.FLOOR), sequence(
					ifTrue(noiseCondition2d(StellarityNoises.SURFACE_4X, -0.305, -0.3),
						ifTrue(stoneDepthCheck(2, true, 0, CaveSurface.FLOOR),
							state(AMETHYST_BLOCK)
						)
					),
					ifTrue(noiseCondition2d(StellarityNoises.SURFACE_2X, -0.5, 2), sequence(
						ifTrue(stoneDepthCheck(0, false, 0, CaveSurface.CEILING),
							state(CALCITE)
						),
						ifTrue(stoneDepthCheck(0, true, 4, CaveSurface.FLOOR),
							state(CONCRETE_POWDER.white())
						)
					))
				))
			),
			ifTrue(biomeTest(biomes, CRYSTAL_CRAGS),
				ifTrue(stoneDepthCheck(1, true, 0, CaveSurface.FLOOR), sequence(
					ifTrue(noiseCondition2d(StellarityNoises.SURFACE_2X, -2, -0.05),
						ifTrue(noiseCondition2d(StellarityNoises.SURFACE, -0.037, 0.015),
							state(AMETHYST_BLOCK)
						)
					),
					ifTrue(noiseCondition2d(StellarityNoises.SURFACE, -0.05, 0.5),
						state(BLACKSTONE)
					),
					ifTrue(noiseCondition2d(StellarityNoises.SURFACE_2X, 0.05, 0.1),
						state(AMETHYST_BLOCK)
					)
				))
			),
			ifTrue(biomeTest(biomes, THE_HALLOW, PRISMATIC_DUNES, HALLOWED_TUNDRA),
				state(DIORITE)
			),
			ifTrue(biomeTest(biomes, AMETHYST_FOREST, PRISMARINE_FOREST),
				state(CALCITE)
			)
		);
	}


	static RuleSource vanillaSurfaceRules(HolderGetter<Biome> biomes) {

		return sequence(
			ifTrue(biomeTest(biomes, END_MIDLANDS), WorldgenData.WILDS_DIRT_SEQUENCE),
			ifTrue(biomeTest(biomes, THE_END),
				ifTrue(stoneDepthCheck(2, false, 6, CaveSurface.FLOOR), sequence(
					ifTrue(noiseCondition2d(StellarityNoises.SURFACE, -0.03, 0.02),
						SurfaceRules.state(from(BASALT))
					),
					ifTrue(noiseCondition2d(StellarityNoises.SURFACE, -0.05, 0.04),
						state(SMOOTH_BASALT)
					)
				))
			),
			ifTrue(biomeTest(biomes, END_HIGHLANDS),
				ifTrue(stoneDepthCheck(0, true, 6, CaveSurface.FLOOR), sequence(
					ifTrue(noiseCondition2d(StellarityNoises.SURFACE, 0.2, 1),
						state(END_STONE)
					),
					ifTrue(noiseCondition2d(StellarityNoises.SURFACE_4X, 0, 0.05),
						state(COARSE_ENDER_DIRT)
					),
					FOREST_DIRT_SEQUENCE[0],
					FOREST_DIRT_SEQUENCE[1]
				))
			)
		);
	}

	static MultiNoiseBiomeSource stellarityBiomeSource(HolderGetter<Biome> biomes) {

		List<Pair<Climate.ParameterPoint, Holder<Biome>>> biomePoints = new ArrayList<>();

//		var crystalPeaks = biomes.get(nullscapeBiome("crystal_peaks"));
//		var shadowlands = biomes.get(nullscapeBiome("shadowlands"));
//		var voidBarrens = biomes.get(nullscapeBiome("void_barrens"));

//		boolean hasNullscape = crystalPeaks.isPresent() || shadowlands.isPresent() || voidBarrens.isPresent();

		for (var point : PARAMETER_POINTS) {
			var biome = biomes.get(point._1());
			if (biome.isEmpty()) continue;

			biomePoints.add(new Pair<>(point._2(), biome.get()));
		}

		return MultiNoiseBiomeSource.createFromList(new Climate.ParameterList<>(biomePoints));
	}
}
