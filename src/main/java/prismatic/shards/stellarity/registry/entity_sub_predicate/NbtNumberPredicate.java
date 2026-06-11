package prismatic.shards.stellarity.registry.entity_sub_predicate;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.MinMaxBounds;
import net.minecraft.advancements.predicates.entity.EntitySubPredicate;
import net.minecraft.commands.arguments.NbtPathArgument;
import net.minecraft.nbt.NumericTag;
import net.minecraft.server.commands.data.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import prismatic.shards.stellarity.Stellarity;

import java.util.Map;

public record NbtNumberPredicate(
	Map<NbtPathArgument.NbtPath, MinMaxBounds.Doubles> paths
) implements EntitySubPredicate {

	public static final Codec<NbtNumberPredicate> CODEC = RecordCodecBuilder.create(instance -> instance.group(
		Codec.unboundedMap(NbtPathArgument.NbtPath.CODEC, MinMaxBounds.Doubles.CODEC).fieldOf("paths").forGetter(NbtNumberPredicate::paths)
	).apply(instance, NbtNumberPredicate::new));

	@Override
	public boolean matches(@NonNull Entity entity, @NonNull ServerLevel level, @Nullable Vec3 position) {
		var accessor = new EntityDataAccessor(entity);
		var pathsEntries = paths.entrySet();
		try {
			var tags = accessor.getData();
			for (var path : pathsEntries) {
				var candidates = path.getKey().get(tags);
				var bounds = path.getValue();
				if (candidates.isEmpty()) {
					Stellarity.LOGGER.error("The path {} does not exist. {}", path.getKey(), pathsEntries);
					return false;
				}
				boolean foundMatch = false;
				for (var candidate : candidates) {
					if (!(candidate instanceof NumericTag numericTag)) {
						Stellarity.LOGGER.error("The path {} does not provide a numerical value. {}", path.getKey(), pathsEntries);
						return false;
					}
					if (!bounds.matches(numericTag.doubleValue())) continue;

					foundMatch = true;
					break;
				}
				if (!foundMatch) return false;
			}
		} catch (CommandSyntaxException e) {
			Stellarity.LOGGER.error("One of more paths in the NBT Number sub predicate was invalid. {}, \nException: {}", pathsEntries, e.toString());
			return false;
		}


		return true;

	}
}
