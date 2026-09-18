package dev.coder2195.stellarity.mixin.entity_rules;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import dev.coder2195.stellarity.entity.DragonBreathCauldronIngredient;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerEntity.class)
public class ServerEntityMixin {
	@Shadow
	@Final
	private Entity entity;

	@Definition(id = "shouldSendPosition", local = @Local(type = boolean.class, name = "shouldSendPosition"))
	@Expression("shouldSendPosition = ?")
	@Inject(method = "sendChanges", at = @At(value = "MIXINEXTRAS:EXPRESSION", shift = At.Shift.AFTER))
	private void sdffds(CallbackInfo ci, @Local(name = "shouldSendPosition") LocalBooleanRef positionChanged) {
		if (entity instanceof DragonBreathCauldronIngredient) positionChanged.set(false);
	}
}
