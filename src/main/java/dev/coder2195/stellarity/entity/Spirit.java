package dev.coder2195.stellarity.entity;

import dev.coder2195.stellarity.registry.StellarityDamageTypes;
import dev.coder2195.stellarity.registry.StellarityMobEffects;
import dev.coder2195.stellarity.tags.StellarityItemTags;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Spirit extends Entity {
	@Nullable
	private EntityReference<LivingEntity> owner = null;

	private int lookedAtTicks = 0;

	private static final EntityDataAccessor<Vector3fc> DATA_INNER_ANGULAR_VELOCITY = SynchedEntityData.defineId(Spirit.class, EntityDataSerializers.VECTOR3);
	private static final EntityDataAccessor<Vector3fc> DATA_AURA_ANGULAR_VELOCITY = SynchedEntityData.defineId(Spirit.class, EntityDataSerializers.VECTOR3);

	public Spirit(EntityType<?> type, Level level) {
		super(type, level);

		RandomSource random = RandomSource.create();
		setInnerAngularVelocity(new Vector3f(
			random.nextBoolean() ? 1 : -1 * (random.nextFloat() * 0.2f + 0.2f),
			random.nextBoolean() ? 1 : -1 * (random.nextFloat() * 0.4f + 0.5f),
			random.nextBoolean() ? 1 : -1 * (random.nextFloat() * 0.8f + 1f)
		));
		setAuraAngularVelocity(new Vector3f(
			random.nextBoolean() ? 1 : -1 * (random.nextFloat() * 0.4f + 0.4f),
			random.nextBoolean() ? 1 : -1 * (random.nextFloat() * 0.8f + 0.9f),
			random.nextBoolean() ? 1 : -1 * (random.nextFloat() * 1.6f + 1.8f)
		));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder entityData) {
		entityData.define(DATA_INNER_ANGULAR_VELOCITY, new Vector3f(0, 0, 0));
		entityData.define(DATA_AURA_ANGULAR_VELOCITY, new Vector3f(0, 0, 0));
	}


	public Vector3fc getInnerAngularVelocity() {
		return entityData.get(DATA_INNER_ANGULAR_VELOCITY);
	}

	public void setInnerAngularVelocity(Vector3fc vec) {
		entityData.set(DATA_INNER_ANGULAR_VELOCITY, vec);
	}

	public Vector3fc getAuraAngularVelocity() {
		return entityData.get(DATA_AURA_ANGULAR_VELOCITY);
	}

	public void setAuraAngularVelocity(Vector3fc vec) {
		entityData.set(DATA_AURA_ANGULAR_VELOCITY, vec);
	}

	@Override
	public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
		return false;
	}

	public void setOwner(@Nullable EntityReference<LivingEntity> owner) {
		this.owner = owner;
	}

	public void setOwner(@Nullable LivingEntity owner) {
		this.owner = owner == null ? null : EntityReference.of(owner);
	}

	public @Nullable LivingEntity getOwner() {
		return owner == null ? null : owner.getEntity(level(), LivingEntity.class);
	}

	// transplanted from vanilla mc for being in livingEntityClass
	public boolean isLookingAtMe(
		final LivingEntity target, final double coneSize, final boolean adjustForDistance, final boolean seeThroughTransparentBlocks, final double... gazeHeights
	) {
		Vec3 look = target.getViewVector(1.0F).normalize();

		for (double gazeHeight : gazeHeights) {
			Vec3 dir = new Vec3(this.getX() - target.getX(), gazeHeight - target.getEyeY(), this.getZ() - target.getZ());
			double dist = dir.length();
			dir = dir.normalize();
			double dot = look.dot(dir);
			if (dot > 1.0 - coneSize / (adjustForDistance ? dist : 1.0)
				&& target.hasLineOfSight(this, seeThroughTransparentBlocks ? ClipContext.Block.VISUAL : ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, gazeHeight)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void tick() {
		super.tick();

		var level = level();
		var position = position();
		if (!(level instanceof ServerLevel serverLevel)) {

			return;
		}

		var owner = getOwner();
		if (owner == null) return;

		var mainHand = owner.getMainHandItem();
		var offHand = owner.getOffhandItem();
		if (!mainHand.is(StellarityItemTags.SPIRIT_DAGGERS) || !offHand.is(StellarityItemTags.SPIRIT_DAGGERS)) return;

		if (isLookingAtMe(owner, 0.025, true, false, this.getEyeY())) {
			lookedAtTicks++;
		} else {
			lookedAtTicks = 0;
			return;
		}

		if (lookedAtTicks < 10) return;

		List<Vec3> hurtPositions = new ArrayList<>();



		var damageSource = damageSources().source(StellarityDamageTypes.SPIRIT_SLASH, this, owner);

		for (var entity : level.getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(3.5), e -> !(e.isInvulnerableTo(serverLevel, damageSource) || e.is(owner)))) {
			hurtPositions.add(entity.position());

			int maxSlownessTicks = 1;
			float damage = 5;
			int fireTicks = 0;
			MutableFloat knockback = new MutableFloat(0);


			for (var itemStack : List.of(mainHand, offHand)) {
				var enchantments = itemStack.getEnchantments();
				for (Holder<Enchantment> enchantHolder : enchantments.keySet()) {
					if (!enchantHolder.isBound()) continue;

					int enchantLevel = enchantments.getLevel(enchantHolder);
					if (enchantHolder.is(Enchantments.SWEEPING_EDGE)) {
						damage += 0.5f * enchantLevel;
						continue;
					}
					if (enchantHolder.is(Enchantments.FIRE_ASPECT)) {
						fireTicks += 15 * enchantLevel;
						continue;
					}
					if (enchantHolder.is(Enchantments.BANE_OF_ARTHROPODS)) {
						maxSlownessTicks += 15 + (enchantLevel * 10);
						continue;
					}

					var enchantment = enchantHolder.value();

					var context = Enchantment.damageContext(serverLevel, enchantments.getLevel(enchantHolder), entity, damageSource);
					for (var effect : enchantment.getEffects(EnchantmentEffectComponents.DAMAGE).stream().filter(effect -> effect.matches(context)).toList()) {
						damage += effect.effect().process(enchantLevel, random, damage) * 0.6f;
					}
					enchantment.modifyKnockback(serverLevel, enchantLevel, itemStack, entity, damageSource, knockback);
				}
			}

			entity.hurtServer(serverLevel, damageSource, damage);
			if (maxSlownessTicks > 20) entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, random.nextInt(20, maxSlownessTicks)));
			entity.setRemainingFireTicks(entity.getRemainingFireTicks() + fireTicks);
			// in livingentity usual knockback
			var directionVector = position.subtract(entity.position()).with(Direction.Axis.Y, 0).normalize();

			entity.knockback(knockback.floatValue(), directionVector.x, directionVector.z, damageSource, damage, false);
		}

		owner.teleportTo(position.x, position.y, position.z);
		owner.addEffect(new MobEffectInstance(StellarityMobEffects.ASSASINATION, 4 * 20));

		this.discard();
	}

	@Override
	protected void readAdditionalSaveData(ValueInput input) {
		input.read("owner", EntityReference.<LivingEntity>codec()).ifPresent(this::setOwner);
		input.read("inner_angular_velocity", ExtraCodecs.VECTOR3F).ifPresent(this::setInnerAngularVelocity);
		input.read("aura_angular_velocity", ExtraCodecs.VECTOR3F).ifPresent(this::setAuraAngularVelocity);
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		output.storeNullable("owner", EntityReference.codec(), owner);
		output.storeNullable("inner_angular_velocity", ExtraCodecs.VECTOR3F, getInnerAngularVelocity());
		output.storeNullable("aura_angular_velocity", ExtraCodecs.VECTOR3F, getAuraAngularVelocity());
	}
}
