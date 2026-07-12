package dev.coder2195.stellarity.datagen.tags;


import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import org.jspecify.annotations.NonNull;

import static net.minecraft.world.damagesource.DamageTypes.*;
import static dev.coder2195.stellarity.registry.StellarityDamageTypes.*;
import static dev.coder2195.stellarity.tags.StellarityDamageTypeTags.*;
import static net.minecraft.tags.DamageTypeTags.*;

@SuppressWarnings("unchecked")
public class DamageTypeTagProvider extends FabricTagsProvider<DamageType> {
	public DamageTypeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, Registries.DAMAGE_TYPE, registriesFuture);
	}

	public TagAppender<DamageType> addTags(TagKey<DamageType> tagKey, TagKey<DamageType>... tags) {
		var appender = builder(tagKey);
		for (var tag : tags) {
			appender.forceAddTag(tag);
		}
		return appender;
	}

	@Override
	protected void addTags(HolderLookup.@NonNull Provider provider) {
		addTags(BYPASSES_COOLDOWN).add(BRITTLE);
		addTags(MELEE).add(PLAYER_ATTACK, MOB_ATTACK, MOB_ATTACK, PRISMEMBER);
		addTags(BYPASSES_ARMOR).add(FROSTBURN, ELECTRIC);
		addTags(AVOIDS_GUARDIAN_THORNS).add(ELECTRIC);
		addTags(BYPASSES_SHIELD).add(ELECTRIC);
		addTags(NO_KNOCKBACK).add(ELECTRIC);
		addTags(NO_KNOCKBACK).add(BRITTLE);
		addTags(TRIGGERS_FLESH_SPEED_BOOST, MELEE, IS_PROJECTILE, IS_EXPLOSION, IS_FALL).add(CACTUS, CRAMMING, DRAGON_BREATH, HOT_FLOOR, STING, IN_FIRE, SWEET_BERRY_BUSH, STALAGMITE, SONIC_BOOM, LIGHTNING_BOLT, ELECTRIC);
		addTags(BLOOD_FOR_BLOOD).add(TAMARIS_EXECUTE);
	}
}
