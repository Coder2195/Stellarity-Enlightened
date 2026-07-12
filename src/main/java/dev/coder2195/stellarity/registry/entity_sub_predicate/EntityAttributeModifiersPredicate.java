package dev.coder2195.stellarity.registry.entity_sub_predicate;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.MinMaxBounds;
import net.minecraft.advancements.predicates.entity.EntitySubPredicate;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;
import dev.coder2195.stellarity.Stellarity;

import java.util.List;
import java.util.Optional;

public record EntityAttributeModifiersPredicate(List<EntryPredicate> entries) implements EntitySubPredicate {
	public static final Codec<EntityAttributeModifiersPredicate> CODEC = RecordCodecBuilder.create(
		/* lambda$static$0 */ i -> i.group(
				EntryPredicate.CODEC.listOf().fieldOf("entries").forGetter(EntityAttributeModifiersPredicate::entries)
			)
			.apply(i, EntityAttributeModifiersPredicate::new)
	);

	@Override
	public boolean matches(Entity entity, ServerLevel level, @Nullable Vec3 position) {
		if (!(entity instanceof LivingEntity livingEntity)) {
			Stellarity.LOGGER.error("Detected attribute modifier predicates being used on non living entities. This is dead code and should be removed.");
			return false;
		}

		return entries.stream().allMatch(entry -> entry.matches(livingEntity));
	}

	public record EntryPredicate(HolderSet<Attribute> attribute, Optional<Identifier> id, MinMaxBounds.Doubles amount,
	                             Optional<AttributeModifier.Operation> operation) {
		public static final Codec<EntryPredicate> CODEC = RecordCodecBuilder.create(
			/* lambda$static$0 */ i -> i.group(
					RegistryCodecs.homogeneousList(Registries.ATTRIBUTE).fieldOf("attribute").forGetter(EntryPredicate::attribute),
					Identifier.CODEC.optionalFieldOf("id").forGetter(EntryPredicate::id),
					MinMaxBounds.Doubles.CODEC.optionalFieldOf("amount", MinMaxBounds.Doubles.ANY).forGetter(EntryPredicate::amount),
					AttributeModifier.Operation.CODEC.optionalFieldOf("operation").forGetter(EntryPredicate::operation)
				)
				.apply(i, EntryPredicate::new)
		);


		public boolean matches(LivingEntity livingEntity) {
			for (var attr : attribute) {
				var attributeHolder = livingEntity.getAttribute(attr);
				if (attributeHolder == null) continue;
				for (var modifier : attributeHolder.getModifiers()) {
					if (id.map(modifier::is).orElse(true) && amount.matches(modifier.amount()) && operation.map(op -> modifier.operation() == op).orElse(true))
						return true;
				}
			}

			return false;
		}

	}
}
