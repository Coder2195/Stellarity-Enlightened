package prismatic.shards.stellarity.client.registry;

import net.minecraft.client.color.item.ItemTintSources;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.client.registry.item_tint_source.ColorTintSource;

public interface StellarityItemTintSources {
	static void init() {
		ItemTintSources.ID_MAPPER.put(Stellarity.id("color"), ColorTintSource.MAP_CODEC);

		Stellarity.LOGGER.info("Initialized Block Tint Sources");
	}
}
