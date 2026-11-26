package dev.coder2195.stellarity.client.event;

import com.google.common.collect.ImmutableMap;
import dev.coder2195.stellarity.item.CopperElektraShield;
import dev.coder2195.stellarity.item.Dragonblade;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;
import dev.coder2195.stellarity.registry.StellarityDataComponents;
import dev.coder2195.stellarity.registry.StellarityItems;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;

import java.util.Map;
import java.util.function.BiConsumer;

public interface StellarityClientTick {
	Map<Item, BiConsumer<ClientLevel, LocalPlayer>> ITEM_HELD_TICKERS = ImmutableMap.<Item, BiConsumer<ClientLevel, LocalPlayer>>builder()
		.put(StellarityItems.TAMARIS, StellarityClientTick::tamarisHeldTick)
		.put(StellarityItems.COPPER_ELEKTRA_SHIELD, StellarityClientTick::copperElektraShieldHeldTick)
		.put(StellarityItems.DRAGONBLADE, StellarityClientTick::dragonbladeHeldTick)
		.build();

	static void tamarisHeldTick(ClientLevel level, LocalPlayer player) {
		var nearbyEntities = level.getEntitiesOfClass(
			LivingEntity.class, player.getBoundingBox().inflate(10),
			e -> !e.equals(player) && e.isAlive() && e.getHealth() / e.getMaxHealth() < 0.25f
		);

		for (var nearbyEntity : nearbyEntities) {
			var particlePos = nearbyEntity.position().add(0, nearbyEntity.getBbHeight() + 0.5, 0);
			level.addParticle(ParticleTypes.SMOKE, particlePos.x, particlePos.y, particlePos.z, 0, 0, 0);
		}
	}

	static void copperElektraShieldHeldTick(ClientLevel level, LocalPlayer player) {
		var mainHand = player.getMainHandItem();
		var offHand = player.getOffhandItem();

		var gameTime = level.getGameTime();


		var target = mainHand.is(StellarityItems.COPPER_ELEKTRA_SHIELD) ? mainHand : offHand;
		long rechargesAt = target.getOrDefault(StellarityDataComponents.RECHARGES_AT, gameTime);

		if ((rechargesAt - gameTime) % CopperElektraShield.DASH_CHARGE_TIME == 1)
			player.playSound(SoundEvents.NOTE_BLOCK_BELL.value(), 1.0f, 0f);
	}

	static void dragonbladeHeldTick(ClientLevel level, LocalPlayer player) {
		var nearbyEntities = level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(20), e -> e.hasAttached(StellarityDataAttachments.DRAGON_STACKS));
		for (var nearbyEntity : nearbyEntities) {
			var dragonStacks = nearbyEntity.getAttached(StellarityDataAttachments.DRAGON_STACKS);
			if (dragonStacks == null) continue;
			short stacks = dragonStacks.stacks();

			var position = nearbyEntity.position();
			var topHeadPos = position.y + nearbyEntity.getBbHeight() + 0.2;


			if (dragonStacks.punched()) {
				level.addAlwaysVisibleParticle(ParticleTypes.SMOKE, position.x, topHeadPos, position.z, 0, 0, 0);
			} else {
				for (int i = 0; i < Dragonblade.PARTICLES.length; i++) {
					if (stacks <= i) break;
					level.addAlwaysVisibleParticle(Dragonblade.PARTICLES[i], position.x, topHeadPos + 0.25 * i, position.z, 0, 0, 0);
				}
			}
		}

	}

	static void init() {
		ClientTickEvents.START_CLIENT_TICK.register((client) -> {
			var level = client.level;
			var player = client.player;

			if (level == null || player == null) return;

			var mainhandItem = player.getMainHandItem().getItem();
			var offhandItem = player.getOffhandItem().getItem();
			var mainhand = ITEM_HELD_TICKERS.get(mainhandItem);
			if (mainhand != null) mainhand.accept(level, player);
			if (mainhandItem.equals(offhandItem)) return;
			var offhand = ITEM_HELD_TICKERS.get(offhandItem);
			if (offhand != null) offhand.accept(level, player);
		});
	}
}
