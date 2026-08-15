package dev.coder2195.stellarity.registry;


import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.effect.*;
import dev.coder2195.stellarity.mixin.accessor.RegenerationMobEffectAccessor;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public interface StellarityMobEffects {

	Holder<MobEffect> VOIDED = register("voided", new VoidedEffect());
	Holder<MobEffect> JINX = register("jinx", new JinxEffect());

	Holder<MobEffect> BRITTLE = register("brittle", new BrittleEffect());
	Holder<MobEffect> CREATIVE_SHOCK = register("creative_shock", new CreativeShockEffect());
	Holder<MobEffect> FROSTBURN = register("frostburn", new FrostburnEffect());
	Holder<MobEffect> PRISMATIC_INFERNO = register("prismatic_inferno", new PrismaticInfernoEffect());
	Holder<MobEffect> LIFE_CRYSTAL_REGENERATION = register("life_crystal_regeneration", RegenerationMobEffectAccessor.create(MobEffectCategory.BENEFICIAL, 13458603));


	static Holder<MobEffect> register(String id, MobEffect effect) {
		return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Stellarity.id(id), effect);
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Mob Effects");
	}
}
