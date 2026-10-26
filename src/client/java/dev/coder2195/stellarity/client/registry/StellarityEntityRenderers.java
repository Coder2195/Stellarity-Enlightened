package dev.coder2195.stellarity.client.registry;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.client.renderer.entity.*;
import dev.coder2195.stellarity.registry.StellarityEntityTypes;
import dev.coder2195.stellarity.util.tuple.Tuple2;
import net.minecraft.client.core.registries.BuiltInClientRegistries;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;

import java.util.List;

public interface StellarityEntityRenderers {
	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Entity Renderers");
		register(StellarityEntityTypes.PHANTOM_ITEM_FRAME, PhantomItemFrameRenderer::new);
		register(StellarityEntityTypes.PRISMATIC_PEARL, ThrownItemRenderer::new);
		register(StellarityEntityTypes.VOIDED_ZOMBIE, VoidedZombieRenderer::new);
		register(StellarityEntityTypes.VOIDED_SILVERFISH, VoidedSilverfishRenderer::new);
		register(StellarityEntityTypes.VOIDED_SKELETON, VoidedSkeletonRenderer::new);
		register(StellarityEntityTypes.VOIDED_SLIME, VoidedSlimeRenderer::new);
		register(StellarityEntityTypes.FLESH_PIGLIN, FleshPiglinRenderer::new);
		register(StellarityEntityTypes.PIXIE, PixieRenderer::new);
		register(StellarityEntityTypes.VOID_ARROW, VoidArrowRenderer::new);
		register(StellarityEntityTypes.SATCHEL_SIGIL, SatchelSigilRenderer::new);
		register(StellarityEntityTypes.SPECTRAL_BOLT, SpectralBoltRenderer::new);
		register(StellarityEntityTypes.SPECTRAL_WISP, NoopRenderer::new);
		register(StellarityEntityTypes.STRIKER_STAR, NoopRenderer::new);
		register(StellarityEntityTypes.CONVEYANCE_SPARK, NoopRenderer::new);
		register(StellarityEntityTypes.OBSTRUCT_SPELL_BLOCK, ObstructSpellBlockRenderer::new);
		register(StellarityEntityTypes.LIGHT_AURA, NoopRenderer::new);
		register(StellarityEntityTypes.POTION_CLOUD, NoopRenderer::new);
		register(StellarityEntityTypes.DRAGON_BREATH_CAULDRON_INGREDIENT, DragonBreathCauldronIngredientRenderer::new);
		for (Tuple2<? extends EntityType<? extends AbstractBoat>, ModelLayerLocation> boat: List.of(
			new Tuple2<>(StellarityEntityTypes.AMETHYII_BOAT, StellarityModelLayers.AMETHYII_BOAT),
			new Tuple2<>(StellarityEntityTypes.HALLOWED_BOAT, StellarityModelLayers.HALLOWED_BOAT),
			new Tuple2<>(StellarityEntityTypes.SHRUBBED_BOAT, StellarityModelLayers.SHRUBBED_BOAT),
			new Tuple2<>(StellarityEntityTypes.PRISMATIC_BOAT, StellarityModelLayers.PRISMATIC_BOAT),
			new Tuple2<>(StellarityEntityTypes.ASHEN_BOAT, StellarityModelLayers.ASHEN_BOAT),
			new Tuple2<>(StellarityEntityTypes.INFERNO_BOAT, StellarityModelLayers.INFERNO_BOAT),

			new Tuple2<>(StellarityEntityTypes.AMETHYII_CHEST_BOAT, StellarityModelLayers.AMETHYII_CHEST_BOAT),
			new Tuple2<>(StellarityEntityTypes.HALLOWED_CHEST_BOAT, StellarityModelLayers.HALLOWED_CHEST_BOAT),
			new Tuple2<>(StellarityEntityTypes.SHRUBBED_CHEST_BOAT, StellarityModelLayers.SHRUBBED_CHEST_BOAT),
			new Tuple2<>(StellarityEntityTypes.PRISMATIC_CHEST_BOAT, StellarityModelLayers.PRISMATIC_CHEST_BOAT),
			new Tuple2<>(StellarityEntityTypes.ASHEN_CHEST_BOAT, StellarityModelLayers.ASHEN_CHEST_BOAT),
			new Tuple2<>(StellarityEntityTypes.INFERNO_CHEST_BOAT, StellarityModelLayers.INFERNO_CHEST_BOAT)
		)) {
			register(boat._1(), c -> new BoatRenderer(c, boat._2()));
		}
		register(StellarityEntityTypes.SPIRIT, SpiritRenderer::new);
	}

	static <T extends Entity> void register(EntityType<? extends T> type, EntityRendererProvider<T> renderer) {
		Registry.register(BuiltInClientRegistries.ENTITY_RENDERER, BuiltInRegistries.ENTITY_TYPE.getKey(type), renderer);
	}
}
