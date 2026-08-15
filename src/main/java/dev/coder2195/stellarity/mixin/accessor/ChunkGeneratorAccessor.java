package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ChunkGenerator.class)
public interface ChunkGeneratorAccessor {
	@Mutable
	@Accessor("biomeSource")
	void stellarity$setBiomeSource(BiomeSource biomeSource);
}
