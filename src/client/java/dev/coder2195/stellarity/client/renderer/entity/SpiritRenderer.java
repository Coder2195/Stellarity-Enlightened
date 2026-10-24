package dev.coder2195.stellarity.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.client.model.entity.SpiritModel;
import dev.coder2195.stellarity.client.registry.StellarityModelLayers;
import dev.coder2195.stellarity.entity.Spirit;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;

public class SpiritRenderer extends EntityRenderer<Spirit, EntityRenderState> {
	private static final Identifier TEXTURE = Stellarity.id("textures/entity/spirit/spirit.png");
	private final SpiritModel model;

	public SpiritRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new SpiritModel(context.bakeLayer(StellarityModelLayers.SPIRIT));
	}

	@Override
	public EntityRenderState createRenderState() {
		return new EntityRenderState();
	}

	@Override
	public void submit(EntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
		poseStack.pushPose();
		submitNodeCollector.submitModel(this.model, state, poseStack, model.renderType(TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, 0xffffffff, null, state.outlineColor);
		poseStack.popPose();
		super.submit(state, poseStack, submitNodeCollector, camera);
	}

	@Override
	protected int getSkyLightLevel(Spirit entity, BlockPos blockPos) {
		return 15;
	}

	@Override
	protected int getBlockLightLevel(Spirit entity, BlockPos blockPos) {
		return 15;
	}
}
