package dev.coder2195.stellarity.registry.criterion_trigger;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.SimpleCriterionTrigger;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.Validatable;
import net.minecraft.world.level.storage.loot.ValidationContextSource;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.jspecify.annotations.NonNull;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.StellarityCriteriaTriggers;

import java.util.Collection;
import java.util.Optional;


public class VoidFishedTrigger extends SimpleCriterionTrigger<VoidFishedTrigger.TriggerInstance> {
	static final Identifier ID = Stellarity.id("void_fished");

	public Identifier getId() {
		return ID;
	}


	public void trigger(ServerPlayer serverPlayer, ItemStack itemStack, FishingHook fishingHook, Collection<ItemStack> collection) {
		LootContext lootContext = EntityPredicate.createContext(serverPlayer, fishingHook.getHookedIn() != null ? fishingHook.getHookedIn() : fishingHook);
		this.trigger(serverPlayer, (triggerInstance) -> triggerInstance.matches(itemStack, lootContext, collection));
	}

	public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<ItemPredicate> rod,
	                              Optional<ContextAwarePredicate> entity,
	                              Optional<ItemPredicate> item) implements SimpleCriterionTrigger.SimpleInstance {
		public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create((instance) -> instance.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player), ItemPredicate.CODEC.optionalFieldOf("rod").forGetter(TriggerInstance::rod), EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("entity").forGetter(TriggerInstance::entity), ItemPredicate.CODEC.optionalFieldOf("item").forGetter(TriggerInstance::item)).apply(instance, TriggerInstance::new));

		@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
		public static Criterion<?> fishedItem(Optional<ContextAwarePredicate> player, Optional<ItemPredicate> itemPredicate, Optional<EntityPredicate> optional2, Optional<ItemPredicate> optional3) {
			return StellarityCriteriaTriggers.VOID_FISHED.createCriterion(new TriggerInstance(player, itemPredicate, EntityPredicate.wrap(optional2), optional3));
		}

		public boolean matches(ItemStack itemStack, LootContext lootContext, Collection<ItemStack> collection) {
			if (this.rod.isPresent() && !this.rod.get().test(itemStack)) {
				return false;
			} else if (this.entity.isPresent() && !this.entity.get().matches(lootContext)) {
				return false;
			} else {
				if (this.item.isPresent()) {
					boolean bl = false;
					Entity entity = lootContext.getOptionalParameter(LootContextParams.THIS_ENTITY);

					if (entity instanceof ItemEntity itemEntity) {
						if (this.item.get().test(itemEntity.getItem())) {
							bl = true;
						}
					}

					for (ItemStack itemStack2 : collection) {
						if (this.item.get().test(itemStack2)) {
							bl = true;
							break;
						}
					}

					return bl;
				}

				return true;
			}
		}


		@Override
		public void validate(final @NonNull ValidationContextSource validator) {
			SimpleCriterionTrigger.SimpleInstance.super.validate(validator);
			Validatable.validate(validator.entityContext(), "entity", this.entity);
		}

	}

	public @NonNull Codec<TriggerInstance> codec() {
		return TriggerInstance.CODEC;
	}
}