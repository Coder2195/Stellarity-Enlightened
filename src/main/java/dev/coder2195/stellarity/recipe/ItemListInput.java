package dev.coder2195.stellarity.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

import java.util.ArrayList;
import java.util.List;

public class ItemListInput extends ArrayList<ItemStack> implements RecipeInput {
	public ItemListInput(List<ItemStack> list) {
		super(list);
	}

	@Override
	public ItemStack getItem(int index) {
		return get(index);
	}
}
