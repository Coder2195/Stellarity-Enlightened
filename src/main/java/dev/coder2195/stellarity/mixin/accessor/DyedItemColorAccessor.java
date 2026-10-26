package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.block.ColorCollection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(DyedItemColor.class)
public interface DyedItemColorAccessor {
	@Accessor("DYE_COLORS")
	static ColorCollection<Integer> DYE_COLORS() {
		throw new AssertionError("Not transformed!");
	}
}
