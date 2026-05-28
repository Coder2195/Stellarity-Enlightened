package prismatic.shards.stellarity.registry.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import prismatic.shards.stellarity.registry.entity.VoidArrow;
import prismatic.shards.stellarity.util.tuple.Tuple3;

import java.util.List;

public class CallOfTheVoid extends BowItem implements StellarityBow {
	public static final Item.Properties PROPERTIES = new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1).durability(424);
	public static final Tuple3<SoundEvent, Float, Float> SHOOT_SOUND = new Tuple3<>(SoundEvents.ENDER_DRAGON_HURT, 0.5f, 0.7f);

	public CallOfTheVoid(Properties properties) {
		super(properties);
	}

	@Override
	protected @NonNull Projectile createProjectile(@NonNull Level level, @NonNull LivingEntity shooter, @NonNull ItemStack weapon, ItemStack projectile, boolean isCrit) {
		if (projectile.getItem() instanceof ArrowItem) {
			return new VoidArrow(level, shooter, projectile.copyWithCount(1), weapon);
		}
		return super.createProjectile(level, shooter, weapon, projectile, isCrit);
	}

	@Override
	protected void shoot(@NonNull ServerLevel level, @NonNull LivingEntity shooter, @NonNull InteractionHand hand, @NonNull ItemStack weapon, @NonNull List<ItemStack> projectiles, float power, float uncertainty, boolean isCrit, @Nullable LivingEntity targetOverride) {
		super.shoot(level, shooter, hand, weapon, projectiles, power * 1.08f, uncertainty, isCrit, targetOverride);
	}

	@Override
	public @Nullable Tuple3<@Nullable SoundEvent, @Nullable Float, @Nullable Float> shootSound(ItemStack weapon, ItemStack projectile, Level level, LivingEntity entity) {
		return SHOOT_SOUND;
	}
}