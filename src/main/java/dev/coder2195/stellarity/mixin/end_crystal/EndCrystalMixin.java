package dev.coder2195.stellarity.mixin.end_crystal;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.end.EnderDragonFight;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.StellarityConfig;
import dev.coder2195.stellarity.interface_injection.ExtEndCrystal;

@Mixin(EndCrystal.class)
public abstract class EndCrystalMixin extends Entity implements ExtEndCrystal {

	public EndCrystalMixin(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}


	@Override
	public void stellarity$setType(Type type) {
		ExtEndCrystal.super.stellarity$setType(type);

		boolean glow = type != Type.NORMAL;
		setGlowingTag(glow);
		this.stellarity$setGlowColor(glow ? 11141290 : -1);
	}

	@Definition(id = "source", local = @Local(type = DamageSource.class, name = "source", argsOnly = true))
	@Expression("source.?(?) == false")
	@ModifyExpressionValue(method = "hurtServer", at = @At("MIXINEXTRAS:EXPRESSION"))

	public boolean blockExplodeAndDrop(boolean original, @Local(argsOnly = true, name = "source") DamageSource source, @Local(argsOnly = true, name = "level") ServerLevel level) {
		boolean drop = shouldDrop(source);
		if (drop) {
			BlockPos pos = blockPosition();
			if (level.getBlockState(pos).is(BlockTags.FIRE)) {
				level.setBlock(blockPosition(), Blocks.AIR.defaultBlockState(), Block.UPDATE_CLIENTS);
			}

			playSound(SoundEvents.GLASS_BREAK, 1, 0.8f);
			playSound(SoundEvents.AMETHYST_BLOCK_BREAK, 1, 0f);

			if (shouldDrop(source) && !source.isCreativePlayer())
				level.addFreshEntity(new ItemEntity(level, getX(), getY(), getZ(), new ItemStack(Items.END_CRYSTAL)));
		}
		return original && !drop;
	}

	@Unique
	private boolean shouldDrop(DamageSource damageSource) {
		var type = stellarity$getType();
		if (type.equals(Type.RESPAWN)) return true;
		if (!(damageSource.getEntity() instanceof Player player) || player.isCrouching()) return false;

		return type.equals(Type.NORMAL) && level() instanceof ServerLevel serverLevel && StellarityConfig.get(serverLevel.getServer()).enableEndCrystalDrop();
	}

	@Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/boss/enderdragon/EndCrystal;blockPosition()Lnet/minecraft/core/BlockPos;", shift = At.Shift.AFTER))
	public void checkType(CallbackInfo ci) {
		Type type = stellarity$getType();
		ServerLevel level = (ServerLevel) level();
		EnderDragonFight dragonFight = level.getDragonFight();
		BlockPos blockPos = blockPosition();
		BlockPos portalLocation = dragonFight == null ? null : dragonFight.exitPortalLocation;
		if (type == Type.RESPAWN) {
			if (portalLocation == null) {
				stellarity$setType(Type.NORMAL);
				Stellarity.LOGGER.info("bad respawn crystal");
			} else {
				boolean valid = false;
				for (Direction direction : Direction.Plane.HORIZONTAL) {
					if (portalLocation.above(3).relative(direction, 4).equals(blockPos)) {
						valid = true;
						break;
					}
				}
				if (!valid) {
					stellarity$setType(Type.NORMAL);
					Stellarity.LOGGER.info("bad respawn crystal 2");
				}
			}
		} else if (type == Type.NORMAL) {
			if (portalLocation != null) {
				for (Direction direction : Direction.Plane.HORIZONTAL) {
					if (portalLocation.above(3).relative(direction, 4).equals(blockPos) && dragonFight.dragonKilled) {
						stellarity$setType(Type.RESPAWN);
						this.playSound(SoundEvents.END_PORTAL_FRAME_FILL, 1, 0.5f);
						Stellarity.LOGGER.info("Found respawn crystal");
						break;
					}
				}
			}
		}
	}
}
