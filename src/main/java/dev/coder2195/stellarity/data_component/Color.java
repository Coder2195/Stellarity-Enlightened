package dev.coder2195.stellarity.data_component;

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
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.component.TooltipProvider;
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
		ItemStack result = itemStack.copyWithCount(1);
		if (currentDye == null) return result;

		Color newColor = applyDyes(currentDye, dyes);
		result.set(StellarityDataComponents.COLOR, newColor);
		return result;
	}


	public static Color applyDyes(final Color currentDye, final List<DyeColor> dyes) {
		return new Color(DyedItemColor.applyDyes(new DyedItemColor(currentDye.rgb), dyes).rgb());
	}

	public void addToTooltip(final Item.TooltipContext context, final Consumer<Component> consumer, final TooltipFlag flag, final DataComponentGetter components) {
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