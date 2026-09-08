package dev.coder2195.stellarity.datagen.tags;

import dev.coder2195.stellarity.registry.StellarityStructures;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.concurrent.CompletableFuture;

import static dev.coder2195.stellarity.tags.StellarityStructureTags.ON_END_CITY_MAPS;
import static dev.coder2195.stellarity.tags.StellarityStructureTags.ON_VILLAGE_MAPS;
import static net.minecraft.world.level.levelgen.structure.BuiltinStructures.END_CITY;

public class StructureTagProvider extends FabricTagsProvider<Structure> {

	public StructureTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, Registries.STRUCTURE, registriesFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		builder(ON_END_CITY_MAPS).add(END_CITY);
		builder(ON_VILLAGE_MAPS).add(StellarityStructures.END_VILLAGE);
	}
}
