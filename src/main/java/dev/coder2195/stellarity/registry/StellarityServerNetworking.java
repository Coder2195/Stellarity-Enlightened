package dev.coder2195.stellarity.registry;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.chat.Component;
import dev.coder2195.stellarity.event.StellarityCommands;
import dev.coder2195.stellarity.networking.ServerboundConfigUpdatePayload;

public interface StellarityServerNetworking {
	static void init() {
		ServerPlayNetworking.registerGlobalReceiver(ServerboundConfigUpdatePayload.TYPE, (payload, context) -> {
			var config = payload.config();
			var player = context.player();
			var server = context.server();
			if (StellarityCommands.CAN_CONFIG.test(server, player)) {
				config.set(server);
				player.sendSystemMessage(Component.translatable("stellarity.config.success"));
			} else {
				player.sendSystemMessage(Component.translatable("commands.help.failed"));
			}
		});
	}
}
