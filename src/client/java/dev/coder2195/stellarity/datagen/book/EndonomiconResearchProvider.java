package dev.coder2195.stellarity.datagen.book;

import com.klikli_dev.modonomicon.api.datagen.research.ResearchProvider;
import com.klikli_dev.modonomicon.api.datagen.research.ResearchSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class EndonomiconResearchProvider extends ResearchProvider {
	public EndonomiconResearchProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries, String modId, List<ResearchSubProvider> subProviders) {
		super(packOutput, registries, modId, subProviders);
	}
}
