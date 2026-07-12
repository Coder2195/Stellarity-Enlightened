package dev.coder2195.stellarity.registry.item;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import static dev.coder2195.stellarity.registry.StellarityItems.basicFood;

public class FrozenCarpaccio extends Item {
	public FrozenCarpaccio(Properties properties) {
		super(properties);
	}

	public static final Properties PROPERTIES = basicFood(7, 8.4f);

	@Override
	public @NonNull ItemStack finishUsingItem(ItemStack itemStack, @NonNull Level level, LivingEntity entity) {
		var number = entity.getRandom().nextInt(0, 10);
		if (itemStack.get(DataComponents.CONSUMABLE) != null && number < 6) {
			//noinspection unchecked
			entity.addEffect(new MobEffectInstance((new Holder[]{
				MobEffects.ABSORPTION, MobEffects.STRENGTH, MobEffects.REGENERATION, MobEffects.RESISTANCE, MobEffects.JUMP_BOOST, MobEffects.SPEED
			})[number], 30 * 20));
		}
		return super.finishUsingItem(itemStack, level, entity);
	}
}
