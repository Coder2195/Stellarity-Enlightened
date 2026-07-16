package dev.coder2195.stellarity.datagen;

import dev.coder2195.stellarity.registry.*;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static dev.coder2195.stellarity.registry.StellarityRegistries.VOIDED_SKELETON_VARIANT;
import static net.minecraft.core.registries.Registries.*;

public class DynamicRegistriesProvider extends FabricDynamicRegistryProvider {
	public DynamicRegistriesProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@SuppressWarnings("DuplicatedCode")
	public static void buildRegistry(RegistrySetBuilder builder) {
		builder.add(DAMAGE_TYPE, StellarityDamageTypes::boostrap);
		builder.add(JUKEBOX_SONG, StellarityJukeboxSongs::bootstrap);
		builder.add(NOISE, StellarityNoises::bootstrap);
		builder.add(PAINTING_VARIANT, StellarityPaintings::bootstrap);
		builder.add(CHICKEN_VARIANT, StellarityMobVariants::bootstrapChicken);
		builder.add(CAT_VARIANT, StellarityMobVariants::bootstrapCat);
		builder.add(WOLF_VARIANT, StellarityMobVariants::bootstrapWolf);
		builder.add(COW_VARIANT, StellarityMobVariants::bootstrapCow);
		builder.add(FROG_VARIANT, StellarityMobVariants::bootstrapFrog);
		builder.add(PIG_VARIANT, StellarityMobVariants::bootstrapPig);
		builder.add(VOIDED_SKELETON_VARIANT, StellarityMobVariants::bootstrapVoidedSkeleton);
		builder.add(CONFIGURED_CARVER, StellarityConfiguredCarvers::bootstrap);
		builder.add(BIOME, StellarityBiomes::bootstrap);
		builder.add(PLACED_FEATURE, StellarityPlacedFeatures::bootstrapEarly);
		builder.add(CONFIGURED_FEATURE, StellarityConfiguredFeatures::bootstrap);
		builder.add(PLACED_FEATURE, StellarityPlacedFeatures::bootstrap);
		builder.add(PROCESSOR_LIST, StellarityProcessorLists::bootstrap);
		builder.add(TEMPLATE_POOL, StellarityTemplatePools::bootstrap);
		builder.add(ENCHANTMENT, StellarityEnchantments::bootstrap);
		builder.add(STRUCTURE, StellarityStructures::bootstrap);
		builder.add(STRUCTURE_SET, StellarityStructureSets::bootstrap);
		builder.add(VILLAGER_TRADE, StellarityVillagerTrades::bootstrap);
		builder.add(TRADE_SET, StellarityVillagerTradeSets::bootstrap);
	}

	@Override
	public void configure(HolderLookup.@NonNull Provider provider, @NonNull Entries entries) {
		for (var registry : List.of(JUKEBOX_SONG, NOISE, PAINTING_VARIANT, COW_VARIANT, FROG_VARIANT, CAT_VARIANT, WOLF_VARIANT, PIG_VARIANT, CHICKEN_VARIANT, VOIDED_SKELETON_VARIANT, CONFIGURED_CARVER, CONFIGURED_FEATURE, PLACED_FEATURE, BIOME, PROCESSOR_LIST, TEMPLATE_POOL, ENCHANTMENT, STRUCTURE, STRUCTURE_SET, VILLAGER_TRADE, TRADE_SET, DAMAGE_TYPE)) {
			entries.addAll(provider.lookupOrThrow(registry));
		}
	}

	@Override
	public @NonNull String getName() {
		return "All Dynamic Registries Provider";
	}
}
