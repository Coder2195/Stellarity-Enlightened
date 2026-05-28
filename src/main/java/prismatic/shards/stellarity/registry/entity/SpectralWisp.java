package prismatic.shards.stellarity.registry.entity;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.registry.StellarityEntityTypes;

public class SpectralWisp extends Projectile {
	public SpectralWisp(EntityType<? extends Projectile> type, Level level) {
		super(type, level);
	}

	public SpectralWisp(@NonNull Level level, @NonNull LivingEntity shooter, ItemStack itemStack, @NonNull ItemStack weapon) {
		super(StellarityEntityTypes.SPECTRAL_WISP, level);
		this.setPos(shooter.position());
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NonNull Builder entityData) {

	}
}
