package dev.coder2195.stellarity.mixin.dragonblade;

import dev.coder2195.stellarity.registry.StellarityDataAttachments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	public LivingEntityMixin(EntityType<?> type, Level level) {
		super(type, level);
	}

	@Inject(method = "tick", at = @At("HEAD"))
	private void dragonStackTick(CallbackInfo ci) {
		var level = level();
		if (level.isClientSide()) return;

		var dragonStacks = getAttached(StellarityDataAttachments.DRAGON_STACKS);
		if (dragonStacks == null) return;
		if (level.getGameTime() > dragonStacks.expiresAt()) {

			this.removeAttached(StellarityDataAttachments.DRAGON_STACKS);
		}
	}


}
