package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.mixin.accessor.CubicSplineMultipointAccessor;
import dev.coder2195.stellarity.util.tuple.Tuple3;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import net.minecraft.world.level.levelgen.densityfunction.op.BinaryFunction;
import net.minecraft.world.level.levelgen.densityfunction.op.SplineFunction;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;

import java.util.ArrayList;
import java.util.List;

import static dev.coder2195.stellarity.util.WorldgenUtil.splineCoord;
import static dev.coder2195.stellarity.util.WorldgenUtil.splinePoint;
import static net.minecraft.world.level.levelgen.densityfunction.DensityFunctions.*;

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


	ResourceKey<DensityFunction> FINAL_DENSITY = id("final_density");
	ResourceKey<DensityFunction> INITIAL_DENSITY = id("initial_density");
	ResourceKey<DensityFunction> CLIMATE_EROSION = id("climate/erosion");
	ResourceKey<DensityFunction> CLIMATE_RIDGES = id("climate/ridges");
	ResourceKey<DensityFunction> CLIMATE_TEMPERATURE = id("climate/temperature");
	ResourceKey<DensityFunction> CLIMATE_HUMIDITY = id("climate/humidity");
	ResourceKey<DensityFunction> CLIMATE_DEPTH = id("climate/depth");
	ResourceKey<DensityFunction> CLIMATE_CONTINENTS = id("climate/continents");

	ResourceKey<DensityFunction> NULLSCAPE_COMPAT_INITIAL_DENSITY = compatId("initial_density");
	ResourceKey<DensityFunction> NULLSCAPE_COMPAT_FINAL_DENSITY = compatId("final_density");

	static ResourceKey<DensityFunction> id(String id) {
		return Stellarity.key(Registries.DENSITY_FUNCTION, id);
	}

	static ResourceKey<DensityFunction> compatId(String id) {
		return Stellarity.key(Registries.DENSITY_FUNCTION, "nullscape_compat", id);
	}

	static void bootstrap(BootstrapContext<DensityFunction> context) {
		var densityFunction = context.lookup(Registries.DENSITY_FUNCTION);
		var noises = context.lookup(Registries.NOISE);

		var base3DNoise = context.register(BASE_3D_NOISE, cache(new BinaryFunction(
			BinaryFunction.Type.MAX, new BlendedNoise(0.225, 0.3, 400, 200, 7), new BlendedNoise(0.3, 0.15, 600, 160, 3)
		).mul(1.2f)));

		var mainIslandIslandBuilder = context.register(MAIN_ISLAND_ISLAND_BUILDER, new SplineFunction(CubicSplineMultipointAccessor.createFromPoints(
			splineCoord(densityFunction, NoiseRouterData.END_ISLANDS), List.of(
				splinePoint(-0.5f, -0.5f, 1f),
				splinePoint(0, CubicSplineMultipointAccessor.createFromPoints(
					splineCoord(noise(noises.getOrThrow(StellarityNoises.MAIN_ISLAND_SHAPER_1), 1, 1).abs()),
					List.of(
						splinePoint(0, 0.25f, 1),
						splinePoint(0, 0.25f, -1)
					)
				), 1f),
				splinePoint(0.5f, 0.5f, 1)
			)
		)));

		List<HolderHolder> mainIslandD = new ArrayList<>();
		// dummy holder holder don't use index 0
		mainIslandD.add(new HolderHolder(base3DNoise));

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
		
		var mainIslandAvg = context.register(MAIN_ISLAND_AVG,
			mainIslandD.get(1).add(mainIslandD.get(2)).add(mainIslandD.get(3).add(mainIslandD.get(4)))
				.add(mainIslandD.get(5).add(mainIslandD.get(6)).add(mainIslandD.get(7).add(mainIslandD.get(8))))
				.add(
					mainIslandD.get(9).add(mainIslandD.get(10)).add(mainIslandD.get(11).add(mainIslandD.get(12)))
					.add(mainIslandD.get(13).add(mainIslandD.get(14)).add(mainIslandD.get(15).add(mainIslandD.get(16))))
				).mul(0.0625f)
		);

		var mainIsland3DNoise = context.register(MAIN_ISLAND_3D_NOISE, cache(new BlendedNoise(0.25, 0.25, 80, 160, 4)));
		var mainIslandEndIslandExtraSize = context.register(MAIN_ISLAND_END_ISLAND_EXTRA_SIZE, cache(endOuterIslands().add(0.3f)));

	}
}
