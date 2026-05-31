package prismatic.shards.stellarity.client.registry.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;
import net.minecraft.resources.Identifier;
import prismatic.shards.stellarity.Stellarity;

@Environment(EnvType.CLIENT)
public class VoidedSlimeRenderer extends SlimeRenderer {
	public VoidedSlimeRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	public static final Identifier TEXTURE = Stellarity.id("textures/entity/voided_slime/voided_slime.png");

	@Override
	public Identifier getTextureLocation(SlimeRenderState state) {
		return TEXTURE;
	}
}
