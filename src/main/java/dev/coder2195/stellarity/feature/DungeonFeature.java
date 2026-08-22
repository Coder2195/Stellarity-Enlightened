package dev.coder2195.stellarity.feature;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.coder2195.stellarity.registry.StellarityLootTables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.function.Predicate;

import static dev.coder2195.stellarity.util.ValueUtil.*;
import static net.minecraft.tags.BlockTags.AIR;
import static net.minecraft.tags.BlockTags.FEATURES_CANNOT_REPLACE;
import static net.minecraft.world.level.block.Blocks.*;

public record DungeonFeature(
	EntityType<?> entityType, IntProvider size, IntProvider height, IntProvider chests
) implements Feature {
	public static final MapCodec<DungeonFeature> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
		EntityType.CODEC.optionalFieldOf("entity_type", EntityTypes.ENDERMITE).forGetter(DungeonFeature::entityType),
		IntProviders.CODEC.optionalFieldOf("size", num(4)).forGetter(DungeonFeature::size),
		IntProviders.CODEC.optionalFieldOf("height", num(6)).forGetter(DungeonFeature::height),
		IntProviders.CODEC.optionalFieldOf("chests", num(1, 2)).forGetter(DungeonFeature::chests)
	).apply(i, DungeonFeature::new));

	public DungeonFeature() {
		this(EntityTypes.ENDERMITE, num(4), num(6), num(1, 2));
	}

	@Override
	public boolean place(@NonNull WorldGenLevel level, @NonNull ChunkGenerator chunkGenerator, @NonNull RandomSource random, @NonNull BlockPos origin) {

		var size = this.size.sample(random);
		var height = this.height.sample(random);
		var chests = Math.max(this.chests.sample(random), 0);
		Predicate<BlockState> replaceable = (state) -> state.is(AIR) || !state.is(FEATURES_CANNOT_REPLACE);

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

	@Override
	public @NonNull MapCodec<? extends Feature> codec() {
		return CODEC;
	}

}
