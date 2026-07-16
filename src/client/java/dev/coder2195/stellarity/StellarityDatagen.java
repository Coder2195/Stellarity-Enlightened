package dev.coder2195.stellarity;

import dev.coder2195.stellarity.datagen.*;
import dev.coder2195.stellarity.datagen.tags.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;
import dev.coder2195.stellarity.datagen.loot_table.BlockLootTableProvider;
import dev.coder2195.stellarity.datagen.loot_table.ChestLootTableProvider;
import dev.coder2195.stellarity.datagen.loot_table.EntityLootTableProvider;
import dev.coder2195.stellarity.datagen.loot_table.FishingLootTableProvider;

public class StellarityDatagen implements DataGeneratorEntrypoint {
	@Override
	public void buildRegistry(@NonNull RegistrySetBuilder builder) {
		DynamicRegistriesProvider.buildRegistry(builder);
	}


	@SuppressWarnings("DuplicatedCode")
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();

		pack.addProvider(ModelProvider::new);
		pack.addProvider(AdvancementProvider::new);
		pack.addProvider(ItemTagProvider::new);
		pack.addProvider(RecipeProvider::new);
		pack.addProvider(BlockLootTableProvider::new);
		pack.addProvider(BlockTagProvider::new);
		pack.addProvider(FishingLootTableProvider::new);
		pack.addProvider(EntityLootTableProvider::new);
		pack.addProvider(DamageTypeTagProvider::new);
		pack.addProvider(EntityTypeTagProvider::new);
		pack.addProvider(BiomeTagProvider::new);
		pack.addProvider(ChestLootTableProvider::new);
		pack.addProvider(DynamicRegistriesProvider::new);
		pack.addProvider((FabricDataGenerator.Pack.Factory<EquipmentAssetProvider>) EquipmentAssetProvider::new);
		pack.addProvider(StructureTagProvider::new);
		pack.addProvider(VillagerTradeTagProvider::new);
		pack.addProvider(VillagerProfessionTagProvider::new);
		// FIXME: once released
//		if (Stellarity.hasModonomicon()) {
//			var englishCache = new LanguageProviderCache("en_us");
//			pack.addProvider(FabricBookProvider.of(new EndonomiconBookProvider(englishCache)));
//		}
	}

	@Override
	public @Nullable String getEffectiveModId() {
		return "stellarity";
	}
}
