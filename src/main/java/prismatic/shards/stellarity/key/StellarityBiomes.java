package prismatic.shards.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityBiomes {
	ResourceKey<Biome> AMETHYST_FOREST = id("amethyst_forest");
	ResourceKey<Biome> ASHFALL_DELTAS = id("ashfall_deltas");
	ResourceKey<Biome> CRYSTAL_CRAGS = id("crystal_crags");
	ResourceKey<Biome> END_SHRUBLAND = id("end_shrubland");
	ResourceKey<Biome> END_WILDS = id("end_wilds");
	ResourceKey<Biome> ENDER_WASTES = id("ender_wastes");
	ResourceKey<Biome> ENDLESS_DUNES = id("endless_dunes");
	ResourceKey<Biome> FIERY_HILLS = id("fiery_hills");
	ResourceKey<Biome> FLESH_TUNDRA = id("flesh_tundra");
	ResourceKey<Biome> FROSTED_VALLEY = id("frosted_valley");
	ResourceKey<Biome> FROZEN_MARSH = id("frozen_marsh");
	ResourceKey<Biome> FROZEN_SHRUBLAND = id("frozen_shrubland");
	ResourceKey<Biome> FROZEN_SPIKES = id("frozen_spikes");
	ResourceKey<Biome> HALLOWED_TUNDRA = id("hallowed_tundra");
	ResourceKey<Biome> PRISMARINE_FOREST = id("prismarine_forest");
	ResourceKey<Biome> PRISMATIC_DUNES = id("prismatic_dunes");
	ResourceKey<Biome> THE_HALLOW = id("the_hallow");
	ResourceKey<Biome> THE_NEST = id("the_nest");
	ResourceKey<Biome> WARPED_MARSH = id("warped_marsh");

	static ResourceKey<Biome> id(String id) {
		return ResourceKey.create(Registries.BIOME, Stellarity.id(id));
	}
}
