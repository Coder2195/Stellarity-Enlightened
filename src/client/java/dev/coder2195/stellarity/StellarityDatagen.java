package dev.coder2195.stellarity;

import com.klikli_dev.modonomicon.api.datagen.FabricBookProvider;
import com.klikli_dev.modonomicon.api.datagen.LanguageProviderCache;
import com.klikli_dev.modonomicon.api.datagen.research.ResearchCache;
import dev.coder2195.stellarity.datagen.*;
import dev.coder2195.stellarity.datagen.book.EndonomiconBookProvider;
import dev.coder2195.stellarity.datagen.loot_table.*;
import dev.coder2195.stellarity.datagen.tags.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;

public class StellarityDatagen implements DataGeneratorEntrypoint {
	@Override
	public void buildRegistry(RegistrySetBuilder builder) {
		DynamicRegistriesProvider.buildRegistry(builder);
	}


	@SuppressWarnings("DuplicatedCode")
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();
		
		pack.addProvider(DynamicRegistriesProvider::new);
		pack.addProvider(ModelProvider::new);
		pack.addProvider(AdvancementProvider::new);
		pack.addProvider(ItemTagProvider::new);
		pack.addProvider(RecipeProvider::new);
		pack.addProvider(BlockTagProvider::new);
		pack.addProvider(DamageTypeTagProvider::new);
		pack.addProvider(EntityTypeTagProvider::new);
		pack.addProvider(BiomeTagProvider::new);
		pack.addProvider(StructureTagProvider::new);
		pack.addProvider(VillagerTradeTagProvider::new);
		pack.addProvider(VillagerProfessionTagProvider::new);
		pack.addProvider(ChestLootTableProvider::new);
		pack.addProvider(EquipmentAssetProvider::new);
		pack.addProvider(FishingLootTableProvider::new);
		pack.addProvider(BlockLootTableProvider::new);
		pack.addProvider(EntityLootTableProvider::new);
	}


	@Override
	public String getEffectiveModId() {
		return "stellarity";
	}
}
