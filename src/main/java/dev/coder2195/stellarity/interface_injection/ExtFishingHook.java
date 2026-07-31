package dev.coder2195.stellarity.interface_injection;

import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;
import net.minecraft.util.Unit;

@SuppressWarnings("NonExtendableApiUsage")
public interface ExtFishingHook extends AttachmentTarget {
	default void stellarity$setBuffVoidFishing() {
		this.setAttached(StellarityDataAttachments.BUFF_VOID_FISHING, Unit.INSTANCE);
	}

	default boolean stellarity$isBuffVoidFishing() {
		return this.getAttached(StellarityDataAttachments.BUFF_VOID_FISHING) != null;
	}

	default void stellarity$clearBuffVoidFishing() {
		this.setAttached(StellarityDataAttachments.BUFF_VOID_FISHING, null);
	}
}
