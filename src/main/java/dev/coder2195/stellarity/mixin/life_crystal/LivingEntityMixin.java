package dev.coder2195.stellarity.mixin.life_crystal;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;
import dev.coder2195.stellarity.registry.StellarityItems;
import dev.coder2195.stellarity.registry.StellarityMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	@Shadow
	public abstract boolean isHolding(Item item);

	@Shadow
	public abstract boolean removeEffect(Holder<MobEffect> effect);

	public LivingEntityMixin(EntityType<?> type, Level level) {
		super(type, level);
	}

	@Unique
	private Vec3 prevPos;


	@Inject(method = "tick", at = @At("HEAD"))
	private void checkForMove(CallbackInfo ci) {
		if (level().isClientSide()) return;

		if (!isHolding(StellarityItems.LIFE_CRYSTAL)) removeLifeCrystalEffects();

		var prevPos = this.prevPos;
		this.prevPos = position();
		if (prevPos == null) {
			return;
		}

		if (prevPos.equals(position())) return;
		removeLifeCrystalEffects();
	}

	@WrapMethod(method = "hurtServer")
	private boolean abortWhenHurt(ServerLevel level, DamageSource source, float damage, Operation<Boolean> original) {
		if (original.call(level, source, damage)) removeLifeCrystalEffects();
		return false;
	}

	@Unique
	private void removeLifeCrystalEffects() {
		removeAttached(StellarityDataAttachments.LIFE_CRYSTAL_HELD_AT);
		removeEffect(StellarityMobEffects.LIFE_CRYSTAL_REGENERATION);
	}
}
