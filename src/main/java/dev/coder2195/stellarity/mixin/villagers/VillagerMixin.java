package dev.coder2195.stellarity.mixin.villagers;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.npc.villager.VillagerData;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import dev.coder2195.stellarity.registry.StellarityVillagerProfessions;

@Mixin(Villager.class)
public abstract class VillagerMixin extends AbstractVillager {
	public VillagerMixin(EntityType<? extends AbstractVillager> type, Level level) {
		super(type, level);
	}

	@Inject(method = "updateTrades", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/npc/villager/Villager;addOffersFromTradeSet(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/trading/MerchantOffers;Lnet/minecraft/resources/ResourceKey;)V", shift = At.Shift.AFTER))
	private void additionalTrades(ServerLevel level, CallbackInfo ci, @Local(name = "data") VillagerData data) {
		var extra = StellarityVillagerProfessions.getExtraTradeSets(data);
		if (extra == null) return;

		for (var set : extra) {
			addOffersFromTradeSet(level, getOffers(), set);
		}
	}
}
