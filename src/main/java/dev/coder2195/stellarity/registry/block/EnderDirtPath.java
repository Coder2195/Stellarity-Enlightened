package dev.coder2195.stellarity.registry.block;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirtPathBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import org.jspecify.annotations.NonNull;
import dev.coder2195.stellarity.registry.StellarityBlocks;


public class EnderDirtPath extends DirtPathBlock {
	public EnderDirtPath(Properties properties) {
		super(properties);
	}

	public static final Properties PROPERTIES = Properties.of()
		.mapColor(MapColor.DIRT)
		.strength(0.65F)
		.sound(SoundType.GRASS)
		.isViewBlocking((_, _, _) -> true)
		.isSuffocating((_, _, c) -> true).forceSolidOn();


	@Override
	public @NonNull BlockState getStateForPlacement(BlockPlaceContext context) {
		return
			!this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ?
				Block.pushEntitiesUp(this.defaultBlockState(), StellarityBlocks.ENDER_DIRT.defaultBlockState(), context.getLevel(), context.getClickedPos()) :
				super.getStateForPlacement(context);
	}


	@Override
	protected @NonNull BlockState updateShape(@NonNull BlockState blockState, @NonNull LevelReader levelReader, @NonNull ScheduledTickAccess scheduledTickAccess, @NonNull BlockPos blockPos, @NonNull Direction direction, @NonNull BlockPos blockPos2, @NonNull BlockState blockState2, @NonNull RandomSource randomSource) {
		if (direction == Direction.UP && !blockState.canSurvive(levelReader, blockPos)) {
			return StellarityBlocks.ENDER_DIRT.defaultBlockState();
		}

		return super.updateShape(blockState, levelReader, scheduledTickAccess, blockPos, direction, blockPos2, blockState2, randomSource);
	}


	@SuppressWarnings("deprecation")
	@Override
	public boolean canSurvive(@NonNull BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
		BlockState blockState2 = levelReader.getBlockState(blockPos.above());
		return !(blockState2.isSolid() || blockState2.getBlock() instanceof DirtPathBlock) || blockState2.getBlock() instanceof FenceGateBlock;
	}
}