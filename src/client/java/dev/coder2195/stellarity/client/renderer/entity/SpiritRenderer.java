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
import org.joml.Vector3f;
import org.joml.Vector3fc;

public class SpiritRenderer extends EntityRenderer<Spirit, SpiritRenderer.SpiritRenderState> {
	private static final Identifier TEXTURE = Stellarity.id("textures/entity/spirit/spirit.png");
	private final SpiritModel model;

	public SpiritRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new SpiritModel(context.bakeLayer(StellarityModelLayers.SPIRIT));
	}

	@Override
	public SpiritRenderState createRenderState() {
		return new SpiritRenderState();
	}

	@Override
	public void submit(SpiritRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
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

	@Override
	public void extractRenderState(Spirit entity, SpiritRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);

		state.innerAngularVelocity = entity.getInnerAngularVelocity();
		state.auraAngularVelocity = entity.getAuraAngularVelocity();
	}

	public static class SpiritRenderState extends EntityRenderState {
		public Vector3fc innerAngularVelocity = new Vector3f(0, 0, 0);
		public Vector3fc auraAngularVelocity = new Vector3f(0, 0, 0);
	}
}
