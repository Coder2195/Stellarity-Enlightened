package dev.coder2195.stellarity.mixin.interface_injections;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.fabricmc.fabric.impl.attachment.AttachmentTargetImpl;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import dev.coder2195.stellarity.interface_injection.ExtEntity;

@SuppressWarnings("UnstableApiUsage")
@Mixin(Entity.class)
public abstract class EntityMixin implements ExtEntity, AttachmentTargetImpl {
	@Shadow
	public abstract Level level();

	@WrapMethod(method = "getTeamColor")
	private int customGlowColor(Operation<Integer> original) {
		int color = stellarity$getGlowColor();
		if (color != -1) return color;

		return original.call();
	}

}
