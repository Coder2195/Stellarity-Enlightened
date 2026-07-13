package dev.coder2195.stellarity.registry.data_component;

import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public record LoafOfPlentyEats(int amount) implements TooltipProvider {
	@Override
	public void addToTooltip(Item.@NonNull TooltipContext context, @NonNull Consumer<Component> consumer, @NonNull TooltipFlag flag, @NonNull DataComponentGetter components) {
		consumer.accept(Component.translatable(""));
	}
}
