package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;
import java.util.Optional;

@Mixin(TreeConfiguration.class)
public interface TreeConfigurationAccessor {
	@Invoker("<init>")
	static TreeConfiguration create(final BlockStateProvider trunkProvider, final TrunkPlacer trunkPlacer, final BlockStateProvider foliageProvider, final FoliagePlacer foliagePlacer, final Optional<RootPlacer> rootPlacer, final FeatureSize minimumSize, final List<TreeDecorator> decorators, final boolean ignoreVines, final BlockStateProvider belowTrunkProvider) {
		throw new AssertionError("Not transformed!");
	}
}
