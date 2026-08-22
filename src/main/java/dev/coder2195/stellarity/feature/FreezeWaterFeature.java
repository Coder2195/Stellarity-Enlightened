package dev.coder2195.stellarity.feature;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import org.jspecify.annotations.NonNull;

import static dev.coder2195.stellarity.util.ValueUtil.from;

public class FreezeWaterFeature implements Feature {
	public static final FreezeWaterFeature INSTANCE = new FreezeWaterFeature();
	public static final MapCodec<FreezeWaterFeature> CODEC = MapCodec.unit(INSTANCE);

	@Override
	public boolean place(@NonNull WorldGenLevel level, @NonNull ChunkGenerator generator, @NonNull RandomSource random, @NonNull BlockPos origin) {

		var pos = new BlockPos.MutableBlockPos();

		for (int dx = -8; dx < 24; dx++) {
			for (int dz = -8; dz < 24; dz++) {
				int x = origin.getX() + dx;
				int z = origin.getZ() + dz;
				int y = level.getHeight(Heightmap.Types.MOTION_BLOCKING, x, z);
				pos.set(x, y, z);

				int floor = level.getMinY();

				while (y > floor) {
					if (level.getBlockState(pos).is(Blocks.WATER)) {
						setBlock(level, pos, from(Blocks.ICE));
					}

					pos.setY(--y);
				}
			}
		}

		return true;

	}

	@Override
	public @NonNull MapCodec<? extends Feature> codec() {
		return CODEC;
	}

}
