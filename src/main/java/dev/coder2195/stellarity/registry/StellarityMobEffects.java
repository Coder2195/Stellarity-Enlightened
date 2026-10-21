package dev.coder2195.stellarity.registry;


import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.effect.*;
import dev.coder2195.stellarity.mixin.accessor.MobEffectAccessor;
import dev.coder2195.stellarity.mixin.accessor.RegenerationMobEffectAccessor;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public interface StellarityMobEffects {

	Holder<MobEffect> VOIDED = register("voided", new VoidedEffect());
	Holder<MobEffect> JINX = register("jinx", MobEffectAccessor.create(MobEffectCategory.HARMFUL, 0x3A0052, StellarityParticleTypes.JINX).addAttributeModifier(Attributes.ARMOR, Stellarity.id("jinx_effect"), -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

	Holder<MobEffect> BRITTLE = register("brittle", MobEffectAccessor.create(MobEffectCategory.HARMFUL, 0xddddff));
	Holder<MobEffect> CREATIVE_SHOCK = register("creative_shock", MobEffectAccessor.create(MobEffectCategory.HARMFUL, 0xFD3DB5, StellarityParticleTypes.CREATIVE_SHOCK));
	Holder<MobEffect> FROSTBURN = register("frostburn", new FrostburnEffect());
	Holder<MobEffect> PRISMATIC_INFERNO = register("prismatic_inferno", new PrismaticInfernoEffect());
	Holder<MobEffect> LIFE_CRYSTAL_REGENERATION = register("life_crystal_regeneration", RegenerationMobEffectAccessor.create(MobEffectCategory.BENEFICIAL, 13458603));

	Holder<MobEffect> ASSASINATION = register("assasination", new AssasinationEffect(MobEffectCategory.BENEFICIAL, 0xff697fff));


	static Holder<MobEffect> register(String id, MobEffect effect) {
		return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Stellarity.id(id), effect);
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Mob Effects");
	}
}
