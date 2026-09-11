package dev.coder2195.stellarity.feature;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.FloatProviders;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.Optional;

public record SpikeFeature(
	Holder<BlockStateProvider> material, Optional<BlockPredicate> canReplace, FloatProvider radius, FloatProvider height,
	FloatProvider windX, FloatProvider windZ
) implements Feature {

	public static final MapCodec<SpikeFeature> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
		BlockStateProvider.CODEC.fieldOf("material").forGetter(SpikeFeature::material),
		BlockPredicate.CODEC.optionalFieldOf("can_replace").forGetter(SpikeFeature::canReplace),
		FloatProviders.CODEC.fieldOf("radius").forGetter(SpikeFeature::radius),
		FloatProviders.CODEC.fieldOf("height").forGetter(SpikeFeature::height),
		FloatProviders.CODEC.fieldOf("wind_x").forGetter(SpikeFeature::windX),
		FloatProviders.CODEC.fieldOf("wind_z").forGetter(SpikeFeature::windZ)
	).apply(instance, SpikeFeature::new));

	@Override
	public boolean place(WorldGenLevel level, ChunkGenerator generator, RandomSource random, BlockPos origin) {

		var originY = origin.getY();
		double currentX = origin.getX();
		double currentZ = origin.getZ();
		var radius = this.radius.sample(random);
		var height = this.height.sample(random);
		var maxY = Math.min(height + originY, level.getMaxY() - 1);
		var windX = this.windX.sample(random);
		var windZ = this.windZ.sample(random);

		var currentRadius = radius;
		var decreaseFactor = radius / height;
		var blockPos = new BlockPos.MutableBlockPos();

		var stateProvider = this.material.value();

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
					if (level.getBlockState(blockPos).canBeReplaced() || canReplace.map(c -> c.test(level, blockPos)).orElse(true))
						level.setBlock(blockPos, stateProvider.getState(level, random, blockPos), Block.UPDATE_CLIENTS);
				}
			}

			currentX -= windX;
			currentZ -= windZ;
			currentRadius -= decreaseFactor;
		}


		return true;
	}

	@Override
	public MapCodec<? extends Feature> codec() {
		return CODEC;
	}
}
