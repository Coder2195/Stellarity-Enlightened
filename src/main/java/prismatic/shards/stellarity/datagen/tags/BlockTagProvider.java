package prismatic.shards.stellarity.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.tags.StellarityBlockTags;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.tags.BlockTags.*;
import static net.minecraft.world.level.block.Blocks.*;
import static net.minecraft.world.level.block.Blocks.DIRT;
import static prismatic.shards.stellarity.registry.StellarityBlocks.*;
import static prismatic.shards.stellarity.tags.StellarityBlockTags.*;


public class BlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
	public BlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}


	@SafeVarargs
	public final TagAppender<Block, Block> addTags(TagKey<Block> tagKey, TagKey<Block>... tags) {
		var appender = valueLookupBuilder(tagKey);
		for (var tag : tags) {
			appender.forceAddTag(tag);
		}
		return appender;
	}


	@Override
	protected void addTags(HolderLookup.@NonNull Provider provider) {
		addTags(StellarityBlockTags.DIRT).add(ENDER_DIRT, ENDER_GRASS_BLOCK, ROOTED_ENDER_DIRT);
		addTags(MINEABLE_WITH_SHOVEL).add(ENDER_DIRT_PATH, ENDER_DIRT, ENDER_GRASS_BLOCK, ROOTED_ENDER_DIRT);
		addTags(MINEABLE_WITH_PICKAXE).add(ALTAR_OF_THE_ACCURSED);
		addTags(NEEDS_DIAMOND_TOOL).add(ALTAR_OF_THE_ACCURSED);
		addTags(DUNE_SPEED_BLOCKS, BlockTags.SAND, CONCRETE_POWDER).add(GRAVEL, SUSPICIOUS_GRAVEL);
		addTags(SOUL_FIRE_BASE_BLOCKS).add(BEDROCK, CRYING_OBSIDIAN).setReplace(false);
		addTags(WORLDGEN_STALACTITE, BlockTags.TERRACOTTA, BASE_STONE_NETHER, BASE_STONE_OVERWORLD, StellarityBlockTags.DIRT).add(END_STONE, DIRT, TUFF, BASALT, MOSS_BLOCK, SMOOTH_BASALT, DEEPSLATE, COBBLED_DEEPSLATE, GRASS_BLOCK, SCULK, PACKED_MUD, SMOOTH_SANDSTONE, SNOW_BLOCK, Blocks.ICE, WHITE_CONCRETE_POWDER, CALCITE, MOSS_BLOCK, WARPED_WART_BLOCK);
		addTags(ANIMALS_SPAWNABLE_ON).add(ENDER_GRASS_BLOCK);
		addTags(WORLDGEN_END_STONE).add(END_STONE);
		addTags(WORLDGEN_AMETHYST_FOREST_BOTTOM).add(END_STONE, CALCITE, TUFF, GRASS_BLOCK, DIRT, ENDER_DIRT, ENDER_GRASS_BLOCK, DIORITE);
		addTags(WORLDGEN_AMETHYST_GEODE_INVALID, BlockTags.AIR).add(OBSIDIAN);
		addTags(WORLDGEN_GRASS_BLOCK).add(GRASS_BLOCK, ENDER_GRASS_BLOCK);
		addTags(SUPPORTS_VEGETATION).add(ENDER_DIRT, ENDER_GRASS_BLOCK);
		addTags(WORLDGEN_CARVER, WORLDGEN_STALACTITE);
		addTags(WORLDGEN_FIERY_HILLS_COMMON).add(NETHER_WART_BLOCK, Blocks.SAND, CALCITE, WHITE_CONCRETE_POWDER, DIORITE);
		addTags(WORLDGEN_FIERY_HILLS_BASALT, WORLDGEN_FIERY_HILLS_COMMON).add(BASALT, SMOOTH_BASALT);
		addTags(WORLDGEN_FIERY_HILLS_BLACKSTONE, WORLDGEN_FIERY_HILLS_COMMON).add(BLACKSTONE);
		addTags(WORLDGEN_FIERY_HILLS_END_STONE, WORLDGEN_FIERY_HILLS_COMMON).add(END_STONE);
		addTags(WORLDGEN_ENDLESS_DUNES_DUNE, WORLDGEN_FIERY_HILLS_BASALT, WORLDGEN_FIERY_HILLS_BLACKSTONE, WORLDGEN_FIERY_HILLS_END_STONE).add(Blocks.SAND, END_STONE, COARSE_DIRT, SMOOTH_SANDSTONE);
		addTags(WORLDGEN_ENDLESS_DUNES_OASIS).add(ROOTED_ENDER_DIRT, COARSE_DIRT, ENDER_DIRT, Blocks.SAND, END_STONE, ENDER_GRASS_BLOCK, MOSS_BLOCK, Blocks.TERRACOTTA, WHITE_CONCRETE_POWDER, DIORITE, CALCITE);
		addTags(SUPPORTS_SUGAR_CANE, StellarityBlockTags.DIRT);
		addTags(WORLDGEN_FLESH_TUNDRA_SURFACE).add(CRIMSON_NYLIUM, ENDER_GRASS_BLOCK, NETHER_WART_BLOCK, END_STONE);
		addTags(WORLDGEN_FROZEN_SPIKES_SURFACE, BlockTags.ICE).add(SNOW_BLOCK, POWDER_SNOW, CALCITE, END_STONE, NETHERRACK);
		addTags(DRIPSTONE_REPLACEABLE, WORLDGEN_STALACTITE);
	}
}
