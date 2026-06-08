package prismatic.shards.stellarity.client.registry.item_tint_source;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import prismatic.shards.stellarity.registry.StellarityDataComponents;
import prismatic.shards.stellarity.registry.block.ColoredLeavesBlock;
import prismatic.shards.stellarity.registry.data_component.Color;

public class ColorTintSource implements ItemTintSource {
	public static final MapCodec<ColorTintSource> MAP_CODEC = MapCodec.unit(ColorTintSource::new);

	@Override
	public int calculate(ItemStack itemStack, @Nullable ClientLevel level, @Nullable LivingEntity owner) {
		return itemStack.getOrDefault(StellarityDataComponents.COLOR, new Color(ColoredLeavesBlock.DEFAULT_COLOR)).rgb() | 0xff000000;
	}

	@Override
	public @NonNull MapCodec<? extends ItemTintSource> type() {
		return MAP_CODEC;
	}
}
