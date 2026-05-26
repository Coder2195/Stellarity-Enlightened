package prismatic.shards.stellarity.registry;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionContents;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.util.tuple.Tuple2;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface StellarityTooltips {
	Component EMPTY_LINE = Component.literal("");
	Component STELLARITY = Component.translatable("Stellarity").withStyle(ChatFormatting.ITALIC).withStyle(Style.EMPTY.withColor(0xCC26FF));
	HashMap<Item, List<Tuple2<Component, Integer>>> TOOLTIPS = new HashMap<>();

	static void initTooltips() {
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Tooltips");
		initTooltips();

		ItemTooltipCallback.EVENT.register((
			itemStack, unused, unused2, list
		) -> {
			Item item = itemStack.getItem();
			boolean isStellarityPotion = item instanceof PotionItem &&
				Optional.ofNullable(itemStack.get(DataComponents.POTION_CONTENTS)).flatMap(PotionContents::potion).map(holder -> BuiltInRegistries.POTION.getKey(holder.value())).map((location) -> location.getNamespace().equals(Stellarity.MOD_ID)).orElse(false);
			var id = BuiltInRegistries.ITEM.getKey(item);

			if (!(id.getNamespace().equals(Stellarity.MOD_ID) || isStellarityPotion)) {
				return;
			}

			list.add(EMPTY_LINE);
			list.add(STELLARITY);
		});
	}
}
