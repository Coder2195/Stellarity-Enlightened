package prismatic.shards.stellarity.datagen.dynamic;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import prismatic.shards.stellarity.key.StellarityTemplatePools;

import java.util.HashMap;

import static prismatic.shards.stellarity.key.StellarityStructures.CAMPSITE;
import static prismatic.shards.stellarity.tags.StellarityBiomeTags.HAS_STRUCTURE_CAMPSITE;
import static prismatic.shards.stellarity.util.WorldgenUtil.absolute;
import static prismatic.shards.stellarity.util.WorldgenUtil.height;

public interface StructureProvider {
	static void bootstrap(BootstrapContext<Structure> context) {
		var templatePools = context.lookup(Registries.TEMPLATE_POOL);
		var biomes = context.lookup(Registries.BIOME);


		context.register(CAMPSITE, new JigsawStructure(
			new Structure.StructureSettings(biomes.getOrThrow(HAS_STRUCTURE_CAMPSITE), new HashMap<>(), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_THIN),
			templatePools.getOrThrow(StellarityTemplatePools.CAMPSITE), 1, height(absolute(0)), false, Heightmap.Types.WORLD_SURFACE
		));
	}
}
