package dev.coder2195.stellarity.networking;

import dev.coder2195.stellarity.Stellarity;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public record ClientboundDragonbladePunchPayload(Vec3 originalPosition, Vec3 targetPosition, List<Vec3> secondaryPositions, boolean wallPunch) implements CustomPacketPayload {
	public static final Type<ClientboundDragonbladePunchPayload> TYPE = new Type<>(Stellarity.id("dragonblade_punch"));
	public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundDragonbladePunchPayload> STREAM_CODEC = StreamCodec.composite(
		Vec3.STREAM_CODEC, ClientboundDragonbladePunchPayload::originalPosition,
		Vec3.STREAM_CODEC, ClientboundDragonbladePunchPayload::targetPosition,
		Vec3.STREAM_CODEC.apply(ByteBufCodecs.list()), ClientboundDragonbladePunchPayload::secondaryPositions,
		ByteBufCodecs.BOOL, ClientboundDragonbladePunchPayload::wallPunch,
		ClientboundDragonbladePunchPayload::new
	);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
