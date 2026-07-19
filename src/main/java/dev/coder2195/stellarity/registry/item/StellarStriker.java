package dev.coder2195.stellarity.registry.item;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.StellarityDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

public class StellarStriker extends Item {
	public static final int TOTAL_CHARGE_TIME = 167 * 20;
	public static final int HIT_CHARGE_TIME = (int) (TOTAL_CHARGE_TIME * 0.03f);
	public static final int DISABLE_TIME = 6 * 20;
	public static final double[] STAR_PERCENTAGES = {
		0, 0.1, 0.2, 0.45, 0.7, 1, 1.5
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

		if (itemStack.get(StellarityDataComponents.RECHARGES_AT) == null) {
			itemStack.set(StellarityDataComponents.RECHARGES_AT, level.getGameTime() + TOTAL_CHARGE_TIME);
			return;
		}

		if (!(EquipmentSlot.MAINHAND.equals(slot) && owner instanceof Player player && player.isShiftKeyDown() && player.isCrouching())) return;

		long gameTime = level.getGameTime();
		long abilityDisabledUntil = itemStack.getOrDefault(StellarityDataComponents.ABILITY_DISABLED_UNTIL, 0L);
		if (gameTime < abilityDisabledUntil) return;
		long rechargesAt = Objects.requireNonNull(itemStack.get(StellarityDataComponents.RECHARGES_AT));
		long timeFilled = TOTAL_CHARGE_TIME - Math.max(0, rechargesAt - gameTime);
		double percentFilled = (double) timeFilled / TOTAL_CHARGE_TIME;
		if (percentFilled < STAR_PERCENTAGES[1]) return;

		int newPercentageIdx = 0;
		for (int i=2; i<STAR_PERCENTAGES.length - 1; i++) {
			if (STAR_PERCENTAGES[i] > percentFilled) break;
			newPercentageIdx = i - 1;
		}

		double newPercentage = STAR_PERCENTAGES[newPercentageIdx] + (percentFilled - STAR_PERCENTAGES[newPercentageIdx + 1]) / (STAR_PERCENTAGES[newPercentageIdx + 2] - STAR_PERCENTAGES[newPercentageIdx + 1]) * (STAR_PERCENTAGES[newPercentageIdx + 1] - STAR_PERCENTAGES[newPercentageIdx]);
		level.players().forEach(p -> p.sendSystemMessage(Component.literal("STRIKING! " + (int) (newPercentage * 100) + "%"), false));

		itemStack.set(StellarityDataComponents.RECHARGES_AT, gameTime + (long) (TOTAL_CHARGE_TIME * (1 - newPercentage)));
		itemStack.set(StellarityDataComponents.ABILITY_DISABLED_UNTIL, gameTime + DISABLE_TIME);
	}

	@Override
	public void hurtEnemy(@NonNull ItemStack itemStack, @NonNull LivingEntity mob, @NonNull LivingEntity attacker) {
		super.hurtEnemy(itemStack, mob, attacker);

		if (!(attacker.level() instanceof ServerLevel level) || !attacker.getMainHandItem().equals(itemStack) || !mob.isDeadOrDying()) return;

		var gameTime = level.getGameTime();
		long rechargesAt = itemStack.getOrDefault(StellarityDataComponents.RECHARGES_AT, gameTime + TOTAL_CHARGE_TIME);
		if (gameTime >= rechargesAt) return;
		itemStack.set(StellarityDataComponents.RECHARGES_AT, rechargesAt - HIT_CHARGE_TIME);
	}
}
