package dev.coder2195.stellarity.networking;

import dev.coder2195.stellarity.Stellarity;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.phys.Vec3;

public record ClientboundDragonbladeSpawnPayload(Vec3 position) implements CustomPacketPayload {
	public static final CustomPacketPayload.Type<ClientboundDragonbladeSpawnPayload> TYPE = new CustomPacketPayload.Type<>(Stellarity.id("dragonblade_spawn"));
	public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundDragonbladeSpawnPayload> STREAM_CODEC = StreamCodec.composite(
		Vec3.STREAM_CODEC, ClientboundDragonbladeSpawnPayload::position,
		ClientboundDragonbladeSpawnPayload::new
	);

	@Override
	public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
