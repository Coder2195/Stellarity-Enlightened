package dev.coder2195.stellarity.event;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.mixin.accessor.ChunkGeneratorAccessor;
import dev.coder2195.stellarity.mixin.accessor.NoiseGeneratorSettingsAccessor;
import dev.coder2195.stellarity.mixin.accessor.NoiseRouterAccessor;
import dev.coder2195.stellarity.mixin.accessor.NoiseSettingsAccessor;
import dev.coder2195.stellarity.util.WorldgenData;
import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.TheEndBiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;

import static dev.coder2195.stellarity.registry.StellarityDensityFunctions.*;

public class StellarityRegistryEntryModifications {
	public static DensityFunction temperature;
	public static DensityFunction vegetation;
	public static DensityFunction continents;
	public static DensityFunction erosion;
	public static DensityFunction depth;
	public static DensityFunction ridges;
	public static DensityFunction preliminarySurfaceLevel;
	public static DensityFunction nullscapePreliminarySurfaceLevel;
	public static DensityFunction finalDensity;
	public static DensityFunction nullscapeFinalDensity;
	public static NoiseRouter endNoiseRouter;
	public static ChunkGenerator chunkGenerator;
	public static Registry<Biome> biomeRegistry;
	public static NoiseGeneratorSettings cachedNoiseSettings;
	public static boolean surfaceRulesDone = false;
	public static int lastBiomeAdded = 100;
	public static boolean nullscapeBiomes = false;

	private static void checkMerge() {
		if (temperature == null || vegetation == null || continents == null || erosion == null || depth == null || ridges == null || (preliminarySurfaceLevel == null && nullscapePreliminarySurfaceLevel == null) || (finalDensity == null && nullscapeFinalDensity == null) || endNoiseRouter == null)
			return;
		
		NoiseRouterAccessor routerAccessor = (NoiseRouterAccessor) (Object) endNoiseRouter;

		routerAccessor.stellarity$setTemperature(temperature);
		routerAccessor.stellarity$setVegetation(vegetation);
		routerAccessor.stellarity$setContinents(continents);
		routerAccessor.stellarity$setErosion(erosion);
		routerAccessor.stellarity$setDepth(depth);
		routerAccessor.stellarity$setRidges(ridges);

		boolean usedNullscape = nullscapeFinalDensity != null && nullscapePreliminarySurfaceLevel != null;
		routerAccessor.stellarity$setPreliminarySurfaceLevel(nullscapePreliminarySurfaceLevel == null ? preliminarySurfaceLevel : nullscapePreliminarySurfaceLevel);
		routerAccessor.stellarity$setFinalDensity(nullscapeFinalDensity == null ? finalDensity : nullscapeFinalDensity);

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

			registryView.registerEntryAdded(Registries.NOISE_SETTINGS, (_, id, generatorSettings) -> {
				if (!id.equals(Stellarity.mcId("end"))) return;

				cachedNoiseSettings = generatorSettings;
				var noise = generatorSettings.noiseSettings();
				endNoiseRouter = generatorSettings.noiseRouter();
				((NoiseSettingsAccessor) (Object) noise).stellarity$setHeight(Math.max(noise.height(), 384));

				//noinspection DataFlowIssue
				var generatorSettingsAccessor = ((NoiseGeneratorSettingsAccessor) (Object) generatorSettings);

				if (!Stellarity.hasBiolith() && !surfaceRulesDone) {
					try {

						generatorSettingsAccessor.stellarity$setSurfaceRule(SurfaceRules.sequence(
							WorldgenData.stellaritySurfaceRules(registryView.asRegistryAccess().lookupOrThrow(Registries.BIOME)),
							WorldgenData.vanillaSurfaceRules(registryView.asRegistryAccess().lookupOrThrow(Registries.BIOME)),
							generatorSettings.surfaceRule()
						));

						surfaceRulesDone = true;

						Stellarity.LOGGER.info("biome registry is mature for surface rules (noise settings)");
					} catch (Exception e) {
						Stellarity.LOGGER.warn("biome registry is not mature for surface rules (noise settings), skipping");
					}

				}

				checkMerge();

				generatorSettingsAccessor.stellarity$setDisableMobGeneration(false);
			});

			registryView.registerEntryAdded(Registries.LEVEL_STEM, (_, id, levelStem) -> {
				if (!id.equals(LevelStem.END.identifier())) return;

				chunkGenerator = levelStem.generator();
				if (biomeRegistry != null) {
					((ChunkGeneratorAccessor) chunkGenerator).stellarity$setBiomeSource(Stellarity.hasBiolith() ? TheEndBiomeSource.create(biomeRegistry) : WorldgenData.stellarityBiomeSource(biomeRegistry, nullscapeBiomes));
					Stellarity.LOGGER.info("adding biomes (level stem)");
				}
			});

			registryView.registerEntryAdded(Registries.BIOME, (i, id, biome) -> {
				biomeRegistry = registryView.asRegistryAccess().lookupOrThrow(Registries.BIOME);

				if (i < lastBiomeAdded) {
					nullscapeBiomes = false;
				}

				lastBiomeAdded = i;

				if (id.getNamespace().equals("nullscape")) {
					nullscapeBiomes = true;
				}

				if (chunkGenerator != null) {
					((ChunkGeneratorAccessor) chunkGenerator).stellarity$setBiomeSource(Stellarity.hasBiolith() ? TheEndBiomeSource.create(biomeRegistry) : WorldgenData.stellarityBiomeSource(biomeRegistry, nullscapeBiomes));

					Stellarity.LOGGER.info("adding biomes (biome registry)");
				}

				if (cachedNoiseSettings != null && !Stellarity.hasBiolith() && !surfaceRulesDone)
					try {
						//noinspection DataFlowIssue
						var cachedNoiseSettingsAccessor = (NoiseGeneratorSettingsAccessor) (Object) cachedNoiseSettings;
						cachedNoiseSettingsAccessor.stellarity$setSurfaceRule(SurfaceRules.sequence(
							WorldgenData.stellaritySurfaceRules(registryView.asRegistryAccess().lookupOrThrow(Registries.BIOME)),
							WorldgenData.vanillaSurfaceRules(registryView.asRegistryAccess().lookupOrThrow(Registries.BIOME)),
							cachedNoiseSettings.surfaceRule()
						));
						Stellarity.LOGGER.info("biome registry is mature for surface rules (biome)");
						surfaceRulesDone = true;
					} catch (Exception e) {
						Stellarity.LOGGER.warn("biome registry is not mature for surface rules (biome), skipping");
					}
			});


		});
	}
}
