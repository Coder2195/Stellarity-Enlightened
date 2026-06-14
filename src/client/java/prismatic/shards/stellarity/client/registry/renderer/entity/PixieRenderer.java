package prismatic.shards.stellarity.client.registry.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.client.registry.StellarityEntityModelLayers;
import prismatic.shards.stellarity.client.registry.model.entity.PixieModel;
import prismatic.shards.stellarity.registry.entity.Pixie;

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
