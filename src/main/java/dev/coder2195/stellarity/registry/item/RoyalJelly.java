package dev.coder2195.stellarity.registry.item;

import net.minecraft.advancements.triggers.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;


public class RoyalJelly extends Item {
	public RoyalJelly(Properties properties) {
		super(properties);
	}

	public static final Properties PROPERTIES = new Properties().stacksTo(16).craftRemainder(Items.GLASS_BOTTLE);

	@Override
	public @NonNull ItemStack finishUsingItem(@NonNull ItemStack itemStack, @NonNull Level level, @NonNull LivingEntity livingEntity) {
		super.finishUsingItem(itemStack, level, livingEntity);
		if (livingEntity instanceof ServerPlayer serverPlayer) {
			CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, itemStack);
			serverPlayer.awardStat(Stats.ITEM_USED.get(this));
		}

		if (!level.isClientSide()) {
			var effects = livingEntity.getActiveEffects().stream().toList();

			for (var effect : effects) {
				if (effect.getEffect().value().getCategory() == MobEffectCategory.HARMFUL) {
					livingEntity.removeEffect(effect.getEffect());
				}
			}
		}

		if (itemStack.isEmpty()) {
			return new ItemStack(Items.GLASS_BOTTLE);
		} else {
			if (livingEntity instanceof Player player && !(player.getAbilities().instabuild)) {
				ItemStack itemStack2 = new ItemStack(Items.GLASS_BOTTLE);
				if (!player.getInventory().add(itemStack2)) {
					player.drop(itemStack2, false);
				}
			}

			return itemStack;
		}
	}

	@Override
	public int getUseDuration(@NonNull ItemStack itemStack, @NonNull LivingEntity livingEntity) {
		return 40;
	}


}
