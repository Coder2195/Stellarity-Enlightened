package dev.coder2195.stellarity.mixin.interface_injections;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import dev.coder2195.stellarity.interface_injection.ExtItemEntity;
import dev.coder2195.stellarity.registry.StellarityBlocks;
import dev.coder2195.stellarity.registry.block.AltarOfTheAccursed;
import dev.coder2195.stellarity.registry.entity.SatchelSigil;

import java.util.HashMap;
import java.util.List;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity implements ExtItemEntity {
	@Shadow
	public abstract ItemStack getItem();

	@Shadow
	public abstract void setItem(ItemStack itemStack);

	@Shadow
	public abstract void setPickUpDelay(int i);

	public ItemEntityMixin(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	public void stellarity$setItemMode(ItemMode mode) {
		ExtItemEntity.super.stellarity$setItemMode(mode);

		boolean crafting = mode.equals(ItemMode.CRAFTING);
		setGlowingTag(crafting);
		this.stellarity$setGlowColor(crafting ? 11141290 : -1);
		setPickUpDelay(crafting ? Short.MAX_VALUE : 0);
	}

	@Override
	public void stellarity$updateResults(HashMap<ItemStack, Integer> results) {
		if (stellarity$getItemMode() != ItemMode.CRAFTING) return;
		ItemStack stack = this.getItem();
		Integer count = results.get(stack);

		if (count == null || count == 0) {
			this.discard();
			return;
		}

		setItem(stack.copyWithCount(count));
	}

	@Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;tick()V", shift = At.Shift.AFTER))
	public void movedOffRecipeBlock(CallbackInfo ci) {
		if (!stellarity$getItemMode().equals(ItemMode.CRAFTING)) return;
		if (level() instanceof ServerLevel level) {
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
				stellarity$setItemMode(ItemMode.DEFAULT);
		}
	}
}
