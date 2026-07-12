package dev.coder2195.stellarity.registry.feature;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import static dev.coder2195.stellarity.util.ValueUtil.from;

public class FreezeWaterFeature extends Feature<NoneFeatureConfiguration> {
	public FreezeWaterFeature() {
		super(NoneFeatureConfiguration.CODEC);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		var level = context.level();
		var origin = context.origin();

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
					;
					pos.setY(--y);
				}
			}
		}

		return true;

	}
}
