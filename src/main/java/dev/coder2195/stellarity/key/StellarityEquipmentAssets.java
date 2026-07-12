package dev.coder2195.stellarity.key;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import dev.coder2195.stellarity.Stellarity;

public interface StellarityEquipmentAssets {
	ResourceKey<EquipmentAsset> SHULKER = id("shulker");
	ResourceKey<EquipmentAsset> PHANTOM_WINGS = id("phantom_wings");
	ResourceKey<EquipmentAsset> DRAGON_WINGS = id("dragon_wings");
	ResourceKey<EquipmentAsset> EMPRESS_WINGS = id("empress_wings");
	ResourceKey<EquipmentAsset> FLORAL = id("floral");
	ResourceKey<EquipmentAsset> CHAMPION = id("champion");
	ResourceKey<EquipmentAsset> HALLOWED = id("hallowed");

	static ResourceKey<EquipmentAsset> id(final String name) {
		return ResourceKey.create(EquipmentAssets.ROOT_ID, Stellarity.id(name));
	}

}
