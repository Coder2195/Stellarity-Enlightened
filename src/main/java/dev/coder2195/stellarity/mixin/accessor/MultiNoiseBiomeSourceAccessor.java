package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.stream.Stream;

@Mixin(MultiNoiseBiomeSource.class)
public interface MultiNoiseBiomeSourceAccessor {
	@Invoker("collectPossibleBiomes")
	Stream<Holder<Biome>> stellarity$collectPossibleBiomes();
}
