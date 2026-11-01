package dev.coder2195.stellarity.util;

import net.minecraft.world.phys.Vec3;

public interface VectorUtil {
	/**
	 * I HATE MATH I THINK THIS WORKS HOPEFULLY
	 * <a href="https://www.desmos.com/3d/kltbjtfplm">Desmos</a>
	 * all vectors have a normal plane
	 * relativeVector is placed in a way where the xz plane for relativeVector is that source's normal plane
	 *
	 * @param source the vector that we use for defining the normal plane
	 * @param relativeVector vector
	 * @return the new vector
	 */
	static Vec3 relativeToNormalPlane(Vec3 source, Vec3 relativeVector) {
		if (source.x == 0 && source.y == 0 && source.z == 0) return Vec3.ZERO;
		if (relativeVector.x == 0 && relativeVector.y == 0 && relativeVector.z == 0) return Vec3.ZERO;
		var uy = source.normalize();
		var helper = Math.abs(uy.x) > 0.9 ? new Vec3(0, 0, 1) : new Vec3(1, 0, 0);
		var vx = helper.cross(uy);
		var ux =  vx.normalize();
		var uz = uy.cross(ux);
		return ux.scale(relativeVector.x).add(uy.scale(relativeVector.y)).add(uz.scale(relativeVector.z));
	}
}
