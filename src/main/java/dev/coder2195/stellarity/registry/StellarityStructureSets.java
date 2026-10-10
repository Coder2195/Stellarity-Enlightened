package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.AbstractSpreadingStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

import java.util.List;
import java.util.Optional;

public interface StellarityStructureSets {
	ResourceKey<StructureSet> SMALL_STRUCTURES = id("small_structures");
	ResourceKey<StructureSet> END_VILLAGES = id("end_villages");
	ResourceKey<StructureSet> FLOATING_TREASURES = id("floating_treasures");
	ResourceKey<StructureSet> END_SHIPWRECKS = id("end_shipwrecks");

	@SuppressWarnings("deprecation")
	static void bootstrap(BootstrapContext<StructureSet> context) {
		var structures = context.lookup(Registries.STRUCTURE);
		var structureSets = context.lookup(Registries.STRUCTURE_SET);

		var villageStructureSet = context.register(END_VILLAGES, new StructureSet(List.of(
			new StructureSet.StructureSelectionEntry(structures.getOrThrow(StellarityStructures.END_VILLAGE), 1)
		), new RandomSpreadStructurePlacement(
			Vec3i.ZERO, AbstractSpreadingStructurePlacement.FrequencyReductionMethod.DEFAULT, 0.7f, 2343435,
			Optional.empty(), 51, 26, RandomSpreadType.TRIANGULAR
		)));

		context.register(SMALL_STRUCTURES, new StructureSet(List.of(
			new StructureSet.StructureSelectionEntry(structures.getOrThrow(StellarityStructures.CAMPSITE), 1)
		), new RandomSpreadStructurePlacement(
			Vec3i.ZERO, AbstractSpreadingStructurePlacement.FrequencyReductionMethod.DEFAULT, 1f, 278609929,

			Optional.of(new AbstractSpreadingStructurePlacement.ExclusionZone(
				villageStructureSet, 8
			)),
			20, 15, RandomSpreadType.LINEAR
		)));

		context.register(FLOATING_TREASURES, new StructureSet(List.of(
			new StructureSet.StructureSelectionEntry(structures.getOrThrow(StellarityStructures.FLOATING_TREASURE), 1)
		), new RandomSpreadStructurePlacement(
			Vec3i.ZERO, AbstractSpreadingStructurePlacement.FrequencyReductionMethod.DEFAULT, 1f, 485323964,
			Optional.of(new AbstractSpreadingStructurePlacement.ExclusionZone(structureSets.getOrThrow(BuiltinStructureSets.END_CITIES), 8)), 23, 12, RandomSpreadType.LINEAR
		)));

		context.register(END_SHIPWRECKS, new StructureSet(List.of(
			new StructureSet.StructureSelectionEntry(structures.getOrThrow(StellarityStructures.END_SHIPWRECK), 1),
			new StructureSet.StructureSelectionEntry(structures.getOrThrow(StellarityStructures.END_SHIPWRECK_DUNES), 1)
		), new RandomSpreadStructurePlacement(
			Vec3i.ZERO, AbstractSpreadingStructurePlacement.FrequencyReductionMethod.DEFAULT, 1f, 656346133,
			Optional.of(new AbstractSpreadingStructurePlacement.ExclusionZone(structureSets.getOrThrow(BuiltinStructureSets.END_CITIES), 8)), 27, 20, RandomSpreadType.LINEAR
		)));


	}

	static ResourceKey<StructureSet> id(String id) {
		return Stellarity.key(Registries.STRUCTURE_SET, id);
	}
}
