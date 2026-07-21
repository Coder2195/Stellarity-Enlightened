package dev.coder2195.stellarity.client.registry;

import dev.coder2195.stellarity.registry.item.StellarStriker;
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

import java.util.List;

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

			if (GameType.SPECTATOR.equals(gameMode)) return;

			final int center = graphics.guiWidth() / 2;
			final int width = 182;
			final int half = width / 2;
			final int screenBottom = graphics.guiHeight();
			int offset = (gameMode == null || gameMode.isSurvival() ? HudStatusBarHeightRegistry.getHeight(VanillaHudElements.ARMOR_BAR) : HudStatusBarHeightRegistry.getHeight(VanillaHudElements.HEALTH_BAR)) - 8;

			var item = player.getMainHandItem();
			final int left = center - half;
			final int right = center + half;
			final int distance = (right - left);

			if (player.isHolding(StellarityItems.COPPER_ELEKTRA_SHIELD)) {
				offset += 18;
				if (!item.is(StellarityItems.COPPER_ELEKTRA_SHIELD)) item = player.getOffhandItem();

				final double percentageCharged = CopperElektraShield.chargePercent(item.getOrDefault(StellarityDataComponents.RECHARGES_AT, 0L), gameTime);
				final int progress = (int) (distance * percentageCharged);

				graphics.fill(left, screenBottom - offset - 2, right, screenBottom - offset + 1, 0x88888888);
				graphics.fill(left, screenBottom - offset - 2, left + progress, screenBottom - offset + 1, 0xFFE0976B);
				for (double percentage: List.of(1d/3, 2d/3, 1d))
					graphics.blit(RenderPipelines.GUI_TEXTURED, Stellarity.mcId("textures/mob_effect/speed.png"), (int) (left + distance * percentage - 8), screenBottom - offset - 8, 0, 0, 16, 16, 16, 16, percentageCharged < percentage ? 0xff888888 : 0xffffffff);


				graphics.item(item, left - 8, screenBottom - offset - 8);
			}



			if (player.isHolding(StellarityItems.STELLAR_STRIKER)) {
				item = player.getMainHandItem();
				if (!item.is(StellarityItems.STELLAR_STRIKER)) item = player.getOffhandItem();

				boolean disabled = item.getOrDefault(StellarityDataComponents.ABILITY_DISABLED_UNTIL, 0L) > gameTime;

				offset += 18;

				double percentageCharged = StellarStriker.chargePercent(item.getOrDefault(StellarityDataComponents.RECHARGES_AT, gameTime + StellarStriker.TOTAL_CHARGE_TIME), gameTime);
				final int progress = (int) (distance * percentageCharged);
				graphics.fill(left, screenBottom - offset - 2, right, screenBottom - offset + 1, disabled ? 0x88aa4444 : 0x88888888);
				graphics.fill(left, screenBottom - offset - 2, left + progress, screenBottom - offset + 1, disabled ? 0x88aa4444 : 0xFFE0976B);

				for (double percentage: StellarStriker.STAR_PERCENTAGES) {
					graphics.blit(RenderPipelines.GUI_TEXTURED, Stellarity.mcId("textures/item/nether_star.png"), (int) (left + distance * percentage - 8), screenBottom - offset - 9, 0, 0, 16, 16, 16, 16, disabled ? 0xff880000 : percentageCharged < percentage ? 0xff888888 : 0xffffffff);
				}

				graphics.item(item, left - 10, screenBottom - offset - 8);
			}
		});
	}
}
