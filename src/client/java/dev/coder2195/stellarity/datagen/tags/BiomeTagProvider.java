package dev.coder2195.stellarity.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static dev.coder2195.stellarity.registry.StellarityBiomes.*;
import static dev.coder2195.stellarity.tags.StellarityBiomeTags.*;
import static net.minecraft.world.level.biome.Biomes.*;

public class BiomeTagProvider extends FabricTagsProvider<Biome> {

	public BiomeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, Registries.BIOME, registriesFuture);
	}

	public static Stream<ResourceKey<Biome>> stellarity() {
		return Stream.of(AMETHYST_FOREST, ASHFALL_DELTAS, CRYSTAL_CRAGS, END_SHRUBLAND, END_WILDS, ENDER_WASTES, ENDLESS_DUNES, FIERY_HILLS, FLESH_TUNDRA, FROSTED_VALLEY, FROZEN_MARSH, FROZEN_SHRUBLAND, FROZEN_SPIKES, HALLOWED_TUNDRA, PRISMARINE_FOREST, THE_HALLOW, THE_NEST, WARPED_MARSH);
	}

	public static Stream<ResourceKey<Biome>> outerVanilla() {
		return Stream.of(END_BARRENS, END_HIGHLANDS, END_MIDLANDS);
	}
	
	@SafeVarargs
	public final TagAppender<Biome> addTags(TagKey<Biome> tagKey, TagKey<Biome>... tags) {
		var appender = builder(tagKey);
		for (var tag : tags) {
			appender.forceAddTag(tag);
		}
		return appender;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.Provider provider) {
		addTags(SNOWY).add(FROZEN_SPIKES, FROSTED_VALLEY, FROZEN_MARSH, HALLOWED_TUNDRA, FROZEN_SHRUBLAND);
		addTags(ALL_STELLARITY).addAll(stellarity());
		addTags(ALL_OUTER).forceAddTag(ALL_STELLARITY).addAll(outerVanilla());
		addTags(ConventionalBiomeTags.IS_END).forceAddTag(ALL_STELLARITY);
		addTags(BiomeTags.IS_END).forceAddTag(ALL_STELLARITY);
		addTags(BiomeTags.ALLOWS_TROPICAL_FISH_SPAWNS_AT_ANY_HEIGHT).forceAddTag(ALL_STELLARITY);
		addTags(SPAWNS_ASH_VOIDED_SKELETON).add(ASHFALL_DELTAS, FIERY_HILLS);
		addTags(SPAWNS_COLD_VOIDED_SKELETON).add(FROSTED_VALLEY, FROZEN_MARSH, FROZEN_SHRUBLAND, FROZEN_SPIKES, HALLOWED_TUNDRA);
		addTags(SPAWNS_FLESH_VOIDED_SKELETON).add(FLESH_TUNDRA);
		addTags(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS).add(ASHFALL_DELTAS, WARPED_MARSH);
		addTags(HAS_STRUCTURE_CAMPSITE).add(END_MIDLANDS, END_WILDS, END_SHRUBLAND, FROZEN_SHRUBLAND, FROZEN_MARSH, FROZEN_SPIKES, WARPED_MARSH, END_HIGHLANDS, AMETHYST_FOREST, PRISMARINE_FOREST, ENDLESS_DUNES, CRYSTAL_CRAGS);
		addTags(HAS_STRUCTURE_CHAPEL).add(THE_HALLOW, PRISMATIC_DUNES, HALLOWED_TUNDRA);
		addTags(HAS_STRUCTURE_DESERT_RUIN).add(ENDLESS_DUNES);
		addTags(HAS_STRUCTURE_FISHING_HUT).add(END_MIDLANDS, END_SHRUBLAND, FROZEN_SHRUBLAND, FROZEN_MARSH, FROZEN_SPIKES, WARPED_MARSH, END_HIGHLANDS, AMETHYST_FOREST, PRISMARINE_FOREST, FLESH_TUNDRA, FROSTED_VALLEY, FIERY_HILLS, ENDLESS_DUNES);
		addTags(HAS_STRUCTURE_FOSSIL).add(FLESH_TUNDRA, THE_NEST, ASHFALL_DELTAS);
		addTags(HAS_STRUCTURE_OBSIDIAN_SPIKE).add(END_BARRENS, END_HIGHLANDS, END_MIDLANDS, SMALL_END_ISLANDS, AMETHYST_FOREST, ASHFALL_DELTAS, WARPED_MARSH, END_WILDS, ENDER_WASTES, FLESH_TUNDRA, THE_NEST, FIERY_HILLS, THE_HALLOW, FROZEN_SPIKES, PRISMARINE_FOREST, CRYSTAL_CRAGS, ENDLESS_DUNES, HALLOWED_TUNDRA, PRISMATIC_DUNES, FROZEN_MARSH, FROZEN_SHRUBLAND, END_SHRUBLAND);
		addTags(HAS_STRUCTURE_END_VILLAGE).add(THE_HALLOW, PRISMATIC_DUNES, HALLOWED_TUNDRA, END_HIGHLANDS, PRISMARINE_FOREST, AMETHYST_FOREST, END_MIDLANDS, ENDLESS_DUNES, WARPED_MARSH, FROZEN_MARSH);
		addTags(NO_VOID_FISHING).add(THE_END);
		addTags(ALLOWS_CONSECRATION).add(THE_HALLOW);
		addTags(HAS_STRUCTURE_FLOATING_TREASURE, ALL_STELLARITY);
	}
}
