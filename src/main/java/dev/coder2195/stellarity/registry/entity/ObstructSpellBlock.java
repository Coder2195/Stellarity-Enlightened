package dev.coder2195.stellarity.registry.entity;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class ObstructSpellBlock extends Entity implements Attackable {
	private int liveTime = 10 * 20;

	private static final EntityDataAccessor<BlockState> DATA_BLOCK_STATE_ID = SynchedEntityData.defineId(
		ObstructSpellBlock.class, EntityDataSerializers.BLOCK_STATE
	);

	public ObstructSpellBlock(EntityType<?> type, Level level) {
		super(type, level);
		setNoGravity(true);
		this.reapplyPosition();
	}

	@Override
	public boolean canBeHitByProjectile() {
		return true;
	}


	@Override
	public boolean canBeCollidedWith(@Nullable Entity other) {
		return true;
	}

	@Override
	public boolean canCollideWith(@NonNull Entity entity) {
		return true;
	}

	@Override
	public void tick() {
		liveTime--;

		if (liveTime <= 0) discard();
	}

	@Override
	public boolean isAlive() {
		return isRemoved();
	}

	@Override
	public boolean canUsePortal(boolean ignorePassenger) {
		return false;
	}

	@Override
	public boolean isPickable() {
		return true;
	}

	@Override
	public boolean canInteractWithLevel() {
		return true;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder entityData) {
		entityData.define(DATA_BLOCK_STATE_ID, Blocks.SHULKER_BOX.defaultBlockState());
	}

	@Override
	public @NonNull EntityDimensions getDimensions(final @NonNull Pose pose) {
		return EntityDimensions.fixed(1, 1);
	}

	@Override
	protected @NonNull AABB makeBoundingBox(@NonNull Vec3 position) {
		return this.getDimensions(Pose.STANDING).makeBoundingBox(position);
	}

	@Override
	public boolean skipAttackInteraction(@NonNull Entity source) {
		return false;
	}

	@Override
	public boolean isIgnoringBlockTriggers() {
		return true;
	}

	@Override
	public @NonNull PushReaction getPistonPushReaction() {
		return PushReaction.IGNORE;
	}

	@Override
	public boolean hurtServer(@NonNull ServerLevel level, @NonNull DamageSource source, float damage) {
		return false;
	}

	@Override
	protected void readAdditionalSaveData(ValueInput input) {
		input.read("live_time", Codec.INT).ifPresent(this::setLiveTime);
		input.read("block_state", BlockState.CODEC).ifPresent(this::setBlockState);
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		output.store("live_time", Codec.INT, liveTime);
		output.store("block_state", BlockState.CODEC, getBlockState());
	}

	public int getLiveTime() {
		return liveTime;
	}

	public void setLiveTime(int liveTime) {
		this.liveTime = liveTime;
	}

	public BlockState getBlockState() {
		return this.entityData.get(DATA_BLOCK_STATE_ID);
	}

	public void setBlockState(BlockState blockState) {
		this.entityData.set(DATA_BLOCK_STATE_ID, blockState, true);
	}

	@Override
	public @Nullable LivingEntity getLastAttacker() {
		return null;
	}

	@Override
	public void onClientRemoval() {
		super.onClientRemoval();

		level().addAlwaysVisibleParticle(ParticleTypes.EXPLOSION, getX(), getY(), getZ(), 0, 0, 0);
	}
}
