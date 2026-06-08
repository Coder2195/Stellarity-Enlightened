package prismatic.shards.stellarity.mixin.elytra;

import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import prismatic.shards.stellarity.registry.StellarityDataComponents;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	public LivingEntityMixin(EntityType<?> type, Level level) {
		super(type, level);
	}

	@Shadow
	public abstract boolean isFallFlying();

	@Shadow
	public abstract ItemStack getItemBySlot(EquipmentSlot slot);

	@Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;updateSwimAmount()V"))
	private void particles(CallbackInfo ci) {
		var level = level();
		if (!level.isClientSide() || !isFallFlying()) return;

		var dyedColor = getItemBySlot(EquipmentSlot.CHEST).get(StellarityDataComponents.COLOR);
		if (dyedColor == null) return;
		int rgb = dyedColor.rgb();
		level.addAlwaysVisibleParticle(new DustColorTransitionOptions(rgb, ARGB.average(rgb, 0xFFFFFF), 1.0f), true, getX(), getY(), getZ(), 0, 0, 0);

	}
}
