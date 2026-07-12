package dev.coder2195.stellarity.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.JukeboxSong;
import dev.coder2195.stellarity.Stellarity;

public interface StellarityJukeboxSongs {
	ResourceKey<JukeboxSong> FIRES_OF_HOKKAI = id("fires_of_hokkai");
	ResourceKey<JukeboxSong> DEVIANTS_LIGHT_MUSIC_BOX = id("deviants_light_music_box");
	ResourceKey<JukeboxSong> PRECIPICE_STEREO = id("precipice_stereo");

	static void bootstrap(BootstrapContext<JukeboxSong> context) {
		context.register(StellarityJukeboxSongs.FIRES_OF_HOKKAI, new JukeboxSong(StellaritySoundEvents.FIRES_OF_HOKKAI, Component.translatable("item.stellarity.music_disc_fires_of_hokkai.desc"), 210, 6));
		context.register(StellarityJukeboxSongs.DEVIANTS_LIGHT_MUSIC_BOX, new JukeboxSong(StellaritySoundEvents.DEVIANTS_LIGHT_MUSIC_BOX, Component.translatable("item.stellarity.music_disc_deviants_light_music_box.desc"), 350, 13));
		context.register(StellarityJukeboxSongs.PRECIPICE_STEREO, new JukeboxSong(StellaritySoundEvents.PRECIPICE_STEREO, Component.translatable("item.stellarity.music_disc_precipice_stereo.desc"), 302, 10));
	}

	private static ResourceKey<JukeboxSong> id(String id) {
		return Stellarity.key(Registries.JUKEBOX_SONG, id);
	}
}
