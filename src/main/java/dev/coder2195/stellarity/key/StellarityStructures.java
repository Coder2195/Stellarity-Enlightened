package dev.coder2195.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import dev.coder2195.stellarity.Stellarity;

public interface StellarityStructures {
	ResourceKey<Structure> CAMPSITE = id("campsite");

	static ResourceKey<Structure> id(String id) {
		return Stellarity.key(Registries.STRUCTURE, id);
	}
}
