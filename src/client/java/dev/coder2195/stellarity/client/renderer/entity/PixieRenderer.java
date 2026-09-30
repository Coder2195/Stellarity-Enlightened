package dev.coder2195.stellarity.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.client.registry.StellarityModelLayers;
import dev.coder2195.stellarity.client.model.entity.PixieModel;
import dev.coder2195.stellarity.entity.Pixie;

public class PixieRenderer extends MobRenderer<Pixie, LivingEntityRenderState, PixieModel> {
	public static final Identifier TEXTURE = Stellarity.id("textures/entity/pixie/pixie.png");

	public PixieRenderer(EntityRendererProvider.Context context) {
		super(context, new PixieModel(context.bakeLayer(StellarityModelLayers.PIXIE)), 0f);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		return TEXTURE;
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}
}
