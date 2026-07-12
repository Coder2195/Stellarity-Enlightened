package dev.coder2195.stellarity.mixin.bow_attributes;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import dev.coder2195.stellarity.registry.item.StellarityBow;

import java.util.Objects;

@Mixin(BowItem.class)
public abstract class BowItemMixin extends ProjectileWeaponItem {
	public BowItemMixin(Properties properties) {
		super(properties);
	}

	@WrapOperation(method = "releaseUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/Entity;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V"))
	private void customBowSound(Level instance, Entity except, double x, double y, double z, SoundEvent sound, SoundSource source, float volume, float pitch, Operation<Void> original, @Local(name = "projectile") ItemStack projectile, @Local(name = "itemStack") ItemStack itemStack, @Local(argsOnly = true, name = "level") Level level, @Local(argsOnly = true, name = "entity") LivingEntity entity) {
		if (this instanceof StellarityBow bow) {
			var shootSound = bow.shootSound(itemStack, projectile, level, entity);
			if (shootSound == null) return;

			sound = Objects.requireNonNullElse(shootSound._1(), sound);
			volume = Objects.requireNonNullElse(shootSound._2(), volume);
			pitch = Objects.requireNonNullElse(shootSound._3(), pitch);

		}
		original.call(instance, except, x, y, z, sound, source, volume, pitch);
	}
}
