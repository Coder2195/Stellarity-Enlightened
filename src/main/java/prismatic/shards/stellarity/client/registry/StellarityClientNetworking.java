package prismatic.shards.stellarity.client.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.networking.ClientboundVoidArrowHitPayload;

@Environment(EnvType.CLIENT)
public interface StellarityClientNetworking {
	static void init() {
		ClientPlayNetworking.registerGlobalReceiver(ClientboundVoidArrowHitPayload.TYPE, ClientboundVoidArrowHitPayload::recieve);

		Stellarity.LOGGER.info("Registering Stellarity Client Networking");
	}
}
