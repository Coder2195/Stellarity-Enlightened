package prismatic.shards.stellarity.registry.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import prismatic.shards.stellarity.util.ColorUtil;

import java.util.EnumSet;
import java.util.Set;

public class Pixie extends PathfinderMob {
	public Pixie(EntityType<? extends PathfinderMob> type, Level level) {
		super(type, level);

		this.moveControl = new PixieMoveControl(this);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0F).add(Attributes.FLYING_SPEED, 0.1F).add(Attributes.MOVEMENT_SPEED, 0.1F).add(Attributes.ATTACK_DAMAGE, 2.0F);
	}

	private @Nullable Holder<Biome> originBiome;

	public @Nullable Holder<Biome> getOriginBiome() {
		return originBiome;
	}


	@Override
	public void baseTick() {
		super.baseTick();

		var level = level();
		if (level.isClientSide()) {
			level.addAlwaysVisibleParticle(new DustParticleOptions(ColorUtil.hsvToRgb(random.nextFloat() * 360, 1 - random.nextFloat() * 0.2f, 1 - random.nextFloat() * 0.2f), 3f), true, getX(), getY(), getZ(), 0, 0, 0);
		}
	}

	public Holder<Biome> getOriginBiomeDefault() {
		if (originBiome == null) setOriginBiome(level().getBiome(blockPosition()));
		return originBiome;
	}

	public void setOriginBiome(@NonNull Holder<Biome> originBiome) {
		this.originBiome = originBiome;
	}

	@Override
	protected void readAdditionalSaveData(@NonNull ValueInput input) {
		super.readAdditionalSaveData(input);
		input.read("origin_biome", Biome.CODEC).ifPresent(this::setOriginBiome);
	}

	@Override
	protected void addAdditionalSaveData(@NonNull ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.storeNullable("origin_biome", Biome.CODEC, getOriginBiome());
	}

	@Override
	public boolean teleportTo(@NonNull ServerLevel level, double x, double y, double z, @NonNull Set<Relative> relatives, float newYRot, float newXRot, boolean resetCamera) {
		if (!super.teleportTo(level, x, y, z, relatives, newYRot, newXRot, resetCamera)) return false;
		moveControl.setWantedPosition(x, y, z, 0.25);
		return true;
	}

	@Override
	public @Nullable SpawnGroupData finalizeSpawn(@NonNull ServerLevelAccessor level, @NonNull DifficultyInstance difficulty, @NonNull EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {
		super.finalizeSpawn(level, difficulty, spawnReason, groupData);

		setOriginBiome(level.getBiome(blockPosition()));

		return groupData;
	}

	@Override
	public void tick() {
		this.noPhysics = true;
		this.setNoGravity(true);
		super.tick();
	}


	@Override
	protected void registerGoals() {
		super.registerGoals();

		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new RandomMoveGoal());
	}

	public static boolean checkPixieSpawnRules(EntityType<Pixie> ignored1, ServerLevelAccessor ignored2, EntitySpawnReason ignored3, BlockPos ignored4, RandomSource ignored5) {
		return true;
	}

	private class RandomMoveGoal extends Goal {
		public RandomMoveGoal() {
			super();
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean canUse() {
			return !Pixie.this.getMoveControl().hasWanted() && Pixie.this.random.nextInt(reducedTickDelay(7)) == 0;
		}

		@Override
		public boolean canContinueToUse() {
			return false;
		}

		@Override
		public void tick() {
			var origin = Pixie.this.blockPosition();
			var level = Pixie.this.level();

			if (!level.isEmptyBlock(origin) && !moveControl.hasWanted()) {
				Pixie.this.moveControl.setWantedPosition(Pixie.this.getX(), Pixie.this.getY() + 5.0, Pixie.this.getZ(), 0.25);
				return;
			}
			for (int attempts = 0; attempts < 3; attempts++) {
				BlockPos testPos = origin.offset(Pixie.this.random.nextInt(15) - 7, Pixie.this.random.nextInt(11) - 5, Pixie.this.random.nextInt(15) - 7);
				if (level.isEmptyBlock(testPos) && level.getBiome(testPos).unwrapKey().map(key -> getOriginBiomeDefault().is(key)).orElse(false)) {
					Pixie.this.moveControl.setWantedPosition(testPos.getX() + 0.5, testPos.getY() + 0.5, testPos.getZ() + 0.5, 0.25);
					if (Pixie.this.getTarget() == null) {
						Pixie.this.getLookControl().setLookAt(testPos.getX() + 0.5, testPos.getY() + 0.5, testPos.getZ() + 0.5, 180.0F, 20.0F);
					}
					break;
				}
			}

		}
	}

	private class PixieMoveControl extends MoveControl<Pixie> {
		public PixieMoveControl(final Pixie pixie) {
			super(pixie);
		}

		@Override
		public void tick() {
			if (this.operation == MoveControl.Operation.MOVE_TO) {
				Vec3 delta = new Vec3(this.wantedX - Pixie.this.getX(), this.wantedY - Pixie.this.getY(), this.wantedZ - Pixie.this.getZ());
				double deltaLength = delta.length();
				if (deltaLength < Pixie.this.getBoundingBox().getSize()) {
					this.operation = MoveControl.Operation.WAIT;
					Pixie.this.setDeltaMovement(Pixie.this.getDeltaMovement().scale(0.5));
				} else {
					Pixie.this.setDeltaMovement(Pixie.this.getDeltaMovement().add(delta.scale(this.speedModifier * 0.05 / deltaLength)));
					if (Pixie.this.getTarget() == null) {
						Vec3 movement = Pixie.this.getDeltaMovement();
						Pixie.this.setYRot(-((float) Mth.atan2(movement.x, movement.z)) * (180.0F / (float) Math.PI));
					} else {
						double tx = Pixie.this.getTarget().getX() - Pixie.this.getX();
						double tz = Pixie.this.getTarget().getZ() - Pixie.this.getZ();
						Pixie.this.setYRot(-((float) Mth.atan2(tx, tz)) * (180.0F / (float) Math.PI));
					}
					Pixie.this.yBodyRot = Pixie.this.getYRot();
				}
			}
		}
	}


}
