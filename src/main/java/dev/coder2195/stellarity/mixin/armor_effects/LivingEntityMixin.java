package dev.coder2195.stellarity.mixin.armor_effects;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import dev.coder2195.stellarity.registry.StellarityItems;

import java.util.function.Predicate;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {


	@Shadow
	public abstract ItemStack getItemBySlot(EquipmentSlot slot);

	@Shadow
	public abstract boolean addEffect(MobEffectInstance newEffect);

	@Shadow
	public abstract LivingEntity getLastAttacker();

	public LivingEntityMixin(EntityType<?> type, Level level) {
		super(type, level);
	}

	@WrapMethod(method = "hurtServer")
	private boolean fullSetEffects(ServerLevel level, DamageSource source, float damage, Operation<Boolean> original) {
		if (!original.call(level, source, damage)) return false;


		ItemStack head = this.getItemBySlot(EquipmentSlot.HEAD);
		ItemStack chest = this.getItemBySlot(EquipmentSlot.CHEST);
		ItemStack legs = this.getItemBySlot(EquipmentSlot.LEGS);
		ItemStack feet = this.getItemBySlot(EquipmentSlot.FEET);

		var pos = this.position();
		var castedSelf = ((LivingEntity) (Entity) this);


		if (head.is(StellarityItems.SHULKER_HELMET) && chest.is(StellarityItems.SHULKER_CHESTPLATE) && legs.is(StellarityItems.SHULKER_LEGGINGS) && feet.is(StellarityItems.SHULKER_BOOTS)) {

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

		ItemStack head = this.getItemBySlot(EquipmentSlot.HEAD);
		ItemStack chest = this.getItemBySlot(EquipmentSlot.CHEST);
		ItemStack legs = this.getItemBySlot(EquipmentSlot.LEGS);
		ItemStack feet = this.getItemBySlot(EquipmentSlot.FEET);
		if (head.is(StellarityItems.SHULKER_HELMET) && chest.is(StellarityItems.SHULKER_CHESTPLATE) && legs.is(StellarityItems.SHULKER_LEGGINGS) && feet.is(StellarityItems.SHULKER_BOOTS) && (newEffect.is(MobEffects.WITHER) || newEffect.is(MobEffects.LEVITATION)))
			return false;

		return original.call(newEffect, source);
	}


}
