package dev.coder2195.stellarity.util;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

public interface NetworkingUtil {
	/**
	 * THIS DOES GUARANTEE IF TRACKED ENTITY IS PLAYER IT WILL ALSO SEND TO CLIENT VERSION OF THAT PLAYER
	 */
	static void sendTrackingPlayers(ServerLevel serverLevel, Entity tracked, CustomPacketPayload packet) {
		var chunkPos = tracked.chunkPosition();
		PlayerLookup.tracking(serverLevel, chunkPos).forEach(player -> ServerPlayNetworking.send(player, packet));
	}
}
