package prismatic.shards.stellarity.client.registry.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ScrollableLayout;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.Nullable;
import prismatic.shards.stellarity.StellarityConfig;

public class ConfigScreen extends Screen {
	private HeaderAndFooterLayout layout;
	private ScrollableLayout bodyScroll;
	private final @Nullable Screen previousScreen;
	private StellarityConfig config;
	private LinearLayout body;
	private final Button closeButton = Button.builder(Component.literal("Close"), (_) -> {
		this.close();
	}).build();
	private final Button saveButton = Button.builder(Component.literal("Save"), (_) -> {
		this.close();
	}).build();

	public ConfigScreen(@Nullable Screen previousScreen, StellarityConfig config) {
		super(Component.translatable("stellarity.config.title"));
		this.config = config;
		this.previousScreen = previousScreen;

	}

	@Override
	protected void init() {
		this.width = this.minecraft.getWindow().getGuiScaledWidth();
		this.height = this.minecraft.getWindow().getGuiScaledHeight();
		layout = new HeaderAndFooterLayout(this);
		body = LinearLayout.vertical().spacing(10);
		body.defaultCellSetting().alignHorizontallyCenter();
		bodyScroll = new ScrollableLayout(this.minecraft, body, layout.getContentHeight());
		layout.addToContents(bodyScroll);
		layout.addToFooter(closeButton);
		layout.addToFooter(saveButton);
		layout.addToHeader(new StringWidget(Component.translatable("stellarity.config.title"), this.font));
		this.layout.visitWidgets(this::addRenderableWidget);
		repositionElements();
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

	public void close() {
		minecraft.gui.setScreen(previousScreen);
	}
}
