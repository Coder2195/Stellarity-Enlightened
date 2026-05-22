package prismatic.shards.stellarity.interface_injection;

import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.world.effect.MobEffectInstance;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.StellarityDataAttachments;

import java.util.HashMap;
import java.util.List;

@SuppressWarnings("NonExtendableApiUsage")
public interface ExtAbstractArrow extends AttachmentTarget {
	default List<MobEffectInstance> stellarity$mobEffects() {
		return getAttached(StellarityDataAttachments.MOB_EFFECTS);
	}

	default void stellarity$setMobEffects(List<MobEffectInstance> effects) {
		setAttached(StellarityDataAttachments.MOB_EFFECTS, effects);
	}

}
