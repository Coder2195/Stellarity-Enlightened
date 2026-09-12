package dev.coder2195.stellarity.mixin.crafting;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.coder2195.stellarity.block.AltarOfTheAccursed;
import dev.coder2195.stellarity.entity.SatchelSigil;
import dev.coder2195.stellarity.interface_injection.ExtItemEntity;
import dev.coder2195.stellarity.recipe.ConsecrationRecipe;
import dev.coder2195.stellarity.registry.StellarityBlocks;
import dev.coder2195.stellarity.registry.StellarityRecipeTypes;
import dev.coder2195.stellarity.tags.StellarityBiomeTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity implements ExtItemEntity {
	@Shadow
	public abstract ItemStack getItem();

	@Shadow
	public abstract void setItem(ItemStack itemStack);

	public ItemEntityMixin(EntityType<?> type, Level level) {
		super(type, level);
	}

	@Unique
	private int lastInWaterCounter = 3;

	@Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;tick()V", shift = At.Shift.AFTER))
	private void movedOffRecipeBlock(CallbackInfo ci) {
		if (!stellarity$getItemMode().equals(ExtItemEntity.ItemMode.ALTAR_CRAFTING)) return;
		if (!(level() instanceof ServerLevel level)) return;
		var position = this.position();
		for (var corner : List.of(
			position.add(0, -0.75, 0),
			position.add(0.5, -0.75, 0.5),
			position.add(0.5, -0.75, -0.5),
			position.add(-0.5, -0.75, -0.5),
			position.add(-0.5, -0.75, 0.5)

		)) {
			var blockstate = level.getBlockState(BlockPos.containing(corner));

			if (blockstate.is(StellarityBlocks.ALTAR_OF_THE_ACCURSED) && !blockstate.getValue(AltarOfTheAccursed.LOCKED))
				return;
		}

		if (level.getEntitiesOfClass(SatchelSigil.class, this.getBoundingBox()).stream().noneMatch(SatchelSigil::isActive))
			stellarity$setItemMode(ExtItemEntity.ItemMode.DEFAULT);
	}

	@Override
	public void stellarity$consecrationCraft(boolean inWater) {
		var itemMode = stellarity$getItemMode();
		var consecrating = itemMode == ItemMode.CONSECRATING;
		var consecrationData = stellarity$getConsecrationData();

		var level = level();
		if (!(level instanceof ServerLevel serverLevel)) {
			if (consecrationData == null) return;

			long tickStage = (ConsecrationRecipe.ConsecrationData.CONSECRATION_DURATION - (consecrationData.consecratesAt() - level().getGameTime()));
			long particles = 3 + (tickStage * 8 / ConsecrationRecipe.ConsecrationData.CONSECRATION_DURATION);
			for (int i=0; i<particles; i++) level.addAlwaysVisibleParticle(new ItemParticleOption(ParticleTypes.ITEM, consecrationData.itemStack().getItem()),
				getX(), getY() + 0.2, getZ(), random.nextGaussian() * 0.2, 0.2 + random.nextDouble() * 0.2, random.nextGaussian() * 0.2);

			return;
		}

		if (!(consecrating || itemMode == ItemMode.DEFAULT)) return;
		var correctBiome = serverLevel.getBiome(blockPosition()).is(StellarityBiomeTags.ALLOWS_CONSECRATION);

		long gameTime = serverLevel.getGameTime();
		if (inWater) lastInWaterCounter = 4;

		if (consecrating) {
			if (!inWater && --lastInWaterCounter < 0 || !correctBiome) {
				stellarity$setItemMode(ItemMode.DEFAULT);
				stellarity$removeConsecrationData();
				return;
			}

			if (consecrationData == null || gameTime < consecrationData.consecratesAt()) return;
			setItem(consecrationData.itemStack());
			stellarity$removeConsecrationData();
			stellarity$setItemMode(ItemMode.RESULT, -1);

			return;
		}

		if (!(inWater && correctBiome)) return;

		var input = new ConsecrationRecipe.Input(getItem());
		var recipe = serverLevel.recipeAccess().getRecipeFor(StellarityRecipeTypes.CONSECRATION, input, serverLevel);
		if (recipe.isEmpty()) return;

		recipe.get().value().apply((ItemEntity) (Object) this);
	}

	@Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;tick()V", shift = At.Shift.AFTER))
	public void consecrationTick(CallbackInfo ci) {
		stellarity$consecrationCraft(isInWater());
	}

	@WrapMethod(method = "isMergable")
	private boolean dontMergeCrafting(Operation<Boolean> original) {
		return original.call() && !stellarity$getItemMode().isCrafting();
	}

	@WrapMethod(method = "tryToMerge")
	private void refinedMergeRules(ItemEntity other, Operation<Void> original) {
		if (other.stellarity$getItemMode() == stellarity$getItemMode()) original.call(other);
	}

}
