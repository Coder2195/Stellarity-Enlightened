package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;

public interface StellarityBlockIds {
	ResourceKey<Block> AMETHYII_WALL_SIGN = id("amethyii_wall_sign");
	ResourceKey<Block> AMETHYII_WALL_HANGING_SIGN = id("amethyii_wall_hanging_sign");
	ResourceKey<Block> POTTED_AMETHYII_SAPLING = id("potted_amethyii_sapling");

	ResourceKey<Block> HALLOWED_WALL_SIGN = id("hallowed_wall_sign");
	ResourceKey<Block> HALLOWED_WALL_HANGING_SIGN = id("hallowed_wall_hanging_sign");
	ResourceKey<Block> POTTED_HALLOWED_SAPLING = id("potted_hallowed_sapling");

	ResourceKey<Block> SHRUBBED_WALL_SIGN = id("shrubbed_wall_sign");
	ResourceKey<Block> SHRUBBED_WALL_HANGING_SIGN = id("shrubbed_wall_hanging_sign");
	ResourceKey<Block> POTTED_SHRUBBED_SAPLING = id("potted_shrubbed_sapling");

	ResourceKey<Block> PRISMATIC_WALL_SIGN = id("prismatic_wall_sign");
	ResourceKey<Block> PRISMATIC_WALL_HANGING_SIGN = id("prismatic_wall_hanging_sign");
	ResourceKey<Block> POTTED_PRISMATIC_SAPLING = id("potted_prismatic_sapling");

	ResourceKey<Block> ASHEN_WALL_SIGN = id("ashen_wall_sign");
	ResourceKey<Block> ASHEN_WALL_HANGING_SIGN = id("ashen_wall_hanging_sign");
	ResourceKey<Block> POTTED_ASHEN_SAPLING = id("potted_ashen_sapling");

	ResourceKey<Block> INFERNO_WALL_SIGN = id("inferno_wall_sign");
	ResourceKey<Block> INFERNO_WALL_HANGING_SIGN = id("inferno_wall_hanging_sign");
	ResourceKey<Block> POTTED_INFERNO_SAPLING = id("potted_inferno_sapling");

	private static ResourceKey<Block> id(String id) {
		return Stellarity.key(Registries.BLOCK, id);
	}
}
