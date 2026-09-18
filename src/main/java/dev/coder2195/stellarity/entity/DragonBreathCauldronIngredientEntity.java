package dev.coder2195.stellarity.entity;

import dev.coder2195.stellarity.block_entity.DragonBreathCauldronBlockEntity;
import dev.coder2195.stellarity.registry.StellarityEntityDataSerializers;
import dev.coder2195.stellarity.registry.StellarityEntityTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class DragonBreathCauldronIngredientEntity extends Entity {
	private static final EntityDataAccessor<Vec3> ANCHOR = SynchedEntityData.defineId(DragonBreathCauldronIngredientEntity.class, StellarityEntityDataSerializers.VEC3);
	private static final EntityDataAccessor<ItemStack> ITEM_STACK = SynchedEntityData.defineId(DragonBreathCauldronIngredientEntity.class, EntityDataSerializers.ITEM_STACK);
	private static final EntityDataAccessor<Float> SYNCED_ROTATION_OFFSET = SynchedEntityData.defineId(DragonBreathCauldronIngredientEntity.class, EntityDataSerializers.FLOAT);

	private float appearanceRotationOffset = 0;

	private @Nullable DragonBreathCauldronBlockEntity cauldronBlockEntity = null;

	public DragonBreathCauldronIngredientEntity(Level level, Vec3 anchor, ItemStack itemStack) {
		super(StellarityEntityTypes.DRAGON_BREATH_CAULDRON_INGREDIENT, level);
		setAnchor(anchor);
		setItemStack(itemStack);
	}

	public DragonBreathCauldronIngredientEntity(Level level, DragonBreathCauldronBlockEntity entity, Vec3 anchor, ItemStack itemStack) {
		this(level, anchor, itemStack);
		this.cauldronBlockEntity = entity;
	}

	@Override
	protected boolean updateFluidInteraction() {
		return false;
	}

	public void setSyncedRotationOffset(float rotationOffset) {
		this.entityData.set(SYNCED_ROTATION_OFFSET, rotationOffset);
	}

	public void setCauldronBlockEntity(@Nullable DragonBreathCauldronBlockEntity cauldronBlockEntity) {
		this.cauldronBlockEntity = cauldronBlockEntity;
	}

	public @Nullable DragonBreathCauldronBlockEntity getCauldronBlockEntity() {
		return cauldronBlockEntity;
	}

	public Vec3 getAnchor() {
		return entityData.get(ANCHOR);
	}

	public void setAnchor(Vec3 anchor) {
		entityData.set(ANCHOR, anchor);
	}

	public DragonBreathCauldronIngredientEntity(EntityType<DragonBreathCauldronIngredientEntity> entityEntityType, Level level) {
		super(entityEntityType, level);
	}


	@Override
	public boolean shouldBeSaved() {
		return false;
	}

	@Override
	public boolean isPickable() {
		return true;
	}

	public void setItemStack(ItemStack itemStack) {
		entityData.set(ITEM_STACK, itemStack);
	}

	public ItemStack getItemStack() {
		return entityData.get(ITEM_STACK);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder entityData) {
		entityData.define(ITEM_STACK, ItemStack.EMPTY);
		entityData.define(ANCHOR, Vec3.ZERO);
		entityData.define(SYNCED_ROTATION_OFFSET, 0f);
	}

	@Override
	public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
		if (source.is(DamageTypes.PLAYER_ATTACK)) dropItem(level);
		return false;
	}

	public void dropItem(ServerLevel level) {
		var position = position();
		level.addFreshEntity(new ItemEntity(level, position.x, position.y, position.z, getItemStack()));
		if (cauldronBlockEntity != null) cauldronBlockEntity.removeIngredient(this);

		this.discard();
	}

	@Override
	public void tick() {
		super.tick();

		var level = level();
		var syncedRotationOffset = getSyncedRotationOffset();

		appearanceRotationOffset = Mth.lerp(0.1f, appearanceRotationOffset < syncedRotationOffset - Mth.PI ? Mth.TWO_PI : 0 + appearanceRotationOffset, getSyncedRotationOffset());

		setPos(getAnchor().add(new Vec3(2, 0, 0).yRot(appearanceRotationOffset + ((int) (level.getGameTime() % 360)) * Mth.DEG_TO_RAD)));

	}

	public float getSyncedRotationOffset() {
		return entityData.get(SYNCED_ROTATION_OFFSET);
	}

	@Override
	public InteractionResult interact(Player player, InteractionHand hand, Vec3 location) {
		if (level() instanceof ServerLevel level) dropItem(level);
		return InteractionResult.SUCCESS;
	}

	@Override
	public boolean isPushedByFluid() {
		return false;
	}

	@Override
	protected void readAdditionalSaveData(ValueInput input) {
		input.read("item_stack", ItemStack.OPTIONAL_CODEC).ifPresent(this::setItemStack);
		input.read("anchor", Vec3.CODEC).ifPresent(this::setAnchor);
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		output.store("item_stack", ItemStack.OPTIONAL_CODEC, getItemStack());
		output.store("anchor", Vec3.CODEC, getAnchor());
	}
}

