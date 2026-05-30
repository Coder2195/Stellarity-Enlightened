package prismatic.shards.stellarity.registry;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.key.StellarityNoises;

import static net.minecraft.world.level.biome.Biomes.*;
import static net.minecraft.world.level.biome.Climate.Parameter.span;
import static net.minecraft.world.level.block.Blocks.*;
import static net.minecraft.world.level.levelgen.SurfaceRules.*;
import static prismatic.shards.stellarity.key.StellarityBiomes.*;
import static prismatic.shards.stellarity.registry.StellarityBlocks.*;
import static prismatic.shards.stellarity.util.ValueUtil.from;
import static prismatic.shards.stellarity.util.WorldgenUtil.state;
import static net.minecraft.world.level.levelgen.SurfaceRules.state;

public interface StellarityBiolithBiomes {
	static void init() {
		for (var placement : StellarityWorldgenData.PARAMETER_POINTS)
			BiomePlacement.addEnd(placement._1(), placement._2());

		SurfaceGeneration.addEndSurfaceRules(Stellarity.id("rules/end"), StellarityWorldgenData.STELLARITY_SURFACE_RULES);
		SurfaceGeneration.addEndSurfaceRules(Stellarity.mcId("rules/stellarity_end"), StellarityWorldgenData.VANILLA_SURFACE_RULES);
	}
}
