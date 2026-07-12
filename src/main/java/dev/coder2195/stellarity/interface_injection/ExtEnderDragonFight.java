package dev.coder2195.stellarity.interface_injection;

import net.minecraft.world.level.dimension.end.EnderDragonFight;

public interface ExtEnderDragonFight {

	default boolean stellarity$portalChestGenerated() {
		throw new AssertionError("Not transformed!");
	}

	default void stellarity$setPortalChestGenerated(boolean generated) {
		throw new AssertionError("Not transformed!");
	}

	static EnderDragonFight apply(EnderDragonFight fight, boolean generated) {
		fight.stellarity$setPortalChestGenerated(true);

		return fight;
	}

	static EnderDragonFight applyDefaults(EnderDragonFight fight) {
		return apply(fight, false);
	}
}
