package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.tags.StellarityBlockTags;
import dev.coder2195.stellarity.tags.StellarityDamageTypeTags;
import dev.coder2195.stellarity.tags.StellarityEntityTypeTags;
import dev.coder2195.stellarity.tags.StellarityItemTags;
import dev.coder2195.stellarity.util.tuple.Tuple2;
import net.minecraft.advancements.predicates.TagPredicate;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.*;
import dev.coder2195.stellarity.Stellarity;
import net.minecraft.world.item.enchantment.effects.EnchantmentAttributeEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.item.enchantment.effects.MultiplyValue;
import net.minecraft.world.item.enchantment.effects.SpawnParticlesEffect;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static dev.coder2195.stellarity.util.EnchantmentUtil.*;
import static dev.coder2195.stellarity.util.EnchantmentUtil.applyEffect;
import static dev.coder2195.stellarity.util.EnchantmentUtil.attribute;
import static dev.coder2195.stellarity.util.EnchantmentUtil.changeItemDamage;
import static dev.coder2195.stellarity.util.EnchantmentUtil.cost;
import static dev.coder2195.stellarity.util.EnchantmentUtil.inBoundingBox;
import static dev.coder2195.stellarity.util.EnchantmentUtil.particleVelocity;
import static dev.coder2195.stellarity.util.EnchantmentUtil.playSound;
import static dev.coder2195.stellarity.util.LootUtil.*;
import static dev.coder2195.stellarity.util.ValueUtil.numf;
import static net.minecraft.world.item.enchantment.EnchantmentEffectComponents.DAMAGE;
import static net.minecraft.world.item.enchantment.EnchantmentEffectComponents.POST_ATTACK;

public interface StellarityEnchantments {
	ResourceKey<Enchantment> AMBUSH = id("ambush");
	ResourceKey<Enchantment> CRITICAL_STRIKE = id("critical_strike");
	ResourceKey<Enchantment> DUNE_SPEED = id("dune_speed");
	ResourceKey<Enchantment> LEVITATION_SHOT = id("levitation_shot");
	ResourceKey<Enchantment> PLATED = id("plated");
	ResourceKey<Enchantment> SOARING = id("soaring");
	ResourceKey<Enchantment> VOID_SHOT = id("void_shot");
	ResourceKey<Enchantment> VOID_STRIKE = id("void_strike");

	static void bootstrap(BootstrapContext<Enchantment> context) {
		var enchants = context.lookup(Registries.ENCHANTMENT);
		var items = context.lookup(Registries.ITEM);
		var entities = context.lookup(Registries.ENTITY_TYPE);
		var blocks = context.lookup(Registries.BLOCK);
		var mobEffects = context.lookup(Registries.MOB_EFFECT);

		var noAI = new CompoundTag();
		noAI.putBoolean("NoAI", true);
		var ambushRequirements = not(any(
			entityProperty(predicate().nbt(nbt(noAI))),
			entityProperty(predicate().entityType(entityType(EntityTypes.PLAYER))),
			entityProperty(predicate().entityType(entityType(entities, StellarityEntityTypeTags.INVALID_TARGETS))),
			entityProperty(predicate().targetedEntity(predicate().entityType(entityType(EntityTypes.PLAYER))))
		).build());
		context.register(AMBUSH, new Enchantment(
			Component.translatable("enchantment.stellarity.ambush"),
			new Enchantment.EnchantmentDefinition(
				items.getOrThrow(ItemTags.SHARP_WEAPON_ENCHANTABLE), Optional.of(items.getOrThrow(ItemTags.SWORDS)), 5, 3,
				cost(12, 17), cost(28, 17), 2, List.of(EquipmentSlotGroup.MAINHAND)
			), HolderSet.empty(),
			DataComponentMap.builder()
				.set(DAMAGE, List.of(new ConditionalEffect<>(
					new MultiplyValue(new LevelBasedValue.Linear(1.5f, 1.5f)), Optional.of(ambushRequirements)
				)))
				.set(POST_ATTACK, Stream.concat(
					Stream.of(new TargetedConditionalEffect<>(EnchantmentTarget.ATTACKER, EnchantmentTarget.VICTIM, allEffects(
						IntStream.range(0, 9).mapToObj((_) ->
							new SpawnParticlesEffect(ParticleTypes.TRIAL_SPAWNER_DETECTED_PLAYER, inBoundingBox(), inBoundingBox(), particleVelocity(), particleVelocity(), numf(0.2f))
						).toArray(EnchantmentEntityEffect[]::new)
					), Optional.of(ambushRequirements))),
					Stream.of(
						new Tuple2<>(StellaritySoundEvents.AMBUSH_LEVEL_1, 1),
						new Tuple2<>(StellaritySoundEvents.AMBUSH_LEVEL_2, 2),
						new Tuple2<>(StellaritySoundEvents.AMBUSH_LEVEL_3, 3)
					).map(tuple -> new TargetedConditionalEffect<>(
						EnchantmentTarget.ATTACKER, EnchantmentTarget.VICTIM, playSound(tuple._1(), numf(0.7f), numf(1)
					), Optional.of(all(ambushRequirements, valueCheck(enchantNum(levelBasedLinear(1, 1)), intRange(tuple._2()))))))
				).toList())
				.build()
		));
		context.register(CRITICAL_STRIKE, new Enchantment(
			Component.translatable("enchantment.stellarity.critical_strike").withColor(0xd600f4),
			new Enchantment.EnchantmentDefinition(
				items.getOrThrow(ItemTags.SHARP_WEAPON_ENCHANTABLE), Optional.of(items.getOrThrow(ItemTags.SWORDS)), 1, 3,
				cost(50, 11), cost(75, 15), 4, List.of(EquipmentSlotGroup.MAINHAND)
			), enchants.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE), DataComponentMap.EMPTY
		));
		var isDuneSpeedBlock = location().setBlock(blockPredicate().of(blocks, StellarityBlockTags.DUNE_SPEED_BLOCKS));
		context.register(DUNE_SPEED, new Enchantment(
			Component.translatable("enchantment.stellarity.dune_speed").withColor(0xffd026),
			new Enchantment.EnchantmentDefinition(
				items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE), Optional.empty(), 1, 3,
				cost(35, 10), cost(50, 10), 8, List.of(EquipmentSlotGroup.FEET)
			), HolderSet.direct(enchants.getOrThrow(Enchantments.SOUL_SPEED)),
			DataComponentMap.builder()
				.set(EnchantmentEffectComponents.LOCATION_CHANGED, List.of(
					new ConditionalEffect<>(
						attribute(Stellarity.id("enchantment.dune_speed"), Attributes.MOVEMENT_SPEED, levelBasedLinear(0.025f, 0.009f), AttributeModifier.Operation.ADD_VALUE),
						Optional.of(all(
							not(entityProperty(predicate().vehicle(predicate()))),
							any(
								all(
									enchantActive(), entityProperty(predicate().flags(flags().setIsFlying(false))),
									any(
										entityProperty(predicate().steppingOn(isDuneSpeedBlock)),
										entityProperty(predicate().flags(flags().setOnGround(false)))
									)
								),
								all(
									enchantInactive(), entityProperty(predicate().flags(flags().setIsFlying(false)).steppingOn(isDuneSpeedBlock))
								)
							)
						).build())
					),
					new ConditionalEffect<>(
						changeItemDamage(levelBasedConstant(1)),
						Optional.of(all(
							randomChance(enchantNum(levelBasedConstant(0.04f))),
							entityProperty(predicate().steppingOn(isDuneSpeedBlock).flags(flags().setOnGround(true)))
						).build())
					)
				))
				.build()
		));
		context.register(LEVITATION_SHOT, new Enchantment(
			Component.translatable("enchantment.stellarity.levitation_shot"),
			new Enchantment.EnchantmentDefinition(
				items.getOrThrow(ItemTags.BOW_ENCHANTABLE), Optional.empty(), 2, 5,
				cost(29, 4), cost(50, 10), 4, List.of(EquipmentSlotGroup.MAINHAND)
			), HolderSet.empty(), DataComponentMap.EMPTY
		));
		context.register(SOARING, new Enchantment(
			Component.translatable("enchantment.stellarity.soaring"),
			new Enchantment.EnchantmentDefinition(
				items.getOrThrow(StellarityItemTags.ELYTRA_ENCHANTABLE), Optional.empty(), 2, 4,
				cost(8, 5), cost(19, 8), 2, List.of(EquipmentSlotGroup.BODY)
			), HolderSet.empty(),
			DataComponentMap.builder()
				.set(EnchantmentEffectComponents.ATTRIBUTES, List.of(
					new EnchantmentAttributeEffect(Stellarity.id("enchantment.soaring"), Attributes.GRAVITY, levelBasedLinear(-0.05f, -0.05f), AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
				))
				.build()
		));
		context.register(PLATED, new Enchantment(
			Component.translatable("enchantment.stellarity.plated"),
			new Enchantment.EnchantmentDefinition(
				items.getOrThrow(StellarityItemTags.ELYTRA_ENCHANTABLE), Optional.empty(), 2, 4,
				cost(8, 5), cost(19, 8), 2, List.of(EquipmentSlotGroup.BODY)
			), HolderSet.direct(enchants.getOrThrow(SOARING)),
			DataComponentMap.builder()
				.set(EnchantmentEffectComponents.ATTRIBUTES, List.of(
					new EnchantmentAttributeEffect(Stellarity.id("enchantment.plated"), Attributes.GRAVITY, levelBasedLinear(0.04f, 0.03f), AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
					new EnchantmentAttributeEffect(Stellarity.id("enchantment.plated"), Attributes.ARMOR, levelBasedLinear(2, 1), AttributeModifier.Operation.ADD_VALUE),
					new EnchantmentAttributeEffect(Stellarity.id("enchantment.plated"), Attributes.ARMOR_TOUGHNESS, levelBasedLookup(levelBasedConstant(1), 0f, 0f, 1f, 1f), AttributeModifier.Operation.ADD_VALUE)
				))
				.build()
		));
		context.register(VOID_SHOT, new Enchantment(
			Component.translatable("enchantment.stellarity.void_shot").withColor(0xD600F4),
			new Enchantment.EnchantmentDefinition(
				items.getOrThrow(StellarityItemTags.RANGED_ENCHANTABLE), Optional.empty(), 1, 1,
				cost(75, 0), cost(100, 0), 10, List.of(EquipmentSlotGroup.MAINHAND)
			), HolderSet.empty(), DataComponentMap.EMPTY
		));
		context.register(VOID_STRIKE, new Enchantment(
			Component.translatable("enchantment.stellarity.void_strike").withColor(0xD600F4),
			new Enchantment.EnchantmentDefinition(
				items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE), Optional.empty(), 1, 1,
				cost(75, 0), cost(100, 0), 10, List.of(EquipmentSlotGroup.MAINHAND)
			), HolderSet.empty(),
			DataComponentMap.builder()
				.set(POST_ATTACK, List.of(
					new TargetedConditionalEffect<>(
						EnchantmentTarget.ATTACKER, EnchantmentTarget.VICTIM,
						applyEffect(
							HolderSet.direct(StellarityMobEffects.VOIDED), levelBasedConstant(160), levelBasedConstant(160),
							levelBasedConstant(0), levelBasedConstant(0)
						),
						Optional.of(damageSource(damage().tag(isTag(StellarityDamageTypeTags.MELEE)).tag(TagPredicate.isNot(DamageTypeTags.BYPASSES_INVULNERABILITY)).isDirect(true)).build())
					)
				))
				.build()
		));
	}

	private static ResourceKey<Enchantment> id(String id) {
		return Stellarity.key(Registries.ENCHANTMENT, id);
	}
}

