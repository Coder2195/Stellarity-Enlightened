package dev.coder2195.stellarity.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import dev.coder2195.stellarity.Stellarity;

public interface StellarityItemTags {
	TagKey<Item> FISHES = id("fishes");
	TagKey<Item> BOWS = id("bows");
	TagKey<Item> ELYTRA_ENCHANTABLE = id("enchantable.elytra");
	TagKey<Item> RANGED_ENCHANTABLE = id("enchantable.ranged");
	TagKey<Item> DONATOR = id("donator");
	TagKey<Item> DEVELOPER = id("developer");

	static TagKey<Item> id(String id) {
		return TagKey.create(Registries.ITEM, Stellarity.id(id));
	}

}
