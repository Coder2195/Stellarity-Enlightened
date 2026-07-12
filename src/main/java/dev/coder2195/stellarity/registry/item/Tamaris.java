package dev.coder2195.stellarity.registry.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;
import dev.coder2195.stellarity.key.StellarityDamageTypes;
import dev.coder2195.stellarity.registry.StellaritySoundEvents;

import java.util.Comparator;

public class Tamaris extends Item {
	public Tamaris(Properties properties) {
		super(properties.sword(ToolMaterial.NETHERITE, 2, -2.4F));
	}


	public static final Properties PROPERTIES = new Item.Properties().stacksTo(1).durability(1561).rarity(Rarity.RARE);

	@Override

	public void inventoryTick(@NonNull ItemStack itemStack, @NonNull ServerLevel level, @NonNull Entity entity, @Nullable EquipmentSlot equipmentSlot) {
		super.inventoryTick(itemStack, level, entity, equipmentSlot);

		Vec3 position = entity.position();

		if (entity instanceof Player player) {
			if (player.getCooldowns().isOnCooldown(itemStack) || !player.isHolding(itemStack::equals))
				return;

			var nearbyEntities = level.getEntitiesOfClass(
				LivingEntity.class,
				new AABB(position.subtract(10, 10, 10), position.add(10, 10, 10)),
				e -> !e.is(player) && e.isAlive() && e.getHealth() / e.getMaxHealth() < 0.25f
			);


			if (!player.isCrouching()) return;
			nearbyEntities.sort(Comparator.comparingDouble((e) -> e.distanceTo(player)));

			for (var nearby : nearbyEntities) {
				boolean failed = false;
				for (InteractionHand interactionHand : InteractionHand.values()) {
					ItemStack itemStack2 = nearby.getItemInHand(interactionHand);
					if (itemStack2.is(Items.TOTEM_OF_UNDYING)) {
						failed = true;
						break;
					}
				}

				if (!nearby.hurtServer((ServerLevel) level, nearby.damageSources().source(StellarityDamageTypes.TAMARIS_EXECUTE, player), 999f))
					continue;

				var nearestPos = nearby.position();

				player.teleportTo(nearestPos.x, nearestPos.y, nearestPos.z);
				itemStack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);

				nearby.playSound(StellaritySoundEvents.TAMARIS_EXECUTE);

				if (failed) player.getCooldowns().addCooldown(itemStack, 11 * 20);

				break;
			}
		}
	}

}
