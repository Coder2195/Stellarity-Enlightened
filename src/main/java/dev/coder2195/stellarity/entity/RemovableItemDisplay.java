package dev.coder2195.stellarity.entity;

import com.mojang.serialization.Codec;
import dev.coder2195.stellarity.mixin.accessor.DisplayAccessor;
import dev.coder2195.stellarity.registry.StellarityEntityTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

public class RemovableItemDisplay extends Display.ItemDisplay {
	public RemovableItemDisplay(EntityType<?> type, Level level) {
		super(type, level);
	}

	private short itemAge = 0;

	public RemovableItemDisplay(Level level, double x, double y, double z, ItemStack itemStack, short itemAge) {
		this(StellarityEntityTypes.REMOVABLE_ITEM_DISPLAY, level);
		this.setPos(x, y, z);
		this.setItemStack(itemStack);
		setItemAge(itemAge);

	}

	public short getItemAge() {
		return itemAge;
	}

	public void setItemAge(short itemAge) {
		this.itemAge = itemAge;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder entityData) {
		super.defineSynchedData(entityData);
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
		super.onSyncedDataUpdated(accessor);
		if (accessor == DisplayAccessor.DATA_HEIGHT_ID() || accessor == DisplayAccessor.DATA_WIDTH_ID()) refreshDimensions();
	}

	@Override
	public EntityDimensions getDimensions(Pose pose) {
		var old = super.getDimensions(pose);
		float newWidth = getWidth();
		float newHeight = getHeight();
		if (old.width() == newWidth && old.height() == newHeight) return old;
		return new EntityDimensions(newWidth, newHeight, newHeight / 2, old.attachments(), old.fixed());
	}

	@Override
	public boolean isPickable() {
		return true;
	}

	@Override
	public InteractionResult interact(Player player, InteractionHand hand, Vec3 location) {
		var level = level();

		if (!level.isClientSide()) {
			drop(level);
		}

		return InteractionResult.SUCCESS;
	}

	public void drop(Level level) {
		var position = position();
		var itemEntity = new ItemEntity(level, position.x, position.y, position.z, this.getItemStack());
		itemEntity.stellarity$setAge(itemAge);
		level.addFreshEntity(itemEntity);
		this.discard();
	}

	@Override
	public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
		drop(level);
		return false;
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.store("item_age", Codec.SHORT, itemAge);
	}

	@Override
	protected void readAdditionalSaveData(ValueInput input) {
		super.readAdditionalSaveData(input);
		input.read("item_age", Codec.SHORT).ifPresent(this::setItemAge);
	}
}
