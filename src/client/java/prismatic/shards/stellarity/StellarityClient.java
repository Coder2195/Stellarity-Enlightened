package prismatic.shards.stellarity;

import net.fabricmc.api.ClientModInitializer;
import prismatic.shards.stellarity.client.event.StellarityClientNetworking;
import prismatic.shards.stellarity.client.event.StellarityClientTick;
import prismatic.shards.stellarity.client.registry.*;

public class StellarityClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		Stellarity.LOGGER.info("Stellarity Client Initializing");

		StellarityEntityModelLayers.init();
		StellarityModels.init();
		StellarityClientParticles.init();
		StellarityEntityRenderers.init();
		StellarityClientNetworking.init();
		StellarityClientTick.init();
	}
}
