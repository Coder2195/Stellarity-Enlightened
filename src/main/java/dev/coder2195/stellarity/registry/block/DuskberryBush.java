package dev.coder2195.stellarity.registry.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;
import dev.coder2195.stellarity.registry.item.Duskberry;
import dev.coder2195.stellarity.tags.StellarityBlockTags;


public class DuskberryBush extends BushBlock implements BonemealableBlock {
	public static final int MAX_AGE = 3;
	public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
	private static final VoxelShape SAPLING_SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 8.0, 13.0);
	private static final VoxelShape MID_GROWTH_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

	public DuskberryBush(Properties properties) {
		super(properties);

		registerDefaultState(defaultBlockState().setValue(AGE, 0));
	}


	public static final MapCodec<BushBlock> CODEC = simpleCodec(DuskberryBush::new);

	@Override
	public @NonNull MapCodec<BushBlock> codec() {
		return CODEC;
	}

	@Override
	protected boolean mayPlaceOn(@NonNull BlockState blockState, @NonNull BlockGetter blockGetter, @NonNull BlockPos blockPos) {
		return super.mayPlaceOn(blockState, blockGetter, blockPos) || blockState.is(StellarityBlockTags.DIRT);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

	public static final Properties PROPERTIES = Properties.of().mapColor(MapColor.PLANT).randomTicks().noCollision().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY);

	@Override
	public boolean isValidBonemealTarget(@NonNull LevelReader levelReader, @NonNull BlockPos blockPos, BlockState blockState) {
		return blockState.getValue(AGE) < MAX_AGE;
	}


	@Override
	public @NonNull VoxelShape getShape(BlockState blockState, @NonNull BlockGetter blockGetter, @NonNull BlockPos blockPos, @NonNull CollisionContext collisionContext) {
		VoxelShape var10000;
		switch (blockState.getValue(AGE)) {
			case 0 -> var10000 = SAPLING_SHAPE;
			case MAX_AGE -> var10000 = Shapes.block();
			default -> var10000 = MID_GROWTH_SHAPE;
		}

		return var10000;
	}


	@Override
	public boolean isBonemealSuccess(@NonNull Level level, @NonNull RandomSource randomSource, @NonNull BlockPos blockPos, @NonNull BlockState blockState) {
		return true;
	}


	@Override
	public void entityInside(@NonNull BlockState blockState, @NonNull Level level, @NonNull BlockPos blockPos, @NonNull Entity entity, @NonNull InsideBlockEffectApplier insideBlockEffectApplier, boolean bl) {
		if (entity instanceof LivingEntity livingEntity) {
			livingEntity.makeStuckInBlock(blockState, new Vec3(0.8F, 0.75F, 0.8F));
			for (var effect : Duskberry.debuffs(blockState.getValue(AGE))) {
				livingEntity.addEffect(effect);
			}
		}
	}

	@Override
	public void performBonemeal(ServerLevel serverLevel, @NonNull RandomSource randomSource, @NonNull BlockPos blockPos, BlockState blockState) {
		int i = Math.min(3, blockState.getValue(AGE) + 1);
		serverLevel.setBlock(blockPos, blockState.setValue(AGE, i), 2);
	}

	@Override
	public void randomTick(BlockState blockState, @NonNull ServerLevel serverLevel, @NonNull BlockPos blockPos, @NonNull RandomSource randomSource) {
		int i = blockState.getValue(AGE);
		if (i < 3 && randomSource.nextInt(8) == 0) {
			BlockState growState = blockState.setValue(AGE, i + 1);
			serverLevel.setBlock(blockPos, growState, 2);
			serverLevel.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(growState));
		}
	}


}
