package prismatic.shards.stellarity.registry;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.core.Holder;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.GameType;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.StellarityConfig;
import prismatic.shards.stellarity.interface_injection.ExtEndCrystal;
import prismatic.shards.stellarity.interface_injection.ExtItemEntity;

import java.util.List;
import java.util.Map;

public interface StellarityDataAttachments {
	AttachmentType<Integer> GLOW_COLOR = AttachmentRegistry.create(Stellarity.id("glow_color"), builder -> builder.
		syncWith(ByteBufCodecs.VAR_INT, AttachmentSyncPredicate.all()).persistent(Codec.INT)
	);

	AttachmentType<List<MobEffectInstance>> MOB_EFFECTS = AttachmentRegistry.create(Stellarity.id("mob_effects"), builder -> builder.persistent(MobEffectInstance.CODEC.listOf()));

	AttachmentType<ExtEndCrystal.Type> END_CRYSTAL_TYPE = AttachmentRegistry.create(Stellarity.id("end_crystal_type"), builder -> builder.persistent(ExtEndCrystal.Type.CODEC).syncWith(ExtEndCrystal.Type.STREAM_CODEC, AttachmentSyncPredicate.all())
	);

	AttachmentType<Boolean> BUFF_VOID_FISHING = AttachmentRegistry.create(Stellarity.id("buff_void_fishing"), builder -> builder.persistent(Codec.BOOL).syncWith(ByteBufCodecs.BOOL, AttachmentSyncPredicate.all())
	);

	AttachmentType<ExtItemEntity.ItemMode> ITEM_MODE = AttachmentRegistry.create(Stellarity.id("item_mode"), builder -> builder.persistent(ExtItemEntity.ItemMode.CODEC).syncWith(ExtItemEntity.ItemMode.STREAM_CODEC, AttachmentSyncPredicate.all())
	);

	AttachmentType<Boolean> EXIT_PORTAL_CHEST = AttachmentRegistry.create(Stellarity.id("exit_portal_chest"), builder -> builder.persistent(Codec.BOOL).syncWith(ByteBufCodecs.BOOL, AttachmentSyncPredicate.all())
	);

	AttachmentType<StellarityConfig> CONFIG = AttachmentRegistry.create(Stellarity.id("config"), builder -> builder.persistent(StellarityConfig.CODEC).syncWith(StellarityConfig.STREAM_CODEC, AttachmentSyncPredicate.all()).initializer(() -> StellarityConfig.DEFAULT)
	);

	AttachmentType<GameType> LAST_GAMEMODE = AttachmentRegistry.create(Stellarity.id("last_gamemode"), builder -> builder.persistent(GameType.CODEC).syncWith(GameType.STREAM_CODEC, AttachmentSyncPredicate.all()));

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Data Attachments");

	}
}
