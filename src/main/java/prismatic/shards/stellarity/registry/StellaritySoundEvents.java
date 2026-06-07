package prismatic.shards.stellarity.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import prismatic.shards.stellarity.Stellarity;

public interface StellaritySoundEvents {
	SoundEvent TAMARIS_EXECUTE = register("item.tamaris.execute");
	SoundEvent TAMARIS_EXECUTE_SPECIAL = register("item.tamaris.execute_special");
	SoundEvent TAMARIS_CHIME = register("item.tamaris.chime");
	SoundEvent TAMARIS_EXECUTE_BG = register("item.tamaris.execute_bg");
	SoundEvent TAMARIS_RAVE = register("item.tamaris.rave");

	SoundEvent CRITICAL_STRIKE = register("enchantment.critical_strike.crit");

	SoundEvent AMBUSH_LEVEL_1 = register("enchantment.ambush.level_1");
	SoundEvent AMBUSH_LEVEL_2 = register("enchantment.ambush.level_2");
	SoundEvent AMBUSH_LEVEL_3 = register("enchantment.ambush.level_3");

	SoundEvent ALTAR_OF_THE_ACCURSED_CRAFT = register("block.altar_of_the_accursed.craft");

	SoundEvent PRISMATIC_PEARL_THROW = register("item.prismatic_pearl.throw");

	SoundEvent SPECTRAL_FURY_SHOOT = register("item.spectral_fury.shoot");
	SoundEvent SPECTRAL_FURY_SHOOT_WISP = register("item.spectral_fury.shoot_wisp");

	SoundEvent COPPER_ELEKTRA_SHIELD_DASH = register("item.copper_elektra_shield.dash");

	SoundEvent VOIDED_ACTIVATE = register("effect.voided.activate");
	SoundEvent VOIDED_DEACTIVATE = register("effect.voided.deactivate");

	Holder<SoundEvent> FIRES_OF_HOKKAI = registerHolder("music_disc.fires_of_hokkai");
	Holder<SoundEvent> DEVIANTS_LIGHT_MUSIC_BOX = registerHolder("music_disc.deviants_light_music_box");
	Holder<SoundEvent> PRECIPICE_STEREO = registerHolder("music_disc.precipice_stereo");

	Holder<SoundEvent> AMBIENT_THE_END_DARK = registerHolder("ambient.the_end.dark");
	Holder<SoundEvent> AMBIENT_THE_END_DARK_CALM = registerHolder("ambient.the_end.dark_calm");
	Holder<SoundEvent> AMBIENT_THE_END_HEAVENLY_BLESSED = registerHolder("ambient.the_end.heavenly_blessed");
	Holder<SoundEvent> AMBIENT_THE_END_HEAVENLY_GRIM = registerHolder("ambient.the_end.heavenly_grim");


	private static SoundEvent register(String id) {
		var location = Stellarity.id(id);
		return Registry.register(BuiltInRegistries.SOUND_EVENT, location, SoundEvent.createVariableRangeEvent(location));
	}

	private static Holder<SoundEvent> registerHolder(String id) {
		var location = Stellarity.id(id);
		return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, location, SoundEvent.createVariableRangeEvent(location));
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Sounds");
	}
}
