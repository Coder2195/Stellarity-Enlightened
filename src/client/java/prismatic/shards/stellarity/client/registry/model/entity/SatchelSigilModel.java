
package prismatic.shards.stellarity.client.registry.model.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.util.Mth;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.client.registry.renderer.entity.SatchelSigilRenderer;
import prismatic.shards.stellarity.registry.entity.SatchelSigil;

@Environment(EnvType.CLIENT)
public class SatchelSigilModel extends EntityModel<SatchelSigilRenderer.SatchelSigilRenderState> {
	private final ModelPart top;
	private final ModelPart bottom;

	public SatchelSigilModel(ModelPart root) {
		super(root, RenderTypes::entityTranslucent);
		this.top = root.getChild("top");
		this.bottom = root.getChild("bottom");
	}


	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition root = modelData.getRoot();

		root.addOrReplaceChild("top", CubeListBuilder.create().texOffs(-32, 0).addBox(-16.0F, 2F, -16.0F, 32.0F, 0.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0, 0, 0));
		root.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(-32, 32).addBox(-16.0F, 0.4F, -16.0F, 32.0F, 0.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0, 0, 0));

		return LayerDefinition.create(modelData, 64, 64);
	}

	@Override
	public void setupAnim(SatchelSigilRenderer.@NonNull SatchelSigilRenderState state) {
		super.setupAnim(state);

		float scale = state.state.equals(SatchelSigil.State.OPENING) ? Mth.clamp(state.elapsedTime / 10, 0, 1) :
			state.state.equals(SatchelSigil.State.CLOSING) ?
				Mth.clamp((state.liveTime + SatchelSigil.TRANSITION_DURATION * 2 - state.elapsedTime) / SatchelSigil.TRANSITION_DURATION, 0, 1) : 1;

		top.xScale = top.zScale = bottom.xScale = bottom.zScale = scale;
		top.yRot = state.elapsedTime / 20;
		bottom.yRot = -state.elapsedTime / 20;
	}
}