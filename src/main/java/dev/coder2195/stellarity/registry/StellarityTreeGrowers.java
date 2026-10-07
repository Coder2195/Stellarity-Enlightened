package dev.coder2195.stellarity.registry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.stream.Stream;

public interface StellarityTreeGrowers {
	TreeGrower AMETHYII = new TreeGrower("stellarity:amethyii", WeightedList.of(), WeightedList.of(StellarityFeatures.AMETHYII_TREE), WeightedList.of(), StellarityFeatures.AMETHYII_TREE);

	@SuppressWarnings("unchecked")
	TreeGrower HALLOWED = new TreeGrower("stellarity:hallowed", WeightedList.of(Stream.concat(Stream.concat(
		StellarityFeatures.COLORED_REGULAR_HALLOWED_TREE.asList().stream(), StellarityFeatures.COLORED_PINE_HALLOWED_TREE.asList().stream()
	), Stream.of(StellarityFeatures.PINE_HALLOWED_TREE, StellarityFeatures.REGULAR_HALLOWED_TREE)).toArray(ResourceKey[]::new)), WeightedList.of(StellarityFeatures.COLORED_MEGA_HALLOWED_TREE.asList().toArray(ResourceKey[]::new)), WeightedList.of(), StellarityFeatures.REGULAR_HALLOWED_TREE);

	TreeGrower PRISMATIC = new TreeGrower("stellarity:prismatic", WeightedList.of(StellarityFeatures.PRISMATIC_TREE), WeightedList.of(), WeightedList.of(), StellarityFeatures.PRISMATIC_TREE);

	TreeGrower ASHEN = new TreeGrower("stellarity:ashen", WeightedList.of(StellarityFeatures.ASHEN_TREE), WeightedList.of(), WeightedList.of(), StellarityFeatures.ASHEN_TREE);
	// todo: seperate out shrubbed trees as its a list rn
	TreeGrower SHRUBBED = new TreeGrower("stellarity:shrubbed", WeightedList.of(StellarityFeatures.SHRUBBED_TREE), WeightedList.of(), WeightedList.of(), StellarityFeatures.SHRUBBED_TREE);

	TreeGrower INFERNO = new TreeGrower("stellarity:inferno", WeightedList.of(StellarityFeatures.INFERNO_TREE), WeightedList.of(), WeightedList.of(), StellarityFeatures.INFERNO_TREE);
}
