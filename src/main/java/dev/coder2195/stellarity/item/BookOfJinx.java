package dev.coder2195.stellarity.item;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.StellarityEntityTypes;
import dev.coder2195.stellarity.registry.StellaritySoundEvents;
import dev.coder2195.stellarity.util.RaycastUtil;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.stream.Stream;

public class BookOfJinx extends Item {
	public static final Properties PROPERTIES = new Properties().stacksTo(1).rarity(Rarity.UNCOMMON);
	private static final int RECHARGE_TIME = 20 * 10;
	public static final List<ParticleOptions> CLOUD_PARTICLES = Stream.of(1f, 1.15f, 1.3f, 1.45f, 1.6f, 1.75f, 1.9f, 2.05f, 2.2f, 2.35f, 2.5f, 2.65f)
		.map(s -> (ParticleOptions) new DustColorTransitionOptions(0xfa59d2, 0x4e1a66, s))
		.toList();

	public BookOfJinx(Properties properties) {
		super(properties);
	}

	@Override
	public @NonNull InteractionResult use(@NonNull Level level, Player player, @NonNull InteractionHand hand) {
		var itemStack = player.getItemInHand(hand);

		player.getCooldowns().addCooldown(itemStack, RECHARGE_TIME);

		if (!(level instanceof ServerLevel serverLevel)) return InteractionResult.SUCCESS;

		serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(), StellaritySoundEvents.SPELLBOOK_CAST, SoundSource.PLAYERS, 1, 0.5f);

		var cloud = StellarityEntityTypes.POTION_CLOUD.create(level, EntitySpawnReason.SPAWN_ITEM_USE);
		if (cloud == null) {
			Stellarity.LOGGER.error("Failed to create Potion cloud");
			return InteractionResult.FAIL;
		}

		cloud.setDeltaMovement(player.getHeadLookAngle().normalize().scale(0.75f));
		cloud.setParticles(CLOUD_PARTICLES);
		cloud.setWidth(5);
		cloud.setPos(player.getEyePosition().add(player.getLookAngle().normalize().scale(5.5f)));

		level.addFreshEntity(cloud);


		return InteractionResult.SUCCESS_SERVER;
	}
}
