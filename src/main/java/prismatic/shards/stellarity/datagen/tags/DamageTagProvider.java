package prismatic.shards.stellarity.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.key.StellarityDamageTypes;
import prismatic.shards.stellarity.tags.StellarityDamageTypeTags;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unchecked")
public class DamageTagProvider extends FabricTagsProvider<DamageType> {
	public DamageTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, Registries.DAMAGE_TYPE, registriesFuture);
	}


	@Override
	protected void addTags(HolderLookup.@NonNull Provider provider) {
		builder(DamageTypeTags.BYPASSES_COOLDOWN).add(StellarityDamageTypes.BRITTLE);
		builder(StellarityDamageTypeTags.MELEE).add(DamageTypes.PLAYER_ATTACK, DamageTypes.MOB_ATTACK, DamageTypes.MOB_ATTACK);
		builder(DamageTypeTags.NO_KNOCKBACK).add(StellarityDamageTypes.BRITTLE);
	}
}
