package dev.coder2195.stellarity.mixin.entity_rules;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Attributes.class)
public class AttributesMixin {

	@Definition(id = "MAX_HEALTH", field = "Lnet/minecraft/world/entity/ai/attributes/Attributes;MAX_HEALTH:Lnet/minecraft/core/Holder;")
	@Expression("MAX_HEALTH = ?(?, new ?(?,?,?,@(1024.0)).?(?))")
	@ModifyExpressionValue(method = "<clinit>", at = @At("MIXINEXTRAS:EXPRESSION"))
	private static double alterMaxHealth(double original) {
		return Math.max(original, 100000);
	}
}
