package dev.coder2195.stellarity.mixin.dragon_fight;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.level.dimension.end.EnderDragonFight;
import net.minecraft.world.level.levelgen.feature.EndSpikeFeature;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;

import java.util.UUID;


@Mixin(EnderDragonFight.class)
public abstract class EnderDragonFightMixin {
	@Shadow
	public boolean dragonKilled;

	@Shadow
	private int aliveCrystals;

	@Shadow
	private ServerLevel level;
	@Unique
	private final ServerBossEvent crystalsRemaining = new ServerBossEvent(UUID.fromString("d4a16717-72f2-4a42-8813-78e50b18f181"), Component.translatable("bossbar.stellarity.crystals_left", aliveCrystals).withStyle(Style.EMPTY.withColor(0x4C0081)), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.NOTCHED_20);


	@Definition(id = "EVENT_DISPLAY_NAME", field = "Lnet/minecraft/world/level/dimension/end/EnderDragonFight;EVENT_DISPLAY_NAME:Lnet/minecraft/network/chat/Component;")
	@Expression("EVENT_DISPLAY_NAME = @(?)")
	@ModifyExpressionValue(method = "<clinit>", at = @At("MIXINEXTRAS:EXPRESSION"))
	private static MutableComponent enderDragonBossBarColor(MutableComponent original) {
		return original.withColor(0xBF00C8);
	}

	@Inject(method = "tick", at = @At("TAIL"))
	private void tick(CallbackInfo ci) {
		crystalsRemaining.setVisible(!dragonKilled);
		if (!dragonKilled) {
			crystalsRemaining.setProgress(Math.min((float) aliveCrystals / EndSpikeFeature.NUMBER_OF_SPIKES, 1.0f));
			crystalsRemaining.setName(Component.translatable("bossbar.stellarity.crystals_left", aliveCrystals).withStyle(Style.EMPTY.withColor(0x4C0081)));
		}
	}


	@Inject(method = "updatePlayers", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerBossEvent;addPlayer(Lnet/minecraft/server/level/ServerPlayer;)V"))
	private void addPlayerBossBar(CallbackInfo ci, @Local(name = "player") ServerPlayer player) {
		crystalsRemaining.addPlayer(player);
	}

	@Inject(method = "updatePlayers", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerBossEvent;removePlayer(Lnet/minecraft/server/level/ServerPlayer;)V"))
	private void removePlayerBossBar(CallbackInfo ci, @Local(name = "player") ServerPlayer player) {
		crystalsRemaining.removePlayer(player);
	}


	@WrapOperation(method = "scanState", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/dimension/end/EnderDragonFight;dragonKilled:Z", opcode = Opcodes.PUTFIELD))
	private void scanStatePreventRevive(EnderDragonFight instance, boolean value, Operation<Void> original) {
		original.call(instance, true);
		Stellarity.LOGGER.info("force overriding to prevent dragon summoning");
	}


	@Definition(id = "hasPreviouslyKilledDragon", field = "Lnet/minecraft/world/level/dimension/end/EnderDragonFight;hasPreviouslyKilledDragon:Z")
	@Expression("this.hasPreviouslyKilledDragon == false")
	@ModifyExpressionValue(method = "setDragonKilled", at = @At("MIXINEXTRAS:EXPRESSION"))
	private boolean overrideEggSettings(boolean shouldSpawn) {
		return level.getServer().globalAttachments().getAttachedOrCreate(StellarityDataAttachments.CONFIG).alwaysGenerateEgg() || shouldSpawn;
	}
}
