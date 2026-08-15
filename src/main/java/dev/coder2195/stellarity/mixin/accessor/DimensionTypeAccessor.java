package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.world.level.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(DimensionType.class)
public interface DimensionTypeAccessor {
	@Mutable
	@Accessor("logicalHeight")
	void stellarity$setLogicalHeight(int max);

	@Mutable
	@Accessor("height")
	void stellarity$setHeight(int max);
}
