package prismatic.shards.stellarity.registry.entity;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.registry.StellarityEntityDataSerializers;
import prismatic.shards.stellarity.registry.StellarityEntityTypes;
import prismatic.shards.stellarity.registry.recipe.AltarRecipe;

import java.util.function.IntFunction;

public class SatchelSigil extends Entity {
	private int localElapsedTime = 0;
	public static final int DEFAULT_ACTIVE_TIME = 60 * 20;
	public static final int TRANSITION_DURATION = 10;

	public enum State {
		OPENING(0),
		ACTIVE(1),
		CLOSING(2);

		private final int id;

		State(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}

		public static final IntFunction<State> BY_ID = ByIdMap.continuous(State::getId, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
		public static final StreamCodec<ByteBuf, State> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, State::getId);
	}

	private static final EntityDataAccessor<State> DATA_STATE = SynchedEntityData.defineId(SatchelSigil.class, StellarityEntityDataSerializers.SATCHEL_SIGIL_STATE);
	private static final EntityDataAccessor<Integer> DATA_ELAPSED_TIME = SynchedEntityData.defineId(SatchelSigil.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> DATA_LIVE_TIME = SynchedEntityData.defineId(SatchelSigil.class, EntityDataSerializers.INT);

	public SatchelSigil(Level level) {
		super(StellarityEntityTypes.SATCHEL_SIGIL, level);
	}

	public SatchelSigil(EntityType<?> type, Level level) {
		super(type, level);
	}


	@Override
	public void tick() {
		super.tick();

		var level = level();
		var position = position();
		var x = position.x;
		var y = position.y;
		var z = position.z;


		if (level instanceof ServerLevel serverLevel) {
			if (localElapsedTime % 10 == 0 && isActive()) AltarRecipe.handleItems(
				serverLevel, x, y, z, false, getBoundingBox(),
				(itemEntity) -> Mth.square(position.x - itemEntity.getX()) + Mth.square(position.z - itemEntity.getZ()) < Mth.square(1)
			);


			if (localElapsedTime == TRANSITION_DURATION) setState(State.ACTIVE);
			else if (localElapsedTime == TRANSITION_DURATION + getLiveTime()) setState(State.CLOSING);
			else if (localElapsedTime > 2 * TRANSITION_DURATION + getLiveTime()) discard();
		} else if (getState().equals(State.ACTIVE)) {
			float angle = localElapsedTime / 20f;
			double dx = Mth.cos(angle) * 2;
			double dz = Mth.sin(angle) * 2;

			var purpleParticle = new DustColorTransitionOptions(0xff00ff, 0xff00ff, 1.4f);

			level.addParticle(
				purpleParticle,
				x + dx, y, z + dz,
				0, 0, 0
			);


			level.addParticle(
				purpleParticle,
				x - dx, y, z - dz,
				0, 0, 0
			);


			dx = Mth.cos(-angle) * 1.5;
			dz = Mth.sin(-angle) * 1.5;


			level.addParticle(
				purpleParticle,
				x + dx, y, z + dz,
				0, 0, 0
			);

			level.addParticle(
				purpleParticle,
				x - dx, y, z - dz,
				0, 0, 0
			);


			if (localElapsedTime % 3 == 0) {
				dx = level.getRandom().nextGaussian() * 0.5;
				dz = level.getRandom().nextGaussian() * 0.5;
				level.addParticle(
					ParticleTypes.ENCHANT,
					x + dx, y + 1.5, z + dz,
					dx * 2, -1.5, dz * 2
				);
			}
		}

		localElapsedTime++;
	}

	public boolean isActive() {
		return getState().equals(State.ACTIVE);
	}

	public int getLocalElapsedTime() {
		return localElapsedTime;
	}

	public int getElapsedTime() {
		return entityData.get(DATA_ELAPSED_TIME);
	}

	public void syncElapsedTime() {
		entityData.set(DATA_ELAPSED_TIME, localElapsedTime);
	}

	public void setLocalElapsedTime(int localElapsedTime) {
		this.localElapsedTime = localElapsedTime;
	}

	public int getLiveTime() {
		return entityData.get(DATA_LIVE_TIME);
	}

	public void setLiveTime(int liveTime) {
		entityData.set(DATA_LIVE_TIME, liveTime);
	}

	public State getState() {
		return entityData.get(DATA_STATE);
	}

	public void setState(State state) {
		entityData.set(DATA_STATE, state);
		syncElapsedTime();
	}


	@Override
	protected void defineSynchedData(SynchedEntityData.@NonNull Builder entityData) {
		entityData.define(DATA_STATE, State.OPENING);
		entityData.define(DATA_ELAPSED_TIME, 0);
		entityData.define(DATA_LIVE_TIME, DEFAULT_ACTIVE_TIME);
	}

	@Override
	protected void readAdditionalSaveData(ValueInput input) {
		input.read("live_time", Codec.INT).map(i -> i < 0 ? 0 : i).ifPresent(this::setLiveTime);
		input.read("elapsed_time", Codec.INT).ifPresent(this::setLocalElapsedTime);

		if (getLocalElapsedTime() >= TRANSITION_DURATION) setState(State.ACTIVE);
		if (getLocalElapsedTime() >= TRANSITION_DURATION + getLiveTime()) setState(State.CLOSING);
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		output.storeNullable("elapsed_time", Codec.INT, getLocalElapsedTime());
		output.storeNullable("live_time", Codec.INT, getLiveTime());
	}

	@Override
	public void onSyncedDataUpdated(@NonNull EntityDataAccessor<?> accessor) {
		super.onSyncedDataUpdated(accessor);

		if (accessor.equals(DATA_STATE)) {
			var state = getState();
			if (state == State.ACTIVE) localElapsedTime = TRANSITION_DURATION;
		}
	}


	@Override
	public boolean hurtServer(@NonNull ServerLevel level, @NonNull DamageSource source, float damage) {
		return false;
	}
}
