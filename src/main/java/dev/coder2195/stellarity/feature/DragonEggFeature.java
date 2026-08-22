package dev.coder2195.stellarity.feature;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import org.jspecify.annotations.NonNull;

public record DragonEggFeature(BlockStateProvider toPlace) implements Feature {
	public static final MapCodec<DragonEggFeature> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			BlockStateProvider.CODEC.fieldOf("to_place").forGetter(DragonEggFeature::toPlace)
			).apply(instance, DragonEggFeature::new));

	public static final int[] LAYERS = {
		5, 6, 6, 7, 7, 7, 7, 7, 6, 6, 6, 5, 5, 4, 3, 2
	};

	@Override
	public boolean place(@NonNull WorldGenLevel level, @NonNull ChunkGenerator chunkGenerator, @NonNull RandomSource random, BlockPos origin) {

		var ox = origin.getX();
		var oz = origin.getZ();


		BlockPos.MutableBlockPos pos = origin.mutable();

		for (int layer : LAYERS) {
			int maxX = ox + layer;
			int maxZ = oz + layer;
			for (int x = ox - layer; x < maxX; x++) {
				for (int z = oz - layer; z < maxZ; z++) {
					pos.setX(x);
					pos.setZ(z);

					level.setBlock(pos, toPlace.getState(level, random, pos), Block.UPDATE_CLIENTS);
				}
			}

			pos.move(0, 1, 0);
		}

		return true;
	}

	@Override
	public @NonNull MapCodec<? extends Feature> codec() {
		return CODEC;
	}

}
