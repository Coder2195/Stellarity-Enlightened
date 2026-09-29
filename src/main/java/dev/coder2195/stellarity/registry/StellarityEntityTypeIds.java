package dev.coder2195.stellarity.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import dev.coder2195.stellarity.Stellarity;

public interface StellarityEntityTypeIds {
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
	ResourceKey<EntityType<?>> SPECTRAL_WISP = id("spectral_wisp");
	ResourceKey<EntityType<?>> STRIKER_STAR = id("striker_star");
	ResourceKey<EntityType<?>> OBSTRUCT_SPELL_BLOCK = id("obstruct_spell_block");
	ResourceKey<EntityType<?>> CONVEYANCE_SPARK = id("conveyance_spark");
	ResourceKey<EntityType<?>> LIGHT_AURA = id("light_aura");
	ResourceKey<EntityType<?>> POTION_CLOUD = id("potion_cloud");
	ResourceKey<EntityType<?>> DRAGON_BREATH_CAULDRON_INGREDIENT = id("dragon_breath_cauldron_ingredient");
	ResourceKey<EntityType<?>> ASHEN_BOAT = id("ashen_boat");
	ResourceKey<EntityType<?>> AMETHYII_BOAT = id("amethyii_boat");
	ResourceKey<EntityType<?>> HALLOWED_BOAT = id("hallowed_boat");
	ResourceKey<EntityType<?>> SHRUBBED_BOAT = id("shrubbed_boat");
	ResourceKey<EntityType<?>> PRISMATIC_BOAT = id("prismatic_boat");
	ResourceKey<EntityType<?>> INFERNO_BOAT = id("inferno_boat");
	ResourceKey<EntityType<?>> ASHEN_CHEST_BOAT = id("ashen_chest_boat");
	ResourceKey<EntityType<?>> AMETHYII_CHEST_BOAT = id("amethyii_chest_boat");
	ResourceKey<EntityType<?>> HALLOWED_CHEST_BOAT = id("hallowed_chest_boat");
	ResourceKey<EntityType<?>> SHRUBBED_CHEST_BOAT = id("shrubbed_chest_boat");
	ResourceKey<EntityType<?>> PRISMATIC_CHEST_BOAT = id("prismatic_chest_boat");
	ResourceKey<EntityType<?>> INFERNO_CHEST_BOAT = id("inferno_chest_boat");

	static ResourceKey<EntityType<?>> id(String id) {
		return Stellarity.key(Registries.ENTITY_TYPE, id);
	}
}
