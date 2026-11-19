package dev.coder2195.stellarity.item;

import dev.coder2195.stellarity.registry.StellarityToolMaterials;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Unit;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.DamageResistant;

public class Dragonblade extends Item {
	public static final Properties PROPERTIES = new Properties().sword(StellarityToolMaterials.DRAGONBLADE, 9, 1.1f).component(DataComponents.UNBREAKABLE, Unit.INSTANCE).delayedComponent(
		DataComponents.DAMAGE_RESISTANT, provider -> new DamageResistant(HolderSet.direct(provider::getOrThrow,
			DamageTypes.IN_FIRE, DamageTypes.ON_FIRE, DamageTypes.LAVA, DamageTypes.LAVA, DamageTypes.CAMPFIRE,
			DamageTypes.CACTUS, DamageTypes.FALLING_ANVIL, DamageTypes.LIGHTNING_BOLT, DamageTypes.FIREBALL, DamageTypes.UNATTRIBUTED_FIREBALL,
			DamageTypes.EXPLOSION, DamageTypes.PLAYER_EXPLOSION, DamageTypes.WITHER, DamageTypes.WITHER_SKULL
		))
	);

	public Dragonblade(Properties properties) {
		super(properties);
	}


}
