package prismatic.shards.stellarity.client.registry.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.registry.entity.Pixie;

@Environment(EnvType.CLIENT)
public class PixieRenderer extends EntityRenderer<Pixie, PixieRenderer.PixieRenderState> {

	public PixieRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public @NonNull PixieRenderState createRenderState() {
		return new PixieRenderState();
	}

	public static class PixieRenderState extends EntityRenderState {
	}
}
