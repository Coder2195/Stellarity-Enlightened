package prismatic.shards.stellarity.registry;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.networking.*;

public interface StellarityNetworking {
	static void init() {
		PayloadTypeRegistry.clientboundPlay().register(ClientboundVoidArrowHitPayload.TYPE, ClientboundVoidArrowHitPayload.STREAM_CODEC);
		PayloadTypeRegistry.clientboundPlay().register(ClientboundConfigScreenPayload.TYPE, ClientboundConfigScreenPayload.STREAM_CODEC);
		PayloadTypeRegistry.clientboundPlay().register(ClientboundElectricDashPayload.TYPE, ClientboundElectricDashPayload.STREAM_CODEC);
		PayloadTypeRegistry.serverboundPlay().register(ServerboundConfigUpdatePayload.TYPE, ServerboundConfigUpdatePayload.STREAM_CODEC);

		Stellarity.LOGGER.info("Registering Stellarity Common Networking");
	}
}
