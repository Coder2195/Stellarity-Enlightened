package dev.coder2195.stellarity.networking;

import dev.coder2195.stellarity.Stellarity;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public record ClientboundSpiritSlashPayload(Vec3 fromPosition, Vec3 toPosition, boolean hasFire, List<Vec3> slashedPositions) implements CustomPacketPayload {
	public static final Type<ClientboundSpiritSlashPayload> TYPE = new Type<>(Stellarity.id("spirit_slash"));
	public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundSpiritSlashPayload> STREAM_CODEC = StreamCodec.composite(
		Vec3.STREAM_CODEC, ClientboundSpiritSlashPayload::fromPosition,
		Vec3.STREAM_CODEC, ClientboundSpiritSlashPayload::toPosition,
		ByteBufCodecs.BOOL, ClientboundSpiritSlashPayload::hasFire,
		Vec3.STREAM_CODEC.apply(ByteBufCodecs.list()), ClientboundSpiritSlashPayload::slashedPositions,
		ClientboundSpiritSlashPayload::new
	);
	
	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
