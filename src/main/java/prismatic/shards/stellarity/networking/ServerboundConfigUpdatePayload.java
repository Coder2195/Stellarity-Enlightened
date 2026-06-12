package prismatic.shards.stellarity.networking;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.StellarityConfig;

public record ServerboundConfigUpdatePayload(StellarityConfig config) implements CustomPacketPayload {
	public static final Type<ServerboundConfigUpdatePayload> TYPE = new Type<>(Stellarity.id("config_update"));
	public static final StreamCodec<RegistryFriendlyByteBuf, ServerboundConfigUpdatePayload> STREAM_CODEC = StreamCodec.composite(
		StellarityConfig.STREAM_CODEC, ServerboundConfigUpdatePayload::config,
		ServerboundConfigUpdatePayload::new
	);

	@Override
	public @NonNull Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
