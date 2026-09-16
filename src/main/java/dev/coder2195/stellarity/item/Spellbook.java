package dev.coder2195.stellarity.item;

import dev.coder2195.stellarity.networking.ClientboundSpellbookCastPayload;
import dev.coder2195.stellarity.util.NetworkingUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

public abstract class Spellbook extends Item {
	public Spellbook(Properties properties) {
		super(properties);
	}

	public void castSpell(ServerLevel level, Player player) {
		var packet = new ClientboundSpellbookCastPayload(player.getEyePosition());

		NetworkingUtil.sendTrackingPlayers(level, player, packet);
	}
}
