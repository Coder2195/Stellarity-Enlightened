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

	static void bootstrap(BootstrapContext<StructureSet> context) {
		var structures = context.lookup(Registries.STRUCTURE);
		var structureSets = context.lookup(Registries.STRUCTURE_SET);

		context.register(SMALL_STRUCTURES, new StructureSet(List.of(
			new StructureSet.StructureSelectionEntry(structures.getOrThrow(StellarityStructures.CAMPSITE), 1)
		), new RandomSpreadStructurePlacement(
			Vec3i.ZERO, StructurePlacement.FrequencyReductionMethod.DEFAULT, 1f, 278609929,
			// TODO: when end city sets get corrected
			Optional.empty(),
//			Optional.of(new ExclusionZone(
//				structureSets.getOrThrow(BuiltinStructureSets.END_CITIES), 10
//			)),
			20, 15, RandomSpreadType.LINEAR
		)));
	}

	static ResourceKey<StructureSet> id(String id) {
		return Stellarity.key(Registries.STRUCTURE_SET, id);
	}
}
