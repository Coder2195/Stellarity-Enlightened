package prismatic.shards.stellarity.client.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import prismatic.shards.stellarity.registry.StellarityDataComponents;
import prismatic.shards.stellarity.registry.StellarityItems;
import prismatic.shards.stellarity.registry.item.CopperElektraShield;

public interface StellarityClientTick {
	static void init() {
		ClientTickEvents.START_CLIENT_TICK.register((client) -> {
			var level = client.level;
			var player = client.player;
			if (level == null || player == null) return;
			var position = player.position();

			var mainHand = player.getMainHandItem();
			var offHand = player.getOffhandItem();
			if (player.isHolding(StellarityItems.TAMARIS)) {
				var nearbyEntities = level.getEntitiesOfClass(
					LivingEntity.class,
					new AABB(position.subtract(10, 10, 10), position.add(10, 10, 10)),
					e -> !e.is(player) && e.isAlive() && e.getHealth() / e.getMaxHealth() < 0.25f
				);

				for (var nearbyEntity : nearbyEntities) {
					var particlePos = nearbyEntity.position().add(0, nearbyEntity.getBbHeight() + 0.5, 0);
					level.addParticle(ParticleTypes.SMOKE, particlePos.x, particlePos.y, particlePos.z, 0, 0, 0);
				}


			} else if (player.isHolding(StellarityItems.COPPER_ELEKTRA_SHIELD)) {
				var gameTime = level.getGameTime();


				var target = mainHand.is(StellarityItems.COPPER_ELEKTRA_SHIELD) ? mainHand : offHand;
				long rechargesAt = target.getOrDefault(StellarityDataComponents.RECHARGES_AT, gameTime);

				if ((rechargesAt - gameTime) % CopperElektraShield.DASH_CHARGE_TIME == 1)
					player.playSound(SoundEvents.NOTE_BLOCK_BELL.value(), 1.0f, 0f);
			}
		});
	}
}
