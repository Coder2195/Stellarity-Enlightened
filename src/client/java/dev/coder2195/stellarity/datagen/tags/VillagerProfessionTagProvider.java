package dev.coder2195.stellarity.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import org.jspecify.annotations.NonNull;

import static dev.coder2195.stellarity.registry.StellarityVillagerProfessions.*;

import java.util.concurrent.CompletableFuture;


import static dev.coder2195.stellarity.tags.StellarityVillagerProfessionTags.*;

public class VillagerProfessionTagProvider extends FabricTagsProvider<VillagerProfession> {
	public VillagerProfessionTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
		super(output, Registries.VILLAGER_PROFESSION, registryLookupFuture);
	}

	@SafeVarargs
	private TagBuilder addTags(TagKey<VillagerProfession> tag, Holder.Reference<VillagerProfession>... trades) {
		var builder = getOrCreateRawBuilder(tag);
		for (var trade : trades) {
			builder.addElement(trade.key().identifier());
		}
		return builder;
	}

	@Override
	protected void addTags(HolderLookup.@NonNull Provider provider) {
		addTags(ALL, ARMORER, BUTCHER, CARTOGRAPHER, CLERIC, FARMER, FISHERMAN, FLETCHER, LEATHERWORKER, LIBRARIAN, MASON, SHEPHERD, TOOLSMITH, WEAPONSMITH);

		addTags(END_PROFESSION).addTag(ALL.location());
	}
}
