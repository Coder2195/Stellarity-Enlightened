package dev.coder2195.stellarity.client.registry;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.client.renderer.entity.*;
import dev.coder2195.stellarity.registry.StellarityEntityTypes;
import dev.coder2195.stellarity.util.tuple.Tuple2;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;

import java.util.List;

public interface StellarityEntityRenderers {
	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Entity Renderers");
		EntityRenderers.register(StellarityEntityTypes.PHANTOM_ITEM_FRAME, PhantomItemFrameRenderer::new);
		EntityRenderers.register(StellarityEntityTypes.PRISMATIC_PEARL, ThrownItemRenderer::new);
		EntityRenderers.register(StellarityEntityTypes.VOIDED_ZOMBIE, VoidedZombieRenderer::new);
		EntityRenderers.register(StellarityEntityTypes.VOIDED_SILVERFISH, VoidedSilverfishRenderer::new);
		EntityRenderers.register(StellarityEntityTypes.VOIDED_SKELETON, VoidedSkeletonRenderer::new);
		EntityRenderers.register(StellarityEntityTypes.VOIDED_SLIME, VoidedSlimeRenderer::new);
		EntityRenderers.register(StellarityEntityTypes.FLESH_PIGLIN, FleshPiglinRenderer::new);
		EntityRenderers.register(StellarityEntityTypes.PIXIE, PixieRenderer::new);
		EntityRenderers.register(StellarityEntityTypes.VOID_ARROW, VoidArrowRenderer::new);
		EntityRenderers.register(StellarityEntityTypes.SATCHEL_SIGIL, SatchelSigilRenderer::new);
		EntityRenderers.register(StellarityEntityTypes.SPECTRAL_BOLT, SpectralBoltRenderer::new);
		EntityRenderers.register(StellarityEntityTypes.SPECTRAL_WISP, NoopRenderer::new);
		EntityRenderers.register(StellarityEntityTypes.STRIKER_STAR, NoopRenderer::new);
		EntityRenderers.register(StellarityEntityTypes.CONVEYANCE_SPARK, NoopRenderer::new);
		EntityRenderers.register(StellarityEntityTypes.OBSTRUCT_SPELL_BLOCK, ObstructSpellBlockRenderer::new);
		EntityRenderers.register(StellarityEntityTypes.LIGHT_AURA, NoopRenderer::new);
		EntityRenderers.register(StellarityEntityTypes.POTION_CLOUD, NoopRenderer::new);
		EntityRenderers.register(StellarityEntityTypes.DRAGON_BREATH_CAULDRON_INGREDIENT, DragonBreathCauldronIngredientRenderer::new);
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
			EntityRenderers.register(boat._1(), c -> new BoatRenderer(c, boat._2()));
		}
	}
}
