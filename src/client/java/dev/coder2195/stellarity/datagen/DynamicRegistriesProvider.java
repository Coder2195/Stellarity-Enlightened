package dev.coder2195.stellarity.datagen;

import dev.coder2195.stellarity.datagen.dynamic.*;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import org.jspecify.annotations.NonNull;
import dev.coder2195.stellarity.datagen.dynamic.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.core.registries.Registries.*;
import static dev.coder2195.stellarity.registry.StellarityRegistries.VOIDED_SKELETON_VARIANT;

public class DynamicRegistriesProvider extends FabricDynamicRegistryProvider {
	public DynamicRegistriesProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@SuppressWarnings("DuplicatedCode")
	public static void buildRegistry(RegistrySetBuilder builder) {
		builder.add(DAMAGE_TYPE, DamageTypeProvider::boostrap);
		builder.add(JUKEBOX_SONG, JukeboxSongProvider::bootstrap);
		builder.add(NOISE, NoiseProvider::bootstrap);
		builder.add(PAINTING_VARIANT, PaintingProvider::bootstrap);
		builder.add(CHICKEN_VARIANT, MobVariantProvider::bootstrapChicken);
		builder.add(CAT_VARIANT, MobVariantProvider::bootstrapCat);
		builder.add(WOLF_VARIANT, MobVariantProvider::bootstrapWolf);
		builder.add(COW_VARIANT, MobVariantProvider::bootstrapCow);
		builder.add(FROG_VARIANT, MobVariantProvider::bootstrapFrog);
		builder.add(PIG_VARIANT, MobVariantProvider::bootstrapPig);
		builder.add(VOIDED_SKELETON_VARIANT, MobVariantProvider::bootstrapVoidedSkeleton);
		builder.add(CONFIGURED_CARVER, ConfiguredCarverProvider::bootstrap);
		builder.add(BIOME, BiomeProvider::bootstrap);
		builder.add(PLACED_FEATURE, PlacedFeatureProvider::bootstrapEarly);
		builder.add(CONFIGURED_FEATURE, ConfiguredFeatureProvider::bootstrap);
		builder.add(PLACED_FEATURE, PlacedFeatureProvider::bootstrap);
		builder.add(PROCESSOR_LIST, ProcessorListProvider::bootstrap);
		builder.add(TEMPLATE_POOL, TemplatePoolProvider::bootstrap);
		builder.add(ENCHANTMENT, EnchantmentProvider::bootstrap);
		builder.add(STRUCTURE, StructureProvider::bootstrap);
		builder.add(STRUCTURE_SET, StructureSetProvider::bootstrap);
		builder.add(VILLAGER_TRADE, VillagerTradeProvider::bootstrap);
		builder.add(TRADE_SET, TradeSetProvider::bootstrap);
	}

	@Override
	public void configure(HolderLookup.@NonNull Provider provider, @NonNull Entries entries) {
		for (var registry : List.of(JUKEBOX_SONG, NOISE, PAINTING_VARIANT, COW_VARIANT, FROG_VARIANT, CAT_VARIANT, WOLF_VARIANT, PIG_VARIANT, CHICKEN_VARIANT, VOIDED_SKELETON_VARIANT, CONFIGURED_CARVER, CONFIGURED_FEATURE, PLACED_FEATURE, BIOME, VILLAGER_TRADE, PROCESSOR_LIST, TEMPLATE_POOL, ENCHANTMENT, STRUCTURE, STRUCTURE_SET, TRADE_SET, DAMAGE_TYPE)) {
			entries.addAll(provider.lookupOrThrow(registry));
		}
	}

	@Override
	public @NonNull String getName() {
		return "All Dynamic Registries Provider";
	}
}
