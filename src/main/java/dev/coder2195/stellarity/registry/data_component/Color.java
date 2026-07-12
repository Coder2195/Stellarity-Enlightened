package dev.coder2195.stellarity.registry.data_component;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ARGB;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import org.jspecify.annotations.NonNull;
import dev.coder2195.stellarity.registry.StellarityDataComponents;

import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

public record Color(int rgb) implements TooltipProvider {
	public static final Codec<Color> CODEC;
	public static final StreamCodec<ByteBuf, Color> STREAM_CODEC;
	public static final int LEATHER_COLOR = -6265536;

	public static int getOrDefault(final ItemStack itemStack, final int defaultColor) {
		Color color = itemStack.get(StellarityDataComponents.COLOR);
		return color != null ? ARGB.opaque(color.rgb()) : defaultColor;
	}

	public static ItemStack applyDyes(final ItemStack itemStack, final List<DyeColor> dyes) {
		Color currentDye = itemStack.get(StellarityDataComponents.COLOR);
		Color newColor = applyDyes(currentDye, dyes);
		ItemStack result = itemStack.copyWithCount(1);
		result.set(StellarityDataComponents.COLOR, newColor);
		return result;
	}


	public static Color applyDyes(final Color currentDye, final List<DyeColor> dyes) {
		int redTotal = 0;
		int greenTotal = 0;
		int blueTotal = 0;
		int intensityTotal = 0;
		int colorCount = 0;
		if (currentDye != null) {
			int red = ARGB.red(currentDye.rgb());
			int green = ARGB.green(currentDye.rgb());
			int blue = ARGB.blue(currentDye.rgb());
			intensityTotal += Math.max(red, Math.max(green, blue));
			redTotal += red;
			greenTotal += green;
			blueTotal += blue;
			++colorCount;
		}

		for (DyeColor dye : dyes) {
			int color = dye.getTextureDiffuseColor();
			int red = ARGB.red(color);
			int green = ARGB.green(color);
			int blue = ARGB.blue(color);
			intensityTotal += Math.max(red, Math.max(green, blue));
			redTotal += red;
			greenTotal += green;
			blueTotal += blue;
			++colorCount;
		}

		int red = redTotal / colorCount;
		int green = greenTotal / colorCount;
		int blue = blueTotal / colorCount;
		float averageIntensity = (float) intensityTotal / (float) colorCount;
		float resultIntensity = (float) Math.max(red, Math.max(green, blue));
		red = (int) ((float) red * averageIntensity / resultIntensity);
		green = (int) ((float) green * averageIntensity / resultIntensity);
		blue = (int) ((float) blue * averageIntensity / resultIntensity);
		int rgb = ARGB.color(0, red, green, blue);
		return new Color(rgb);
	}

	public void addToTooltip(final Item.TooltipContext context, final Consumer<Component> consumer, final @NonNull TooltipFlag flag, final @NonNull DataComponentGetter components) {
		int color = rgb();
		int inverted = invert(color);
		consumer.accept(Component.translatable("item.color",
			Component.literal(String.format(Locale.ROOT, "#%06X", 0xFFFFFF & color))
				.append(Component.literal(" █").withStyle(Style.EMPTY.withColor(color).withShadowColor(inverted)))
		).withStyle(ChatFormatting.GRAY));
	}

	private static int invert(int rgb) {
		int r = 255 - (rgb >> 16 & 0xFF);
		int g = 255 - (rgb >> 8 & 0xFF);
		int b = 255 - (rgb & 0xFF);

		return 0xFF << 24 | r << 16 | g << 8 | b;
	}

	static {
		CODEC = ExtraCodecs.RGB_COLOR_CODEC.xmap(Color::new, Color::rgb);
		STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT, Color::rgb, Color::new);
	}
}