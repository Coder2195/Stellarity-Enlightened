package prismatic.shards.stellarity.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityItemTags {
	TagKey<Item> FISHES = id("fishes");
	TagKey<Item> BOWS = id("bows");
	TagKey<Item> ELYTRA_ENCHANTABLE = id("enchantable.elytra");
	TagKey<Item> RANGED_ENCHANTABLE = id("enchantable.ranged");

	static TagKey<Item> id(String id) {
		return TagKey.create(Registries.ITEM, Stellarity.id(id));
	}

}
