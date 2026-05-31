package prismatic.shards.stellarity.util;

import net.minecraft.advancements.predicates.*;
import net.minecraft.advancements.predicates.entity.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.*;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.EnchantmentLevelProvider;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jspecify.annotations.NonNull;

import java.util.List;

public interface LootUtil {
	static LootPool.Builder pool() {
		return new LootPool.Builder();
	}

	static LootItemCondition.Builder onDamage(DamageSourcePredicate.Builder predicate) {
		return DamageSourceCondition.hasDamageSource(predicate);
	}

	static InvertedLootItemCondition not(LootItemCondition term) {
		return new InvertedLootItemCondition(term);
	}

	static LootItemCondition.Builder not(LootItemCondition.Builder term) {
		return InvertedLootItemCondition.invert(term);
	}

	static DamageSourcePredicate.Builder damage() {
		return new DamageSourcePredicate.Builder();
	}

	static EntityPredicate.Builder predicate() {
		return new EntityPredicate.Builder();
	}

	static EntityTypePredicate entityType(EntityType<?> type) {
		return EntityTypePredicate.of(BuiltInRegistries.ENTITY_TYPE, type);
	}

	static EntityTypePredicate entityType(HolderGetter<EntityType<?>> lookup, TagKey<EntityType<?>> tag) {
		return EntityTypePredicate.of(lookup, tag);
	}

	static NbtPredicate nbt(CompoundTag tag) {
		return new NbtPredicate(tag);
	}


	static ConstantValue num(float num) {
		return new ConstantValue(num);
	}

	static UniformGenerator num(float min, float max) {
		return new UniformGenerator(num(min), num(max));
	}

	static LootPoolSingletonContainer.Builder<?> item(ItemLike i) {
		return LootItem.lootTableItem(i);
	}

	static LootPoolSingletonContainer.Builder<?> tableLoot(ResourceKey<LootTable> key) {
		return NestedLootTable.lootTableReference(key);
	}

	static LootItemConditionalFunction.Builder<?> count(NumberProvider provider) {
		return SetItemCountFunction.setCount(provider);
	}

	static LootItemConditionalFunction.Builder<?> countAdd(NumberProvider provider) {
		return SetItemCountFunction.setCount(provider, true);
	}


	static <T> LootItemConditionalFunction.Builder<?> component(DataComponentType<T> type, T obj) {
		return SetComponentsFunction.setComponent(type, obj);
	}


	static EnchantWithLevelsFunction.Builder enchant(HolderLookup.Provider provider, int min, int max) {
		return EnchantWithLevelsFunction.enchantWithLevels(provider, num(min, max));
	}

	static EnchantWithLevelsFunction.Builder enchant(HolderGetter<Enchantment> enchantments, int min, int max) {
		return new EnchantWithLevelsFunction.Builder(num(min, max)).withOptions(enchantments.getOrThrow(EnchantmentTags.ON_RANDOM_LOOT));
	}

	static LootItemConditionalFunction.Builder<?> potion(Holder<Potion> potion) {
		return SetPotionFunction.setPotion(potion);
	}

	static LootItemConditionalFunction.Builder<?> damage(float damage) {
		return SetItemDamageFunction.setDamage(num(damage));
	}

	static LootItemConditionalFunction.Builder<?> damage(float min, float max) {
		return SetItemDamageFunction.setDamage(num(min, max));
	}


	static LootPoolSingletonContainer.Builder<?> lootTable(Identifier location) {

		return NestedLootTable.lootTableReference(ResourceKey.create(Registries.LOOT_TABLE, location));
	}

	static LootPoolSingletonContainer.Builder<?> lootTable(ResourceKey<LootTable> location) {
		return NestedLootTable.lootTableReference(location);
	}

	static LootTable.Builder lootTable() {
		return LootTable.lootTable();
	}

	static LootItemBlockStatePropertyCondition.Builder blockState(Block block) {
		return new LootItemBlockStatePropertyCondition.Builder(block);
	}

	static BlockPredicate.Builder blockPredicate() {
		return BlockPredicate.Builder.block();
	}

	static ValueCheckCondition valueCheck(NumberProvider value, IntRange range) {
		return new ValueCheckCondition(value, range);
	}

	static EnchantmentLevelProvider enchantNum(LevelBasedValue amount) {
		return EnchantmentLevelProvider.forEnchantmentLevel(amount);
	}

	static LootItemCondition.Builder enchantInactive() {
		return EnchantmentActiveCheck.enchantmentInactiveCheck();
	}

	static LootItemCondition.Builder enchantActive() {
		return EnchantmentActiveCheck.enchantmentActiveCheck();
	}

	static LevelBasedValue.Linear levelBasedLinear(float base, float perLevelAboveFirst) {
		return new LevelBasedValue.Linear(base, perLevelAboveFirst);
	}

	static LevelBasedValue.Lookup levelBasedLookup(LevelBasedValue fallback, Float... values) {
		return LevelBasedValue.lookup(List.of(values), fallback);
	}

	static LevelBasedValue.Constant levelBasedConstant(float level) {
		return new LevelBasedValue.Constant(level);
	}

	static LootItemCondition.Builder randomChance(NumberProvider numberProvider) {
		return LootItemRandomChanceCondition.randomChance(numberProvider);
	}

	static LootItemCondition.Builder randomChance(float chance) {
		return LootItemRandomChanceCondition.randomChance(chance);
	}


	static IntRange intRange(int min, int max) {
		return IntRange.range(min, max);
	}

	static IntRange intRange(int constant) {
		return IntRange.exact(constant);
	}

	static <T extends Comparable<T> & StringRepresentable> StatePropertiesPredicate.Builder hasProperty(Property<@NonNull T> property, T value) {
		return StatePropertiesPredicate.Builder.properties().hasProperty(property, value);
	}

	static StatePropertiesPredicate.Builder hasProperty(Property<Integer> property, int value) {
		return StatePropertiesPredicate.Builder.properties().hasProperty(property, value);
	}

	static StatePropertiesPredicate.Builder hasProperty(Property<Boolean> property, boolean value) {
		return StatePropertiesPredicate.Builder.properties().hasProperty(property, value);
	}

	static Holder<Enchantment> enchant(HolderLookup.Provider registries, ResourceKey<Enchantment> enchantment) {
		return registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(enchantment);
	}

	static LootItemConditionalFunction.Builder<?> enchantLevel(Holder<Enchantment> enchantment, int i) {
		return ApplyBonusCount.addUniformBonusCount(enchantment, i);
	}

	static SequenceFunction modifiers(LootItemFunction... functions) {
		return SequenceFunction.of(List.of(functions));
	}

	static LootItemCondition.Builder chance(float chance) {
		return LootItemRandomChanceCondition.randomChance(chance);
	}

	static LootItemCondition.Builder chance(NumberProvider numberProvider) {
		return LootItemRandomChanceCondition.randomChance(numberProvider);
	}

	static EnchantRandomlyFunction.Builder enchant() {
		return EnchantRandomlyFunction.randomEnchantment();
	}

	static LootItemFunction modifier(ResourceKey<LootItemFunction> key) {
		return FunctionReference.functionReference(key).build();
	}

	static LootItemCondition.Builder entityProperty(LootContext.EntityTarget target, EntityPredicate.Builder predicate) {
		return LootItemEntityPropertyCondition.hasProperties(target, predicate.build());
	}

	static LootItemCondition.Builder damageSource(DamageSourcePredicate.Builder sourcePredicate) {
		return DamageSourceCondition.hasDamageSource(sourcePredicate);
	}

	static <T> TagPredicate<T> isTag(TagKey<T> tagKey) {
		return TagPredicate.is(tagKey);
	}

	static <T> TagPredicate<T> notTag(TagKey<T> tagKey) {
		return TagPredicate.isNot(tagKey);
	}

	static LootItemCondition.Builder entityProperty(EntityPredicate.Builder predicate) {
		return entityProperty(LootContext.EntityTarget.THIS, predicate);
	}

	static LootPoolSingletonContainer.Builder<?> empty() {
		return EmptyLootItem.emptyItem();
	}

	static LootItemCondition.Builder biome(Holder<Biome> biome) {
		return LocationCheck.checkLocation(LocationPredicate.Builder.inBiome(biome));
	}

	static AllOfCondition all(LootItemCondition... conditions) {
		return AllOfCondition.allOf(List.of(conditions));
	}

	static AllOfCondition.Builder all(LootItemCondition.Builder... conditions) {
		return AllOfCondition.allOf(conditions);
	}

	static AnyOfCondition.Builder any(LootItemCondition.Builder... conditions) {
		return AnyOfCondition.anyOf(conditions);
	}

	static EnchantedCountIncreaseFunction.Builder enchantCount(Holder<Enchantment> enchant, NumberProvider count) {
		return new EnchantedCountIncreaseFunction.Builder(enchant, count);
	}

	static EnchantedCountIncreaseFunction.Builder enchantCount(HolderLookup.Provider provider, NumberProvider count) {
		return EnchantedCountIncreaseFunction.lootingMultiplier(provider, count);
	}

	static LootItemCondition.Builder playerKill() {
		return LootItemKilledByPlayerCondition.killedByPlayer();
	}

	static LootItemCondition.Builder chanceEnchanted(HolderLookup.Provider provider, float chance, float perLevel) {
		return LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(provider, chance, perLevel);
	}

	static LootItemCondition.Builder chanceEnchanted(Holder<Enchantment> enchantment, float chance, LevelBasedValue perLevel) {
		return () -> new LootItemRandomChanceWithEnchantedBonusCondition(chance, perLevel, enchantment);
	}

	static AlternativesEntry.Builder alternatives(LootPoolEntryContainer.Builder<?>... entries) {
		return new AlternativesEntry.Builder(entries);
	}

	static LocationPredicate.Builder location() {
		return LocationPredicate.Builder.location();
	}

	static EntityFlagsPredicate.Builder flags() {
		return EntityFlagsPredicate.Builder.flags();
	}


}
