package dev.coder2195.stellarity.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import dev.coder2195.stellarity.Stellarity;

import java.util.List;

import static dev.coder2195.stellarity.util.ValueUtil.from;
import static net.minecraft.world.level.block.Blocks.*;

public interface StellarityProcessorLists {
	ResourceKey<StructureProcessorList> CAMPSITE = id("campsite");
	ResourceKey<StructureProcessorList> VILLAGE_FLOWERING_AZALEA_LEAVES = id("village/flowering_azalea_leaves");

	static void bootstrap(BootstrapContext<StructureProcessorList> context) {
		context.register(CAMPSITE, new StructureProcessorList(List.of(
			new RuleProcessor(List.of(
				new ProcessorRule(new RandomBlockMatchTest(END_STONE_BRICKS, 0.076f), AlwaysTrueTest.INSTANCE, from(END_STONE))
			))
		)));

		context.register(VILLAGE_FLOWERING_AZALEA_LEAVES, new StructureProcessorList(List.of(
			new RuleProcessor(List.of(
				new ProcessorRule(new RandomBlockMatchTest(AZALEA_LEAVES, 0.3f), AlwaysTrueTest.INSTANCE, AZALEA_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true))
			))
		)));
	}

	private static ResourceKey<StructureProcessorList> id(String id) {
		return Stellarity.key(Registries.PROCESSOR_LIST, id);
	}
}
