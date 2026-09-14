package dev.coder2195.stellarity.block;

import dev.coder2195.stellarity.block_entity.DragonsBreathCauldronBlockEntity;
import dev.coder2195.stellarity.registry.StellarityCauldronInteractions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

public class DragonsBreathCauldron extends LayeredCauldronBlock implements EntityBlock {

	public static final Properties PROPERTIES = Properties.ofFullCopy(Blocks.LAVA_CAULDRON);

	public DragonsBreathCauldron(Properties properties) {
		super(Biome.Precipitation.NONE, StellarityCauldronInteractions.DRAGONS_BREATH, properties);
	}


	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
		return new DragonsBreathCauldronBlockEntity(worldPosition, blockState);
	}

	@Override
	public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
		return DragonsBreathCauldronBlockEntity::tick;
	}
}
