package prismatic.shards.stellarity.datagen.dynamic;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.world.level.levelgen.structure.pools.EmptyPoolElement;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import prismatic.shards.stellarity.Stellarity;

import java.util.List;

import static prismatic.shards.stellarity.key.StellarityTemplatePools.*;

public interface TemplatePoolProvider {
	static void bootstrap(BootstrapContext<StructureTemplatePool> context) {
		final var templatePools = context.lookup(Registries.TEMPLATE_POOL);
		final var EMPTY = templatePools.getOrThrow(Pools.EMPTY);
//		context.register(CAMPSITE, new StructureTemplatePool(EMPTY, List.of(
//			new Pair<>(new SinglePoolElement(Stellarity.id("campsite/1"), ), 3)
//		)))
	}
}
