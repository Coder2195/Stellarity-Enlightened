package prismatic.shards.stellarity.registry;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.networking.ClientboundConfigPayload;
import prismatic.shards.stellarity.networking.ClientboundVoidArrowHitPayload;

public interface StellarityNetworking {
	static void init() {
		PayloadTypeRegistry.clientboundPlay().register(ClientboundVoidArrowHitPayload.TYPE, ClientboundVoidArrowHitPayload.STREAM_CODEC);
		PayloadTypeRegistry.clientboundPlay().register(ClientboundConfigPayload.TYPE, ClientboundConfigPayload.STREAM_CODEC);

		Stellarity.LOGGER.info("Registering Stellarity Common Networking");
	}
}
