package prismatic.shards.stellarity.networking;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.StellarityConfig;

public record ClientboundConfigScreenPayload(StellarityConfig config, boolean canEdit) implements CustomPacketPayload {
	public static final Type<ClientboundConfigScreenPayload> TYPE = new Type<>(Stellarity.id("config_menu"));
	public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundConfigScreenPayload> STREAM_CODEC = StreamCodec.composite(
		StellarityConfig.STREAM_CODEC, ClientboundConfigScreenPayload::config,
		ByteBufCodecs.BOOL, ClientboundConfigScreenPayload::canEdit,
		ClientboundConfigScreenPayload::new
	);

	@Override
	public @NonNull Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
