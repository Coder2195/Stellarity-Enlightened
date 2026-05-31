package prismatic.shards.stellarity.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.predicates.entity.EntityTypePredicate;
import net.minecraft.advancements.predicates.entity.PlayerPredicate;
import net.minecraft.advancements.triggers.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.StellarityBlocks;
import prismatic.shards.stellarity.registry.StellarityItems;
import prismatic.shards.stellarity.registry.advancement_criterion.SpecialCraftTrigger;
import prismatic.shards.stellarity.registry.advancement_criterion.VoidFishedTrigger;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;


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

	public static Advancement.Builder advancement() {
		return Advancement.Builder.advancement();
	}

	public static Advancement.Builder recipe() {
		return Advancement.Builder.recipeAdvancement();
	}

	@Override
	public void generateAdvancement(HolderLookup.Provider registryLookup, @NonNull Consumer<AdvancementHolder> consumer) {
		final HolderLookup.RegistryLookup<Item> items = registryLookup.lookupOrThrow(Registries.ITEM);
		final HolderLookup.RegistryLookup<EntityType<?>> entities = registryLookup.lookupOrThrow(Registries.ENTITY_TYPE);

		var ENTER_END_GATEWAY = dummy(Stellarity.mcId("end/enter_end_gateway"));
		var ENTER_END = dummy(Stellarity.mcId("story/enter_the_end"));
		var END_ROOT = dummy(Stellarity.mcId("end/root"));
		var KILL_DRAGON = dummy(Stellarity.mcId("end/kill_dragon"));


		var VOID_REELS = advancement()
			.display(StellarityItems.FISHER_OF_VOIDS,
				Component.translatable("advancements.stellarity.void_reels"),
				Component.translatable("advancements.stellarity.void_reels.description"),
				null,
				TASK,
				true,
				true,
				false
			)
			.parent(ENTER_END_GATEWAY)
			.addCriterion("fishing", VoidFishedTrigger.TriggerInstance.fishedItem(
				Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()
			)).requirements(requires(new String[][]{{"fishing"}}))
			.build(Stellarity.id("void_fishing/void_reels"));

		var TOPPED_OFF = advancement()
			.display(
				StellarityItems.CRYSTAL_HEARTFISH,
				Component.translatable("advancements.stellarity.topped_off"),
				Component.translatable("advancements.stellarity.topped_off.description"),
				null,
				TASK,
				true,
				true,
				false
			)
			.parent(VOID_REELS)
			.addCriterion("impossible", impossible())
			.build(Stellarity.id("void_fishing/topped_off"));


		var FIND_DUSKBERRY = advancement()
			.display(
				StellarityItems.DUSKBERRY,
				Component.translatable("advancements.stellarity.duskberry_find"),
				Component.translatable("advancements.stellarity.duskberry_find.description"),
				null,
				TASK,
				true,
				true,
				false
			)
			.parent(ENTER_END)
			.addCriterion("get_item", InventoryChangeTrigger.TriggerInstance.hasItems(StellarityItems.DUSKBERRY))
			.requirements(requires(new String[][]{{"get_item"}}))
			.build(Stellarity.id("exploration/duskberry/find"));

		var POOR_LIFE_CHOICES = advancement()
			.display(
				StellarityItems.DUSKBERRY,
				Component.translatable("advancements.stellarity.poor_life_choices"),
				Component.translatable("advancements.stellarity.poor_life_choices.description"),
				null,
				CHALLENGE,
				true,
				true,
				false
			)
			.addCriterion("eat", ConsumeItemTrigger.TriggerInstance.usedItem(items, StellarityItems.DUSKBERRY))
			.addCriterion("feed", PlayerInteractTrigger.TriggerInstance.itemUsedOnEntity(ItemPredicate.Builder.item().of(items, StellarityItems.DUSKBERRY),

				Optional.of(ContextAwarePredicate.create(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(entities, EntityTypes.FOX).build()).build())
				)
			))
			.addCriterion("place", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(StellarityBlocks.DUSKBERRY_BUSH))
			.parent(FIND_DUSKBERRY)
			.requirements(requires(new String[][]{{"eat"}, {"feed"}, {"place"}}))
			.build(Stellarity.id("exploration/duskberry/poor_life_choices"));

		var SACRIFICAL_RITUAL = advancement().display(
				Items.END_CRYSTAL,
				Component.translatable("advancements.stellarity.sacrificial_ritual"),
				Component.translatable("advancements.stellarity.sacrificial_ritual.description"),
				null,
				GOAL,
				true,
				true,
				false
			).parent(END_ROOT)
			.addCriterion("summon", SummonedEntityTrigger.TriggerInstance.summonedEntity(new EntityPredicate.Builder().entityType(EntityTypePredicate.of(entities, EntityTypes.ENDER_DRAGON))))
			.requirements(
				requires(new String[][]{{"summon"}})
			).build(Stellarity.id("ender_dragon/sacrificial_ritual"));

		var RESPAWN_DRAGON = advancement().display(
				Items.END_CRYSTAL,
				Component.translatable("advancements.end.respawn_dragon.title"),
				Component.translatable("advancements.end.respawn_dragon.description"),
				null,
				TASK,
				true,
				true,
				false
			).parent(KILL_DRAGON)
			.addCriterion("summon", CriteriaTriggers.SUMMONED_ENTITY.createCriterion(new SummonedEntityTrigger.TriggerInstance(
					Optional.of(
						EntityPredicate.wrap(new EntityPredicate.Builder().player(PlayerPredicate.Builder.player().checkAdvancementDone(Stellarity.id("ender_dragon/sacrificial_ritual"), true).build()))
					),
					Optional.of(
						EntityPredicate.wrap(new EntityPredicate.Builder().entityType(EntityTypePredicate.of(entities, EntityTypes.ENDER_DRAGON))))
				)
			))
			.requirements(
				requires(new String[][]{{"summon"}})
			).build(Stellarity.mcId("end/respawn_dragon"));

		var ALTAR_OF_THE_ACCURSED_INTRO = advancement().display(
				StellarityItems.ENDONOMICON,
				Component.translatable("advancements.stellarity.altar_of_the_accursed_intro"),
				Component.translatable("advancements.stellarity.altar_of_the_accursed_intro.description"),
				null,
				GOAL,
				true,
				true,
				false
			).parent(KILL_DRAGON)
			.addCriterion("craft", SpecialCraftTrigger.triggerInstance(
				Optional.empty(),
				Optional.of(ContextAwarePredicate.create(
					new LootItemBlockStatePropertyCondition.Builder(StellarityBlocks.ALTAR_OF_THE_ACCURSED).build()
				)),
				Optional.of(
					ItemPredicate.Builder.item().of(items, StellarityItems.ENDONOMICON).build()
				)
			))
			.requirements(
				requires(new String[][]{{"craft"}})
			).build(Stellarity.mcId("altar_of_the_accursed/altar_of_the_accursed_intro"));

		// TODO: reparent to intro to dark magic
		var CURSED_CRAFTING = advancement().display(
				StellarityItems.ALTAR_OF_THE_ACCURSED,
				Component.translatable("advancements.stellarity.cursed_crafting"),
				Component.translatable("advancements.stellarity.cursed_crafting.description"),
				null,
				GOAL,
				true,
				true,
				false
			).parent(ALTAR_OF_THE_ACCURSED_INTRO)
			.addCriterion("craft", SpecialCraftTrigger.triggerInstance(
				Optional.empty(),
				Optional.of(ContextAwarePredicate.create(
					new LootItemBlockStatePropertyCondition.Builder(StellarityBlocks.ALTAR_OF_THE_ACCURSED).build()
				)),
				Optional.empty()
			))
			.requirements(
				requires(new String[][]{{"craft"}})
			).build(Stellarity.mcId("altar_of_the_accursed/cursed_crafting"));

		var CRAFT_FULL_SHULKER_ARMOR = advancement().display(
				StellarityItems.SHULKER_CHESTPLATE,
				Component.translatable("advancements.stellarity.craft_full_shulker_armor"),
				Component.translatable("advancements.stellarity.craft_full_shulker_armor.description"),
				null,
				CHALLENGE,
				true,
				true,
				false
			).parent(CURSED_CRAFTING)
			.addCriterion("helmet", InventoryChangeTrigger.TriggerInstance.hasItems(StellarityItems.SHULKER_HELMET))
			.addCriterion("chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(StellarityItems.SHULKER_CHESTPLATE))
			.addCriterion("leggings", InventoryChangeTrigger.TriggerInstance.hasItems(StellarityItems.SHULKER_LEGGINGS))
			.addCriterion("boots", InventoryChangeTrigger.TriggerInstance.hasItems(StellarityItems.SHULKER_BOOTS))
			.requirements(
				requires(new String[][]{{"helmet"}, {"chestplate"}, {"leggings"}, {"boots"}})
			).build(Stellarity.mcId("altar_of_the_accursed/craft_full_shulker_armor"));


		for (var advancement : List.of(VOID_REELS, TOPPED_OFF, FIND_DUSKBERRY, POOR_LIFE_CHOICES, SACRIFICAL_RITUAL, RESPAWN_DRAGON, CURSED_CRAFTING, CRAFT_FULL_SHULKER_ARMOR, ALTAR_OF_THE_ACCURSED_INTRO)) {
			consumer.accept(advancement);
		}
	}

	private Criterion<ImpossibleTrigger.TriggerInstance> impossible() {
		return CriteriaTriggers.IMPOSSIBLE.createCriterion(new ImpossibleTrigger.TriggerInstance());
	}
}
