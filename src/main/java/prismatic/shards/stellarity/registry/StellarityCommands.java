package prismatic.shards.stellarity.registry;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.commands.Commands;
import prismatic.shards.stellarity.StellarityConfig;
import prismatic.shards.stellarity.networking.ClientboundConfigPayload;

public interface StellarityCommands {
	static void init() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(Commands.literal("stellarity_config" +
				"").executes(context -> {
				var level = context.getSource().getServer();
				var config = StellarityConfig.get(level);
				var player = context.getSource().getPlayer();
				if (player != null) ServerPlayNetworking.send(player, new ClientboundConfigPayload(config));
				return 1;
			}));
		});
	}
}
