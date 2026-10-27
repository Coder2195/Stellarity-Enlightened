package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.util.tuple.Tuple3;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import net.minecraft.world.level.levelgen.densityfunction.TilingMode;
import net.minecraft.world.level.levelgen.densityfunction.generator.NoiseFunction;
import net.minecraft.world.level.levelgen.densityfunction.op.upscale.UpscaleFunction;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;

import java.util.ArrayList;
import java.util.List;

import static dev.coder2195.stellarity.util.WorldgenUtil.*;
import static dev.coder2195.stellarity.util.WorldgenUtil.spline;
import static net.minecraft.world.level.levelgen.densityfunction.DensityFunctions.*;
import static net.minecraft.world.level.levelgen.densityfunction.DensityFunctions.spline;

public interface StellarityDensityFunctions {
	ResourceKey<DensityFunction> BASE_3D_NOISE = id("base_3d_noise");
	ResourceKey<DensityFunction> MAIN_ISLAND_ISLAND_BUILDER = id("main_island/island_builder");
	ResourceKey<DensityFunction> MAIN_ISLAND_D1 = id("main_island/d1");
	ResourceKey<DensityFunction> MAIN_ISLAND_D2 = id("main_island/d2");
	ResourceKey<DensityFunction> MAIN_ISLAND_D3 = id("main_island/d3");
	ResourceKey<DensityFunction> MAIN_ISLAND_D4 = id("main_island/d4");
	ResourceKey<DensityFunction> MAIN_ISLAND_D5 = id("main_island/d5");
	ResourceKey<DensityFunction> MAIN_ISLAND_D6 = id("main_island/d6");
	ResourceKey<DensityFunction> MAIN_ISLAND_D7 = id("main_island/d7");
	ResourceKey<DensityFunction> MAIN_ISLAND_D8 = id("main_island/d8");
	ResourceKey<DensityFunction> MAIN_ISLAND_D9 = id("main_island/d9");
	ResourceKey<DensityFunction> MAIN_ISLAND_D10 = id("main_island/d10");
	ResourceKey<DensityFunction> MAIN_ISLAND_D11 = id("main_island/d11");
	ResourceKey<DensityFunction> MAIN_ISLAND_D12 = id("main_island/d12");
	ResourceKey<DensityFunction> MAIN_ISLAND_D13 = id("main_island/d13");
	ResourceKey<DensityFunction> MAIN_ISLAND_D14 = id("main_island/d14");
	ResourceKey<DensityFunction> MAIN_ISLAND_D15 = id("main_island/d15");
	ResourceKey<DensityFunction> MAIN_ISLAND_D16 = id("main_island/d16");
	ResourceKey<DensityFunction> MAIN_ISLAND_3D_NOISE = id("main_island/3d_noise");
	ResourceKey<DensityFunction> MAIN_ISLAND_AVG = id("main_island/avg");
	ResourceKey<DensityFunction> MAIN_ISLAND_END_ISLAND_EXTRA_SIZE = id("main_island/end_island_extra_size");
	ResourceKey<DensityFunction> MAIN_ISLAND_FARLANDS_TAPER = id("main_island/farlands_taper");
	ResourceKey<DensityFunction> MAIN_ISLAND_SHAPER_1 = id("main_island/shaper_1");
	ResourceKey<DensityFunction> MAIN_ISLAND_SHAPER_2 = id("main_island/shaper_2");
	ResourceKey<DensityFunction> MAIN_ISLAND_ISLAND = id("main_island/island");

	ResourceKey<DensityFunction> CAVE_NOODLE = id("cave/noodle");

	ResourceKey<DensityFunction> CLIMATE_CONTINENTS = id("climate/continents");
	ResourceKey<DensityFunction> CLIMATE_EROSION = id("climate/erosion");
	ResourceKey<DensityFunction> CLIMATE_RIDGES = id("climate/ridges");
	ResourceKey<DensityFunction> CLIMATE_TEMPERATURE = id("climate/temperature");
	ResourceKey<DensityFunction> CLIMATE_HUMIDITY = id("climate/humidity");
	ResourceKey<DensityFunction> CLIMATE_DEPTH = id("climate/depth");

	ResourceKey<DensityFunction> FACTOR = id("factor");
	ResourceKey<DensityFunction> JAGGEDNESS = id("jaggedness");
	ResourceKey<DensityFunction> OFFSET = id("offset");
	ResourceKey<DensityFunction> INITIAL_DENSITY = id("initial_density");
	ResourceKey<DensityFunction> SLOPED_CHEESE = id("sloped_cheese");

	// unfinished
	ResourceKey<DensityFunction> FINAL_DENSITY = id("final_density");

	ResourceKey<DensityFunction> NULLSCAPE_COMPAT_INITIAL_DENSITY = compatId("initial_density");
	ResourceKey<DensityFunction> NULLSCAPE_COMPAT_FINAL_DENSITY = compatId("final_density");

	static ResourceKey<DensityFunction> id(String id) {
		return Stellarity.key(Registries.DENSITY_FUNCTION, id);
	}

	static ResourceKey<DensityFunction> compatId(String id) {
		return Stellarity.key(Registries.DENSITY_FUNCTION, "nullscape_compat", id);
	}

	static void bootstrap(BootstrapContext<DensityFunction> context) {
		var densityFunctions = context.lookup(Registries.DENSITY_FUNCTION);
		var noises = context.lookup(Registries.NOISE);

		var base3DNoise = densityFunction(context.register(BASE_3D_NOISE, cache(max(
			new BlendedNoise(0.225, 0.3, 400, 200, 7), new BlendedNoise(0.3, 0.15, 600, 160, 3)
		).mul(1.2f))));

		var mainIslandIslandBuilder = densityFunction(context.register(MAIN_ISLAND_ISLAND_BUILDER, spline(spline(
			splineCoord(densityFunctions, NoiseRouterData.END_ISLANDS), List.of(
				splinePoint(-0.5f, -0.5f, 1f),
				splinePoint(0, spline(
					splineCoord(noise(noises.getOrThrow(StellarityNoises.MAIN_ISLAND_SHAPER_1), 1, 1).abs()),
					List.of(
						splinePoint(0, 0.25f, 1),
						splinePoint(0, 0.25f, -1)
					)
				), 1f),
				splinePoint(0.5f, 0.5f, 1)
			)
		))));

		List<HolderHolder> mainIslandD = new ArrayList<>();
		// dummy holder holder don't use index 0
		mainIslandD.add(base3DNoise);

		for (var islandD : List.of(
			new Tuple3<>(MAIN_ISLAND_D1, StellarityNoises.MAIN_ISLAND_N1, 1),
			new Tuple3<>(MAIN_ISLAND_D2, StellarityNoises.MAIN_ISLAND_N2, 2),
			new Tuple3<>(MAIN_ISLAND_D3, StellarityNoises.MAIN_ISLAND_N3, 3),
			new Tuple3<>(MAIN_ISLAND_D4, StellarityNoises.MAIN_ISLAND_N4, 4),
			new Tuple3<>(MAIN_ISLAND_D5, StellarityNoises.MAIN_ISLAND_N5, 5),
			new Tuple3<>(MAIN_ISLAND_D6, StellarityNoises.MAIN_ISLAND_N6, 6),
			new Tuple3<>(MAIN_ISLAND_D7, StellarityNoises.MAIN_ISLAND_N7, 7),
			new Tuple3<>(MAIN_ISLAND_D8, StellarityNoises.MAIN_ISLAND_N8, 8),
			new Tuple3<>(MAIN_ISLAND_D9, StellarityNoises.MAIN_ISLAND_N9, 9),
			new Tuple3<>(MAIN_ISLAND_D10, StellarityNoises.MAIN_ISLAND_N10, 10),
			new Tuple3<>(MAIN_ISLAND_D11, StellarityNoises.MAIN_ISLAND_N11, 11),
			new Tuple3<>(MAIN_ISLAND_D12, StellarityNoises.MAIN_ISLAND_N12, 12),
			new Tuple3<>(MAIN_ISLAND_D13, StellarityNoises.MAIN_ISLAND_N13, 13),
			new Tuple3<>(MAIN_ISLAND_D14, StellarityNoises.MAIN_ISLAND_N14, 14),
			new Tuple3<>(MAIN_ISLAND_D15, StellarityNoises.MAIN_ISLAND_N15, 15),
			new Tuple3<>(MAIN_ISLAND_D16, StellarityNoises.MAIN_ISLAND_N16, 16)
		))
			mainIslandD.add(islandD._3(), new HolderHolder(context.register(islandD._1(), square(
				noise(noises.getOrThrow(islandD._2()), 1, 0).add(noise(noises.getOrThrow(islandD._2()), 0.95, 0).mul(-1)
				).mul(345)
			))));

		var mainIslandAvg = densityFunction(context.register(MAIN_ISLAND_AVG,
			add(
				add(
					add(
						add(mainIslandD.get(1), mainIslandD.get(2)),
						add(mainIslandD.get(3), mainIslandD.get(4))
					),
					add(
						add(mainIslandD.get(5), mainIslandD.get(6)),
						add(mainIslandD.get(7), mainIslandD.get(8))
					)
				),
				add(add(
						add(mainIslandD.get(9), mainIslandD.get(10)),
						add(mainIslandD.get(11), mainIslandD.get(12))
					),
					add(
						add(mainIslandD.get(13), mainIslandD.get(14)),
						add(mainIslandD.get(15), mainIslandD.get(16)
						)
					)
				)
			).mul(0.0625f)
		));

		context.register(MAIN_ISLAND_3D_NOISE, cache(new BlendedNoise(0.25, 0.25, 80, 160, 4)));
		context.register(MAIN_ISLAND_END_ISLAND_EXTRA_SIZE, cache(endOuterIslands().add(0.3f)));
		var farlandsTaper = densityFunction(context.register(MAIN_ISLAND_FARLANDS_TAPER, spline(spline(splineCoord(mainIslandAvg), List.of(
			splinePoint(0.4f, -0.5f, 0),
			splinePoint(0.65f, -0.5f, 0),
			splinePoint(1.05f, 0, 0)
		)))));
		context.register(MAIN_ISLAND_SHAPER_1, new NoiseFunction(
			noises.getOrThrow(StellarityNoises.MAIN_ISLAND_SHAPER_2),
			2, 1,
			noise(noises.getOrThrow(StellarityNoises.MAIN_ISLAND_SHIFT_X), 2, 4).mul(300),
			noise(noises.getOrThrow(StellarityNoises.MAIN_ISLAND_SHIFT_Y), 1, 2).mul(150),
			noise(noises.getOrThrow(StellarityNoises.MAIN_ISLAND_SHIFT_Z), 2, 4).mul(300)
		).mul(2));
		context.register(MAIN_ISLAND_SHAPER_2, new NoiseFunction(
			noises.getOrThrow(StellarityNoises.MAIN_ISLAND_SHAPER_2),
			-2, -1,
			noise(noises.getOrThrow(StellarityNoises.MAIN_ISLAND_SHIFT_X), -2, -4).mul(150),
			noise(noises.getOrThrow(StellarityNoises.MAIN_ISLAND_SHIFT_Y), -1, -2).mul(75),
			noise(noises.getOrThrow(StellarityNoises.MAIN_ISLAND_SHIFT_Z), -2, -4).mul(150)
		).mul(2));
		var mcShiftX = densityFunction(densityFunctions, NoiseRouterData.SHIFT_X);
		var mcShiftZ = densityFunction(densityFunctions, NoiseRouterData.SHIFT_Z);
		var climateContinents = densityFunction(context.register(CLIMATE_CONTINENTS, cache(rangeChoice(
			mainIslandAvg, -1000000, 1.05f,
			constant(-2), new NoiseFunction(
				noises.getOrThrow(StellarityNoises.CLIMATE_CONTINENTALNESS), 0.2, 0,
				mcShiftX, zero(), mcShiftZ
			)
		))));
		var climateErosion = densityFunction(context.register(CLIMATE_EROSION, cache(rangeChoice(
			mainIslandAvg, -1000000, 0.45f,
			constant(-2), rangeChoice(
				mainIslandAvg, 0.45f, 1.05f,
				constant(2), new NoiseFunction(
					noises.getOrThrow(StellarityNoises.CLIMATE_EROSION), 0.2, 0,
					mcShiftX, zero(), mcShiftZ
				)
			)
		))));
		context.register(CLIMATE_HUMIDITY, cache(new NoiseFunction(noises.getOrThrow(StellarityNoises.CLIMATE_HUMIDITY), 0.25, 0, mcShiftX, zero(), mcShiftZ)));
		context.register(CLIMATE_RIDGES, cache(new NoiseFunction(noises.getOrThrow(StellarityNoises.CLIMATE_WEIRDNESS), 0.2325, 0, mcShiftX, zero(), mcShiftZ)));
		context.register(CLIMATE_TEMPERATURE, cache(new NoiseFunction(noises.getOrThrow(StellarityNoises.CLIMATE_TEMPERATURE), 0.25, 0, mcShiftX, zero(), mcShiftZ)));

		var factor = densityFunction(context.register(FACTOR, cache(cache(spline(spline(
			splineCoord(climateContinents), List.of(
				splinePoint(0.2f, spline(splineCoord(climateErosion), List.of(
					splinePoint(0.6f, 0.15f, 0),
					splinePoint(0.7f, 0.2f, 0),
					splinePoint(0.8f, 0.21f, 0),
					splinePoint(0.9f, 0.37f, 0),
					splinePoint(1, 0.25f, 0)
				)), 0.2f),
				splinePoint(0.3f, spline(splineCoord(climateErosion), List.of(
					splinePoint(0.1f, 0.1f, 0),
					splinePoint(0.2f, 0.05f, 0),
					splinePoint(0.3f, 0, 0),
					splinePoint(0.4f, 0.05f, 0),
					splinePoint(0.5f, 0.1f, 0),
					splinePoint(0.9f, 0.37f, 0),
					splinePoint(1, 0.25f, 0)
				)), 0),
				splinePoint(0.4f, spline(splineCoord(climateErosion), List.of(
					splinePoint(0.1f, 0.1f, 0),
					splinePoint(0.2f, 0.05f, 0),
					splinePoint(0.3f, 0, 0),
					splinePoint(0.4f, 0.05f, 0),
					splinePoint(0.5f, 0.1f, 0),
					splinePoint(0.6f, 0.15f, 0),
					splinePoint(0.7f, 0.2f, 0),
					splinePoint(0.8f, 0.21f, 0),
					splinePoint(0.9f, 0.37f, 0),
					splinePoint(1, 0.25f, 0)
				)), 0),
				splinePoint(0.5f, spline(splineCoord(climateErosion), List.of(
					splinePoint(0.3f, 0, 0),
					splinePoint(0.4f, 0.05f, 0),
					splinePoint(0.5f, 0.1f, 0),
					splinePoint(0.8f, 0.21f, 0),
					splinePoint(0.9f, 0.37f, 0),
					splinePoint(1, 0.25f, 0)
				)), 0.21f),
				splinePoint(0.6f, spline(splineCoord(climateErosion), List.of(
					splinePoint(0.2f, 0.05f, 0),
					splinePoint(0.5f, 0.1f, 0),
					splinePoint(0.6f, 0.15f, 0)
				)), -0.2f),
				splinePoint(0.7f, spline(splineCoord(climateErosion), List.of(
					splinePoint(0.1f, 0.1f, 0),
					splinePoint(0.8f, 0.21f, 0)
				)), 0.2f),
				splinePoint(0.9f, spline(splineCoord(climateErosion), List.of(
					splinePoint(0.1f, 0.1f, 0),
					splinePoint(0.2f, 0.05f, 0),
					splinePoint(0.3f, 0, 0),
					splinePoint(0.4f, 0.05f, 0),
					splinePoint(0.5f, 0.1f, 0),
					splinePoint(0.6f, 0.15f, 0),
					splinePoint(0.7f, 0.2f, 0),
					splinePoint(0.8f, 0.21f, 0),
					splinePoint(0.9f, 0.37f, 0),
					splinePoint(1, 0.25f, 0)
				)), 0)
			)
		)).mul(1.5f).add(-0.1f)))));

		var jaggedness = densityFunction(context.register(JAGGEDNESS, cache(cache(spline(spline(
			splineCoord(climateContinents), List.of(
				splinePoint(0.2f, spline(splineCoord(climateErosion), List.of(
					splinePoint(0.1f, 0.05f, 0),
					splinePoint(0.5f, 0.15f, 0),
					splinePoint(0.9f, 0.2f, 0),
					splinePoint(1, 0.25f, 0)
				)), 1),
				splinePoint(0.4f, spline(splineCoord(climateErosion), List.of(
					splinePoint(0.1f, 0.05f, 0),
					splinePoint(0.4f, 0.1f, 0),
					splinePoint(0.5f, 0.15f, 0),
					splinePoint(0.9f, 0.2f, 0),
					splinePoint(1, 0.25f, 0)
				)), 10),
				splinePoint(0.5f, spline(splineCoord(climateErosion), List.of(
					splinePoint(0.6f, 0.1f, 0),
					splinePoint(0.7f, 0.114f, 0),
					splinePoint(0.8f, 0.17f, 0),
					splinePoint(0.9f, 0.2f, 0),
					splinePoint(1, 0.25f, 0)
				)), 0),
				splinePoint(0.6f, spline(splineCoord(climateErosion), List.of(
					splinePoint(0.1f, 0.05f, 0),
					splinePoint(0.3f, 0.055f, 0),
					splinePoint(0.6f, 0.1f, 0),
					splinePoint(0.7f, 0.114f, 0),
					splinePoint(1, 0.25f, 0)
				)), 0),
				splinePoint(0.7f, spline(splineCoord(climateErosion), List.of(
					splinePoint(0.1f, 0.05f, 0),
					splinePoint(0.4f, 0.1f, 0),
					splinePoint(1, 0.25f, 0)
				)), 0.2f),
				splinePoint(0.8f, spline(splineCoord(climateErosion), List.of(
					splinePoint(0.1f, 0.05f, 0),
					splinePoint(0.2f, 0.05f, 0),
					splinePoint(0.3f, 0.055f, 0),
					splinePoint(0.5f, 0.15f, 0),
					splinePoint(0.9f, 0.2f, 0),
					splinePoint(1, 0.25f, 0)
				)), 3),
				splinePoint(0.9f, spline(splineCoord(climateErosion), List.of(
					splinePoint(0.8f, 0.17f, 0),
					splinePoint(0.9f, 0.2f, 0),
					splinePoint(1, 0.25f, 0)
				)), 1.52f)
			)
		))))));

		var offset = densityFunction(context.register(OFFSET, cache(cache(spline(spline(
			splineCoord(climateContinents), List.of(
				splinePoint(-1, spline(splineCoord(climateErosion), List.of(
					splinePoint(-0.8f, -0.36f, 0),
					splinePoint(-0.9f, -0.265f, 0),
					splinePoint(-1f, -0.05f, 0)
				)), 0.1f),
				splinePoint(-0.6f, spline(splineCoord(climateErosion), List.of(
					splinePoint(-0.6f, -0.165f, 0),
					splinePoint(-0.7f, -0.1655f, 0),
					splinePoint(-0.9f, -0.265f, 0),
					splinePoint(-0.8f, -0.36f, 0),
					splinePoint(-1f, -0.05f, 0)
				)), 0.1f),
				splinePoint(-0.4f, spline(splineCoord(climateErosion), List.of(
					splinePoint(-0.1f, -0.025f, 0),
					splinePoint(-0.2f, -0.05f, 0)
				)), 0.2f),
				splinePoint(0.2f, spline(splineCoord(climateErosion), List.of(
					splinePoint(0.6f, 0.09f, 0),
					splinePoint(0.7f, 0.09f, 0),
					splinePoint(0.8f, 0.1f, 0),
					splinePoint(0.9f, 0.13f, 0),
					splinePoint(1, 0.09f, 0)
				)), 0.2f),
				splinePoint(0.6f, spline(splineCoord(climateErosion), List.of(
					splinePoint(0, 0.05f, 0),
					splinePoint(0.1f, 0.08f, 0),
					splinePoint(0.1f, 0.09f, 0),
					splinePoint(0.3f, 0.15f, 0),
					splinePoint(0.4f, 0.16f, 0),
					splinePoint(0.5f, 0.09f, 0),
					splinePoint(0.6f, 0.09f, 0),
					splinePoint(0.7f, 0.09f, 0),
					splinePoint(0.8f, 0.1f, 0),
					splinePoint(0.9f, 0.13f, 0),
					splinePoint(1, 0.09f, 0)
				)), 0.06f),
				splinePoint(0.8f, spline(splineCoord(climateErosion), List.of(
					splinePoint(0.6f, 0.09f, 0),
					splinePoint(0.8f, 0.1f, 0)
				)), 0),
				splinePoint(0.9f, spline(splineCoord(climateErosion), List.of(
					splinePoint(0.1f, 0.08f, 0),
					splinePoint(0.1f, 0.09f, 0),
					splinePoint(0.7f, 0.09f, 0),
					splinePoint(0.8f, 0.1f, 0)
				)), 0),
				splinePoint(1, spline(splineCoord(climateErosion), List.of(
					splinePoint(0, 0.05f, 0),
					splinePoint(0.6f, 0.09f, 0),
					splinePoint(0.7f, 0.09f, 0),
					splinePoint(0.9f, 0.13f, 0),
					splinePoint(1, 0.09f, 0)
				)), 0)
			)
		)).add(-0.15f).mul(1.88f)))));

		var mcY = densityFunction(densityFunctions, NoiseRouterData.Y);

		var climateDepth = densityFunction(context.register(CLIMATE_DEPTH, cache(rangeChoice(
			gradient(Direction.Axis.Y, TilingMode.CLAMP_TO_EDGE, 0, 256, 0, 256),
			0, 1, constant(2),
			mul(gradient(Direction.Axis.Y, TilingMode.CLAMP_TO_EDGE, 24, 256, -2, 2), offset)
		))));

		context.register(INITIAL_DENSITY, quarterNegative(mul(climateDepth, cache(factor))).mul(4));
		var caveNoodle = densityFunction(context.register(CAVE_NOODLE, add(
			upscale(rangeChoice(
				mcY, 0, 256,
				noise(noises.getOrThrow(Noises.NOODLE_THICKNESS)).mul(-0.1f).add(-0.1f), zero()
			), UpscaleFunction.Mode.INTERPOLATE_LINEAR, 4, 8),
			max(
				abs(upscale(rangeChoice(
					mcY, 0, 256,
					noise(noises.getOrThrow(Noises.NOODLE_RIDGE_A), 2, 2), zero()
				), UpscaleFunction.Mode.INTERPOLATE_LINEAR, 4, 8)),
				abs(upscale(rangeChoice(
					mcY, 0, 256,
					noise(noises.getOrThrow(Noises.NOODLE_RIDGE_B), 2, 2), zero()
				), UpscaleFunction.Mode.INTERPOLATE_LINEAR, 4, 8))
			).mul(1.5f)
		)));

		var mainIslandIsland = densityFunction(context.register(MAIN_ISLAND_ISLAND, squeeze(upscale(
			upscale(
				mul(
					gradient(Direction.Axis.Y, TilingMode.CLAMP_TO_EDGE, 45, 65, 1, 0),
					mul(
						gradient(Direction.Axis.Y, TilingMode.CLAMP_TO_EDGE, 2, 26, 0, 1),
						mainIslandIslandBuilder.add(1)
					).add(-1).add(1)
				).add(-1), UpscaleFunction.Mode.INTERPOLATE_LINEAR, 4, 8
			), UpscaleFunction.Mode.INTERPOLATE_LINEAR, 4, 8)
		)));

		var slopedCheese = densityFunction(context.register(SLOPED_CHEESE, upscale(
			blendDensity(
				mul(
					gradient(Direction.Axis.Y, TilingMode.CLAMP_TO_EDGE, -10, 27, 0, 1),
					mul(
						gradient(Direction.Axis.Y, TilingMode.CLAMP_TO_EDGE, 105, 165, 1, 0),
						add(
							mul(
								add(
									climateDepth,
									mul(
										jaggedness,
										halfNegative(mul(
											factor,
											noise(noises.getOrThrow(Noises.JAGGED), 1500, 0)
										))
									)
								),
								factor
							).mul(0.8f),
							base3DNoise
						).add(0.1f)
					).add(-0.1f).add(1.4f)
				).add(-1.4f)
			), UpscaleFunction.Mode.INTERPOLATE_LINEAR, 4, 8
		).add(-0.2f).mul(0.1111111f)));

		context.register(FINAL_DENSITY, rangeChoice(
			mainIslandAvg, -1000000, 0.45f,
			mainIslandIsland,
			add(
				add(
					min(slopedCheese, caveNoodle),
					farlandsTaper
				),
				clamp(beardifier(), -0.15f, 1)
			)
		));
	}
}
