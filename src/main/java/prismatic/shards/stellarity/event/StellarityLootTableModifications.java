package prismatic.shards.stellarity.event;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.advancements.predicates.DamageSourcePredicate;
import net.minecraft.advancements.predicates.TagPredicate;
import net.minecraft.commands.arguments.NbtPathArgument;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.providers.number.Sum;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.StellarityItems;
import prismatic.shards.stellarity.registry.number_provider.EntityNbtValue;

import java.util.HashMap;

import static prismatic.shards.stellarity.util.LootUtil.*;


public interface StellarityLootTableModifications {
	HashMap<Identifier, LootTableEvents.Modify> modifications = new HashMap<>();
	CompoundTag endVariant = new CompoundTag();

	static void init() {
		endVariant.putString("variant", "stellarity:end");

		modifications.put(Stellarity.mcId("entities/magma_cube"), ((_, builder, _, _) -> {
			builder.withPool(pool().when(
				onDamage(damage().source(
					predicate().entityType(entityType(EntityTypes.FROG)).nbt(nbt(endVariant))
				))).add(item(StellarityItems.ASHEN_FROGLIGHT)
			));
		}));
		modifications.put(BuiltInLootTables.CHICKEN_LAY.identifier(), (((_, builder, _, _) -> {
			var nbtCheck = entityProperty(predicate().nbt(nbt(endVariant)));

			builder.withPool(pool().add(item(StellarityItems.ENDER_EGG)).when(nbtCheck));
		})));
		modifications.put(Stellarity.mcId("entities/shulker"), (_, builder, _, provider) -> {
			builder
				.withPool(pool().add(item(Items.SHULKER_SHELL).apply(count(num(0, 2)))))
				.withPool(pool().add(item(StellarityItems.SHULKER_BODY)).when(chanceEnchanted(provider, 0.75f, 0.05f)));
		});
		modifications.put(Stellarity.mcId("entities/phantom"), (_, builder, _, provider) -> {
			try {
				var sizeValue = new EntityNbtValue(LootContext.EntityTarget.THIS, NbtPathArgument.NbtPath.of("size"), 0);
				builder
					.modifyPools(pool -> pool.setRolls(sizeValue))
					.withPool(pool().setRolls(sizeValue).add(item(Items.GUNPOWDER)
						.apply(count(num(0, 1)))
						.when(damageSource(new DamageSourcePredicate.Builder().tag(new TagPredicate<>(DamageTypeTags.IS_FIRE, true))))
					))
					// TODO: Phantom wings
					.withPool(pool().add(item(StellarityItems.SHULKER_BODY)).when(randomChance(Sum.sum(num(3), sizeValue))));
			} catch (CommandSyntaxException e) {
				Stellarity.LOGGER.error("Failed to initialize NBT path argument {}", e.toString());
			}
		});


		LootTableEvents.MODIFY.register((key, builder, lootTableSource, provider) -> {
			var action = modifications.get(key.identifier());
			if (action == null) return;
			action.modifyLootTable(key, builder, lootTableSource, provider);
		});

	}
}
