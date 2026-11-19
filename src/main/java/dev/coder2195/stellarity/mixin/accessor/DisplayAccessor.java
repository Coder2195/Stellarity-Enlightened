package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Display.class)
public interface DisplayAccessor {
	@Accessor("DATA_HEIGHT_ID")
	static EntityDataAccessor<Float> DATA_HEIGHT_ID() {
		throw new AssertionError("Not transformed!");
	}

	@Accessor("DATA_WIDTH_ID")
	static EntityDataAccessor<Float> DATA_WIDTH_ID() {
		throw new AssertionError("Not transformed!");
	}
}
