package dev.coder2195.stellarity.client.registry.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.coder2195.stellarity.registry.entity.ObstructSpellBlock;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import org.jspecify.annotations.NonNull;

public class ObstructSpellBlockRenderer extends EntityRenderer<ObstructSpellBlock, ObstructSpellBlockRenderer.ObstructSpellBlockState> {
	public final BlockModelResolver blockModelResolver;
	public static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();

	public ObstructSpellBlockRenderer(EntityRendererProvider.Context context) {
		super(context);
		blockModelResolver = context.getBlockModelResolver();
	}

	@Override
	public void submit(@NonNull ObstructSpellBlockState state, @NonNull PoseStack poseStack, @NonNull SubmitNodeCollector submitNodeCollector, @NonNull CameraRenderState camera) {
		super.submit(state, poseStack, submitNodeCollector, camera);

		poseStack.translate(-0.5, 0, -0.5);

		state.blockModel.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor);
	}

	@Override
	public void extractRenderState(@NonNull ObstructSpellBlock entity, @NonNull ObstructSpellBlockState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);

		blockModelResolver.update(state.blockModel, entity.getBlockState(), BLOCK_DISPLAY_CONTEXT);
	}

	@Override
	public @NonNull ObstructSpellBlockState createRenderState() {
		return new ObstructSpellBlockState();
	}

	public static class ObstructSpellBlockState extends EntityRenderState {
		public BlockModelRenderState blockModel = new BlockModelRenderState();
	}
}
