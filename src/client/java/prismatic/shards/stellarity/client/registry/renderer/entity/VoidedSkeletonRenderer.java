package prismatic.shards.stellarity.client.registry.renderer.entity;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.registry.entity.VoidedSkeleton;

public class VoidedSkeletonRenderer extends AbstractSkeletonRenderer<VoidedSkeleton, VoidedSkeletonRenderer.VoidedSkeletonRenderState> {
	public VoidedSkeletonRenderer(final EntityRendererProvider.Context context) {
		super(context, ModelLayers.SKELETON, ModelLayers.SKELETON_ARMOR);
	}

	public static class VoidedSkeletonRenderState extends SkeletonRenderState {
		public Identifier texture;
	}


	@Override
	public @NonNull VoidedSkeletonRenderState createRenderState() {
		return new VoidedSkeletonRenderState();
	}

	@Override
	public void extractRenderState(@NonNull VoidedSkeleton entity, @NonNull VoidedSkeletonRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.texture = entity.getVariant().value().assetInfo().texturePath();
	}

	@Override
	public @NonNull Identifier getTextureLocation(VoidedSkeletonRenderState state) {
		return state.texture;
	}
}
