package prismatic.shards.stellarity.mixin.interface_injections;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import prismatic.shards.stellarity.interface_injection.ExtAbstractArrow;
import prismatic.shards.stellarity.registry.StellarityMobEffects;


@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin extends Projectile implements ExtAbstractArrow {
	public AbstractArrowMixin(EntityType<? extends Projectile> entityType, Level level) {
		super(entityType, level);
	}

	@Inject(method = "doPostHurtEffects", at = @At("TAIL"))
	private void applyEffects(CallbackInfo ci, @Local(argsOnly = true, name = "mob") LivingEntity mob) {
		for (var effect : stellarity$mobEffects()) {
			mob.addEffect(effect, getOwner());
		}
	}
}
