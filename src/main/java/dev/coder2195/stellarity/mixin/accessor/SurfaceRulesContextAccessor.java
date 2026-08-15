package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SurfaceRules.Context.class)
public interface SurfaceRulesContextAccessor {

	@Invoker("getBiome")
	Holder<Biome> stellarity$getBiome();
}
