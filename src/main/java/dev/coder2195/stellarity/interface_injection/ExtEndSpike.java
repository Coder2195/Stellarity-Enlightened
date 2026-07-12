package dev.coder2195.stellarity.interface_injection;


import net.minecraft.world.level.levelgen.feature.EndSpikeFeature;

public interface ExtEndSpike {
	default boolean stellarity$hasAltar() {
		throw new AssertionError("Not transformed");
	}

	default void stellarity$setAltar(boolean hasAltar) {
		throw new AssertionError("Not transformed");
	}

	default boolean stellarity$hasCryingObsidianTops() {
		throw new AssertionError("Not transformed");
	}

	default void stellarity$setCryingObsidianTops(boolean cryingObsidianTops) {
		throw new AssertionError("Not transformed");
	}

	default boolean stellarity$hasCrystal() {
		throw new AssertionError("Not transformed");
	}

	default void stellarity$setHasCrystal(boolean hasCrystal) {
		throw new AssertionError("Not transformed");
	}

	static EndSpikeFeature.EndSpike apply(EndSpikeFeature.EndSpike spike, boolean hasAltar, boolean cryingObsidianTops, boolean hasCrystal) {
		spike.stellarity$setAltar(hasAltar);
		spike.stellarity$setCryingObsidianTops(cryingObsidianTops);
		spike.stellarity$setHasCrystal(hasCrystal);
		return spike;
	}

	static EndSpikeFeature.EndSpike applyDefaults(EndSpikeFeature.EndSpike spike) {
		return apply(spike, false, false, true);
	}
}
