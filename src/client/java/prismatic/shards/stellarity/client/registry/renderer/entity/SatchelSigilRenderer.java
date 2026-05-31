package prismatic.shards.stellarity.client.registry.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.client.registry.StellarityEntityModelLayers;
import prismatic.shards.stellarity.client.registry.model.entity.SatchelSigilModel;
import prismatic.shards.stellarity.registry.entity.SatchelSigil;

public class SatchelSigilRenderer extends EntityRenderer<SatchelSigil, SatchelSigilRenderer.SatchelSigilRenderState> {
	public static final Identifier TEXTURE = Stellarity.id("textures/entity/satchel_sigil/satchel_sigil.png");
	private final EntityModel<SatchelSigilRenderState> model;

	public SatchelSigilRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new SatchelSigilModel(context.bakeLayer(StellarityEntityModelLayers.SATCHEL_SIGIL));
	}

	@Override
	public void submit(@NonNull SatchelSigilRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, @NonNull CameraRenderState camera) {
		poseStack.pushPose();
		submitNodeCollector.submitModel(this.model, state, poseStack, model.renderType(TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, 0xffbb00ff, null, state.outlineColor, null);
		poseStack.popPose();
		super.submit(state, poseStack, submitNodeCollector, camera);
	}

	@Override
	protected int getSkyLightLevel(@NonNull SatchelSigil unused, @NonNull BlockPos unused2) {
		return 15;
	}

	@Override
	protected int getBlockLightLevel(SatchelSigil entity, BlockPos blockPos) {
		return 15;
	}

	@Override
	public @NonNull SatchelSigilRenderState createRenderState() {
		return new SatchelSigilRenderState();
	}

	@Override
	public void extractRenderState(SatchelSigil entity, SatchelSigilRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.elapsedTime = entity.getLocalElapsedTime() + partialTicks;
		state.liveTime = entity.getLiveTime();
		state.state = entity.getState();
	}

	public static class SatchelSigilRenderState extends EntityRenderState {
		public float elapsedTime;
		public int liveTime;
		public SatchelSigil.State state;
	}
}
