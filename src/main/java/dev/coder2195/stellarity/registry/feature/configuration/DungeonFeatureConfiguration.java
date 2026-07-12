package dev.coder2195.stellarity.registry.feature.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import static dev.coder2195.stellarity.util.ValueUtil.num;

public record DungeonFeatureConfiguration(
	EntityType<?> entityType, IntProvider size, IntProvider height, IntProvider chests
) implements FeatureConfiguration {
	public static final Codec<DungeonFeatureConfiguration> CODEC = RecordCodecBuilder.create(i -> i.group(
		EntityType.CODEC.optionalFieldOf("entity_type", EntityTypes.ENDERMITE).forGetter(DungeonFeatureConfiguration::entityType),
		IntProviders.CODEC.optionalFieldOf("size", num(4)).forGetter(DungeonFeatureConfiguration::size),
		IntProviders.CODEC.optionalFieldOf("height", num(6)).forGetter(DungeonFeatureConfiguration::height),
		IntProviders.CODEC.optionalFieldOf("chests", num(1, 2)).forGetter(DungeonFeatureConfiguration::chests)
	).apply(i, DungeonFeatureConfiguration::new));

	public DungeonFeatureConfiguration() {
		this(EntityTypes.ENDERMITE, num(4), num(6), num(1, 2));
	}
}
