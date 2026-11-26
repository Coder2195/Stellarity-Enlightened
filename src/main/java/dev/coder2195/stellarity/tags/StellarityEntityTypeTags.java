package dev.coder2195.stellarity.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import dev.coder2195.stellarity.Stellarity;

public interface StellarityEntityTypeTags {
	TagKey<EntityType<?>> INVALID_TARGETS = id("invalid_targets");
	TagKey<EntityType<?>> IMMUNE_TO_DRAGONBLADE = id("immune_to_dragonblade");

	static TagKey<EntityType<?>> id(String id) {
		return TagKey.create(Registries.ENTITY_TYPE, Stellarity.id(id));
	}

}
