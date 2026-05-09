package prismatic.shards.stellarity.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.levelgen.Heightmap;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.key.StellarityEntityIds;
import prismatic.shards.stellarity.registry.entity.*;


public interface StellarityEntities {

	EntityType<PhantomItemFrame> PHANTOM_ITEM_FRAME = register(StellarityEntityIds.PHANTOM_ITEM_FRAME, EntityType.Builder.of(PhantomItemFrame::new, MobCategory.MISC));

	EntityType<ThrownPrismaticPearl> PRISMATIC_PEARL = register(StellarityEntityIds.PRISMATIC_PEARL, EntityType.Builder.<ThrownPrismaticPearl>of(ThrownPrismaticPearl::new, MobCategory.MISC).noLootTable().sized(0.25F, 0.25F));

	EntityType<VoidedZombie> VOIDED_ZOMBIE = register(StellarityEntityIds.VOIDED_ZOMBIE, EntityType.Builder.<VoidedZombie>of(VoidedZombie::new, MobCategory.MONSTER).sized(0.6F, 1.95F).eyeHeight(1.74F).passengerAttachments(2.0125F).ridingOffset(-0.7F).clientTrackingRange(8).notInPeaceful());

	EntityType<VoidedSkeleton> VOIDED_SKELETON = register(StellarityEntityIds.VOIDED_SKELETON, EntityType.Builder.<VoidedSkeleton>of(VoidedSkeleton::new, MobCategory.MONSTER).sized(0.6F, 1.99F).eyeHeight(1.74F).ridingOffset(-0.7F).clientTrackingRange(8).notInPeaceful());

	EntityType<VoidedSilverfish> VOIDED_SILVERFISH = register(StellarityEntityIds.VOIDED_SILVERFISH, EntityType.Builder.<VoidedSilverfish>of(VoidedSilverfish::new, MobCategory.MONSTER).sized(0.4F, 0.3F).eyeHeight(0.13F).passengerAttachments(0.2375F).clientTrackingRange(8).notInPeaceful());

	EntityType<VoidedSlime> VOIDED_SLIME = register(StellarityEntityIds.VOIDED_SLIME, EntityType.Builder.<VoidedSlime>of(VoidedSlime::new, MobCategory.MONSTER).sized(0.52F, 0.52F).eyeHeight(0.325F).spawnDimensionsScale(4.0F).clientTrackingRange(10).notInPeaceful());

	static <T extends Entity> EntityType<T> register(ResourceKey<EntityType<?>> key, EntityType.Builder<T> builder) {
		return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Entities");

		FabricDefaultAttributeRegistry.register(VOIDED_ZOMBIE, VoidedZombie.createAttributes());
		FabricDefaultAttributeRegistry.register(VOIDED_SKELETON, VoidedSkeleton.createAttributes());
		FabricDefaultAttributeRegistry.register(VOIDED_SILVERFISH, VoidedSilverfish.createAttributes());
		FabricDefaultAttributeRegistry.register(VOIDED_SLIME, VoidedSlime.createAttributes());
		SpawnPlacements.register(VOIDED_ZOMBIE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(VOIDED_SKELETON, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(VOIDED_SILVERFISH, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(VOIDED_SLIME, SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, VoidedSlime::checkSpawnRules);
	}
}
