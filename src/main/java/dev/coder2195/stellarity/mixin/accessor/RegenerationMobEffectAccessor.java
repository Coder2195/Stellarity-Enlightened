package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.RegenerationMobEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(RegenerationMobEffect.class)
public interface RegenerationMobEffectAccessor {
	@Invoker("<init>")
	static RegenerationMobEffect create(MobEffectCategory mobEffectCategory, int i) {
		throw new AssertionError("Not transformed!");
	}
}
