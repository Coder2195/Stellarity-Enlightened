package dev.coder2195.stellarity.mixin.exit_portal;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.EndPodiumFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(EndPodiumFeature.class)
public abstract class EndPodiumFeatureMixin implements Feature {
	@Shadow
	@Final
	private boolean active;

	@Unique
	private void printRow(WorldGenLevel worldGenLevel, BlockState[] palette, int[][] blueprint, BlockPos start) {
		for (int i = 0; i < blueprint.length; i++) {
			int[] row = blueprint[i];
			for (int j = 0; j < row.length; j++) {
				this.setBlock(worldGenLevel, start.offset(i, 0, j), palette[row[j]]);
			}
		}
	}

	@WrapMethod(method = "place")
	private boolean stellarityPortal(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin, Operation<Boolean> original) {

		for (int dx = -6; dx <= 6; dx++) {
			for (int dz = -6; dz <= 6; dz++) {
				setBlock(level, origin.offset(dx, 0, dz), Blocks.OBSIDIAN.defaultBlockState());
			}
		}


		int p = active ? 4 : 0;
		printRow(level, new BlockState[]{
				Blocks.AIR.defaultBlockState(),
				Blocks.OBSIDIAN.defaultBlockState(),
				Blocks.CRYING_OBSIDIAN.defaultBlockState().getBlock().defaultBlockState(),
				Blocks.BEDROCK.defaultBlockState(),
				Blocks.END_PORTAL.defaultBlockState(),
				Blocks.REINFORCED_DEEPSLATE.defaultBlockState(),
			}, new int[][]{
				{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
				{0, 1, 0, 0, 1, 1, 2, 1, 1, 0, 0, 1, 0},
				{0, 0, 2, 1, 3, 3, 3, 3, 3, 1, 2, 0, 0},
				{0, 0, 1, 5, p, p, 3, p, p, 5, 1, 0, 0},
				{0, 1, 3, p, p, p, 3, p, p, p, 3, 1, 0},
				{0, 1, 3, p, p, 5, 3, 5, p, p, 3, 1, 0},
				{1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 1},
				{0, 1, 3, p, p, 5, 3, 5, p, p, 3, 1, 0},
				{0, 1, 3, p, p, p, 3, p, p, p, 3, 1, 0},
				{0, 0, 1, 5, p, p, 3, p, p, 5, 1, 0, 0},
				{0, 0, 2, 1, 3, 3, 3, 3, 3, 1, 2, 0, 0},
				{0, 1, 0, 0, 1, 1, 2, 1, 1, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			}, origin.offset(-6, 1, -6)
		);


		printRow(level, new BlockState[]{
				Blocks.AIR.defaultBlockState(),
				Blocks.BEDROCK.defaultBlockState(),
				Blocks.REINFORCED_DEEPSLATE.defaultBlockState(),
				Blocks.COBBLED_DEEPSLATE_SLAB.defaultBlockState(),
				Blocks.COBBLED_DEEPSLATE_STAIRS.defaultBlockState().setValue(StairBlock.FACING, Direction.NORTH),
				Blocks.COBBLED_DEEPSLATE_STAIRS.defaultBlockState().setValue(StairBlock.FACING, Direction.SOUTH),
				Blocks.COBBLED_DEEPSLATE_STAIRS.defaultBlockState().setValue(StairBlock.FACING, Direction.EAST),
				Blocks.COBBLED_DEEPSLATE_STAIRS.defaultBlockState().setValue(StairBlock.FACING, Direction.WEST),
			}, new int[][]{

				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 3, 5, 1, 4, 3, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0},
				{0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0},
				{0, 0, 6, 0, 0, 2, 1, 2, 0, 0, 6, 0, 0},
				{0, 5, 1, 4, 3, 1, 1, 1, 3, 5, 1, 4, 0},
				{0, 0, 7, 0, 0, 2, 1, 2, 0, 0, 7, 0, 0},
				{0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0},
				{0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 3, 5, 1, 4, 3, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			}, origin.offset(-6, 2, -6)
		);

		for (int dy = 3; dy <= 6; dy++)
			for (int dx = -6; dx <= 6; dx++) {
				for (int dz = -6; dz <= 6; dz++) {
					setBlock(level, origin.offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
				}
			}

		printRow(level, new BlockState[]{
			Blocks.BEDROCK.defaultBlockState(),
			Blocks.REINFORCED_DEEPSLATE.defaultBlockState(),
		}, new int[][]{
			{1, 0, 1},
			{0, 0, 0},
			{1, 0, 1}
		}, origin.offset(-1, 3, -1));

		printRow(level, new BlockState[]{
			Blocks.BEDROCK.defaultBlockState(),
			Blocks.AIR.defaultBlockState(),
			Blocks.COBBLED_DEEPSLATE_STAIRS.defaultBlockState().setValue(StairBlock.FACING, Direction.NORTH),
			Blocks.COBBLED_DEEPSLATE_STAIRS.defaultBlockState().setValue(StairBlock.FACING, Direction.SOUTH),
			Blocks.COBBLED_DEEPSLATE_STAIRS.defaultBlockState().setValue(StairBlock.FACING, Direction.EAST),
			Blocks.COBBLED_DEEPSLATE_STAIRS.defaultBlockState().setValue(StairBlock.FACING, Direction.WEST),
		}, new int[][]{
			{1, 4, 1},
			{3, 0, 2},
			{1, 5, 1}
		}, origin.offset(-1, 4, -1));

		setBlock(level, origin.above(5), Blocks.BEDROCK.defaultBlockState());
		setBlock(level, origin.above(6), Blocks.BEDROCK.defaultBlockState());

		return true;
	}
}
