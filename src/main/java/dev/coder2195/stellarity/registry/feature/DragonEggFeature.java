package dev.coder2195.stellarity.registry.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import org.jspecify.annotations.NonNull;

public class DragonEggFeature extends Feature<SimpleBlockConfiguration> {
	public DragonEggFeature(Codec<SimpleBlockConfiguration> codec) {
		super(codec);
	}

	public static final int[] LAYERS = {
		5, 6, 6, 7, 7, 7, 7, 7, 6, 6, 6, 5, 5, 4, 3, 2
	};

	@Override
	public boolean place(@NonNull FeaturePlaceContext<SimpleBlockConfiguration> context) {
		var origin = context.origin();
		var ox = origin.getX();
		var oz = origin.getZ();
		var level = context.level();
		var toPlace = context.config().toPlace();
		var random = context.random();

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
}
