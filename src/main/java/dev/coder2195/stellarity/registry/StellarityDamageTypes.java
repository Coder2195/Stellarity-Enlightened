package dev.coder2195.stellarity.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import dev.coder2195.stellarity.Stellarity;


public interface StellarityDamageTypes {
	ResourceKey<DamageType> BRITTLE = id("brittle");
	ResourceKey<DamageType> ELECTRIC = id("electric");
	ResourceKey<DamageType> FROSTBURN = id("frostburn");
	ResourceKey<DamageType> TAMARIS_EXECUTE = id("tamaris_execute");
	ResourceKey<DamageType> VOID_ARROW_SHRAPNEL = id("void_arrow_shrapnel");
	ResourceKey<DamageType> PRISMEMBER = id("prismember");

	static void boostrap(BootstrapContext<DamageType> context) {
		context.register(StellarityDamageTypes.BRITTLE, new DamageType("stellarity.brittle", DamageScaling.NEVER, 0.1f, DamageEffects.FREEZING));
		context.register(StellarityDamageTypes.FROSTBURN, new DamageType("stellarity.frostburn", DamageScaling.NEVER, 0.1f, DamageEffects.FREEZING));
		context.register(StellarityDamageTypes.TAMARIS_EXECUTE, new DamageType("stellarity.tamaris_execute", DamageScaling.NEVER, 0.1f));
		context.register(StellarityDamageTypes.VOID_ARROW_SHRAPNEL, new DamageType("stellarity.void_arrow_shrapnel", DamageScaling.NEVER, 0.1f));
		context.register(StellarityDamageTypes.PRISMEMBER, new DamageType("player", DamageScaling.NEVER, 0.1f));
		context.register(StellarityDamageTypes.ELECTRIC, new DamageType("lightningBolt", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0.1f));
	}

	private static ResourceKey<DamageType> id(String id) {
		return Stellarity.key(Registries.DAMAGE_TYPE, id);
	}
}