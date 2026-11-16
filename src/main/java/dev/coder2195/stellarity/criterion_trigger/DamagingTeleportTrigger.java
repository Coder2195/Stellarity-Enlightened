package dev.coder2195.stellarity.criterion_trigger;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.DistancePredicate;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.LocationPredicate;
import net.minecraft.advancements.predicates.MinMaxBounds;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.triggers.SimpleCriterionTrigger;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.*;

public class DamagingTeleportTrigger extends SimpleCriterionTrigger<DamagingTeleportTrigger.TriggerInstance> {


	@Override
	public Codec<TriggerInstance> codec() {
		return TriggerInstance.CODEC;
	}

	public void trigger(final ServerPlayer player, final Collection<? extends Entity> victims, final ItemStack weapon, Vec3 start, Vec3 end) {
		List<LootContext> victimContexts = Lists.newArrayList();
		Set<EntityType<?>> entityTypes = Sets.newHashSet();

		for (Entity victim : victims) {
			entityTypes.add(victim.getType());
			victimContexts.add(EntityPredicate.createContext(player, victim));
		}

		this.trigger(player, /* lambda$trigger$0 */ t -> t.matches(player,victimContexts, entityTypes.size(), weapon, start, end));
	}

	public record TriggerInstance(Optional<Holder<LootItemCondition>> player, List<Holder<LootItemCondition>> victims,
																MinMaxBounds.Ints uniqueEntityTypes, MinMaxBounds.Ints victimCount,
																Optional<ItemPredicate> weapon,
																Optional<LocationPredicate> startPosition, Optional<LocationPredicate> endPosition,
																Optional<DistancePredicate> distance) implements SimpleCriterionTrigger.SimpleInstance {
		public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(
			/* lambda$static$0 */ i -> i.group(
					LootItemCondition.CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player),
					LootItemCondition.CODEC.listOf().optionalFieldOf("victims", List.of()).forGetter(TriggerInstance::victims),
					MinMaxBounds.Ints.CODEC.optionalFieldOf("unique_entity_types", MinMaxBounds.Ints.ANY).forGetter(TriggerInstance::uniqueEntityTypes),
					MinMaxBounds.Ints.CODEC.optionalFieldOf("victim_count", MinMaxBounds.Ints.ANY).forGetter(TriggerInstance::victimCount),
					ItemPredicate.CODEC.optionalFieldOf("weapon").forGetter(TriggerInstance::weapon),
					LocationPredicate.CODEC.optionalFieldOf("start_position").forGetter(TriggerInstance::startPosition),
					LocationPredicate.CODEC.optionalFieldOf("end_position").forGetter(TriggerInstance::endPosition),
					DistancePredicate.CODEC.optionalFieldOf("distance").forGetter(TriggerInstance::distance)
				)
				.apply(i, TriggerInstance::new));

		public boolean matches(ServerPlayer player, final Collection<LootContext> victims, final int uniqueEntityTypes, final @Nullable ItemStack weapon, Vec3 start, Vec3 end) {
			var level = player.level();
			if (!this.uniqueEntityTypes.matches(uniqueEntityTypes) || !this.victimCount.matches(victims.size())) {
				return false;
			}


			if (this.startPosition.isPresent() && !this.startPosition.get().matches(level, start.x, start.y, start.z)) return false;
			if (this.endPosition.isPresent() && !this.endPosition.get().matches(level, start.x, start.y, end.z)) return false;
			if (this.distance.isPresent() && !this.distance.get().matches(start.x, start.y, start.z, end.x, end.y, end.z)) return false;

			if (this.weapon.isEmpty() || weapon != null && this.weapon.get().test(weapon)) {
				if (this.victims.isEmpty()) return true;

				List<LootContext> victimsCopy = Lists.newArrayList(victims);

				for (Holder<LootItemCondition> predicate : this.victims) {
					boolean found = false;
					Iterator<LootContext> iterator = victimsCopy.iterator();

					while (iterator.hasNext()) {
						LootContext entity = iterator.next();
						if (predicate.value().test(entity)) {
							iterator.remove();
							found = true;
							break;
						}
					}

					if (!found) {
						return false;
					}
				}

				return true;
			}

			return false;
		}
	}
}
