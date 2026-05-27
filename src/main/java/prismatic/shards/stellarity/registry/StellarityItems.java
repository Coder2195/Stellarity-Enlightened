package prismatic.shards.stellarity.registry;

import com.google.common.collect.Streams;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.TeleportRandomlyConsumeEffect;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.key.StellarityMobVariants;
import prismatic.shards.stellarity.key.StellarityItemIds;
import prismatic.shards.stellarity.key.StellarityJukeboxSongs;
import prismatic.shards.stellarity.registry.item.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface StellarityItems {
	/**
	 * Generated from Datapack python script thingy
	 * BOOK_OF_CONVEYANCE uncommon
	 * BOOK_OF_JINX uncommon
	 * BOOK_OF_LIGHT uncommon
	 * BOOK_OF_OBSTRUCT uncommon
	 * BOOK_OF_RETURN uncommon
	 * BOOK_OF_UPDRAFT uncommon
	 * CALL_OF_THE_VOID epic
	 * CHAMPION_BOOTS uncommon
	 * CHAMPION_CHESTPLATE uncommon
	 * CHAMPION_HELMET uncommon
	 * CHAMPION_LEGGINGS uncommon
	 * COPPER_ELEKTRA_SHIELD uncommon
	 * CREST_OF_THE_END rare
	 * DRAGONBLADE epic
	 * DRAGON_WINGS rare
	 * DUSKBERRY epic
	 * EMPRESS_WINGS epic
	 * ENDERITE_SMITHING_TEMPLATE rare
	 * ENDERMANS_HAND uncommon
	 * FISHER_OF_VOIDS uncommon
	 * FLORAL_BOOTS uncommon
	 * FLORAL_CHESTPLATE uncommon
	 * FLORAL_HELMET uncommon
	 * FLORAL_LEGGINGS uncommon
	 * FLUFFY_HAMMER rare
	 * GOLDEN_CHORUS_FRUIT rare
	 * HALLOWED_BOOTS uncommon
	 * HALLOWED_CHESTPLATE uncommon
	 * HALLOWED_HELMET uncommon
	 * HALLOWED_LEGGINGS uncommon
	 * LIFE_CRYSTAL rare
	 * PHANTOM_WINGS uncommon
	 * PHO rare
	 * PRISMATIC_PUNCH epic
	 * PRISMATIC_SHIELD epic
	 * PRISMATIC_SUSHI uncommon
	 * PRISMEMBER epic
	 * PRISMITE uncommon
	 * REINFORCED_HORSE_ARMOR uncommon
	 * SHARANGA uncommon
	 * SHULKER_BOOTS uncommon
	 * SHULKER_CHESTPLATE uncommon
	 * SHULKER_HELMET uncommon
	 * SHULKER_LEGGINGS uncommon
	 * SLAYER_CROSSBOW rare
	 * SOARING_INSIGNIA epic
	 * SPECTRAL_FURY uncommon
	 * STARSTRUCK_SHIELD epic
	 * TAMARIS rare
	 * THE_BEGINNING uncommon
	 * THE_END uncommon
	 */
	Item ENDER_DIRT = registerBlock(StellarityItemIds.ENDER_DIRT, StellarityBlocks.ENDER_DIRT);
	Item ENDER_GRASS_BLOCK = registerBlock(StellarityItemIds.ENDER_GRASS_BLOCK, StellarityBlocks.ENDER_GRASS_BLOCK);
	Item ASHEN_FROGLIGHT = registerBlock(StellarityItemIds.ASHEN_FROGLIGHT, StellarityBlocks.ASHEN_FROGLIGHT);
	Item ROOTED_ENDER_DIRT = registerBlock(StellarityItemIds.ROOTED_ENDER_DIRT, StellarityBlocks.ROOTED_ENDER_DIRT);
	Item ENDER_DIRT_PATH = registerBlock(StellarityItemIds.ENDER_DIRT_PATH, StellarityBlocks.ENDER_DIRT_PATH);
	Item ALTAR_OF_THE_ACCURSED = registerBlock(StellarityItemIds.ALTAR_OF_THE_ACCURSED, StellarityBlocks.ALTAR_OF_THE_ACCURSED);
	Item ENDERITE_BLOCK = registerBlock(StellarityItemIds.ENDERITE_BLOCK, StellarityBlocks.ENDERITE_BLOCK);
	Item COARSE_ENDER_DIRT = registerBlock(StellarityItemIds.COARSE_ENDER_DIRT, StellarityBlocks.COARSE_ENDER_DIRT);

	Item CALL_OF_THE_VOID = register(StellarityItemIds.CALL_OF_THE_VOID, CallOfTheVoid::new, CallOfTheVoid.PROPERTIES);
	Item FISHER_OF_VOIDS = register(StellarityItemIds.FISHER_OF_VOIDS, FishingRodItem::new, new Item.Properties().stacksTo(1).durability(100).rarity(Rarity.UNCOMMON));

	Item SUSHI = register(StellarityItemIds.SUSHI, basicFood(4, 2.4f));
	Item GOLDEN_CHORUS_FRUIT = register(StellarityItemIds.GOLDEN_CHORUS_FRUIT, tpFoodProperties(6, 14.4f, true, 300).rarity(Rarity.RARE));
	Item FRIED_CHORUS_FRUIT = register(StellarityItemIds.FRIED_CHORUS_FRUIT, tpFoodProperties(7, 11.2f, 32));
	Item FROZEN_CARPACCIO = register(StellarityItemIds.FROZEN_CARPACCIO, FrozenCarpaccio::new, FrozenCarpaccio.PROPERTIES);
	Item ENDERMAN_FLESH = register(StellarityItemIds.ENDERMAN_FLESH, tpFoodProperties(4, 0.8f, 16, new StellarityItems.EffectChance(new MobEffectInstance(MobEffects.HUNGER, 40 * 20, 0), 0.8f)));
	Item CRYSTAL_HEARTFISH = register(StellarityItemIds.CRYSTAL_HEARTFISH, CrystalHeartfish::new, CrystalHeartfish.PROPERTIES);
	Item GRILLED_ENDERMAN_FLESH = register(StellarityItemIds.GRILLED_ENDERMAN_FLESH, basicFood(6, 9.6f));
	Item FLAREFIN_KOI = register(StellarityItemIds.FLAREFIN_KOI, foodProperties(4, 0.8f, new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 16 * 20)));
	Item AMETHYST_BUDFISH = register(StellarityItemIds.AMETHYST_BUDFISH);
	Item CRIMSON_TIGERFISH = register(StellarityItemIds.CRIMSON_TIGERFISH, foodProperties(1, 0.2f,
		new MobEffectInstance(MobEffects.HUNGER, 30 * 20),
		new MobEffectInstance(MobEffects.POISON, 20 * 20)));
	Item ENDER_KOI = register(StellarityItemIds.ENDER_KOI, basicFood(1, 0.6f));
	Item FLESHY_PIRANHA = register(StellarityItemIds.FLESHY_PIRANHA, foodProperties(1, 0.2f,
		new MobEffectInstance(MobEffects.HUNGER, 30 * 20),
		new MobEffectInstance(MobEffects.POISON, 20 * 20)
	));
	Item BUBBLEFISH = register(StellarityItemIds.BUBBLEFISH, foodProperties(0, 0, new MobEffectInstance(MobEffects.WATER_BREATHING, 20 * 20)));
	Item PRISMITE = register(StellarityItemIds.PRISMITE, foodProperties(3, 1.8f, new MobEffectInstance(MobEffects.REGENERATION, 5 * 20)).rarity(Rarity.UNCOMMON));
	Item OVERGROWN_COD = register(StellarityItemIds.OVERGROWN_COD, Item::new,
		foodProperties(1, 0.2f, new MobEffectInstance(MobEffects.SLOWNESS, 3 * 20, 2)));
	Item SHULKER_BODY = register(StellarityItemIds.SHULKER_BODY, tpFoodProperties(4, 0.8f, true, 16, new StellarityItems.EffectChance(new MobEffectInstance(MobEffects.HUNGER, 40 * 20, 0), 0.3f)));
	Item PRISMATIC_SUSHI = register(StellarityItemIds.PRISMATIC_SUSHI, foodProperties(4, 2.4f, true, new MobEffectInstance(MobEffects.HEALTH_BOOST, 40 * 20)).rarity(Rarity.UNCOMMON));
	Item SHEPHERDS_PIE = register(StellarityItemIds.SHEPHERDS_PIE, Item::new,
		foodProperties(20, 20f, true,
			new MobEffectInstance(MobEffects.HEALTH_BOOST, 20, 2),
			new MobEffectInstance(MobEffects.REGENERATION, 64 * 20, 1)
		));
	Item CHORUS_PIE = register(StellarityItemIds.CHORUS_PIE, foodProperties(8, 4.8f));
	Item PHANTOM_ITEM_FRAME = register(StellarityItemIds.PHANTOM_ITEM_FRAME, PhantomItemFrameItem::new, PhantomItemFrameItem.PROPERTIES);
	Item PHO = register(StellarityItemIds.PHO, Item::new, foodProperties(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).craftRemainder(Items.BOWL),
		new FoodProperties.Builder(), Consumables.defaultFood(), 13, 20f, true,
		new MobEffectInstance(MobEffects.ABSORPTION, 150 * 20),
		new MobEffectInstance(MobEffects.STRENGTH, 150 * 20),
		new MobEffectInstance(MobEffects.REGENERATION, 32 * 20)
	).usingConvertsTo(Items.BOWL));
	Item FROST_MINNOW = register(StellarityItemIds.FROST_MINNOW, foodProperties(1, 0.2f,
		new MobEffectInstance(MobEffects.SLOWNESS, 20 * 20)
	));
	Item GOOSH = register(StellarityItemIds.GOOSH, foodProperties(1, 0,
		new MobEffectInstance(MobEffects.OOZING, 59 * 20),
		new MobEffectInstance(MobEffects.JUMP_BOOST, 15 * 20),
		new MobEffectInstance(MobEffects.NAUSEA, 5 * 20)
	));
	Item CHORUS_STEW = register(StellarityItemIds.CHORUS_STEW, Item::new, foodProperties(new Item.Properties().stacksTo(1).craftRemainder(Items.BOWL),
		new FoodProperties.Builder(), Consumables.defaultFood().consumeSeconds(1.6f), 13, 20f, true,
		new MobEffectInstance(MobEffects.ABSORPTION, 150 * 20),
		new MobEffectInstance(MobEffects.STRENGTH, 150 * 20),
		new MobEffectInstance(MobEffects.REGENERATION, 32 * 20)
	).usingConvertsTo(Items.BOWL));

	Item TAMARIS = register(StellarityItemIds.TAMARIS, Tamaris::new, Tamaris.PROPERTIES);
	Item CHORUS_PLATING = register(StellarityItemIds.CHORUS_PLATING);
	Item ENDERITE_SHARD = register(StellarityItemIds.ENDERITE_SHARD);
	Item ENDERITE_UPGRADE_SMITHING_TEMPLATE = register(StellarityItemIds.ENDERITE_UPGRADE_SMITHING_TEMPLATE, (properties) -> new SmithingTemplateItem(
		Component.translatable("item.stellarity.enderite_upgrade_smithing_template.applies_to").withStyle(ChatFormatting.BLUE),
		Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients", Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients.count.4"), Component.translatable("item.stellarity.hallowed_ingot")).withStyle(ChatFormatting.BLUE),
		Component.translatable("item.stellarity.enderite_upgrade_smithing_template.upgrade").withStyle(ChatFormatting.GRAY),
		Component.empty(),
		List.of(),
		List.of(), properties
	) {
		@Override
		public void appendHoverText(@NonNull ItemStack itemStack, @NonNull TooltipContext tooltipContext, @NonNull TooltipDisplay tooltipDisplay, @NonNull Consumer<Component> consumer, @NonNull TooltipFlag tooltipFlag) {
			super.appendHoverText(itemStack, tooltipContext, tooltipDisplay, consumer, tooltipFlag);
			consumer.accept(CommonComponents.space().append(Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients", Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients.count.4"), Component.translatable("item.stellarity.chorus_plating")).withStyle(ChatFormatting.BLUE)));
			consumer.accept(CommonComponents.space().append(Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients", Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients.count.4"), Component.translatable("item.minecraft.shulker_shell")).withStyle(ChatFormatting.BLUE)));
			consumer.accept(CommonComponents.space().append(Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients", Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients.count.8"), Component.translatable("block.minecraft.cherry_leaves")).withStyle(ChatFormatting.BLUE)));
		}
	}, new Item.Properties().rarity(Rarity.RARE));

	Item HALLOWED_INGOT = register(StellarityItemIds.HALLOWED_INGOT);
	Item SAND_RUNE = register(StellarityItemIds.SAND_RUNE);
	Item STARLIGHT_SOOT = register(StellarityItemIds.STARLIGHT_SOOT);
	Item GILDED_PURPUR_KEY = register(StellarityItemIds.GILDED_PURPUR_KEY);
	Item PURPUR_KEY = register(StellarityItemIds.PURPUR_KEY);
	Item WINGED_KEY = register(StellarityItemIds.WINGED_KEY);

	Item PRISMATIC_PEARL = register(StellarityItemIds.PRISMATIC_PEARL, PrismaticPearlItem::new, PrismaticPearlItem.PROPERTIES);
	Item ENDONOMICON = register(StellarityItemIds.ENDONOMICON, Endonomicon::new, Endonomicon.PROPERTIES);

	Item MUSIC_DISC_DEVIANTS_LIGHT_MUSIC_BOX = register(StellarityItemIds.MUSIC_DISC_DEVIANTS_LIGHT_MUSIC_BOX,
		Item::new, new Item.Properties().stacksTo(1).jukeboxPlayable(StellarityJukeboxSongs.DEVIANTS_LIGHT_MUSIC_BOX)
	);
	Item MUSIC_DISC_FIRES_OF_HOKKAI = register(StellarityItemIds.MUSIC_DISC_FIRES_OF_HOKKAI,
		Item::new, new Item.Properties().stacksTo(1).jukeboxPlayable(StellarityJukeboxSongs.FIRES_OF_HOKKAI)
	);
	Item MUSIC_DISC_PRECIPICE_STEREO = register(StellarityItemIds.MUSIC_DISC_PRECIPICE_STEREO,
		Item::new, new Item.Properties().stacksTo(1).jukeboxPlayable(StellarityJukeboxSongs.PRECIPICE_STEREO)
	);


	Supplier<ItemStack> AMARENE_POTION = createPotion(StellarityPotions.AMARENE);

	Supplier<ItemStack> BLIND_RAGE_POTION = createPotion(StellarityPotions.BLIND_RAGE);
	Supplier<ItemStack> LONG_BLIND_RAGE_POTION = createPotion(StellarityPotions.LONG_BLIND_RAGE);

	Supplier<ItemStack> ENDURANCE_POTION = createPotion(StellarityPotions.ENDURANCE);
	Supplier<ItemStack> LONG_ENDURANCE_POTION = createPotion(StellarityPotions.LONG_ENDURANCE);
	Supplier<ItemStack> STRONG_ENDURANCE_POTION = createPotion(StellarityPotions.STRONG_ENDURANCE);

	Supplier<ItemStack> ENTANGLEMENT_POTION = createSplashPotion(StellarityPotions.ENTANGLEMENT);
	Supplier<ItemStack> LONG_ENTANGLEMENT_POTION = createSplashPotion(StellarityPotions.LONG_ENTANGLEMENT);
	Supplier<ItemStack> STRONG_ENTANGLEMENT_POTION = createSplashPotion(StellarityPotions.STRONG_ENTANGLEMENT);

	Supplier<ItemStack> FROST_CLOUD_POTION = createLingeringPotion(StellarityPotions.FROST_CLOUD);

	Supplier<ItemStack> HELLFIRE_TREADER_POTION = createPotion(StellarityPotions.HELLFIRE_TREADER);
	Supplier<ItemStack> LONG_HELLFIRE_TREADER_POTION = createPotion(StellarityPotions.LONG_HELLFIRE_TREADER);
	Supplier<ItemStack> STRONG_HELLFIRE_TREADER_POTION = createPotion(StellarityPotions.STRONG_HELLFIRE_TREADER);

	Supplier<ItemStack> LIFEFORCE_POTION = createPotion(StellarityPotions.LIFEFORCE);
	Supplier<ItemStack> LONG_LIFEFORCE_POTION = createPotion(StellarityPotions.LONG_LIFEFORCE);
	Supplier<ItemStack> STRONG_LIFEFORCE_POTION = createPotion(StellarityPotions.STRONG_LIFEFORCE);

	Supplier<ItemStack> SPELUNKER_POTION = createPotion(StellarityPotions.SPELUNKER);
	Supplier<ItemStack> LONG_SPELUNKER_POTION = createPotion(StellarityPotions.LONG_SPELUNKER);
	Supplier<ItemStack> STRONG_SPELUNKER_POTION = createPotion(StellarityPotions.STRONG_SPELUNKER);

	Supplier<ItemStack> POSEIDONS_NECTAR_POTION = createPotion(StellarityPotions.POSEIDONS_NECTAR);
	Supplier<ItemStack> RED_POTION = createPotion(StellarityPotions.RED);

	Supplier<ItemStack> REGENERAGA_POTION = createPotion(StellarityPotions.REGENERAGA);
	Supplier<ItemStack> LONG_REGENERAGA_POTION = createPotion(StellarityPotions.LONG_REGENERAGA);
	Supplier<ItemStack> STRONG_REGENERAGA_POTION = createPotion(StellarityPotions.STRONG_REGENERAGA);

	Supplier<ItemStack> LUCK_POTION = createPotion(StellarityPotions.LUCK);

	Supplier<ItemStack> CHORUS_JUICE = createPotion(StellarityPotions.CHORUS_JUICE);

	Item ROYAL_JELLY = register(StellarityItemIds.ROYAL_JELLY, RoyalJelly::new, foodProperties(RoyalJelly.PROPERTIES, new FoodProperties.Builder(), Consumables.defaultFood().sound(SoundEvents.HONEY_DRINK), 6, 3.6f, true,
		new MobEffectInstance(MobEffects.ABSORPTION, 60 * 20)
	).usingConvertsTo(Items.GLASS_BOTTLE));

	Item ROYAL_JELLY_II = register(StellarityItemIds.ROYAL_JELLY_II, RoyalJelly::new, foodProperties(RoyalJelly.PROPERTIES, new FoodProperties.Builder(), Consumables.defaultFood(), 6, 3.6f, true,
		new MobEffectInstance(MobEffects.ABSORPTION, 30 * 20, 2)
	).usingConvertsTo(Items.GLASS_BOTTLE));

	Item SATCHEL_OF_VOIDS = register(StellarityItemIds.SATCHEL_OF_VOIDS, SatchelOfVoids::new, SatchelOfVoids.PROPERTIES);
	Item DUSKBERRY = register(StellarityItemIds.DUSKBERRY, Duskberry::new, Duskberry.PROPERTIES);

	Item SHULKER_HELMET = register(StellarityItemIds.SHULKER_HELMET, new Item.Properties().rarity(Rarity.UNCOMMON).humanoidArmor(StellarityArmorMaterials.SHULKER, ArmorType.HELMET).attributes(StellarityArmorMaterials.createShulkerAttributes(StellarityArmorMaterials.SHULKER, ArmorType.HELMET)));
	Item SHULKER_CHESTPLATE = register(StellarityItemIds.SHULKER_CHESTPLATE, new Item.Properties().rarity(Rarity.UNCOMMON).humanoidArmor(StellarityArmorMaterials.SHULKER, ArmorType.CHESTPLATE).attributes(StellarityArmorMaterials.createShulkerAttributes(StellarityArmorMaterials.SHULKER, ArmorType.CHESTPLATE)));
	Item SHULKER_LEGGINGS = register(StellarityItemIds.SHULKER_LEGGINGS, new Item.Properties().rarity(Rarity.UNCOMMON).humanoidArmor(StellarityArmorMaterials.SHULKER, ArmorType.LEGGINGS).attributes(StellarityArmorMaterials.createShulkerAttributes(StellarityArmorMaterials.SHULKER, ArmorType.LEGGINGS)));
	Item SHULKER_BOOTS = register(StellarityItemIds.SHULKER_BOOTS, new Item.Properties().rarity(Rarity.UNCOMMON).humanoidArmor(StellarityArmorMaterials.SHULKER, ArmorType.BOOTS).attributes(StellarityArmorMaterials.createShulkerAttributes(StellarityArmorMaterials.SHULKER, ArmorType.BOOTS)));

	Item ENDER_EGG = register(StellarityItemIds.ENDER_EGG, EggItem::new, new Item.Properties().stacksTo(16).delayedHolderComponent(DataComponents.CHICKEN_VARIANT, StellarityMobVariants.CHICKEN_END));

	Item VOIDED_ZOMBIE_SPAWN_EGG = registerSpawnEgg(StellarityItemIds.VOIDED_ZOMBIE_SPAWN_EGG, StellarityEntityTypes.VOIDED_ZOMBIE);
	Item VOIDED_SKELETON_SPAWN_EGG = registerSpawnEgg(StellarityItemIds.VOIDED_SKELETON_SPAWN_EGG, StellarityEntityTypes.VOIDED_SKELETON);
	Item VOIDED_SILVERFISH_SPAWN_EGG = registerSpawnEgg(StellarityItemIds.VOIDED_SILVERFISH_SPAWN_EGG, StellarityEntityTypes.VOIDED_SILVERFISH);
	Item VOIDED_SLIME_SPAWN_EGG = registerSpawnEgg(StellarityItemIds.VOIDED_SLIME_SPAWN_EGG, StellarityEntityTypes.VOIDED_SLIME);
	Item FLESH_PIGLIN_SPAWN_EGG = registerSpawnEgg(StellarityItemIds.FLESH_PIGLIN_SPAWN_EGG, StellarityEntityTypes.FLESH_PIGLIN);

	Item POTASSIFISH = register(StellarityItemIds.POTASSIFISH, foodProperties(1, 0.6f, new MobEffectInstance(MobEffects.REGENERATION, 4 * 20)));
	Item SHARANGA = register(StellarityItemIds.SHARANGA, Sharanga::new, Sharanga.PROPERTIES);

	static Supplier<ItemStack> createPotion(Holder<Potion> potion) {
		return () -> PotionContents.createItemStack(Items.POTION, potion);
	}

	static Supplier<ItemStack> createSplashPotion(Holder<Potion> potion) {
		return () -> PotionContents.createItemStack(Items.SPLASH_POTION, potion);
	}

	static Supplier<ItemStack> createLingeringPotion(Holder<Potion> potion) {
		return () -> PotionContents.createItemStack(Items.LINGERING_POTION, potion);
	}

	static Item registerBlock(ResourceKey<Item> key, Block block) {
		return registerBlock(key, block, new Item.Properties());
	}

	static Item registerBlock(ResourceKey<Item> key, Block block, Item.Properties properties) {
		properties.useBlockDescriptionPrefix().setId(key);
		Item item = new BlockItem(block, properties);

		return Registry.register(BuiltInRegistries.ITEM, key, item);
	}

	private static Item registerSpawnEgg(ResourceKey<Item> key, final EntityType<?> type) {
		return register(key, SpawnEggItem::new, (new Item.Properties()).spawnEgg(type));
	}

	static Item register(ResourceKey<Item> key, Function<Item.Properties, Item> itemFactory) {
		return register(key, itemFactory, new Item.Properties());
	}

	static Item register(ResourceKey<Item> key) {
		return register(key, Item::new);
	}

	static Item register(ResourceKey<Item> key, Item.Properties properties) {

		return register(key, Item::new, properties);
	}

	static Item register(ResourceKey<Item> key, Function<Item.Properties, Item> itemFactory, Item.Properties properties) {
		properties.setId(key);
		Item item = itemFactory.apply(properties);

		Registry.register(BuiltInRegistries.ITEM, key, item);

		return item;
	}

	record EffectChance(MobEffectInstance effect, float chance) {
		public EffectChance(MobEffectInstance effect) {
			this(effect, 1.0f);
		}
	}


	static Item.Properties foodProperties(Item.Properties properties, FoodProperties.Builder foodProperties, Consumable.Builder consumable, int nutrition, float saturation, boolean alwaysEat, EffectChance... effectChances) {
		foodProperties = foodProperties
			.nutrition(nutrition)
			.saturationModifier(saturation);
		if (alwaysEat) {
			foodProperties = foodProperties.alwaysEdible();
		}

		for (EffectChance ec : effectChances) {
			consumable = consumable.onConsume(new ApplyStatusEffectsConsumeEffect(ec.effect, ec.chance));
		}
		return properties.food(foodProperties.build(), consumable.build());

	}


	static Item.Properties foodProperties(Item.Properties properties, FoodProperties.Builder foodProperties,
	                                      int nutrition, float saturation, boolean alwaysEat, EffectChance... effectChances) {
		return foodProperties(properties, foodProperties, Consumables.defaultFood(), nutrition, saturation, alwaysEat, effectChances);
	}


	static Item.Properties foodProperties(int nutrition, float saturation, boolean alwaysEat, EffectChance... effectChances) {
		return foodProperties(new Item.Properties(), new FoodProperties.Builder(), nutrition, saturation, alwaysEat, effectChances);
	}

	static Item.Properties foodProperties(int nutrition, float saturation, EffectChance... effectChances) {
		return foodProperties(nutrition, saturation, false, effectChances);
	}

	static Item.Properties foodProperties(int nutrition, float saturation, MobEffectInstance first, MobEffectInstance... effects) {
		return foodProperties(nutrition, saturation, false, Streams.concat(Stream.of(first), Arrays.stream(effects)).map(EffectChance::new).toArray(EffectChance[]::new));
	}

	static Item.Properties foodProperties(int nutrition, float saturation, boolean alwaysEat, MobEffectInstance first, MobEffectInstance... effects) {
		return foodProperties(nutrition, saturation, alwaysEat, Streams.concat(Stream.of(first), Arrays.stream(effects)).map(EffectChance::new).toArray(EffectChance[]::new));
	}

	static Item.Properties foodProperties(Item.Properties properties, FoodProperties.Builder foodProperties, Consumable.Builder consumable, int nutrition, float saturation, boolean alwaysEat, MobEffectInstance first, MobEffectInstance... effects) {
		return foodProperties(properties, foodProperties, consumable, nutrition, saturation, alwaysEat,
			Streams.concat(Stream.of(first), Arrays.stream(effects)).map(EffectChance::new).toArray(EffectChance[]::new)
		);
	}

	static Item.Properties tpFoodProperties(int nutrition, float saturation, boolean alwaysEat, int teleportDiameter, StellarityItems.EffectChance... effectChances) {
		return tpFoodProperties(
			Consumables.defaultFood(),
			nutrition,
			saturation,
			alwaysEat,
			teleportDiameter,
			effectChances
		);
	}

	static Item.Properties tpFoodProperties(Consumable.Builder consumable, int nutrition, float saturation, boolean alwaysEat, int teleportDiameter, StellarityItems.EffectChance... effectChances) {
		return foodProperties(
			new Item.Properties(),
			new FoodProperties.Builder(),
			consumable.onConsume(new TeleportRandomlyConsumeEffect(teleportDiameter)),
			nutrition,
			saturation,
			alwaysEat,
			effectChances
		);
	}


	static Item.Properties tpFoodProperties(int nutrition, float saturation, int teleportDiameter, StellarityItems.EffectChance... effectChances) {
		return tpFoodProperties(
			nutrition,
			saturation,
			false,
			teleportDiameter,
			effectChances
		);
	}

	static Item.Properties basicFood(int nutrition, float saturation) {
		return foodProperties(nutrition, saturation, false);
	}

	HashMap<Item, Integer> NAME_COLORS = new HashMap<>();

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Items");

		NAME_COLORS.put(ENDERITE_UPGRADE_SMITHING_TEMPLATE, ChatFormatting.LIGHT_PURPLE.getColor());
		NAME_COLORS.put(HALLOWED_INGOT, 0xD9E3ED);
		NAME_COLORS.put(CHORUS_PLATING, 0xA372C3);
//		NAME_COLORS.put(BOOK_OF_RETURN, 0x9C369F);
//		NAME_COLORS.put(BOOK_OF_UPDRAFT, 0xE0E0E0);
//		NAME_COLORS.put(BOOK_OF_CONVEYANCE, 0xA946E7);
//		NAME_COLORS.put(BOOK_OF_OBSTRUCT, 0xF816FF);
//		NAME_COLORS.put(BOOK_OF_JINX, 0xF816FF);
//		NAME_COLORS.put(BOOK_OF_LIGHT, 0xFFF300);
		NAME_COLORS.put(CHORUS_PIE, ChatFormatting.WHITE.getColor());
		NAME_COLORS.put(FROZEN_CARPACCIO, ChatFormatting.WHITE.getColor());
		NAME_COLORS.put(SUSHI, ChatFormatting.WHITE.getColor());
		NAME_COLORS.put(PHO, ChatFormatting.AQUA.getColor());
		NAME_COLORS.put(PRISMATIC_SUSHI, ChatFormatting.YELLOW.getColor());
		NAME_COLORS.put(GOLDEN_CHORUS_FRUIT, 0x55FFFF);
		NAME_COLORS.put(GRILLED_ENDERMAN_FLESH, ChatFormatting.WHITE.getColor());
		NAME_COLORS.put(CHORUS_STEW, ChatFormatting.WHITE.getColor());
//		NAME_COLORS.put(CANDIED_CHORUS_FRUIT, ChatFormatting.WHITE.getColor());
		NAME_COLORS.put(ENDERMAN_FLESH, ChatFormatting.WHITE.getColor());
		NAME_COLORS.put(SHULKER_BODY, ChatFormatting.WHITE.getColor());
		NAME_COLORS.put(FRIED_CHORUS_FRUIT, ChatFormatting.WHITE.getColor());
		NAME_COLORS.put(PRISMITE, ChatFormatting.YELLOW.getColor());
//		NAME_COLORS.put(ENDERMANS_HAND, 0xed8cff);
		NAME_COLORS.put(DUSKBERRY, 0xAB6AD1);
//		NAME_COLORS.put(COPPER_ELEKTRA_SHIELD, 0xE0976B);
//		NAME_COLORS.put(SOARING_INSIGNIA, 0xFF76D0);
//		NAME_COLORS.put(LIFE_CRYSTAL, 0x9936D6);
//		NAME_COLORS.put(STARSTRUCK_SHIELD, 0xFF76D0);
//		NAME_COLORS.put(RADIANT_JEWEL, 0xff5555);
//		NAME_COLORS.put(PRISMATIC_SHIELD, 0xFF76D0);
//		NAME_COLORS.put(CREST_OF_THE_END, 0x9936D6);
//		NAME_COLORS.put(SHULKER_HOE, 0x976A97);
//		NAME_COLORS.put(SHULKER_SWORD, 0x976A97);
//		NAME_COLORS.put(SHULKER_AXE, 0x976A97);
//		NAME_COLORS.put(SHULKER_SHOVEL, 0x976A97);
//		NAME_COLORS.put(SHULKER_PICKAXE, 0x976A97);
//		NAME_COLORS.put(SHULKER_SPEAR, 0x976A97);
		NAME_COLORS.put(FISHER_OF_VOIDS, 0x8865AF);
		NAME_COLORS.put(ROYAL_JELLY, ChatFormatting.YELLOW.getColor());
		NAME_COLORS.put(ROYAL_JELLY_II, ChatFormatting.YELLOW.getColor());
//		NAME_COLORS.put(REINFORCED_HORSE_ARMOR, 0x976A97);
		NAME_COLORS.put(SHULKER_HELMET, 0x976A97);
		NAME_COLORS.put(SHULKER_BOOTS, 0x976A97);
		NAME_COLORS.put(SHULKER_CHESTPLATE, 0x976A97);
		NAME_COLORS.put(SHULKER_LEGGINGS, 0x976A97);
//		NAME_COLORS.put(DRAGON_WINGS, 0x9936D6);
//		NAME_COLORS.put(PHANTOM_WINGS, ChatFormatting.YELLOW.getColor());
//		NAME_COLORS.put(EMPRESS_WINGS, 0xFF76D0);
//		NAME_COLORS.put(HALLOWED_HELMET, 0xFFDD52);
//		NAME_COLORS.put(HALLOWED_BOOTS, 0xFFDD52);
//		NAME_COLORS.put(HALLOWED_CHESTPLATE, 0xFFDD52);
//		NAME_COLORS.put(HALLOWED_LEGGINGS, 0xFFDD52);
//		NAME_COLORS.put(FLORAL_HELMET, 0xFC92D3);
//		NAME_COLORS.put(FLORAL_BOOTS, 0xFC92D3);
//		NAME_COLORS.put(FLORAL_CHESTPLATE, 0xFC92D3);
//		NAME_COLORS.put(FLORAL_LEGGINGS, 0xFC92D3);
//		NAME_COLORS.put(CHAMPION_HELMET, 0xA87CC4);
//		NAME_COLORS.put(CHAMPION_BOOTS, 0xA87CC4);
//		NAME_COLORS.put(CHAMPION_CHESTPLATE, 0xA87CC4);
//		NAME_COLORS.put(CHAMPION_LEGGINGS, 0xA87CC4);
//		NAME_COLORS.put(PRISMEMBER, 0xFF76D0);
//		NAME_COLORS.put(STELLAR_STRIKER, 0xFFF593);
//		NAME_COLORS.put(STARLESS_SCYTHE, 0xFFD70C);
//		NAME_COLORS.put(SLAYER_CROSSBOW, ChatFormatting.GOLD.getColor());
//		NAME_COLORS.put(HARVESTER, 0x4BC6FF);
//		NAME_COLORS.put(ANCIENT_WOODEN_SWORD, ChatFormatting.WHITE.getColor());
//		NAME_COLORS.put(PRISMATIC_PUNCH, 0xFF76D0);
//		NAME_COLORS.put(DRAGONBLADE, 0xCD6AFF);
//		NAME_COLORS.put(SPECTRAL_FURY, 0xBDF1FF);
//		NAME_COLORS.put(KALEIDOSCOPE, 0xFFCF37);
//		NAME_COLORS.put(THE_BEGINNING, 0xF5DC68);
//		NAME_COLORS.put(THE_END, 0xF5DC68);
		NAME_COLORS.put(CALL_OF_THE_VOID, ChatFormatting.DARK_PURPLE.getColor());
//		NAME_COLORS.put(SHARANGA, 0xFF6B6B);
		NAME_COLORS.put(TAMARIS, 0x9936D6);
//		NAME_COLORS.put(FLUFFY_HAMMER, 0x9936D6);
	}
}