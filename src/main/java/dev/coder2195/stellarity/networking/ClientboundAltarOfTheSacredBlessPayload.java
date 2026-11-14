package dev.coder2195.stellarity.networking;

import dev.coder2195.stellarity.Stellarity;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.phys.Vec3;

public record ClientboundAltarOfTheSacredBlessPayload(Vec3 position) implements CustomPacketPayload {
	public static final CustomPacketPayload.Type<ClientboundAltarOfTheSacredBlessPayload> TYPE = new CustomPacketPayload.Type<>(Stellarity.id("altar_of_the_sacred_bless"));
	public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundAltarOfTheSacredBlessPayload> STREAM_CODEC = StreamCodec.composite(
		Vec3.STREAM_CODEC, ClientboundAltarOfTheSacredBlessPayload::position,
		ClientboundAltarOfTheSacredBlessPayload::new
	);

	@Override
	public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
