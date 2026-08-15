package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.world.level.levelgen.NoiseSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(NoiseSettings.class)
public interface NoiseSettingsAccessor {
	@Accessor("height")
	@Mutable
	void stellarity$setHeight(int max);
}
