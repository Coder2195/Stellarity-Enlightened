package prismatic.shards.stellarity.datagen.dynamic;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.attribute.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import prismatic.shards.stellarity.key.StellarityConfiguredCarvers;
import prismatic.shards.stellarity.registry.StellarityEntityTypes;
import prismatic.shards.stellarity.registry.StellaritySoundEvents;

import java.util.List;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static net.minecraft.core.Holder.direct;
import static net.minecraft.data.worldgen.placement.NetherPlacements.BASALT_PILLAR;
import static net.minecraft.data.worldgen.placement.VegetationPlacements.PATCH_BUSH;
import static net.minecraft.data.worldgen.placement.VegetationPlacements.PATCH_DRY_GRASS_DESERT;
import static prismatic.shards.stellarity.key.StellarityBiomes.*;
import static prismatic.shards.stellarity.key.StellarityPlacedFeatures.*;


public interface BiomeProvider {
	static void bootstrap(BootstrapContext<Biome> context) {
		var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
		var worldCarvers = context.lookup(Registries.CONFIGURED_CARVER);

		context.register(AMETHYST_FOREST, new Biome.BiomeBuilder()
			.temperature(0.8f).downfall(0.4f).hasPrecipitation(false)
			.setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0x3f473f)
			.setAttribute(EnvironmentAttributes.SKY_COLOR, 0)
			.setAttribute(EnvironmentAttributes.FOG_COLOR, 0)
			.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0)
			.setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
				of(StellaritySoundEvents.AMBIENT_THE_END_HEAVENLY_GRIM),
				of(new AmbientMoodSettings(direct(SoundEvents.AMBIENT_UNDERWATER_LOOP_ADDITIONS_RARE), 1000, 4, 2)),
				List.of(new AmbientAdditionsSettings(direct(SoundEvents.AMBIENT_UNDERWATER_LOOP_ADDITIONS_ULTRA_RARE), 0.001))
			)).specialEffects(new BiomeSpecialEffects(0xf3d1ff, of(0xd494ff), empty(), of(0xdeadff), BiomeSpecialEffects.GrassColorModifier.NONE))
			.mobSpawnSettings(new MobSpawnSettings.Builder()
				.addSpawn(MobCategory.MONSTER, 8, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 4, 4))
				.addSpawn(MobCategory.CREATURE, 12, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 1, 4))
				.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.PIG, 1, 4))
				.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 1, 4))
				.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.COW, 1, 4))
				.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 1, 4))
				.creatureGenerationProbability(.4f)

				.build()
			).generationSettings(new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, AMETHYST_FOREST_CALCITE_BOTTOM)
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, AMETHYST_FOREST_AMETHYST_GEODES)
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, AMETHYST_FOREST_TUFF_ROCKS)
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, AMETHYST_FOREST_OBSIDIAN)
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, AMETHYST_FOREST_DIRT)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AMETHYST_FOREST_TREES)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AMETHYST_FOREST_CRYSTAL_GRASS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AMETHYST_FOREST_FLOWERS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AMETHYST_FOREST_ROOTS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PATCH_BUSH)
				.build()
			).build()
		);

		context.register(ASHFALL_DELTAS, new Biome.BiomeBuilder()
			.temperature(0.8f).downfall(0.9f).hasPrecipitation(false)
			.setAttribute(EnvironmentAttributes.SKY_COLOR, 0)
			.setAttribute(EnvironmentAttributes.FOG_COLOR, 0)
			.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0xc4c4cf)
			.setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
				of(StellaritySoundEvents.AMBIENT_THE_END_DARK),
				of(new AmbientMoodSettings(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 1250, 3, 2)),
				List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0033))
			))
			.setAttribute(EnvironmentAttributes.SURFACE_SLIME_SPAWN_CHANCE, 0.4f)
			.setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(
				ParticleTypes.WHITE_ASH, 0.01f
			)))
			.setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0x3f473f)
			.specialEffects(new BiomeSpecialEffects(
				0xe5eeff, of(0xc2c2c2), empty(), of(0xdedede), BiomeSpecialEffects.GrassColorModifier.NONE
			))
			.mobSpawnSettings(new MobSpawnSettings.Builder().creatureGenerationProbability(0.4f)
				.addSpawn(MobCategory.MONSTER, 11, new MobSpawnSettings.SpawnerData(StellarityEntityTypes.VOIDED_SKELETON, 2, 2))
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(EntityType.FROG, 1, 4))
				.addSpawn(MobCategory.MONSTER, 20, new MobSpawnSettings.SpawnerData(StellarityEntityTypes.VOIDED_SLIME, 1, 2))
				.creatureGenerationProbability(0.2f)
				.build())
			.generationSettings(new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, GLOBAL_STALACTITES)
				.addFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, ASHFALL_DELTAS_WATER_DELTAS)
				.addFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, ASHFALL_DELTAS_GRASS_DELTAS)
				.addFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, ASHFALL_DELTAS_BASALT_COLUMNS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ASHFALL_DELTAS_SEAGRASS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ASHFALL_DELTAS_TREES)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ASHFALL_DELTAS_VEGETATION)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PATCH_BUSH)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, ASHFALL_DELTAS_MAGMA)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, ASHFALL_DELTAS_ASH_PILES)
				.build()
			).build());

		context.register(CRYSTAL_CRAGS, new Biome.BiomeBuilder()
			.temperature(0.5f).downfall(0.5f).hasPrecipitation(false)
			.setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0x3f473f)
			.setAttribute(EnvironmentAttributes.SKY_COLOR, 0)
			.setAttribute(EnvironmentAttributes.FOG_COLOR, 0)
			.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x5d2a6f)
			.setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
				of(StellaritySoundEvents.AMBIENT_THE_END_HEAVENLY_GRIM),
				of(new AmbientMoodSettings(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 1100, 6, 2)),
				List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_BASALT_DELTAS_ADDITIONS, 0.0111))
			))
			.setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.DRIPPING_OBSIDIAN_TEAR, 0.00022f)))
			.specialEffects(new BiomeSpecialEffects(0xe499ff, of(0xdd90fe), empty(), of(0xdd90fe), BiomeSpecialEffects.GrassColorModifier.NONE))
			.mobSpawnSettings(new MobSpawnSettings.Builder()
				.addSpawn(MobCategory.MONSTER, 20, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 4, 4))
				.addMobCharge(EntityType.ENDERMAN, 0.7, 1)
				.build())
			.generationSettings(new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, GLOBAL_STALACTITES)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, CRYSTAL_CRAGS_HILLS)
				.addFeature(GenerationStep.Decoration.LAKES, CRYSTAL_CRAGS_CRYSTAL_ROOTS)
				.addFeature(GenerationStep.Decoration.LAKES, CRYSTAL_CRAGS_AMETHYST_CRYSTALS)
				.addFeature(GenerationStep.Decoration.LAKES, CRYSTAL_CRAGS_AMETHYST_DELTAS)
				.addFeature(GenerationStep.Decoration.LAKES, CRYSTAL_CRAGS_GRASS_DELTAS)
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, CRYSTAL_CRAGS_BUDDING_AMETHYST_ORE)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CRYSTAL_CRAGS_CHORUS_PLANTS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CRYSTAL_CRAGS_CRYSTAL_GRASS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CRYSTAL_CRAGS_GRASS)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_DUNGEONS)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_FOSSILS)
				.build()
			).build()
		);

		var shrubLandWildsEntities = new MobSpawnSettings.Builder()
			.addSpawn(MobCategory.MONSTER, 60, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 4, 4))
			.addSpawn(MobCategory.MONSTER, 30, new MobSpawnSettings.SpawnerData(StellarityEntityTypes.VOIDED_ZOMBIE, 4, 4))
			.addSpawn(MobCategory.MONSTER, 10, new MobSpawnSettings.SpawnerData(EntityType.PHANTOM, 4, 4))
			.addSpawn(MobCategory.CREATURE, 12, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 1, 4))
			.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.PIG, 1, 4))
			.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 1, 4))
			.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.COW, 1, 4))
			.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 1, 4))
			.addMobCharge(EntityType.ENDERMAN, 0.7, 1)
			.addMobCharge(StellarityEntityTypes.VOIDED_ZOMBIE, 0.7, 1)
			.addMobCharge(EntityType.PHANTOM, 0.7, 1)
			.creatureGenerationProbability(.4f)

			.build();
		context.register(END_SHRUBLAND, new Biome.BiomeBuilder()
			.temperature(0.8f).downfall(0.4f).hasPrecipitation(false)
			.setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0x3f472f)
			.setAttribute(EnvironmentAttributes.SKY_COLOR, 0)
			.setAttribute(EnvironmentAttributes.FOG_COLOR, 0)
			.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x041f33)
			.setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.SPORE_BLOSSOM_AIR, 0.002f)))
			.setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
				of(StellaritySoundEvents.AMBIENT_THE_END_HEAVENLY_GRIM),
				of(new AmbientMoodSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 1000, 2, 1)),
				List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111))
			)).specialEffects(new BiomeSpecialEffects(0x43d5ee, of(0xffed75), empty(), of(0xfaf389), BiomeSpecialEffects.GrassColorModifier.NONE))
			.mobSpawnSettings(shrubLandWildsEntities).generationSettings(new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, GLOBAL_STALACTITES)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, END_WILDS_DIRT)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, END_WILDS_ROOTS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_WILDS_TREES)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_WILDS_FOREST)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_WILDS_CHORUS_PLANTS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_SHRUBLAND_GRASS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_SHRUBLAND_SHRUBS)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_DUNGEONS)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_FOSSILS)
				.build()
			).build()
		);

		//noinspection DuplicatedCode
		context.register(END_WILDS, new Biome.BiomeBuilder()
			.temperature(0.8f).downfall(0.4f).hasPrecipitation(false)
			.setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0x3f472f)
			.setAttribute(EnvironmentAttributes.SKY_COLOR, 0)
			.setAttribute(EnvironmentAttributes.FOG_COLOR, 0)
			.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x041f33)
			.setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
				of(StellaritySoundEvents.AMBIENT_THE_END_DARK),
				of(new AmbientMoodSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 1000, 2, 1)),
				List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111))
			)).setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.SPORE_BLOSSOM_AIR, 0.001f)))
			.specialEffects(new BiomeSpecialEffects(0x43d5ee, of(0xfed234), empty(), of(0xfed234), BiomeSpecialEffects.GrassColorModifier.NONE))
			.mobSpawnSettings(shrubLandWildsEntities).generationSettings(new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, GLOBAL_STALACTITES)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, END_WILDS_DIRT)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, END_WILDS_ROOTS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_WILDS_TREES)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_WILDS_FOREST)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_WILDS_CHORUS_PLANTS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_WILDS_GRASS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_WILDS_BUSHES)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_DUNGEONS)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_FOSSILS)
				.build()
			).build()
		);

		context.register(ENDER_WASTES, new Biome.BiomeBuilder()
			.temperature(0.5f).downfall(0.5f).hasPrecipitation(false)
			.setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0x3f473f)
			.setAttribute(EnvironmentAttributes.SKY_COLOR, 0)
			.setAttribute(EnvironmentAttributes.FOG_COLOR, 0)
			.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x302947)
			.setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
				of(StellaritySoundEvents.AMBIENT_THE_END_DARK),
				of(new AmbientMoodSettings(SoundEvents.AMBIENT_CAVE, 1000, 4, 2)),
				List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111))
			)).specialEffects(new BiomeSpecialEffects(0x504771, of(0x553a5f), empty(), of(0x4c3654), BiomeSpecialEffects.GrassColorModifier.NONE))
			.mobSpawnSettings(new MobSpawnSettings.Builder()
				.addSpawn(MobCategory.MONSTER, 40, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 4, 4))
				.addSpawn(MobCategory.MONSTER, 5, new MobSpawnSettings.SpawnerData(StellarityEntityTypes.VOIDED_SKELETON, 2, 3))
				.addMobCharge(EntityType.ENDERMAN, 0.6, 1)
				.addMobCharge(StellarityEntityTypes.VOIDED_SKELETON, 0.6, 1)
				.build()
			).generationSettings(new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, GLOBAL_STALACTITES)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, ENDER_WASTES_HILLS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ENDER_WASTES_CHORUS_PLANTS)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_DUNGEONS)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_FOSSILS)
				.build()
			).build()
		);

		context.register(ENDLESS_DUNES, new Biome.BiomeBuilder()
			.temperature(0.8f).downfall(0.4f).hasPrecipitation(false)
			.setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0x3f472f)
			.setAttribute(EnvironmentAttributes.SKY_COLOR, 0x78a7ff)
			.setAttribute(EnvironmentAttributes.FOG_COLOR, 0xc0d8ff)
			.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x041f33)
			.setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
				of(StellaritySoundEvents.AMBIENT_THE_END_HEAVENLY_GRIM),
				of(new AmbientMoodSettings(SoundEvents.AMBIENT_CAVE, 1000, 4, 2)),
				List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_CAVE, 0.001))
			))
			.setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.WHITE_ASH, 0.0011f)))
			.specialEffects(new BiomeSpecialEffects(0x43d5ee, of(0x75ae1c), empty(), of(0x91bf4a), BiomeSpecialEffects.GrassColorModifier.NONE))
			.mobSpawnSettings(new MobSpawnSettings.Builder()
				.addSpawn(MobCategory.MONSTER, 80, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 4, 4))
				.addSpawn(MobCategory.MONSTER, 30, new MobSpawnSettings.SpawnerData(StellarityEntityTypes.VOIDED_ZOMBIE, 4, 4))
				.addSpawn(MobCategory.MONSTER, 15, new MobSpawnSettings.SpawnerData(StellarityEntityTypes.VOIDED_SKELETON, 4, 4))
				.addMobCharge(EntityType.ENDERMAN, 1, 0.3)
				.addMobCharge(StellarityEntityTypes.VOIDED_ZOMBIE, 1, 0.14)
				.addMobCharge(StellarityEntityTypes.VOIDED_SKELETON, 1, 0.15)
				.build()
			).generationSettings(new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
				.addCarver(StellarityConfiguredCarvers.RAVINE)
				.addCarver(StellarityConfiguredCarvers.CRACK)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, GLOBAL_STALACTITES)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, ENDLESS_DUNES_OASIS)
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, ENDLESS_DUNES_SAND_DELTAS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ENDLESS_DUNES_VEGETATION)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PATCH_DRY_GRASS_DESERT)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ENDLESS_DUNES_GRASS)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_DUNGEONS)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_FOSSILS)
				.build()
			).build()
		);

		context.register(FIERY_HILLS, new Biome.BiomeBuilder()
			.temperature(0.5f).downfall(0.5f).hasPrecipitation(false)
			.setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0x3f472f)
			.setAttribute(EnvironmentAttributes.SKY_COLOR, 0x000000)
			.setAttribute(EnvironmentAttributes.FOG_COLOR, 0x000000)
			.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x041f33)
			.setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
				of(StellaritySoundEvents.AMBIENT_THE_END_DARK),
				of(new AmbientMoodSettings(SoundEvents.AMBIENT_NETHER_WASTES_MOOD, 820, 8, 2)),
				List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_BASALT_DELTAS_ADDITIONS, 0.005))
			))
			.setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.WHITE_ASH, 0.0055f)))
			.specialEffects(new BiomeSpecialEffects(0x43d5ee, of(0xff772e), empty(), of(0xff772e), BiomeSpecialEffects.GrassColorModifier.NONE))
			.mobSpawnSettings(new MobSpawnSettings.Builder()
				.addSpawn(MobCategory.MONSTER, 20, new MobSpawnSettings.SpawnerData(EntityType.MAGMA_CUBE, 1, 2))
				.addMobCharge(EntityType.MAGMA_CUBE, 1, 0.18)
				.build()
			).generationSettings(new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, GLOBAL_STALACTITES)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, FIERY_HILLS_HILLS)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, FIERY_HILLS_BLACKSTONE_HILLS)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, FIERY_HILLS_BASALT_HILLS)
				.addFeature(GenerationStep.Decoration.LAKES, FIERY_HILLS_LAVA_DELTAS)
				.addFeature(GenerationStep.Decoration.LAKES, FIERY_HILLS_SAND_DELTAS)
				.addFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, FIERY_HILLS_GOLD_ORE)
				.addFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, FIERY_HILLS_MAGMA_ORE)
				.addFeature(GenerationStep.Decoration.FLUID_SPRINGS, FIERY_HILLS_SAND)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FIERY_HILLS_VENTS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FIERY_HILLS_FIRE)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FIERY_HILLS_TREES)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FIERY_HILLS_VEGETATION)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_DUNGEONS)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_FOSSILS)
				.build()
			).build()
		);

		context.register(FLESH_TUNDRA, new Biome.BiomeBuilder()
			.temperature(0.8f).downfall(0.4f).hasPrecipitation(false)
			.setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0x3f472f)
			.setAttribute(EnvironmentAttributes.SKY_COLOR, 0x000000)
			.setAttribute(EnvironmentAttributes.FOG_COLOR, 0x000000)
			.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0xff0000)
			.setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
				of(StellaritySoundEvents.AMBIENT_THE_END_DARK),
				of(new AmbientMoodSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 3000, 10, 100)),
				List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_WARPED_FOREST_ADDITIONS, 0.00111))
			))
			.setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.CRIMSON_SPORE, 0.0056f)))
			.specialEffects(new BiomeSpecialEffects(0xff0000, of(0xc43b3b), empty(), of(0xc43b3b), BiomeSpecialEffects.GrassColorModifier.NONE))
			.mobSpawnSettings(new MobSpawnSettings.Builder()
				.addSpawn(MobCategory.MONSTER, 7, new MobSpawnSettings.SpawnerData(StellarityEntityTypes.VOIDED_SILVERFISH, 2, 2))
				.addSpawn(MobCategory.MONSTER, 15, new MobSpawnSettings.SpawnerData(StellarityEntityTypes.VOIDED_SKELETON, 2, 2))
				.addSpawn(MobCategory.MONSTER, 40, new MobSpawnSettings.SpawnerData(StellarityEntityTypes.FLESH_PIGLIN, 4, 4))
				.addMobCharge(StellarityEntityTypes.VOIDED_SKELETON, 1, 0.8f)
				.addMobCharge(StellarityEntityTypes.VOIDED_SILVERFISH, 1, 0.5f)
				.build()
			).generationSettings(new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, FLESH_TUNDRA_STALACTITES)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, FLESH_TUNDRA_NETHERRACK_BOTTOM)
				.addFeature(GenerationStep.Decoration.LAKES, FLESH_TUNDRA_CRIMSON_DELTAS)
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, BASALT_PILLAR)
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, FLESH_TUNDRA_BONE_CEILING)
				.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, FLESH_TUNDRA_TREES)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FLESH_TUNDRA_VEGETATION)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FLESH_TUNDRA_VINES)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FLESH_TUNDRA_ROOTS)
				.build()
			).build()
		);

		context.register(FROSTED_VALLEY, new Biome.BiomeBuilder()
			.temperature(-0.5f).downfall(0.4f).hasPrecipitation(false).temperatureAdjustment(Biome.TemperatureModifier.FROZEN)
			.setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0x3f472f)
			.setAttribute(EnvironmentAttributes.SKY_COLOR, 0x000000)
			.setAttribute(EnvironmentAttributes.FOG_COLOR, 0x000000)
			.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x050533)
			.setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
				of(StellaritySoundEvents.AMBIENT_THE_END_DARK),
				of(new AmbientMoodSettings(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 200, 4, 4)),
				List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.01111))
			))
			.setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.WHITE_ASH, 0.0094f)))
			.specialEffects(new BiomeSpecialEffects(0x3d57d6, of(0xffffff), empty(), of(0xffffff), BiomeSpecialEffects.GrassColorModifier.NONE))
			.mobSpawnSettings(new MobSpawnSettings.Builder()
				.addSpawn(MobCategory.MONSTER, 50, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 4, 4))
				.addSpawn(MobCategory.MONSTER, 40, new MobSpawnSettings.SpawnerData(StellarityEntityTypes.VOIDED_SKELETON, 4, 4))
				.addSpawn(MobCategory.MONSTER, 10, new MobSpawnSettings.SpawnerData(StellarityEntityTypes.VOIDED_SILVERFISH, 2, 2))
				.addMobCharge(EntityType.ENDERMAN, 1, 0.4)
				.addMobCharge(StellarityEntityTypes.VOIDED_SKELETON, 1, 0.4)
				.addMobCharge(StellarityEntityTypes.VOIDED_SILVERFISH, 1, 0.5)
				.build()
			).generationSettings(new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, GLOBAL_STALACTITES)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, FROZEN_SPIKES_LARGE_DRIPSTONE)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, FROSTED_VALLEY_HILLS)
				.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, FROZEN_SPIKES_BLUE_ICE_ORE)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_DUNGEONS)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_FOSSILS)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_FREEZE_WATER)
				.build()
			).build()
		);

		context.register(FROZEN_MARSH, new Biome.BiomeBuilder()
			.temperature(-0.5f).downfall(0.4f).hasPrecipitation(false)
			.setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0x3f472f)
			.setAttribute(EnvironmentAttributes.SKY_COLOR, 0x000000)
			.setAttribute(EnvironmentAttributes.FOG_COLOR, 0x000000)
			.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x050533)
			.setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
				of(StellaritySoundEvents.AMBIENT_THE_END_DARK),
				of(new AmbientMoodSettings(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 800, 4, 4)),
				List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.01111))
			))
			.setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.WHITE_ASH, 0.0094f)))
			.specialEffects(new BiomeSpecialEffects(0x3d57d6, of(0xffffff), empty(), of(0xffffff), BiomeSpecialEffects.GrassColorModifier.NONE))
			.mobSpawnSettings(new MobSpawnSettings.Builder()
				.build()
			).generationSettings(new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
				.addCarver(StellarityConfiguredCarvers.RAVINE)
				.addCarver(StellarityConfiguredCarvers.CRACK)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, GLOBAL_STALACTITES)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, FROZEN_SPIKES_LARGE_DRIPSTONE)
				.addFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, FROZEN_MARSH_PONDS)
				.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, FROZEN_SPIKES_BLUE_ICE_ORE)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FROZEN_MARSH_VEGETATION)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_DUNGEONS)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_FOSSILS)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_FREEZE_WATER)
				.build()
			).build());

		context.register(FROZEN_SHRUBLAND, new Biome.BiomeBuilder()
			.temperature(-0.5f).downfall(0.4f).hasPrecipitation(false)
			.setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0x3f472f)
			.setAttribute(EnvironmentAttributes.SKY_COLOR, 0x000000)
			.setAttribute(EnvironmentAttributes.FOG_COLOR, 0x000000)
			.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x050533)
			.setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
				of(StellaritySoundEvents.AMBIENT_THE_END_DARK),
				of(new AmbientMoodSettings(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 200, 4, 4)),
				List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.011111))
			))
			.setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.WHITE_ASH, 0.0094f)))
			.specialEffects(new BiomeSpecialEffects(0x3d57d6, of(0xffffff), empty(), of(0xffffff), BiomeSpecialEffects.GrassColorModifier.NONE))
			.mobSpawnSettings(new MobSpawnSettings.Builder()
				.addSpawn(MobCategory.MONSTER, 45, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 4, 4))
				.addSpawn(MobCategory.MONSTER, 35, new MobSpawnSettings.SpawnerData(StellarityEntityTypes.VOIDED_SKELETON, 4, 4))
				.addSpawn(MobCategory.MONSTER, 10, new MobSpawnSettings.SpawnerData(StellarityEntityTypes.VOIDED_SILVERFISH, 2, 2))
				.addSpawn(MobCategory.MONSTER, 15, new MobSpawnSettings.SpawnerData(EntityType.PHANTOM, 2, 2))
				.addMobCharge(EntityType.ENDERMAN, 1, 0.6)
				.addMobCharge(StellarityEntityTypes.VOIDED_SKELETON, 1, 0.4)
				.addMobCharge(StellarityEntityTypes.VOIDED_SILVERFISH, 1, 0.5)
				.addMobCharge(EntityType.PHANTOM, 1, 0.3)
				.build()
			).generationSettings(new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, GLOBAL_STALACTITES)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, FROZEN_SPIKES_LARGE_DRIPSTONE)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, FROZEN_SHRUBLAND_DIRT)
				.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, FROZEN_SPIKES_BLUE_ICE_ORE)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FROZEN_SHRUBLAND_CHORUS_PLANTS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FROZEN_SHRUBLAND_SHRUBS)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_DUNGEONS)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_FOSSILS)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_FREEZE_WATER)
				.build()
			).build()
		);

		context.register(FROZEN_SPIKES, new Biome.BiomeBuilder()
			.temperature(-0.5f).downfall(0.4f).hasPrecipitation(false).temperatureAdjustment(Biome.TemperatureModifier.FROZEN)
			.setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0x3f472f)
			.setAttribute(EnvironmentAttributes.SKY_COLOR, 0x000000)
			.setAttribute(EnvironmentAttributes.FOG_COLOR, 0x000000)
			.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x050533)
			.setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
				of(StellaritySoundEvents.AMBIENT_THE_END_DARK),
				of(new AmbientMoodSettings(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 200, 4, 4)),
				List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.011111))
			))
			.setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.WHITE_ASH, 0.0094f)))
			.specialEffects(new BiomeSpecialEffects(0x3d57d6, of(0xffffff), empty(), of(0xffffff), BiomeSpecialEffects.GrassColorModifier.NONE))
			.mobSpawnSettings(new MobSpawnSettings.Builder()
				.addSpawn(MobCategory.MONSTER, 60, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 4, 4))
				.addSpawn(MobCategory.MONSTER, 40, new MobSpawnSettings.SpawnerData(StellarityEntityTypes.VOIDED_SKELETON, 4, 4))
				.addSpawn(MobCategory.MONSTER, 10, new MobSpawnSettings.SpawnerData(StellarityEntityTypes.VOIDED_SILVERFISH, 2, 2))
				.addMobCharge(EntityType.ENDERMAN, 1, 0.4)
				.addMobCharge(StellarityEntityTypes.VOIDED_SKELETON, 1, 0.4)
				.addMobCharge(StellarityEntityTypes.VOIDED_SILVERFISH, 1, 0.5)
				.build()
			).generationSettings(new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
				.addCarver(StellarityConfiguredCarvers.RAVINE)
				.addCarver(StellarityConfiguredCarvers.CRACK)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, GLOBAL_STALACTITES)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, FROZEN_SPIKES_LARGE_DRIPSTONE)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, FROZEN_SPIKES_HILLS)
				.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, FROZEN_SPIKES_BLUE_ICE_ORE)
				.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, FROZEN_SPIKES_POWDER_SNOW_ORE)
				.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, FROZEN_SPIKES_ICE_SPIKES)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_DUNGEONS)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_FOSSILS)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_FREEZE_WATER)
				.build()
			).build()
		);

		context.register(HALLOWED_TUNDRA, new Biome.BiomeBuilder()
			.temperature(-0.5f).downfall(0.6f).hasPrecipitation(false).temperatureAdjustment(Biome.TemperatureModifier.FROZEN)
			.setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0x3f472f)
			.setAttribute(EnvironmentAttributes.SKY_COLOR, 0x7ba4ff)
			.setAttribute(EnvironmentAttributes.FOG_COLOR, 0xc0d8ff)
			.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0xe566ff)
			.setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
				of(StellaritySoundEvents.AMBIENT_THE_END_HEAVENLY_BLESSED),
				of(new AmbientMoodSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 900, 2, 2)),
				List.of(new AmbientAdditionsSettings(direct(SoundEvents.AMBIENT_UNDERWATER_LOOP_ADDITIONS_RARE), 0.00055))
			))
			.setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.CHERRY_LEAVES, 0.0012f)))
			.specialEffects(new BiomeSpecialEffects(0xea80ff, of(0xa6e4ff), empty(), of(0xb2e1ff), BiomeSpecialEffects.GrassColorModifier.NONE))
			.mobSpawnSettings(new MobSpawnSettings.Builder()
				.addSpawn(MobCategory.AMBIENT, 20, new MobSpawnSettings.SpawnerData(StellarityEntityTypes.PIXIE, 1, 2))
				.addMobCharge(StellarityEntityTypes.PIXIE, 1, 0.25)
				.build()
			).generationSettings(new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, GLOBAL_STALACTITES)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, FROZEN_SPIKES_LARGE_DRIPSTONE)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, FROSTED_VALLEY_HILLS)
				.addFeature(GenerationStep.Decoration.LAKES, THE_HALLOW_CRYSTAL_ROOTS)
				.addFeature(GenerationStep.Decoration.LAKES, THE_HALLOW_ROCKS)
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, HALLOWED_TUNDRA_LAKE)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HALLOWED_TUNDRA_TREES)
				// TODO: tree lanterns can use decorators
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, THE_HALLOW_LANTERNS)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_FREEZE_WATER)
				.build()
			).build()
		);

		context.register(PRISMARINE_FOREST, new Biome.BiomeBuilder()
			.temperature(0.8f).downfall(0.4f).hasPrecipitation(false)
			.setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0x3f472f)
			.setAttribute(EnvironmentAttributes.SKY_COLOR, 0x000000)
			.setAttribute(EnvironmentAttributes.FOG_COLOR, 0x000000)
			.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x16395a)
			.setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
				of(StellaritySoundEvents.AMBIENT_THE_END_HEAVENLY_BLESSED),
				of(new AmbientMoodSettings(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 800, 4, 4)),
				List.of(new AmbientAdditionsSettings(direct(SoundEvents.AMBIENT_UNDERWATER_LOOP_ADDITIONS_RARE), 0.0111))
			)).setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.DRIPPING_DRIPSTONE_WATER, 0.001f)))
			.specialEffects(new BiomeSpecialEffects(0x58c9ee, of(0xadfeff), empty(), of(0x94fff8), BiomeSpecialEffects.GrassColorModifier.NONE))
			.mobSpawnSettings(new MobSpawnSettings.Builder()
				.creatureGenerationProbability(.4f)
				.addSpawn(MobCategory.CREATURE, 12, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 1, 4))
				.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.PIG, 1, 4))
				.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 1, 4))
				.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.COW, 1, 4))
				.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 1, 4))
				.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.FROG, 1, 4))
				.build()
			).generationSettings(new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
				.addCarver(StellarityConfiguredCarvers.RAVINE)
				.addCarver(StellarityConfiguredCarvers.CRACK)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, AMETHYST_FOREST_CALCITE_BOTTOM)
				.addFeature(GenerationStep.Decoration.LAKES, PRISMARINE_FOREST_PONDS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PRISMARINE_FOREST_TREES)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PRISMARINE_FOREST_FLOWERS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PRISMARINE_FOREST_GRASS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PATCH_BUSH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AMETHYST_FOREST_ROOTS)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_DUNGEONS)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_FOSSILS)
				.build()
			).build()
		);

		context.register(PRISMATIC_DUNES, new Biome.BiomeBuilder()
			.temperature(0.6f).downfall(0.6f).hasPrecipitation(false)
			.setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0x3f472f)
			.setAttribute(EnvironmentAttributes.SKY_COLOR, 0x7ba4ff)
			.setAttribute(EnvironmentAttributes.FOG_COLOR, 0xc0d8ff)
			.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0xe566ff)
			.setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
				of(StellaritySoundEvents.AMBIENT_THE_END_HEAVENLY_BLESSED),
				of(new AmbientMoodSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 900, 2, 2)),
				List.of(new AmbientAdditionsSettings(direct(SoundEvents.AMBIENT_UNDERWATER_LOOP_ADDITIONS_RARE), 0.00055f))
			))
			.setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.WHITE_ASH, 0.002f)))
			.specialEffects(new BiomeSpecialEffects(0xea80ff, of(0x58ccfe), empty(), of(0x5cc0ff), BiomeSpecialEffects.GrassColorModifier.NONE))
			.mobSpawnSettings(new MobSpawnSettings.Builder()
				.addSpawn(MobCategory.AMBIENT, 20, new MobSpawnSettings.SpawnerData(StellarityEntityTypes.PIXIE, 1, 2))
				.build()
			).generationSettings(new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, THE_HALLOW_DIORITE_BOTTOM)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, CRYSTAL_CRAGS_AMETHYST_CRYSTALS)
				.addFeature(GenerationStep.Decoration.LAKES, THE_HALLOW_CRYSTAL_ROOTS)
				.addFeature(GenerationStep.Decoration.LAKES, PRISMATIC_DUNES_GLASS_SPIKES)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PRISMATIC_DUNES_CRYSTAL_GRASS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PRISMATIC_DUNES_GRASS_PATCHES)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_FOSSILS)
				.build()
			).build()
		);

		//noinspection DuplicatedCode
		context.register(THE_HALLOW, new Biome.BiomeBuilder()
			.temperature(0.6f).downfall(0.6f).hasPrecipitation(false)
			.setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0x3f472f)
			.setAttribute(EnvironmentAttributes.SKY_COLOR, 0x7ba4ff)
			.setAttribute(EnvironmentAttributes.FOG_COLOR, 0xc0d8ff)
			.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0xe566ff)
			.setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
				of(StellaritySoundEvents.AMBIENT_THE_END_HEAVENLY_BLESSED),
				of(new AmbientMoodSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 900, 2, 2)),
				List.of(new AmbientAdditionsSettings(direct(SoundEvents.AMBIENT_UNDERWATER_LOOP_ADDITIONS_RARE), 0.000055))
			))
			.setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.CHERRY_LEAVES, 0.0012f)))
			.specialEffects(new BiomeSpecialEffects(0xea80ff, of(0x58ccfe), empty(), of(0x5cc0ff), BiomeSpecialEffects.GrassColorModifier.NONE))
			.mobSpawnSettings(new MobSpawnSettings.Builder()
				.creatureGenerationProbability(.4f)
				.addSpawn(MobCategory.AMBIENT, 20, new MobSpawnSettings.SpawnerData(StellarityEntityTypes.PIXIE, 1, 2))
				.addSpawn(MobCategory.CREATURE, 12, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 1, 4))
				.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.PIG, 1, 4))
				.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 1, 4))
				.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.COW, 1, 4))
				.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 1, 4))
				.build()
			).generationSettings(new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, THE_HALLOW_DIORITE_BOTTOM)
				.addFeature(GenerationStep.Decoration.LAKES, THE_HALLOW_CRYSTAL_ROOTS)
				.addFeature(GenerationStep.Decoration.LAKES, CRYSTAL_CRAGS_AMETHYST_CRYSTALS)
				.addFeature(GenerationStep.Decoration.LAKES, THE_HALLOW_ROCKS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, THE_HALLOW_TREES)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, THE_HALLOW_GROUND_FLOWERS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, THE_HALLOW_FLOWERS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, THE_HALLOW_GRASS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PATCH_BUSH)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, THE_HALLOW_LANTERNS)
				.build()
			).build()
		);

		context.register(THE_NEST, new Biome.BiomeBuilder()
			.temperature(0.8f).downfall(0.4f).hasPrecipitation(false)
			.setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0x3f472f)
			.setAttribute(EnvironmentAttributes.SKY_COLOR, 0x000000)
			.setAttribute(EnvironmentAttributes.FOG_COLOR, 0x000000)
			.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x333333)
			.setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
				of(StellaritySoundEvents.AMBIENT_THE_END_DARK_CALM),
				of(new AmbientMoodSettings(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 1600, 5, 1)),
				List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_BASALT_DELTAS_ADDITIONS, 0.0033))
			))
			.setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.WHITE_ASH, 0.01f)))
			.specialEffects(new BiomeSpecialEffects(0x595959, of(0x8a8a8a), empty(), of(0x878787), BiomeSpecialEffects.GrassColorModifier.NONE))
			.mobSpawnSettings(new MobSpawnSettings.Builder()
				.addSpawn(MobCategory.MONSTER, 90, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 4, 4))
				.addMobCharge(EntityType.ENDERMAN, 0.8, 1)
				.build()
			).generationSettings(new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
				.addCarver(StellarityConfiguredCarvers.RAVINE)
				.addCarver(StellarityConfiguredCarvers.CRACK)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, GLOBAL_STALACTITES)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, THE_NEST_DEEPSLATE)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, THE_NEST_TUFF)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, THE_NEST_DEAD_CORAL)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, THE_NEST_TRANSITION)
				.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, THE_NEST_DRAGON_EGGS)
				.build()
			).build()
		);

		context.register(WARPED_MARSH, new Biome.BiomeBuilder()
			.temperature(0.8f).downfall(0.4f).hasPrecipitation(false)
			.setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0x3f472f)
			.setAttribute(EnvironmentAttributes.SKY_COLOR, 0x000000)
			.setAttribute(EnvironmentAttributes.FOG_COLOR, 0x000000)
			.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x67bbc1)
			.setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
				of(StellaritySoundEvents.AMBIENT_THE_END_HEAVENLY_BLESSED),
				of(new AmbientMoodSettings(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 1200, 4, 1)),
				List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_WARPED_FOREST_ADDITIONS, 0.0111))
			))
			.setAttribute(EnvironmentAttributes.SURFACE_SLIME_SPAWN_CHANCE, 0.4f)
			.setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.SPORE_BLOSSOM_AIR, 0.001f)))
			.specialEffects(new BiomeSpecialEffects(0xa2d9e7, of(0x00dde0), empty(), of(0x01ccda), BiomeSpecialEffects.GrassColorModifier.NONE))
			.mobSpawnSettings(new MobSpawnSettings.Builder()
				.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.FROG, 1, 4))
				.addSpawn(MobCategory.CREATURE, 12, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 1, 4))
				.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.PIG, 1, 4))
				.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 1, 4))
				.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.COW, 1, 4))
				.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 1, 4))
				.addSpawn(MobCategory.MONSTER, 20, new MobSpawnSettings.SpawnerData(StellarityEntityTypes.VOIDED_SLIME, 1, 2))
				.creatureGenerationProbability(0.4f)
				.build()
			).generationSettings(new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
				.addFeature(GenerationStep.Decoration.RAW_GENERATION, GLOBAL_STALACTITES)
				.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, WARPED_MARSH_PONDS)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WARPED_MARSH_VEGETATION)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WARPED_MARSH_WATER_VEGETATION)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WARPED_MARSH_TREES)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WARPED_MARSH_SLIME)
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WARPED_MARSH_HANGING_FROGLIGHTS)
				.build()
			).build()
		);
	}
}
