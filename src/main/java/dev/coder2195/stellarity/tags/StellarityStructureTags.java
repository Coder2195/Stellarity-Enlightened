package dev.coder2195.stellarity.tags;

import dev.coder2195.stellarity.Stellarity;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public interface StellarityStructureTags {
	TagKey<Structure> EXPLORATION_MAP_END_CITY = id("exploration_map/end_city");
	TagKey<Structure> EXPLORATION_MAP_VILLAGE = id("exploration_map/village");

	static TagKey<Structure> id(String id) {
		return TagKey.create(Registries.STRUCTURE, Stellarity.id(id));
	}
}
