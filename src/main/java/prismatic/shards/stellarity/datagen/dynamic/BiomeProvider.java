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
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.StellarityEntities;
import prismatic.shards.stellarity.registry.StellaritySounds;

import java.util.List;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static net.minecraft.core.Holder.direct;
import static net.minecraft.data.worldgen.placement.VegetationPlacements.PATCH_BUSH;
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
				of(StellaritySounds.AMBIENT_THE_END_HEAVENLY_GRIM),
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
				.creatureGenerationProbability(0.9999f)
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
				.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Stellarity.mcKey(Registries.PLACED_FEATURE, "patch_bush"))
				.build()
			).build()
		);

		context.register(ASHFALL_DELTAS, new Biome.BiomeBuilder()
			.temperature(0.8f).downfall(0.9f).hasPrecipitation(false).temperatureAdjustment(Biome.TemperatureModifier.NONE)
			.setAttribute(EnvironmentAttributes.SKY_COLOR, 0)
			.setAttribute(EnvironmentAttributes.FOG_COLOR, 0)
			.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0xc4c4cf)
			.setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
				of(StellaritySounds.AMBIENT_THE_END_DARK),
				of(new AmbientMoodSettings(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 1250, 3, 2)),
				List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0033))
			))
			.setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(
				ParticleTypes.WHITE_ASH, 0.01f
			)))
			.setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0x3f473f)
			.specialEffects(new BiomeSpecialEffects(
				0xe5eeff, of(0xc2c2c2), empty(), of(0xdedede), BiomeSpecialEffects.GrassColorModifier.NONE
			))
			.mobSpawnSettings(new MobSpawnSettings.Builder()
				.addSpawn(MobCategory.MONSTER, 11, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 2, 2))
				.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(EntityType.FROG, 1, 4))
				.addSpawn(MobCategory.WATER_AMBIENT, 100, new MobSpawnSettings.SpawnerData(EntityType.TROPICAL_FISH, 1, 2))
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
				of(StellaritySounds.AMBIENT_THE_END_HEAVENLY_GRIM),
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
			.addSpawn(MobCategory.MONSTER, 30, new MobSpawnSettings.SpawnerData(StellarityEntities.VOIDED_ZOMBIE, 4, 4))
			.addSpawn(MobCategory.MONSTER, 10, new MobSpawnSettings.SpawnerData(EntityType.PHANTOM, 4, 4))
			.addSpawn(MobCategory.CREATURE, 12, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 1, 4))
			.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.PIG, 1, 4))
			.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 1, 4))
			.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.COW, 1, 4))
			.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 1, 4))
			.addMobCharge(EntityType.ENDERMAN, 0.7, 1)
			.addMobCharge(StellarityEntities.VOIDED_ZOMBIE, 0.7, 1)
			.addMobCharge(EntityType.PHANTOM, 0.7, 1)
			.creatureGenerationProbability(0.9999f)
			.build();
		context.register(END_SHRUBLAND, new Biome.BiomeBuilder()
			.temperature(0.8f).downfall(0.4f).hasPrecipitation(false)
			.setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0x3f472f)
			.setAttribute(EnvironmentAttributes.SKY_COLOR, 0)
			.setAttribute(EnvironmentAttributes.FOG_COLOR, 0)
			.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x041f33)
			.setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.SPORE_BLOSSOM_AIR, 0.002f)))
			.setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
				of(StellaritySounds.AMBIENT_THE_END_HEAVENLY_GRIM),
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
				of(StellaritySounds.AMBIENT_THE_END_DARK),
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
				of(StellaritySounds.AMBIENT_THE_END_DARK),
				of(new AmbientMoodSettings(SoundEvents.AMBIENT_CAVE, 1000, 4, 2)),
				List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111))
			)).specialEffects(new BiomeSpecialEffects(0x504771, of(0x553a5f), empty(), of(0x4c3654), BiomeSpecialEffects.GrassColorModifier.NONE))
			.mobSpawnSettings(new MobSpawnSettings.Builder()
				.addSpawn(MobCategory.MONSTER, 40, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 4, 4))
				.addSpawn(MobCategory.MONSTER, 5, new MobSpawnSettings.SpawnerData(StellarityEntities.VOIDED_SKELETON, 2, 3))
				.addMobCharge(EntityType.ENDERMAN, 0.6, 1)
				.addMobCharge(StellarityEntities.VOIDED_SKELETON, 0.6, 1)
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


		for (var biome : List.of(ENDLESS_DUNES, FIERY_HILLS, FLESH_TUNDRA, FROSTED_VALLEY, FROZEN_MARSH, FROZEN_SHRUBLAND, FROZEN_SPIKES, HALLOWED_TUNDRA, PRISMARINE_FOREST, PRISMATIC_DUNES, THE_HALLOW, THE_NEST, WARPED_MARSH)) {
			context.register(biome, new Biome.BiomeBuilder()
				.temperature(0.8f).downfall(0.4f).hasPrecipitation(false)
				.setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0x3f472f)
				.setAttribute(EnvironmentAttributes.SKY_COLOR, 0)
				.setAttribute(EnvironmentAttributes.FOG_COLOR, 0)
				.setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0)
				.setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
					of(StellaritySounds.AMBIENT_THE_END_HEAVENLY_GRIM),
					of(new AmbientMoodSettings(SoundEvents.AMBIENT_CAVE, 1000, 4, 2)),
					List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_CAVE, 0.001))
				)).specialEffects(new BiomeSpecialEffects(0xf3d1ff, of(0xd494ff), empty(), of(0xdeadff), BiomeSpecialEffects.GrassColorModifier.NONE))
				.mobSpawnSettings(new MobSpawnSettings.Builder()
					.build()
				).generationSettings(new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
					.addFeature(GenerationStep.Decoration.RAW_GENERATION, GLOBAL_STALACTITES)
					.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_DUNGEONS)
					.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, GLOBAL_FOSSILS)
					.build()
				).build()
			);
		}
	}
}
