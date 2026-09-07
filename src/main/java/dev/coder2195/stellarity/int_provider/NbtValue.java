package dev.coder2195.stellarity.int_provider;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.coder2195.stellarity.Stellarity;
import net.minecraft.commands.arguments.NbtPathArgument;
import net.minecraft.nbt.NumericTag;
import net.minecraft.server.commands.data.EntityDataAccessor;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProvider;

public record NbtValue(
	LootContext.EntityTarget target, NbtPathArgument.NbtPath path, int defaultValue
) implements ContextIntProvider {
	public static final MapCodec<NbtValue> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
		LootContext.EntityTarget.CODEC.fieldOf("target").forGetter(NbtValue::target),
		NbtPathArgument.NbtPath.CODEC.fieldOf("path").forGetter(NbtValue::path),
		Codec.INT.optionalFieldOf("default_value", 0).forGetter(NbtValue::defaultValue)
	).apply(builder, NbtValue::new));

	@Override
	public int getIntUnsafe(LootContext context) {
		var entity = context.getOptional(target.contextParam());

		if (entity == null) return defaultValue;
		var accessor = new EntityDataAccessor(entity);

		try {
			var tags = path.get(accessor.getData());
			for (var tag : tags) {
				if (tag instanceof NumericTag numericTag) return numericTag.intValue();
			}
		} catch (CommandSyntaxException e) {
			Stellarity.LOGGER.info("The path in one of the NBT Value Providers was invalid. {}, {}, {} \nException: {}", target, path, defaultValue, e.toString());
			return defaultValue;
		}

		return defaultValue;
	}

	@Override
	public void validate(ValidationContext context) {
	}

	@Override
	public MapCodec<? extends ContextIntProvider> codec() {
		return CODEC;
	}
}
