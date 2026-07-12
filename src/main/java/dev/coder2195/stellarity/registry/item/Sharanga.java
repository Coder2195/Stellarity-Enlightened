package dev.coder2195.stellarity.registry.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import dev.coder2195.stellarity.registry.entity.SpectralBolt;
import dev.coder2195.stellarity.util.tuple.Tuple3;

public class Sharanga extends BowItem implements StellarityBow {
	public static final Item.Properties PROPERTIES = new Item.Properties().durability(424).rarity(Rarity.UNCOMMON).stacksTo(1);
	public static final Tuple3<SoundEvent, Float, Float> SHOOT_SOUND = new Tuple3<>(SoundEvents.WARDEN_SONIC_BOOM, 0.5f, 2f);

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

			return new SpectralBolt(level, shooter, projectile.copyWithCount(1), weapon);
		}
		return super.createProjectile(level, shooter, weapon, projectile, isCrit);
	}

	@Override
	public @Nullable Tuple3<@Nullable SoundEvent, @Nullable Float, @Nullable Float> shootSound(ItemStack weapon, ItemStack projectile, Level level, LivingEntity entity) {
		return SHOOT_SOUND;
	}
}
