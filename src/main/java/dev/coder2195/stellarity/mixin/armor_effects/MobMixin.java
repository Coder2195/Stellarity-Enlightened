package dev.coder2195.stellarity.mixin.armor_effects;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.coder2195.stellarity.mixin_helper.ArmorEffectsHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Mob.class)
public abstract class MobMixin extends LivingEntity {

	protected MobMixin(EntityType<? extends LivingEntity> type, Level level) {
		super(type, level);
	}

	@WrapMethod(method = "doHurtTarget")
	private boolean championIncreaseDamage(ServerLevel level, Entity target, Operation<Boolean> original) {
		if (!original.call(level, target)) return false;

		ArmorEffectsHelper.championIncreaseDamage(this, level);

		return true;
	}

}
