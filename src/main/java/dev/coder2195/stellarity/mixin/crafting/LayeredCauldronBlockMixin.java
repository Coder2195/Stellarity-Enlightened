package dev.coder2195.stellarity.mixin.crafting;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LayeredCauldronBlock.class)
public class LayeredCauldronBlockMixin {
	@Inject(method = "entityInside", at= @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;immutable()Lnet/minecraft/core/BlockPos;"))
	private void handleItemCraft(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean isPrecise, CallbackInfo ci) {
		if (entity instanceof ItemEntity itemEntity) {
			itemEntity.stellarity$consecrationCraft(true);
		}
	}
}
