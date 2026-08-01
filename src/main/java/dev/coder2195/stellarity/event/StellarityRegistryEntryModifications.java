package dev.coder2195.stellarity.event;

import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.SurfaceRules;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.util.WorldgenData;

import static dev.coder2195.stellarity.registry.StellarityDensityFunctions.*;

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
	private static boolean nullscapeCompatActive = false;
	private static ChunkGenerator chunkGenerator;
	private static Registry<Biome> biomeRegistry;
	private static NoiseGeneratorSettings cachedNoiseSettings;
	private static boolean surfaceRulesDone = false;

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
			temperature = null;
			vegetation = null;
			continents = null;
			erosion = null;
			depth = null;
			ridges = null;
			preliminarySurfaceLevel = null;
			finalDensity = null;
			endNoiseRouter = null;
			nullscapeCompatActive = false;
			chunkGenerator = null;
			biomeRegistry = null;
			cachedNoiseSettings = null;
			surfaceRulesDone = false;

			registryView.registerEntryAdded(Registries.DENSITY_FUNCTION, (_, id, densityFunction) -> {
				var namespace = id.getNamespace();
				if (!(namespace.equals(Stellarity.MOD_ID) || namespace.equals("nullscape_compat")) ) return;
				if (id.equals(CLIMATE_TEMPERATURE.identifier())) temperature = densityFunction;
				else if (id.equals(CLIMATE_HUMIDITY.identifier())) vegetation = densityFunction;
				else if (id.equals(CLIMATE_CONTINENTS.identifier())) continents = densityFunction;
				else if (id.equals(CLIMATE_EROSION.identifier())) erosion = densityFunction;
				else if (id.equals(CLIMATE_DEPTH.identifier())) depth = densityFunction;
				else if (id.equals(CLIMATE_RIDGES.identifier())) ridges = densityFunction;
				else if (id.equals(NULLSCAPE_COMPAT_INITIAL_DENSITY.identifier())) {
					nullscapeCompatActive = true;
					Stellarity.LOGGER.info("Nullscape detected, pulling nullscape initial density");
					preliminarySurfaceLevel = densityFunction;
				}
				else if (id.equals(NULLSCAPE_COMPAT_FINAL_DENSITY.identifier())) {
					nullscapeCompatActive = true;
					Stellarity.LOGGER.info("Nullscape detected, pulling nullscape final density");
					finalDensity = densityFunction;
				}
				else if (id.equals(INITIAL_DENSITY.identifier()) && !nullscapeCompatActive) preliminarySurfaceLevel = densityFunction;
				else if (id.equals(FINAL_DENSITY.identifier()) && !nullscapeCompatActive) finalDensity = densityFunction;

				checkMerge();
			});

			registryView.registerEntryAdded(Registries.NOISE_SETTINGS, (_, id, noiseSettings) -> {
				if (!id.equals(Stellarity.mcId("end"))) return;

				cachedNoiseSettings = noiseSettings;
				var noise = noiseSettings.noiseSettings();
				endNoiseRouter = noiseSettings.noiseRouter();
				noise.height = Math.max(noise.height(), 384);

				if (!Stellarity.hasBiolith() && !surfaceRulesDone) {
					try {
						noiseSettings.surfaceRule = SurfaceRules.sequence(
							WorldgenData.stellaritySurfaceRules(registryView.asRegistryAccess().lookupOrThrow(Registries.BIOME)),
							WorldgenData.vanillaSurfaceRules(registryView.asRegistryAccess().lookupOrThrow(Registries.BIOME)),
							noiseSettings.surfaceRule
						);

						surfaceRulesDone = true;

						Stellarity.LOGGER.info("biome registry is mature for surface rules (noise settings)");
					} catch (Exception e) {
						Stellarity.LOGGER.warn("biome registry is not mature for surface rules (noise settings), skipping");
					}

				}

				checkMerge();

				noiseSettings.disableMobGeneration = false;

			});

			registryView.registerEntryAdded(Registries.LEVEL_STEM, (_, id, levelStem) -> {
				if (!id.equals(LevelStem.END.identifier()) || Stellarity.hasBiolith()) return;

				chunkGenerator = levelStem.generator();
				if (biomeRegistry != null) {
					chunkGenerator.biomeSource = WorldgenData.stellarityBiomeSource(biomeRegistry);
				}
			});

			registryView.registerEntryAdded(Registries.BIOME, (_, id, biome) -> {
				biomeRegistry = registryView.asRegistryAccess().lookupOrThrow(Registries.BIOME);
				if (chunkGenerator != null && !Stellarity.hasBiolith()) {
					chunkGenerator.biomeSource = WorldgenData.stellarityBiomeSource(biomeRegistry);
				}

				if (cachedNoiseSettings != null && !Stellarity.hasBiolith() && !surfaceRulesDone)
					try {
						cachedNoiseSettings.surfaceRule = SurfaceRules.sequence(
							WorldgenData.stellaritySurfaceRules(registryView.asRegistryAccess().lookupOrThrow(Registries.BIOME)),
							WorldgenData.vanillaSurfaceRules(registryView.asRegistryAccess().lookupOrThrow(Registries.BIOME)),
							cachedNoiseSettings.surfaceRule
						);
						Stellarity.LOGGER.info("biome registry is mature for surface rules (biome)");
						surfaceRulesDone = true;
					} catch (Exception e) {
						Stellarity.LOGGER.warn("biome registry is not mature for surface rules (biome), skipping");
					}
			});


		});
	}
}
