package dev.coder2195.stellarity.networking;

import dev.coder2195.stellarity.Stellarity;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

public record ClientboundFloralBloomBloomPayload(Vec3 position, float damage) implements CustomPacketPayload {
	public static final Type<ClientboundFloralBloomBloomPayload> TYPE = new Type<>(Stellarity.id("floral_bloom_bloom"));
	public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundFloralBloomBloomPayload> STREAM_CODEC = StreamCodec.composite(
		Vec3.STREAM_CODEC, ClientboundFloralBloomBloomPayload::position,
		ByteBufCodecs.FLOAT, ClientboundFloralBloomBloomPayload::damage,
		ClientboundFloralBloomBloomPayload::new
	);

	@Override
	public @NonNull Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
