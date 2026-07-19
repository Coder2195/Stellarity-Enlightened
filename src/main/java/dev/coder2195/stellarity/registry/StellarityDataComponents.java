package dev.coder2195.stellarity.registry;

import com.mojang.serialization.Codec;
import dev.coder2195.stellarity.registry.data_component.LoafOfPlentyEats;
import net.fabricmc.fabric.api.item.v1.ItemComponentTooltipProviderRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.data_component.Color;
import net.minecraft.util.Unit;

public interface StellarityDataComponents {
	DataComponentType<Color> COLOR = register("color", DataComponentType.<Color>builder().persistent(Color.CODEC).networkSynchronized(Color.STREAM_CODEC));
	DataComponentType<Long> RECHARGES_AT = register("recharges_at", DataComponentType.<Long>builder().persistent(Codec.LONG).networkSynchronized(ByteBufCodecs.LONG));
	DataComponentType<LoafOfPlentyEats> LOAF_OF_PLENTY_EATS = register("loaf_of_plenty_eats", DataComponentType.<LoafOfPlentyEats>builder().persistent(LoafOfPlentyEats.CODEC).networkSynchronized(LoafOfPlentyEats.STREAM_CODEC));
	DataComponentType<Unit> MARKED_ITEM = register("marked_item", DataComponentType.<Unit>builder().persistent(Unit.CODEC).networkSynchronized(Unit.STREAM_CODEC));
	DataComponentType<Long> ABILITY_DISABLED_UNTIL = register("ability_disabled_until", DataComponentType.<Long>builder().persistent(Codec.LONG).networkSynchronized(ByteBufCodecs.LONG));


	static <T> DataComponentType<T> register(String id, DataComponentType.Builder<T> component) {
		return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Stellarity.id(id), component.build());
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Data Components");

		ItemComponentTooltipProviderRegistry.addBefore(DataComponents.LORE, LOAF_OF_PLENTY_EATS);
		ItemComponentTooltipProviderRegistry.addAfter(DataComponents.DYED_COLOR, COLOR);
	}
}
