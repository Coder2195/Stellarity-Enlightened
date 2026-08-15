package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.dimension.end.EnderDragonFight;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EnderDragonFight.class)
public interface EnderDragonFightAccessor {
	@Accessor("exitPortalLocation")
	BlockPos stellarity$getExitPortalLocation();

	@Accessor("dragonKilled")
	boolean stellarity$getDragonKilled();
}
