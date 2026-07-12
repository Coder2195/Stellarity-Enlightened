package dev.coder2195.stellarity.client.gui.widget;

import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;

public class IntegerSliderTextInput extends SliderTextInput {
	private int value;

	public int getValue() {
		return value;
	}

	public IntegerSliderTextInput(int x, int y, Font font, Component title, int sliderMin, int sliderMax, int min, int max, int step, int initialValue) {
		super(x, y, font, title, sliderMin, sliderMax, min, max, step);

		editBox.value = String.valueOf(initialValue);
		slider.setValueNoApply(valueToSlider(initialValue));
		
		this.value = initialValue;

		editBox.setResponder(text -> {
			int num = value;
			try {
				num = Integer.parseInt(text);
			} catch (Exception e) {
				this.editBox.setValue(String.valueOf(num));
			}
			if (num == this.value) return;
			if (num < min) num = min;
			if (num > max) num = max;

			this.value = num;
			this.editBox.setValue(String.valueOf(num));
			this.slider.setValue(valueToSlider(num));
		});

		slider.setOnValueChange(sliderValue -> {
			int num = (int) sliderToValue(sliderValue);
			if (num == this.value) return;
			this.value = num;
			this.editBox.setValue(String.valueOf(num));
		});
	}


}
