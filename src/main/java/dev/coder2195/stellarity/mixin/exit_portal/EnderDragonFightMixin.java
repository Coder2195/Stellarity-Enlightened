package dev.coder2195.stellarity.mixin.exit_portal;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.dimension.end.EnderDragonFight;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import dev.coder2195.stellarity.interface_injection.ExtEnderDragonFight;
import dev.coder2195.stellarity.key.StellarityLootTables;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;

import java.util.List;


@Mixin(EnderDragonFight.class)
public abstract class EnderDragonFightMixin implements ExtEnderDragonFight {
	@Shadow
	@Nullable
	public BlockPos
		exitPortalLocation;

	@Shadow
	private ServerLevel
		level;

	@Shadow
	public

	abstract boolean hasPreviouslyKilledDragon();

	@Shadow
	public List<EntityReference<EndCrystal>> respawnCrystals;

	@Redirect(method = "tryRespawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;relative(Lnet/minecraft/core/Direction;I)Lnet/minecraft/core/BlockPos;"))
	private BlockPos

	adjustPosition(BlockPos blockPos, Direction direction, int i) {
		return blockPos.relative(direction, 4).above(2);

	}

	@Inject(method = "spawnExitPortal", at = @At("TAIL"))
	private void placeChest(boolean bl, CallbackInfo ci) {
		if (stellarity$portalChestGenerated() || exitPortalLocation == null) return;
		var chestPos = exitPortalLocation.offset(7, 1, 0);
		level.setBlock(chestPos, Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.EAST), Block.UPDATE_CLIENTS);
		var entity = level.getBlockEntity(chestPos);

		if (entity instanceof ChestBlockEntity chestEntity) {
			chestEntity.setLootTable(StellarityLootTables.EXIT_PORTAL, level.getSeed());
			chestEntity.setAttached(StellarityDataAttachments.EXIT_PORTAL_CHEST, true);

			stellarity$setPortalChestGenerated(true);

		}

	}

	@WrapOperation(method = "abortRespawnSequence", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/dimension/end/EnderDragonFight;spawnExitPortal(Z)V"))
	private void dontAllowEscape(EnderDragonFight instance, boolean bl, Operation<Void> original) {
		original.call(instance, hasPreviouslyKilledDragon());

	}

	@Inject(method = "resetSpikeCrystals", at = @At("HEAD"))
	private void resetRespawnCrystals(CallbackInfo ci) {
		if (respawnCrystals == null) return;
		for (EntityReference<EndCrystal> ref : respawnCrystals) {
			var endCrystal = ref.getEntity(level, EndCrystal.class);
			if (endCrystal == null) continue;
			endCrystal.setInvulnerable(false);
			endCrystal.setBeamTarget(null);

		}

	}

	@WrapMethod(method = "spawnExitPortal")
	private void setPortalRespawn(boolean activated, Operation<Void> original) {
		original.call(activated);
		if (exitPortalLocation == null) return;
	}

	// FIXME: once this is corrected in official repos
//	@WrapOperation(method = "onCrystalDestroyed", at = @At(value = "INVOKE", target = "Ljava/util/List;contains(Ljava/lang/Object;)Z"))
//	private boolean bugFixEndCrystalCheck(List<EntityReference<EndCrystal>> instance, Object o, Operation<Boolean> original) {
//		if (!(o instanceof EndCrystal endCrystal)) return false;
//		for (var ref : instance) {
//			if (ref.matches(endCrystal)) return true;
//		}
//
//		return false;
//	}
}



