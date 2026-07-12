package dev.coder2195.stellarity.event;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.commands.Commands;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.Permissions;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.StellarityConfig;
import dev.coder2195.stellarity.networking.ClientboundConfigScreenPayload;

import java.util.function.BiPredicate;

public interface StellarityCommands {
	BiPredicate<MinecraftServer, ServerPlayer> CAN_CONFIG = (server, player) ->
		(!server.isDedicatedServer() && server.isSingleplayerOwner(player.nameAndId())) || player.permissions().hasPermission(Permissions.COMMANDS_ADMIN);

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity commands");
		CommandRegistrationCallback.EVENT.register((dispatcher, _, _) -> {
			dispatcher.register(Commands.literal("stellarity_config").executes(context -> {
				var server = context.getSource().getServer();
				var config = StellarityConfig.get(server);
				var player = context.getSource().getPlayer();
				if (player != null)
					ServerPlayNetworking.send(player, new ClientboundConfigScreenPayload(config, CAN_CONFIG.test(server, player)));
				return 1;
			}));
		});
	}
}
