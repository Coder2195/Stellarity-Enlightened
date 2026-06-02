package prismatic.shards.stellarity.registry;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.item.v1.ItemComponentTooltipProviderRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.data_component.DyedColor;

public interface StellarityDataComponents {
	DataComponentType<DyedColor> DYED_COLOR = register("dyed_color", DataComponentType.<DyedColor>builder().persistent(DyedColor.CODEC).networkSynchronized(DyedColor.STREAM_CODEC));
	DataComponentType<Long> RECHARGES_AT = register("recharges_at", DataComponentType.<Long>builder().persistent(Codec.LONG).networkSynchronized(ByteBufCodecs.LONG));


	static <T> DataComponentType<T> register(String id, DataComponentType.Builder<T> component) {
		return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Stellarity.id(id), component.build());
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Data Components");

		ItemComponentTooltipProviderRegistry.addAfter(DataComponents.DYED_COLOR, DYED_COLOR);
	}
}
