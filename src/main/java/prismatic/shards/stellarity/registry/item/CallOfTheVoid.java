package prismatic.shards.stellarity.registry.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import prismatic.shards.stellarity.registry.entity.VoidArrow;

import java.util.List;

public class CallOfTheVoid extends BowItem {
	public static final Item.Properties PROPERTIES = new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1).durability(424);

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

}