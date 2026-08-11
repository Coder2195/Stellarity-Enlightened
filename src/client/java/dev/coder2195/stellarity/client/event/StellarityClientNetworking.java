package dev.coder2195.stellarity.client.event;

import dev.coder2195.stellarity.networking.*;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.client.gui.screen.ConfigScreen;
import dev.coder2195.stellarity.registry.StellaritySoundEvents;

public interface StellarityClientNetworking {
	static void init() {
		ClientPlayNetworking.registerGlobalReceiver(ClientboundVoidArrowHitPayload.TYPE, StellarityClientNetworking::voidArrowHit);
		ClientPlayNetworking.registerGlobalReceiver(ClientboundConfigScreenPayload.TYPE, StellarityClientNetworking::configScreen);
		ClientPlayNetworking.registerGlobalReceiver(ClientboundElectricDashPayload.TYPE, StellarityClientNetworking::electricDash);
		ClientPlayNetworking.registerGlobalReceiver(ClientboundSpellbookCastPayload.TYPE, StellarityClientNetworking::spellbookCast);
		ClientPlayNetworking.registerGlobalReceiver(ClientboundHolyProtectionDodgePayload.TYPE, StellarityClientNetworking::holyProtectionDodge);
		ClientPlayNetworking.registerGlobalReceiver(ClientboundFloralBloomBloomPayload.TYPE, StellarityClientNetworking::floralBloomBloom);

		Stellarity.LOGGER.info("Registering Stellarity Client Networking");
	}

	static void floralBloomBloom(ClientboundFloralBloomBloomPayload packet, ClientPlayNetworking.Context context) {
	}


	static void holyProtectionDodge(ClientboundHolyProtectionDodgePayload packet, ClientPlayNetworking.Context context) {
		var client = context.client();
		var player = client.player;
		var level = client.level;

		if (level == null) return;
		var random = RandomSource.create();

		var position = packet.position();

		for (int i=0; i<11; i++) {
			level.addParticle(ParticleTypes.END_ROD, true, true, position.x, position.y, position.z, random.nextGaussian() * 0.11, random.nextGaussian() * 0.11, random.nextGaussian() * 0.11);
			level.addParticle(ParticleTypes.FIREWORK, true, true, position.x, position.y, position.z, random.nextGaussian() * 0.11, random.nextGaussian() * 0.11, random.nextGaussian() * 0.11);
		}
		for (int i=0; i<22; i++) {
			level.addParticle(ParticleTypes.POOF, true, true, position.x + random.nextGaussian() * 0.3, position.y + random.nextGaussian() * 0.5, position.z + random.nextGaussian() * 0.3, 0, 0, 0);
		}

		level.addParticle(ColorParticleOption.create(ParticleTypes.FLASH, 0xffffffff), true, true,position.x, position.y, position.z, 0, 0, 0);
		level.playSound(player, position.x, position.y, position.z, StellaritySoundEvents.HALLOWED_ARMOR_DODGE, packet.source());
	}

	static void voidArrowHit(ClientboundVoidArrowHitPayload packet, ClientPlayNetworking.Context context) {
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
	}

	static void configScreen(ClientboundConfigScreenPayload packet, ClientPlayNetworking.Context context) {
		var minecraft = context.client();
		var config = packet.config();

		ConfigScreen.show(minecraft, config, packet.canEdit());
	}

	static void electricDash(ClientboundElectricDashPayload packet, ClientPlayNetworking.Context context) {
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

	}

	static void spellbookCast(ClientboundSpellbookCastPayload packet, ClientPlayNetworking.Context context) {
		var position = packet.position();
		var level = context.player().level();

		var random = RandomSource.create();

		double x = position.x;
		double y = position.y;
		double z = position.z;
		level.playLocalSound(x, y, z, StellaritySoundEvents.SPELLBOOK_CAST, SoundSource.PLAYERS, 1, 0.5f, false);
		for (int i = 0; i < 30; i++) {
			level.addParticle(ParticleTypes.ENCHANT, x + random.nextDouble() * 0.44 - 0.22, y + random.nextDouble() * 0.56 - 0.28, z + random.nextDouble() * 0.44 - 0.22, random.nextGaussian(), random.nextGaussian(), random.nextGaussian());
		}
	}
}
