package dev.coder2195.stellarity.mixin.dragons_eye;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import dev.coder2195.stellarity.registry.StellarityItems;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	public LivingEntityMixin(EntityType<?> type, Level level) {
		super(type, level);
	}

	@Shadow
	public abstract boolean isHolding(Item item);

	@WrapMethod(method = "addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z")
	private boolean blockBadEffects(MobEffectInstance newEffect, Entity source, Operation<Boolean> original) {
		if (isHolding(StellarityItems.DRAGONS_EYE) && newEffect.getEffect().value().getCategory().equals(MobEffectCategory.HARMFUL))
			return false;

		return original.call(newEffect, source);
	}
}
