package dev.coder2195.stellarity.event;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.util.WorldgenData;
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

import static dev.coder2195.stellarity.registry.StellarityDensityFunctions.*;

public class StellarityRegistryEntryModifications {
	private static DensityFunction temperature;
	private static DensityFunction vegetation;
	private static DensityFunction continents;
	private static DensityFunction erosion;
	private static DensityFunction depth;
	private static DensityFunction ridges;
	private static DensityFunction preliminarySurfaceLevel;
	private static DensityFunction nullscapePreliminarySurfaceLevel;
	private static DensityFunction finalDensity;
	private static DensityFunction nullscapeFinalDensity;
	private static NoiseRouter endNoiseRouter;
	private static ChunkGenerator chunkGenerator;
	private static Registry<Biome> biomeRegistry;
	private static NoiseGeneratorSettings cachedNoiseSettings;
	private static boolean surfaceRulesDone = false;
	private static int lastBiomeAdded;
	private static boolean nullscapeBiomes = false;

	private static void checkMerge() {
		if (temperature == null || vegetation == null || continents == null || erosion == null || depth == null || ridges == null || (preliminarySurfaceLevel == null && nullscapePreliminarySurfaceLevel == null) || (finalDensity == null && nullscapeFinalDensity == null) || endNoiseRouter == null)
			return;

		endNoiseRouter.temperature = temperature;
		endNoiseRouter.vegetation = vegetation;
		endNoiseRouter.continents = continents;
		endNoiseRouter.erosion = erosion;
		endNoiseRouter.depth = depth;
		endNoiseRouter.ridges = ridges;

		boolean usedNullscape = nullscapeFinalDensity != null && nullscapePreliminarySurfaceLevel != null;
		endNoiseRouter.preliminarySurfaceLevel = nullscapePreliminarySurfaceLevel == null ? preliminarySurfaceLevel : nullscapePreliminarySurfaceLevel;
		endNoiseRouter.finalDensity = nullscapeFinalDensity == null ? finalDensity : nullscapeFinalDensity;

		Stellarity.LOGGER.info("MERGED! This is an important checkpoint as it could corrupt worlds without it. Used Nullscape: {}", usedNullscape);
	}

	public static void resetState() {
		temperature = null;
		vegetation = null;
		continents = null;
		erosion = null;
		depth = null;
		ridges = null;
		nullscapePreliminarySurfaceLevel = null;
		nullscapeFinalDensity = null;
		preliminarySurfaceLevel = null;
		finalDensity = null;
		endNoiseRouter = null;
		chunkGenerator = null;
		cachedNoiseSettings = null;
		surfaceRulesDone = false;
	}


	public static void init() {
		DynamicRegistrySetupCallback.EVENT.register(registryView -> {
			StellarityRegistryEntryModifications.resetState();

			registryView.registerEntryAdded(Registries.DENSITY_FUNCTION, (_, id, densityFunction) -> {
				var namespace = id.getNamespace();
				if (!(namespace.equals(Stellarity.MOD_ID) || namespace.equals("nullscape_compat"))) return;
				if (id.equals(CLIMATE_TEMPERATURE.identifier())) temperature = densityFunction;
				else if (id.equals(CLIMATE_HUMIDITY.identifier())) vegetation = densityFunction;
				else if (id.equals(CLIMATE_CONTINENTS.identifier())) continents = densityFunction;
				else if (id.equals(CLIMATE_EROSION.identifier())) erosion = densityFunction;
				else if (id.equals(CLIMATE_DEPTH.identifier())) depth = densityFunction;
				else if (id.equals(CLIMATE_RIDGES.identifier())) ridges = densityFunction;
				else if (id.equals(NULLSCAPE_COMPAT_INITIAL_DENSITY.identifier())) {
					Stellarity.LOGGER.info("Nullscape detected, pulling nullscape initial density");
					nullscapePreliminarySurfaceLevel = densityFunction;
				} else if (id.equals(NULLSCAPE_COMPAT_FINAL_DENSITY.identifier())) {
					Stellarity.LOGGER.info("Nullscape detected, pulling nullscape final density");
					nullscapeFinalDensity = densityFunction;
				} else if (id.equals(INITIAL_DENSITY.identifier())) preliminarySurfaceLevel = densityFunction;
				else if (id.equals(FINAL_DENSITY.identifier())) finalDensity = densityFunction;

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
					chunkGenerator.biomeSource = WorldgenData.stellarityBiomeSource(biomeRegistry, nullscapeBiomes);
					Stellarity.LOGGER.info("adding biomes (level stem)");
				}
			});

			registryView.registerEntryAdded(Registries.BIOME, (i, id, biome) -> {
				biomeRegistry = registryView.asRegistryAccess().lookupOrThrow(Registries.BIOME);

				if (i < lastBiomeAdded) {
					lastBiomeAdded = i;
					nullscapeBiomes = false;
				}

				if (id.getNamespace().equals("nullscape")) {
					nullscapeBiomes = true;
				}

				if (chunkGenerator != null && !Stellarity.hasBiolith()) {
					chunkGenerator.biomeSource = WorldgenData.stellarityBiomeSource(biomeRegistry, nullscapeBiomes);
					Stellarity.LOGGER.info("adding biomes (biome registry)");
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
