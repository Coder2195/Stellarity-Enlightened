package dev.coder2195.stellarity.mixin.villagers;


import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.npc.villager.VillagerType;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import dev.coder2195.stellarity.registry.StellarityVillagerTypes;

import java.util.Optional;

@Mixin(VillagerType.class)
public class VillagerTypeMixin {
	@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
	@ModifyExpressionValue(method = "byBiome", at = @At(value = "INVOKE", target = "Ljava/util/Optional;map(Ljava/util/function/Function;)Ljava/util/Optional;"))
	private static Optional<ResourceKey<VillagerType>> byStellarityBiome(Optional<ResourceKey<VillagerType>> original, @Local(argsOnly = true, name = "biome") Holder<Biome> biome) {
		if (biome.is(BiomeTags.IS_END)) return Optional.of(StellarityVillagerTypes.END.key());

		return original;
	}
}
