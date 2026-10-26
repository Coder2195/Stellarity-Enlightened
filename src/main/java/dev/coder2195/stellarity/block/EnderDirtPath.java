package dev.coder2195.stellarity.block;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.sounds.BlockSoundSets;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import dev.coder2195.stellarity.registry.StellarityBlocks;


public class EnderDirtPath extends PathBlock {
	public EnderDirtPath(Properties properties) {
		super(StellarityBlocks.ENDER_DIRT, properties);
	}

	public static final Properties PROPERTIES = Properties.of()
		.mapColor(MapColor.DIRT)
		.strength(0.65F)
		.sound(BlockSoundSets.GRASS)
		.isViewBlocking((_, _, _, _) -> true)
		.isSuffocating((_, _, _) -> true).forceSolidOn();


	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return
			!this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ?
				Block.pushEntitiesUp(this.defaultBlockState(), StellarityBlocks.ENDER_DIRT.defaultBlockState(), context.getLevel(), context.getClickedPos()) :
				super.getStateForPlacement(context);
	}


	@Override
	protected BlockState updateShape(BlockState blockState, LevelReader levelReader, ScheduledTickAccess scheduledTickAccess, BlockPos blockPos, Direction direction, BlockPos blockPos2, BlockState blockState2, RandomSource randomSource) {
		if (direction == Direction.UP && !blockState.canSurvive(levelReader, blockPos)) {
			return StellarityBlocks.ENDER_DIRT.defaultBlockState();
		}

		return super.updateShape(blockState, levelReader, scheduledTickAccess, blockPos, direction, blockPos2, blockState2, randomSource);
	}


	@SuppressWarnings("deprecation")
	@Override
	public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
		BlockState blockState2 = levelReader.getBlockState(blockPos.above());
		return !(blockState2.isSolid() || blockState2.getBlock() instanceof PathBlock) || blockState2.getBlock() instanceof FenceGateBlock;
	}
}