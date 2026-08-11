package dev.coder2195.stellarity.interface_injection;

import dev.coder2195.stellarity.util.FloralBloom;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.world.effect.MobEffectInstance;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("NonExtendableApiUsage")
public interface ExtAbstractArrow extends AttachmentTarget {
	FloralBloom.Applier DEFAULT_FLORAL_BLOOM_APPLIER = new FloralBloom.Applier(4, 2, 70, 30);

	default FloralBloom.Applier stellarity$getFloralBloomApplier() {
		return getAttachedOrElse(StellarityDataAttachments.FLORAL_BLOOM_APPLIER, DEFAULT_FLORAL_BLOOM_APPLIER);
	}

	default void stellarity$setFloralBloomApplier(FloralBloom.Applier applier) {
		setAttached(StellarityDataAttachments.FLORAL_BLOOM_APPLIER, applier);
	}

	default FloralBloom.Applier stellarity$defaultFloralBloomApplier() {
		return DEFAULT_FLORAL_BLOOM_APPLIER;
	}

	default double stellarity$getDamageMultiplier() {
		return getAttachedOrElse(StellarityDataAttachments.ARROW_DAMAGE_MULTIPLIER, 1.0);
	}

	default void stellarity$setDamageMultiplier(double multiplier) {
		setAttached(StellarityDataAttachments.ARROW_DAMAGE_MULTIPLIER, multiplier);
	}

	default List<MobEffectInstance> stellarity$mobEffects() {
		return getAttachedOrElse(StellarityDataAttachments.MOB_EFFECTS, List.of());
	}

	default List<MobEffectInstance> stellarity$mobEffectsMutable() {
		return new ArrayList<>(stellarity$mobEffects());
	}

	default void stellarity$setMobEffects(List<MobEffectInstance> effects) {
		setAttached(StellarityDataAttachments.MOB_EFFECTS, effects);
	}

	default void stellarity$addMobEffects(MobEffectInstance... effects) {
		var list = stellarity$mobEffectsMutable();
		list.addAll(Arrays.asList(effects));
		stellarity$setMobEffects(list);
	}
}
