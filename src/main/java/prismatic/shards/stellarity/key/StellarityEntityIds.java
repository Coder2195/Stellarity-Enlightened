package prismatic.shards.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityEntityIds {
	ResourceKey<EntityType<?>> PHANTOM_ITEM_FRAME = id("phantom_item_frame");
	ResourceKey<EntityType<?>> PRISMATIC_PEARL = id("prismatic_pearl");
	ResourceKey<EntityType<?>> VOIDED_ZOMBIE = id("voided_zombie");
	ResourceKey<EntityType<?>> VOIDED_SKELETON = id("voided_skeleton");
	ResourceKey<EntityType<?>> VOIDED_SILVERFISH = id("voided_silverfish");
	ResourceKey<EntityType<?>> VOIDED_SLIME = id("voided_slime");
	ResourceKey<EntityType<?>> FLESH_PIGLIN = id("flesh_piglin");
	ResourceKey<EntityType<?>> PIXIE = id("pixie");
	ResourceKey<EntityType<?>> VOID_ARROW = id("void_arrow");
	ResourceKey<EntityType<?>> SATCHEL_SIGIL = id("satchel_sigil");
	ResourceKey<EntityType<?>> SPECTRAL_BOLT = id("spectral_bolt");

	static ResourceKey<EntityType<?>> id(String id) {
		return Stellarity.key(Registries.ENTITY_TYPE, id);
	}
}
