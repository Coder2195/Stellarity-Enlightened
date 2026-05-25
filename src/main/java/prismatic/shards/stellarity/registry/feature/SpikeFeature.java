package prismatic.shards.stellarity.registry.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.registry.feature.configuration.SpikeFeatureConfiguration;

public class SpikeFeature extends Feature<SpikeFeatureConfiguration> {
	public SpikeFeature(Codec<SpikeFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(@NonNull FeaturePlaceContext<SpikeFeatureConfiguration> context) {
		var config = context.config();
		var level = context.level();
		var origin = context.origin();
		var random = context.random();
		var canReplace = config.canReplace();

		var originY = origin.getY();
		double currentX = origin.getX();
		double currentZ = origin.getZ();
		var radius = config.radius().sample(random);
		var height = config.height().sample(random);
		var maxY = Math.min(height + originY, level.getMaxY() - 1);
		var windX = config.windX().sample(random);
		var windZ = config.windZ().sample(random);

		var currentRadius = radius;
		var decreaseFactor = radius / height;
		var blockPos = new BlockPos.MutableBlockPos();

		for (float y = originY; y < maxY; y++) {
			var radiusSquared = Mth.square(currentRadius);
			int xCap = Mth.ceil(currentRadius);
			for (int dx = -xCap; dx <= xCap; dx++) {
				int zCap = Mth.ceil(currentRadius);
				for (int dz = -zCap; dz <= zCap; dz++) {
					int distanceSquared = Mth.square(dx) + Mth.square(dz);
					if (distanceSquared > radiusSquared)
						continue;
					blockPos.set(currentX + dx, y, currentZ + dz);
					if (level.getBlockState(blockPos).isAir() || canReplace.map(c -> c.test(level, blockPos)).orElse(true))
						level.setBlock(blockPos, config.stateProvider().getState(level, random, blockPos), Block.UPDATE_CLIENTS);
				}
			}

			currentX -= windX;
			currentZ -= windZ;
			currentRadius -= decreaseFactor;
		}


		return true;
	}
}
