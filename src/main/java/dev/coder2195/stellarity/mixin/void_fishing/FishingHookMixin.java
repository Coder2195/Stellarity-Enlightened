package dev.coder2195.stellarity.mixin.void_fishing;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.PowerParticleOption;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import dev.coder2195.stellarity.interface_injection.ExtFishingHook;
import dev.coder2195.stellarity.key.StellarityLootTables;
import dev.coder2195.stellarity.registry.StellarityCriteriaTriggers;
import dev.coder2195.stellarity.registry.StellarityItems;


@Mixin(FishingHook.class)
public abstract class FishingHookMixin extends Projectile implements ExtFishingHook {
	@Unique
	private static final ParticleOptions DRAGON_BREATH = PowerParticleOption.create(ParticleTypes.DRAGON_BREATH, 1f);

	@Shadow
	private FishingHook.FishHookState currentState;

	@Unique
	private boolean warned = false;

	@Unique
	private boolean isVoidFishing = false;

	@Unique
	private boolean splashed = false;

	@Shadow
	@Nullable
	public abstract Player getPlayerOwner();

	@Shadow
	@Final
	private int lureSpeed;

	@Shadow
	private int timeUntilLured;

	public FishingHookMixin(EntityType<? extends Projectile> entityType, Level level) {
		super(entityType, level);
	}

	@Unique
	private boolean isEnd() {
		return this.level().dimensionTypeRegistration().is(BuiltinDimensionTypes.END);
	}

	@Unique
	private boolean isEndMidAir() {
		BlockPos pos = this.blockPosition();

		return isEnd() && this.level().getBlockState(pos).is(Blocks.AIR) && this.level().getBlockState(pos.below()).is(Blocks.AIR);
	}

	@Unique
	private boolean evalVoidFishing() {
		return isEndMidAir() && currentState == FishingHook.FishHookState.BOBBING;
	}

	@Unique
	private boolean canBob() {
		var owner = this.getOwner();
		if (owner == null) return false;
		double y = getY();

		if (y < 0 && !warned) {
			warned = true;
			if (getPlayerOwner() instanceof ServerPlayer player) {
				player.connection.send(new ClientboundSetActionBarTextPacket(Component.translatable("message.stellarity.void_fishing_too_deep").withStyle(ChatFormatting.DARK_PURPLE)));
			}
		}

		if (y > 0 && warned) {
			warned = false;
		}

		return !warned && isEndMidAir() && (this.currentState == FishingHook.FishHookState.BOBBING || this.distanceTo(owner) > 20.0);
	}

	@Definition(id = "f", local = @Local(type = float.class))
	@Expression("f > 0.0")
	@ModifyExpressionValue(method = "tick()V", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
	private boolean checkCanBob(boolean original) {
		return canBob() || original;
	}

	@WrapOperation(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/FishingHook;move(Lnet/minecraft/world/entity/MoverType;Lnet/minecraft/world/phys/Vec3;)V"))
	private void voidFishingHover(FishingHook instance, MoverType moverType, Vec3 vec3, Operation<Void> original) {
		this.isVoidFishing = evalVoidFishing();
		if (isVoidFishing) {
			this.setDeltaMovement(0.0, 0.0, 0.0);
		} else {
			original.call(instance, moverType, vec3);
		}
	}

	@Inject(method = "tick", at = @At("TAIL"))
	private void visuals(CallbackInfo ci) {
		var level = level();
		if (level.isClientSide()) {
			if (!isVoidFishing) return;
			double x = getX();
			double y = getY();
			double z = getZ();

			if (!splashed) {
				splashed = true;

				for (float i = 0; i < 2 * Mth.PI; i += Mth.PI / 30) {
					float vx = Mth.cos(i) * 0.04f;
					float vz = Mth.sin(i) * 0.04f;

					level.addParticle(DRAGON_BREATH, x, y, z, vx, 0, vz);

				}
			}

			ParticleOptions particleType = random.nextBoolean() ? DRAGON_BREATH : ParticleTypes.END_ROD;


			double dx = random.nextDouble() * 4 - 2;
			double dy = random.nextDouble() * 2 - 1;
			double dz = random.nextDouble() * 4 - 2;

			level.addParticle(
				particleType,
				x + dx,
				y + dy,
				z + dz,
				dx * 0.01,
				dy * 0.01,
				dz * 0.01
			);
		}
	}


	@WrapOperation(method = "catchingFish", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Ljava/lang/Object;)Z"))
	private boolean addVoidVishingToWaterCheck(BlockState instance, Object block, Operation<Boolean> original) {
		return isVoidFishing || original.call(instance, block);
	}

	@WrapOperation(method = "shouldStopFishing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
	public boolean dontStopFisherOfVoids(ItemStack instance, Object item, Operation<Boolean> original) {
		return instance.is(StellarityItems.FISHER_OF_VOIDS) || original.call(instance, item);
	}


	@WrapOperation(method = "catchingFish", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;sendParticles(Lnet/minecraft/core/particles/ParticleOptions;DDDIDDDD)I"))
	private int splashParticles(ServerLevel instance, ParticleOptions particleOptions, double d, double e, double f, int i, double g, double h, double j, double k, Operation<Integer> original) {
		if (isVoidFishing) {
			if (particleOptions == ParticleTypes.SPLASH || particleOptions == ParticleTypes.FISHING)
				particleOptions = ParticleTypes.WITCH;
			if (particleOptions == ParticleTypes.BUBBLE) particleOptions = DRAGON_BREATH;
		}

		return original.call(instance, particleOptions, d, e, f, evalVoidFishing() ? i * 2 : i, g, h, j, k);
	}


	@WrapOperation(method = "catchingFish", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/FishingHook;playSound(Lnet/minecraft/sounds/SoundEvent;FF)V"))
	public void louderSplash(FishingHook instance, SoundEvent soundEvent, float v, float p, Operation<Void> original) {
		original.call(instance, soundEvent, evalVoidFishing() ? 1.5f : v, p);
	}


	@WrapOperation(method = "catchingFish", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/projectile/FishingHook;lureSpeed:I", opcode = Opcodes.GETFIELD))
	private int increaseLure(FishingHook instance, Operation<Integer> original) {
		isVoidFishing = evalVoidFishing();
		int lure = original.call(instance);
		if (!stellarity$getVoidFishingBuff() || !isVoidFishing) {
			return lure;
		}

		return lure + 200;
	}

	@WrapOperation(method = "retrieve", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/loot/LootTable;getRandomItems(Lnet/minecraft/world/level/storage/loot/LootParams;)Lit/unimi/dsi/fastutil/objects/ObjectArrayList;"))
	private ObjectArrayList<ItemStack> voidFishingRetrieve(LootTable instance, LootParams params, Operation<ObjectArrayList<ItemStack>> original, @Local(name = "owner") Player owner, @Local(argsOnly = true, name = "rod") ItemStack rod) {
		if (isVoidFishing && level() instanceof ServerLevel level) {
			instance = level.getServer().reloadableRegistries().getLootTable(StellarityLootTables.VOID_FISHING_EVENT);
		}
		ObjectArrayList<ItemStack> list = original.call(instance, params);
		if (isVoidFishing) {
			StellarityCriteriaTriggers.VOID_FISHED.trigger((ServerPlayer) owner, rod, (FishingHook) (Object) this, list);
		}
		return list;
	}
}