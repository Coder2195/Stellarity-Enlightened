package prismatic.shards.stellarity.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.Stellarity;

import java.util.List;

public record ClientboundVoidArrowHitPayload(Vec3 position, List<Vec3> shrapnel) implements CustomPacketPayload {
	public static final Type<ClientboundVoidArrowHitPayload> TYPE = new Type<>(Stellarity.id("void_arrow_hit"));
	public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundVoidArrowHitPayload> STREAM_CODEC = StreamCodec.composite(Vec3.STREAM_CODEC, ClientboundVoidArrowHitPayload::position, Vec3.STREAM_CODEC.apply(ByteBufCodecs.list()), ClientboundVoidArrowHitPayload::shrapnel, ClientboundVoidArrowHitPayload::new);

	@Override
	public @NonNull Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

}
