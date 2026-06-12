package prismatic.shards.stellarity.client.registry.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.server.dialog.input.TextInput;
import org.jspecify.annotations.Nullable;
import prismatic.shards.stellarity.StellarityConfig;

import java.util.HashMap;
import java.util.Optional;

public class ConfigScreen extends Screen {
	private HeaderAndFooterLayout layout;
	private ScrollableLayout bodyScroll;
	private final @Nullable Screen previousScreen;
	private final StellarityConfig config;
	private LinearLayout body;
	private final Button closeButton = Button.builder(Component.literal("Close"), (_) -> {
		this.close();
	}).build();
	private final Button saveButton = Button.builder(Component.literal("Save"), (_) -> {
		this.close();
	}).build();
	private final HashMap<String, Checkbox> checkBoxes = new HashMap<>();


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


		for (var booleanInput : StellarityConfig.BOOLEAN_INPUTS) {
			var name = booleanInput._1();
			var checkbox = Checkbox.builder(
				Component.translatable("stellarity.config." + name + ".title")
					.setStyle(Style.EMPTY.withHoverEvent(
						new HoverEvent.ShowText(Component.translatable("stellarity.config." + name + ".description"))
					)), this.font).selected(booleanInput._2().apply(config)).build();

			body.addChild(checkbox);
			checkBoxes.put(name, checkbox);
		}

		for (var integerInput : StellarityConfig.INTEGER_INPUTS) {

			var name = integerInput._1();
			var layout = new LinearLayout(300, 20, LinearLayout.Orientation.HORIZONTAL);
			final var editBox = new EditBox(this.font, 50, 20, Component.translatable("stellarity.config." + name + ".title"));
			final var slider = new AbstractSliderButton(0, 0, 250, 20, Component.translatable("stellarity.config." + name + ".title")
				.setStyle(Style.EMPTY.withHoverEvent(
					new HoverEvent.ShowText(Component.translatable("stellarity.config." + name + ".description"))
				)), integerInput._2().apply(config)) {

				@Override
				protected void updateMessage() {
					this.message = Component.literal(String.valueOf(this.value));
				}

				@Override
				protected void applyValue() {

				}
			};

			layout.addChild(slider);
			layout.addChild(editBox);

			body.addChild(layout);

		}


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
