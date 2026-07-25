package dev.coder2195.stellarity.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class BookOfLight extends Item {
	public static final Properties PROPERTIES = new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON);
	private static final int RECHARGE_TIME = 48 * 20;

	public BookOfLight(Properties properties) {
		super(properties);
	}

	@Override
	public @NonNull InteractionResult use(@NonNull Level level, Player player, @NonNull InteractionHand hand) {
		var itemStack = player.getItemInHand(hand);

		player.getCooldowns().addCooldown(itemStack, RECHARGE_TIME);

		if (!(level instanceof ServerLevel serverLevel)) return InteractionResult.SUCCESS;

		return InteractionResult.SUCCESS_SERVER;
	}
}
