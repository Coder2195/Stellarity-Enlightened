package prismatic.shards.stellarity.registry.number_provider;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.NumberProviders;
import org.jspecify.annotations.NonNull;

import java.util.List;

public record Multiply(List<NumberProvider> factors) implements NumberProvider {
	public static final MapCodec<Multiply> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
		NumberProviders.CODEC.listOf().fieldOf("factors").forGetter(Multiply::factors)
	).apply(builder, Multiply::new));

	public static Multiply multiply(NumberProvider... factors) {
		return new Multiply(List.of(factors));
	}

	@Override
	public float getFloat(@NonNull LootContext context) {
		float product = 1;
		for (var factor : factors) {
			product *= factor.getFloat(context);
		}
		return product;
	}

	@Override
	public @NonNull MapCodec<? extends NumberProvider> codec() {
		return CODEC;
	}
}
