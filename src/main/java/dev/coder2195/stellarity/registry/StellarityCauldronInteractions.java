package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.mixin.accessor.CauldronInteractionsAccessor;
import dev.coder2195.stellarity.mixin.accessor.CauldronInteractionsDispatcherAccessor;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.cauldron.CauldronInteractions;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

public interface StellarityCauldronInteractions {
	CauldronInteraction.Dispatcher DRAGON_BREATH = CauldronInteractionsAccessor.newDispatcher("stellarity:dragon_breath");
	static void init() {
		((CauldronInteractionsDispatcherAccessor) CauldronInteractions.WATER).stellarity$put(Items.DRAGON_BREATH, (state, level, pos, player, hand, itemInHand) -> {
			if (level.isClientSide()) return InteractionResult.SUCCESS;
			level.setBlock(pos, StellarityBlocks.DRAGON_BREATH_CAULDRON.withPropertiesOf(state), Block.UPDATE_ALL);
			itemInHand.consume(1, player);
			player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));

			return InteractionResult.SUCCESS_SERVER;
		});
	}
}
