package dev.coder2195.stellarity.datagen.tags;

import dev.coder2195.stellarity.registry.StellarityStructures;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

import static dev.coder2195.stellarity.tags.StellarityStructureTags.EXPLORATION_MAP_END_CITY;
import static dev.coder2195.stellarity.tags.StellarityStructureTags.EXPLORATION_MAP_VILLAGE;
import static net.minecraft.world.level.levelgen.structure.BuiltinStructures.END_CITY;

public class StructureTagProvider extends FabricTagsProvider<Structure> {

	public StructureTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, Registries.STRUCTURE, registriesFuture);
	}

	@Override
	protected void addTags(HolderLookup.@NonNull Provider provider) {
		builder(EXPLORATION_MAP_END_CITY).add(END_CITY);
		builder(EXPLORATION_MAP_VILLAGE).add(StellarityStructures.VILLAGE);
	}
}
