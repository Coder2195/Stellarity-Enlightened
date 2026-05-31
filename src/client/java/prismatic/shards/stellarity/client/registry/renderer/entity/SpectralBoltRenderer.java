package prismatic.shards.stellarity.client.registry.renderer.entity;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.entity.SpectralBolt;

public class SpectralBoltRenderer extends ArrowRenderer<SpectralBolt, ArrowRenderState> {
	public static final Identifier TEXTURE = Stellarity.mcId("textures/entity/projectiles/arrow_spectral.png");

	public SpectralBoltRenderer(final EntityRendererProvider.Context context) {
		super(context);
	}

	protected @NonNull Identifier getTextureLocation(final @NonNull ArrowRenderState state) {
		return TEXTURE;
	}

	public @NonNull ArrowRenderState createRenderState() {
		return new ArrowRenderState();
	}
}
