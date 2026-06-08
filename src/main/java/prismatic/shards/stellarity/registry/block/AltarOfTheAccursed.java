package prismatic.shards.stellarity.registry.block;


import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
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
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.registry.StellarityBlockEntityTypes;
import prismatic.shards.stellarity.registry.block_entity.AltarOfTheAccursedBlockEntity;

public class AltarOfTheAccursed extends Block implements EntityBlock {
	public enum PlaceType implements StringRepresentable {
		NORMAL,
		CREATIVE,
		SATCHEL;

		@Override

		public @NonNull String getSerializedName() {
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
	public @NonNull VoxelShape getShape(@NonNull BlockState blockState, @NonNull BlockGetter blockGetter, @NonNull BlockPos blockPos, @NonNull CollisionContext collisionContext) {
		return SHAPE;
	}

	public static final MapCodec<AltarOfTheAccursed> CODEC = BaseEntityBlock.simpleCodec(AltarOfTheAccursed::new);

	@Override
	public @NonNull MapCodec<AltarOfTheAccursed> codec() {
		return CODEC;
	}

	@Override
	public @NonNull RenderShape getRenderShape(@NonNull BlockState blockState) {
		return RenderShape.MODEL;
	}

	@Override
	public @Nullable BlockEntity newBlockEntity(@NonNull BlockPos blockPos, @NonNull BlockState blockState) {
		return new AltarOfTheAccursedBlockEntity(blockPos, blockState);
	}

	@Override
	public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NonNull Level level, @NonNull BlockState blockState, @NonNull BlockEntityType<T> type) {
		if (type == StellarityBlockEntityTypes.ALTAR_OF_THE_ACCURSED) return AltarOfTheAccursedBlockEntity::tick;
		return null;
	}
}
