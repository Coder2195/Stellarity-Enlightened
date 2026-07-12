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
		PayloadTypeRegistry.clientboundPlay().register(ClientboundVoidArrowHitPayload.TYPE, ClientboundVoidArrowHitPayload.STREAM_CODEC);
		PayloadTypeRegistry.clientboundPlay().register(ClientboundConfigScreenPayload.TYPE, ClientboundConfigScreenPayload.STREAM_CODEC);
		PayloadTypeRegistry.clientboundPlay().register(ClientboundElectricDashPayload.TYPE, ClientboundElectricDashPayload.STREAM_CODEC);
		PayloadTypeRegistry.serverboundPlay().register(ServerboundConfigUpdatePayload.TYPE, ServerboundConfigUpdatePayload.STREAM_CODEC);

		Stellarity.LOGGER.info("Registering Stellarity Common Networking");
	}
}
