package dev.coder2195.stellarity.block_entity;

import com.mojang.serialization.Codec;
import dev.coder2195.stellarity.entity.DragonBreathCauldronIngredientEntity;
import dev.coder2195.stellarity.registry.StellarityBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.PowerParticleOption;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class DragonBreathCauldronBlockEntity extends BlockEntity {
	private final List<DragonBreathCauldronIngredientEntity> ingredientEntities = new ArrayList<>();
	private List<ItemStack> ingredients = new ArrayList<>();
	private int maxIngredients = 10;
	private int tickCounter = 0;
	private int remainingUses = -1;


	public DragonBreathCauldronBlockEntity(BlockPos worldPosition, BlockState blockState) {
		super(StellarityBlockEntityTypes.DRAGON_BREATH_CAULDRON, worldPosition, blockState);
	}

	public static void tick(Level level, BlockPos blockPos, BlockState blockState, BlockEntity blockEntity) {
		if (blockEntity instanceof DragonBreathCauldronBlockEntity entity) entity.tick(level, blockPos, blockState);
	}

	public void tick(Level level, BlockPos blockPos, BlockState blockState) {
		var random = level.getRandom();

		var centerPos = Vec3.atCenterOf(blockPos);
		if (level.isClientSide()) {
			double x = centerPos.x + random.nextDouble() * 0.8 - 0.4;
			double z = centerPos.z + random.nextDouble() * 0.8 - 0.4;

			level.addAlwaysVisibleParticle(PowerParticleOption.create(ParticleTypes.DRAGON_BREATH, 1), true, x, centerPos.y, z, 0, 0.03, 0);

			return;
		}

		if (++tickCounter > 100) {
			tickCounter = 0;
			syncIngredientEntities(level, blockPos);
		}

		var ingredientsSize = ingredientEntities.size();
		if (ingredientsSize == 0) return;
	}

	@Override
	public void setLevel(Level level) {
		super.setLevel(level);
		syncIngredientEntities(level, worldPosition);
	}

	public void syncIngredientEntities(Level level, BlockPos blockPos) {
		int ingredientsSize = ingredients.size();
		int entitiesSize = ingredientEntities.size();
		int commonSize = Math.min(ingredientsSize, entitiesSize);
		var anchor = Vec3.atCenterOf(blockPos);

		for (int i=0; i<commonSize; i++) {
			var entity = ingredientEntities.get(i);
			entity.setItemStack(ingredients.get(i));
			entity.setCauldronBlockEntity(this);
			entity.setAnchor(anchor);
		}

		if (ingredientsSize < entitiesSize) {
			for (int i=entitiesSize - 1; i>=commonSize; i--) {
				ingredientEntities.remove(i).discard();
			}
			return;
		}
		for (int i=commonSize; i<ingredientsSize; i++) {
			var newEntity = new DragonBreathCauldronIngredientEntity(level, this, anchor, ingredients.get(i));
			ingredientEntities.add(newEntity);
			level.addFreshEntity(newEntity);
		}

		syncRotationOffsets();
	}

	public void syncRotationOffsets() {
		var entitiesSize = ingredientEntities.size();

		for (int i=0; i<entitiesSize; i++) {
			ingredientEntities.get(i).setSyncedRotationOffset(Mth.TWO_PI * i / entitiesSize);
		}
	}

	public void setIngredients(List<ItemStack> unparsed) {
		this.ingredients = new ArrayList<>();
		for (var rawIngredient : unparsed) {
			int count = rawIngredient.getCount();
			for (int i = 0; i < count; i++) {
				this.ingredients.add(rawIngredient.copyWithCount(1));
			}
		}
	}

	@Override
	public void preRemoveSideEffects(BlockPos pos, BlockState state) {
		if (level instanceof ServerLevel serverLevel) while(!ingredientEntities.isEmpty()) ingredientEntities.getLast().dropItem(serverLevel);
	}

	public boolean isFull() {
		return ingredients.size() >= maxIngredients;
	}

	public ItemStack addIngredient(ItemStack ingredient) {
		boolean willOverflow = ingredients.size() + ingredient.count() > maxIngredients;
		int ingredientCount = ingredient.count();
		int count = willOverflow ? maxIngredients - ingredients.size() : ingredientCount;
		for (int i=0; i<count; i++) {
			ingredients.add(ingredient.copyWithCount(1));
		}
		if (!willOverflow) return ItemStack.EMPTY;
		ingredient.setCount(ingredientCount - count);
		syncRotationOffsets();
		return ingredient;
	}

	public void setMaxIngredients(int maxIngredients) {
		this.maxIngredients = maxIngredients;
	}

	public int getMaxIngredients() {
		return maxIngredients;
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		input.read("ingredients", ItemStack.CODEC.listOf()).ifPresent(this::setIngredients);
		input.read("max_ingredients", Codec.INT).ifPresent(this::setMaxIngredients);

		if (level != null) syncIngredientEntities(level, worldPosition);
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);

		if (!ingredients.isEmpty()) output.store("ingredients", ItemStack.CODEC.listOf(), ingredients);
		output.store("max_ingredients", Codec.INT, maxIngredients);
	}

	public void removeIngredient(DragonBreathCauldronIngredientEntity ingredientEntity) {
		ingredientEntities.remove(ingredientEntity);
		ingredients.remove(ingredientEntity.getItemStack());
		syncRotationOffsets();
	}
}
