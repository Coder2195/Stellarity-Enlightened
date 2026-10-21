package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MobEffect.class)
public interface MobEffectAccessor {
	@Invoker("<init>")
	static MobEffect create(final MobEffectCategory category, final int color) {
		throw new AssertionError("Not transformed!");
	}

	@Invoker("<init>")
	static MobEffect create(final MobEffectCategory category, final int color, final ParticleOptions particleOptions) {
		throw new AssertionError("Not transformed!");
	}
}
