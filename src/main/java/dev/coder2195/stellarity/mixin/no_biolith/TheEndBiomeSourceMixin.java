package dev.coder2195.stellarity.mixin.no_biolith;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.biome.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.util.WorldgenData;

import java.util.stream.Stream;

@Mixin(TheEndBiomeSource.class)
public abstract class TheEndBiomeSourceMixin extends BiomeSource {
	@Unique
	private MultiNoiseBiomeSource biomeSource;


	@ModifyExpressionValue(method = "create", at = @At(value = "NEW", target = "(Lnet/minecraft/core/Holder;Lnet/minecraft/core/Holder;Lnet/minecraft/core/Holder;Lnet/minecraft/core/Holder;Lnet/minecraft/core/Holder;)Lnet/minecraft/world/level/biome/TheEndBiomeSource;"))
	private static TheEndBiomeSource editBiomeSource(TheEndBiomeSource original, @Local(argsOnly = true, name = "biomes") HolderGetter<Biome> biomes) {
		if (!Stellarity.hasBiolith())
			((TheEndBiomeSourceMixin) (Object) original).biomeSource = WorldgenData.stellarityBiomeSource(biomes);

		return original;
	}


	@WrapMethod(method = "getNoiseBiome")
	private Holder<Biome> getNoiseBiomeOverride(int quartX, int quartY, int quartZ, Climate.Sampler sampler, Operation<Holder<Biome>> original) {
		if (biomeSource != null) return biomeSource.getNoiseBiome(quartX, quartY, quartZ, sampler);
		return original.call(quartX, quartY, quartZ, sampler);
	}

	@WrapMethod(method = "collectPossibleBiomes")
	private Stream<Holder<Biome>> collectPossibleBiomesOverride(Operation<Stream<Holder<Biome>>> original) {
		if (biomeSource != null) return biomeSource.collectPossibleBiomes();
		return original.call();
	}
}
