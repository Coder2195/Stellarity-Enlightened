package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.mixin.accessor.CauldronInteractionsAccessor;
import net.minecraft.core.cauldron.CauldronInteraction;

public interface StellarityCauldronInteractions {
	CauldronInteraction.Dispatcher DRAGONS_BREATH = CauldronInteractionsAccessor.newDispatcher("stellarity:dragons_breath");
	static void init() {

	}
}
