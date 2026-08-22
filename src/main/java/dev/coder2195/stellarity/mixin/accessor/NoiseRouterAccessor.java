package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseRouter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(NoiseRouter.class)
public interface NoiseRouterAccessor {
	@Mutable
	@Accessor("temperature")
	void stellarity$setTemperature(DensityFunction temperature);

	@Mutable
	@Accessor("vegetation")
	void stellarity$setVegetation(DensityFunction vegetation);

	@Mutable
	@Accessor("continents")
	void stellarity$setContinents(DensityFunction continents);

	@Mutable
	@Accessor("erosion")
	void stellarity$setErosion(DensityFunction erosion);

	@Mutable
	@Accessor("depth")
	void stellarity$setDepth(DensityFunction depth);

	@Mutable
	@Accessor("ridges")
	void stellarity$setRidges(DensityFunction ridges);

	@Mutable
	@Accessor("preliminarySurfaceLevel")
	void stellarity$setPreliminarySurfaceLevel(DensityFunction densityFunction);

	@Mutable
	@Accessor("finalDensity")
	void stellarity$setFinalDensity(DensityFunction densityFunction);
}
