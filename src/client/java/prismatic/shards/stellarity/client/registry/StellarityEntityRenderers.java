package prismatic.shards.stellarity.client.registry;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.client.registry.renderer.entity.*;
import prismatic.shards.stellarity.registry.StellarityEntityTypes;

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
		EntityRenderers.register(StellarityEntityTypes.SPECTRAL_WISP, EmptyRenderer::new);
	}
}
