package dev.coder2195.stellarity.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.jspecify.annotations.Nullable;

public class Spirit extends Entity {
	@Nullable
	private EntityReference<LivingEntity> owner = null;

	private static final EntityDataAccessor<Vector3fc> DATA_INNER_ANGULAR_VELOCITY = SynchedEntityData.defineId(Spirit.class, EntityDataSerializers.VECTOR3);
	private static final EntityDataAccessor<Vector3fc> DATA_AURA_ANGULAR_VELOCITY = SynchedEntityData.defineId(Spirit.class, EntityDataSerializers.VECTOR3);

	public Spirit(EntityType<?> type, Level level) {
		super(type, level);

		RandomSource random = RandomSource.create();
		setInnerAngularVelocity(new Vector3f(
			random.nextBoolean() ? 1 : -1 * (random.nextFloat() * 0.2f + 0.2f),
			random.nextBoolean() ? 1 : -1 * (random.nextFloat() * 0.4f + 0.5f),
			random.nextBoolean() ? 1 : -1 * (random.nextFloat() * 0.8f + 1f)
		));
		setAuraAngularVelocity(new Vector3f(
			random.nextBoolean() ? 1 : -1 * (random.nextFloat() * 0.4f + 0.4f),
			random.nextBoolean() ? 1 : -1 * (random.nextFloat() * 0.8f + 0.9f),
			random.nextBoolean() ? 1 : -1 * (random.nextFloat() * 1.6f + 1.8f)
		));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder entityData) {
		entityData.define(DATA_INNER_ANGULAR_VELOCITY, new Vector3f(0, 0, 0));
		entityData.define(DATA_AURA_ANGULAR_VELOCITY, new Vector3f(0, 0, 0));
	}


	public Vector3fc getInnerAngularVelocity() {
		return entityData.get(DATA_INNER_ANGULAR_VELOCITY);
	}

	public void setInnerAngularVelocity(Vector3fc vec) {
		entityData.set(DATA_INNER_ANGULAR_VELOCITY, vec);
	}

	public Vector3fc getAuraAngularVelocity() {
		return entityData.get(DATA_AURA_ANGULAR_VELOCITY);
	}

	public void setAuraAngularVelocity(Vector3fc vec) {
		entityData.set(DATA_AURA_ANGULAR_VELOCITY, vec);
	}

	@Override
	public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
		return false;
	}

	public void setOwner(@Nullable EntityReference<LivingEntity> owner) {
		this.owner = owner;
	}

	public void setOwner(@Nullable LivingEntity owner) {
		this.owner = owner == null ? null : EntityReference.of(owner);
	}

	public @Nullable LivingEntity getOwner() {
		return owner == null ? null : owner.getEntity(level(), LivingEntity.class);
	}

	@Override
	protected void readAdditionalSaveData(ValueInput input) {
		input.read("owner", EntityReference.<LivingEntity>codec()).ifPresent(this::setOwner);
		input.read("inner_angular_velocity", ExtraCodecs.VECTOR3F).ifPresent(this::setInnerAngularVelocity);
		input.read("aura_angular_velocity", ExtraCodecs.VECTOR3F).ifPresent(this::setAuraAngularVelocity);
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		output.storeNullable("owner", EntityReference.codec(), owner);
		output.storeNullable("inner_angular_velocity", ExtraCodecs.VECTOR3F, getInnerAngularVelocity());
		output.storeNullable("aura_angular_velocity", ExtraCodecs.VECTOR3F, getAuraAngularVelocity());
	}
}
