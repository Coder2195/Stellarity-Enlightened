package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.cauldron.CauldronInteractions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(CauldronInteractions.class)
public interface CauldronInteractionsAccessor {
	@Invoker("newDispatcher")
	static CauldronInteraction.Dispatcher newDispatcher(String string) {
		throw new AssertionError("Not transformed!");
	}
}
