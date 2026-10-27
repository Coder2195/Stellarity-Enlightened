package dev.coder2195.stellarity.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TimelineTags;
import net.minecraft.world.timeline.Timeline;

import java.util.concurrent.CompletableFuture;

public class TimelineTagProvider extends FabricTagsProvider<Timeline> {
	public TimelineTagProvider(FabricPackOutput output,  CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
		super(output, Registries.TIMELINE, registryLookupFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider registries) {
		builder(TimelineTags.IN_END);
	}
}
