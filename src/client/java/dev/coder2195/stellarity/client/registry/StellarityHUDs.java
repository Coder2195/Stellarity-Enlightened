package dev.coder2195.stellarity.client.registry;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudStatusBarHeightRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.GameType;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.StellarityDataComponents;
import dev.coder2195.stellarity.registry.StellarityItems;
import dev.coder2195.stellarity.registry.item.CopperElektraShield;

public interface StellarityHUDs {
	Identifier ABILITIES = Stellarity.id("abilities");

	static void init() {
		HudElementRegistry.attachElementAfter(VanillaHudElements.HOTBAR, ABILITIES, (graphics, deltaTracker) -> {
			var minecraft = Minecraft.getInstance();
			var player = minecraft.player;
			if (player == null) return;
			var gameMode = player.gameMode();
			var level = player.level();
			var gameTime = level.getGameTime();

			if (player.gameMode().equals(GameType.SPECTATOR)) return;
			if (player.isHolding(StellarityItems.COPPER_ELEKTRA_SHIELD)) {
				final int center = graphics.guiWidth() / 2;
				final int width = 182;
				final int half = width / 2;
				final int screenBottom = graphics.guiHeight();
				int offset = (gameMode == null || gameMode.isSurvival() ? HudStatusBarHeightRegistry.getHeight(VanillaHudElements.ARMOR_BAR) : HudStatusBarHeightRegistry.getHeight(VanillaHudElements.HEALTH_BAR)) + 20;

				var item = player.getMainHandItem();
				if (!item.is(StellarityItems.COPPER_ELEKTRA_SHIELD)) item = player.getOffhandItem();

				final int left = center - half;
				final int right = center + half;
				final int distance = (right - left);
				final int progress = (int) (distance * ((CopperElektraShield.DASH_CHARGE_TIME * 3 - Math.max(0, item.getOrDefault(StellarityDataComponents.RECHARGES_AT, gameTime) - (double) gameTime - deltaTracker.getGameTimeDeltaPartialTick(false))) / (CopperElektraShield.DASH_CHARGE_TIME * 3)));


				graphics.fill(left, screenBottom - offset - 2, right, screenBottom - offset + 1, 0x88888888);
				graphics.fill(left, screenBottom - offset - 2, left + progress, screenBottom - offset + 1, 0xFFE0976B);
				graphics.blit(RenderPipelines.GUI_TEXTURED, Stellarity.mcId("textures/mob_effect/resistance.png"), left - 8, screenBottom - offset - 8, 0, 0, 16, 16, 16, 16);
				graphics.blit(RenderPipelines.GUI_TEXTURED, Stellarity.mcId("textures/mob_effect/speed.png"), (int) (left + distance * (1d / 3) - 8), screenBottom - offset - 8, 0, 0, 16, 16, 16, 16);
				graphics.blit(RenderPipelines.GUI_TEXTURED, Stellarity.mcId("textures/mob_effect/speed.png"), (int) (left + distance * (2d / 3) - 8), screenBottom - offset - 8, 0, 0, 16, 16, 16, 16);
				graphics.blit(RenderPipelines.GUI_TEXTURED, Stellarity.mcId("textures/mob_effect/speed.png"), right - 8, screenBottom - offset - 8, 0, 0, 16, 16, 16, 16);
			}
		});
	}
}
