package dev.coder2195.stellarity.datagen.tags;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockItemTags;
import net.minecraft.data.tags.BlockItemTagAppender;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.world.item.Item;

import static dev.coder2195.stellarity.registry.StellarityBlockItemIds.*;
import static dev.coder2195.stellarity.tags.StellarityBlockItemTags.*;
import static net.minecraft.references.BlockItemIds.*;
import static net.minecraft.tags.BlockItemTags.*;


import java.util.function.BiFunction;

public class BlockItemTagProvider<T> {

	private final BiFunction<BlockItemTagId, BlockItemTagId[], BlockItemTagAppender<T>> tagAppender;
	private final boolean blockMode;

	public BlockItemTagProvider(BiFunction<BlockItemTagId, BlockItemTagId[], BlockItemTagAppender<T>> tagAppender, boolean blockMode) {
		this.tagAppender = tagAppender;
		this.blockMode = blockMode;
	}

	@SuppressWarnings("unchecked")
	private ResourceKey<T> key(BlockItemId tagKey) {
		return (ResourceKey<T>) (blockMode ? tagKey.block() : tagKey.item());
	}

	public final BlockItemTagAppender<T> addTags(BlockItemTagId tagKey, BlockItemTagId... tags) {
		return this.tagAppender.apply(tagKey, tags);
	}

	@SuppressWarnings("DuplicatedCode")
	protected void run() {
		addTags(STRIPPED_LOGS_EXCEPT_CHERRY, ConventionalBlockItemTags.STRIPPED_LOGS).remove(key(STRIPPED_CHERRY_LOG));
		addTags(LOGS_EXCEPT_CHERRY, ConventionalBlockItemTags.NATURAL_LOGS).remove(key(CHERRY_LOG));
		addTags(WOOD_EXCEPT_CHERRY, ConventionalBlockItemTags.NATURAL_WOODS).remove(key(CHERRY_WOOD));
		addTags(STRIPPED_WOOD_EXCEPT_CHERRY, ConventionalBlockItemTags.STRIPPED_WOODS).remove(key(STRIPPED_CHERRY_WOOD));
		addTags(LEAVES_EXCEPT_CHERRY, BlockItemTags.LEAVES).remove(key(CHERRY_LEAVES));
		
		addTags(LOGS_THAT_BURN).add(ASHEN_LOG, AMETHYII_LOG, HALLOWED_LOG, SHRUBBED_LOG, PRISMATIC_LOG, INFERNO_LOG);
		addTags(LEAVES).add(ASHEN_LEAVES, AMETHYII_LEAVES, HALLOWED_LEAVES, SHRUBBED_LEAVES, PRISMATIC_LEAVES, INFERNO_LEAVES);
		addTags(WOODEN_SHELVES).add(ASHEN_SHELF, AMETHYII_SHELF, HALLOWED_SHELF, SHRUBBED_SHELF, PRISMATIC_SHELF, INFERNO_SHELF);
		addTags(SAPLINGS).add(ASHEN_SAPLING, AMETHYII_SAPLING, HALLOWED_SAPLING, SHRUBBED_SAPLING, PRISMATIC_SAPLING, INFERNO_SAPLING);
		addTags(WOODEN_STAIRS).add(ASHEN_STAIRS, AMETHYII_STAIRS, HALLOWED_STAIRS, SHRUBBED_STAIRS, PRISMATIC_STAIRS, INFERNO_STAIRS);
		addTags(WOODEN_SLABS).add(ASHEN_SLAB, AMETHYII_SLAB, HALLOWED_SLAB, SHRUBBED_SLAB, PRISMATIC_SLAB, INFERNO_SLAB);
		addTags(WOODEN_FENCES).add(ASHEN_FENCE, AMETHYII_FENCE, HALLOWED_FENCE, SHRUBBED_FENCE, PRISMATIC_FENCE, INFERNO_FENCE);
		addTags(FENCE_GATES).add(ASHEN_FENCE_GATE, AMETHYII_FENCE_GATE, HALLOWED_FENCE_GATE, SHRUBBED_FENCE_GATE, PRISMATIC_FENCE_GATE, INFERNO_FENCE_GATE);
		addTags(WOODEN_DOORS).add(ASHEN_DOOR, AMETHYII_DOOR, HALLOWED_DOOR, SHRUBBED_DOOR, PRISMATIC_DOOR, INFERNO_DOOR);
		addTags(WOODEN_TRAPDOORS).add(ASHEN_TRAPDOOR, AMETHYII_TRAPDOOR, HALLOWED_TRAPDOOR, SHRUBBED_TRAPDOOR, PRISMATIC_TRAPDOOR, INFERNO_TRAPDOOR);
		addTags(WOODEN_BUTTONS).add(ASHEN_BUTTON, AMETHYII_BUTTON, HALLOWED_BUTTON, SHRUBBED_BUTTON, PRISMATIC_BUTTON, INFERNO_BUTTON);
	}
}
