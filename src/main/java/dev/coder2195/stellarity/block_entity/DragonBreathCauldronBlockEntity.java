package dev.coder2195.stellarity.block_entity;

import com.mojang.serialization.Codec;
import dev.coder2195.stellarity.entity.DragonBreathCauldronIngredient;
import dev.coder2195.stellarity.interface_injection.ExtItemEntity;
import dev.coder2195.stellarity.networking.ClientboundCauldronCraftPayload;
import dev.coder2195.stellarity.recipe.ItemListInput;
import dev.coder2195.stellarity.registry.StellarityBlockEntityTypes;
import dev.coder2195.stellarity.registry.StellarityRecipeTypes;
import dev.coder2195.stellarity.util.NetworkingUtil;
import dev.coder2195.stellarity.util.tuple.Tuple2;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.PowerParticleOption;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class DragonBreathCauldronBlockEntity extends BlockEntity {
	private final List<DragonBreathCauldronIngredient> ingredientEntities = new ArrayList<>();
	private List<ItemStack> ingredients = new ArrayList<>();
	private int maxIngredients = 10;
	private int tickCounter = Integer.MIN_VALUE;
	private int remainingUses = Integer.MIN_VALUE;
	private int disabledTime = 0;

	public static final int[] CAULDRON_THRESHOLDS = {-1, 2, 4, 6};
	public static final int[] UNKNOWN_USES_FALLBACK = {0, 2, 4, 7};

	public DragonBreathCauldronBlockEntity(BlockPos worldPosition, BlockState blockState) {
		super(StellarityBlockEntityTypes.DRAGON_BREATH_CAULDRON, worldPosition, blockState);
	}

	public static void tick(Level level, BlockPos blockPos, BlockState blockState, BlockEntity blockEntity) {
		if (blockEntity instanceof DragonBreathCauldronBlockEntity entity) entity.tick(level, blockPos, blockState);
	}

	public void tick(Level level, BlockPos blockPos, BlockState blockState) {
		var random = level.getRandom();

		var centerPos = Vec3.atCenterOf(blockPos);
		if (!(level instanceof ServerLevel serverLevel)) {
			double x = centerPos.x + random.nextDouble() * 0.8 - 0.4;
			double z = centerPos.z + random.nextDouble() * 0.8 - 0.4;

			level.addAlwaysVisibleParticle(PowerParticleOption.create(ParticleTypes.DRAGON_BREATH, 1), true, x, centerPos.y, z, 0, 0.03, 0);

			return;
		}

		if (tickCounter == Integer.MIN_VALUE || ++tickCounter > 100) {
			tickCounter = 0;
			syncIngredientEntities(level, blockPos);
		}

		var ingredientsSize = ingredientEntities.size();
		if (disabledTime > 0) {
			disabledTime--;
			return;
		}
		if (ingredientsSize == 0 || tickCounter % 10 != 0) return;
		var input = new ItemListInput(ingredients);
		var recipes = serverLevel.recipeAccess().getRecipeFor(StellarityRecipeTypes.CAULDRON_CRAFTING, input, serverLevel);
		if (recipes.isEmpty()) return;
		// offset for 0 index
		int cauldronLevel = blockState.getValueOrElse(LayeredCauldronBlock.LEVEL, 3);
		if (remainingUses == Integer.MIN_VALUE) remainingUses = UNKNOWN_USES_FALLBACK[cauldronLevel];

		var itemStack = recipes.get().value().assemble(input);

		var resultEntity = new ItemEntity(serverLevel, centerPos.x, centerPos.y, centerPos.z, itemStack);
		resultEntity.stellarity$setItemMode(ExtItemEntity.ItemMode.RESULT, -1);
		serverLevel.addFreshEntity(resultEntity);
		remainingUses--;
		NetworkingUtil.sendTrackingPlayers(serverLevel, blockPos, new ClientboundCauldronCraftPayload(
			centerPos, ingredientEntities.stream().map(entity -> new Tuple2<>(
				ItemStackTemplate.fromNonEmptyStack(entity.getItemStack()), entity.position()
		)).toList()));
		setIngredients(List.of());
		syncIngredientEntities(level, blockPos);

		if (remainingUses == 0) {
			level.setBlock(blockPos, Blocks.CAULDRON.defaultBlockState(), Block.UPDATE_ALL);
			return;
		}

		for (int i = 0; i < CAULDRON_THRESHOLDS.length; i++) {
			if (CAULDRON_THRESHOLDS[i] < remainingUses) continue;
			if (i != cauldronLevel) level.setBlock(blockPos, blockState.setValue(LayeredCauldronBlock.LEVEL, i), Block.UPDATE_ALL);
			return;
		}

	}

	public void syncIngredientEntities(Level level, BlockPos blockPos) {
		int ingredientsSize = ingredients.size();
		int entitiesSize = ingredientEntities.size();
		int commonSize = Math.min(ingredientsSize, entitiesSize);
		var anchor = Vec3.atCenterOf(blockPos);

		for (int i = 0; i < commonSize; i++) {
			var entity = ingredientEntities.get(i);
			entity.setItemStack(ingredients.get(i));
			entity.setCauldronBlockEntity(this);
			entity.setAnchor(anchor);
		}

		if (ingredientsSize < entitiesSize) {
			for (int i = entitiesSize - 1; i >= commonSize; i--) {
				ingredientEntities.remove(i).discard();
			}
			return;
		}
		for (int i = commonSize; i < ingredientsSize; i++) {
			var newEntity = new DragonBreathCauldronIngredient(level, this, anchor, ingredients.get(i));
			ingredientEntities.add(newEntity);
			level.addFreshEntity(newEntity);
		}

		syncRotationOffsets();
	}

	public void syncRotationOffsets() {
		var entitiesSize = ingredientEntities.size();

		for (int i = 0; i < entitiesSize; i++) {
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

		setChanged();
	}

	@Override
	public void preRemoveSideEffects(BlockPos pos, BlockState state) {
		if (level instanceof ServerLevel serverLevel) while (!ingredientEntities.isEmpty()) ingredientEntities.getLast().dropItem(serverLevel);
	}

	public boolean isFull() {
		return ingredients.size() >= maxIngredients;
	}

	public ItemStack addIngredient(ItemStack ingredient) {
		boolean willOverflow = ingredients.size() + ingredient.count() > maxIngredients;
		int ingredientCount = ingredient.count();
		int count = willOverflow ? maxIngredients - ingredients.size() : ingredientCount;
		for (int i = 0; i < count; i++) {
			ingredients.add(ingredient.copyWithCount(1));
		}

		disabledTime = 20;

		setChanged();

		if (!willOverflow) return ItemStack.EMPTY;
		ingredient.setCount(ingredientCount - count);
		syncRotationOffsets();
		return ingredient;
	}

	public void setMaxIngredients(int maxIngredients) {
		this.maxIngredients = maxIngredients;

		setChanged();
	}

	public void setRemainingUses(int remainingUses) {
		this.remainingUses = remainingUses;

		setChanged();
	}

	public int getRemainingUses(BlockState blockState) {
		return remainingUses;
	}

	public int getMaxIngredients() {
		return maxIngredients;
	}

	public void setDisabledTime(int disabledTime) {
		this.disabledTime = disabledTime;
	}

	public int getDisabledTime() {
		return disabledTime;
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);

		input.read("max_ingredients", Codec.INT).ifPresent(this::setMaxIngredients);
		input.read("ingredients", ItemStack.CODEC.listOf()).ifPresent(this::setIngredients);
		input.read("remaining_uses", Codec.INT).ifPresent(this::setRemainingUses);
		input.read("disabled_time", Codec.INT).ifPresent(this::setDisabledTime);

		if (level != null) syncIngredientEntities(level, worldPosition);
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);

		output.store("max_ingredients", Codec.INT, maxIngredients);
		output.store("ingredients", ItemStack.CODEC.listOf(), ingredients);
		output.store("remaining_uses", Codec.INT, remainingUses);
		output.store("disabled_time", Codec.INT, disabledTime);
	}

	public void removeIngredient(DragonBreathCauldronIngredient ingredientEntity) {
		ingredientEntities.remove(ingredientEntity);
		ingredients.remove(ingredientEntity.getItemStack());
		disabledTime = 30;
		syncRotationOffsets();
		setChanged();
	}
}
