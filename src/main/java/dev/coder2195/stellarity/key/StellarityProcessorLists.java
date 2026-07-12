package dev.coder2195.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import dev.coder2195.stellarity.Stellarity;

public interface StellarityProcessorLists {
	ResourceKey<StructureProcessorList> CAMPSITE = id("campsite");

	static ResourceKey<StructureProcessorList> id(String id) {
		return Stellarity.key(Registries.PROCESSOR_LIST, id);
	}
}
