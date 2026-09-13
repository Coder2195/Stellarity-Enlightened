package dev.coder2195.stellarity.networking;

import dev.coder2195.stellarity.Stellarity;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public record ClientboundConsecrationCraftPayload(Vec3 position, ItemStack result) implements CustomPacketPayload {
	public static final Type<ClientboundConsecrationCraftPayload> TYPE = new Type<>(Stellarity.id("consecration_craft"));
	public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundConsecrationCraftPayload> STREAM_CODEC = StreamCodec.composite(
		Vec3.STREAM_CODEC, ClientboundConsecrationCraftPayload::position,
		ItemStack.STREAM_CODEC, ClientboundConsecrationCraftPayload::result,
		ClientboundConsecrationCraftPayload::new
	);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
