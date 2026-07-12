package dev.coder2195.stellarity.client.registry.model.entity;


import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import org.jspecify.annotations.NonNull;

public class PixieModel extends EntityModel<LivingEntityRenderState> {
	private final ModelPart body;
	private final ModelPart wing1;
	private final ModelPart wing2;

	public PixieModel(ModelPart root) {
		super(root, RenderTypes::entityTranslucent);
		this.body = root.getChild("body");
		this.wing1 = root.getChild("wing1");
		this.wing2 = root.getChild("wing2");
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();

		root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 8).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.0F, -1.0F));

		root.addOrReplaceChild("wing1", CubeListBuilder.create().texOffs(-4, 4).addBox(0.0F, 0.0F, -3.0F, 8.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 22.0F, 0.0F));

		root.addOrReplaceChild("wing2", CubeListBuilder.create().texOffs(-4, 0).addBox(-8.0F, 0.0F, -2.0F, 8.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 22.0F, -1.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}


	@Override
	public void setupAnim(@NonNull LivingEntityRenderState state) {
		super.setupAnim(state);

		float wingRot = (float) (Math.sin(state.ageInTicks * 2) * (Math.PI / 4));
		wing1.setRotation(0f, 0f, wingRot);
		wing2.setRotation(0f, 0f, -wingRot);
	}
}