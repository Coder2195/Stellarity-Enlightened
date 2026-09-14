package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.mixin.accessor.CauldronInteractionsAccessor;
import dev.coder2195.stellarity.mixin.accessor.CauldronInteractionsDispatcherAccessor;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.cauldron.CauldronInteractions;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Items;

public interface StellarityCauldronInteractions {
	CauldronInteraction.Dispatcher DRAGON_BREATH = CauldronInteractionsAccessor.newDispatcher("stellarity:dragon_breath");
	static void init() {
		((CauldronInteractionsDispatcherAccessor) CauldronInteractions.WATER).stellarity$put(Items.DRAGON_BREATH, (state, level, pos, player, hand, itemInHand) -> {
			if (level.isClientSide()) return InteractionResult.SUCCESS;
			
			
			return InteractionResult.SUCCESS_SERVER;
		});
	}
}
