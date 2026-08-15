package dev.coder2195.stellarity.client.mixin.accessor;

import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.entity.ItemFrameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemFrameRenderer.class)
public interface ItemFrameRendererAccessor {
	@Mutable
	@Accessor("blockModelResolver")
	BlockModelResolver stellarity$getBlockModelResolver();
}
