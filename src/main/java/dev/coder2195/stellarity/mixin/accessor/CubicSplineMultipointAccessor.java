package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.util.BoundedFloatFunction;
import net.minecraft.util.CubicSpline;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(CubicSpline.Multipoint.class)
public interface CubicSplineMultipointAccessor {
	@Invoker("createFromPoints")
	static <I extends BoundedFloatFunction<?>> CubicSpline.Multipoint<I> createFromPoints(
		final I coordinate, final List<CubicSpline.Multipoint.Point<I>> points
	) {
		throw new AssertionError("Not transformed!");
	}
}
