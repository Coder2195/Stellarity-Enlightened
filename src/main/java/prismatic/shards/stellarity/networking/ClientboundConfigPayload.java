package prismatic.shards.stellarity.networking;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.StellarityConfig;

public record ClientboundConfigPayload(StellarityConfig config) implements CustomPacketPayload {
	public static final Type<ClientboundConfigPayload> TYPE = new Type<>(Stellarity.id("config"));
	public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundConfigPayload> STREAM_CODEC = StreamCodec.composite(
		StellarityConfig.STREAM_CODEC, ClientboundConfigPayload::config,
		ClientboundConfigPayload::new
	);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
