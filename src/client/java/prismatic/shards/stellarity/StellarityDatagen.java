package prismatic.shards.stellarity;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.datagen.*;
import prismatic.shards.stellarity.datagen.loot_table.BlockLootTableProvider;
import prismatic.shards.stellarity.datagen.loot_table.ChestLootTableProvider;
import prismatic.shards.stellarity.datagen.loot_table.EntityLootTableProvider;
import prismatic.shards.stellarity.datagen.loot_table.FishingLootTableProvider;
import prismatic.shards.stellarity.datagen.tags.*;

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
		pack.addProvider(VillageTradeTagProvider::new);
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
