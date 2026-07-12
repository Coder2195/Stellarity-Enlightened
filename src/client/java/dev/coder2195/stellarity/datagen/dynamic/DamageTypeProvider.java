package dev.coder2195.stellarity.datagen.dynamic;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import dev.coder2195.stellarity.key.StellarityDamageTypes;

public interface DamageTypeProvider {
	static void boostrap(BootstrapContext<DamageType> context) {
		context.register(StellarityDamageTypes.BRITTLE, new DamageType("stellarity.brittle", DamageScaling.NEVER, 0.1f, DamageEffects.FREEZING));
		context.register(StellarityDamageTypes.FROSTBURN, new DamageType("stellarity.frostburn", DamageScaling.NEVER, 0.1f, DamageEffects.FREEZING));
		context.register(StellarityDamageTypes.TAMARIS_EXECUTE, new DamageType("stellarity.tamaris_execute", DamageScaling.NEVER, 0.1f));
		context.register(StellarityDamageTypes.VOID_ARROW_SHRAPNEL, new DamageType("stellarity.void_arrow_shrapnel", DamageScaling.NEVER, 0.1f));
		context.register(StellarityDamageTypes.PRISMEMBER, new DamageType("player", DamageScaling.NEVER, 0.1f));
		context.register(StellarityDamageTypes.ELECTRIC, new DamageType("lightningBolt", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0.1f));
	}
}
