package prismatic.shards.stellarity.client.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.client.gui.screen.ConfigScreen;
import prismatic.shards.stellarity.networking.ClientboundConfigScreenPayload;
import prismatic.shards.stellarity.networking.ClientboundElectricDashPayload;
import prismatic.shards.stellarity.networking.ClientboundVoidArrowHitPayload;
import prismatic.shards.stellarity.registry.StellaritySoundEvents;

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

		ClientPlayNetworking.registerGlobalReceiver(ClientboundConfigScreenPayload.TYPE, (packet, context) -> {
			var minecraft = context.client();
			var config = packet.config();

			ConfigScreen.show(minecraft, config, packet.canEdit());
		});

		ClientPlayNetworking.registerGlobalReceiver(ClientboundElectricDashPayload.TYPE, (packet, context) -> {
			var from = packet.from();
			var to = packet.to();
			var creeperPositions = packet.creeperPositions();

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

			for (var creeperPosition : creeperPositions) {
				level.playLocalSound(creeperPosition.x, creeperPosition.y, creeperPosition.z, SoundEvents.TRIDENT_THUNDER.value(), SoundSource.PLAYERS, 2, 1, false);
			}

			level.playLocalSound(to.x, to.y, to.z, StellaritySoundEvents.COPPER_ELEKTRA_SHIELD_DASH, SoundSource.PLAYERS, 1, 1, false);

		});

		Stellarity.LOGGER.info("Registering Stellarity Client Networking");
	}
}
