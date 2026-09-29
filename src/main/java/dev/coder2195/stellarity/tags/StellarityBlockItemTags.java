package dev.coder2195.stellarity.tags;

import dev.coder2195.stellarity.Stellarity;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public interface StellarityBlockItemTags {
	BlockItemTagId WOOD_EXCEPT_CHERRY = id("wood_except_cherry");
	BlockItemTagId LOGS_EXCEPT_CHERRY = id("logs_except_cherry");
	BlockItemTagId STRIPPED_WOOD_EXCEPT_CHERRY = id("stripped_wood_except_cherry");
	BlockItemTagId STRIPPED_LOGS_EXCEPT_CHERRY = id("stripped_logs_except_cherry");
	BlockItemTagId LEAVES_EXCEPT_CHERRY = id("leaves_except_cherry");

	static BlockItemTagId id(String id) {
		return BlockItemTagId.create(Stellarity.id(id), Stellarity.id(id));
	}

}
