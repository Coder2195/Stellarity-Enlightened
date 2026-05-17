package prismatic.shards.stellarity.registry;

import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.entity.variant.VoidedSkeletonVariant;

public interface StellarityRegistries {
	ResourceKey<Registry<VoidedSkeletonVariant>> VOIDED_SKELETON_VARIANT = id("voided_skeleton_variant");

	static <T> ResourceKey<Registry<T>>

	id(String id) {
		return ResourceKey.createRegistryKey(Stellarity.id(id));

	}

	static void init() {
		DynamicRegistries.registerSynced(VOIDED_SKELETON_VARIANT, VoidedSkeletonVariant.DIRECT_CODEC, VoidedSkeletonVariant.NETWORK_CODEC, DynamicRegistries.SyncOption.SKIP_WHEN_EMPTY);

	}
}
