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

public class DragonBreathCauldronItemEntity extends Entity {
	public final @Nullable DragonBreathCauldronBlockEntity parent;
	private static final EntityDataAccessor<Vec3> ANCHOR = SynchedEntityData.defineId(DragonBreathCauldronItemEntity.class, StellarityEntityDataSerializers.VEC3);
	private static final EntityDataAccessor<ItemStack> ITEM_STACK = SynchedEntityData.defineId(DragonBreathCauldronItemEntity.class, EntityDataSerializers.ITEM_STACK);

	public DragonBreathCauldronItemEntity(DragonBreathCauldronBlockEntity parent, Level level, ItemStack itemStack) {
		super(StellarityEntityTypes.DRAGON_BREATH_CAULDRON_ITEM, level);
		this.parent = parent;
		setAnchor(Vec3.atCenterOf(parent.getBlockPos()));
		setPos(getAnchor().add(new Vec3(0, 2, 0)));
		setItemStack(itemStack);
	}

	public Vec3 getAnchor() {
		return entityData.get(ANCHOR);
	}

	public void setAnchor(Vec3 anchor) {
		entityData.set(ANCHOR, anchor);
	}

	public DragonBreathCauldronItemEntity(EntityType<DragonBreathCauldronItemEntity> entityEntityType, Level level) {
		super(entityEntityType, level);
		this.parent = null;
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
	}

	@Override
	public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
		if (source.is(DamageTypes.PLAYER_ATTACK)) {
			dropItem();
		}
		return false;
	}

	public void dropItem() {
		this.discard();
		var level = level();
		var position = position();
		level.addFreshEntity(new ItemEntity(level, position.x, position.y, position.z, getItemStack()));
	}

	@Override
	public void tick() {
		super.tick();

		var level = level();

		setPos(getAnchor().add(new Vec3(2, 0, 0).yRot(((int) (level.getGameTime() % 360)) * Mth.DEG_TO_RAD)));
	}

	@Override
	public InteractionResult interact(Player player, InteractionHand hand, Vec3 location) {
		if (!level().isClientSide()) dropItem();
		return InteractionResult.SUCCESS;
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

