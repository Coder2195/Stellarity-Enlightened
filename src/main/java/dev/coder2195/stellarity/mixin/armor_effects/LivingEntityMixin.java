package dev.coder2195.stellarity.mixin.armor_effects;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.coder2195.stellarity.mixin_helper.ArmorEffectsHelper;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.PowerParticleOption;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	@Shadow
	public abstract ItemStack getItemBySlot(EquipmentSlot slot);

	@Shadow
	public abstract boolean addEffect(MobEffectInstance newEffect);

	@Shadow
	public abstract LivingEntity getLastAttacker();

	@Shadow
	@Nullable
	public abstract AttributeInstance getAttribute(Holder<Attribute> attribute);

	@Shadow
	protected abstract AABB getHitbox();

	public LivingEntityMixin(EntityType<?> type, Level level) {
		super(type, level);
	}

	@WrapMethod(method = "hurtServer")
	private boolean fullShulkerSetEffects(ServerLevel level, DamageSource source, float damage, Operation<Boolean> original) {
		if (!original.call(level, source, damage)) return false;

		var pos = this.position();
		var castedSelf = ((LivingEntity) (Entity) this);


		if (ArmorEffectsHelper.isFullChampionArmor((castedSelf))) {

			Predicate<LivingEntity> filter = attackFilter(castedSelf);

			var hostiles = level.getEntitiesOfClass(LivingEntity.class, new AABB(pos.add(-5, -3, -5), pos.add(5, 3, 5)), filter);

			int size = hostiles.size();

			if (size >= 4) {
				addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 30 * 20));
			} else if (size < 3) {
				var moreHostiles = level.getEntitiesOfClass(LivingEntity.class, new AABB(pos.add(-32, -8, -32), pos.add(32, 8, 32)), filter);
				var moreSize = moreHostiles.size();
				for (int i = size, j = 0; i < 3; i++, j++) {
					if (j >= moreSize) break;
					hostiles.add(moreHostiles.get(j));
				}
			}

			var totalSize = hostiles.size();
			if (totalSize > 0) for (int i = 0; i < 3; i++) {
				if (random.nextBoolean()) break;
				var shulkerBullet = new ShulkerBullet(level, castedSelf, hostiles.get(i % totalSize), null);
				shulkerBullet.setPos(position().add(0, getEyeHeight() * 0.6, 0));
				level.addFreshEntity(shulkerBullet);

			}
		}
		return true;
	}

	@Unique
	private @NonNull Predicate<LivingEntity> attackFilter(LivingEntity castedSelf) {
		final var attacker = getLastAttacker();

		return castedSelf instanceof Monster monster ? (e) -> e != castedSelf && castedSelf.canAttack(e) && (
			e.is(attacker) || e instanceof Player || e instanceof Mob mob && castedSelf == mob.getTarget()
		) : castedSelf instanceof Player player ? (e) -> e != castedSelf && castedSelf.canAttack(e) && (
			e.is(attacker) || e instanceof Monster || e instanceof Mob mob && castedSelf == mob.getTarget()
		) : (e) -> e != castedSelf && castedSelf.canAttack(e) && (e.is(attacker) || e instanceof Mob mob && castedSelf == mob.getTarget());
	}

	@WrapMethod(method = "addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z")
	private boolean blockEffects(MobEffectInstance newEffect, Entity source, Operation<Boolean> original) {
		// will be for totem stuff
		if (newEffect.is(MobEffects.LEVITATION) && source == this) return original.call(newEffect, source);

		if (ArmorEffectsHelper.isFullShulkerArmor((LivingEntity) (Object) this) && (newEffect.is(MobEffects.WITHER) || newEffect.is(MobEffects.LEVITATION)))
			return false;

		return original.call(newEffect, source);
	}

	@Inject(method = "tick", at=@At("HEAD"))
	private void championTick(CallbackInfo ci) {
		var level = level();
		var castedSelf = (LivingEntity) (Object) this;
		ParticleOptions particle = castedSelf instanceof Player player && player.nameAndId().name().equals("kohara_") ? ParticleTypes.CHERRY_LEAVES : PowerParticleOption.create(ParticleTypes.DRAGON_BREATH, 1);

		var hitbox = getHitbox();
		var xDist = hitbox.getXsize();
		var yDist = hitbox.getYsize();
		var zDist = hitbox.getZsize();
		var center = hitbox.getCenter().subtract(getHeadLookAngle().normalize().scale((xDist + yDist) / 4));


		if (ArmorEffectsHelper.isFullChampionArmor(castedSelf) && level.isClientSide()) {
			for (int i=0; i<2; i++) level.addParticle(particle, true,
				false,
				center.x + xDist * (random.nextDouble() - 0.5),
				center.y + yDist * (random.nextDouble() - 0.5),
				center.z + zDist * (random.nextDouble() - 0.5),
				0, 0, 0
			);
		}

		var gameTime = level().getGameTime();
		var attachedCooldown = getAttached(StellarityDataAttachments.CHAMPION_BOOST_UNTIL);
		if (attachedCooldown == null) {
			setAttached(StellarityDataAttachments.CHAMPION_BOOST_UNTIL, gameTime + ArmorEffectsHelper.CHAMPION_BOOST_DURATION);
			return;
		}

		if (gameTime < attachedCooldown) return;

		removeAttached(StellarityDataAttachments.CHAMPION_BOOST_UNTIL);

		var attackDamage = getAttribute(Attributes.ATTACK_DAMAGE);
		if (attackDamage == null || attackDamage.getModifier(ArmorEffectsHelper.CHAMPION_MODIFIER) == null) return;
		attackDamage.removeModifier(ArmorEffectsHelper.CHAMPION_MODIFIER);
	}


}
