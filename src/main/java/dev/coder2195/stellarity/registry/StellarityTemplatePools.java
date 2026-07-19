package dev.coder2195.stellarity.registry;

import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.util.tuple.Tuple2;
import dev.coder2195.stellarity.util.tuple.Tuple3;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.data.worldgen.placement.VillagePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.FeaturePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;

import java.util.List;
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
		final var placedFeatures = context.lookup(Registries.PLACED_FEATURE);

		var campsiteProcessor = processorLists.getOrThrow(StellarityProcessorLists.CAMPSITE);
		var emptyProcessor = processorLists.getOrThrow(ProcessorLists.EMPTY);
		context.register(CAMPSITE, new StructureTemplatePool(EMPTY, Stream.of(
			new Tuple2<>("1", 3), new Tuple2<>("2", 2)
		).map(tuple -> new Pair<StructurePoolElement, Integer>(new SinglePoolElement(
			Either.left(Stellarity.id("campsite/" + tuple._1())), campsiteProcessor, StructureTemplatePool.Projection.RIGID, Optional.empty()
		), tuple._2())).toList()));

		context.register(VILLAGE_ANIMALS, new StructureTemplatePool(EMPTY, Stream.of(
			new Tuple2<>("cows", 6), new Tuple2<>("pigs", 6), new Tuple2<>("sheep", 4), new Tuple2<>("mooshrooms", 1)
		).map(tuple -> new Pair<StructurePoolElement, Integer>(new SinglePoolElement(
			Either.left(Stellarity.id("village/entities/" + tuple._1())), emptyProcessor, StructureTemplatePool.Projection.RIGID, Optional.of(LiquidSettings.IGNORE_WATERLOGGING)
		), tuple._2())).toList()));


		var villageFloweringAzaleaLeavesProcessor = processorLists.getOrThrow(StellarityProcessorLists.VILLAGE_FLOWERING_AZALEA_LEAVES);
		context.register(VILLAGE_BEES, new StructureTemplatePool(EMPTY, Stream.of(
			new Tuple2<>("1_bee", 5), new Tuple2<>("2_bees", 3), new Tuple2<>("3_bees", 1)
		).map(tuple -> new Pair<StructurePoolElement, Integer>(new SinglePoolElement(
			Either.left(Stellarity.id("village/entities/" + tuple._1())), emptyProcessor, StructureTemplatePool.Projection.RIGID, Optional.of(LiquidSettings.IGNORE_WATERLOGGING)
		), tuple._2())).toList()));

		context.register(VILLAGE_DECORATIONS, new StructureTemplatePool(EMPTY, Stream.concat(
			Stream.of(
				new Tuple2<>("lamp_1", 1), new Tuple2<>("lamp_2", 1), new Tuple2<>("lamp_3", 1), new Tuple2<>("lamp_4", 1),
				new Tuple2<>("lamp_5", 1), new Tuple2<>("lamp_6", 1), new Tuple2<>("lamp_7", 1)
			).map(tuple -> new Pair<StructurePoolElement, Integer>(new SinglePoolElement(
				Either.left(Stellarity.id("village/decorations/" + tuple._1())), emptyProcessor, StructureTemplatePool.Projection.RIGID, Optional.of(LiquidSettings.IGNORE_WATERLOGGING)
			), tuple._2())),
			Stream.of(
				new Tuple2<>(VillagePlacements.OAK_VILLAGE, 4), new Tuple2<>(VillagePlacements.PILE_HAY_VILLAGE, 3), new Tuple2<>(VillagePlacements.PILE_MELON_VILLAGE, 3), new Tuple2<>(VillagePlacements.PILE_PUMPKIN_VILLAGE, 3)
			).map(feature -> new Pair<>((StructurePoolElement) FeaturePoolElement.feature(placedFeatures.getOrThrow(feature._1())).apply(StructureTemplatePool.Projection.RIGID), feature._2()))
		).toList()));

		context.register(VILLAGE_DECORATIONS_ROAD, new StructureTemplatePool(EMPTY, Stream.of(
			new Tuple3<>("campfire_1", emptyProcessor, 1), new Tuple3<>("campfire_2", emptyProcessor, 1),
			new Tuple3<>("lamp_1", emptyProcessor, 1), new Tuple3<>("lamp_2", emptyProcessor, 1), new Tuple3<>("lamp_3", emptyProcessor, 1), new Tuple3<>("lamp_4", emptyProcessor, 1),
			new Tuple3<>("lamp_5", emptyProcessor, 1), new Tuple3<>("lamp_6", emptyProcessor, 1), new Tuple3<>("lamp_7", emptyProcessor, 1),
			new Tuple3<>("planter_box_1", villageFloweringAzaleaLeavesProcessor, 1), new Tuple3<>("planter_box_2", emptyProcessor, 1), new Tuple3<>("planter_box_3", emptyProcessor, 1),
			new Tuple3<>("planter_box_4", emptyProcessor, 1), new Tuple3<>("planter_box_5", villageFloweringAzaleaLeavesProcessor, 1),
			new Tuple3<>("planter_box_6", emptyProcessor, 1), new Tuple3<>("planter_box_7", emptyProcessor, 1)

		).map(decoration -> new Pair<StructurePoolElement, Integer>(new SinglePoolElement(
			Either.left(Stellarity.id("village/decorations/" + decoration._1())), decoration._2(), StructureTemplatePool.Projection.RIGID, Optional.of(LiquidSettings.IGNORE_WATERLOGGING)
		), decoration._3())).toList()));

		context.register(VILLAGE_GOLEMS, new StructureTemplatePool(EMPTY, List.of(new Pair<>(new SinglePoolElement(
			Either.left(Stellarity.id("village/entities/golem")), emptyProcessor, StructureTemplatePool.Projection.RIGID, Optional.of(LiquidSettings.IGNORE_WATERLOGGING)
		), 1))));

		context.register(VILLAGE_LARGE_BUILDINGS, new StructureTemplatePool(EMPTY, Stream.of(
			"butcher_big", "cartographer_big", "fletcher_big", "farm_big", "temple_big", "shepherd_big", "market_1", "market_2"
		).map(building -> new Pair<StructurePoolElement, Integer>(
			new SinglePoolElement(Either.left(Stellarity.id("village/"+building)), emptyProcessor, StructureTemplatePool.Projection.RIGID, Optional.of(LiquidSettings.IGNORE_WATERLOGGING)), 1
		)).toList()));

		context.register(VILLAGE_LAYOUTS, new StructureTemplatePool(EMPTY, List.of(new Pair<>(new SinglePoolElement(
			Either.left(Stellarity.id("village/layouts/1")), emptyProcessor, StructureTemplatePool.Projection.RIGID, Optional.of(LiquidSettings.IGNORE_WATERLOGGING)
		), 1))));

		context.register(VILLAGE_RESPAWN_ANCHORS, new StructureTemplatePool(EMPTY, Stream.of(
			new Tuple2<>("0", 1), new Tuple2<>("1", 2), new Tuple2<>("2", 3), new Tuple2<>("3", 3), new Tuple2<>("4", 1)
		).map(level -> new Pair<StructurePoolElement, Integer>(
			new SinglePoolElement(Either.left(Stellarity.id("village/respawn_anchors/"+ level._1())), emptyProcessor, StructureTemplatePool.Projection.RIGID, Optional.of(LiquidSettings.IGNORE_WATERLOGGING)), level._2()
		)).toList()));

		context.register(VILLAGE_SHEEP, new StructureTemplatePool(EMPTY, List.of(new Pair<>(new SinglePoolElement(
			Either.left(Stellarity.id("village/entities/sheep")), emptyProcessor, StructureTemplatePool.Projection.RIGID, Optional.of(LiquidSettings.IGNORE_WATERLOGGING)
		), 1))));

		context.register(VILLAGE_SMALL_BUILDINGS, new StructureTemplatePool(EMPTY, Stream.of(
			new Tuple2<>("animal_pen_1", 1), new Tuple2<>("animal_pen_2", 1),
			new Tuple2<>("archer_tower_1", 1), new Tuple2<>("archer_tower_2", 1), new Tuple2<>("archer_tower_3", 1),
			new Tuple2<>("armorer_1", 3), new Tuple2<>("butcher_1", 3), new Tuple2<>("cartographer_1", 3),
			new Tuple2<>("farm_1", 3), new Tuple2<>("farm_2", 1), new Tuple2<>("farm_3", 1), new Tuple2<>("farm_4", 1), new Tuple2<>("farm_5", 1),
			new Tuple2<>("fisherman_1", 3), new Tuple2<>("garden_1", 1), new Tuple2<>("library_1", 3), new Tuple2<>("mason_1", 3), new Tuple2<>("shepherd_1", 3),
			new Tuple2<>("small_house_1", 4), new Tuple2<>("small_house_2", 4), new Tuple2<>("small_house_3", 4), new Tuple2<>("small_house_4", 4), new Tuple2<>("small_house_5", 4),
			new Tuple2<>("tannery_1", 3), new Tuple2<>("temple_1", 3), new Tuple2<>("toolsmith_1", 3), new Tuple2<>("weaponsmith_1", 3)
		).map(level -> new Pair<StructurePoolElement, Integer>(
			new SinglePoolElement(Either.left(Stellarity.id("village/"+ level._1())), emptyProcessor, StructureTemplatePool.Projection.RIGID, Optional.of(LiquidSettings.IGNORE_WATERLOGGING)), level._2()
		)).toList()));

		context.register(VILLAGE_TOWN_CENTERS, new StructureTemplatePool(EMPTY, Stream.of(
			"town_center_1", "town_center_2", "town_center_3"
		).map(building -> new Pair<StructurePoolElement, Integer>(
			new SinglePoolElement(Either.left(Stellarity.id("village/"+building)), emptyProcessor, StructureTemplatePool.Projection.RIGID, Optional.of(LiquidSettings.IGNORE_WATERLOGGING)), 1
		)).toList()));

		context.register(VILLAGE_VILLAGERS_JOBLESS, new StructureTemplatePool(EMPTY, Stream.of(
			new Tuple2<>("1_villager", 2), new Tuple2<>("2_villagers", 3), new Tuple2<>("3_villagers", 1)
		).map(level -> new Pair<StructurePoolElement, Integer>(
			new SinglePoolElement(Either.left(Stellarity.id("village/entities/"+ level._1())), emptyProcessor, StructureTemplatePool.Projection.RIGID, Optional.of(LiquidSettings.IGNORE_WATERLOGGING)), level._2()
		)).toList()));
		
		
	}

	private static ResourceKey<StructureTemplatePool> id(String id) {
		return Stellarity.key(Registries.TEMPLATE_POOL, id);
	}
}
