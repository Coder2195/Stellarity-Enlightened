package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static dev.coder2195.stellarity.tags.StellarityBiomeTags.HAS_STRUCTURE_CAMPSITE;
import static dev.coder2195.stellarity.tags.StellarityBiomeTags.HAS_STRUCTURE_VILLAGE;
import static dev.coder2195.stellarity.util.WorldgenUtil.absolute;
import static dev.coder2195.stellarity.util.WorldgenUtil.height;

public interface StellarityStructures {
	ResourceKey<Structure> CAMPSITE = id("campsite");
	ResourceKey<Structure> VILLAGE = id("village");

	static void bootstrap(BootstrapContext<Structure> context) {
		var templatePools = context.lookup(Registries.TEMPLATE_POOL);
		var biomes = context.lookup(Registries.BIOME);

		context.register(CAMPSITE, new JigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(HAS_STRUCTURE_CAMPSITE), new HashMap<>(), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_THIN
			), templatePools.getOrThrow(StellarityTemplatePools.CAMPSITE), 1, height(absolute(0)), false, Heightmap.Types.WORLD_SURFACE
		));

		Map<MobCategory, StructureSpawnOverride> villageSpawns = new HashMap<>();
		for (var category : MobCategory.values())
			villageSpawns.put(category, new StructureSpawnOverride(BoundingBoxType.STRUCTURE, WeightedList.of()));

		context.register(VILLAGE, new JigsawStructure(
			new Structure.StructureSettings(
				biomes.getOrThrow(HAS_STRUCTURE_VILLAGE), villageSpawns, GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX
			), templatePools.getOrThrow(StellarityTemplatePools.VILLAGE_LAYOUTS), Optional.empty(), 6, height(absolute(0)),
			false, Optional.of(Heightmap.Types.OCEAN_FLOOR), new JigsawStructure.MaxDistance(116), List.of(),
			new DimensionPadding(30, 0), JigsawStructure.DEFAULT_LIQUID_SETTINGS
		));
	}

	private static ResourceKey<Structure> id(String id) {
		return Stellarity.key(Registries.STRUCTURE, id);
	}
}
