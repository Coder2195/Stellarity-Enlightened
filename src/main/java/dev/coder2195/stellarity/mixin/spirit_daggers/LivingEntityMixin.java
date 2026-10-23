package dev.coder2195.stellarity.mixin.spirit_daggers;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.item.SpiritDagger;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;
import dev.coder2195.stellarity.registry.StellarityEntityTypes;
import dev.coder2195.stellarity.tags.StellarityItemTags;
import dev.coder2195.stellarity.util.ColorUtil;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@SuppressWarnings("NonExtendableApiUsage")
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements AttachmentTarget {
	@Unique
	private final RandomSource random = RandomSource.create();

	public LivingEntityMixin(EntityType<?> type, Level level) {
		super(type, level);
	}

	@WrapMethod(method = "hurtServer")
	private boolean checkHurt(ServerLevel level, DamageSource source, float damage, Operation<Boolean> original) {
		if (!original.call(level, source, damage)) return false;

		if (random.nextBoolean()) return true;

		if (!(source.getEntity() instanceof LivingEntity livingEntity) || livingEntity.isHolding(item -> item.is(StellarityItemTags.SPIRIT_DAGGERS))) return true;

		long time = level.getGameTime();
		if (getAttachedOrElse(StellarityDataAttachments.SPIRIT_ATTRACTABLE_AT, 0L) > time) return true;

		var entity = StellarityEntityTypes.SPIRIT.create(level, EntitySpawnReason.TRIGGERED);
		if (entity == null) {
			Stellarity.LOGGER.error("Couldn't create spirit for some reason idfk");
			return true;
		}
		entity.setPos(position());
		level.addFreshEntity(entity);

		setAttached(StellarityDataAttachments.SPIRIT_ATTRACTABLE_AT, time + SpiritDagger.TIMEOUT_TICKS);

		return true;
	}

	@WrapMethod(method = "tick")
	private void attractCooldownTick(Operation<Void> original) {

		original.call();
		var level = level();
		if (!level.isClientSide()) return;

		long time = level.getGameTime();
		if (getAttachedOrElse(StellarityDataAttachments.SPIRIT_ATTRACTABLE_AT, Long.MAX_VALUE) < time) return;
		var pos = position();

		level.addParticle(
			ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, ColorUtil.hsvToRgb(random.nextFloat() * 360, random.nextFloat() + 0.3f + 0.7f, random.nextFloat() * 0.3f + 0.7f)),
			pos.x, pos.y + getBbHeight() + 0.5, pos.z, 0, 0, 0
		);

	}
}
