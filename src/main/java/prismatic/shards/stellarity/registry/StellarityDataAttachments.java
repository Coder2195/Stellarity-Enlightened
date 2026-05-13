package prismatic.shards.stellarity.registry;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.GameType;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.StellarityConfig;
import prismatic.shards.stellarity.interface_injection.ExtEndCrystal;
import prismatic.shards.stellarity.interface_injection.ExtItemEntity;
import prismatic.shards.stellarity.registry.entity.ThrownPrismaticPearl;
import prismatic.shards.stellarity.registry.entity.variant.VoidedSkeletonVariant;

import java.util.List;
import java.util.Map;

public interface StellarityDataAttachments {
	AttachmentType<Integer> GLOW_COLOR = AttachmentRegistry.create(Stellarity.id("glow_color"), builder -> builder.
		syncWith(ByteBufCodecs.VAR_INT, AttachmentSyncPredicate.all()).persistent(Codec.INT)
	);

	AttachmentType<Map<Identifier, Integer>> ENCHANTMENTS = AttachmentRegistry.create(Stellarity.id("enchantments"), builder -> builder.persistent(Codec.unboundedMap(Identifier.CODEC, Codec.INT))
	);

	AttachmentType<ExtEndCrystal.Type> END_CRYSTAL_TYPE = AttachmentRegistry.create(Stellarity.id("end_crystal_type"), builder -> builder.persistent(ExtEndCrystal.Type.CODEC).syncWith(ExtEndCrystal.Type.STREAM_CODEC, AttachmentSyncPredicate.all())
	);

	AttachmentType<Boolean> VOID_FISHING_BUFF = AttachmentRegistry.create(Stellarity.id("buff_void_fishing"), builder -> builder.persistent(Codec.BOOL).syncWith(ByteBufCodecs.BOOL, AttachmentSyncPredicate.all())
	);

	AttachmentType<ExtItemEntity.ItemMode> ITEM_MODE = AttachmentRegistry.create(Stellarity.id("item_mode"), builder -> builder.persistent(ExtItemEntity.ItemMode.CODEC).syncWith(ExtItemEntity.ItemMode.STREAM_CODEC, AttachmentSyncPredicate.all())
	);

	AttachmentType<ThrownPrismaticPearl.Trail> PRISMATIC_PEARL_TRAIL = AttachmentRegistry.create(Stellarity.id("prismatic_pearl_trail"), builder -> builder.persistent(ThrownPrismaticPearl.Trail.CODEC).syncWith(ThrownPrismaticPearl.Trail.STREAM_CODEC, AttachmentSyncPredicate.all())
	);

	AttachmentType<Boolean> EXIT_PORTAL_CHEST = AttachmentRegistry.create(Stellarity.id("exit_portal_chest"), builder -> builder.persistent(Codec.BOOL).syncWith(ByteBufCodecs.BOOL, AttachmentSyncPredicate.all())
	);

	AttachmentType<StellarityConfig> CONFIG = AttachmentRegistry.create(Stellarity.id("config"), builder -> builder.persistent(StellarityConfig.CODEC).syncWith(StellarityConfig.STREAM_CODEC, AttachmentSyncPredicate.all()).initializer(() -> StellarityConfig.DEFAULT)
	);

	AttachmentType<GameType> LAST_GAMEMODE = AttachmentRegistry.create(Stellarity.id("last_gamemode"), builder -> builder.persistent(GameType.CODEC).syncWith(GameType.STREAM_CODEC, AttachmentSyncPredicate.all()));

	AttachmentType<Holder<VoidedSkeletonVariant>> VOIDED_SKELETON_VARIANT = AttachmentRegistry.create(Stellarity.id("voided_skeleton_variant"), builder -> builder.persistent(VoidedSkeletonVariant.REFERENCE_CODEC).syncWith(VoidedSkeletonVariant.REFERENCE_STREAM_CODEC, AttachmentSyncPredicate.all())
	);

	AttachmentType<List<BlockPos>> CRYSTAL_PLACEMENTS = AttachmentRegistry.create(Stellarity.id("exit_portal_location"), builder -> builder.persistent(BlockPos.CODEC.listOf()).syncWith(ByteBufCodecs.<ByteBuf, BlockPos>list().apply(BlockPos.STREAM_CODEC), AttachmentSyncPredicate.all()));

	AttachmentType<Boolean> HARVESTER_MINIBOSS = AttachmentRegistry.create(Stellarity.id("harvester_miniboss"), builder -> builder.persistent(Codec.BOOL).syncWith(ByteBufCodecs.BOOL, AttachmentSyncPredicate.all()));


	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Data Attachments");

	}
}
