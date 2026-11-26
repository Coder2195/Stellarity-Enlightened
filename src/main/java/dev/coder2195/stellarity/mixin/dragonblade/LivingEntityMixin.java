package dev.coder2195.stellarity.mixin.dragonblade;

import dev.coder2195.stellarity.item.Dragonblade;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
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
		var gameTime = level.getGameTime();

		var dragonStacks = getAttached(StellarityDataAttachments.DRAGON_STACKS);
		if (dragonStacks == null) return;

		short stacks = dragonStacks.stacks();
		// todo: eventually move into client ticking for dragonblade only looks
		if (level.isClientSide()) {
			if (stacks < 1) return;

			var position = position();
			var topHeadPos = position.y + getBbHeight() + 0.2;


			if (dragonStacks.punched()) {
				level.addAlwaysVisibleParticle(ParticleTypes.SMOKE, position.x, topHeadPos + 1, position.z, 0, 0, 0);
			} else {
				for (int i = 0; i < Dragonblade.PARTICLES.length; i++) {
					if (stacks <= i) break;
					level.addAlwaysVisibleParticle(Dragonblade.PARTICLES[i], position.x, topHeadPos + 0.25 * i, position.z, 0, 0, 0);
				}
			}

			return;
		}

		if (gameTime > dragonStacks.expiresAt()) this.removeAttached(StellarityDataAttachments.DRAGON_STACKS);
	}


}
