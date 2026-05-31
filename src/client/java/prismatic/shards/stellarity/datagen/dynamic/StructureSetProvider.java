package prismatic.shards.stellarity.datagen.dynamic;

import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement.FrequencyReductionMethod;
import prismatic.shards.stellarity.key.StellarityStructures;

import java.util.List;
import java.util.Optional;

import static prismatic.shards.stellarity.key.StellarityStructureSets.SMALL_STRUCTURES;

public interface StructureSetProvider {
	@SuppressWarnings("deprecation")
	static void bootstrap(BootstrapContext<StructureSet> context) {
		var structures = context.lookup(Registries.STRUCTURE);
		var structureSets = context.lookup(Registries.STRUCTURE_SET);

		context.register(SMALL_STRUCTURES, new StructureSet(List.of(
			new StructureSet.StructureSelectionEntry(structures.getOrThrow(StellarityStructures.CAMPSITE), 1)
		), new RandomSpreadStructurePlacement(
			Vec3i.ZERO, FrequencyReductionMethod.DEFAULT, 1f, 278609929,
			// TODO: when end city sets get corrected
			Optional.empty(),
//			Optional.of(new ExclusionZone(
//				structureSets.getOrThrow(BuiltinStructureSets.END_CITIES), 10
//			)),
			20, 15, RandomSpreadType.LINEAR
		)));
	}
}
