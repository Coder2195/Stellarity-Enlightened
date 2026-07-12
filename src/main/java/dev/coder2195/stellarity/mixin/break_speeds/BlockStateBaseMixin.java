package dev.coder2195.stellarity.mixin.break_speeds;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateHolder;
import net.minecraft.world.level.block.state.properties.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import dev.coder2195.stellarity.registry.StellarityBlocks;
import dev.coder2195.stellarity.registry.block.AltarOfTheAccursed;


@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class BlockStateBaseMixin extends StateHolder<Block, BlockState> {


	protected BlockStateBaseMixin(Block owner, Property<?>[] propertyKeys, Comparable<?>[] propertyValues) {
		super(owner, propertyKeys, propertyValues);
	}

	@Shadow
	public abstract Block getBlock();


	@WrapMethod(method = "getDestroySpeed")
	private float dynamicDestroySpeed(BlockGetter blockGetter, BlockPos blockPos, Operation<Float> original) {
		if (getBlock().equals(StellarityBlocks.ALTAR_OF_THE_ACCURSED) && getValue(AltarOfTheAccursed.PLACE_TYPE) == AltarOfTheAccursed.PlaceType.SATCHEL)
			return 50;

		return original.call(blockGetter, blockPos);
	}
}
