package dev.coder2195.stellarity.tags;

import dev.coder2195.stellarity.Stellarity;
import net.minecraft.tags.BlockItemTagId;

public interface StellarityBlockItemTags {
	BlockItemTagId WOOD_EXCEPT_CHERRY = id("wood_except_cherry");
	BlockItemTagId LOGS_EXCEPT_CHERRY = id("logs_except_cherry");
	BlockItemTagId STRIPPED_WOOD_EXCEPT_CHERRY = id("stripped_wood_except_cherry");
	BlockItemTagId STRIPPED_LOGS_EXCEPT_CHERRY = id("stripped_logs_except_cherry");
	BlockItemTagId LEAVES_EXCEPT_CHERRY = id("leaves_except_cherry");

	BlockItemTagId ASHEN_LOGS = id("ashen_logs");
	BlockItemTagId AMETHYII_LOGS = id("amethyii_logs");
	BlockItemTagId HALLOWED_LOGS = id("hallowed_logs");
	BlockItemTagId SHRUBBED_LOGS = id("shrubbed_logs");
	BlockItemTagId PRISMATIC_LOGS = id("prismatic_logs");
	BlockItemTagId INFERNO_LOGS = id("inferno_logs");

	static BlockItemTagId id(String id) {
		return BlockItemTagId.create(Stellarity.id(id), Stellarity.id(id));
	}

}
