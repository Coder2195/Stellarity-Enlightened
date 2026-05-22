package prismatic.shards.stellarity.mixin.enchantments;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import prismatic.shards.stellarity.key.StellarityEnchantments;
import prismatic.shards.stellarity.registry.StellarityMobEffects;

import java.util.ArrayList;

@Mixin(ProjectileWeaponItem.class)
public class ProjectileWeaponItemMixin {

	@WrapOperation(method = "shoot", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ProjectileWeaponItem;createProjectile(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;Z)Lnet/minecraft/world/entity/projectile/Projectile;"))
	private Projectile levitationShot(ProjectileWeaponItem instance, Level level, LivingEntity livingEntity, ItemStack itemStack, ItemStack itemStack2, boolean b, Operation<Projectile> original) {
		var projectile = original.call(instance, level, livingEntity, itemStack, itemStack2, b);

		if (projectile instanceof Arrow arrow) {
			ArrayList<MobEffectInstance> effects = new ArrayList<>();
			for (var entry : itemStack.getEnchantments().entrySet()) {
				int enchantLevel = entry.getIntValue();
				if (entry.getKey().is(StellarityEnchantments.LEVITATION_SHOT)) {
					var random = level.getRandom();
					var offsetLevel = enchantLevel - 1;

					if (random.nextDouble() < (15 + 10d * offsetLevel) / (100 + 12d * offsetLevel)) {
						effects.add(new MobEffectInstance(MobEffects.LEVITATION, random.nextIntBetweenInclusive(20 * 4, 20 * 7) + 10 * offsetLevel));
					}
				}
				if (entry.getKey().is(StellarityEnchantments.VOID_SHOT)) {
					effects.add(new MobEffectInstance(StellarityMobEffects.VOIDED, 160));
				}
			}
			arrow.stellarity$setMobEffects(effects);
		}

		return projectile;
	}
}
