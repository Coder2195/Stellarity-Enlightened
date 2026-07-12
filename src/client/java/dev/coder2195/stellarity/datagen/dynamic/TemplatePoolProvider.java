package dev.coder2195.stellarity.datagen.dynamic;

import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.key.StellarityProcessorLists;
import dev.coder2195.stellarity.util.tuple.Tuple2;

import java.util.Optional;
import java.util.stream.Stream;

import static dev.coder2195.stellarity.key.StellarityTemplatePools.CAMPSITE;

public interface TemplatePoolProvider {
	static void bootstrap(BootstrapContext<StructureTemplatePool> context) {
		final var templatePools = context.lookup(Registries.TEMPLATE_POOL);
		final var processorLists = context.lookup(Registries.PROCESSOR_LIST);
		final var EMPTY = templatePools.getOrThrow(Pools.EMPTY);

		var campsiteProcessor = processorLists.getOrThrow(StellarityProcessorLists.CAMPSITE);
		context.register(CAMPSITE, new StructureTemplatePool(EMPTY, Stream.of(
			new Tuple2<>("campsite/1", 3), new Tuple2<>("campsite/2", 2)
		).map(tuple ->
			new Pair<>((StructurePoolElement) new SinglePoolElement(
				Either.left(Stellarity.id(tuple._1())), campsiteProcessor, StructureTemplatePool.Projection.RIGID, Optional.empty()
			), tuple._2())).toList()
		));
	}
}
