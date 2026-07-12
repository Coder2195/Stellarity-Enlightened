package dev.coder2195.stellarity.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import dev.coder2195.stellarity.Stellarity;

public interface StellarityNoises {
	ResourceKey<NormalNoise.NoiseParameters> CLIMATE_CONTINENTALNESS = id("climate/continentalness");
	ResourceKey<NormalNoise.NoiseParameters> CLIMATE_EROSION = id("climate/erosion");
	ResourceKey<NormalNoise.NoiseParameters> CLIMATE_HUMIDITY = id("climate/humidity");
	ResourceKey<NormalNoise.NoiseParameters> CLIMATE_TEMPERATURE = id("climate/temperature");
	ResourceKey<NormalNoise.NoiseParameters> CLIMATE_WEIRDNESS = id("climate/weirdness");

	ResourceKey<NormalNoise.NoiseParameters> MAIN_ISLAND_EDGES_1 = id("main_island/edges_1");
	ResourceKey<NormalNoise.NoiseParameters> MAIN_ISLAND_EDGES_2 = id("main_island/edges_2");
	ResourceKey<NormalNoise.NoiseParameters> MAIN_ISLAND_EDGES_3 = id("main_island/edges_3");
	ResourceKey<NormalNoise.NoiseParameters> MAIN_ISLAND_N1 = id("main_island/n1");
	ResourceKey<NormalNoise.NoiseParameters> MAIN_ISLAND_N2 = id("main_island/n2");
	ResourceKey<NormalNoise.NoiseParameters> MAIN_ISLAND_N3 = id("main_island/n3");
	ResourceKey<NormalNoise.NoiseParameters> MAIN_ISLAND_N4 = id("main_island/n4");
	ResourceKey<NormalNoise.NoiseParameters> MAIN_ISLAND_SHAPER_1 = id("main_island/shaper_1");
	ResourceKey<NormalNoise.NoiseParameters> MAIN_ISLAND_SHAPER_2 = id("main_island/shaper_2");
	ResourceKey<NormalNoise.NoiseParameters> MAIN_ISLAND_SHIFT_X = id("main_island/shift_x");
	ResourceKey<NormalNoise.NoiseParameters> MAIN_ISLAND_SHIFT_Y = id("main_island/shift_y");
	ResourceKey<NormalNoise.NoiseParameters> MAIN_ISLAND_SHIFT_Z = id("main_island/shift_z");
	ResourceKey<NormalNoise.NoiseParameters> MAIN_ISLAND_SURFACE = id("main_island/surface");

	ResourceKey<NormalNoise.NoiseParameters> JAGGED = id("jagged");
	ResourceKey<NormalNoise.NoiseParameters> SURFACE = id("surface");
	ResourceKey<NormalNoise.NoiseParameters> SURFACE_2X = id("surface_2x");
	ResourceKey<NormalNoise.NoiseParameters> SURFACE_4X = id("surface_4x");

	static void bootstrap(BootstrapContext<NormalNoise.NoiseParameters> context) {
		context.register(SURFACE_2X, new NormalNoise.NoiseParameters(-7, 1, 1, 1));
		context.register(SURFACE, new NormalNoise.NoiseParameters(-6, 1, 1, 1));
		context.register(SURFACE_4X, new NormalNoise.NoiseParameters(-8, 1, 1, 1));
		context.register(JAGGED, new NormalNoise.NoiseParameters(-16, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
		context.register(CLIMATE_EROSION, new NormalNoise.NoiseParameters(-9, 1, 1, 0, 1, 1));
		context.register(CLIMATE_TEMPERATURE, new NormalNoise.NoiseParameters(-10, 1.5, 0, 1, 0, 0, 0));
		context.register(CLIMATE_HUMIDITY, new NormalNoise.NoiseParameters(-8, 1, 1, 0, 0, 0, 0));
		context.register(CLIMATE_CONTINENTALNESS, new NormalNoise.NoiseParameters(-9, 1, 1, 2, 2, 2, 1, 1, 1, 1));
		context.register(CLIMATE_WEIRDNESS, new NormalNoise.NoiseParameters(-7, 1, 2, 1, 0, 0, 0));
		context.register(MAIN_ISLAND_N1, new NormalNoise.NoiseParameters(-14, 1.0));
		context.register(MAIN_ISLAND_SHIFT_X, new NormalNoise.NoiseParameters(-6, 1));
		context.register(MAIN_ISLAND_N3, new NormalNoise.NoiseParameters(-14, 1.0));
		context.register(MAIN_ISLAND_N2, new NormalNoise.NoiseParameters(-14, 1.0));
		context.register(MAIN_ISLAND_N4, new NormalNoise.NoiseParameters(-14, 1.0));
		context.register(MAIN_ISLAND_SURFACE, new NormalNoise.NoiseParameters(-3, 1, 1, 1, 1, 1));
		context.register(MAIN_ISLAND_EDGES_3, new NormalNoise.NoiseParameters(-5, -1, 0.5, 1.5, 0));
		context.register(MAIN_ISLAND_EDGES_1, new NormalNoise.NoiseParameters(-7, 1.5, -0.2, 1, 0, 0));
		context.register(MAIN_ISLAND_SHIFT_Y, new NormalNoise.NoiseParameters(-6, 1));
		context.register(MAIN_ISLAND_EDGES_2, new NormalNoise.NoiseParameters(-6, 1, 2, 1, 0, 0));
		context.register(MAIN_ISLAND_SHAPER_1, new NormalNoise.NoiseParameters(-4, 1));
		context.register(MAIN_ISLAND_SHIFT_Z, new NormalNoise.NoiseParameters(-6, 1));
		context.register(MAIN_ISLAND_SHAPER_2, new NormalNoise.NoiseParameters(-8, 1.0, 1.0, 1.0, 1.0));
	}

	private static ResourceKey<NormalNoise.NoiseParameters> id(String id) {
		return ResourceKey.create(Registries.NOISE, Stellarity.id(id));
	}
}
