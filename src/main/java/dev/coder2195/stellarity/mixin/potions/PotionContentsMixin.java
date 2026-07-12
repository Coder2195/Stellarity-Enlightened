
package dev.coder2195.stellarity.mixin.potions;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.StellarityPotions;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Consumer;


@Mixin(PotionContents.class)
public abstract class PotionContentsMixin {

	@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
	@Shadow
	@Final
	private Optional<Holder<Potion>> potion;


	@WrapOperation(method = "getColorOr", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/alchemy/PotionContents;getColorOptional(Ljava/lang/Iterable;)Ljava/util/OptionalInt;"))
	private OptionalInt getColorThis(Iterable<MobEffectInstance> iterable, Operation<OptionalInt> original) {
		if (potion.isPresent()) {
			Integer color = StellarityPotions.COLORS.get(potion.get());
			if (color != null) return OptionalInt.of(color);
		}

		return original.call(iterable);
	}


	@WrapMethod(method = "addToTooltip")
	private void removeRedTooltip(Item.TooltipContext tooltipContext, Consumer<Component> consumer, TooltipFlag tooltipFlag, DataComponentGetter dataComponentGetter, Operation<Void> original) {
		if (potion.map((p) -> p.is(Stellarity.id("red"))).orElse(false)) return;

		original.call(tooltipContext, consumer, tooltipFlag, dataComponentGetter);
	}


}