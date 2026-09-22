package dev.coder2195.stellarity.networking;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.util.CustomStreamCodecs;
import dev.coder2195.stellarity.util.tuple.Tuple2;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public record ClientboundCauldronCraftPayload(Vec3 resultPosition, List<Tuple2<ItemStackTemplate, Vec3>> fromEntries) implements CustomPacketPayload {

	public static final Type<ClientboundCauldronCraftPayload> TYPE = new Type<>(Stellarity.id("cauldron_craft"));

	public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundCauldronCraftPayload> STREAM_CODEC = StreamCodec.composite(
		Vec3.STREAM_CODEC, ClientboundCauldronCraftPayload::resultPosition,
		CustomStreamCodecs.ITEM_STACK_TEMPLATE_VEC_3_TUPLES, ClientboundCauldronCraftPayload::fromEntries,
		ClientboundCauldronCraftPayload::new
	);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
