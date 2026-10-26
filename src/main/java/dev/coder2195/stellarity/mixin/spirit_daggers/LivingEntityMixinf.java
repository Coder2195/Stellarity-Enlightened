package dev.coder2195.stellarity.mixin.spirit_daggers;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.item.SpiritDagger;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;
import dev.coder2195.stellarity.registry.StellarityEntityTypes;
import dev.coder2195.stellarity.registry.StellarityItems;
import dev.coder2195.stellarity.registry.StellaritySoundEvents;
import dev.coder2195.stellarity.tags.StellarityDamageTypeTags;
import dev.coder2195.stellarity.tags.StellarityItemTags;
import dev.coder2195.stellarity.util.ColorUtil;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@SuppressWarnings("NonExtendableApiUsage")
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixinf extends Entity implements AttachmentTarget {

	@Shadow
	public abstract boolean isDeadOrDying();

	public LivingEntityMixinf(EntityType<?> type, Level level) {
		super(type, level);
	}

	@WrapMethod(method = "hurtServer")
	private boolean checkHurt(ServerLevel level, DamageSource source, float damage, Operation<Boolean> original) {
		if (!original.call(level, source, damage)) return false;

		if (random.nextBoolean() || source.is(StellarityDamageTypeTags.BLOCKS_SPIRIT_SUMMONING) || isDeadOrDying()) return true;
		if (!(source.getEntity() instanceof LivingEntity attacker && attacker.isHolding(item -> item.is(StellarityItemTags.SPIRIT_DAGGERS)))) return true;

		long time = level.getGameTime();
		if (getAttachedOrElse(StellarityDataAttachments.SPIRIT_ATTRACTABLE_AT, 0L) > time) return true;

		var spirit = StellarityEntityTypes.SPIRIT.create(level, EntitySpawnReason.TRIGGERED);
		if (spirit == null) {
			Stellarity.LOGGER.error("Couldn't create spirit for some reason idfk");
			return true;
		}
		spirit.setPos(position().add(0, (getBbHeight() - spirit.getBbHeight()) / 2, 0));
		spirit.setOwner(attacker);
		level.addFreshEntity(spirit);

		setAttached(StellarityDataAttachments.SPIRIT_ATTRACTABLE_AT, time + SpiritDagger.TIMEOUT_TICKS);

		return true;
	}

	@WrapMethod(method = "handleHandSwap")
	private void soundEffects(Map<EquipmentSlot, ItemStack> changedItems, Operation<Void> original) {
		original.call(changedItems);
		var level = level();
		var position = position();
		var itemSet = changedItems.values();
		for (var stack : itemSet) {
			if (stack.is(StellarityItems.THE_BEGINNING)) {
				level.playSound(null, position.x, position.y, position.z, StellaritySoundEvents.THE_BEGINNING_EQUIP, SoundSource.NEUTRAL, 1, 1);
				continue;
			}

			if (stack.is(StellarityItems.THE_END)) level.playSound(null, position.x, position.y, position.z, StellaritySoundEvents.THE_END_EQUIP, SoundSource.NEUTRAL, 1, 1);
		}
	}


	@WrapMethod(method = "tick")
	private void attractCooldownTick(Operation<Void> original) {

		original.call();
		var level = level();
		if (!level.isClientSide()) return;

		long time = level.getGameTime();
		if (getAttachedOrElse(StellarityDataAttachments.SPIRIT_ATTRACTABLE_AT, 0L) < time) return;
		var pos = position();

		level.addParticle(
			ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, ColorUtil.hsvToRgb(random.nextFloat() * 360, random.nextFloat() + 0.3f + 0.7f, random.nextFloat() * 0.3f + 0.7f)),
			pos.x, pos.y + getBbHeight() + 0.5, pos.z, 0, 0, 0
		);

	}
}
