
package prismatic.shards.stellarity.client.mixin.phantom_item_frame_model;

import com.llamalad7.mixinextras.sugar.Local;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.client.resources.model.BlockStateDefinitions;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import prismatic.shards.stellarity.Stellarity;

import java.util.Map;
import java.util.function.Function;

import static prismatic.shards.stellarity.client.registry.renderer.entity.PhantomItemFrameRenderer.FAKE_STATE_DEFINITION;

@MixinEnvironment("client")
@Mixin(BlockStateDefinitions.class)
public class BlockStateDefinitionsMixin {
	@Inject(method = "definitionLocationToBlockStateMapper", at = @At(value = "INVOKE", target = "Ljava/util/Objects;requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;"))
	private static void addStellarityBlockStates(CallbackInfoReturnable<Function<Identifier, StateDefinition<Block, BlockState>>> cir, @Local(name = "result") Map<Identifier, StateDefinition<Block, BlockState>> result) {
		result.put(Stellarity.id("phantom_item_frame"), FAKE_STATE_DEFINITION);
	}
}
