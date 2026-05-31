package prismatic.shards.stellarity.client.registry.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ScrollableLayout;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.Nullable;
import prismatic.shards.stellarity.StellarityConfig;

@Environment(EnvType.CLIENT)
public class ConfigScreen extends Screen {
	private HeaderAndFooterLayout layout;
	private ScrollableLayout bodyScroll;
	private @Nullable Screen previousScreen;
	private StellarityConfig config;
	private LinearLayout body;

	public ConfigScreen(@Nullable Screen previousScreen, StellarityConfig config) {
		super(Component.translatable("stellarity.config.title"));
		this.config = config;
		this.previousScreen = previousScreen;
	}

	@Override
	protected void init() {
		layout = new HeaderAndFooterLayout(this);
		body = new LinearLayout(0, 0, LinearLayout.Orientation.VERTICAL);
		bodyScroll = new ScrollableLayout(this.minecraft, body, layout.getContentHeight());
		layout.addToContents(bodyScroll);
	}

	@Override
	protected void repositionElements() {
		this.bodyScroll.arrangeElements();
		this.bodyScroll.setMaxHeight(this.layout.getContentHeight());
		this.layout.arrangeElements();
	}

	public static void show(Minecraft minecraft, StellarityConfig config) {
		minecraft.gui.setScreen(new ConfigScreen(minecraft.gui.screen(), config));
	}

	public void hide() {
		minecraft.gui.setScreen(previousScreen);
	}
}
