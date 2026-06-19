package prismatic.shards.stellarity.registry.material_condition;


import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Ported from Minecraft 26.1.
 */
public final class LegacyBiomeConditionSource implements SurfaceRules.ConditionSource {
	public static final MapCodec<LegacyBiomeConditionSource> CODEC = ResourceKey.codec(Registries.BIOME)
		.listOf()
		.fieldOf("biome_is")
		.xmap(LegacyBiomeConditionSource::isBiome, e -> e.biomes);
	private final List<ResourceKey<Biome>> biomes;
	private final Predicate<ResourceKey<Biome>> biomeNameTest;

	private LegacyBiomeConditionSource(List<ResourceKey<Biome>> biomes) {
		this.biomes = biomes;
		this.biomeNameTest = Set.copyOf(biomes)::contains;
	}

	@SafeVarargs
	public static SurfaceRules.ConditionSource isBiome(ResourceKey<Biome>... target) {
		return isBiome(List.of(target));
	}

	private static LegacyBiomeConditionSource isBiome(List<ResourceKey<Biome>> target) {
		return new LegacyBiomeConditionSource(target);
	}

	@Override
	public @NonNull MapCodec<? extends SurfaceRules.ConditionSource> codec() {
		return CODEC;
	}

	@Override
	public SurfaceRules.Condition apply(SurfaceRules.Context ruleContext) {
		return new SurfaceRules.LazyYCondition(ruleContext) {
			@Override
			protected boolean compute() {
				return this.context.getBiome().is(LegacyBiomeConditionSource.this.biomeNameTest);
			}
		};
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else {
			return o instanceof LegacyBiomeConditionSource that && this.biomes.equals(that.biomes);
		}
	}

	@Override
	public int hashCode() {
		return this.biomes.hashCode();
	}

	@Override
	public String toString() {
		return "LegacyBiomeConditionSource[biomes=" + this.biomes + "]";
	}
}