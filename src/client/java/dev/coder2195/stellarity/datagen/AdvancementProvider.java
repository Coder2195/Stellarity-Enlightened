package dev.coder2195.stellarity.datagen;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.datagen.tags.BiomeTagProvider;
import dev.coder2195.stellarity.registry.StellarityBlocks;
import dev.coder2195.stellarity.registry.StellarityCriteriaTriggers;
import dev.coder2195.stellarity.registry.StellarityItems;
import dev.coder2195.stellarity.registry.StellarityStructures;
import dev.coder2195.stellarity.registry.criterion_trigger.DashTrigger;
import dev.coder2195.stellarity.registry.criterion_trigger.SpecialCraftTrigger;
import dev.coder2195.stellarity.registry.criterion_trigger.VoidFishedTrigger;
import dev.coder2195.stellarity.registry.entity_sub_predicate.EntityAttributeModifiersPredicate;
import dev.coder2195.stellarity.registry.entity_sub_predicate.NbtNumberPredicate;
import dev.coder2195.stellarity.tags.StellarityDamageTypeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.*;
import net.minecraft.advancements.predicates.*;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.predicates.entity.EntityTypePredicate;
import net.minecraft.advancements.predicates.entity.PlayerPredicate;
import net.minecraft.advancements.triggers.*;
import net.minecraft.commands.arguments.NbtPathArgument;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import org.jspecify.annotations.NonNull;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.stream.Stream;


public class AdvancementProvider extends FabricAdvancementProvider {


	public final AdvancementType TASK = AdvancementType.TASK;
	public final AdvancementType GOAL = AdvancementType.GOAL;
	public final AdvancementType CHALLENGE = AdvancementType.CHALLENGE;

	public AdvancementProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup);
	}

	public static AdvancementHolder dummy(Identifier id) {
		//noinspection DataFlowIssue
		return new AdvancementHolder(id, null);
	}

	public static AdvancementRequirements requires(String[][] array) {
		return new AdvancementRequirements(Arrays.stream(array).map(List::of).toList());
	}

	public static AdvancementRequirements requires(String... array) {
		return new AdvancementRequirements(Arrays.stream(array).map(List::of).toList());
	}

	public static Advancement.Builder advancement() {
		return Advancement.Builder.advancement();
	}

	public static Advancement.Builder recipe() {
		return Advancement.Builder.recipeAdvancement();
	}

	@Override
	public void generateAdvancement(HolderLookup.Provider registryLookup, @NonNull Consumer<AdvancementHolder> consumer) {
		final var items = registryLookup.lookupOrThrow(Registries.ITEM);
		final var entities = registryLookup.lookupOrThrow(Registries.ENTITY_TYPE);
		final var structures = registryLookup.lookupOrThrow(Registries.STRUCTURE);


		final var ENTER_END_GATEWAY = dummy(Stellarity.mcId("end/enter_end_gateway"));
		final var ENTER_END = dummy(Stellarity.mcId("story/enter_the_end"));
		final var END_ROOT = dummy(Stellarity.mcId("end/root"));
		final var KILL_DRAGON = dummy(Stellarity.mcId("end/kill_dragon"));
		final var ADVENTURE_ROOT = dummy(Stellarity.mcId("adventure/root"));
		final var KILL_A_MOB = dummy(Stellarity.mcId("adventure/kill_a_mob"));


		final var VOID_REELS = advancement()
			.display(StellarityItems.FISHER_OF_VOIDS,
				Component.translatable("advancements.stellarity.void_reels"), Component.translatable("advancements.stellarity.void_reels.description"),
				null, TASK, true, true, false
			)
			.parent(ENTER_END_GATEWAY)
			.addCriterion("fishing", VoidFishedTrigger.TriggerInstance.fishedItem(
				Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()
			)).requirements(requires(new String[][]{{"fishing"}}))
			.build(Stellarity.id("void_fishing/void_reels"));

		final var TOPPED_OFF = advancement()
			.display(
				StellarityItems.CRYSTAL_HEARTFISH,
				Component.translatable("advancements.stellarity.topped_off"), Component.translatable("advancements.stellarity.topped_off.description"),
				null, TASK, true, true, false
			)
			.parent(VOID_REELS)
			.addCriterion("eat", CriteriaTriggers.CONSUME_ITEM.createCriterion(new ConsumeItemTrigger.TriggerInstance(
				Optional.of(EntityPredicate.wrap(EntityPredicate.Builder.entity().put(EntityAttributeModifiersPredicate.CODEC, new EntityAttributeModifiersPredicate(List.of(
					new EntityAttributeModifiersPredicate.EntryPredicate(
						HolderSet.direct(Attributes.MAX_HEALTH), Optional.of(Stellarity.id("crystal_heartfish")),
						MinMaxBounds.Doubles.atLeast(10), Optional.empty()
					)
				))))),
				Optional.of(ItemPredicate.Builder.item().of(items, StellarityItems.CRYSTAL_HEARTFISH).build())
			)))
			.requirements(requires("eat"))
			.build(Stellarity.id("void_fishing/topped_off"));


		final var FIND_DUSKBERRY = advancement()
			.display(
				StellarityItems.DUSKBERRY,
				Component.translatable("advancements.stellarity.duskberry_find"), Component.translatable("advancements.stellarity.duskberry_find.description"),
				null, TASK, true, true, false
			)
			.parent(ENTER_END)
			.addCriterion("get_item", InventoryChangeTrigger.TriggerInstance.hasItems(StellarityItems.DUSKBERRY))
			.requirements(requires("get_item"))
			.build(Stellarity.id("exploration/duskberry/find"));

		final var POOR_LIFE_CHOICES = advancement()
			.display(
				StellarityItems.DUSKBERRY,
				Component.translatable("advancements.stellarity.poor_life_choices"), Component.translatable("advancements.stellarity.poor_life_choices.description"),
				null, CHALLENGE, true, true, false
			)
			.addCriterion("eat", ConsumeItemTrigger.TriggerInstance.usedItem(items, StellarityItems.DUSKBERRY))
			.addCriterion("feed", PlayerInteractTrigger.TriggerInstance.itemUsedOnEntity(ItemPredicate.Builder.item().of(items, StellarityItems.DUSKBERRY),
				Optional.of(ContextAwarePredicate.create(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(entities, EntityTypes.FOX).build()).build())
				)))
			.addCriterion("place", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(StellarityBlocks.DUSKBERRY_BUSH))
			.parent(FIND_DUSKBERRY)
			.requirements(requires("eat", "feed", "place"))
			.build(Stellarity.id("exploration/duskberry/poor_life_choices"));

		final var SACRIFICAL_RITUAL = advancement().display(
				Items.END_CRYSTAL,
				Component.translatable("advancements.stellarity.sacrificial_ritual"), Component.translatable("advancements.stellarity.sacrificial_ritual.description"),
				null, GOAL, true, true, false
			).parent(END_ROOT)
			.addCriterion("summon", SummonedEntityTrigger.TriggerInstance.summonedEntity(new EntityPredicate.Builder().entityType(EntityTypePredicate.of(entities, EntityTypes.ENDER_DRAGON))))
			.requirements(requires("summon"))
			.build(Stellarity.id("ender_dragon/sacrificial_ritual"));

		final var RESPAWN_DRAGON = advancement().display(
				Items.END_CRYSTAL,
				Component.translatable("advancements.end.respawn_dragon.title"), Component.translatable("advancements.end.respawn_dragon.description"),
				null, TASK, true, true, false
			).parent(KILL_DRAGON)
			.addCriterion("summon", CriteriaTriggers.SUMMONED_ENTITY.createCriterion(new SummonedEntityTrigger.TriggerInstance(
				Optional.of(EntityPredicate.wrap(new EntityPredicate.Builder().player(
					PlayerPredicate.Builder.player().checkAdvancementDone(Stellarity.id("ender_dragon/sacrificial_ritual"), true).build()
				))),
				Optional.of(EntityPredicate.wrap(new EntityPredicate.Builder().entityType(EntityTypePredicate.of(entities, EntityTypes.ENDER_DRAGON))))
			)))
			.requirements(
				requires("summon")
			).build(Stellarity.id("end/respawn_dragon"));

		final var ALTAR_OF_THE_ACCURSED_INTRO = advancement().display(
				StellarityItems.ENDONOMICON,
				Component.translatable("advancements.stellarity.altar_of_the_accursed_intro"), Component.translatable("advancements.stellarity.altar_of_the_accursed_intro.description"),
				null, GOAL, true, true, false
			).parent(KILL_DRAGON)
			.addCriterion("craft", SpecialCraftTrigger.triggerInstance(
				Optional.empty(),
				Optional.of(ContextAwarePredicate.create(new LootItemBlockStatePropertyCondition.Builder(StellarityBlocks.ALTAR_OF_THE_ACCURSED).build())),
				Optional.of(ItemPredicate.Builder.item().of(items, StellarityItems.ENDONOMICON).build())
			))
			.requirements(requires("craft"))
			.build(Stellarity.id("altar_of_the_accursed/altar_of_the_accursed_intro"));

		final var CURSED_CRAFTING = advancement().display(
				StellarityItems.ALTAR_OF_THE_ACCURSED,
				Component.translatable("advancements.stellarity.cursed_crafting"), Component.translatable("advancements.stellarity.cursed_crafting.description"),
				null, GOAL, true, true, false
			).parent(ALTAR_OF_THE_ACCURSED_INTRO)
			.addCriterion("craft", SpecialCraftTrigger.triggerInstance(
				Optional.empty(),
				Optional.of(ContextAwarePredicate.create(
					new LootItemBlockStatePropertyCondition.Builder(StellarityBlocks.ALTAR_OF_THE_ACCURSED).build()
				)),
				Optional.empty()
			))
			.requirements(requires("craft"))
			.build(Stellarity.id("altar_of_the_accursed/cursed_crafting"));

		final var CRAFT_FULL_SHULKER_ARMOR = advancement().display(
				StellarityItems.SHULKER_CHESTPLATE,
				Component.translatable("advancements.stellarity.craft_full_shulker_armor"), Component.translatable("advancements.stellarity.craft_full_shulker_armor.description"),
				null, CHALLENGE, true, true, false
			).parent(CURSED_CRAFTING)
			.addCriterion("helmet", InventoryChangeTrigger.TriggerInstance.hasItems(StellarityItems.SHULKER_HELMET))
			.addCriterion("chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(StellarityItems.SHULKER_CHESTPLATE))
			.addCriterion("leggings", InventoryChangeTrigger.TriggerInstance.hasItems(StellarityItems.SHULKER_LEGGINGS))
			.addCriterion("boots", InventoryChangeTrigger.TriggerInstance.hasItems(StellarityItems.SHULKER_BOOTS))
			.requirements(requires("helmet", "chestplate", "leggings", "boots"))
			.build(Stellarity.id("altar_of_the_accursed/craft_full_shulker_armor"));

		final var ELECTRIFIED = advancement().display(
				StellarityItems.COPPER_ELEKTRA_SHIELD,
				Component.translatable("advancements.stellarity.electrified"), Component.translatable("advancements.stellarity.electrified.description"),
				null, TASK, true, true, false
			).parent(ADVENTURE_ROOT)
			.addCriterion("dash", StellarityCriteriaTriggers.DASH.createCriterion(new DashTrigger.TriggerInstance(
				Optional.empty(), List.of(), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.atLeast(5),
				Optional.of(ItemPredicate.Builder.item().of(items, StellarityItems.COPPER_ELEKTRA_SHIELD).build())
			)))
			.requirements(requires("dash"))
			.build(Stellarity.id("adventure/electrified"));

		final var BLOOD_FOR_BLOOD = advancement().display(
				StellarityItems.TAMARIS,
				Component.translatable("advancements.stellarity.blood_for_blood"), Component.translatable("advancements.stellarity.blood_for_blood.description"),
				null, TASK, true, true, false
			).parent(CURSED_CRAFTING)
			.addCriterion("kill", KilledTrigger.TriggerInstance.playerKilledEntity(Optional.empty(), new DamageSourcePredicate.Builder().tag(
				new TagPredicate<>(StellarityDamageTypeTags.BLOOD_FOR_BLOOD, true)
			)))
			.requirements(requires("kill"))
			.build(Stellarity.id("altar_of_the_accursed/blood_for_blood"));

		final var NIGHT_SKY_STALKERS = advancement().display(
				Items.PHANTOM_MEMBRANE,
				Component.translatable("advancements.stellarity.night_sky_stalkers"), Component.translatable("advancements.stellarity.night_sky_stalkers.description"),
				null, TASK, true, true, false
			).parent(KILL_A_MOB)
			.addCriterion("kill", KilledTrigger.TriggerInstance.playerKilledEntity(new EntityPredicate.Builder().of(entities, EntityTypes.PHANTOM)))
			.requirements(requires("kill"))
			.build(Stellarity.id("adventure/night_sky_stalkers"));

		Map<NbtPathArgument.NbtPath, MinMaxBounds.Doubles> largePhantomData = new HashMap<>();
		try {
			largePhantomData.put(NbtPathArgument.NbtPath.of("size"), MinMaxBounds.Doubles.atLeast(2));
		} catch (CommandSyntaxException e) {
			throw new RuntimeException(e);
		}
		final var KILL_LARGE_PHANTOM = advancement().display(
				Items.PHANTOM_SPAWN_EGG,
				Component.translatable("advancements.stellarity.kill_large_phantom"), Component.translatable("advancements.stellarity.kill_large_phantom.description"),
				null, TASK, true, true, false
			).parent(NIGHT_SKY_STALKERS)
			.addCriterion("kill_large", KilledTrigger.TriggerInstance.playerKilledEntity(
				new EntityPredicate.Builder().of(entities, EntityTypes.PHANTOM).put(NbtNumberPredicate.CODEC, new NbtNumberPredicate(largePhantomData))
			))
			.requirements(requires("kill_large"))
			.build(Stellarity.id("adventure/kill_large_phantom"));

		var DISCOVER_ALL_BIOMES_BUILDER = advancement().display(
			Items.LEATHER_BOOTS,
			Component.translatable("advancements.stellarity.discover_all_biomes"), Component.translatable("advancements.stellarity.discover_all_biomes.description"),
			null, CHALLENGE, true, true, false
		).parent(ENTER_END_GATEWAY).rewards(new AdvancementRewards.Builder().addExperience(4500).build());
		List<String> biomes = new ArrayList<>();
		for (var biome : (Iterable<ResourceKey<Biome>>) () -> Stream.concat(BiomeTagProvider.outerVanilla(), BiomeTagProvider.stellarity()).iterator()) {
			var name = biome.identifier().getPath();
			biomes.add(name);
			DISCOVER_ALL_BIOMES_BUILDER.addCriterion(name, PlayerTrigger.TriggerInstance.located(new LocationPredicate.Builder().setBiomes(HolderSet.direct(registryLookup.getOrThrow(biome)))));
		}
		var DISCOVER_ALL_BIOMES = DISCOVER_ALL_BIOMES_BUILDER.requirements(requires(biomes.toArray(String[]::new))).build(Stellarity.id("exploration/discover_all_biomes"));

		var FIND_END_VILLAGE = advancement().display(
				StellarityItems.ENDERITE_SHARD,
				Component.translatable("advancements.stellarity.find_end_village"), Component.translatable("advancements.stellarity.find_end_village.description"),
				null, TASK, true, true, false
			).parent(ENTER_END_GATEWAY)
			.addCriterion("find_village", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structures.getOrThrow(StellarityStructures.VILLAGE))))
			.requirements(requires("find_village")).build(Stellarity.id("exploration/find_end_village"));


		for (var advancement : List.of(
			VOID_REELS, TOPPED_OFF, FIND_DUSKBERRY, POOR_LIFE_CHOICES, SACRIFICAL_RITUAL, RESPAWN_DRAGON,
			CURSED_CRAFTING, CRAFT_FULL_SHULKER_ARMOR, ALTAR_OF_THE_ACCURSED_INTRO, ELECTRIFIED, BLOOD_FOR_BLOOD,
			NIGHT_SKY_STALKERS, KILL_LARGE_PHANTOM, DISCOVER_ALL_BIOMES, FIND_END_VILLAGE
		)) {
			consumer.accept(advancement);
		}
	}

	private Criterion<ImpossibleTrigger.TriggerInstance> impossible() {
		return CriteriaTriggers.IMPOSSIBLE.createCriterion(new ImpossibleTrigger.TriggerInstance());
	}
}
