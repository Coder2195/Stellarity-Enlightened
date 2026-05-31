package prismatic.shards.stellarity.client.registry.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SilverfishRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.Stellarity;

public class VoidedSilverfishRenderer extends SilverfishRenderer {

	public VoidedSilverfishRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	public static final Identifier TEXTURE = Stellarity.id("textures/entity/voided_silverfish/voided_silverfish.png");

	@Override
	public @NonNull Identifier getTextureLocation(@NonNull LivingEntityRenderState state) {
		return TEXTURE;
	}
}
