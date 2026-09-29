package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.world.level.levelgen.feature.EndSpikeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EndSpikeFeature.class)
public interface EndSpikeFeatureAccessor {
	@Accessor("NUMBER_OF_SPIKES")
	static int NUMBER_OF_SPIKES() {
		throw new AssertionError("Not transformed!");
	}

}
