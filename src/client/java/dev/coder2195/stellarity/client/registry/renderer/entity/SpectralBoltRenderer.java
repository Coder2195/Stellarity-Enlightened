package dev.coder2195.stellarity.client.registry.renderer.entity;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.entity.SpectralBolt;

public class SpectralBoltRenderer extends ArrowRenderer<SpectralBolt, ArrowRenderState> {
	public static final Identifier TEXTURE = Stellarity.id("textures/entity/projectiles/spectral_bolt.png");

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
