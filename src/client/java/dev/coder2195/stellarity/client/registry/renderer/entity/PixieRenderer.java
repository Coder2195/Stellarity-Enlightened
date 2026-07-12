package dev.coder2195.stellarity.client.registry.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.client.registry.StellarityEntityModelLayers;
import dev.coder2195.stellarity.client.registry.model.entity.PixieModel;
import dev.coder2195.stellarity.registry.entity.Pixie;

public class PixieRenderer extends MobRenderer<Pixie, LivingEntityRenderState, PixieModel> {
	public static final Identifier TEXTURE = Stellarity.id("textures/entity/pixie/pixie.png");

	public PixieRenderer(EntityRendererProvider.Context context) {
		super(context, new PixieModel(context.bakeLayer(StellarityEntityModelLayers.PIXIE)), 0f);
	}

	@Override
	public @NonNull Identifier getTextureLocation(@NonNull LivingEntityRenderState state) {
		return TEXTURE;
	}

	@Override
	public @NonNull LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}
}
