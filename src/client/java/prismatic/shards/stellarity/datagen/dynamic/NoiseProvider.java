package prismatic.shards.stellarity.datagen.dynamic;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import static prismatic.shards.stellarity.key.StellarityNoises.*;

public interface NoiseProvider {
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
}
