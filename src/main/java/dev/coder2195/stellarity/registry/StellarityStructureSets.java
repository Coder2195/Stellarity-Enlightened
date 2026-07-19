package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;

import java.util.List;
import java.util.Optional;

public interface StellarityStructureSets {
	ResourceKey<StructureSet> SMALL_STRUCTURES = id("small_structures");
	ResourceKey<StructureSet> VILLAGES = id("villages");

	@SuppressWarnings("deprecation")
	static void bootstrap(BootstrapContext<StructureSet> context) {
		var structures = context.lookup(Registries.STRUCTURE);

		var villageStructureSet = context.register(VILLAGES, new StructureSet(List.of(
			new StructureSet.StructureSelectionEntry(structures.getOrThrow(StellarityStructures.VILLAGE), 1)
		), new RandomSpreadStructurePlacement(
			Vec3i.ZERO, StructurePlacement.FrequencyReductionMethod.DEFAULT, 0.7f, 2343435,
			Optional.empty(), 51, 26, RandomSpreadType.TRIANGULAR
		)));

		context.register(SMALL_STRUCTURES, new StructureSet(List.of(
			new StructureSet.StructureSelectionEntry(structures.getOrThrow(StellarityStructures.CAMPSITE), 1)
		), new RandomSpreadStructurePlacement(
			Vec3i.ZERO, StructurePlacement.FrequencyReductionMethod.DEFAULT, 1f, 278609929,

			Optional.of(new StructurePlacement.ExclusionZone(
				villageStructureSet, 8
			)),
			20, 15, RandomSpreadType.LINEAR
		)));


	}

	static ResourceKey<StructureSet> id(String id) {
		return Stellarity.key(Registries.STRUCTURE_SET, id);
	}
}
