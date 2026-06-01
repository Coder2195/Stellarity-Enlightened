package prismatic.shards.stellarity.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.levelgen.Heightmap;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.key.StellarityEntityTypeIds;
import prismatic.shards.stellarity.registry.entity.*;


public interface StellarityEntityTypes {

	EntityType<PhantomItemFrame> PHANTOM_ITEM_FRAME = register(StellarityEntityTypeIds.PHANTOM_ITEM_FRAME, EntityType.Builder.of(PhantomItemFrame::new, MobCategory.MISC));

	EntityType<ThrownPrismaticPearl> PRISMATIC_PEARL = register(StellarityEntityTypeIds.PRISMATIC_PEARL, EntityType.Builder.<ThrownPrismaticPearl>of(ThrownPrismaticPearl::new, MobCategory.MISC).noLootTable().sized(0.25F, 0.25F));

	EntityType<VoidedZombie> VOIDED_ZOMBIE = register(StellarityEntityTypeIds.VOIDED_ZOMBIE, EntityType.Builder.<VoidedZombie>of(VoidedZombie::new, MobCategory.MONSTER).sized(0.6F, 1.95F).eyeHeight(1.74F).passengerAttachments(2.0125F).ridingOffset(-0.7F).clientTrackingRange(8).notInPeaceful());

	EntityType<VoidedSkeleton> VOIDED_SKELETON = register(StellarityEntityTypeIds.VOIDED_SKELETON, EntityType.Builder.of(VoidedSkeleton::new, MobCategory.MONSTER).sized(0.6F, 1.99F).eyeHeight(1.74F).ridingOffset(-0.7F).clientTrackingRange(8).notInPeaceful());

	EntityType<VoidedSilverfish> VOIDED_SILVERFISH = register(StellarityEntityTypeIds.VOIDED_SILVERFISH, EntityType.Builder.<VoidedSilverfish>of(VoidedSilverfish::new, MobCategory.MONSTER).sized(0.4F, 0.3F).eyeHeight(0.13F).passengerAttachments(0.2375F).clientTrackingRange(8).notInPeaceful());

	EntityType<VoidedSlime> VOIDED_SLIME = register(StellarityEntityTypeIds.VOIDED_SLIME, EntityType.Builder.<VoidedSlime>of(VoidedSlime::new, MobCategory.MONSTER).sized(0.52F, 0.52F).eyeHeight(0.325F).spawnDimensionsScale(4.0F).clientTrackingRange(10).notInPeaceful());

	EntityType<FleshPiglin> FLESH_PIGLIN = register(StellarityEntityTypeIds.FLESH_PIGLIN, EntityType.Builder.of(FleshPiglin::new, MobCategory.MONSTER).sized(0.6F, 1.95F).eyeHeight(1.74F).passengerAttachments(2.0125F).ridingOffset(-0.7F).clientTrackingRange(8).notInPeaceful());

	EntityType<Pixie> PIXIE = register(StellarityEntityTypeIds.PIXIE, EntityType.Builder.of(Pixie::new, MobCategory.AMBIENT).sized(0.5f, 0.5f).eyeHeight(0.25f).passengerAttachments(0.4f).clientTrackingRange(8));

	EntityType<VoidArrow> VOID_ARROW = register(StellarityEntityTypeIds.VOID_ARROW, EntityType.Builder.<VoidArrow>of(VoidArrow::new, MobCategory.MISC).sized(0.5F, 0.5F)
		.eyeHeight(0.13F)
		.clientTrackingRange(4)
		.updateInterval(20).noLootTable());

	EntityType<SatchelSigil> SATCHEL_SIGIL = register(StellarityEntityTypeIds.SATCHEL_SIGIL, EntityType.Builder.<SatchelSigil>of(SatchelSigil::new, MobCategory.MISC).sized(2, 0.125F).noLootTable());

	EntityType<SpectralBolt> SPECTRAL_BOLT = register(StellarityEntityTypeIds.SPECTRAL_BOLT, EntityType.Builder.<SpectralBolt>of(SpectralBolt::new, MobCategory.MISC).sized(0.5F, 0.5F)
		.eyeHeight(0.13F)
		.clientTrackingRange(4)
		.updateInterval(20).noLootTable());

	EntityType<SpectralWisp> SPECTRAL_WISP = register(StellarityEntityTypeIds.SPECTRAL_WISP, EntityType.Builder.<SpectralWisp>of(SpectralWisp::new, MobCategory.MISC).sized(0.5F, 0.5F)
		.eyeHeight(0.13F)
		.clientTrackingRange(4)
		.updateInterval(2).noLootTable());

	static <T extends Entity> EntityType<T> register(ResourceKey<EntityType<?>> key, EntityType.Builder<T> builder) {
		return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Entity Types");

		//noinspection DataFlowIssue: fabric bug
		FabricDefaultAttributeRegistry.register(VOIDED_ZOMBIE, VoidedZombie.createAttributes());
		FabricDefaultAttributeRegistry.register(VOIDED_SKELETON, VoidedSkeleton.createAttributes());
		FabricDefaultAttributeRegistry.register(VOIDED_SILVERFISH, VoidedSilverfish.createAttributes());
		FabricDefaultAttributeRegistry.register(VOIDED_SLIME, VoidedSlime.createAttributes());
		FabricDefaultAttributeRegistry.register(FLESH_PIGLIN, FleshPiglin.createAttributes());
		FabricDefaultAttributeRegistry.register(PIXIE, Pixie.createAttributes());

		SpawnPlacements.register(VOIDED_ZOMBIE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, VoidedZombie::checkMonsterSpawnRules);
		SpawnPlacements.register(VOIDED_SKELETON, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, VoidedSkeleton::checkMonsterSpawnRules);
		SpawnPlacements.register(VOIDED_SILVERFISH, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, VoidedSilverfish::checkMonsterSpawnRules);
		SpawnPlacements.register(VOIDED_SLIME, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, VoidedSlime::checkSpawnRules);
		SpawnPlacements.register(FLESH_PIGLIN, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FleshPiglin::checkMonsterSpawnRules);
		SpawnPlacements.register(PIXIE, SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Pixie::checkPixieSpawnRules);
	}
}
