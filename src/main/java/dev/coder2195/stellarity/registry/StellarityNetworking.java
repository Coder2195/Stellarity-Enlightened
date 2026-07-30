package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.networking.ClientboundConfigScreenPayload;
import dev.coder2195.stellarity.networking.ClientboundElectricDashPayload;
import dev.coder2195.stellarity.networking.ClientboundVoidArrowHitPayload;
import dev.coder2195.stellarity.networking.ServerboundConfigUpdatePayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.networking.*;

public interface StellarityNetworking {
	static void init() {
		var clientbound = PayloadTypeRegistry.clientboundPlay();
		var serverbound = PayloadTypeRegistry.serverboundPlay();
		clientbound.register(ClientboundVoidArrowHitPayload.TYPE, ClientboundVoidArrowHitPayload.STREAM_CODEC);
		clientbound.register(ClientboundConfigScreenPayload.TYPE, ClientboundConfigScreenPayload.STREAM_CODEC);
		clientbound.register(ClientboundElectricDashPayload.TYPE, ClientboundElectricDashPayload.STREAM_CODEC);
		serverbound.register(ServerboundConfigUpdatePayload.TYPE, ServerboundConfigUpdatePayload.STREAM_CODEC);
		clientbound.register(ClientboundSpellbookCastPayload.TYPE, ClientboundSpellbookCastPayload.STREAM_CODEC);

		Stellarity.LOGGER.info("Registering Stellarity Common Networking");
	}
}
