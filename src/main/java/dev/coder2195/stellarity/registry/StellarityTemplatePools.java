package dev.coder2195.stellarity.registry;

import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.util.tuple.Tuple2;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;

import java.util.Optional;
import java.util.stream.Stream;

public interface StellarityTemplatePools {
	ResourceKey<StructureTemplatePool> CAMPSITE = id("campsite");
	ResourceKey<StructureTemplatePool> VILLAGE_ANIMALS = id("village/animals");
	ResourceKey<StructureTemplatePool> VILLAGE_BEES = id("village/bees");
	ResourceKey<StructureTemplatePool> VILLAGE_DECORATIONS = id("village/decorations");
	ResourceKey<StructureTemplatePool> VILLAGE_DECORATIONS_ROAD = id("village/decorations_road");
	ResourceKey<StructureTemplatePool> VILLAGE_GOLEMS = id("village/golems");
	ResourceKey<StructureTemplatePool> VILLAGE_LARGE_BUILDINGS = id("village/large_buildings");
	ResourceKey<StructureTemplatePool> VILLAGE_LAYOUTS = id("village/layouts");
	ResourceKey<StructureTemplatePool> VILLAGE_RESPAWN_ANCHORS = id("village/respawn_anchors");
	ResourceKey<StructureTemplatePool> VILLAGE_SHEEP = id("village/sheep");
	ResourceKey<StructureTemplatePool> VILLAGE_SMALL_BUILDINGS = id("village/small_buildings");
	ResourceKey<StructureTemplatePool> VILLAGE_TOWN_CENTERS = id("village/town_centers");
	ResourceKey<StructureTemplatePool> VILLAGE_VILLAGERS_JOBLESS = id("village/villagers_jobless");

	static void bootstrap(BootstrapContext<StructureTemplatePool> context) {
		final var templatePools = context.lookup(Registries.TEMPLATE_POOL);
		final var processorLists = context.lookup(Registries.PROCESSOR_LIST);
		final var EMPTY = templatePools.getOrThrow(Pools.EMPTY);

		var campsiteProcessor = processorLists.getOrThrow(StellarityProcessorLists.CAMPSITE);
		context.register(CAMPSITE, new StructureTemplatePool(EMPTY, Stream.of(
			new Tuple2<>("1", 3), new Tuple2<>("2", 2)
		).map(tuple -> new Pair<StructurePoolElement, Integer>(new SinglePoolElement(
			Either.left(Stellarity.id("campsite/" + tuple._1())), campsiteProcessor, StructureTemplatePool.Projection.RIGID, Optional.empty()
		), tuple._2())).toList()));

		context.register(VILLAGE_ANIMALS, new StructureTemplatePool(EMPTY, Stream.of(
			new Tuple2<>("cows", 6), new Tuple2<>("pigs", 6), new Tuple2<>("sheep", 4), new Tuple2<>("mooshrooms", 1)
		).map(tuple -> new Pair<StructurePoolElement, Integer>(new SinglePoolElement(
			Either.left(Stellarity.id("village/entities/" + tuple._1())), campsiteProcessor, StructureTemplatePool.Projection.RIGID, Optional.of(LiquidSettings.IGNORE_WATERLOGGING)
		), tuple._2())).toList()));


	}

	private static ResourceKey<StructureTemplatePool> id(String id) {
		return Stellarity.key(Registries.TEMPLATE_POOL, id);
	}
}
