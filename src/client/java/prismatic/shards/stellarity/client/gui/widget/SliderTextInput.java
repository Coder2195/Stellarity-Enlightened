package prismatic.shards.stellarity.client.gui.widget;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.MultiLineTextWidget;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public abstract class SliderTextInput extends LinearLayout {
	public final Font font;
	public final double sliderMin;
	public final double sliderMax;
	public final double min;
	public final double max;
	public final double step;
	protected final EditBox editBox;
	protected final Slider slider;
	protected final LinearLayout inputLayout = new LinearLayout(20, 300, Orientation.HORIZONTAL);
	private boolean active = true;

	public static class Slider extends AbstractSliderButton {
		public Slider(int x, int y, int width, int height, Component message, double initialValue) {
			super(x, y, width, height, message, initialValue);
		}

		@Nullable Consumer<Double> onValueChange = null;

		public void setOnValueChange(@Nullable Consumer<Double> onValueChange) {
			this.onValueChange = onValueChange;
		}

		@Override
		public void updateMessage() {

		}

		@Override
		protected void applyValue() {
			if (onValueChange == null) return;
			onValueChange.accept(this.value);
		}

		@Override
		public void setValue(double newValue) {
			super.setValue(newValue);
		}

		public void setValueNoApply(double newValue) {
			this.value = newValue;
			updateMessage();
		}

	}

	public SliderTextInput(int x, int y, Font font, Component title, double sliderMin, double sliderMax, double min, double max, double step) {
		super(x, y, Orientation.VERTICAL);
		this.font = font;
		this.sliderMin = sliderMin;
		this.sliderMax = sliderMax;
		this.min = min;
		this.max = max;
		this.step = step;

		this.editBox = new EditBox(font, 50, 20, Component.empty());
		editBox.setCentered(true);

		this.slider = new Slider(0, 0, x - 50, 20, Component.empty(), 0);

		var titleText = new MultiLineTextWidget(title, font).setCentered(true);
		titleText.setMaxWidth(x);

		inputLayout.addChild(slider);
		inputLayout.addChild(editBox);

		this.addChild(titleText);
		this.addChild(inputLayout);
		this.spacing(5);
	}


	public double sliderToValue(double slider) {
		return sliderMin + Math.round(slider * (sliderMax - sliderMin) / step) * step;
	}

	public double valueToSlider(double value) {
		return Math.clamp((value - sliderMin) / (sliderMax - sliderMin), 0d, 1d);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
		slider.active = active;
		editBox.active = active;
	}
}
