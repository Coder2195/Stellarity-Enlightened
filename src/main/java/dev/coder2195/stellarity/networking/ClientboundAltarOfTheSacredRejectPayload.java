package dev.coder2195.stellarity.networking;

import dev.coder2195.stellarity.Stellarity;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.phys.Vec3;

public record ClientboundAltarOfTheSacredRejectPayload(Vec3 position) implements CustomPacketPayload {
	public static final Type<ClientboundAltarOfTheSacredRejectPayload> TYPE = new Type<>(Stellarity.id("altar_of_the_sacred_reject"));
	public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundAltarOfTheSacredRejectPayload> STREAM_CODEC = StreamCodec.composite(
		Vec3.STREAM_CODEC, ClientboundAltarOfTheSacredRejectPayload::position,
		ClientboundAltarOfTheSacredRejectPayload::new
	);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
