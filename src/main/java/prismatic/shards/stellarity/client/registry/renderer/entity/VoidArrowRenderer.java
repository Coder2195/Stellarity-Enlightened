package prismatic.shards.stellarity.client.registry.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.entity.VoidArrow;

@Environment(EnvType.CLIENT)
public class VoidArrowRenderer extends ArrowRenderer<VoidArrow, ArrowRenderState> {
	public static final Identifier SPECTRAL_ARROW_LOCATION = Stellarity.id("textures/entity/projectiles/void_arrow.png");

	public VoidArrowRenderer(final EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	protected @NonNull Identifier getTextureLocation(final ArrowRenderState state) {
		return SPECTRAL_ARROW_LOCATION;
	}

	public @NonNull ArrowRenderState createRenderState() {
		return new ArrowRenderState();
	}
}