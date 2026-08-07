package dev.coder2195.stellarity.criterion_trigger;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.coder2195.stellarity.registry.StellarityCriteriaTriggers;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.advancements.predicates.DamagePredicate;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class HolyProtectionDodgeTrigger extends SimpleCriterionTrigger<HolyProtectionDodgeTrigger.TriggerInstance> {
	@Override
	public @NonNull Codec<TriggerInstance> codec() {
		return TriggerInstance.CODEC;
	}

	public void trigger(final ServerPlayer player, final DamageSource source, final float originalDamage, final float actualDamage) {
		this.trigger(player, /* lambda$trigger$0 */ t -> t.matches(player, source, originalDamage, actualDamage));
	}

	public static Criterion<?> trigger() {
		return StellarityCriteriaTriggers.HOLY_PROTECTION_DODGE.createCriterion(new TriggerInstance(Optional.empty(), Optional.empty()));
	}

	public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<DamagePredicate> damage) implements SimpleCriterionTrigger.SimpleInstance {
		public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(
			/* lambda$static$0 */ i -> i.group(
					EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player),
					DamagePredicate.CODEC.optionalFieldOf("damage").forGetter(TriggerInstance::damage)
				)
				.apply(i, TriggerInstance::new)
		);

		public boolean matches(final ServerPlayer player, final DamageSource source, final float originalDamage, final float actualDamage) {
			return this.damage.isEmpty() || this.damage.get().matches(player, source, originalDamage, actualDamage, true);
		}
	}
}
