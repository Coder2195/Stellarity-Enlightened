package dev.coder2195.stellarity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FallingParticlesLeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Optional;

public class ColoredParticleLeavesBlock extends FallingParticlesLeavesBlock {
	private final int color;
	public ColoredParticleLeavesBlock(final float leafParticleChance, int color, final BlockBehaviour.Properties properties) {
		super(leafParticleChance, Optional.empty(), properties);
		this.color = color;

	}

	@Override
	protected void spawnFallingLeavesParticle(Level level, BlockPos pos, RandomSource random) {
		ColorParticleOption particle = ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, color);
		ParticleUtils.spawnParticleBelow(level, pos, random, particle);
	}
}
