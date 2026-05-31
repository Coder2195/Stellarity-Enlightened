package prismatic.shards.stellarity.client.registry.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SilverfishRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import prismatic.shards.stellarity.Stellarity;

@Environment(EnvType.CLIENT)
public class VoidedSilverfishRenderer extends SilverfishRenderer {

	public VoidedSilverfishRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	public static final Identifier TEXTURE = Stellarity.id("textures/entity/voided_silverfish/voided_silverfish.png");

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		return TEXTURE;
	}
}
