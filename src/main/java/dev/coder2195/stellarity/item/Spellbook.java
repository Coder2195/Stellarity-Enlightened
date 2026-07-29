package dev.coder2195.stellarity.item;

import dev.coder2195.stellarity.registry.StellaritySoundEvents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public abstract class Spellbook extends Item {
	public Spellbook(Properties properties) {
		super(properties);
	}

	public void castSpell(@NonNull Level level, Player player) {
		double x = player.getX();
		double y = player.getY();
		double z = player.getZ();
		var random = player.getRandom();
		level.playSound(null, x, y, z, StellaritySoundEvents.SPELLBOOK_CAST, SoundSource.PLAYERS, 1, 0.5f);
		for (int i=0; i<30; i++) {
			level.addParticle(ParticleTypes.ENCHANT, x + random.nextDouble() * 0.44 - 0.22, y + random.nextDouble() * 0.56 - 0.28, random.nextDouble() * 0.44 - 0.22, 0, 0, 0);
		}
	}
}
