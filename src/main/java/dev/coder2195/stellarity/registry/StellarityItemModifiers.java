package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.tags.StellarityStructureTags;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Unit;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.saveddata.maps.MapDecorationTypes;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.SetNameFunction;

import static dev.coder2195.stellarity.util.LootUtil.*;

public interface StellarityItemModifiers {
	ResourceKey<LootItemFunction> END_CITY_MAP = id("end_city_map");
	ResourceKey<LootItemFunction> CHAPEL_OF_LIGHT_MAP = id("chapel_of_light_map");
	ResourceKey<LootItemFunction> END_VILLAGE_MAP = id("end_village_map");

	private static ResourceKey<LootItemFunction> id(String id) {
		return Stellarity.key(Registries.ITEM_MODIFIER, id);
	}

	static void bootstrap(BootstrapContext<LootItemFunction> context) {
		var structures = context.lookup(Registries.STRUCTURE);

		context.register(END_CITY_MAP, sequence(
			explorationMap(StellarityMapDecorationTypes.END_CITY, structures.getOrThrow(StellarityStructureTags.ON_END_CITY_MAPS), (byte) 3, 96, true),
			setName(Component.translatable("filled_map.stellarity.end_city").setStyle(Style.EMPTY.withItalic(false)), SetNameFunction.Target.CUSTOM_NAME),
			setComponents(DataComponentPatch.builder().set(DataComponents.RARITY, Rarity.RARE).set(StellarityDataComponents.MARKED_ITEM, Unit.INSTANCE).build())
		).value());

		context.register(END_VILLAGE_MAP, sequence(explorationMap(StellarityMapDecorationTypes.END_VILLAGE, structures.getOrThrow(StellarityStructureTags.ON_END_VILLAGE_MAPS), (byte) 3, 96, false),
			setName(Component.translatable("filled_map.stellarity.end_village"), SetNameFunction.Target.CUSTOM_NAME),
			setComponents(DataComponentPatch.builder().set(StellarityDataComponents.MARKED_ITEM, Unit.INSTANCE).set(DataComponents.RARITY, Rarity.RARE).build())
		).value());

		context.register(CHAPEL_OF_LIGHT_MAP, sequence(
			explorationMap(StellarityMapDecorationTypes.CHAPEL_OF_LIGHT, structures.getOrThrow(StellarityStructureTags.ON_END_VILLAGE_MAPS), (byte) 3, 96, false),
			setName(Component.translatable("filled_map.stellarity.chapel_of_light").setStyle(Style.EMPTY.withItalic(false)), SetNameFunction.Target.CUSTOM_NAME),
			setComponents(DataComponentPatch.builder().set(DataComponents.RARITY, Rarity.RARE).set(StellarityDataComponents.MARKED_ITEM, Unit.INSTANCE).build())
		).value());

	}
}
