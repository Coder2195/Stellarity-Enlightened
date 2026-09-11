package dev.coder2195.stellarity.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.coder2195.stellarity.Stellarity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;
import java.util.function.Predicate;

public record EndCrystalTowerFeature(IntProvider diameter, IntProvider height, Holder<BlockStateProvider> material, boolean extendDown, Optional<BlockPredicate> canReplace) implements Feature {
	public static final MapCodec<EndCrystalTowerFeature> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
		IntProviders.codec(1, 16).fieldOf("diameter").forGetter(EndCrystalTowerFeature::diameter),
		IntProviders.POSITIVE_CODEC.fieldOf("height").forGetter(EndCrystalTowerFeature::height),
		BlockStateProvider.CODEC.fieldOf("material").forGetter(EndCrystalTowerFeature::material),
		Codec.BOOL.optionalFieldOf("extend_down", true).forGetter(EndCrystalTowerFeature::extendDown),
		BlockPredicate.CODEC.optionalFieldOf("can_replace").forGetter(EndCrystalTowerFeature::canReplace)
	).apply(instance, EndCrystalTowerFeature::new));

	@Override
	public MapCodec<? extends Feature> codec() {
		return CODEC;
	}

	public static final boolean[][][] PATTERNS = {
		{},
		{{true}},
		{{true, true}, {true, true}},
		{{false, true, false}, {true, true, true}, {false, true, false}},
		{{false, true, true, false}, {true, true, true, true}, {true, true, true, true}, {false, true, true, false}},
		pattern(5), pattern(6), pattern(7), pattern(8),
		pattern(9), pattern(10), pattern(11), pattern(12), pattern(13),
		pattern(14), pattern(15), pattern(16)
	};

	public static boolean[][] pattern(int diameter) {
		var booleanArr = new boolean[diameter][diameter];
		int radius = diameter / 2;
		int evenOffset = diameter % 2 == 1 ? 0 : 1;


		// https://gamedev.stackexchange.com/questions/176036/how-to-draw-a-smoother-solid-fill-circle here so j don't forget
		int d = (5 - radius * 4) / 4;
		int x = 0;
		int y = radius;

		do {
			//noinspection DuplicatedCode
			booleanArr[radius + x - evenOffset][radius + y - evenOffset] = true;
			booleanArr[radius + x - evenOffset][radius - y] = true;
			booleanArr[radius - x][radius + y - evenOffset] = true;
			booleanArr[radius - x][radius - y] = true;
			//noinspection DuplicatedCode
			booleanArr[radius + y - evenOffset][radius + x - evenOffset] = true;
			booleanArr[radius + y - evenOffset][radius - x] = true;
			booleanArr[radius - y][radius + x - evenOffset] = true;
			booleanArr[radius - y][radius - x] = true;
			if (d < 0) {
				d += 2 * x + 1;
			} else {
				d += 2 * (x - y) + 1;
				y--;
			}
			x++;
		} while (x <= y);

		for (int i = 1; i < booleanArr.length - 1; i++) {
			boolean[] row = booleanArr[i];
			int j = 0;
			while (!row[j]) j++;
			while (row[j]) j++;
			while (!row[j] && j < row.length - 1) {
				row[j] = true;
				j++;
			}
		}

		return booleanArr;
	}

	@Override
	public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
		var diameter = this.diameter.sample(random);
		var height = this.height.sample(random);

		int maxY = Math.min(level.getMaxY(), origin.getY() + height);
		var crystalPos = Vec3.atBottomCenterOf(origin.atY(maxY + 1)).add(diameter % 2 == 0 ? -0.5 : 0, 0, diameter % 2 == 0 ? -0.5 : 0);

		int start = diameter / 2;
		origin = origin.offset(-start, 0, -start);
		int originX = origin.getX();
		int originZ = origin.getZ();

		var toPlace = material.value();

		var pattern = PATTERNS[Mth.clamp(diameter, 1, 16)];
		int minY = level.getMinY();
		Predicate<BlockPos> replaceable = canReplace.map((predicate) -> (Predicate<BlockPos>) ((pos) -> predicate.test(level, pos))).orElse((pos) -> {
			var blockState = level.getBlockState(pos);
			return blockState.canBeReplaced();
		});

		BlockPos.MutableBlockPos mutableBlockPos = origin.mutable();
		for (int dx = 0; dx < diameter; dx++) {
			mutableBlockPos.setX(originX + dx);
			for (int dz = 0; dz < diameter; dz++) {
				if (!pattern[dx][dz]) continue;

				mutableBlockPos.setZ(originZ + dz);
				for (int y = origin.getY(); y < maxY; y++) {
					mutableBlockPos.setY(y);
					if (replaceable.test(mutableBlockPos)) setBlock(level, mutableBlockPos, toPlace.getState(level, random, mutableBlockPos));
				}

				if (!extendDown) continue;

				int y = origin.getY();

				while (--y >= minY) {
					mutableBlockPos.setY(y);
					if (!replaceable.test(mutableBlockPos)) break;
					setBlock(level, mutableBlockPos, toPlace.getState(level, random, mutableBlockPos));
				}

			}
		}

		var endCrystal = EntityTypes.END_CRYSTAL.create(level.getLevel(), EntitySpawnReason.CHUNK_GENERATION);
		if (endCrystal == null) {
			Stellarity.LOGGER.info("Unable to create End crystal for Crystal Tower Feature for some reason");
			return true;
		}
		endCrystal.setPos(crystalPos);
		level.addFreshEntity(endCrystal);

		return true;
	}
}
