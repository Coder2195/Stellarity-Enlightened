package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(NoiseGeneratorSettings.class)
public interface NoiseGeneratorSettingsAccessor {
	@Mutable
	@Accessor("surfaceRule")
	void stellarity$setSurfaceRule(SurfaceRules.RuleSource sequence);

	@Mutable
	@Accessor("disableMobGeneration")
	void stellarity$setDisableMobGeneration(boolean b);
}
