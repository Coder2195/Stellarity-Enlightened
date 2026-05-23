
package prismatic.shards.stellarity.client.registry.model.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.client.registry.renderer.entity.SatchelSigilRenderer;

@Environment(EnvType.CLIENT)
public class SatchelSigilModel extends EntityModel<SatchelSigilRenderer.SatchelSigilRenderState> {
	private final ModelPart top;
	private final ModelPart bottom;

	public SatchelSigilModel(ModelPart root) {
		super(root);
		this.top = root.getChild("top");
		this.bottom = root.getChild("bottom");
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition root = modelData.getRoot();

		root.addOrReplaceChild("top", CubeListBuilder.create().texOffs(-32, 0).addBox(-16.0F, -2.0F, -16.0F, 32.0F, 0.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0, 0, 0));
		root.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(-32, 32).addBox(-16.0F, -1.0F, -16.0F, 32.0F, 0.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0, 1, 0));

		return LayerDefinition.create(modelData, 64, 64);
	}

	@Override
	public void setupAnim(SatchelSigilRenderer.@NonNull SatchelSigilRenderState state) {
		super.setupAnim(state);

		top.yRot = state.elapsedTime / 20;
		bottom.yRot = -state.elapsedTime / 20;
	}
}