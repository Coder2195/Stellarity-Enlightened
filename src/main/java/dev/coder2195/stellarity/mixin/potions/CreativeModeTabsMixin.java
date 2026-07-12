package dev.coder2195.stellarity.mixin.potions;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.Holder;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.alchemy.Potion;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CreativeModeTabs.class)
public class CreativeModeTabsMixin {
	@WrapMethod(method = "lambda$generatePotionEffectTypes$0")
	private static boolean removeStellarityPotions(FeatureFlagSet enabledFeatures, Holder.Reference<Potion> potion, Operation<Boolean> original) {
		return original.call(enabledFeatures, potion) && !potion.key().identifier().getNamespace().equals("stellarity");
	}
}
