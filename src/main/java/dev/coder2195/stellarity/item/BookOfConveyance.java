package dev.coder2195.stellarity.item;

import dev.coder2195.stellarity.entity.ConveyanceSpark;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class BookOfConveyance extends Spellbook {
	public static final Properties PROPERTIES = new Properties().stacksTo(1).rarity(Rarity.UNCOMMON);
	public static final int RECHARGE_TIME = 20 * 20;

	public BookOfConveyance(Properties properties) {
		super(properties);
	}

	@Override
	public @NonNull InteractionResult use(@NonNull Level level, Player player, @NonNull InteractionHand hand) {
		var itemStack = player.getItemInHand(hand);

		player.getCooldowns().addCooldown(itemStack, RECHARGE_TIME);

		castSpell(level, player);
		if (!(level instanceof ServerLevel serverLevel)) return InteractionResult.SUCCESS;

		var spark = new ConveyanceSpark(level, player);

		spark.setPos(player.getEyePosition());
		serverLevel.addFreshEntity(spark);

		return InteractionResult.SUCCESS_SERVER;
	}
}
