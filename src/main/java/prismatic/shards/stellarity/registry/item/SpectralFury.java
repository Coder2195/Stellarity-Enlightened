package prismatic.shards.stellarity.registry.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import prismatic.shards.stellarity.registry.StellarityDataAttachments;
import prismatic.shards.stellarity.registry.StellarityItems;
import prismatic.shards.stellarity.registry.StellaritySoundEvents;
import prismatic.shards.stellarity.registry.entity.SpectralWisp;
import prismatic.shards.stellarity.util.tuple.Tuple3;

public class SpectralFury extends BowItem implements StellarityBow {
	public static final Properties PROPERTIES = new Properties().durability(636).rarity(Rarity.UNCOMMON).stacksTo(1);
	public static final Tuple3<SoundEvent, Float, Float> SHOOT_SOUND = new Tuple3<>(StellaritySoundEvents.SPECTRAL_FURY_SHOOT, null, null);

	public SpectralFury(Properties properties) {
		super(properties);
	}

	@Override
	public void inventoryTick(@NonNull ItemStack itemStack, @NonNull ServerLevel level, @NonNull Entity owner, @Nullable EquipmentSlot slot) {
		super.inventoryTick(itemStack, level, owner, slot);
		if (!(owner instanceof LivingEntity livingEntity)) return;
		if (!livingEntity.isUsingItem() || !livingEntity.getUseItem().is(StellarityItems.SPECTRAL_FURY)) {
			livingEntity.setAttached(StellarityDataAttachments.SPECTRAL_FURY_CHARGED, false);
			return;
		}
		if (livingEntity.getAttachedOrElse(StellarityDataAttachments.SPECTRAL_FURY_CHARGED, false)) return;
		int timeHeld = this.getUseDuration(itemStack, livingEntity) - livingEntity.getUseItemRemainingTicks();
		float pow = getPowerForTime(timeHeld);
		if (pow == 1.0) {
			level.playSound(null, livingEntity.blockPosition(), SoundEvents.NOTE_BLOCK_XYLOPHONE.value(), SoundSource.PLAYERS, 1, 0);
			livingEntity.setAttached(StellarityDataAttachments.SPECTRAL_FURY_CHARGED, true);
		}


	}

	@SuppressWarnings("DuplicatedCode")
	@Override
	protected @NonNull Projectile createProjectile(@NonNull Level level, @NonNull LivingEntity shooter, @NonNull ItemStack weapon, ItemStack projectile, boolean isCrit) {
		if (projectile.is(Items.SPECTRAL_ARROW) && isCrit) {
			var chance = 0.25 + EnchantmentHelper.getItemEnchantmentLevel(shooter.registryAccess().getOrThrow(Enchantments.INFINITY), weapon) > 0 ? 0.25 : 0;
			if (shooter.getRandom().nextDouble() < chance && shooter instanceof Player player && !player.isCreative()) {
				player.getInventory().add(new ItemStack(Items.SPECTRAL_ARROW));
			}
			level.playSound(null, shooter.blockPosition(), StellaritySoundEvents.SPECTRAL_FURY_SHOOT_WISP, SoundSource.PLAYERS);

			return new SpectralWisp(level, shooter, projectile.copyWithCount(1), weapon);
		}
		return super.createProjectile(level, shooter, weapon, projectile, isCrit);
	}

	@Override
	public @Nullable Tuple3<@Nullable SoundEvent, @Nullable Float, @Nullable Float> shootSound(ItemStack weapon, ItemStack projectile, Level level, LivingEntity entity) {
		return SHOOT_SOUND;
	}
}
