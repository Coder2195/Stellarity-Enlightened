package dev.coder2195.stellarity;

import dev.coder2195.stellarity.client.registry.*;
import net.fabricmc.api.ClientModInitializer;
import dev.coder2195.stellarity.client.event.StellarityClientNetworking;
import dev.coder2195.stellarity.client.event.StellarityClientTick;
import dev.coder2195.stellarity.client.registry.*;

public class StellarityClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		Stellarity.LOGGER.info("Stellarity Client Initializing");

		StellarityEntityModelLayers.init();
		StellarityBlockTintSources.init();
		StellarityItemTintSources.init();
		StellarityClientParticles.init();
		StellarityEntityRenderers.init();
		StellarityClientNetworking.init();
		StellarityClientTick.init();
		StellarityHUDs.init();
	}
}
