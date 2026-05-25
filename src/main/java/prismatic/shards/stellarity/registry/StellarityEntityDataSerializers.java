package prismatic.shards.stellarity.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityDataRegistry;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataSerializer;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.entity.SatchelSigil;
import prismatic.shards.stellarity.registry.entity.ThrownPrismaticPearl;
import prismatic.shards.stellarity.registry.entity.variant.VoidedSkeletonVariant;

public interface StellarityEntityDataSerializers {
	EntityDataSerializer<Holder<VoidedSkeletonVariant>> VOIDED_SKELETON_VARIANT = register("voided_skeleton_variant", VoidedSkeletonVariant.STREAM_CODEC);
	EntityDataSerializer<ThrownPrismaticPearl.Trail> PRISMATIC_PEARL_TRAIL = register("prismatic_pearl_trail", ThrownPrismaticPearl.Trail.STREAM_CODEC);
	EntityDataSerializer<SatchelSigil.State> SATCHEL_SIGIL_STATE = register("satchel_sigil_state", SatchelSigil.State.STREAM_CODEC);

	static <T> EntityDataSerializer<T> register(String id, StreamCodec<? super RegistryFriendlyByteBuf, T> codec) {
		var serializer = EntityDataSerializer.forValueType(codec);
		FabricEntityDataRegistry.register(Stellarity.id(id), serializer);
		return serializer;
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Entity Data Serializers");
	}
}
