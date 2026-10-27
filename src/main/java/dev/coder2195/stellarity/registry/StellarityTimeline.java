package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.clock.WorldClocks;
import net.minecraft.world.timeline.Timeline;

public interface StellarityTimeline {
	ResourceKey<Timeline> END_LIGHT = id("end_light");

	static void bootstrap(BootstrapContext<Timeline> context) {
		var worldClocks = context.lookup(Registries.WORLD_CLOCK);
		context.register(END_LIGHT, Timeline.builder(worldClocks.getOrThrow(WorldClocks.OVERWORLD))
			.setPeriodTicks(24000)
			.addTrack(EnvironmentAttributes.SKY_LIGHT_LEVEL, floatBuilder -> {
				floatBuilder.addKeyframe(0, 0f);
				floatBuilder.addKeyframe(23999, 0f);
			})
			.build());
	}

	private static ResourceKey<Timeline> id(String id) {
		return Stellarity.key(Registries.TIMELINE, id);
	}
}
