package dev.coder2195.stellarity.event;

import net.fabricmc.fabric.api.event.player.PlayerPickItemEvents;
import dev.coder2195.stellarity.registry.StellarityDataComponents;
import dev.coder2195.stellarity.registry.block_entity.ColoredBlockEntity;
import dev.coder2195.stellarity.registry.data_component.Color;

public interface StellarityPlayerPickItemModifications {
	static void init() {
		PlayerPickItemEvents.BLOCK.register((player, pos, state, requestIncludeData) -> {
			var level = player.level();

			if (level.getBlockEntity(pos) instanceof ColoredBlockEntity coloredBlockEntity) {
				var itemStack = state.getCloneItemStack(level, pos, true);
				itemStack.set(StellarityDataComponents.COLOR, new Color(coloredBlockEntity.getColor()));

				return itemStack;
			}

			return null;
		});
	}
}
