package prismatic.shards.stellarity.registry.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.registry.entity.SpectralBolt;

public class Sharanga extends BowItem {
	public static final Item.Properties PROPERTIES = new Item.Properties().durability(424).rarity(Rarity.UNCOMMON).stacksTo(1);

	public Sharanga(Properties properties) {
		super(properties);
	}

	@Override
	protected @NonNull Projectile createProjectile(@NonNull Level level, @NonNull LivingEntity shooter, @NonNull ItemStack weapon, ItemStack projectile, boolean isCrit) {
		if (projectile.is(Items.SPECTRAL_ARROW)) {
			var chance = 0.25 + EnchantmentHelper.getItemEnchantmentLevel(shooter.registryAccess().getOrThrow(Enchantments.INFINITY), weapon) > 0 ? 0.25 : 0;
			if (shooter.getRandom().nextDouble() < chance && shooter instanceof Player player && !player.isCreative()) {
				player.getInventory().add(new ItemStack(Items.SPECTRAL_ARROW));
			}

			if (level instanceof ServerLevel serverLevel)
				serverLevel.playSound(null, shooter.blockPosition(), SoundEvents.WARDEN_SONIC_BOOM, SoundSource.PLAYERS, 0.5f, 2);
			return new SpectralBolt(level, shooter, projectile.copyWithCount(1), weapon);
		}
		return super.createProjectile(level, shooter, weapon, projectile, isCrit);
	}
}
