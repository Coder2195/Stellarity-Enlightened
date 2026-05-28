package prismatic.shards.stellarity.registry.entity;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import prismatic.shards.stellarity.registry.StellarityEntityTypes;

public class SpectralBolt extends AbstractArrow {
	private float spin = 0;
	private int elapsedTime = 0;
	private Vec3 posOld = null;

	public SpectralBolt(EntityType<? extends AbstractArrow> type, Level level) {
		super(type, level);
	}

	public SpectralBolt(final Level level, final LivingEntity owner, final ItemStack pickupItemStack, final @Nullable ItemStack firedFromWeapon) {
		super(StellarityEntityTypes.SPECTRAL_BOLT, owner, level, pickupItemStack, firedFromWeapon);
	}

	public int getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(int elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	@Override
	protected void readAdditionalSaveData(@NonNull ValueInput input) {
		super.readAdditionalSaveData(input);
		input.read("elapsed_time", Codec.INT).ifPresent(this::setElapsedTime);
	}

	@Override
	protected void addAdditionalSaveData(@NonNull ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.store("elapsed_time", Codec.INT, elapsedTime);
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
		if (posOld == null) posOld = position();
		super.tick();
		if (!level.isClientSide()) {
			elapsedTime++;
			if (elapsedTime == 3 * 20) {
				this.discard();
			}

			return;
		}
		drawTrail(position());
	}

	private void drawTrail(Vec3 target) {
		var level = level();
		var delta = target.subtract(posOld);
		var steps = delta.length() / 0.25;
		var stepVec = delta.scale(1 / steps);

		var move = delta.normalize();
		var helper = Math.abs(move.x) > 0.9 ? new Vec3(0, 0, 100) : new Vec3(100, 0, 0);
		var perpendicular = helper.cross(move);
		for (int i = 0; i < steps; i++) {
			var normal = move.cross(perpendicular).scale(Math.cos(spin)).add(perpendicular.scale(Math.sin(spin))).normalize().scale(0.5);
			var particle1 = posOld.add(normal);
			var particle2 = posOld.add(normal.scale(-1));
			level.addAlwaysVisibleParticle(ParticleTypes.END_ROD, true, particle1.x, particle1.y, particle1.z, 0, 0, 0);
			level.addAlwaysVisibleParticle(ParticleTypes.END_ROD, true, particle2.x, particle2.y, particle2.z, 0, 0, 0);

			posOld = posOld.add(stepVec);
			spin += 0.2f;
		}
	}

	@Override
	public void shootFromRotation(@NonNull Entity source, float xRot, float yRot, float yOffset, float pow, float uncertainty) {
		super.shootFromRotation(source, xRot, yRot, yOffset, pow * 1.3f, uncertainty);
		this.setNoGravity(true);
	}

	@Override
	protected void onHitBlock(@NonNull BlockHitResult hitResult) {
		super.onHitBlock(hitResult);
		this.discard();
	}

	@Override
	public void onClientRemoval() {
		drawTrail(position().add(getDeltaMovement()));
		var level = level();
		var random = getRandom();
		for (int i = 0; i < 20; i++) {
			level.addAlwaysVisibleParticle(ParticleTypes.END_ROD, true, getX(), getY(), getZ(), (random.nextDouble() - 0.5) * 0.5, (random.nextDouble() - 0.5) * 0.5, (random.nextDouble() - 0.5) * 0.5);
		}
		super.onClientRemoval();
	}
}
