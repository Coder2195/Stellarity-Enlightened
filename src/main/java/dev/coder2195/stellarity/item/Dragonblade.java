package dev.coder2195.stellarity.item;

import dev.coder2195.stellarity.mixin.accessor.ProjectileUtilAccessor;
import dev.coder2195.stellarity.registry.StellarityDamageTypes;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;
import dev.coder2195.stellarity.registry.StellarityMobEffects;
import dev.coder2195.stellarity.registry.StellarityToolMaterials;
import dev.coder2195.stellarity.util.DragonStacks;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.DamageResistant;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;

public class Dragonblade extends Item {
	public static final Properties PROPERTIES = new Properties().sword(StellarityToolMaterials.DRAGONBLADE, 3, -2.9f).component(DataComponents.UNBREAKABLE, Unit.INSTANCE).delayedComponent(
		DataComponents.DAMAGE_RESISTANT, provider -> new DamageResistant(HolderSet.direct(provider::getOrThrow,
			DamageTypes.IN_FIRE, DamageTypes.ON_FIRE, DamageTypes.LAVA, DamageTypes.LAVA, DamageTypes.CAMPFIRE,
			DamageTypes.CACTUS, DamageTypes.FALLING_ANVIL, DamageTypes.LIGHTNING_BOLT, DamageTypes.FIREBALL, DamageTypes.UNATTRIBUTED_FIREBALL,
			DamageTypes.EXPLOSION, DamageTypes.PLAYER_EXPLOSION, DamageTypes.WITHER, DamageTypes.WITHER_SKULL
		))
	).rarity(Rarity.EPIC);
	public static final int STACK_DURATION = 16 * 20;

	public Dragonblade(Properties properties) {
		super(properties);
	}

	public static final ParticleOptions[] PARTICLES = {
		new DustParticleOptions(0x2d004b, 1.5f), new DustParticleOptions(0x6500a9, 1.5f), new DustParticleOptions(0x9a00100, 1.5f)
	};

	@Override
	public void postHurtEnemy(ItemStack itemStack, LivingEntity mob, LivingEntity attacker) {
		super.postHurtEnemy(itemStack, mob, attacker);

		if (attacker.isCrouching() && mob.getAttachedOrElse(StellarityDataAttachments.DRAGON_STACKS, DragonStacks.EMPTY).stacks() >= 3) {
			dragonBladePunch(itemStack, mob, attacker);
			return;
		}
		addDragonStack(mob);
	}

	public static void addDragonStack(LivingEntity mob) {
		var level = mob.level();
		if (level.isClientSide()) return;
		var gameTime = level.getGameTime();

		var currentStack = mob.getAttachedOrElse(StellarityDataAttachments.DRAGON_STACKS, DragonStacks.EMPTY);
		mob.setAttached(StellarityDataAttachments.DRAGON_STACKS, new DragonStacks(currentStack.stacks() < 3 ? (short) (currentStack.stacks() + 1) : currentStack.stacks(), gameTime + STACK_DURATION));
	}

	public static void dragonBladePunch(ItemStack itemStack, LivingEntity mob, LivingEntity attacker) {

		var level = mob.level();
		if (!(level instanceof ServerLevel serverLevel)) return;
		mob.addEffect(new MobEffectInstance(StellarityMobEffects.VOIDED, 10 * 20));
		mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 7 * 20));
		mob.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 20, 255));
		var damageSource = new DamageSource(level.damageSources().damageTypes.getOrThrow(StellarityDamageTypes.DRAGONBLADE_PUNCH), attacker);

		float damage = 7 + itemStack.getEnchantments().getLevel(level.registryAccess().getOrThrow(Enchantments.KNOCKBACK)) * 1.5f;

		var mobPos = mob.position();
		var directionVector = mobPos.subtract(attacker.position()).with(Direction.Axis.Y, 0);
		directionVector = directionVector.scale(8 / directionVector.length()).with(Direction.Axis.Y, 0.5);

		var hitResult = ProjectileUtilAccessor.getHitResult(mobPos, mob, _ -> false, directionVector, level, 0.1f, ClipContext.Block.COLLIDER);

		if (hitResult.getType() == HitResult.Type.BLOCK) {
			damage += 25;
			for (var nearbyEntity : level.getEntitiesOfClass(LivingEntity.class, mob.getBoundingBox().inflate(4), entity -> !entity.equals(mob) && !entity.isInvulnerableTo(serverLevel, damageSource))) {
				nearbyEntity.hurtServer(serverLevel, damageSource, 4);
			}
		}

		var finalPos = hitResult.getLocation();
		mob.hurtServer(serverLevel, damageSource, damage);
		mob.setPos(finalPos.x, finalPos.y, finalPos.z);
		mob.setDeltaMovement(directionVector.scale(0.1));
		mob.removeAttached(StellarityDataAttachments.DRAGON_STACKS);
	}


}
