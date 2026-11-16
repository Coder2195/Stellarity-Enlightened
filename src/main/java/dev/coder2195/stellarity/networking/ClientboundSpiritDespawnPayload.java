package dev.coder2195.stellarity.networking;

import dev.coder2195.stellarity.Stellarity;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.phys.Vec3;

public record ClientboundSpiritDespawnPayload(Vec3 position) implements CustomPacketPayload {
	public static final Type<ClientboundSpiritDespawnPayload> TYPE = new Type<>(Stellarity.id("spirit_despawn"));
	public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundSpiritDespawnPayload> STREAM_CODEC = StreamCodec.composite(
		Vec3.STREAM_CODEC, ClientboundSpiritDespawnPayload::position,
		ClientboundSpiritDespawnPayload::new
	);
	
	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
