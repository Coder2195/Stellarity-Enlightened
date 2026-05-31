package prismatic.shards.stellarity.registry;

import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.SurfaceRules;
import prismatic.shards.stellarity.Stellarity;

import static prismatic.shards.stellarity.key.StellarityDensityFunctions.*;

public class StellarityRegistryEntryModifications {
	private static DensityFunction temperature;
	private static DensityFunction vegetation;
	private static DensityFunction continents;
	private static DensityFunction erosion;
	private static DensityFunction depth;
	private static DensityFunction ridges;
	private static DensityFunction preliminarySurfaceLevel;
	private static DensityFunction finalDensity;
	private static NoiseRouter endNoiseRouter;

	private static void checkMerge() {
		if (temperature == null || vegetation == null || continents == null || erosion == null || depth == null || ridges == null || preliminarySurfaceLevel == null || finalDensity == null || endNoiseRouter == null)
			return;

		endNoiseRouter.temperature = temperature;
		endNoiseRouter.vegetation = vegetation;
		endNoiseRouter.continents = continents;
		endNoiseRouter.erosion = erosion;
		endNoiseRouter.depth = depth;
		endNoiseRouter.ridges = ridges;
		endNoiseRouter.preliminarySurfaceLevel = preliminarySurfaceLevel;
		endNoiseRouter.finalDensity = finalDensity;

		Stellarity.LOGGER.info("MERGED! This is an important checkpoint as it could corrupt worlds without it.");

	}


	public static void init() {
		DynamicRegistrySetupCallback.EVENT.register(registryView -> {
			registryView.registerEntryAdded(Registries.DENSITY_FUNCTION, (unused, id, densityFunction) -> {
				if (!id.getNamespace().equals(Stellarity.MOD_ID)) return;
				if (id.equals(CLIMATE_TEMPERATURE.identifier())) temperature = densityFunction;
				else if (id.equals(CLIMATE_HUMIDITY.identifier())) vegetation = densityFunction;
				else if (id.equals(CLIMATE_CONTINENTS.identifier())) continents = densityFunction;
				else if (id.equals(CLIMATE_EROSION.identifier())) erosion = densityFunction;
				else if (id.equals(CLIMATE_DEPTH.identifier())) depth = densityFunction;
				else if (id.equals(CLIMATE_RIDGES.identifier())) ridges = densityFunction;
				else if (id.equals(INITIAL_DENSITY.identifier())) preliminarySurfaceLevel = densityFunction;
				else if (id.equals(FINAL_DENSITY.identifier())) finalDensity = densityFunction;

				checkMerge();
			});

			registryView.registerEntryAdded(Registries.NOISE_SETTINGS, (unused, id, noiseSettings) -> {
				if (!id.equals(Stellarity.mcId("end"))) return;

				var noise = noiseSettings.noiseSettings();
				endNoiseRouter = noiseSettings.noiseRouter();
				noise.height = Math.max(noise.height(), 384);

				if (!Stellarity.hasBiolith())
					noiseSettings.surfaceRule = SurfaceRules.sequence(
						StellarityWorldgenData.stellaritySurfaceRules(registryView.asRegistryAccess().lookupOrThrow(Registries.BIOME)),
						StellarityWorldgenData.vanillaSurfaceRules(registryView.asRegistryAccess().lookupOrThrow(Registries.BIOME)),
						noiseSettings.surfaceRule
					);

				checkMerge();

				noiseSettings.disableMobGeneration = false;

			});

			registryView.registerEntryAdded(Registries.LEVEL_STEM, (unused, id, unused2) -> {
				if (id.equals(LevelStem.END.identifier()))
					Stellarity.LOGGER.warn("UNEXPECTED LEVEL STEM LOADED. Please use biolith for biome compat. {}", id);
			});


		});
	}
}
