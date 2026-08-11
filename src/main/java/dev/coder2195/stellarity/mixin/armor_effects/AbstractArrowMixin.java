package dev.coder2195.stellarity.mixin.armor_effects;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.coder2195.stellarity.interface_injection.ExtAbstractArrow;
import dev.coder2195.stellarity.mixin_helper.ArmorEffectsHelper;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;
import dev.coder2195.stellarity.registry.StellarityItems;
import dev.coder2195.stellarity.util.FloralBloom;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin extends Projectile implements ExtAbstractArrow {
	public AbstractArrowMixin(EntityType<? extends Projectile> type, Level level) {
		super(type, level);
	}

	@WrapMethod(method = "shoot")
	private void checkFloralPerks(double xd, double yd, double zd, float pow, float uncertainty, Operation<Void> original) {
		var owner = getOwner();
		boolean hasChestplate = false;
		if (owner instanceof LivingEntity livingEntity) {
			hasChestplate = livingEntity.getItemBySlot(EquipmentSlot.CHEST).is(StellarityItems.FLORAL_CHESTPLATE);
			if (livingEntity.getItemBySlot(EquipmentSlot.LEGS).is(StellarityItems.FLORAL_LEGGINGS)) stellarity$setDamageMultiplier(stellarity$getDamageMultiplier() * 1.1);
			if (ArmorEffectsHelper.isFullFloralArmor(livingEntity)) stellarity$setFloralBloomApplier(stellarity$defaultFloralBloomApplier());
		}

		original.call(xd, yd, zd, pow * (hasChestplate ? 1.25f : 1), uncertainty);
	}

	@Inject(method = "doPostHurtEffects", at = @At("HEAD"))
	private void applyFloralBloom(LivingEntity mob, CallbackInfo ci) {
		var level = level();
		if (level.isClientSide()) return;

		var floralBloom = mob.getAttached(StellarityDataAttachments.FLORAL_BLOOM);
		var applier = stellarity$getFloralBloomApplier();
		if (applier == null) return;

		mob.setAttached(StellarityDataAttachments.FLORAL_BLOOM, new FloralBloom(
			floralBloom == null ? applier.baseDamage() : floralBloom.damage() + applier.damageStack(),
			floralBloom == null ? level().getGameTime() + applier.baseExplodeDelay() : floralBloom.explodeAt() + applier.explodeDelayStack()
		));
	}
}
