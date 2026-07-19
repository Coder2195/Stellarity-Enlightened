package dev.coder2195.stellarity.mixin.stellar_striker;

import dev.coder2195.stellarity.registry.StellarityDataComponents;
import dev.coder2195.stellarity.registry.StellarityItems;
import dev.coder2195.stellarity.registry.item.StellarStriker;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	@Shadow
	public abstract boolean isHolding(Item item);

	@Shadow
	public abstract ItemStack getMainHandItem();

	@Shadow
	public abstract ItemStack getOffhandItem();

	@Shadow
	public abstract float getYHeadRot();

	public LivingEntityMixin(EntityType<?> type, Level level) {
		super(type, level);
	}

	@Inject(method = "tick", at=@At("HEAD"))
	private void visualEffects(CallbackInfo ci) {
		var level = level();
		if (!(level.isClientSide() && isHolding(StellarityItems.STELLAR_STRIKER))) return;

		var gameTime = level.getGameTime();
		var item = getMainHandItem();
		if (!item.is(StellarityItems.STELLAR_STRIKER)) item = getOffhandItem();

		if (item.getOrDefault(StellarityDataComponents.ABILITY_DISABLED_UNTIL, 0L) > gameTime) return;
		var rechargesAt = item.get(StellarityDataComponents.RECHARGES_AT);
		if (rechargesAt == null) return;
		double chargePercent = (StellarStriker.TOTAL_CHARGE_TIME - (rechargesAt - gameTime)) / (double) StellarStriker.TOTAL_CHARGE_TIME;

		int stars = StellarStriker.stars(chargePercent);

		var position = position();
		var rotation = -getYHeadRot() * (float) (Math.PI / 180d);

		stars = Math.min(stars, StellarStriker.STAR_POSITIONS.length);

		for (int i=0; i<stars; i++) {
			var starPosition = position.add(StellarStriker.STAR_POSITIONS[i].yRot(rotation));
			level.addParticle(new DustColorTransitionOptions(0xffffa8, 0xec9a00, 0.7f), starPosition.x, starPosition.y, starPosition.z, 0, 0, 0);
		}

	}
}
