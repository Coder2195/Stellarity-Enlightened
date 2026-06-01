package prismatic.shards.stellarity.registry.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShieldItem;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import prismatic.shards.stellarity.registry.StellarityItems;

public class CopperElektraShield extends ShieldItem {
	public CopperElektraShield(Properties properties) {
		super(properties);
	}

	public static final Properties PROPERTIES = new Properties().stacksTo(1).durability(336).rarity(Rarity.UNCOMMON);

	@Override
	public void inventoryTick(@NonNull ItemStack itemStack, @NonNull ServerLevel level, @NonNull Entity owner, @Nullable EquipmentSlot slot) {
		super.inventoryTick(itemStack, level, owner, slot);

		if (!(owner instanceof LivingEntity livingEntity && livingEntity.isUsingItem() && livingEntity.getUseItem().is(StellarityItems.COPPER_ELEKTRA_SHIELD)))
			return;


	}
}
