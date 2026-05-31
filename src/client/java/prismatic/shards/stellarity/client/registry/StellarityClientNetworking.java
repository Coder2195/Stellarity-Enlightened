package prismatic.shards.stellarity.client.registry;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.core.particles.ParticleTypes;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.client.registry.screen.ConfigScreen;
import prismatic.shards.stellarity.networking.ClientboundConfigPayload;
import prismatic.shards.stellarity.networking.ClientboundVoidArrowHitPayload;

public interface StellarityClientNetworking {
	static void init() {
		ClientPlayNetworking.registerGlobalReceiver(ClientboundVoidArrowHitPayload.TYPE, (packet, context) -> {
			var level = context.client().level;
			if (level == null) return;

			var position = packet.position();
			var shrapnel = packet.shrapnel();


			shrapnel.forEach(vec -> {
				double length = vec.length();
				double chunks = length / 0.2;
				for (int i = 0; i < chunks; i++) {
					double progress = i / chunks;
					level.addParticle(ParticleTypes.ELECTRIC_SPARK, vec.x * progress + position.x, vec.y * progress + position.y, vec.z * progress + position.z, 0, 0, 0);
				}
			});
		});

		ClientPlayNetworking.registerGlobalReceiver(ClientboundConfigPayload.TYPE, (packet, context) -> {
			var minecraft = context.client();
			var config = packet.config();

			ConfigScreen.show(minecraft, config);
		});

		Stellarity.LOGGER.info("Registering Stellarity Client Networking");
	}
}
