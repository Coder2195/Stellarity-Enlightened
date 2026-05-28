package prismatic.shards.stellarity.registry.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.registry.StellaritySoundEvents;
import prismatic.shards.stellarity.registry.entity.ThrownPrismaticPearl;


public class PrismaticPearlItem extends Item {
	public PrismaticPearlItem(Properties properties) {
		super(properties);
	}

	public static final Properties PROPERTIES = new Item.Properties().stacksTo(1);


	@Override
	public @NonNull InteractionResult use(@NonNull Level level, Player player, @NonNull InteractionHand interactionHand) {
		ItemStack itemStack = player.getItemInHand(interactionHand);

		if (level instanceof ServerLevel serverLevel) {
			player.getCooldowns().addCooldown(itemStack, 5 * 20);

			var pearl = Projectile.spawnProjectileFromRotation(ThrownPrismaticPearl::new, serverLevel, itemStack, player, 0.0F, 1.5F * 1.25F, 1.0F);
			pearl.setItem(itemStack);

			serverLevel.playSound(null, player.blockPosition(), StellaritySoundEvents.PRISMATIC_PEARL_THROW, SoundSource.NEUTRAL);
		}


		return InteractionResult.SUCCESS;

	}
}
