package dev.coder2195.stellarity.block_entity;

import dev.coder2195.stellarity.registry.StellarityBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.PowerParticleOption;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class DragonsBreathCauldronBlockEntity extends BlockEntity {
	public DragonsBreathCauldronBlockEntity(BlockPos worldPosition, BlockState blockState) {
		super(StellarityBlockEntityTypes.DRAGON_BREATH_CAULDRON, worldPosition, blockState);
	}

	public static void tick(Level level, BlockPos blockPos, BlockState blockState, BlockEntity blockEntity) {
		if (!(blockEntity instanceof DragonsBreathCauldronBlockEntity entity)) return;

		var random = level.getRandom();

		var centerPos = Vec3.atCenterOf(blockPos);
		if (level.isClientSide()) {
			double x = centerPos.x + random.nextDouble() * 0.8 - 0.4;
			double z = centerPos.z + random.nextDouble() * 0.8 - 0.4;

			level.addAlwaysVisibleParticle(PowerParticleOption.create(ParticleTypes.DRAGON_BREATH, 1), true, x, centerPos.y, z, 0, 0.03, 0);
		}
	}
}
