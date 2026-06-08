package prismatic.shards.stellarity.event;

import net.fabricmc.fabric.api.event.player.PlayerPickItemEvents;
import prismatic.shards.stellarity.registry.StellarityDataComponents;
import prismatic.shards.stellarity.registry.block_entity.ColoredBlockEntity;
import prismatic.shards.stellarity.registry.data_component.Color;

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
