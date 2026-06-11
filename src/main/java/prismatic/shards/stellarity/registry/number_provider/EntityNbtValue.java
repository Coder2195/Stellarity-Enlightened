package prismatic.shards.stellarity.registry.number_provider;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.commands.arguments.NbtPathArgument;
import net.minecraft.nbt.NumericTag;
import net.minecraft.server.commands.data.EntityDataAccessor;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import prismatic.shards.stellarity.Stellarity;

public record EntityNbtValue(
	LootContext.EntityTarget entityTarget, NbtPathArgument.NbtPath path, float defaultValue
) implements NumberProvider {
	public static final MapCodec<EntityNbtValue> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
		LootContext.EntityTarget.CODEC.fieldOf("target").forGetter(EntityNbtValue::entityTarget),
		NbtPathArgument.NbtPath.CODEC.fieldOf("path").forGetter(EntityNbtValue::path),
		Codec.FLOAT.optionalFieldOf("default_value", 0f).forGetter(EntityNbtValue::defaultValue)
	).apply(builder, EntityNbtValue::new));

	@Override
	public float getFloat(LootContext context) {
		var entity = context.getOptionalParameter(entityTarget.contextParam());

		if (entity == null) return defaultValue;
		var accessor = new EntityDataAccessor(entity);

		try {
			var tags = path.get(accessor.getData());
			for (var tag : tags) {
				if (tag instanceof NumericTag numericTag) return numericTag.floatValue();
			}
		} catch (CommandSyntaxException e) {
			Stellarity.LOGGER.info("The path in one of the NBT Value Providers was invalid. {}, {}, {}", entityTarget, path, defaultValue);
			return defaultValue;
		}


		return defaultValue;


	}

	@Override
	public MapCodec<? extends NumberProvider> codec() {
		return CODEC;
	}
}
