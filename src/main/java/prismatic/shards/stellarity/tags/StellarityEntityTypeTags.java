package prismatic.shards.stellarity.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityEntityTypeTags {
	TagKey<EntityType<?>> INVALID_TARGETS = id("invalid_targets");
	TagKey<EntityType<?>> PLACEHOLDER_DONT_USE = id("placeholder_dont_use");


	static TagKey<EntityType<?>> id(String id) {
		return TagKey.create(Registries.ENTITY_TYPE, Stellarity.id(id));
	}

}
