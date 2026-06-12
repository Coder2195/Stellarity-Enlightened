package prismatic.shards.stellarity.mixin.void_totem;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DeathProtection;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import prismatic.shards.stellarity.StellarityConfig;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	@Shadow
	public abstract boolean addEffect(MobEffectInstance newEffect);

	public LivingEntityMixin(EntityType<?> type, Level level) {
		super(type, level);
	}

	@ModifyExpressionValue(method = "checkTotemDeathProtection", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/damagesource/DamageSource;is(Lnet/minecraft/tags/TagKey;)Z"))
	private boolean preventVoidSaveDeath(boolean original, @Local(name = "killingDamage", argsOnly = true) DamageSource killingDamage) {
		if (level() instanceof ServerLevel serverLevel && killingDamage.is(DamageTypes.FELL_OUT_OF_WORLD) && StellarityConfig.get(serverLevel.getServer()).enableTotemVoidSaving())
			return false;
		return original;
	}

	@WrapOperation(method = "checkTotemDeathProtection", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/component/DeathProtection;applyEffects(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;)V"))
	private void executeVoidSave(DeathProtection instance, ItemStack itemStack, LivingEntity entity, Operation<Void> original, @Local(name = "killingDamage", argsOnly = true) DamageSource killingDamage) {
		var level = entity.level();
		if (!StellarityConfig.get(level).enableTotemVoidSaving()) return;

		if (!killingDamage.is(DamageTypes.FELL_OUT_OF_WORLD)) {
			original.call(instance, itemStack, entity);
			return;
		}

		teleportTo(getX(), level().getMaxY(), getZ());
		resetFallDistance();
		addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 60 * 20));
	}
}
