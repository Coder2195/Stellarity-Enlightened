package dev.coder2195.stellarity.util;

import net.minecraft.world.level.levelgen.feature.EndSpikeFeature;
import dev.coder2195.stellarity.interface_injection.ExtEndSpike;

import java.util.List;

public interface Constants {
	List<EndSpikeFeature.EndSpike> OBSIDIAN_SPIKES = List.of(
		ExtEndSpike.apply(new EndSpikeFeature.EndSpike(63, 0, 5, 100, false), false, true, true),
		ExtEndSpike.apply(new EndSpikeFeature.EndSpike(50, 36, 4, 105, false), false, true, true),
		ExtEndSpike.apply(new EndSpikeFeature.EndSpike(18, 59, 4, 94, true), false, true, true),
		ExtEndSpike.apply(new EndSpikeFeature.EndSpike(-19, 59, 5, 106, false), false, true, true),
		ExtEndSpike.apply(new EndSpikeFeature.EndSpike(-51, 36, 4, 105, false), false, true, true),
		ExtEndSpike.apply(new EndSpikeFeature.EndSpike(-63, 0, 5, 93, true), false, true, true),
		ExtEndSpike.apply(new EndSpikeFeature.EndSpike(-51, -39, 6, 100, false), false, true, true),
		ExtEndSpike.apply(new EndSpikeFeature.EndSpike(-19, -60, 6, 96, false), false, true, true),
		ExtEndSpike.apply(new EndSpikeFeature.EndSpike(18, -60, 5, 87, true), false, true, true),
		ExtEndSpike.apply(new EndSpikeFeature.EndSpike(50, -39, 8, 105, true), true, true, true)
	);
}
