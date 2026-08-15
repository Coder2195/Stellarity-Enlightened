package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.Predicate;

@Mixin(ProjectileUtil.class)
public interface ProjectileUtilAccessor {
	@Invoker("getHitResult")
	static HitResult getHitResult(final Vec3 from, final Entity source, final Predicate<Entity> matching, final Vec3 delta, final Level level, final float entityMargin, final ClipContext.Block clipType) {
		throw new AssertionError("Not transformed!");
	}
}
