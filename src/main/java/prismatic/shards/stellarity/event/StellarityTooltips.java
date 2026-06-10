package prismatic.shards.stellarity.event;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionContents;
import prismatic.shards.stellarity.Stellarity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static prismatic.shards.stellarity.key.StellarityBlockItemIds.ALTAR_OF_THE_SACRED;
import static prismatic.shards.stellarity.key.StellarityItemIds.*;


public interface StellarityTooltips {
	Descriptions DESCRIPTIONS = new Descriptions();

	class DescriptionBuilder {
		private Identifier id;
		private int lines;
		private boolean flavorText = false;
		private boolean tip = false;
		private Type type = Type.ITEM;

		enum Type {
			BLOCK,
			ITEM;

			@Override
			public String toString() {
				if (this.equals(BLOCK)) return "block";
				else return "item";
			}
		}

		public DescriptionBuilder(Identifier id, int lines) {
			this.id = id;
			this.lines = lines;
		}

		public DescriptionBuilder flavorText() {
			this.flavorText = true;
			return this;
		}

		public DescriptionBuilder tip() {
			this.tip = true;
			return this;
		}

		public DescriptionBuilder block() {
			this.type = Type.BLOCK;
			return this;
		}


		public List<Component> build() {
			ArrayList<Component> list = new ArrayList<>();

			if (lines > 0) list.add(Component.empty());
			String base = type.toString() + "." + id.getNamespace() + "." + id.getPath();
			if (lines == 1)
				list.add(Component.translatable(base + ".description").withColor(0xEEEEEE));
			else {
				for (int i = 1; i <= lines; i++)
					list.add(Component.translatable(base + ".description." + i).withColor(0xEEEEEE));
			}

			if (flavorText || tip) list.add(Component.empty());
			if (flavorText)
				list.add(Component.translatable(base + ".flavor_text").withStyle(ChatFormatting.ITALIC).withColor(0xEEEEEE));
			if (tip) {
				list.add(Component.translatable(base + ".tip").withStyle(ChatFormatting.ITALIC).withColor(0x727272));
			}

			return list;
		}

		public void build(BiConsumer<Identifier, List<Component>> consumer) {
			consumer.accept(id, build());
		}
	}

	class Descriptions extends HashMap<Identifier, List<Component>> {


		public Descriptions() {
			super();

			Stream.of(
				desc(ALTAR_OF_THE_SACRED.item(), 4).block().flavorText(),
				desc(ANCIENT_WOODEN_SWORD, 0).flavorText(),
				desc(BOOK_OF_CONVEYANCE, 3),
				desc(BOOK_OF_JINX, 3),
				desc(BOOK_OF_LIGHT, 3),
				desc(BOOK_OF_OBSTRUCT, 2),
				desc(BOOK_OF_RETURN, 1),
				desc(BOOK_OF_UPDRAFT, 3),
				desc(CALL_OF_THE_VOID, 2),
				desc(CHORUS_PETAL, 0).flavorText(),
				desc(CHORUS_PLATING, 1),
				desc(COPPER_ELEKTRA_SHIELD, 4),
				desc(CREST_OF_THE_END, 2),
				desc(CRYSTAL_HEARTFISH, 3),
				desc(DECAYED_CLOVER, 2),
				desc(DRAGON_WINGS, 2),
				desc(DRAGONBLADE, 1).flavorText().tip(),
				desc(DUSKBERRY, 4),
				desc(EMPRESS_WINGS, 2).flavorText(),
				desc(ENDERITE_SHARD, 1),
				desc(ENDERMANS_HAND, 0).flavorText(),
				desc(ENDERITE_UPGRADE_SMITHING_TEMPLATE, 1),
				desc(ENDONOMICON, 1).flavorText(),
				desc(FISHER_OF_VOIDS, 1),
				desc(FLUFFY_HAMMER, 2).flavorText(),
				desc(GILDED_PURPUR_KEY, 1),
				desc(GOOSH, 2).flavorText(),
				desc(HARVESTER, 12),
				desc(KALEIDOSCOPE, 3),
				desc(LIFE_CRYSTAL, 1),
				desc(PHANTOM_ITEM_FRAME, 1),
				desc(PHANTOM_WINGS, 1),
				desc(PRISMATIC_PUNCH, 3),
				desc(PRISMEMBER, 3),
				desc(PURPUR_KEY, 1),
				desc(SANDSTORM_TRIDENT, 1).flavorText(),
				desc(SATCHEL_OF_VOIDS, 5),
				desc(SHARANGA, 2),
				desc(SHEPHERDS_PIE, 0).flavorText(),
				desc(SHULKER_BODY, 0).flavorText(),
				desc(SLAYER_CROSSBOW, 2),
				desc(SOARING_INSIGNIA, 4),
				desc(SPECTRAL_FURY, 3),
				// the beginning and the end missing (spirit dagger)
				desc(STARLESS_SCYTHE, 2),
				desc(STARLIGHT_SOOT, 2),
				desc(STARSTRUCK_SHIELD, 1),
				desc(STELLAR_STRIKER, 3),
				desc(TAMARIS, 2),
				desc(VOID_LOCKET, 2),
				desc(WINGED_KEY, 1)
			).forEach(b -> b.build(this::put));


		}

		public DescriptionBuilder desc(ResourceKey<Item> item, int lines) {
			return new DescriptionBuilder(item.identifier(), lines);
		}

	}

	Component EMPTY_LINE = Component.empty();
	Component STELLARITY = Component.translatable("Stellarity").withStyle(ChatFormatting.ITALIC).withStyle(Style.EMPTY.withColor(0xCC26FF));


	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Tooltips");
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

			var descriptions = DESCRIPTIONS.get(id);
			if (descriptions != null) {
				int index = 1;
				for (var line : descriptions) {
					list.add(index, line);
					index++;
				}
			}

			list.add(EMPTY_LINE);
			list.add(STELLARITY);
		});
	}
}
