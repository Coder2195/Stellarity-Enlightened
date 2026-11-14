package dev.coder2195.stellarity.loot_function;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.codec.HolderSetCodec;
import net.minecraft.util.RandomSource;
import net.minecraft.util.context.ContextKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.Optional;
import java.util.Set;

public class PickItemFunction extends LootItemConditionalFunction.ContextUser {
	private final HolderSet<Item> items;
	private final RandomSource random = RandomSource.create();
	public static final MapCodec<PickItemFunction> MAP_CODEC = RecordCodecBuilder.mapCodec(
		i -> commonFields(i)
			.and(
				HolderSetCodec.create(Registries.ITEM, Item.CODEC, false).fieldOf("items").forGetter(PickItemFunction::getItems)
			).apply(i, PickItemFunction::new)
	);

	public PickItemFunction(Optional<Holder<LootItemCondition>> predicates, HolderSet<Item> items) {
		super(predicates);
		this.items = items;
	}

	public HolderSet<Item> getItems() {
		return items;
	}

	@Override
	public MapCodec<? extends ContextUser> codec() {
		return MAP_CODEC;
	}

	@Override
	protected ItemStack run(ItemStack itemStack, LootContext context) {
		if (items.size() == 0) return itemStack;
		var item = items.getRandomElement(random);
		return item.map(itemHolder -> itemStack.transmuteCopy(itemHolder.value())).orElse(itemStack);
	}

	@Override
	public Set<ContextKey<?>> getRequiredParams() {
		return Set.of();
	}
}
