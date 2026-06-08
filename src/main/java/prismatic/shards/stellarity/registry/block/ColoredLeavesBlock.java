package prismatic.shards.stellarity.registry.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import prismatic.shards.stellarity.registry.block_entity.ColoredBlockEntity;

public class ColoredLeavesBlock extends TintedParticleLeavesBlock implements EntityBlock {
	public static final int DEFAULT_COLOR = 0x00ff00;
	public static final MapCodec<ColoredLeavesBlock> CODEC = simpleCodec(ColoredLeavesBlock::new);

	public ColoredLeavesBlock(Properties properties) {
		super(1f, properties);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.@NonNull Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
	}

	@Override
	public @NonNull MapCodec<? extends TintedParticleLeavesBlock> codec() {
		return CODEC;
	}

	protected boolean triggerEvent(final @NonNull BlockState state, final @NonNull Level level, final @NonNull BlockPos pos, final int b0, final int b1) {
		super.triggerEvent(state, level, pos, b0, b1);
		BlockEntity blockEntity = level.getBlockEntity(pos);
		return blockEntity != null && blockEntity.triggerEvent(b0, b1);
	}

	@Override
	public @Nullable BlockEntity newBlockEntity(@NonNull BlockPos worldPosition, @NonNull BlockState blockState) {
		return new ColoredBlockEntity(worldPosition, blockState, DEFAULT_COLOR);
	}
}
