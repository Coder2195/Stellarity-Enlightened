package dev.coder2195.stellarity.client.registry;

import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.client.registry.particle.CriticalStrikeParticle;
import dev.coder2195.stellarity.registry.StellarityParticleTypes;

public interface StellarityClientParticles {
	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Client Particles");

		ParticleProviderRegistry.getInstance().register(StellarityParticleTypes.CRITICAL_STRIKE, CriticalStrikeParticle.Provider::new);
		ParticleProviderRegistry.getInstance().register(StellarityParticleTypes.CREATIVE_SHOCK, CriticalStrikeParticle.Provider::new);
	}
}
