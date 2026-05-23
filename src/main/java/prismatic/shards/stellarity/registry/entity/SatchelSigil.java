package prismatic.shards.stellarity.registry.entity;

import com.mojang.serialization.Codec;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.registry.StellarityEntityTypes;
import prismatic.shards.stellarity.registry.recipe.AltarRecipe;

public class SatchelSigil extends Entity {
	private int liveTime = 60 * 20;
	private int elapsedTime = 0;

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
			if (elapsedTime % 10 == 0) AltarRecipe.handleItems(
				serverLevel, x, y, z, false, getBoundingBox(),
				(itemEntity) -> Mth.square(position.x - itemEntity.getX()) + Mth.square(position.z - itemEntity.getZ()) < Mth.square(1)
			);

			if (elapsedTime >= liveTime) this.discard();
		}

		elapsedTime++;
	}

	public int getElapsedTime() {
		return elapsedTime;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NonNull Builder entityData) {
	}

	@Override
	public boolean hurtServer(@NonNull ServerLevel level, @NonNull DamageSource source, float damage) {
		return false;
	}

	@Override
	protected void readAdditionalSaveData(ValueInput input) {
		input.read("elapsed_time", Codec.INT).ifPresent(i -> elapsedTime = i);
		input.read("live_time", Codec.INT).ifPresent(i -> liveTime = i);
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		output.storeNullable("elapsed_time", Codec.INT, elapsedTime);
		output.storeNullable("live_time", Codec.INT, liveTime);
	}
}
