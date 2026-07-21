package dev.coder2195.stellarity.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;

import java.util.function.Predicate;

public interface RaycastUtil {

	static HitResult raycastLine(Level level, Vec3 from, Vec3 to, ClipContext.Block blockContext, ClipContext.Fluid fluidContext, Predicate<Entity> matchEntityPredicate) {
		HitResult closestHit  = level.clip(new ClipContext(from, to, blockContext, fluidContext, CollisionContext.empty()));
		if (closestHit.getType().equals(HitResult.Type.BLOCK)) to = closestHit.getLocation();
		double distance = from.distanceTo(to);

		for (var entity : level.getEntities(null, new AABB(from, to).inflate(1))) {
			if (!matchEntityPredicate.test(entity)) continue;

			var clipResultOptional = entity.getBoundingBox().clip(from, to);
			if (clipResultOptional.isEmpty()) continue;
			var clipResult = clipResultOptional.get();
			double testDistance = from.distanceTo(clipResult);
			if (testDistance >= distance) continue;
			distance = testDistance;
			closestHit = new EntityHitResult(entity, clipResult);
		}

		return closestHit;
	}
}
