package dev.coder2195.stellarity.item;

import dev.coder2195.stellarity.registry.StellarityDataAttachments;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class BookOfReturn extends Spellbook {
	public static final Properties PROPERTIES = new Properties().stacksTo(1).rarity(Rarity.UNCOMMON);

	public BookOfReturn(Properties properties) {
		super(properties);
	}

	public static final int USE_DELAY = 10 * 20;
	public static final int RECHARGE_TIME = 60 * 20;

	public static final Vec3[] PARTICLE_POSITIONS = generateParticlePositions();

	private static Vec3[] generateParticlePositions() {
		var array = new Vec3[1287];

		//0-299 cirlce 2.85
		for (int i=0; i < 300; i++) {
			float angle = i * Mth.TWO_PI / 300f;
			array[i] = new Vec3(Math.sin(angle), 0, Math.cos(angle)).scale(2.85);
		}

		//300-583 square inside circle
		for (var rotation: List.of(0, 1, 2, 3)) {
			for (int i=0; i<71; i++) {
				array[300 + rotation * 71 + i] = new Vec3(1.988735, 0, (i-35d) * 3.97747 / 70.0).yRot(rotation * Mth.HALF_PI);
			}
		}

		//584-683 circle 0.9
		for (int i=0; i < 100; i++) {
			float angle = i * Mth.TWO_PI / 100f;
			array[i + 584] = new Vec3(Math.sin(angle), 0, Math.cos(angle)).scale(0.9);
		}

		//684-758 circle 0.7
		for (int i=0; i < 75; i++) {
			float angle = i * Mth.TWO_PI / 75f;
			array[i + 684] = new Vec3(Math.sin(angle), 0, Math.cos(angle)).scale(0.7);
		}

		//759-958 cirlce 1.875
		for (int i=0; i < 200; i++) {
			float angle = i * Mth.TWO_PI / 100f;
			array[i + 759] = new Vec3(Math.sin(angle), 0, Math.cos(angle)).scale(1.875);
		}

		//959-1142 octagon for 0.9 circle
		for (var rotation: List.of(0, 1, 2, 3, 4, 5, 6, 7)) {
			for (int i=0; i<23; i++) {
				array[959 + rotation * 23 + i] = new Vec3(1.732, 0, (i-11) * 1.435 / 23).yRot((rotation + 0.5f) * Mth.HALF_PI / 2f);
			}
		}

		//1143+1286 lines
		for (var rotation: List.of(0, 1, 2, 3)) {
			for (int i=0; i<36; i++) {
				array[1143 + rotation * 36 + i] = new Vec3(0, 0, (i * 2.15 / 36d) + 0.7).yRot((rotation + 0.5f) * Mth.HALF_PI);
			}
		}

		return array;
	}

	public static final ParticleOptions RING_PARTICLE = new DustParticleOptions(0xbf00bf, 0.33745f);

	@Override
	public @NonNull InteractionResult use(@NonNull Level level, @NonNull Player player, @NonNull InteractionHand hand) {
		if (player.getAttached(StellarityDataAttachments.RETURN_SPELL_AT) != null) return super.use(level, player, hand);

		player.setAttached(StellarityDataAttachments.RETURN_SPELL_AT, level.getGameTime() + USE_DELAY);
		castSpell(level, player);

		return InteractionResult.SUCCESS;
	}
}
