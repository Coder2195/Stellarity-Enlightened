package dev.coder2195.stellarity.mixin.entity_goals;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.coder2195.stellarity.entity_goal.LookAtSpiritGoal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Mob.class)
public abstract class MobMixin extends LivingEntity {
	@Shadow
	@Final
	protected GoalSelector goalSelector;

	protected MobMixin(EntityType<? extends LivingEntity> type, Level level) {
		super(type, level);
	}

	@WrapOperation(method = "<init>", at= @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;registerGoals()V"))
	private void registerGlobalGoals(Mob instance, Operation<Void> original) {
		original.call(instance);
		this.goalSelector.addGoal(0, new LookAtSpiritGoal(instance));
	}
}
