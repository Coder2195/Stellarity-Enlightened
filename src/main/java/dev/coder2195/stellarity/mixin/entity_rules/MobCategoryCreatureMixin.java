package dev.coder2195.stellarity.mixin.entity_rules;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.MobCategory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MobCategory.class)
public class MobCategoryCreatureMixin {

	@Shadow
	@Final
	private String name;

	@WrapMethod(method = "getMaxInstancesPerChunk")
	private int changeCap(Operation<Integer> original) {
		int old = original.call();
		return name.equals("creature") ? Math.max(old, 40) : old;
	}
}
