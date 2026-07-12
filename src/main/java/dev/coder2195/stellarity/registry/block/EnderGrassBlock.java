package dev.coder2195.stellarity.registry.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;
import net.minecraft.world.level.material.MapColor;
import org.jspecify.annotations.NonNull;
import dev.coder2195.stellarity.registry.StellarityBlocks;

public class EnderGrassBlock extends GrassBlock {
	public EnderGrassBlock(Properties properties) {
		super(properties);
	}

	public static final Properties PROPERTIES = Properties.of()
		.mapColor(MapColor.GRASS)
		.randomTicks()
		.strength(0.6F)
		.sound(SoundType.GRASS);

	@Override
	public void randomTick(@NonNull BlockState state, @NonNull ServerLevel level, @NonNull BlockPos pos, @NonNull RandomSource random) {
		if (!canBeGrass(state, level, pos)) {
			level.setBlockAndUpdate(pos, StellarityBlocks.ENDER_DIRT.defaultBlockState());
		} else {
			if (level.getMaxLocalRawBrightness(pos.above()) >= 3) {
				BlockState blockState = this.defaultBlockState();

				for (int i = 0; i < 4; ++i) {
					BlockPos blockPos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
					if (level.getBlockState(blockPos).is(StellarityBlocks.ENDER_DIRT) && canPropagate(blockState, level, blockPos)) {
						level.setBlockAndUpdate(blockPos, blockState.setValue(SNOWY, level.getBlockState(blockPos.above()).is(Blocks.SNOW)));
					}
				}
			}

		}
	}

	private static boolean canPropagate(BlockState state, LevelReader level, BlockPos pos) {
		BlockPos blockPos = pos.above();
		return canBeGrass(state, level, pos) && !level.getFluidState(blockPos).is(FluidTags.WATER);
	}

	private static boolean canBeGrass(BlockState state, LevelReader levelReader, BlockPos pos) {
		BlockPos blockPos = pos.above();
		BlockState blockState = levelReader.getBlockState(blockPos);
		if (blockState.is(Blocks.SNOW) && blockState.getValue(SnowLayerBlock.LAYERS) == 1) {
			return true;
		} else if (blockState.getFluidState().getAmount() == 8) {
			return false;
		} else {


			int i = LightEngine.getLightDampeningInto(blockState, state, Direction.UP, blockState.getLightDampening());
			return i < 15;

		}


	}


}
