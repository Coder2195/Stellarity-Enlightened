package dev.coder2195.stellarity.mixin.item_name_colors;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.fabricmc.fabric.api.item.v1.FabricItemStack;
import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import dev.coder2195.stellarity.registry.StellarityItems;
import dev.coder2195.stellarity.registry.StellarityPotions;


@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements DataComponentHolder, ItemInstance, FabricItemStack {
	@Shadow
	public abstract Item getItem();


	@ModifyExpressionValue(method = "getStyledHoverName", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/MutableComponent;withStyle(Lnet/minecraft/ChatFormatting;)Lnet/minecraft/network/chat/MutableComponent;"))
	private MutableComponent customItemColors(MutableComponent original) {

		var custom = get(DataComponents.CUSTOM_NAME);
		if (custom != null && custom.getStyle().getColor() != null) return original;

		var itemColor = StellarityItems.NAME_COLORS.get(getItem());
		if (itemColor != null) {
			original.withColor(itemColor);
		}

		if (is(Items.POTION)) {
			var potionContents = get(DataComponents.POTION_CONTENTS);
			if (potionContents == null) return original;
			potionContents.potion().map(StellarityPotions.NAME_COLORS::get).ifPresent(original::withColor);
		}

		return original;
	}
}
