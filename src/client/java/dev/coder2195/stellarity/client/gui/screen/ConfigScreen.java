package dev.coder2195.stellarity.client.gui.screen;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.ScrollableLayout;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import org.jspecify.annotations.Nullable;
import dev.coder2195.stellarity.StellarityConfig;
import dev.coder2195.stellarity.client.gui.widget.IntegerSliderTextInput;
import dev.coder2195.stellarity.networking.ServerboundConfigUpdatePayload;

import java.util.HashMap;

public class ConfigScreen extends Screen {
	private HeaderAndFooterLayout layout;
	private ScrollableLayout bodyScroll;
	private final @Nullable Screen previousScreen;
	private final boolean canEdit;
	private final StellarityConfig config;
	private final LinearLayout body = LinearLayout.vertical().spacing(10);
	private final HashMap<String, Checkbox> checkBoxes = new HashMap<>();
	private final HashMap<String, IntegerSliderTextInput> healthInputs = new HashMap<>();
	private final Button saveButton = Button.builder(Component.translatable("stellarity.config.save"), (_) -> {
		var newConfig = new StellarityConfig(
			checkBoxes.get("join_message").selected(),
			checkBoxes.get("always_generate_egg").selected(),
			checkBoxes.get("enable_end_crystal_drop").selected(),
			checkBoxes.get("enable_totem_void_saving").selected(),
			checkBoxes.get("allow_disenchanting").selected(),
			checkBoxes.get("enable_creative_shock").selected(),
			checkBoxes.get("nerf_elytra").selected(),
			checkBoxes.get("boss_status_messages").selected(),
			healthInputs.get("dragon_health").getValue(),
			healthInputs.get("empress_of_light_health").getValue(),
			healthInputs.get("shulking_health").getValue()
		);

		ClientPlayNetworking.send(new ServerboundConfigUpdatePayload(newConfig));
		this.close();
	}).build();
	private final Button exitButton = Button.builder(Component.translatable("stellarity.config.exit"), (_) -> {
		this.close();
	}).build();
	private final LinearLayout footer = LinearLayout.horizontal().spacing(10);

	public ConfigScreen(@Nullable Screen previousScreen, StellarityConfig config, boolean canEdit) {
		super(Component.translatable("stellarity.config.title"));
		this.config = config;
		this.previousScreen = previousScreen;
		this.canEdit = canEdit;
	}

	@Override
	protected void init() {
		this.width = this.minecraft.getWindow().getGuiScaledWidth();
		this.height = this.minecraft.getWindow().getGuiScaledHeight();
		layout = new HeaderAndFooterLayout(this);
		body.defaultCellSetting().alignHorizontallyCenter();
		bodyScroll = new ScrollableLayout(this.minecraft, body, layout.getContentHeight());


		footer.addChild(exitButton);
		if (canEdit) footer.addChild(saveButton);
		layout.addToFooter(footer);


		layout.addToContents(bodyScroll);
		layout.addToHeader(new StringWidget(Component.translatable("stellarity.config.title"), this.font));


		for (var booleanInput : StellarityConfig.BOOLEAN_INPUTS) {
			var name = booleanInput._1();
			var checkbox = Checkbox.builder(
				Component.translatable("stellarity.config." + name + ".title")
					.setStyle(Style.EMPTY.withHoverEvent(
						new HoverEvent.ShowText(Component.translatable("stellarity.config." + name + ".description"))
					)), this.font).selected(booleanInput._2().apply(config)).build();

			body.addChild(checkbox);
			checkbox.active = canEdit;
			checkBoxes.put(name, checkbox);
		}

		for (var healthInput : StellarityConfig.HEALTH_INPUTS) {
			var name = healthInput._1();
			var intSliderTextInput = new IntegerSliderTextInput(300, 20, font, Component.translatable("stellarity.config." + name + ".title").setStyle(Style.EMPTY.withHoverEvent(
				new HoverEvent.ShowText(Component.translatable("stellarity.config." + name + ".description"))
			)), 10, 5000, 10, 100000, 10, healthInput._2().apply(config));

			body.addChild(intSliderTextInput);
			intSliderTextInput.setActive(canEdit);
			healthInputs.put(name, intSliderTextInput);
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


	public static void show(Minecraft minecraft, StellarityConfig config, boolean canEdit) {
		minecraft.gui.setScreen(new ConfigScreen(minecraft.gui.screen(), config, canEdit));
	}

	public void close() {
		minecraft.gui.setScreen(previousScreen);
	}
}
