package prismatic.shards.stellarity.registry;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.chat.Component;
import prismatic.shards.stellarity.event.StellarityCommands;
import prismatic.shards.stellarity.networking.ServerboundConfigUpdatePayload;

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
