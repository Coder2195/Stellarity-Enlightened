package dev.coder2195.stellarity.networking;

import dev.coder2195.stellarity.Stellarity;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

public record ClientboundHolyProtectionDodgePayload(Vec3 position) implements CustomPacketPayload {
	public static final Type<ClientboundHolyProtectionDodgePayload> TYPE = new Type<>(Stellarity.id("holy_protection_dodge"));
	public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundHolyProtectionDodgePayload> STREAM_CODEC = StreamCodec.composite(
		Vec3.STREAM_CODEC, ClientboundHolyProtectionDodgePayload::position,
		ClientboundHolyProtectionDodgePayload::new
	);

	@Override
	public @NonNull Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
