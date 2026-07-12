package dev.coder2195.stellarity.registry.entity;


import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ByIdMap;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;
import dev.coder2195.stellarity.registry.StellarityEntityDataSerializers;
import dev.coder2195.stellarity.registry.StellarityEntityTypes;
import dev.coder2195.stellarity.registry.StellarityItems;
import dev.coder2195.stellarity.util.CustomCodecs;

import java.util.Set;
import java.util.function.IntFunction;

public class ThrownPrismaticPearl extends ThrowableItemProjectile {
	public ThrownPrismaticPearl(EntityType<? extends ThrownPrismaticPearl> entityType, Level level) {
		super(entityType, level);
	}

	private static final EntityDataAccessor<Trail> DATA_TRAIL = SynchedEntityData.defineId(ThrownPrismaticPearl.class, StellarityEntityDataSerializers.PRISMATIC_PEARL_TRAIL);

	private @Nullable Vec3 oldPos = null;

	public ThrownPrismaticPearl(Level level, LivingEntity livingEntity, ItemStack itemStack) {
		super(StellarityEntityTypes.PRISMATIC_PEARL, livingEntity, level, itemStack);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder entityData) {
		super.defineSynchedData(entityData);
		entityData.define(DATA_TRAIL, Trail.NORMAL);
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.storeNullable("trail", Trail.CODEC, getTrail());
	}

	@Override
	protected void readAdditionalSaveData(ValueInput input) {
		super.readAdditionalSaveData(input);
		input.read("trail", Trail.CODEC).ifPresent(this::setTrail);
	}

	@Override
	public void shootFromRotation(@NonNull Entity entity, float f, float g, float h, float i, float j) {
		super.shootFromRotation(entity, f, g, h, i, j);

		if (!level().isClientSide() && entity instanceof Player player) {

			String name = player.getGameProfile().name();
			if (name.equalsIgnoreCase("bush_moss")) setTrail(Trail.BISEXUAL);
			else if (name.equalsIgnoreCase("coder2195")) setTrail(Trail.TRANSGENDER);
		}
	}

	public enum Trail {
		NORMAL(0, new int[]{
			0xfa80fc,
			0xfa9ce0,
			0xfab5c7,
			0xfac5b7,
			0xfad9a3,
			0xcb6448,
			0x7a07f6,
			0x77e1a3,
			0x77b8cc,
			0x935118,
			0xbbff41,
			0xcae150,
			0xfa8150,
			0xfa811d
		}),
		BISEXUAL(1, new int[]{
			0xD60270,
			0xD60270,
			0xD60270,
			0xD60270,
			0x6348a3,
			0x6348a3,
			0x6348a3,
			0x6348a3,
			0x004be0,
			0x004be0,
			0x004be0,
			0x004be0
		}),
		TRANSGENDER(2, new int[]{
			0x5BCEFA,
			0x5BCEFA,
			0x5BCEFA,
			0x5BCEFA,
			0xF5A9B8,
			0xF5A9B8,
			0xF5A9B8,
			0xF5A9B8,
			0xFFFFFF,
			0xFFFFFF,
			0xFFFFFF,
			0xFFFFFF
		});

		public final int id;
		public final int[] colors;

		Trail(int id, int[] colors) {
			this.id = id;
			this.colors = colors;
		}

		public int id() {
			return id;
		}

		public static final IntFunction<Trail> BY_ID = ByIdMap.continuous(Trail::id, values(), ByIdMap.OutOfBoundsStrategy.WRAP);

		public static final StreamCodec<ByteBuf, Trail> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, Trail::id);

		public static final Codec<Trail> CODEC = CustomCodecs.enumName(Trail.class, NORMAL);
	}

	private int colorIndex = 0;

	public Trail getTrail() {
		return entityData.get(DATA_TRAIL);
	}

	public void setTrail(Trail trail) {
		entityData.set(DATA_TRAIL, trail);
	}

	@Override
	public void tick() {

		var level = level();

		if (!level.isClientSide()) {
			super.tick();
			return;
		}

		int[] colors = getTrail().colors;

		var position = position();
		var x = position.x;
		var y = position.y;
		var z = position.z;

		if (oldPos == null) {
			oldPos = new Vec3(x, y, z);
		}

		super.tick();

		var dx = x - oldPos.x;
		var dy = y - oldPos.y;
		var dz = z - oldPos.z;

		long steps = (int) (Math.max(Math.max(Math.abs(dx), Math.abs(dy)), Math.abs(dz)) / 0.1);

		var xStep = dx / steps;
		var yStep = dy / steps;
		var zStep = dz / steps;

		if (colorIndex >= colors.length || colorIndex < 0) {
			colorIndex = 0;
		}

		for (int i = 0; i <= steps + 1; i++) {
			var color = colors[colorIndex];
			level.addAlwaysVisibleParticle(new DustParticleOptions(color, 1.5f), true, x + i * xStep, y + i * yStep, z + i * zStep, 0, 0, 0);
		}

		colorIndex++;

		oldPos = new Vec3(x, y, z);

	}

	public ThrownPrismaticPearl(Level level, LivingEntity livingEntity) {
		super(StellarityEntityTypes.PRISMATIC_PEARL, livingEntity, level, new ItemStack(StellarityItems.PRISMATIC_PEARL));
	}

	@Override
	protected @NonNull Item getDefaultItem() {
		return StellarityItems.PRISMATIC_PEARL;
	}

	private static final int[] LAND_COLORS = {
		0xfa80fc, 0xfa9ce0, 0xfab5c7, 0xfac5b7, 0xfad9a3, 0xcb6448, 0x7a07f6, 0x77e1a3, 0x77b8cc, 0x935118, 0xbbff41, 0xcae150, 0xfa8150, 0xfa811d
	};

	@Override
	protected void onHit(@NonNull HitResult hitResult) {
		super.onHit(hitResult);

		var level = level();
		var position = position();

		if (level instanceof ServerLevel serverLevel && !isRemoved()) {
			var owner = getOwner();
			if (owner != null) {
				owner.teleportTo(serverLevel, position.x, position.y, position.z, Set.of(), owner.getYHeadRot(), owner.getXRot(), true);
				level.playSound(null, owner.blockPosition(), SoundEvents.ENDER_EYE_DEATH, SoundSource.NEUTRAL);
			}


			this.discard();
		}

		for (int color : LAND_COLORS) {
			level.addParticle(new DustParticleOptions(color, 1.5f), position.x + random.nextDouble() * 2 - 1, position.y, position.z + random.nextDouble() * 2 - 1, 0, 0, 0);
		}
	}

	@Override
	public void syncPacketPositionCodec(double d, double e, double f) {
		super.syncPacketPositionCodec(d, e, f);
	}

	protected void onHitEntity(@NonNull EntityHitResult entityHitResult) {
		super.onHitEntity(entityHitResult);
		var level = level();
		if (level.isClientSide()) return;
		entityHitResult.getEntity().hurtServer((ServerLevel) level(), this.damageSources().thrown(this, this.getOwner()), 0.0F);
	}


}
