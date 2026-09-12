package dev.coder2195.stellarity.interface_injection;

import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;

@SuppressWarnings("NonExtendableApiUsage")
public interface ExtEntity extends AttachmentTarget {
	void setGlowingTag(boolean glowing);

	default void stellarity$removeGlowColor() {
		this.removeAttached(StellarityDataAttachments.GLOW_COLOR);
	}

	default void stellarity$setGlowColor(int color) {
		setGlowingTag(color != -1);
		if (color == -1) {
			stellarity$removeGlowColor();
			return;
		}
		this.setAttached(StellarityDataAttachments.GLOW_COLOR, color);
	}

	default int stellarity$getGlowColor() {
		return this.getAttachedOrElse(StellarityDataAttachments.GLOW_COLOR, -1);
	}
}