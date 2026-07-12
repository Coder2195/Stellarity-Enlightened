package dev.coder2195.stellarity.interface_injection;

import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.world.effect.MobEffectInstance;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("NonExtendableApiUsage")
public interface ExtAbstractArrow extends AttachmentTarget {
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
