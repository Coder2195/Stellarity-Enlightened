package dev.coder2195.stellarity.registry;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import net.minecraft.world.level.biome.Biomes;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.util.WorldgenData;

public interface StellarityBiolithBiomes {
	static void init() {
		var points = Stellarity.hasNullscape() ? WorldgenData.NULLSCAPE_PARAMETER_POINTS : WorldgenData.PARAMETER_POINTS;
		for (var point : points)
			BiomePlacement.addEnd(point._1(), point._2());
		BiomePlacement.replaceEnd(Biomes.SMALL_END_ISLANDS, Biomes.END_HIGHLANDS);

		SurfaceGeneration.addEndSurfaceRules(Stellarity.id("rules/end"), WorldgenData::stellaritySurfaceRules);
		SurfaceGeneration.addEndSurfaceRules(Stellarity.mcId("rules/stellarity_end"), WorldgenData::vanillaSurfaceRules);
	}
}
