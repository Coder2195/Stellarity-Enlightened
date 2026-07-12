package dev.coder2195.stellarity.registry;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import net.minecraft.world.level.biome.Biomes;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.util.WorldgenData;

public interface StellarityBiolithBiomes {
	static void init() {
		for (var placement : WorldgenData.PARAMETER_POINTS)
			BiomePlacement.addEnd(placement._1(), placement._2());
		BiomePlacement.replaceEnd(Biomes.SMALL_END_ISLANDS, Biomes.END_HIGHLANDS);

		SurfaceGeneration.addEndSurfaceRules(Stellarity.id("rules/end"), WorldgenData.stellaritySurfaceRules(null));
		SurfaceGeneration.addEndSurfaceRules(Stellarity.mcId("rules/stellarity_end"), WorldgenData.vanillaSurfaceRules(null));


	}
}
