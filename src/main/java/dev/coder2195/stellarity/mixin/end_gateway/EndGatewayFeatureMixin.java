package dev.coder2195.stellarity.mixin.end_gateway;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.levelgen.feature.EndGatewayFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.EndGatewayConfiguration;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EndGatewayFeature.class)
public abstract class EndGatewayFeatureMixin extends Feature<EndGatewayConfiguration> {

	public EndGatewayFeatureMixin(Codec<EndGatewayConfiguration> codec) {
		super(codec);
	}

	@WrapMethod(method = "place")
	public boolean stellarityGateway(FeaturePlaceContext<EndGatewayConfiguration> context, Operation<Boolean> original) {
		if (!original.call(context)) return false;
		BlockPos blockPos = context.origin();
		WorldGenLevel level = context.level();

		var wall = Blocks.END_STONE_BRICK_WALL.defaultBlockState();
		var bricks = Blocks.END_STONE_BRICKS.defaultBlockState();
		var bStairs = Blocks.PURPUR_STAIRS.defaultBlockState().setValue(StairBlock.FACING, Direction.NORTH);
		var tStairs = Blocks.PURPUR_STAIRS.defaultBlockState().setValue(StairBlock.FACING, Direction.NORTH).setValue(StairBlock.HALF, Half.TOP);
		var pillar = Blocks.PURPUR_PILLAR.defaultBlockState();
		var etSlab = Blocks.END_STONE_BRICK_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP);
		var ebSlab = Blocks.END_STONE_BRICK_SLAB.defaultBlockState();
		var ptSlab = Blocks.PURPUR_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP);
		var pbSlab = Blocks.PURPUR_SLAB.defaultBlockState();


		for (int i = -1; i <= 1; i += 2) {
			for (Rotation rot : Rotation.values()) {

				boolean b = i == -1;
				var stairs = (b ? tStairs : bStairs).rotate(rot);
				var eSlab = (b ? etSlab : ebSlab);
				var pSlab = (b ? pbSlab : ptSlab);


				setBlock(level, blockPos.offset(new BlockPos(0, i * 3, 1).rotate(rot)), stairs);
				setBlock(level, blockPos.offset(new BlockPos(1, i * 2, 1).rotate(rot)), eSlab);
				setBlock(level, blockPos.offset(new BlockPos(0, i, 2).rotate(rot)), stairs);
				setBlock(level, blockPos.offset(new BlockPos(1, i, 2).rotate(rot)), pSlab);
				setBlock(level, blockPos.offset(new BlockPos(2, i, 1).rotate(rot)), pSlab);
				setBlock(level, blockPos.offset(new BlockPos(1, i, 1).rotate(rot)), bricks);
				setBlock(level, blockPos.offset(new BlockPos(0, i * 2, 1).rotate(rot)), pillar);

				if (i == -1) {
					setBlock(level, blockPos.offset(new BlockPos(1, 0, 1).rotate(rot)), wall);
				}
			}
			setBlock(level, blockPos.above(i * 5), wall);
			setBlock(level, blockPos.above(i * 4), bricks);
		}

		return true;
	}


}
