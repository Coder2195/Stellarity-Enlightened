package dev.coder2195.stellarity.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

import static dev.coder2195.stellarity.key.StellarityEnchantments.CRITICAL_STRIKE;

public class EnchantmentTagProvider extends FabricTagsProvider<Enchantment> {

	public EnchantmentTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
		super(output, Registries.ENCHANTMENT, registryLookupFuture);
	}

	@Override
	protected void addTags(HolderLookup.@NonNull Provider registries) {
		builder(EnchantmentTags.DAMAGE_EXCLUSIVE).add(CRITICAL_STRIKE);
	}
}
