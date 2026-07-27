package dev.coder2195.stellarity.client.registry;

import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.client.registry.particle.ScaleShrinkingParticle;
import dev.coder2195.stellarity.registry.StellarityParticleTypes;

public interface StellarityClientParticles {
	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Client Particles");

		// TODO: fix in future
		ParticleProviderRegistry.getInstance().register(StellarityParticleTypes.CRITICAL_STRIKE, ScaleShrinkingParticle.Provider::new);
		ParticleProviderRegistry.getInstance().register(StellarityParticleTypes.CREATIVE_SHOCK, ScaleShrinkingParticle.Provider::new);
		ParticleProviderRegistry.getInstance().register(StellarityParticleTypes.JINX, ScaleShrinkingParticle.Provider::new);
	}
}
