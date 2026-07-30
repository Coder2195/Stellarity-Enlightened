package dev.coder2195.stellarity.item;

import dev.coder2195.stellarity.networking.ClientboundSpellbookCastPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.NonNull;

public abstract class Spellbook extends Item {
	public Spellbook(Properties properties) {
		super(properties);
	}

	public void castSpell(@NonNull ServerLevel level, Player player) {
		for (var serverPlayer: level.players()) {
			ServerPlayNetworking.send(serverPlayer, new ClientboundSpellbookCastPayload(player.getEyePosition()));
		}
	}
}
