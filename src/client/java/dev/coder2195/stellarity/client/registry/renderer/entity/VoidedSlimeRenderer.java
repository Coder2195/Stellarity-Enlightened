package dev.coder2195.stellarity.client.registry.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;
import dev.coder2195.stellarity.Stellarity;

public class VoidedSlimeRenderer extends SlimeRenderer {
	public VoidedSlimeRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	public static final Identifier TEXTURE = Stellarity.id("textures/entity/voided_slime/voided_slime.png");

	@Override
	public @NonNull Identifier getTextureLocation(@NonNull SlimeRenderState state) {
		return TEXTURE;
	}
}
