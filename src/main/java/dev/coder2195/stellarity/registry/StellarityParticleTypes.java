package dev.coder2195.stellarity.registry;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import dev.coder2195.stellarity.Stellarity;

public interface StellarityParticleTypes {
	SimpleParticleType CRITICAL_STRIKE = register("critical_strike", FabricParticleTypes.simple());
	SimpleParticleType CREATIVE_SHOCK = register("creative_shock", FabricParticleTypes.simple(false));

	static SimpleParticleType register(String id, SimpleParticleType particle) {
		return Registry.register(BuiltInRegistries.PARTICLE_TYPE, Stellarity.id(id), particle);
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Particles");
	}
}
