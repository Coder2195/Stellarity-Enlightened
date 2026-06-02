package prismatic.shards.stellarity.networking;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.Stellarity;

import java.util.List;

public record ClientboundElectricDashPayload(Vec3 from, Vec3 to)
	implements CustomPacketPayload {
	public static final Type<ClientboundElectricDashPayload> TYPE = new Type<>(Stellarity.id("electric_dash"));
	public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundElectricDashPayload> STREAM_CODEC = StreamCodec.composite(
		Vec3.STREAM_CODEC, ClientboundElectricDashPayload::from,
		Vec3.STREAM_CODEC, ClientboundElectricDashPayload::to,
		ClientboundElectricDashPayload::new
	);

	@Override
	public @NonNull Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
