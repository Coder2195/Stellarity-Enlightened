package prismatic.shards.stellarity.mixin.mob_effects;


import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import prismatic.shards.stellarity.registry.StellarityDataAttachments;
import prismatic.shards.stellarity.registry.StellarityMobEffects;
import prismatic.shards.stellarity.registry.effect.CreativeShockEffect;

import java.util.Collection;


@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player {
	@Shadow
	@Final
	public ServerPlayerGameMode gameMode;


	@Shadow
	public abstract boolean setGameMode(GameType gameType);

	@Shadow
	@Final
	private PlayerAdvancements advancements;


	public ServerPlayerMixin(Level level, GameProfile gameProfile) {
		super(level, gameProfile);
	}

	@Unique
	private void setLastGamemode(GameType gamemode) {
		setAttached(StellarityDataAttachments.LAST_GAMEMODE, gamemode);
	}

	@Unique
	private @Nullable GameType getLastGamemode() {
		return getAttached(StellarityDataAttachments.LAST_GAMEMODE);
	}


	@Inject(method = "onEffectAdded", at = @At("HEAD"))
	private void effectAdded(MobEffectInstance effectInstance, Entity entity, CallbackInfo ci) {
		var type = gameMode.getGameModeForPlayer();


		if (!effectInstance.is(StellarityMobEffects.CREATIVE_SHOCK)) return;

		if (!CreativeShockEffect.extremeCreativeShock() && type == GameType.CREATIVE) return;

		if (getLastGamemode() == null) setLastGamemode(type);
		setGameMode(GameType.ADVENTURE);
	}


	@Inject(method = "onEffectsRemoved", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerGamePacketListenerImpl;send(Lnet/minecraft/network/protocol/Packet;)V", shift = At.Shift.AFTER))
	protected void effectRemoved(Collection<MobEffectInstance> collection, CallbackInfo ci, @Local(name = "effect") MobEffectInstance effect) {
		if (!effect.is(StellarityMobEffects.CREATIVE_SHOCK)) return;

		var lastGamemode = getLastGamemode();
		if (lastGamemode == null) lastGamemode = GameType.SURVIVAL;

		setGameMode(lastGamemode);
		setLastGamemode(null);
	}
}
