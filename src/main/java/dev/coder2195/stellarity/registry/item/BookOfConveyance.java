package dev.coder2195.stellarity.registry.item;

import dev.coder2195.stellarity.registry.StellaritySoundEvents;
import dev.coder2195.stellarity.registry.entity.ConveyanceSpark;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class BookOfConveyance extends Item {
	public static final Properties PROPERTIES = new Properties().stacksTo(1).rarity(Rarity.UNCOMMON);
	public static final int RECHARGE_TIME = 20 * 20;

	public BookOfConveyance(Properties properties) {
		super(properties);
	}

	@Override
	public @NonNull InteractionResult use(@NonNull Level level, Player player, @NonNull InteractionHand hand) {
		var itemStack = player.getItemInHand(hand);

		player.getCooldowns().addCooldown(itemStack, RECHARGE_TIME);

		if (!(level instanceof ServerLevel serverLevel)) return InteractionResult.SUCCESS;
		serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(), StellaritySoundEvents.SPELLBOOK_CAST, SoundSource.PLAYERS, 1, 0.5f);

		var spark = new ConveyanceSpark(level, player);

		spark.setPos(player.getEyePosition());
		serverLevel.addFreshEntity(spark);

		return InteractionResult.SUCCESS_SERVER;
	}
}
