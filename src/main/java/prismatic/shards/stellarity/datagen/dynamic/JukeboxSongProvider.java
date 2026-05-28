package prismatic.shards.stellarity.datagen.dynamic;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.JukeboxSong;
import prismatic.shards.stellarity.key.StellarityJukeboxSongs;
import prismatic.shards.stellarity.registry.StellaritySoundEvents;

public interface JukeboxSongProvider {
	static void bootstrap(BootstrapContext<JukeboxSong> context) {
		context.register(StellarityJukeboxSongs.FIRES_OF_HOKKAI, new JukeboxSong(StellaritySoundEvents.FIRES_OF_HOKKAI, Component.translatable("item.stellarity.music_disc_fires_of_hokkai.desc"), 210, 6));
		context.register(StellarityJukeboxSongs.DEVIANTS_LIGHT_MUSIC_BOX, new JukeboxSong(StellaritySoundEvents.DEVIANTS_LIGHT_MUSIC_BOX, Component.translatable("item.stellarity.music_disc_deviants_light_music_box.desc"), 350, 13));
		context.register(StellarityJukeboxSongs.PRECIPICE_STEREO, new JukeboxSong(StellaritySoundEvents.PRECIPICE_STEREO, Component.translatable("item.stellarity.music_disc_precipice_stereo.desc"), 302, 10));
	}
}
