package dev.coder2195.stellarity.block;


import dev.coder2195.stellarity.block_entity.AltarOfTheAccursedBlockEntity;
import dev.coder2195.stellarity.registry.StellarityBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class AltarOfTheAccursed extends Block implements EntityBlock {
	public enum PlaceType implements StringRepresentable {
		NORMAL,
		CREATIVE,
		SATCHEL;

		@Override

		public String getSerializedName() {
			return switch (this) {
				case NORMAL -> "normal";
				case CREATIVE -> "creative";
				case SATCHEL -> "satchel";
			};
		}

		public boolean bypassesDragon() {
			return this != NORMAL;
		}
	}

	public static final BooleanProperty LOCKED = BooleanProperty.create("locked");
	public static final EnumProperty<PlaceType> PLACE_TYPE = EnumProperty.create("place_type", PlaceType.class);


	public AltarOfTheAccursed(Properties properties) {
		super(properties);

		registerDefaultState(defaultBlockState().setValue(LOCKED, false).setValue(PLACE_TYPE, PlaceType.CREATIVE));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(LOCKED).add(PLACE_TYPE);
	}


	public static final VoxelShape SHAPE = Block.box(0.0F, 0.0F, 0.0F, 16.0F, 13.0F, 16.0F);

	public static final Properties PROPERTIES = Properties.of()
		.mapColor(MapColor.COLOR_GREEN)
		.instrument(NoteBlockInstrument.BASEDRUM)
		.sound(SoundType.GLASS)
		.lightLevel((_) -> 7)
		.strength(-1.0F, 6700000.0F)
		.requiresCorrectToolForDrops();

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
		return SHAPE;
	}

	@Override
	public RenderShape getRenderShape(BlockState blockState) {
		return RenderShape.MODEL;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new AltarOfTheAccursedBlockEntity(blockPos, blockState);
	}

	@Override
	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
		return AltarOfTheAccursedBlockEntity::tick;
	}
}
