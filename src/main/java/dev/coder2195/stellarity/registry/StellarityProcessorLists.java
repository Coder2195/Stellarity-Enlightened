package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.rule.blockentity.AppendLoot;

import java.util.List;

import static dev.coder2195.stellarity.registry.StellarityBlocks.COARSE_ENDER_DIRT;
import static dev.coder2195.stellarity.registry.StellarityBlocks.ENDER_DIRT;
import static dev.coder2195.stellarity.util.ValueUtil.from;
import static dev.coder2195.stellarity.util.ValueUtil.numRaw;
import static net.minecraft.world.level.block.Blocks.*;

public interface StellarityProcessorLists {
	ResourceKey<StructureProcessorList> CAMPSITE = id("campsite");
	ResourceKey<StructureProcessorList> END_VILLAGE_FLOWERING_AZALEA_LEAVES = id("end_village_flowering_azalea_leaves");
	ResourceKey<StructureProcessorList> END_SHIPWRECK_DUNES = id("end_shipwreck_dunes");
	ResourceKey<StructureProcessorList> END_SHIPWRECK = id("end_shipwreck");

	static void bootstrap(BootstrapContext<StructureProcessorList> context) {

		context.register(CAMPSITE, new StructureProcessorList(List.of(
			new RuleProcessor(List.of(
				new ProcessorRule(new RandomBlockMatchTest(END_STONE_BRICKS, 0.076f), AlwaysTrueTest.INSTANCE, from(END_STONE))
			))
		)));

		context.register(END_VILLAGE_FLOWERING_AZALEA_LEAVES, new StructureProcessorList(List.of(
			new RuleProcessor(List.of(
				new ProcessorRule(new RandomBlockMatchTest(AZALEA_LEAVES, 0.3f), AlwaysTrueTest.INSTANCE, from(AZALEA_LEAVES).setValue(LeavesBlock.PERSISTENT, true))
			))
		)));


		context.register(END_SHIPWRECK_DUNES, new StructureProcessorList(List.of(
			new RuleProcessor(List.of(
				new ProcessorRule(new RandomBlockMatchTest(ENDER_DIRT, 0.1f), AlwaysTrueTest.INSTANCE, from(END_STONE)),
				new ProcessorRule(new RandomBlockMatchTest(ENDER_DIRT, 0.25f), AlwaysTrueTest.INSTANCE, from(COARSE_ENDER_DIRT)),
				new ProcessorRule(new RandomBlockMatchTest(ENDER_DIRT, 0.35f), AlwaysTrueTest.INSTANCE, from(GRAVEL)),
				new ProcessorRule(new BlockMatchTest(ENDER_DIRT), AlwaysTrueTest.INSTANCE, from(SAND))
			)),
			new CappedProcessor(new RuleProcessor(List.of(
				new ProcessorRule(new BlockMatchTest(SAND), AlwaysTrueTest.INSTANCE, PosAlwaysTrueTest.INSTANCE, from(SUSPICIOUS_SAND), new AppendLoot(StellarityLootTables.END_SHIPWRECK_ARCHAEOLOGY_DUNES_RARE))
			)), numRaw(6, 12)),
			new CappedProcessor(new RuleProcessor(List.of(
				new ProcessorRule(new BlockMatchTest(SAND), AlwaysTrueTest.INSTANCE, PosAlwaysTrueTest.INSTANCE, from(SUSPICIOUS_SAND), new AppendLoot(StellarityLootTables.END_SHIPWRECK_ARCHAEOLOGY_DUNES_COMMON))
			)), numRaw(10, 18)),

			new CappedProcessor(new RuleProcessor(List.of(
				new ProcessorRule(new BlockMatchTest(GRAVEL), AlwaysTrueTest.INSTANCE, PosAlwaysTrueTest.INSTANCE, from(SUSPICIOUS_GRAVEL), new AppendLoot(StellarityLootTables.END_SHIPWRECK_ARCHAEOLOGY_DUNES_RARE))
			)), numRaw(6, 12)),
			new CappedProcessor(new RuleProcessor(List.of(
				new ProcessorRule(new BlockMatchTest(GRAVEL), AlwaysTrueTest.INSTANCE, PosAlwaysTrueTest.INSTANCE, from(SUSPICIOUS_GRAVEL), new AppendLoot(StellarityLootTables.END_SHIPWRECK_ARCHAEOLOGY_DUNES_COMMON))
			)), numRaw(10, 18)),
			new BlockIgnoreProcessor(List.of(STRUCTURE_VOID))
		)));


		context.register(END_SHIPWRECK, new StructureProcessorList(List.of(
			new RuleProcessor(List.of(
				new ProcessorRule(new RandomBlockMatchTest(ENDER_DIRT, 0.25f), AlwaysTrueTest.INSTANCE, from(END_STONE)),
				new ProcessorRule(new RandomBlockMatchTest(ENDER_DIRT, 0.3f), AlwaysTrueTest.INSTANCE, from(COARSE_ENDER_DIRT)),
				new ProcessorRule(new RandomBlockMatchTest(ENDER_DIRT, 0.15f), AlwaysTrueTest.INSTANCE, from(GRAVEL)),
				new ProcessorRule(new BlockMatchTest(ENDER_DIRT), AlwaysTrueTest.INSTANCE, from(SAND))
			)),
			new CappedProcessor(new RuleProcessor(List.of(
				new ProcessorRule(new BlockMatchTest(SAND), AlwaysTrueTest.INSTANCE, PosAlwaysTrueTest.INSTANCE, from(SUSPICIOUS_SAND), new AppendLoot(StellarityLootTables.END_SHIPWRECK_ARCHAEOLOGY_RARE))
			)), numRaw(5, 10)),
			new CappedProcessor(new RuleProcessor(List.of(
				new ProcessorRule(new BlockMatchTest(SAND), AlwaysTrueTest.INSTANCE, PosAlwaysTrueTest.INSTANCE, from(SUSPICIOUS_SAND), new AppendLoot(StellarityLootTables.END_SHIPWRECK_ARCHAEOLOGY_COMMON))
			)), numRaw(10, 18)),

			new CappedProcessor(new RuleProcessor(List.of(
				new ProcessorRule(new BlockMatchTest(GRAVEL), AlwaysTrueTest.INSTANCE, PosAlwaysTrueTest.INSTANCE, from(SUSPICIOUS_GRAVEL), new AppendLoot(StellarityLootTables.END_SHIPWRECK_ARCHAEOLOGY_RARE))
			)), numRaw(5, 10)),
			new CappedProcessor(new RuleProcessor(List.of(
				new ProcessorRule(new BlockMatchTest(GRAVEL), AlwaysTrueTest.INSTANCE, PosAlwaysTrueTest.INSTANCE, from(SUSPICIOUS_GRAVEL), new AppendLoot(StellarityLootTables.END_SHIPWRECK_ARCHAEOLOGY_COMMON))
			)), numRaw(10, 18)),
			new BlockIgnoreProcessor(List.of(STRUCTURE_VOID))
		)));
	}

	private static ResourceKey<StructureProcessorList> id(String id) {
		return Stellarity.key(Registries.PROCESSOR_LIST, id);
	}
}
