package dev.coder2195.stellarity.datagen.dynamic;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

import java.util.List;

import static net.minecraft.world.level.block.Blocks.*;
import static dev.coder2195.stellarity.key.StellarityProcessorLists.*;
import static dev.coder2195.stellarity.util.ValueUtil.from;

public interface ProcessorListProvider {
	static void bootstrap(BootstrapContext<StructureProcessorList> context) {
		context.register(CAMPSITE, new StructureProcessorList(List.of(
			new RuleProcessor(List.of(
				new ProcessorRule(new RandomBlockMatchTest(END_STONE_BRICKS, 0.076f), AlwaysTrueTest.INSTANCE, from(END_STONE))
			))
		)));
	}
}
