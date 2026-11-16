package dev.coder2195.stellarity.item;

import dev.coder2195.stellarity.registry.StellarityToolMaterials;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Unit;
import net.minecraft.world.item.Item;

public class Dragonblade extends Item {
	public static final Properties PROPERTIES = new Properties().sword(StellarityToolMaterials.DRAGONBLADE, 9, 1.1f).component(DataComponents.UNBREAKABLE, Unit.INSTANCE);

	public Dragonblade(Properties properties) {
		super(properties);
	}


}
