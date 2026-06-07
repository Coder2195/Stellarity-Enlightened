package prismatic.shards.stellarity.client.registry;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityHUDs {
	Identifier ABILITIES = Stellarity.id("abilities");

	static void init() {
		HudElementRegistry.attachElementAfter(VanillaHudElements.ARMOR_BAR, ABILITIES, (graphics, deltaTracker) -> {
			var minecraft = Minecraft.getInstance();
			var player = minecraft.player;
			if (player == null) return;


		});
	}
}
