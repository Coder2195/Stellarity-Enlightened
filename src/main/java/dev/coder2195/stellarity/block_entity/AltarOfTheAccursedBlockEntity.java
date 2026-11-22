package dev.coder2195.stellarity.block_entity;

import com.mojang.math.Transformation;
import com.mojang.serialization.Codec;
import dev.coder2195.stellarity.block.AltarOfTheAccursed;
import dev.coder2195.stellarity.entity.RemovableItemDisplay;
import dev.coder2195.stellarity.networking.ClientboundDragonbladeSpawnPayload;
import dev.coder2195.stellarity.recipe.AltarOfTheAccursedRecipe;
import dev.coder2195.stellarity.registry.StellarityBlockEntityTypes;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;
import dev.coder2195.stellarity.registry.StellarityItems;
import dev.coder2195.stellarity.util.NetworkingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.end.EnderDragonFight;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;


public class AltarOfTheAccursedBlockEntity extends BlockEntity {
	public AltarOfTheAccursedBlockEntity(BlockPos pos, BlockState state) {
		this(StellarityBlockEntityTypes.ALTAR_OF_THE_ACCURSED, pos, state);
	}

	private long ticksPassed = 0;
	private int dragonbladeSpawnTime = -1;

	public AltarOfTheAccursedBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
		super(blockEntityType, blockPos, blockState);
	}

	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		output.store("dragonblade_spawn_time", Codec.INT, dragonbladeSpawnTime);
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		input.read("dragonblade_spawn_time", Codec.INT).ifPresent(this::setDragonbladeSpawnTime);
	}

	public void setDragonbladeSpawnTime(int dragonbladeSpawnTime) {
		this.dragonbladeSpawnTime = dragonbladeSpawnTime;
	}

	public int getDragonbladeSpawnTime() {
		return dragonbladeSpawnTime;
	}

	public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T blockEntity) {
		if (blockEntity instanceof AltarOfTheAccursedBlockEntity entity) entity.tick(level, blockPos, blockState);
	}

	@SuppressWarnings("DuplicatedCode")
	public void tick(Level level, BlockPos blockPos, BlockState blockState) {
		boolean locked = blockState.getValue(AltarOfTheAccursed.LOCKED);
		var placeType = blockState.getValue(AltarOfTheAccursed.PLACE_TYPE);

		this.ticksPassed++;
		var centerPos = Vec3.atCenterOf(blockPos);
		double x = centerPos.x;
		double y = centerPos.y;
		double z = centerPos.z;

		var bottomCenterAbove = Vec3.atBottomCenterOf(blockPos.above());

		if (level.isClientSide()) {
			if (locked) return;

			float angle = this.ticksPassed / 20f;
			double dx = Mth.cos(angle);
			double dz = Mth.sin(angle);


			var purpleParticle = new DustColorTransitionOptions(0xbb00ff, 0x1b0025, 1.4f);

			level.addParticle(
				purpleParticle,
				x + dx, y, z + dz,
				0, 0, 0
			);


			level.addParticle(
				purpleParticle,
				x - dx, y, z - dz,
				0, 0, 0
			);


			dx = Mth.cos(-angle) * 1.5;
			dz = Mth.sin(-angle) * 1.5;


			level.addParticle(
				purpleParticle,
				x + dx, y, z + dz,
				0, 0, 0
			);

			level.addParticle(
				purpleParticle,
				x - dx, y, z - dz,
				0, 0, 0
			);

			if (this.ticksPassed % 3 == 0) {
				dx = level.getRandom().nextGaussian() * 0.5;
				dz = level.getRandom().nextGaussian() * 0.5;
				level.addParticle(
					ParticleTypes.ENCHANT,
					x + dx, y + 1.5, z + dz,
					dx * 2, -1.5, dz * 2
				);
			}

			level.addParticle(
				ParticleTypes.WITCH,
				x, y + 0.5, z,
				0, 0,
				0
			);

		} else if (level instanceof ServerLevel serverLevel) {



			if (!placeType.bypassesDragon()) {
				var end = serverLevel.getServer().getLevel(Level.END);
				EnderDragonFight dragonFight = end == null ? null : end.getDragonFight();


				boolean newLocked = dragonFight != null && !dragonFight.hasPreviouslyKilledDragon();
				if (locked != newLocked) {
					locked = newLocked;
					serverLevel.setBlockAndUpdate(blockPos, blockState.setValue(AltarOfTheAccursed.LOCKED, newLocked));
				}
			}

			if (this.ticksPassed % 10 == 0) {
				if (!serverLevel.globalAttachments().hasAttached(StellarityDataAttachments.DRAGONBLADE_EXISTANCE)) {
					serverLevel.globalAttachments().setAttached(StellarityDataAttachments.DRAGONBLADE_EXISTANCE, Unit.INSTANCE);
					dragonbladeSpawnTime = 200;
				}

				if (dragonbladeSpawnTime <= 0) AltarOfTheAccursedRecipe.handleItems(serverLevel, x, y, z, locked);
			}

			if (placeType == AltarOfTheAccursed.PlaceType.NORMAL) {
				if (dragonbladeSpawnTime >= 0 && dragonbladeSpawnTime-- == 0) {
					var itemDisplay = new RemovableItemDisplay(serverLevel, bottomCenterAbove.x, bottomCenterAbove.y, bottomCenterAbove.z, new ItemStack(StellarityItems.DRAGONBLADE), Short.MIN_VALUE);
					itemDisplay.setHeight(1);
					itemDisplay.setWidth(0.5f);
					itemDisplay.setTransformation(new Transformation(new Vector3f(0, 0.5f, -0.075f), new Quaternionf(1, 0, 0, 0), new Vector3f(1, 1, 1), new Quaternionf(0, 0, 0, 1)));
					itemDisplay.setItemTransform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND);
					level.addFreshEntity(itemDisplay);
					NetworkingUtil.sendAllPlayers(serverLevel, new ClientboundDragonbladeSpawnPayload(Vec3.atBottomCenterOf(blockPos.above())));
				}
			}



		}
	}




}
