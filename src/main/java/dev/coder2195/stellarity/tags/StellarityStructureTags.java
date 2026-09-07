package dev.coder2195.stellarity.tags;

import dev.coder2195.stellarity.Stellarity;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public interface StellarityStructureTags {
	TagKey<Structure> ON_END_CITY_MAPS = id("on_end_city_maps");
	TagKey<Structure> ON_VILLAGE_MAPS = id("on_village_maps");

	static TagKey<Structure> id(String id) {
		return TagKey.create(Registries.STRUCTURE, Stellarity.id(id));
	}
}
