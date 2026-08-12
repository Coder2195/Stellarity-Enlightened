package dev.coder2195.stellarity.mixin.elytra;

import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import dev.coder2195.stellarity.registry.StellarityDataComponents;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	public LivingEntityMixin(EntityType<?> type, Level level) {
		super(type, level);
	}

	@Shadow
	public abstract boolean isFallFlying();

	@Shadow
	public abstract ItemStack getItemBySlot(EquipmentSlot slot);

	@Unique
	private Vec3 posOld;

	@Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;updateSwimAmount()V"))
	private void particles(CallbackInfo ci) {
		var level = level();
		if (!level.isClientSide() || !isFallFlying()) return;
		if (posOld == null) {
			posOld = position();
			return;
		}

		var dyedColor = getItemBySlot(EquipmentSlot.CHEST).get(StellarityDataComponents.COLOR);
		if (dyedColor == null) return;
		int rgb = dyedColor.rgb();
		var particle = new DustColorTransitionOptions(rgb, ARGB.average(rgb, 0xFFFFFF), 1.0f);
		drawTrail(position(), particle, level);
	}

	@Unique
	@SuppressWarnings("DuplicatedCode")
	private void drawTrail(Vec3 target, ParticleOptions options, Level level) {
		var delta = target.subtract(posOld);
		var steps = delta.length() / 0.1;
		var stepVec = delta.scale(1 / steps);
		for (int i = 0; i < steps; i++) {
			level.addAlwaysVisibleParticle(options, true, posOld.x + i * stepVec.x, posOld.y + i * stepVec.y, posOld.z + i * stepVec.z, 0, 0, 0);
		}

		posOld = posOld.add(stepVec.scale((int) steps));
	}
}
