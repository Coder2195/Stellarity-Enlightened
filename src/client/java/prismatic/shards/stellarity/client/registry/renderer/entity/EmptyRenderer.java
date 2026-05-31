package prismatic.shards.stellarity.client.registry.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.Entity;
import org.jspecify.annotations.NonNull;

@Environment(EnvType.CLIENT)
public class EmptyRenderer<T extends Entity> extends EntityRenderer<T, EmptyRenderer.RenderState> {

	public EmptyRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public EmptyRenderer.@NonNull RenderState createRenderState() {
		return new RenderState();
	}

	public static class RenderState extends EntityRenderState {
	}
}
