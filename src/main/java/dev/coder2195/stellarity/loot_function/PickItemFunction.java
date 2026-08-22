package dev.coder2195.stellarity.loot_function;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.HolderSetCodec;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class PickItemFunction extends LootItemConditionalFunction {
	private final HolderSet<Item> items;
	private final RandomSource random = RandomSource.create();
	public static final MapCodec<PickItemFunction> MAP_CODEC = RecordCodecBuilder.mapCodec(
		i -> commonFields(i)
			.and(
				HolderSetCodec.create(Registries.ITEM, Item.CODEC, false).fieldOf("items").forGetter(PickItemFunction::getItems)
			).apply(i, PickItemFunction::new)
	);

	public PickItemFunction(List<LootItemCondition> predicates, HolderSet<Item> items) {
		super(predicates);
		this.items = items;
	}

	public HolderSet<Item> getItems() {
		return items;
	}

	@Override
	public @NonNull MapCodec<? extends LootItemConditionalFunction> codec() {
		return MAP_CODEC;
	}

	@Override
	protected @NonNull ItemStack run(@NonNull ItemStack itemStack, @NonNull LootContext context) {
		if (items.size() == 0) return itemStack;
		var item = items.getRandomElement(random);
		return item.map(itemHolder -> itemStack.transmuteCopy(itemHolder.value())).orElse(itemStack);
	}
}
