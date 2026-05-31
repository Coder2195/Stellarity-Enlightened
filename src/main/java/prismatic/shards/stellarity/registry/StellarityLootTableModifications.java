package prismatic.shards.stellarity.registry;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityTypes;
import prismatic.shards.stellarity.Stellarity;

import static prismatic.shards.stellarity.util.LootUtil.*;


public interface StellarityLootTableModifications {
	static void init() {
		LootTableEvents.MODIFY.register((key, builder, source, provider) -> {
			var id = key.identifier();

			var endVariant = new CompoundTag();
			endVariant.putString("variant", "stellarity:end");


			if (id.equals(Stellarity.mcId("entities/magma_cube"))) {
				Stellarity.LOGGER.info("Modifying Magma Cube Loot Table");


				builder.withPool(pool().when(
					onDamage(damage().source(
						predicate().entityType(entityType(EntityTypes.FROG)).nbt(nbt(endVariant))
					))).add(item(StellarityItems.ASHEN_FROGLIGHT)
				));
			} else if (id.equals(Stellarity.mcId("gameplay/chicken_lay"))) {

				var nbtCheck = entityProperty(predicate().nbt(nbt(endVariant)));

				builder.withPool(pool().add(item(StellarityItems.ENDER_EGG)).when(nbtCheck));
			}
		});

	}
}
