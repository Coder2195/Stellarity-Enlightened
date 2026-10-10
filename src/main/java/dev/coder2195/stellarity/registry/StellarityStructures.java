package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.util.tuple.Tuple3;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride.BoundingBoxType;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.DimensionPadding;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static dev.coder2195.stellarity.tags.StellarityBiomeTags.*;
import static dev.coder2195.stellarity.util.WorldgenUtil.*;

public interface StellarityStructures {
	ResourceKey<Structure> CAMPSITE = id("campsite");
	ResourceKey<Structure> END_VILLAGE = id("end_village");
	ResourceKey<Structure> FLOATING_TREASURE = id("floating_treasure");
	ResourceKey<Structure> END_SHIPWRECK = id("end_shipwreck");
	ResourceKey<Structure> END_SHIPWRECK_DUNES = id("end_shipwreck_dunes");

	static void bootstrap(BootstrapContext<Structure> context) {
		var templatePools = context.lookup(Registries.TEMPLATE_POOL);
		var biomes = context.lookup(Registries.BIOME);

		context.register(CAMPSITE, new JigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(HAS_STRUCTURE_CAMPSITE), new HashMap<>(), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_THIN
			), templatePools.getOrThrow(StellarityTemplatePools.CAMPSITE), Optional.empty(), 1, height(absolute(0)), false, Optional.of(Heightmap.Types.WORLD_SURFACE), new JigsawStructure.MaxDistance(40), List.of(), new DimensionPadding(30, 0), LiquidSettings.APPLY_WATERLOGGING
		));

		context.register(FLOATING_TREASURE, new JigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(HAS_STRUCTURE_FLOATING_TREASURE), new HashMap<>(), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BURY
			), templatePools.getOrThrow(StellarityTemplatePools.FLOATING_TREASURE), 1, height(belowTop(50), belowTop(10)), false
		));

		Map<MobCategory, StructureSpawnOverride> villageSpawns = new HashMap<>();
		for (var category : MobCategory.values())
			villageSpawns.put(category, new StructureSpawnOverride(BoundingBoxType.STRUCTURE, WeightedList.of()));

		context.register(END_VILLAGE, new JigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(HAS_STRUCTURE_END_VILLAGE), villageSpawns, GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX
			), templatePools.getOrThrow(StellarityTemplatePools.END_VILLAGE_LAYOUTS), Optional.empty(), 6, height(absolute(0)),
			false, Optional.of(Heightmap.Types.OCEAN_FLOOR), new JigsawStructure.MaxDistance(116), List.of(),
			new DimensionPadding(30, 0), JigsawStructure.DEFAULT_LIQUID_SETTINGS
		));

		for (var endShipwreck: List.of(
			new Tuple3<>(END_SHIPWRECK, HAS_STRUCTURE_END_SHIPWRECK, StellarityTemplatePools.END_SHIPWRECK),
			new Tuple3<>(END_SHIPWRECK_DUNES, HAS_STRUCTURE_END_SHIPWRECK_DUNES, StellarityTemplatePools.END_SHIPWRECK_DUNES)
		)) context.register(endShipwreck._1(), new JigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(endShipwreck._2()), new HashMap<>(), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.NONE
			), templatePools.getOrThrow(endShipwreck._3()), Optional.empty(), 1, height(absolute(-20), absolute(-15)), false, Optional.of(Heightmap.Types.WORLD_SURFACE), new JigsawStructure.MaxDistance(40), List.of(), new DimensionPadding(30, 0), LiquidSettings.APPLY_WATERLOGGING)
		);
	}

	private static ResourceKey<Structure> id(String id) {
		return Stellarity.key(Registries.STRUCTURE, id);
	}
}
