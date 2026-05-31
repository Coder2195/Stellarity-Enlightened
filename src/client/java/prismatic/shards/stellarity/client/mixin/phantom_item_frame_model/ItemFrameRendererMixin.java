
package prismatic.shards.stellarity.client.mixin.phantom_item_frame_model;


import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.ItemFrameRenderer;
import net.minecraft.world.entity.decoration.ItemFrame;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import prismatic.shards.stellarity.registry.StellarityEntityTypes;

import static prismatic.shards.stellarity.client.registry.renderer.entity.PhantomItemFrameRenderer.FAKE_STATE_DEFINITION;

@MixinEnvironment("client")
@Mixin(ItemFrameRenderer.class)
public class ItemFrameRendererMixin {
	@Shadow
	@Final
	public static BlockDisplayContext BLOCK_DISPLAY_CONTEXT;

	@WrapOperation(method = "extractRenderState(Lnet/minecraft/world/entity/decoration/ItemFrame;Lnet/minecraft/client/renderer/entity/state/ItemFrameRenderState;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/block/BlockModelResolver;updateForItemFrame(Lnet/minecraft/client/renderer/block/BlockModelRenderState;ZZ)V"))
	private void phantomItemFrameState(BlockModelResolver instance, BlockModelRenderState renderState, boolean isGlowing, boolean map, Operation<Void> original, @Local(argsOnly = true, name = "entity") ItemFrame entity) {
		if (entity.is(StellarityEntityTypes.PHANTOM_ITEM_FRAME)) {
			instance.update(renderState, FAKE_STATE_DEFINITION.any(), BLOCK_DISPLAY_CONTEXT);

			return;
		}

		original.call(instance, renderState, isGlowing, map);
	}
}
