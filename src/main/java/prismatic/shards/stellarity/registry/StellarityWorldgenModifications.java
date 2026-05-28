package prismatic.shards.stellarity.registry;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.dimension.v1.DimensionEvents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.attribute.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import prismatic.shards.stellarity.Stellarity;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static net.minecraft.world.attribute.EnvironmentAttributes.*;
import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.*;
import static prismatic.shards.stellarity.key.StellarityPlacedFeatures.*;

public interface StellarityWorldgenModifications {

	static ResourceKey<PlacedFeature> mc(String id) {
		return Stellarity.mcKey(Registries.PLACED_FEATURE, id);
	}

	@SuppressWarnings("DuplicatedCode")
	static void init() {
		DimensionEvents.MODIFY_ATTRIBUTES.register((dimension, attributes, registries) -> {
			if (!dimension.is(BuiltinDimensionTypes.END)) return;

			attributes.set(BED_RULE, new BedRule(BedRule.Rule.ALWAYS, BedRule.Rule.ALWAYS, false, Optional.empty()));
			attributes.set(RESPAWN_ANCHOR_WORKS, false);
			attributes.set(CAN_START_RAID, true);
			attributes.set(PIGLINS_ZOMBIFY, false);
			attributes.set(WATER_EVAPORATES, false);

			DimensionType dimensionType = dimension.value();
			dimensionType.logicalHeight = Math.max(dimensionType.logicalHeight, 384);
			dimensionType.height = Math.max(dimensionType.height, 384);
			dimensionType.monsterSettings().monsterSpawnBlockLightLimit = 15;
		});


		Predicate<BiomeSelectionContext> outerVanilla = context -> context.getBiomeHolder().is(Biomes.END_BARRENS) || context.getBiomeHolder().is(Biomes.END_HIGHLANDS) || context.getBiomeHolder().is(Biomes.END_MIDLANDS);
		BiomeModifications.addFeature(context -> context.getBiomeHolder().is(Biomes.THE_END) || context.getBiomeHolder().is(Biomes.END_BARRENS) || context.getBiomeHolder().is(Biomes.END_HIGHLANDS) || context.getBiomeHolder().is(Biomes.END_MIDLANDS), RAW_GENERATION, GLOBAL_STALACTITES);
		BiomeModifications.addFeature(outerVanilla, TOP_LAYER_MODIFICATION, GLOBAL_FOSSILS);
		BiomeModifications.addFeature(outerVanilla, TOP_LAYER_MODIFICATION, GLOBAL_DUNGEONS);


		Predicate<BiomeSelectionContext> mainIsland = context -> context.getBiomeHolder().is(Biomes.THE_END);
		BiomeModifications.addFeature(mainIsland, RAW_GENERATION, MAIN_ISLAND_HILLS);
		BiomeModifications.addFeature(mainIsland, UNDERGROUND_DECORATION, MAIN_ISLAND_OBSIDIAN);
		BiomeModifications.addFeature(mainIsland, FLUID_SPRINGS, MAIN_ISLAND_PATCHES);
		BiomeModifications.addFeature(mainIsland, VEGETAL_DECORATION, MAIN_ISLAND_CHORUS_PLANTS);
		BiomeModifications.addFeature(mainIsland, TOP_LAYER_MODIFICATION, MAIN_ISLAND_RING);
		BiomeModifications.addFeature(mainIsland, TOP_LAYER_MODIFICATION, MAIN_ISLAND_PORTAL_PLATFORM);
		BiomeModifications.create(Stellarity.id("the_end_replacements")).add(ModificationPhase.REPLACEMENTS, mainIsland, (_, modification) -> {
				var attributes = modification.getAttributes();
				attributes.set(SKY_COLOR, 0x000000);
				attributes.set(FOG_COLOR, 0x000000);
				attributes.set(WATER_FOG_COLOR, 0x41307e);

				var effects = modification.getEffects();
				effects.setWaterColor(0x62529e);
				effects.setGrassColorOverride(0xdedede);
				effects.setFoliageColorOverride(0xc2c2c2);

				var mobSpawns = modification.getMobSpawnSettings();
				mobSpawns.removeSpawnsOfEntityType(EntityType.ENDERMAN);
				mobSpawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 4, 4), 10);
				mobSpawns.clearMobCharge(EntityType.ENDERMAN);
				mobSpawns.addMobCharge(EntityType.ENDERMAN, 0.75, 1);
			}
		);

		Predicate<BiomeSelectionContext> endBarrens = context -> context.getBiomeHolder().is(Biomes.END_BARRENS);
		BiomeModifications.addFeature(endBarrens, RAW_GENERATION, END_BARRENS_STALACTITES);
		BiomeModifications.addFeature(endBarrens, UNDERGROUND_DECORATION, END_BARRENS_HILLS);
		BiomeModifications.create(Stellarity.id("end_barrens_replacements")).add(ModificationPhase.REPLACEMENTS, endBarrens, (_, modification) -> {
				var attributes = modification.getAttributes();
				attributes.set(SKY_COLOR, 0x000000);
				attributes.set(FOG_COLOR, 0x000000);
				attributes.set(WATER_FOG_COLOR, 0x302947);
				attributes.set(AMBIENT_LIGHT_COLOR, 0x3f473f);
				attributes.set(AMBIENT_SOUNDS, new AmbientSounds(Optional.of(StellaritySoundEvents.AMBIENT_THE_END_DARK), Optional.of(new AmbientMoodSettings(
					SoundEvents.AMBIENT_CAVE, 800, 8, 2
				)), List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.005))));
				attributes.set(AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.ASH, 0.0111f)));

				var effects = modification.getEffects();
				effects.setWaterColor(0x504771);
				effects.setGrassColorOverride(0x4c3654);
				effects.setFoliageColorOverride(0x553a5f);

				var mobSpawns = modification.getMobSpawnSettings();
				mobSpawns.removeSpawnsOfEntityType(EntityType.ENDERMAN);
				mobSpawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 4, 4), 30);
				mobSpawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(StellarityEntityTypes.VOIDED_ZOMBIE, 4, 4), 30);
				mobSpawns.clearMobCharge(EntityType.ENDERMAN);
				mobSpawns.addMobCharge(EntityType.ENDERMAN, 0.8, 1);
				mobSpawns.addMobCharge(StellarityEntityTypes.VOIDED_ZOMBIE, 0.68, 1);
			}
		);

		Predicate<BiomeSelectionContext> endMidlands = context -> context.getBiomeHolder().is(Biomes.END_MIDLANDS);
		BiomeModifications.addFeature(endMidlands, LAKES, END_MIDLANDS_OBSIDIAN_SPIKES);
		BiomeModifications.addFeature(endMidlands, LAKES, END_MIDLANDS_ROCKS);
		BiomeModifications.addFeature(endMidlands, STRONGHOLDS, END_MIDLANDS_VEGETATION);
		BiomeModifications.addFeature(endMidlands, STRONGHOLDS, Stellarity.mcKey(Registries.PLACED_FEATURE, "patch_bush"));
		BiomeModifications.addFeature(endMidlands, VEGETAL_DECORATION, END_MIDLANDS_CHORUS_PLANTS);
		BiomeModifications.addFeature(endMidlands, TOP_LAYER_MODIFICATION, GLOBAL_FOSSILS);
		BiomeModifications.create(Stellarity.id("end_midlands_replacements")).add(ModificationPhase.REPLACEMENTS, endMidlands, (_, modification) -> {
			var attributes = modification.getAttributes();
			attributes.set(SKY_COLOR, 0x000000);
			attributes.set(FOG_COLOR, 0x000000);
			attributes.set(WATER_FOG_COLOR, 0x6c519e);
			attributes.set(AMBIENT_LIGHT_COLOR, 0x3f473f);
			attributes.set(AMBIENT_SOUNDS, new AmbientSounds(Optional.of(StellaritySoundEvents.AMBIENT_THE_END_HEAVENLY_GRIM), Optional.of(new AmbientMoodSettings(
				SoundEvents.AMBIENT_NETHER_WASTES_MOOD, 900, 8, 2
			)), List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.001))));

			var effects = modification.getEffects();
			effects.setWaterColor(0x6c519e);
			effects.setGrassColorOverride(0xb063d9);
			effects.setFoliageColorOverride(0xc161db);

			var mobSpawns = modification.getMobSpawnSettings();
			mobSpawns.removeSpawnsOfEntityType(EntityType.ENDERMAN);
			mobSpawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 4, 4), 10);
			mobSpawns.clearMobCharge(EntityType.ENDERMAN);
			mobSpawns.addMobCharge(EntityType.ENDERMAN, 0.7, 1);
		});

		Predicate<BiomeSelectionContext> endHighlands = context -> context.getBiomeHolder().is(Biomes.END_HIGHLANDS);
		BiomeModifications.addFeature(endHighlands, LAKES, END_MIDLANDS_OBSIDIAN_SPIKES);
		BiomeModifications.addFeature(endHighlands, LOCAL_MODIFICATIONS, END_HIGHLANDS_LARGE_DIRT_PATCHES);
		BiomeModifications.addFeature(endHighlands, LOCAL_MODIFICATIONS, END_HIGHLANDS_SMALL_DIRT_PATCHES);
		BiomeModifications.addFeature(endHighlands, VEGETAL_DECORATION, END_HIGHLANDS_CHORUS_BUDS);
		BiomeModifications.addFeature(endHighlands, VEGETAL_DECORATION, END_HIGHLANDS_CHORUS_PLANTS);
		BiomeModifications.addFeature(endHighlands, VEGETAL_DECORATION, END_HIGHLANDS_CHORUS_LEAVES);
		BiomeModifications.addFeature(endHighlands, VEGETAL_DECORATION, END_HIGHLANDS_BUSHES);
		BiomeModifications.addFeature(endHighlands, VEGETAL_DECORATION, END_HIGHLANDS_PITCHER_PLANTS);
		BiomeModifications.addFeature(endHighlands, VEGETAL_DECORATION, END_HIGHLANDS_GRASS);
		BiomeModifications.addFeature(endHighlands, VEGETAL_DECORATION, END_HIGHLANDS_ROOTS);


		BiomeModifications.create(Stellarity.id("end_highlands_replacements")).add(ModificationPhase.REPLACEMENTS, endHighlands, (_, modification) -> {
			var attributes = modification.getAttributes();
			attributes.set(SKY_COLOR, 0x000000);
			attributes.set(FOG_COLOR, 0x000000);
			attributes.set(WATER_FOG_COLOR, 0x5c79f0);
			attributes.set(AMBIENT_LIGHT_COLOR, 0x3f473f);
			attributes.set(AMBIENT_SOUNDS, new AmbientSounds(Optional.of(StellaritySoundEvents.AMBIENT_THE_END_HEAVENLY_GRIM), Optional.of(new AmbientMoodSettings(
				SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 850, 2, 2
			)), List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0013))));
			attributes.set(AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.WHITE_ASH, 0.003f)));

			var effects = modification.getEffects();
			effects.setWaterColor(0x5c6df0);
			effects.setGrassColorOverride(0xc153d0);
			effects.setFoliageColorOverride(0xeed6ee);

			var mobSpawns = modification.getMobSpawnSettings();
			mobSpawns.removeSpawnsOfEntityType(EntityType.ENDERMAN);
			mobSpawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 4, 4), 20);
		});


	}
}
