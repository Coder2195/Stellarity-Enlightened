package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.tags.StellarityBlockTags;
import dev.coder2195.stellarity.tags.StellarityItemTags;
import net.minecraft.world.item.ToolMaterial;

public interface StellarityToolMaterials {
	ToolMaterial SHULKER = new ToolMaterial(StellarityBlockTags.INCORRECT_FOR_SHULKER_TOOL, 2701, 14.4f, 4.5f, 15, StellarityItemTags.SHULKER_TOOL_MATERIALS) {
	};

	ToolMaterial SPIRIT_DAGGER = new ToolMaterial(StellarityBlockTags.EMPTY, 496, 1, 1, 10, StellarityItemTags.EMPTY);
}
