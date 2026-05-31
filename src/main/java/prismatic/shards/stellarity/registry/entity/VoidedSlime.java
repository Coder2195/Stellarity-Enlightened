package prismatic.shards.stellarity.registry.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.cubemob.Slime;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.registry.StellarityEntityTypes;
import prismatic.shards.stellarity.registry.StellarityMobEffects;

public class VoidedSlime extends Slime {
	public VoidedSlime(EntityType<? extends VoidedSlime> type, Level level) {
		super(type, level);
	}

	public VoidedSlime(Level level) {
		this(StellarityEntityTypes.VOIDED_SLIME, level);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes();
	}

	@Override
	public boolean doHurtTarget(@NonNull ServerLevel level, @NonNull Entity target) {
		if (!super.doHurtTarget(level, target)) return false;

		if (target instanceof LivingEntity entity) {
			entity.addEffect(new MobEffectInstance(StellarityMobEffects.VOIDED, 10 * 20));
		}

		return true;
	}

	public static boolean checkSpawnRules(
		EntityType<? extends VoidedSlime> type, LevelAccessor level, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random
	) {
		if (level.getDifficulty() == Difficulty.PEACEFUL) return false;

		if (EntitySpawnReason.isSpawner(spawnReason)) {
			return checkMobSpawnRules(type, level, spawnReason, pos, random);
		}

		if (level.getBiome(pos).is(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS)) {
			float surfaceSlimeSpawnChance = level.environmentAttributes().getValue(EnvironmentAttributes.SURFACE_SLIME_SPAWN_CHANCE, pos);
			if (random.nextFloat() < surfaceSlimeSpawnChance && level.getMaxLocalRawBrightness(pos) <= 7) {
				return checkMobSpawnRules(type, level, spawnReason, pos, random);
			}
		}

		if (!(level instanceof WorldGenLevel)) return false;

		ChunkPos chunkPos = ChunkPos.containing(pos);
		boolean slimeChunk = WorldgenRandom.seedSlimeChunk(chunkPos.x(), chunkPos.z(), ((WorldGenLevel) level).getSeed(), 987234911L).nextInt(10) == 0;
		if (random.nextInt(10) == 0 && slimeChunk && pos.getY() < 40) {
			return checkMobSpawnRules(type, level, spawnReason, pos, random);
		}


		return false;
	}
}
