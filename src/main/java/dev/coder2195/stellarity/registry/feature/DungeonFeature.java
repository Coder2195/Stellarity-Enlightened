package dev.coder2195.stellarity.registry.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import dev.coder2195.stellarity.registry.StellarityLootTables;
import dev.coder2195.stellarity.registry.feature.configuration.DungeonFeatureConfiguration;

import java.util.ArrayList;

import static net.minecraft.tags.BlockTags.AIR;
import static net.minecraft.tags.BlockTags.FEATURES_CANNOT_REPLACE;
import static net.minecraft.world.level.block.Blocks.*;
import static dev.coder2195.stellarity.util.ValueUtil.from;
import static dev.coder2195.stellarity.util.ValueUtil.property;

public class DungeonFeature extends Feature<DungeonFeatureConfiguration> {
	public DungeonFeature(Codec<DungeonFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<DungeonFeatureConfiguration> context) {
		var config = context.config();

		var replaceable = Feature.isReplaceable(FEATURES_CANNOT_REPLACE).and(state -> !state.is(AIR));
		var origin = context.origin();
		var level = context.level();
		var oldSpawnerState = level.getBlockState(origin);
		var random = context.random();
		var size = config.size().sample(random);
		var height = config.height().sample(random);
		var chests = Math.max(config.chests().sample(random), 0);
		var entityType = config.entityType();

		if (size < 3 || height < 4)
			return false;


		ArrayList<BlockPos> possibleChestPositions = new ArrayList<>();
		int insideSize = size - 2;


		for (int x = -insideSize; x <= insideSize; x++) {
			for (int z = -insideSize; z <= insideSize; z++) {

				BlockPos pos = origin.offset(x, 0, z);
				var state = level.getBlockState(pos);
				if (state.is(AIR) || level.getBlockState(origin.offset(x, -1, z)).is(AIR)) return false;
				if (x == 0 && z == 0) continue;
				if (!replaceable.test(state)) continue;
				possibleChestPositions.add(pos);
			}
		}

		if (possibleChestPositions.size() < chests) return false;


		for (int i = 0; i < chests; i++) {
			var pos = possibleChestPositions.remove(random.nextInt(possibleChestPositions.size()));
			setBlock(level, pos, Blocks.CHEST.defaultBlockState());
			if (level.getBlockEntity(pos) instanceof ChestBlockEntity chest) {
				chest.setLootTable(StellarityLootTables.DUNGEON);
			}
		}


		for (Rotation rot : Rotation.values()) {
			BlockState stairs = property(property(PURPUR_STAIRS, BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH), BlockStateProperties.STAIRS_SHAPE, StairsShape.STRAIGHT);
			BlockState upsideDownStairs = stairs.setValue(BlockStateProperties.HALF, Half.TOP);
			BlockState endStairs = property(property(END_STONE_BRICK_STAIRS, BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH), BlockStateProperties.STAIRS_SHAPE, StairsShape.STRAIGHT);
			BlockState downEndStairs = endStairs.setValue(BlockStateProperties.HALF, Half.TOP);
			BlockState purpur = from(PURPUR_BLOCK);
			BlockState pillar = from(PURPUR_PILLAR);
			BlockState bricks = from(END_STONE_BRICKS);

			for (int y = -1; y <= height - 2; y++) {
				boolean floor = y == -1;
				boolean innerFloor = y == 0;
				boolean ceiling = y == height - 2;
				for (int x = 0; x <= size; x++) {
					for (int z = 1; z <= size; z++) {


						var offset = new BlockPos(x, y, z).rotate(rot);
						var vecOff = new Vec3i(offset.getX(), offset.getY(), offset.getZ());
						BlockState toSet = from(Blocks.AIR);

						boolean isCorner = x == size && z == size;
						boolean isEdge = x == size || z == size;
						boolean innerCorner = x == size - 1 && z == size - 1;
						if (floor || ceiling) {
							toSet = isCorner ?
								purpur : isEdge ?
												 floor ? stairs :
												 upsideDownStairs
												 :
												 bricks;
						} else if (innerFloor || y == height - 3) {
							var innerEdge = x == size - 1 || z == size - 1;
							toSet = isCorner || innerCorner ? pillar :
								isEdge ?
								innerFloor ? upsideDownStairs : stairs
								: innerEdge ?
									innerFloor ? endStairs : downEndStairs
									:
									toSet;
						} else {
							toSet = isCorner || innerCorner ? pillar : isEdge ? bricks : toSet;
						}

						if (x > z) {
							toSet = toSet.rotate(Rotation.COUNTERCLOCKWISE_90);
						}

						safeSetBlock(level, origin.offset(vecOff), toSet.rotate(rot), replaceable);

					}
				}
				safeSetBlock(level, origin.offset(0, y, 0), floor || ceiling ? bricks : innerFloor ? from(Blocks.SPAWNER) : from(END_STONE_BRICK_WALL), replaceable);
			}
		}

		if (level.getBlockEntity(origin) instanceof SpawnerBlockEntity spawner) {
			spawner.setEntityId(entityType, random);
		}

		return true;

	}
}
