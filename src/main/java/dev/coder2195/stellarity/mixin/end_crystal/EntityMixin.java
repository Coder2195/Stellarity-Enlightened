package dev.coder2195.stellarity.mixin.end_crystal;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
	@Shadow
	private Level level;

	@Shadow
	public abstract void playSound(SoundEvent sound, float volume, float pitch);

	@Inject(method = "interact", at = @At("HEAD"))
	private void interact(Player player, InteractionHand hand, Vec3 location, CallbackInfoReturnable<InteractionResult> cir) {
		if (!((Entity) (Object) this instanceof EndCrystal)) return;
		if (level.isClientSide()) {
			player.playSound(SoundEvents.AMETHYST_CLUSTER_HIT, 0.2f, 0.5f);
			player.playSound(SoundEvents.AMETHYST_CLUSTER_HIT, 0.2f, 1f);
		}
	}
}
