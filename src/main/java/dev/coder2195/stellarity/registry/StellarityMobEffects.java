package dev.coder2195.stellarity.registry;


import dev.coder2195.stellarity.registry.effect.*;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.effect.*;

public interface StellarityMobEffects {

	Holder<MobEffect> VOIDED = register("voided", new VoidedEffect());
	Holder<MobEffect> JINX = register("jinx", new JinxEffect());

	Holder<MobEffect> BRITTLE = register("brittle", new BrittleEffect());
	Holder<MobEffect> CREATIVE_SHOCK = register("creative_shock", new CreativeShockEffect());
	Holder<MobEffect> FROSTBURN = register("frostburn", new FrostburnEffect());
	Holder<MobEffect> PRISMATIC_INFERNO = register("prismatic_inferno", new PrismaticInfernoEffect());


	static Holder<MobEffect> register(String id, MobEffect effect) {
		return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Stellarity.id(id), effect);
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Mob Effects");
	}
}
