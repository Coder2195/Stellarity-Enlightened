package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(CauldronInteraction.Dispatcher.class)
public interface CauldronInteractionsDispatcherAccessor {
	@Invoker("put")
	void stellarity$put(final Item item, final CauldronInteraction interaction);
}
