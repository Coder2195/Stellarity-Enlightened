package dev.coder2195.stellarity.event;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.mixin.accessor.ChunkGeneratorAccessor;
import dev.coder2195.stellarity.mixin.accessor.NoiseGeneratorSettingsAccessor;
import dev.coder2195.stellarity.mixin.accessor.NoiseRouterAccessor;
import dev.coder2195.stellarity.mixin.accessor.NoiseSettingsAccessor;
import dev.coder2195.stellarity.util.WorldgenData;
import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.TheEndBiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import net.minecraft.world.level.levelgen.material.MaterialRules;
import org.jspecify.annotations.Nullable;

import static dev.coder2195.stellarity.registry.StellarityDensityFunctions.*;

public class StellarityRegistryEntryModifications {
	public static @Nullable DensityFunction temperature;
	public static @Nullable DensityFunction vegetation;
	public static @Nullable DensityFunction continents;
	public static @Nullable DensityFunction erosion;
	public static @Nullable DensityFunction depth;
	public static @Nullable DensityFunction ridges;
	public static @Nullable DensityFunction initialDensity;
	public static @Nullable DensityFunction nullscapeInitialDensity;
	public static @Nullable DensityFunction finalDensity;
	public static @Nullable DensityFunction nullscapeFinalDensity;
	public static @Nullable NoiseRouter endNoiseRouter;
	public static @Nullable ChunkGenerator chunkGenerator;
	public static @Nullable Registry<Biome> biomeRegistry;
	public static @Nullable NoiseGeneratorSettings cachedNoiseSettings;
	public static boolean surfaceRulesDone = false;
	public static int lastBiomeAdded = 100;
	public static boolean nullscapeBiomes = false;

	private static void checkMerge() {
		if (temperature == null || vegetation == null || continents == null || erosion == null || depth == null || ridges == null || (initialDensity == null && nullscapeInitialDensity == null) || (finalDensity == null && nullscapeFinalDensity == null) || endNoiseRouter == null)
			return;
		
		NoiseRouterAccessor routerAccessor = (NoiseRouterAccessor) (Object) endNoiseRouter;

		routerAccessor.stellarity$setTemperature(temperature);
		routerAccessor.stellarity$setVegetation(vegetation);
		routerAccessor.stellarity$setContinents(continents);
		routerAccessor.stellarity$setErosion(erosion);
		routerAccessor.stellarity$setDepth(depth);
		routerAccessor.stellarity$setRidges(ridges);

		boolean usedNullscape = nullscapeFinalDensity != null && nullscapeInitialDensity != null;
		routerAccessor.stellarity$setChunkSurfaceLevel(nullscapeInitialDensity == null ? initialDensity : nullscapeInitialDensity);
		routerAccessor.stellarity$setFinalDensity(nullscapeFinalDensity == null ? finalDensity : nullscapeFinalDensity);

		Stellarity.LOGGER.info("MERGED! This is an important checkpoint as it could corrupt worlds without it. Used Nullscape: {}", usedNullscape);
	}

	public static void resetState() {
//		temperature = null;
//		vegetation = null;
//		continents = null;
//		erosion = null;
//		depth = null;
//		ridges = null;
//		nullscapeInitialDensity = null;
//		nullscapeFinalDensity = null;
//		initialDensity = null;
//		finalDensity = null;
//		endNoiseRouter = null;
//		chunkGenerator = null;
//		cachedNoiseSettings = null;
//		surfaceRulesDone = false;
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
					nullscapeInitialDensity = densityFunction;
				} else if (id.equals(NULLSCAPE_COMPAT_FINAL_DENSITY.identifier())) {
					Stellarity.LOGGER.info("Nullscape detected, pulling nullscape final density");
					nullscapeFinalDensity = densityFunction;
				} else if (id.equals(INITIAL_DENSITY.identifier())) initialDensity = densityFunction;
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

						generatorSettingsAccessor.stellarity$setMaterialRule(Holder.direct(
							MaterialRules.sequence(
								WorldgenData.stellarityMaterialRules(registryView.asRegistryAccess().lookupOrThrow(Registries.BIOME)),
								WorldgenData.vanillaMaterialRules(registryView.asRegistryAccess().lookupOrThrow(Registries.BIOME)),
								generatorSettings.materialRule().value()
							)
						));

						surfaceRulesDone = true;

						Stellarity.LOGGER.info("biome registry is mature for surface rules (noise settings)");
					} catch (Exception e) {
						Stellarity.LOGGER.warn("biome registry is not mature for surface rules (noise settings), skipping");
					}

				}

				checkMerge();

				generatorSettingsAccessor.stellarity$setDisableMobGeneration(false);
				generatorSettingsAccessor.stellarity$setUseLegacyRandomSource(false);
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
						cachedNoiseSettingsAccessor.stellarity$setMaterialRule(Holder.direct(MaterialRules.sequence(
							WorldgenData.stellarityMaterialRules(registryView.asRegistryAccess().lookupOrThrow(Registries.BIOME)),
							WorldgenData.vanillaMaterialRules(registryView.asRegistryAccess().lookupOrThrow(Registries.BIOME)),
							cachedNoiseSettings.materialRule().value()
						)));
						Stellarity.LOGGER.info("biome registry is mature for surface rules (biome)");
						surfaceRulesDone = true;
					} catch (Exception e) {
						Stellarity.LOGGER.warn("biome registry is not mature for surface rules (biome), skipping");
					}
			});


		});
	}
}
