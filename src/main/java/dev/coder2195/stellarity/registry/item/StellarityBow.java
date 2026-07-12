package dev.coder2195.stellarity.registry.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;
import dev.coder2195.stellarity.util.tuple.Tuple3;

public interface StellarityBow {
	Tuple3<@Nullable SoundEvent, @Nullable Float, @Nullable Float> DEFAULT = new Tuple3<>(null, null, null);

	default @Nullable Tuple3<@Nullable SoundEvent, @Nullable Float, @Nullable Float> shootSound(final ItemStack weapon, ItemStack projectile, final Level level, final LivingEntity entity) {
		return DEFAULT;
	}
}
