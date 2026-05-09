package prismatic.shards.stellarity.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalEntityTypeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.tags.StellarityEntityTags;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.world.entity.EntityType.*;
import static prismatic.shards.stellarity.registry.StellarityEntities.VOIDED_SLIME;

public class EntityTagProvider extends FabricTagsProvider.EntityTypeTagsProvider {
	public EntityTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
		super(output, completableFuture);
	}

	@Override
	public void addTags(HolderLookup.@NonNull Provider provider) {
		valueLookupBuilder(StellarityEntityTags.INVALID_TARGETS).addOptionalTag(ConventionalEntityTypeTags.BOATS).forceAddTag(EntityTypeTags.IMPACT_PROJECTILES).addOptionalTag(ConventionalEntityTypeTags.MINECARTS).add(ITEM_FRAME, GLOW_ITEM_FRAME, PAINTING, ARMOR_STAND, LEASH_KNOT, INTERACTION,
			AREA_EFFECT_CLOUD, ENDER_PEARL, END_CRYSTAL, EVOKER_FANGS, EXPERIENCE_BOTTLE, EXPERIENCE_ORB, EYE_OF_ENDER,
			FALLING_BLOCK, FISHING_BOBBER, FIREWORK_ROCKET, FIREBALL, ITEM, LLAMA_SPIT, SHULKER_BULLET, SPLASH_POTION, LINGERING_POTION
		);

		valueLookupBuilder(EntityTypeTags.FROG_FOOD).add(VOIDED_SLIME);
	}
}
