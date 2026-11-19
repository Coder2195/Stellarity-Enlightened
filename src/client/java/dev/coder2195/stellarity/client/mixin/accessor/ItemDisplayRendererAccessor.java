package dev.coder2195.stellarity.client.mixin.accessor;

import net.minecraft.client.renderer.entity.DisplayRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(DisplayRenderer.ItemDisplayRenderer.class)
public interface ItemDisplayRendererAccessor {
	@Invoker("<init>")
	static DisplayRenderer.ItemDisplayRenderer create(final EntityRendererProvider.Context context) {
		throw new AssertionError("Not transformed!");
	}
}
