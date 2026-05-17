package prismatic.shards.stellarity.registry.feature.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.FloatProviders;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.Optional;

public record SpikeFeatureConfiguration(
	BlockStateProvider stateProvider, Optional<BlockPredicate> canReplace, FloatProvider radius, FloatProvider height,
	FloatProvider windX, FloatProvider windZ
) implements FeatureConfiguration {
	public static final Codec<SpikeFeatureConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
		BlockStateProvider.CODEC.fieldOf("state_provider").forGetter(SpikeFeatureConfiguration::stateProvider),
		BlockPredicate.CODEC.optionalFieldOf("can_replace").forGetter(SpikeFeatureConfiguration::canReplace),
		FloatProviders.CODEC.fieldOf("radius").forGetter(SpikeFeatureConfiguration::radius),
		FloatProviders.CODEC.fieldOf("height").forGetter(SpikeFeatureConfiguration::height),
		FloatProviders.CODEC.fieldOf("wind_x").forGetter(SpikeFeatureConfiguration::windX),
		FloatProviders.CODEC.fieldOf("wind_z").forGetter(SpikeFeatureConfiguration::windZ)
	).apply(instance, SpikeFeatureConfiguration::new));
}
