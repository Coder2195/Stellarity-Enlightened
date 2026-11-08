package dev.coder2195.stellarity.datagen;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.*;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.CachedOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static dev.coder2195.stellarity.registry.StellarityRegistries.VOIDED_SKELETON_VARIANT;
import static net.minecraft.core.registries.Registries.*;

public class DynamicRegistriesProvider extends FabricDynamicRegistryProvider {
	public DynamicRegistriesProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	public static final HashMap<ResourceKey<?>, ResourceCondition[]> RESOURCE_CONDITION_MAP = new HashMap<>() {{
		ResourceCondition[] nullscapeCheck = {ResourceConditions.tagsPopulated(TagKey.create(BLOCK, Stellarity.id("nullscape", "amethyst_cut")))};
		put(StellarityDensityFunctions.NULLSCAPE_COMPAT_CLIMATE_DEPTH, nullscapeCheck);
		put(StellarityDensityFunctions.NULLSCAPE_COMPAT_FINAL_DENSITY, nullscapeCheck);
		put(StellarityDensityFunctions.NULLSCAPE_COMPAT_INITIAL_DENSITY, nullscapeCheck);
	}};

	@SuppressWarnings("DuplicatedCode")
	public static void buildRegistry(RegistrySetBuilder builder) {
		builder.add(TIMELINE, StellarityTimeline::bootstrap);
		builder.add(NOISE, StellarityNoises::bootstrap);
		builder.add(DENSITY_FUNCTION, StellarityDensityFunctions::bootstrap);
		builder.add(DAMAGE_TYPE, StellarityDamageTypes::bootstrap);
		builder.add(JUKEBOX_SONG, StellarityJukeboxSongs::bootstrap);
		builder.add(PAINTING_VARIANT, StellarityPaintings::bootstrap);
		builder.add(CHICKEN_VARIANT, StellarityMobVariants::bootstrapChicken);
		builder.add(CAT_VARIANT, StellarityMobVariants::bootstrapCat);
		builder.add(WOLF_VARIANT, StellarityMobVariants::bootstrapWolf);
		builder.add(COW_VARIANT, StellarityMobVariants::bootstrapCow);
		builder.add(FROG_VARIANT, StellarityMobVariants::bootstrapFrog);
		builder.add(PIG_VARIANT, StellarityMobVariants::bootstrapPig);
		builder.add(VOIDED_SKELETON_VARIANT, StellarityMobVariants::bootstrapVoidedSkeleton);
		builder.add(CARVER, StellarityConfiguredCarvers::bootstrap);
		builder.add(BIOME, StellarityBiomes::bootstrap);
		builder.add(PLACED_FEATURE, StellarityPlacedFeatures::bootstrapEarly);
		builder.add(FEATURE, StellarityFeatures::bootstrap);
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
	public CompletableFuture<?> run(CachedOutput cache) {
		return super.run(cache);
	}

	public static void buildReloableRegistry(RegistrySetBuilder builder) {
		builder.add(ITEM_MODIFIER, StellarityItemModifiers::bootstrap);
	}

	@Override
	public void configure(HolderLookup.Provider provider, Entries entries) {
		for (var registry : List.of(TIMELINE, NOISE, DENSITY_FUNCTION, ITEM_MODIFIER, JUKEBOX_SONG, PAINTING_VARIANT, COW_VARIANT, FROG_VARIANT, CAT_VARIANT, WOLF_VARIANT, PIG_VARIANT, CHICKEN_VARIANT, VOIDED_SKELETON_VARIANT, CARVER, FEATURE, PLACED_FEATURE, BIOME, PROCESSOR_LIST, TEMPLATE_POOL, ENCHANTMENT, STRUCTURE, STRUCTURE_SET, VILLAGER_TRADE, TRADE_SET, DAMAGE_TYPE)) {
			addAll(provider.lookupOrThrow(registry), entries);
		}
	}

	public <T> void addAll(HolderLookup.RegistryLookup<T> registry, Entries entries) {
		registry.listElementIds()
			.forEach(resourceKey -> {
				var conditions = RESOURCE_CONDITION_MAP.get(resourceKey);
				if (conditions != null) {
					entries.add(registry, resourceKey, conditions);
					return;
				}
				if (resourceKey.identifier().getNamespace().equals(Stellarity.MOD_ID))
					entries.add(registry, resourceKey);
			});
	}

	@Override
	public String getName() {
		return "All Dynamic Registries Provider";
	}
}
