package dev.coder2195.stellarity.mixin.dragon_fight;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.enderdragon.EnderDragonPart;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.end.EnderDragonFight;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import dev.coder2195.stellarity.StellarityConfig;


@Mixin(EnderDragon.class)
public abstract class EnderDragonMixin extends Mob implements Enemy {
	@Shadow
	@Nullable
	private EnderDragonFight dragonFight;

	protected EnderDragonMixin(EntityType<? extends Mob> entityType, Level level) {
		super(entityType, level);
	}


	@Inject(method = "<init>", at = @At("CTOR_HEAD"))
	private void setHealth(EntityType<? extends EnderDragon> type, Level level, CallbackInfo ci) {
		var maxHealth = this.getAttribute(Attributes.MAX_HEALTH);
		if (level instanceof ServerLevel serverLevel && maxHealth != null)
			maxHealth.setBaseValue(StellarityConfig.get(serverLevel).dragonHealth());
	}

	@WrapOperation(method = "onCrystalDestroyed", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/boss/enderdragon/EnderDragon;hurt(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/boss/enderdragon/EnderDragonPart;Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
	private boolean blockDamage(EnderDragon instance, ServerLevel serverLevel, EnderDragonPart enderDragonPart, DamageSource damageSource, float v, Operation<Boolean> original) {
		return false;

	}


	@Inject(method = "hurt(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/boss/enderdragon/EnderDragonPart;Lnet/minecraft/world/damagesource/DamageSource;F)Z", at = @At("HEAD"), cancellable = true)
	private void invulnerable(ServerLevel serverLevel, EnderDragonPart enderDragonPart, DamageSource damageSource, float f, CallbackInfoReturnable<Boolean> cir) {

		if (dragonFight != null && dragonFight.aliveCrystals() != 0) {
			cir.setReturnValue(false);
			cir.cancel();
		}
	}

	@Definition(id = "DRAGON_IMMUNE", field = "Lnet/minecraft/tags/BlockTags;DRAGON_IMMUNE:Lnet/minecraft/tags/TagKey;")
	@Expression("?.?(DRAGON_IMMUNE)")
	@WrapOperation(method = "checkWalls", at = @At("MIXINEXTRAS:EXPRESSION"))
	private boolean dontBreakBlocks(BlockState instance, TagKey<Block> tagKey, Operation<Boolean> original) {
		return true;
	}

}
