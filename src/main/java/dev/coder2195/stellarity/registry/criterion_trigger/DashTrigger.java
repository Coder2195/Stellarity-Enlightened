package dev.coder2195.stellarity.registry.criterion_trigger;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.MinMaxBounds;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.triggers.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.*;

public class DashTrigger extends SimpleCriterionTrigger<DashTrigger.TriggerInstance> {


	@Override
	public @NonNull Codec<TriggerInstance> codec() {
		return TriggerInstance.CODEC;
	}

	public void trigger(final ServerPlayer player, final Collection<Entity> victims, @Nullable final ItemStack weapon) {
		List<LootContext> victimContexts = Lists.newArrayList();
		Set<EntityType<?>> entityTypes = Sets.newHashSet();

		for (Entity victim : victims) {
			entityTypes.add(victim.getType());
			victimContexts.add(EntityPredicate.createContext(player, victim));
		}

		this.trigger(player, /* lambda$trigger$0 */ t -> t.matches(victimContexts, entityTypes.size(), weapon));
	}

	public record TriggerInstance(Optional<ContextAwarePredicate> player, List<ContextAwarePredicate> victims,
	                              MinMaxBounds.Ints uniqueEntityTypes, MinMaxBounds.Ints victimCount,
	                              Optional<ItemPredicate> weapon) implements SimpleCriterionTrigger.SimpleInstance {
		public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(
			/* lambda$static$0 */ i -> i.group(
					EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player),
					EntityPredicate.ADVANCEMENT_CODEC.listOf().optionalFieldOf("victims", List.of()).forGetter(TriggerInstance::victims),
					MinMaxBounds.Ints.CODEC.optionalFieldOf("unique_entity_types", MinMaxBounds.Ints.ANY).forGetter(TriggerInstance::uniqueEntityTypes),
					MinMaxBounds.Ints.CODEC.optionalFieldOf("victim_count", MinMaxBounds.Ints.ANY).forGetter(TriggerInstance::victimCount),
					ItemPredicate.CODEC.optionalFieldOf("weapon").forGetter(TriggerInstance::weapon)
				)
				.apply(i, TriggerInstance::new));

		public boolean matches(final Collection<LootContext> victims, final int uniqueEntityTypes, @Nullable final ItemStack weapon) {
			if (!this.uniqueEntityTypes.matches(uniqueEntityTypes) || !this.victimCount.matches(victims.size())) {
				return false;
			}

			if (this.weapon.isEmpty() || weapon != null && this.weapon.get().test(weapon)) {
				if (this.victims.isEmpty()) return true;

				List<LootContext> victimsCopy = Lists.<LootContext>newArrayList(victims);

				for (ContextAwarePredicate predicate : this.victims) {
					boolean found = false;
					Iterator<LootContext> iterator = victimsCopy.iterator();

					while (iterator.hasNext()) {
						LootContext entity = iterator.next();
						if (predicate.matches(entity)) {
							iterator.remove();
							found = true;
							break;
						}
					}

					if (!found) return false;
				}

				return true;
			}

			return false;
		}
	}
}
