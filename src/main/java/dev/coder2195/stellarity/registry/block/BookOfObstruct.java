package dev.coder2195.stellarity.registry.block;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.StellarityEntityTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

public class BookOfObstruct extends Item {
	public static final Properties PROPERTIES = new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON);
	public static final int RECHARGE_TIME = 15 * 20;

	public BookOfObstruct(Properties properties) {
		super(properties);
	}

	@Override
	public @NonNull InteractionResult use(@NonNull Level level, Player player, @NonNull InteractionHand hand) {
		var itemStack = player.getItemInHand(hand);

		player.getCooldowns().addCooldown(itemStack, RECHARGE_TIME);

		if (!(level instanceof ServerLevel serverLevel)) return InteractionResult.SUCCESS;
		serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 1, 0.5f);

		float rot = (float) -Math.toRadians(player.getYHeadRot());

		for (int dx=-3; dx<=3; dx++) {
			for (int dy=-1; dy<=1; dy++) {
				Vec3 placeDistance = new Vec3(dx, dy + Math.abs(dx / 300d), 3);


				var obstructBlock = StellarityEntityTypes.OBSTRUCT_SPELL_BLOCK.create(level, EntitySpawnReason.EVENT);
				if (obstructBlock == null) {
					Stellarity.LOGGER.warn("Failed to create obstruct blocks");
					continue;
				}

				obstructBlock.setPos(player.position().add(placeDistance.yRot(rot)));
				serverLevel.addFreshEntity(obstructBlock);

			}
		}

		return InteractionResult.SUCCESS_SERVER;
	}
}
