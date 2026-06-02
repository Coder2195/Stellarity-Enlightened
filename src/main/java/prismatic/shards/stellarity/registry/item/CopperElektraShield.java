package prismatic.shards.stellarity.registry.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import prismatic.shards.stellarity.registry.StellarityItems;

import java.util.List;
import java.util.Optional;

public class CopperElektraShield extends ShieldItem {
	public CopperElektraShield(Properties properties) {
		super(properties);
	}

	public static final Properties PROPERTIES = new Properties().stacksTo(1).durability(336).rarity(Rarity.UNCOMMON)
		.component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
		.repairable(ItemTags.WOODEN_TOOL_MATERIALS)
		.equippableUnswappable(EquipmentSlot.OFFHAND)
		.delayedComponent(
			DataComponents.BLOCKS_ATTACKS,
			/* lambda$static$150 */ context -> new BlocksAttacks(
				0.25F,
				1.0F,
				List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
				new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
				Optional.of(context.getOrThrow(DamageTypeTags.BYPASSES_SHIELD)),
				Optional.of(SoundEvents.SHIELD_BLOCK),
				Optional.of(SoundEvents.SHIELD_BREAK)
			)
		)
		.component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK);

	@Override
	public void inventoryTick(@NonNull ItemStack itemStack, @NonNull ServerLevel level, @NonNull Entity owner, @Nullable EquipmentSlot slot) {
		super.inventoryTick(itemStack, level, owner, slot);

		if (!(owner instanceof Player player && player.isShiftKeyDown() && player.isUsingItem() && player.getUseItem().is(StellarityItems.COPPER_ELEKTRA_SHIELD)))
			return;

	}


}
