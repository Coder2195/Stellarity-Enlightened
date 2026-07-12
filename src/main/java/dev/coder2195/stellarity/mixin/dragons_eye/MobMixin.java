package dev.coder2195.stellarity.mixin.dragons_eye;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import dev.coder2195.stellarity.registry.StellarityItems;

@Mixin(Mob.class)
public abstract class MobMixin extends LivingEntity {
	protected MobMixin(EntityType<? extends LivingEntity> type, Level level) {
		super(type, level);
	}

	@WrapMethod(method = "canAttack")
	private boolean blockEndermanPhantomAttackingPlayer(LivingEntity target, Operation<Boolean> original) {
		if (target.isHolding(StellarityItems.DRAGONS_EYE) && (this.is(EntityTypes.ENDERMAN) || this.is(EntityTypes.PHANTOM)))
			return false;
		return original.call(target);
	}
}