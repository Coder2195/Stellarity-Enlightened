package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.material.rule.MaterialRule;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(NoiseGeneratorSettings.class)
public interface NoiseGeneratorSettingsAccessor {
	@Mutable
	@Accessor("materialRule")
	void stellarity$setMaterialRule(Holder<MaterialRule> sequence);

	@Mutable
	@Accessor("disableMobGeneration")
	void stellarity$setDisableMobGeneration(boolean b);

	@Mutable
	@Accessor("useLegacyRandomSource")
	void stellarity$setUseLegacyRandomSource(boolean b);
}
