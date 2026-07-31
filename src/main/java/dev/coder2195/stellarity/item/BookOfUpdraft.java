package dev.coder2195.stellarity.item;

import dev.coder2195.stellarity.registry.StellarityDataAttachments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class BookOfUpdraft extends Spellbook {
	public static final Properties PROPERTIES = new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON);
	public static final int RECHARGE_TIME = 16 * 20;

	public BookOfUpdraft(Properties properties) {
		super(properties);
	}

	@Override
	public @NonNull InteractionResult use(@NonNull Level level, @NonNull Player player, @NonNull InteractionHand hand) {
		if (!(level instanceof ServerLevel serverLevel)) return InteractionResult.SUCCESS;
		castSpell(serverLevel, player);

		player.getCooldowns().addCooldown(player.getItemInHand(hand), 99999999);

		player.setAttached(StellarityDataAttachments.UPDRAFT_LEVITATION_UNTIL, level.getGameTime() + 5);


		serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BAT_TAKEOFF, player.getSoundSource(), 0.8f, 1.0f);
		serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.TRIDENT_RIPTIDE_1, player.getSoundSource(), 1f, 1.0f);
		return InteractionResult.SUCCESS_SERVER;
	}
}
