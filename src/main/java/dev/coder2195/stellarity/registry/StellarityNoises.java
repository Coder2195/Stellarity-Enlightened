package dev.coder2195.stellarity.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import dev.coder2195.stellarity.Stellarity;

import java.util.List;

import static dev.coder2195.stellarity.util.WorldgenUtil.oldNoise;

public interface StellarityNoises {
	ResourceKey<NormalNoise> CLIMATE_CONTINENTALNESS = id("climate/continentalness");
	ResourceKey<NormalNoise> CLIMATE_EROSION = id("climate/erosion");
	ResourceKey<NormalNoise> CLIMATE_HUMIDITY = id("climate/humidity");
	ResourceKey<NormalNoise> CLIMATE_TEMPERATURE = id("climate/temperature");
	ResourceKey<NormalNoise> CLIMATE_WEIRDNESS = id("climate/weirdness");

	ResourceKey<NormalNoise> MAIN_ISLAND_EDGES_1 = id("main_island/edges_1");
	ResourceKey<NormalNoise> MAIN_ISLAND_EDGES_2 = id("main_island/edges_2");
	ResourceKey<NormalNoise> MAIN_ISLAND_EDGES_3 = id("main_island/edges_3");
	ResourceKey<NormalNoise> MAIN_ISLAND_N1 = id("main_island/n1");
	ResourceKey<NormalNoise> MAIN_ISLAND_N2 = id("main_island/n2");
	ResourceKey<NormalNoise> MAIN_ISLAND_N3 = id("main_island/n3");
	ResourceKey<NormalNoise> MAIN_ISLAND_N4 = id("main_island/n4");
	ResourceKey<NormalNoise> MAIN_ISLAND_N5 = id("main_island/n5");
	ResourceKey<NormalNoise> MAIN_ISLAND_N6 = id("main_island/n6");
	ResourceKey<NormalNoise> MAIN_ISLAND_N7 = id("main_island/n7");
	ResourceKey<NormalNoise> MAIN_ISLAND_N8 = id("main_island/n8");
	ResourceKey<NormalNoise> MAIN_ISLAND_N9 = id("main_island/n9");
	ResourceKey<NormalNoise> MAIN_ISLAND_N10 = id("main_island/n10");
	ResourceKey<NormalNoise> MAIN_ISLAND_N11 = id("main_island/n11");
	ResourceKey<NormalNoise> MAIN_ISLAND_N12 = id("main_island/n12");
	ResourceKey<NormalNoise> MAIN_ISLAND_N13 = id("main_island/n13");
	ResourceKey<NormalNoise> MAIN_ISLAND_N14 = id("main_island/n14");
	ResourceKey<NormalNoise> MAIN_ISLAND_N15 = id("main_island/n15");
	ResourceKey<NormalNoise> MAIN_ISLAND_N16 = id("main_island/n16");
	ResourceKey<NormalNoise> MAIN_ISLAND_SHAPER_1 = id("main_island/shaper_1");
	ResourceKey<NormalNoise> MAIN_ISLAND_SHAPER_2 = id("main_island/shaper_2");
	ResourceKey<NormalNoise> MAIN_ISLAND_SHIFT_X = id("main_island/shift_x");
	ResourceKey<NormalNoise> MAIN_ISLAND_SHIFT_Y = id("main_island/shift_y");
	ResourceKey<NormalNoise> MAIN_ISLAND_SHIFT_Z = id("main_island/shift_z");
	ResourceKey<NormalNoise> MAIN_ISLAND_SURFACE = id("main_island/surface");

	ResourceKey<NormalNoise> JAGGED = id("jagged");
	ResourceKey<NormalNoise> SURFACE = id("surface");
	ResourceKey<NormalNoise> SURFACE_2X = id("surface_2x");
	ResourceKey<NormalNoise> SURFACE_4X = id("surface_4x");

	static void bootstrap(BootstrapContext<NormalNoise> context) {
		context.register(SURFACE_2X, oldNoise(-7, 1, 1, 1));
		context.register(SURFACE, oldNoise(-6, 1, 1, 1));
		context.register(SURFACE_4X, oldNoise(-8, 1, 1, 1));
		context.register(JAGGED, oldNoise(-16, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
		context.register(CLIMATE_EROSION, oldNoise(-9, 1, 1, 0, 1, 1));
		context.register(CLIMATE_TEMPERATURE, oldNoise(-10, 1.5, 0, 1, 0, 0, 0));
		context.register(CLIMATE_HUMIDITY, oldNoise(-8, 1, 1, 0, 0, 0, 0));
		context.register(CLIMATE_CONTINENTALNESS, oldNoise(-9, 1, 1, 2, 2, 2, 1, 1, 1, 1));
		context.register(CLIMATE_WEIRDNESS, oldNoise(-7, 1, 2, 1, 0, 0, 0));

		// ask void on what this fucking is for cuz i don't fucking know why he made 16 fucking noises but I trust that guy so forgive me God
		for (var nNoise: List.of(
			MAIN_ISLAND_N1, MAIN_ISLAND_N2, MAIN_ISLAND_N3, MAIN_ISLAND_N4,
			MAIN_ISLAND_N5, MAIN_ISLAND_N6, MAIN_ISLAND_N7, MAIN_ISLAND_N8,
			MAIN_ISLAND_N9, MAIN_ISLAND_N10, MAIN_ISLAND_N11, MAIN_ISLAND_N12,
			MAIN_ISLAND_N13, MAIN_ISLAND_N14, MAIN_ISLAND_N15, MAIN_ISLAND_N16
		)) context.register(nNoise, oldNoise(-14, 1.0));


		context.register(MAIN_ISLAND_SHIFT_X, oldNoise(-6, 1));
		context.register(MAIN_ISLAND_SURFACE, oldNoise(-3, 1, 1, 1, 1, 1));
		context.register(MAIN_ISLAND_EDGES_3, oldNoise(-5, 0, 0.5, 1.5, 0));
		context.register(MAIN_ISLAND_EDGES_1, oldNoise(-7, 1.5, 0, 1, 0, 0));
		context.register(MAIN_ISLAND_SHIFT_Y, oldNoise(-6, 1));
		context.register(MAIN_ISLAND_EDGES_2, oldNoise(-6, 1, 2, 1, 0, 0));
		context.register(MAIN_ISLAND_SHAPER_1, oldNoise(-4, 1));
		context.register(MAIN_ISLAND_SHIFT_Z, oldNoise(-6, 1));
		context.register(MAIN_ISLAND_SHAPER_2, oldNoise(-8, 1.0, 1.0, 1.0, 1.0));
	}

	private static ResourceKey<NormalNoise> id(String id) {
		return ResourceKey.create(Registries.NOISE, Stellarity.id(id));
	}
}
