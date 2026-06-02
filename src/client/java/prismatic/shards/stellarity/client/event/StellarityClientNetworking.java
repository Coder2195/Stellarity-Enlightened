package prismatic.shards.stellarity.client.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LightningBolt;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.client.registry.screen.ConfigScreen;
import prismatic.shards.stellarity.networking.ClientboundConfigPayload;
import prismatic.shards.stellarity.networking.ClientboundElectricDashPayload;
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

		ClientPlayNetworking.registerGlobalReceiver(ClientboundElectricDashPayload.TYPE, (packet, context) -> {
			var from = packet.from();
			var to = packet.to();

			var level = context.player().level();

			var displacement = to.subtract(from);
			var particles = Math.floor(displacement.length() / 0.05);

			var random = RandomSource.create();
			for (int i = 0; i < particles; i++) {
				var progress = i / particles;

				level.addAlwaysVisibleParticle(ParticleTypes.ELECTRIC_SPARK, true,
					from.x + displacement.x * progress + random.nextGaussian() * 0.25,
					from.y + displacement.y * progress + random.nextGaussian() * 0.25,
					from.z + displacement.z * progress + random.nextGaussian() * 0.25,
					random.nextGaussian() * 1.1, random.nextGaussian() * 1.1, random.nextGaussian() * 1.1
				);

				level.addAlwaysVisibleParticle(new DustParticleOptions(0xf4f4f4, 1), true,
					from.x + displacement.x * progress + random.nextGaussian() * 0.4,
					from.y + displacement.y * progress + random.nextGaussian() * 0.4,
					from.z + displacement.z * progress + random.nextGaussian() * 0.4,
					random.nextGaussian() * 1.1, random.nextGaussian() * 1.1, random.nextGaussian() * 1.1
				);

				level.addAlwaysVisibleParticle(new DustParticleOptions(0x4dc3ff, 1), true,
					from.x + displacement.x * progress + random.nextGaussian() * 0.4,
					from.y + displacement.y * progress + random.nextGaussian() * 0.4,
					from.z + displacement.z * progress + random.nextGaussian() * 0.4,
					random.nextGaussian() * 1.1, random.nextGaussian() * 1.1, random.nextGaussian() * 1.1
				);


			}
		});

		Stellarity.LOGGER.info("Registering Stellarity Client Networking");
	}
}
