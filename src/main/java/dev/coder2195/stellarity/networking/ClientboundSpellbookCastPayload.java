package dev.coder2195.stellarity.networking;

import dev.coder2195.stellarity.Stellarity;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

public record ClientboundSpellbookCastPayload(Vec3 position) implements CustomPacketPayload {
	public static final CustomPacketPayload.Type<ClientboundSpellbookCastPayload> TYPE = new CustomPacketPayload.Type<>(Stellarity.id("spellbook_cast"));
	public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundSpellbookCastPayload> STREAM_CODEC = StreamCodec.composite(
		Vec3.STREAM_CODEC, ClientboundSpellbookCastPayload::position,
		ClientboundSpellbookCastPayload::new
	);

	@Override
	public @NonNull Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
