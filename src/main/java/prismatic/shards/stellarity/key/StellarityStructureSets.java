package prismatic.shards.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityStructureSets {
	ResourceKey<StructureSet> SMALL_STRUCTURES = id("small_structures");

	static ResourceKey<StructureSet> id(String id) {
		return Stellarity.key(Registries.STRUCTURE_SET, id);
	}
}
