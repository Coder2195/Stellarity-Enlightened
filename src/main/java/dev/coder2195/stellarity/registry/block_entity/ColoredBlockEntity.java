package dev.coder2195.stellarity.registry.block_entity;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import dev.coder2195.stellarity.registry.StellarityBlockEntityTypes;
import dev.coder2195.stellarity.registry.StellarityDataComponents;
import dev.coder2195.stellarity.registry.data_component.Color;

public class ColoredBlockEntity extends BlockEntity {
	private @Nullable Integer color = null;
	private int defaultColor;

	public ColoredBlockEntity(BlockPos worldPosition, BlockState blockState) {
		super(StellarityBlockEntityTypes.COLORED_BLOCK, worldPosition, blockState);
	}

	public ColoredBlockEntity(BlockPos worldPosition, BlockState blockState, int defaultColor) {
		super(StellarityBlockEntityTypes.COLORED_BLOCK, worldPosition, blockState);
		this.defaultColor = defaultColor;
	}

	public ColoredBlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {
		super(type, worldPosition, blockState);
	}


	public int getColor() {
		return color == null ? defaultColor : color;
	}

	public void setColor(int color) {
		this.color = color;
	}


	@Override
	protected void loadAdditional(@NonNull ValueInput input) {
		super.loadAdditional(input);
		input.read("color", Codec.INT).ifPresent(c -> this.color = c);
		setChanged();
	}

	@Override
	protected void saveAdditional(@NonNull ValueOutput output) {
		super.saveAdditional(output);
		output.store("color", Codec.INT, getColor());
	}

	@Override
	public void setChanged() {
		super.setChanged();

		if (level == null) return;

		BlockState state = getBlockState();
		level.sendBlockUpdated(worldPosition, state, state, Block.UPDATE_ALL);
	}

	@Override
	protected void collectImplicitComponents(DataComponentMap.@NonNull Builder components) {
		super.collectImplicitComponents(components);
		components.set(StellarityDataComponents.COLOR, new Color(getColor()));
	}

	@Override
	public @NonNull CompoundTag getUpdateTag(HolderLookup.@NonNull Provider registryLookup) {
		return saveWithoutMetadata(registryLookup);
	}

	@Override
	protected void applyImplicitComponents(@NonNull DataComponentGetter components) {
		super.applyImplicitComponents(components);
		this.color = components.getOrDefault(StellarityDataComponents.COLOR, new Color(defaultColor)).rgb();
	}

	@Override
	public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}
}
