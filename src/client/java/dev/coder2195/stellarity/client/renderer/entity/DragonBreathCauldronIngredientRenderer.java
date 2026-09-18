package dev.coder2195.stellarity.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.coder2195.stellarity.entity.DragonBreathCauldronIngredientEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ItemClusterRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Brightness;

public class DragonBreathCauldronIngredientRenderer extends EntityRenderer<DragonBreathCauldronIngredientEntity, DragonBreathCauldronIngredientRenderer.DragonBreathCauldronItemEntityRenderState> {
	private final ItemModelResolver itemModelResolver;
	private static final int BRIGHTNESS = new Brightness(15, 15).pack();

	public DragonBreathCauldronIngredientRenderer(final EntityRendererProvider.Context context) {
		super(context);
		itemModelResolver = context.getItemModelResolver();
	}

	@Override
	public void submit(DragonBreathCauldronItemEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
		if (state.item.isEmpty()) return;
		poseStack.pushPose();
		poseStack.translate(0, -0.125f, 0);
		poseStack.scale(2f, 2f, 2f);
		state.item.submit(poseStack, submitNodeCollector, BRIGHTNESS, OverlayTexture.NO_OVERLAY, state.outlineColor);

		poseStack.popPose();
		super.submit(state, poseStack, submitNodeCollector, camera);
	}

	@Override
	public DragonBreathCauldronItemEntityRenderState createRenderState() {
		return new DragonBreathCauldronItemEntityRenderState();
	}

	@Override
	public void extractRenderState(DragonBreathCauldronIngredientEntity entity, DragonBreathCauldronItemEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.extractItemGroupRenderState(entity, entity.getItemStack(), itemModelResolver);
	}

	public static class DragonBreathCauldronItemEntityRenderState extends ItemClusterRenderState {

	}
}
