package dev.coder2195.stellarity.mixin.exit_portal;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractChestBlock;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;

import java.util.function.Supplier;

@Mixin(ChestBlock.class)
public abstract class ChestBlockMixin extends AbstractChestBlock<ChestBlockEntity> {
	protected ChestBlockMixin(Properties properties, Supplier<BlockEntityType<? extends ChestBlockEntity>> blockEntityType) {
		super(properties, blockEntityType);
	}

	@WrapMethod(method = "getTicker")
	private <T extends BlockEntity> @NonNull BlockEntityTicker<T> stellarityTicking(Level level, BlockState blockState, BlockEntityType<T> type, Operation<BlockEntityTicker<T>> original) {
		final var originalMethod = original.call(level, blockState, type);
		return (level1, pos, state, entity) -> {
			if (level1.isClientSide() && entity.getAttachedOrElse(StellarityDataAttachments.EXIT_PORTAL_CHEST, false)) {
				for (int i = 0; i < 3; i++) {
					Vec3 position = Vec3.atCenterOf(entity.getBlockPos());

					var random = level.getRandom();

					double xv = random.nextGaussian() * 0.15;
					double yv = random.nextGaussian() * 0.15;
					double zv = random.nextGaussian() * 0.15;
					double xa = random.nextGaussian() * 0.8;
					double ya = random.nextGaussian() * 0.8;
					double za = random.nextGaussian() * 0.8;

					level.addParticle(ParticleTypes.PORTAL, true, true, position.x + xv, position.y + yv, position.z + zv, xa, ya, za);
				}
			}

			if (originalMethod != null) originalMethod.tick(level, pos, state, entity);
		};
	}

}
