package dev.coder2195.stellarity.loot_pool_entry;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.entries.SingleEntryContainerBase;
import net.minecraft.world.level.storage.loot.entries.UniformContainerBase;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.Optional;
import java.util.function.Consumer;

public class ItemStackEntry extends SingleEntryContainerBase {
	public static final MapCodec<ItemStackEntry> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
		ItemStackTemplate.CODEC.fieldOf("name").forGetter(/* lambda$static$1 */ e -> e.itemStack)
	).and(uniformFields(i)).apply(i, ItemStackEntry::new));


	private final ItemStackTemplate itemStack;

	protected ItemStackEntry(ItemStackTemplate itemStack, int weight, int quality, Optional<Holder<LootItemCondition>> condition, Optional<Holder<LootItemFunction>> modifier) {
		super(weight, quality, condition, modifier);
		this.itemStack = itemStack;
	}

	@Override
	public MapCodec<? extends SingleEntryContainerBase> codec() {
		return CODEC;
	}

	@Override
	protected void createItemStack(Consumer<ItemStack> output, LootContext context) {
		itemStack.create();
	}

	public static  UniformContainerBase.Builder<?> lootTableItemStack(ItemStackTemplate itemStack) {
		return simpleBuilder(
			(weight, quality, conditions, functions) -> new ItemStackEntry(
				itemStack, weight, quality, conditions, functions
			)
		);
	}
}
