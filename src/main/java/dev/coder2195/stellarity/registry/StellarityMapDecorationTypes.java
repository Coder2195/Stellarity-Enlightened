package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;

public interface StellarityMapDecorationTypes {
	Holder<MapDecorationType> END_CITY = register("end_city", true, false);
	Holder<MapDecorationType> END_VILLAGE = register("end_village", true, false);
	Holder<MapDecorationType> CHAPEL_OF_LIGHT = register("chapel_of_light", true, false);
	Holder<MapDecorationType> FLOATING_TREASURE = register("floating_treasure", true, false);

	private static Holder<MapDecorationType> register(final String id, final boolean showOnItemFrame, final boolean trackCount) {
		ResourceKey<MapDecorationType> key = ResourceKey.create(Registries.MAP_DECORATION_TYPE, Stellarity.id(id));
		MapDecorationType type = new MapDecorationType(Stellarity.id(id), showOnItemFrame, trackCount);
		return Registry.registerForHolder(BuiltInRegistries.MAP_DECORATION_TYPE, key, type);
	}

  static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Map Decoration Types");
  }
}
