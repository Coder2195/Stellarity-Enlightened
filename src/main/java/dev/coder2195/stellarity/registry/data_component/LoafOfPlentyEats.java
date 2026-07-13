package dev.coder2195.stellarity.registry.data_component;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public record LoafOfPlentyEats(int amount) implements TooltipProvider {
	public static final int[][] DESCRIPTIONS = {
		{1, 2}, {3, 4}, {5, 6}, {7}, {8, 9}, {10}, {11, 12}, {13, 14}, {15}, {16, 17}, {18, 19}, {20, 21}, {22, 23}, {24, 25}
	};
	public static final Codec<LoafOfPlentyEats> CODEC = Codec.INT.xmap(LoafOfPlentyEats::new, LoafOfPlentyEats::amount);
	public static final StreamCodec<ByteBuf, LoafOfPlentyEats> STREAM_CODEC = ByteBufCodecs.INT.map(LoafOfPlentyEats::new, LoafOfPlentyEats::amount);

	@Override
	public void addToTooltip(Item.@NonNull TooltipContext context, @NonNull Consumer<Component> consumer, @NonNull TooltipFlag flag, @NonNull DataComponentGetter components) {
		for (int desc: DESCRIPTIONS[Mth.clamp(amount - 1, 0, DESCRIPTIONS.length - 1)]) {
			consumer.accept(Component.translatable("item.stellarity.loaf_of_plenty.description." + desc));
		}
	}

	public LoafOfPlentyEats increment() {
		return new LoafOfPlentyEats(amount + 1);
	}
}
