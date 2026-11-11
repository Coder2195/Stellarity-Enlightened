package dev.coder2195.stellarity.block_entity;

import dev.coder2195.stellarity.registry.StellarityBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class AltarOfTheSacredBlockEntity extends BlockEntity {
	private final RandomSource random = RandomSource.create();

	public AltarOfTheSacredBlockEntity(BlockPos worldPosition, BlockState blockState) {
		super(StellarityBlockEntityTypes.ALTAR_OF_THE_SACRED, worldPosition, blockState);
	}


	public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T blockEntity) {
		if (blockEntity instanceof AltarOfTheSacredBlockEntity entity) entity.tick(level, blockPos, blockState);
	}

	private void tick(Level level, BlockPos blockPos, BlockState blockState) {
		var abovePos = blockPos.above();
		var topCenterPos = Vec3.atBottomCenterOf(abovePos);

		var itemsOnTop = level.getEntitiesOfClass(ItemEntity.class, AABB.encapsulatingFullBlocks(abovePos, abovePos));

		if (level.isClientSide()) {

			level.addAlwaysVisibleParticle(ParticleTypes.FIREWORK, true,
				topCenterPos.x + random.nextGaussian() * 0.25, topCenterPos.y - 0.2, topCenterPos.z + random.nextGaussian() * 0.25,
				random.nextGaussian() * 0.01, random.nextGaussian() * 0.01, random.nextGaussian() * 0.01
			);
			level.addAlwaysVisibleParticle(ParticleTypes.WITCH, true,
				topCenterPos.x + random.nextGaussian() * 0.05, topCenterPos.y + random.nextGaussian() * 0.4, topCenterPos.z + random.nextGaussian() * 0.05,
				0, 0, 0
			);
			for (int i = 0; i < 2; i++) {
				level.addAlwaysVisibleParticle(new DustParticleOptions(0xfad959, 1f), true,
					topCenterPos.x + random.nextGaussian() * 0.2, topCenterPos.y - 0.1 + random.nextGaussian() * 0.25, topCenterPos.z + random.nextGaussian() * 0.2,
					0, 0, 0
				);

				level.addAlwaysVisibleParticle(new DustColorTransitionOptions(0xffeb66, 0x80e6ff, 0.8f), true,
					topCenterPos.x + random.nextGaussian() * 0.15, topCenterPos.y + 0.1 + random.nextGaussian() * 0.3, topCenterPos.z + random.nextGaussian() * 0.15,
					0, 0, 0
				);
			}

			if (random.nextDouble() < 0.25) level.addAlwaysVisibleParticle(ParticleTypes.END_ROD, true,
				topCenterPos.x + random.nextGaussian() * 0.08, topCenterPos.y + 0.2 + random.nextGaussian() * 0.3, topCenterPos.z + random.nextGaussian() * 0.08,
				random.nextGaussian() * 0.02, random.nextGaussian() * 0.02, random.nextGaussian() * 0.02
				);

			if (random.nextDouble() < 0.33) level.addAlwaysVisibleParticle(ParticleTypes.TOTEM_OF_UNDYING, true,
				topCenterPos.x, topCenterPos.y, topCenterPos.z,
				0, 0, 0
			);

			if (random.nextDouble() < 0.10) for (int i=0; i<2; i++) level.addAlwaysVisibleParticle(ParticleTypes.TOTEM_OF_UNDYING, true,
				topCenterPos.x + random.nextGaussian() * 0.15, topCenterPos.y + 0.1 + random.nextGaussian() * 0.2, topCenterPos.z + random.nextGaussian() * 0.15,
				random.nextGaussian() * 0.02, random.nextGaussian() * 0.02, random.nextGaussian() * 0.02
			);

			if (!itemsOnTop.isEmpty()) for (int i=0; i<2; i++) level.addAlwaysVisibleParticle(ParticleTypes.WAX_OFF, true,
				topCenterPos.x + random.nextGaussian() * 0.3, topCenterPos.y + 0.3 + random.nextGaussian() * 0.15, topCenterPos.z + random.nextGaussian() * 0.3,
				random.nextGaussian() * 0.01, random.nextGaussian() * 0.01, random.nextGaussian() * 0.01
			);

			return;
		}


	}
}
