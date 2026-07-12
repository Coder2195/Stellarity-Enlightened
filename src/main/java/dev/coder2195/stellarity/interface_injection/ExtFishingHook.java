package dev.coder2195.stellarity.interface_injection;

import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;

@SuppressWarnings("NonExtendableApiUsage")
public interface ExtFishingHook extends AttachmentTarget {
	default void stellarity$setVoidFishingBuff(boolean buff) {
		this.setAttached(StellarityDataAttachments.BUFF_VOID_FISHING, buff);
	}

	default boolean stellarity$getVoidFishingBuff() {
		return this.getAttachedOrElse(StellarityDataAttachments.BUFF_VOID_FISHING, false);
	}


}
