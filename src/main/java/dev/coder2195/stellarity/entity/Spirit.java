package dev.coder2195.stellarity.entity;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

public class Spirit extends Entity {
	@Nullable
	private EntityReference<LivingEntity> owner = null;

	public Spirit(EntityType<?> type, Level level) {
		super(type, level);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder entityData) {

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
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		output.storeNullable("owner", EntityReference.codec(), owner);
	}
}
