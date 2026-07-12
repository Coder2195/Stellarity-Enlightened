package dev.coder2195.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.JukeboxSong;
import dev.coder2195.stellarity.Stellarity;

public interface StellarityJukeboxSongs {
	ResourceKey<JukeboxSong> FIRES_OF_HOKKAI = id("fires_of_hokkai");
	ResourceKey<JukeboxSong> DEVIANTS_LIGHT_MUSIC_BOX = id("deviants_light_music_box");
	ResourceKey<JukeboxSong> PRECIPICE_STEREO = id("precipice_stereo");

	static ResourceKey<JukeboxSong> id(String id) {
		return Stellarity.key(Registries.JUKEBOX_SONG, id);
	}
}
