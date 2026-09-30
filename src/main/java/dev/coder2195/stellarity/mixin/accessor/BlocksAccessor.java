package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.references.BlockItemId;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Blocks.class)
public interface BlocksAccessor {
	@Invoker("registerStair")
	static Block registerStair(final BlockItemId id, final Block base){
		throw new AssertionError("Not transformed");
	}

	@Invoker("registerSlab")
	static Block registerSlab(final BlockItemId id, final Block base) {
		throw new AssertionError("Not transformed");
	}

	@Invoker("wallVariant")
	static BlockBehaviour.Properties wallVariant(Block standingBlock, boolean copyName) {
		throw new AssertionError("Not transformed");
	}
}