package dev.coder2195.stellarity.mixin.spellbook;

import dev.coder2195.stellarity.item.BookOfReturn;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;
import dev.coder2195.stellarity.registry.StellarityItems;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	public LivingEntityMixin(EntityType<?> type, Level level) {
		super(type, level);
	}

	@Unique
	private Vec3 previousPos = null;
	@Unique
	private float rotation = 0;

	@Inject(method = "tick", at = @At("HEAD"))
	private void returnSpellTick(CallbackInfo ci) {
		var returnAt = getAttached(StellarityDataAttachments.RETURN_SPELL_AT);

		var here = this.position();
		if (returnAt == null) {
			previousPos = here;
			return;
		}

		var level = this.level();
		long remainingTime = Math.max(0, returnAt - level.getGameTime());


		rotation += 2f / (0.3f * remainingTime + 2.5f);

		if (level.isClientSide()) {
			for (Vec3 vec : BookOfReturn.PARTICLE_POSITIONS) {
				var transformed = vec.yRot(rotation).add(here);
				level.addParticle(BookOfReturn.RING_PARTICLE, transformed.x, transformed.y, transformed.z, 0, 0, 0);

			}

			double portalSpeed = 2.5 - remainingTime / (double) BookOfReturn.USE_DELAY;
			for (int i = 0; i < remainingTime / 35d; i++) {
				level.addParticle(ParticleTypes.PORTAL, here.x, here.y + 1.2, here.z, random.nextGaussian() * portalSpeed, random.nextGaussian() * portalSpeed, random.nextGaussian() * portalSpeed);
				level.addParticle(ParticleTypes.ENCHANT, here.x, here.y + 1.2, here.z, random.nextGaussian() * portalSpeed, random.nextGaussian() * portalSpeed, random.nextGaussian() * portalSpeed);
			}
		}

		if (remainingTime == 0) {
			removeAttached(StellarityDataAttachments.RETURN_SPELL_AT);
			if (((Object) this) instanceof Player player) {
				player.getCooldowns().addCooldown(new ItemStack(StellarityItems.BOOK_OF_RETURN), BookOfReturn.RECHARGE_TIME);

				if (player instanceof ServerPlayer serverPlayer) {
					serverPlayer.teleport(serverPlayer.findRespawnPositionAndUseSpawnBlock(false,_ ->
						serverPlayer.level().sendParticles(ColorParticleOption.create(ParticleTypes.FLASH, -1), serverPlayer.getX(), serverPlayer.getY() + 1.2, serverPlayer.getZ(), 1, 0, 0, 0, 0)
					));
				}
			}
		}

		if (previousPos != null && previousPos.distanceTo(here) > 0.1) {

			removeAttached(StellarityDataAttachments.RETURN_SPELL_AT);
		}

		previousPos = here;

	}

}
