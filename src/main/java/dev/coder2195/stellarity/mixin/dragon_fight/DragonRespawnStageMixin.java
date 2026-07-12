package dev.coder2195.stellarity.mixin.dragon_fight;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "net/minecraft/world/level/dimension/end/DragonRespawnStage$3")
public class DragonRespawnStageMixin {
	// Purely to prevent a section of the end portal from being chopped
	@WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;removeBlock(Lnet/minecraft/core/BlockPos;Z)Z"))
	private boolean bugFixAltarSectionDeleted(ServerLevel instance, BlockPos blockPos, boolean b, Operation<Boolean> original) {
		return false;
	}
}
