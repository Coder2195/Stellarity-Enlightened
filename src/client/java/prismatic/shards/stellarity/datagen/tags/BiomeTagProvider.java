package prismatic.shards.stellarity.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static net.minecraft.world.level.biome.Biomes.*;
import static prismatic.shards.stellarity.key.StellarityBiomes.*;
import static prismatic.shards.stellarity.tags.StellarityBiomeTags.*;

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


	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.@NonNull Provider provider) {
		builder(SNOWY).add(FROZEN_SPIKES, FROSTED_VALLEY, FROZEN_MARSH, HALLOWED_TUNDRA, FROZEN_SHRUBLAND);
		builder(ALL_STELLARITY).addAll(stellarity());
		builder(ALL_OUTER).forceAddTag(ALL_STELLARITY).addAll(outerVanilla());
		builder(ConventionalBiomeTags.IS_END).forceAddTag(ALL_STELLARITY);
		builder(BiomeTags.IS_END).forceAddTag(ALL_STELLARITY);
		builder(BiomeTags.ALLOWS_TROPICAL_FISH_SPAWNS_AT_ANY_HEIGHT).forceAddTag(ALL_STELLARITY);
		builder(SPAWNS_ASH_VOIDED_SKELETON).add(ASHFALL_DELTAS, FIERY_HILLS);
		builder(SPAWNS_COLD_VOIDED_SKELETON).add(FROSTED_VALLEY, FROZEN_MARSH, FROZEN_SHRUBLAND, FROZEN_SPIKES, HALLOWED_TUNDRA);
		builder(SPAWNS_FLESH_VOIDED_SKELETON).add(FLESH_TUNDRA);
		builder(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS).add(ASHFALL_DELTAS, WARPED_MARSH);
		builder(HAS_STRUCTURE_CAMPSITE).add(END_MIDLANDS, END_WILDS, END_SHRUBLAND, FROZEN_SHRUBLAND, FROZEN_MARSH, FROZEN_SPIKES, WARPED_MARSH, END_HIGHLANDS, AMETHYST_FOREST, PRISMARINE_FOREST, ENDLESS_DUNES, CRYSTAL_CRAGS);
	}
}
