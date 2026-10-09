package dev.coder2195.stellarity.loot_function;


import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.coder2195.stellarity.Stellarity;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.Optional;

// imported from 26.2
public class FunctionReference extends LootItemConditionalFunction {
	public static final MapCodec<FunctionReference> MAP_CODEC = RecordCodecBuilder.mapCodec(
		i -> commonFields(i).and(Identifier.CODEC.fieldOf("name").forGetter(FunctionReference::getName)).apply(i, FunctionReference::new)
	);

	private final Identifier name;

	public Identifier getName() {
		return name;
	}

	public FunctionReference(Optional<Holder<LootItemCondition>> condition, Identifier name) {
		super(condition);
		this.name = name;
	}

	public FunctionReference(Identifier name) {
		this(Optional.empty(), name);
	}

	public FunctionReference(ResourceKey<? extends LootItemFunction> name) {
		this(name.identifier());
	}

	@Override
	public MapCodec<FunctionReference> codec() {
		return MAP_CODEC;
	}

	@Override
	protected ItemStack run(ItemStack itemStack, LootContext context) {
		var referencedFunc = context.getLevel().getServer().reloadableRegistries().lookup().get(ResourceKey.create(Registries.ITEM_MODIFIER, name));

		if (referencedFunc.isEmpty()) {
			Stellarity.LOGGER.error("Could not find modifier {} in loot function reference", name);
			return itemStack;
		}
		var funcHolder = referencedFunc.get();
		if (funcHolder.isBound()) {
			return funcHolder.value().apply(itemStack, context);
		}

		Stellarity.LOGGER.error("Not bound {} in loot function reference", name);
		return itemStack;
	}
}
