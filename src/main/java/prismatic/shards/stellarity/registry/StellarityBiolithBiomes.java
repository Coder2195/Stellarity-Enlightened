package prismatic.shards.stellarity.registry;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import prismatic.shards.stellarity.Stellarity;

import static net.minecraft.world.level.biome.Climate.Parameter.span;

public interface StellarityBiolithBiomes {
	static void init() {
		for (var placement : StellarityWorldgenData.PARAMETER_POINTS)
			BiomePlacement.addEnd(placement._1(), placement._2());

		SurfaceGeneration.addEndSurfaceRules(Stellarity.id("rules/end"), StellarityWorldgenData.stellaritySurfaceRules(null));
		SurfaceGeneration.addEndSurfaceRules(Stellarity.mcId("rules/stellarity_end"), StellarityWorldgenData.vanillaSurfaceRules(null));
	}
}
