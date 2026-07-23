package dev.coder2195.stellarity.datagen.tags;

import dev.coder2195.stellarity.tags.StellarityEntityTypeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalEntityTypeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

import static dev.coder2195.stellarity.registry.StellarityEntityTypeIds.*;
import static net.minecraft.world.entity.EntityTypeIds.*;

public class EntityTypeTagProvider extends FabricTagsProvider.EntityTypeTagsProvider {
	public EntityTypeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
		super(output, completableFuture);
	}

	@SafeVarargs
	public final TagAppender<EntityType<?>> addTags(TagKey<EntityType<?>> tagKey, TagKey<EntityType<?>>... tags) {
		var appender = builder(tagKey);
		for (var tag : tags) {
			appender.forceAddTag(tag);
		}
		return appender;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addTags(HolderLookup.@NonNull Provider provider) {
		addTags(StellarityEntityTypeTags.INVALID_TARGETS, ConventionalEntityTypeTags.BOATS, EntityTypeTags.IMPACT_PROJECTILES, ConventionalEntityTypeTags.MINECARTS).add(ITEM_FRAME, GLOW_ITEM_FRAME, PAINTING, ARMOR_STAND, LEASH_KNOT, INTERACTION,
			AREA_EFFECT_CLOUD, ENDER_PEARL, END_CRYSTAL, EVOKER_FANGS, EXPERIENCE_BOTTLE, EXPERIENCE_ORB, EYE_OF_ENDER,
			FALLING_BLOCK, FISHING_BOBBER, FIREWORK_ROCKET, FIREBALL, ITEM, LLAMA_SPIT, SHULKER_BULLET, SPLASH_POTION, LINGERING_POTION
		);

		addTags(EntityTypeTags.INVERTED_HEALING_AND_HARM).add(VOIDED_SKELETON, VOIDED_ZOMBIE, FLESH_PIGLIN);
		addTags(EntityTypeTags.BURN_IN_DAYLIGHT).add(VOIDED_SKELETON, VOIDED_ZOMBIE);
		addTags(EntityTypeTags.ARROWS).add(SPECTRAL_BOLT, SPECTRAL_WISP, VOID_ARROW);
		addTags(EntityTypeTags.FROG_FOOD).add(VOIDED_SLIME);
		addTags(EntityTypeTags.DEFLECTS_PROJECTILES).add(OBSTRUCT_SPELL_BLOCK);
	}
}
