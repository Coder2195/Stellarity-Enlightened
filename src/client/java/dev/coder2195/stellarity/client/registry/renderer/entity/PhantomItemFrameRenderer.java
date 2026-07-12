package dev.coder2195.stellarity.client.registry.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemFrameRenderer;
import net.minecraft.client.renderer.entity.state.ItemFrameRenderState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import org.jspecify.annotations.NonNull;
import dev.coder2195.stellarity.registry.entity.PhantomItemFrame;


public class PhantomItemFrameRenderer extends ItemFrameRenderer<PhantomItemFrame> {
	public static final StateDefinition<Block, BlockState> FAKE_STATE_DEFINITION = (new StateDefinition.Builder<Block, BlockState>(Blocks.AIR)).add(new Property[]{}).create(Block::defaultBlockState, BlockState::new);


	public PhantomItemFrameRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public void extractRenderState(@NonNull PhantomItemFrame entity, @NonNull ItemFrameRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);

		if (!state.isInvisible) {

			this.blockModelResolver.update(state.frameModel, FAKE_STATE_DEFINITION.any(), ItemFrameRenderer.BLOCK_DISPLAY_CONTEXT);
		}
	}
}