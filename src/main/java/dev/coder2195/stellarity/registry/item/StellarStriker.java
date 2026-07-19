package dev.coder2195.stellarity.registry.item;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.StellarityDataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class StellarStriker extends Item {
	public static final int TOTAL_CHARGE_TIME = 167 * 20;
	public static final int HIT_CHARGE_TIME = (int) (TOTAL_CHARGE_TIME * 0.03f);
	public static final int DISABLE_TIME = 6 * 20;
	public static final double[] STAR_PERCENTAGES = {
		0.1, 0.2, 0.45, 0.7, 1
	};

	public static final Vec3[] STAR_POSITIONS = {
		new Vec3(0, 2.5, 0), new Vec3(-1, 2, 0), new Vec3(1, 2, 0), new Vec3(1.2, 1.1, 0), new Vec3(-1.2, 1.1, 0),
	};

	public static final Properties PROPERTIES = new Properties().stacksTo(1).sword(new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 9, 6, 15, ItemTags.NETHERITE_TOOL_MATERIALS) {
		@Override
		public @NonNull ItemAttributeModifiers createSwordAttributes(float attackDamageBaseline, float attackSpeedBaseline) {
			return super.createSwordAttributes(attackDamageBaseline, attackSpeedBaseline)
				.withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(Stellarity.id("weapon_reach"), 0.3, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
		}
	}, 3, -3.1f);


	public StellarStriker(Properties properties) {
		super(properties);
	}

	@Override
	public void inventoryTick(@NonNull ItemStack itemStack, @NonNull ServerLevel level, @NonNull Entity owner, @Nullable EquipmentSlot slot) {
		super.inventoryTick(itemStack, level, owner, slot);

		if (itemStack.get(StellarityDataComponents.RECHARGES_AT) == null)
			itemStack.set(StellarityDataComponents.RECHARGES_AT, level.getGameTime() + TOTAL_CHARGE_TIME);

	}

	public static double chargePercent(long rechargesAt, long gameTime) {
		return Mth.clamp((TOTAL_CHARGE_TIME - (rechargesAt - gameTime)) / (double) TOTAL_CHARGE_TIME, 0, 1);
	}

	public static int stars(double chargePercent) {
		int stars = 0;
		for (double percentage : STAR_PERCENTAGES) {
			if (chargePercent < percentage) break;
			stars++;
		}
		return stars;
	}

	@Override
	public void hurtEnemy(@NonNull ItemStack itemStack, @NonNull LivingEntity mob, @NonNull LivingEntity attacker) {
		super.hurtEnemy(itemStack, mob, attacker);

		if (!(attacker.level() instanceof ServerLevel level) || !attacker.getMainHandItem().equals(itemStack)) return;

		long gameTime = level.getGameTime();
		long rechargesAt = itemStack.getOrDefault(StellarityDataComponents.RECHARGES_AT, gameTime + TOTAL_CHARGE_TIME);
		if (itemStack.getOrDefault(StellarityDataComponents.ABILITY_DISABLED_UNTIL, 0L) > gameTime) return;
		double percentageCharged = chargePercent(rechargesAt, gameTime);

		if (attacker.isCrouching() && (!(attacker instanceof Player player) || player.isShiftKeyDown()) && percentageCharged >= STAR_PERCENTAGES[0]) {
			itemStack.set(StellarityDataComponents.RECHARGES_AT, gameTime + DISABLE_TIME + TOTAL_CHARGE_TIME);
			itemStack.set(StellarityDataComponents.ABILITY_DISABLED_UNTIL, gameTime + DISABLE_TIME);
			return;
		}
		if (!mob.isDeadOrDying()) return;

		itemStack.set(StellarityDataComponents.RECHARGES_AT, rechargesAt - HIT_CHARGE_TIME);
	}
}
