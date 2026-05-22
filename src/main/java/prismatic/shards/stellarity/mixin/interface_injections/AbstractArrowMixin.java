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
		int levitationShot = stellarity$levitationShot();
		boolean voidShot = stellarity$voidShot();
		if (levitationShot > 0 && random.nextFloat() < 0.1 * levitationShot) {
			int time = random.nextIntBetweenInclusive(70 + 10 * levitationShot, 130 + 10 * levitationShot);
			mob.addEffect(new MobEffectInstance(MobEffects.LEVITATION, time));
		}
		if (voidShot) {
			mob.addEffect(new MobEffectInstance(StellarityMobEffects.VOIDED, 160));
		}
	}
}
