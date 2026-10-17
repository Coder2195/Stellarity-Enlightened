package dev.coder2195.stellarity.item;

import dev.coder2195.stellarity.registry.StellarityToolMaterials;
import net.minecraft.world.item.Item;

public class SpiritDagger extends Item {
	public SpiritDagger(Properties properties) {
		super(properties);
	}

	public static final Properties PROPERTIES = new Properties().sword(StellarityToolMaterials.SPIRIT_DAGGER, 3, -2f);
}
