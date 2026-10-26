package dev.coder2195.stellarity.client.model.entity;

// Made with Blockbench 5.2.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import dev.coder2195.stellarity.client.renderer.entity.SpiritRenderer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class SpiritModel extends EntityModel<SpiritRenderer.SpiritRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart inner;
	private final ModelPart aura;

	public SpiritModel(ModelPart root) {
		super(root, RenderTypes::entityTranslucent);

		this.inner = root.getChild("inner");
		this.aura = root.getChild("aura");
	}

	public static LayerDefinition getLayerDefinition() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition root = modelData.getRoot();
		root.addOrReplaceChild("inner", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 0.0F));

		root.addOrReplaceChild("aura", CubeListBuilder.create().texOffs(0, 21).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 0.0F));
		return LayerDefinition.create(modelData, 64, 64);
	}


	@Override
	public void setupAnim(SpiritRenderer.SpiritRenderState state) {
		super.setupAnim(state);

		inner.xRot = (state.ageInTicks) * state.innerAngularVelocity.x() / 20;
		inner.zRot = (state.ageInTicks) * state.innerAngularVelocity.y() / 20;
		inner.yRot = (state.ageInTicks) * state.innerAngularVelocity.z() / 20;

		aura.yRot = (state.ageInTicks) * state.auraAngularVelocity.x() / 20;
		aura.xRot = (state.ageInTicks) * state.auraAngularVelocity.y() / 20;
		aura.zRot = (state.ageInTicks) * state.auraAngularVelocity.z() / 20;
		aura.xScale = aura.yScale = aura.zScale = 1.4f;
	}

}