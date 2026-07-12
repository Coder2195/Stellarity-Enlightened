package dev.coder2195.stellarity.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.DensityFunction;
import dev.coder2195.stellarity.Stellarity;

public interface StellarityDensityFunctions {
	ResourceKey<DensityFunction> BASE_3D_NOISE = id("base_3d_noise");
	ResourceKey<DensityFunction> FINAL_DENSITY = id("final_density");
	ResourceKey<DensityFunction> JAGGEDNESS = id("jaggedness");
	ResourceKey<DensityFunction> SLOPED_CHEESE = id("sloped_cheese");
	ResourceKey<DensityFunction> OFFSET = id("offset");
	ResourceKey<DensityFunction> INITIAL_DENSITY = id("initial_density");
	ResourceKey<DensityFunction> FACTOR = id("factor");
	ResourceKey<DensityFunction> CLIMATE_EROSION = id("climate/erosion");
	ResourceKey<DensityFunction> CLIMATE_RIDGES = id("climate/ridges");
	ResourceKey<DensityFunction> CLIMATE_TEMPERATURE = id("climate/temperature");
	ResourceKey<DensityFunction> CLIMATE_HUMIDITY = id("climate/humidity");
	ResourceKey<DensityFunction> CLIMATE_DEPTH = id("climate/depth");
	ResourceKey<DensityFunction> CLIMATE_CONTINENTS = id("climate/continents");
	ResourceKey<DensityFunction> MAIN_ISLAND_D2 = id("main_island/d2");
	ResourceKey<DensityFunction> MAIN_ISLAND_END_ISLAND_EXTRA_SIZE = id("main_island/end_island_extra_size");
	ResourceKey<DensityFunction> MAIN_ISLAND_3D_NOISE = id("main_island/3d_noise");
	ResourceKey<DensityFunction> MAIN_ISLAND_D4 = id("main_island/d4");
	ResourceKey<DensityFunction> MAIN_ISLAND_AVG = id("main_island/avg");
	ResourceKey<DensityFunction> MAIN_ISLAND_ISLAND = id("main_island/island");
	ResourceKey<DensityFunction> MAIN_ISLAND_D3 = id("main_island/d3");
	ResourceKey<DensityFunction> MAIN_ISLAND_D1 = id("main_island/d1");
	ResourceKey<DensityFunction> MAIN_ISLAND_SHAPER_1 = id("main_island/shaper_1");
	ResourceKey<DensityFunction> MAIN_ISLAND_ISLAND_BUILDER = id("main_island/island_builder");
	ResourceKey<DensityFunction> MAIN_ISLAND_SHAPER_2 = id("main_island/shaper_2");
	ResourceKey<DensityFunction> CAVE_NOODLE = id("cave/noodle");

	static ResourceKey<DensityFunction> id(String id) {
		return Stellarity.key(Registries.DENSITY_FUNCTION, id);
	}
}
