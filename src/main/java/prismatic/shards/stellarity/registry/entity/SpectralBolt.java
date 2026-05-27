package prismatic.shards.stellarity.registry.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import prismatic.shards.stellarity.registry.StellarityEntityTypes;

public class SpectralBolt extends AbstractArrow {
	private float spin = 0;

	public SpectralBolt(EntityType<? extends AbstractArrow> type, Level level) {
		super(type, level);
	}

	public SpectralBolt(final Level level, final LivingEntity owner, final ItemStack pickupItemStack, final @Nullable ItemStack firedFromWeapon) {
		super(StellarityEntityTypes.SPECTRAL_BOLT, owner, level, pickupItemStack, firedFromWeapon);
	}


	@Override
	protected @NonNull ItemStack getDefaultPickupItem() {
		return ItemStack.EMPTY;
	}

	@Override
	public byte getPierceLevel() {
		return (byte) Math.max(super.getPierceLevel(), 3);
	}

	@Override
	public void tick() {
		var level = level();
		var position = position();
		if (!level.isClientSide()) return;
		var move = getDeltaMovement();
		var normal = move.cross(new Vec3(Math.sin(spin), Math.cos(spin), Math.cos(spin))).normalize().scale(0.5);
		var particle1 = position.add(normal);
		var particle2 = position.add(normal.scale(-1));
		level.addAlwaysVisibleParticle(ParticleTypes.END_ROD, particle1.x, particle1.y, particle1.z, 0, 0, 0);
		level.addAlwaysVisibleParticle(ParticleTypes.END_ROD, particle2.x, particle2.y, particle2.z, 0, 0, 0);
		spin += 0.5f;
		super.tick();
	}

	@Override
	public void shootFromRotation(Entity source, float xRot, float yRot, float yOffset, float pow, float uncertainty) {
		super.shootFromRotation(source, xRot, yRot, yOffset, pow, uncertainty);
		this.setNoGravity(true);
	}

	@Override
	protected void onHitBlock(BlockHitResult hitResult) {
		super.onHitBlock(hitResult);
		this.discard();
	}
}
