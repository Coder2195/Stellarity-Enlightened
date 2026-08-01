package dev.coder2195.stellarity.event;

import dev.coder2195.stellarity.Stellarity;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

public class StellarityServerTick {
	public static int tickCount = 0;
	public static void init() {
		ServerTickEvents.END_SERVER_TICK.register((server) -> {
			if (++tickCount != 40) return;
			tickCount = 0;

			if (StellarityRegistryEntryModifications.nullscapeBiomes && Stellarity.hasBiolith() && !Stellarity.hasNullscape()) for (var player : server.getPlayerList().getPlayers()) {
				player.sendSystemMessage(Component.translatable("message.stellarity.nullscape_mod_required").setStyle(Style.EMPTY.withBold(true).withColor(0xff0000)));
			}
		});

	}
}
