package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LivingEntity.class)
public interface LivingEntityAccessor {
	@Accessor("lastDamageStamp")
	long stellarity$getLastDamageStamp();

	@Accessor("lastHurt")
	float stellarity$getLastHurt();
}
