package dev.coder2195.stellarity.block_entity;

import dev.coder2195.stellarity.registry.StellarityBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class AltarOfTheSacredBlockEntity extends BlockEntity {
	private RandomSource random = RandomSource.create();
	public AltarOfTheSacredBlockEntity(BlockPos worldPosition, BlockState blockState) {
		super(StellarityBlockEntityTypes.ALTAR_OF_THE_SACRED, worldPosition, blockState);
	}


	public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T blockEntity) {
		if (blockEntity instanceof AltarOfTheSacredBlockEntity entity) entity.tick(level, blockPos, blockState);
	}

	private void tick(Level level, BlockPos blockPos, BlockState blockState) {
		var topCenterPos = Vec3.atCenterOf(blockPos.above());

		if (level.isClientSide()) {

			level.addAlwaysVisibleParticle(ParticleTypes.FIREWORK, true, topCenterPos.x + random.nextGaussian() * 0.25, topCenterPos.y - 0.2, topCenterPos.z + random.nextGaussian() * 0.25, random.nextGaussian() * 0.01, random.nextGaussian() * 0.01 + 0.03, random.nextGaussian() * 0.01);
			level.addAlwaysVisibleParticle(ParticleTypes.WITCH, true, topCenterPos.x + random.nextGaussian() * 0.05, topCenterPos.y + random.nextGaussian() * 0.4, topCenterPos.z + random.nextGaussian() * 0.05, 0, 0, 0);

			return;
		}
	}
}
