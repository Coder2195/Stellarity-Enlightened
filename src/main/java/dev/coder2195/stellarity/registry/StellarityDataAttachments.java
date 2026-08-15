package dev.coder2195.stellarity.registry;

import com.mojang.serialization.Codec;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.StellarityConfig;
import dev.coder2195.stellarity.interface_injection.ExtEndCrystal;
import dev.coder2195.stellarity.interface_injection.ExtItemEntity;
import dev.coder2195.stellarity.util.FloralBloom;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.Unit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.GameType;

import java.util.List;

public interface StellarityDataAttachments {
	AttachmentType<Integer> GLOW_COLOR = AttachmentRegistry.create(Stellarity.id("glow_color"), builder -> builder.
		syncWith(ByteBufCodecs.VAR_INT, AttachmentSyncPredicate.all()).persistent(Codec.INT)
	);

	AttachmentType<List<MobEffectInstance>> MOB_EFFECTS = AttachmentRegistry.create(Stellarity.id("mob_effects"), builder -> builder.persistent(MobEffectInstance.CODEC.listOf()));

	AttachmentType<ExtEndCrystal.Type> END_CRYSTAL_TYPE = AttachmentRegistry.create(Stellarity.id("end_crystal_type"), builder -> builder.persistent(ExtEndCrystal.Type.CODEC).syncWith(ExtEndCrystal.Type.STREAM_CODEC, AttachmentSyncPredicate.all())
	);

	AttachmentType<Unit> BUFF_VOID_FISHING = AttachmentRegistry.create(Stellarity.id("buff_void_fishing"), builder -> builder.persistent(Unit.CODEC).syncWith(Unit.STREAM_CODEC, AttachmentSyncPredicate.all())
	);

	AttachmentType<ExtItemEntity.ItemMode> ITEM_MODE = AttachmentRegistry.create(Stellarity.id("item_mode"), builder -> builder.persistent(ExtItemEntity.ItemMode.CODEC).syncWith(ExtItemEntity.ItemMode.STREAM_CODEC, AttachmentSyncPredicate.all())
	);

	AttachmentType<Unit> EXIT_PORTAL_CHEST = AttachmentRegistry.create(Stellarity.id("exit_portal_chest"), builder -> builder.persistent(Unit.CODEC).syncWith(Unit.STREAM_CODEC, AttachmentSyncPredicate.all())
	);

	AttachmentType<StellarityConfig> CONFIG = AttachmentRegistry.create(Stellarity.id("config"), builder -> builder.persistent(StellarityConfig.CODEC).syncWith(StellarityConfig.STREAM_CODEC, AttachmentSyncPredicate.all()).initializer(() -> StellarityConfig.DEFAULT)
	);

	AttachmentType<GameType> LAST_GAMEMODE = AttachmentRegistry.create(Stellarity.id("last_gamemode"), builder -> builder.persistent(GameType.CODEC).syncWith(GameType.STREAM_CODEC, AttachmentSyncPredicate.all()));

	AttachmentType<Unit> SPECTRAL_FURY_CHARGED = AttachmentRegistry.create(Stellarity.id("spectral_fury_charged"), builder -> builder.persistent(Unit.CODEC));

	AttachmentType<Long> RETURN_SPELL_AT = AttachmentRegistry.create(Stellarity.id("return_spell_at"), builder -> builder.persistent(Codec.LONG).syncWith(ByteBufCodecs.VAR_LONG, AttachmentSyncPredicate.all())
	);

	AttachmentType<Long> UPDRAFT_LEVITATION_UNTIL = AttachmentRegistry.create(Stellarity.id("updraft_levitation_until"), builder -> builder.persistent(Codec.LONG).syncWith(ByteBufCodecs.VAR_LONG, AttachmentSyncPredicate.all())
	);
	AttachmentType<Long> UPDRAFT_GLIDING_UNTIL = AttachmentRegistry.create(Stellarity.id("updraft_gliding_until"), builder -> builder.persistent(Codec.LONG).syncWith(ByteBufCodecs.VAR_LONG, AttachmentSyncPredicate.all())
	);
	AttachmentType<Unit> UPDRAFT_SLOW_FALLING = AttachmentRegistry.create(Stellarity.id("updraft_slow_falling"), builder -> builder.persistent(Unit.CODEC).syncWith(Unit.STREAM_CODEC, AttachmentSyncPredicate.all())
	);

	AttachmentType<Long> CHAMPION_BOOST_UNTIL = AttachmentRegistry.create(Stellarity.id("champion_boost_until"), builder -> builder.persistent(Codec.LONG).syncWith(ByteBufCodecs.VAR_LONG, AttachmentSyncPredicate.all()));

	AttachmentType<Long> HOLY_PROTECTION_DODGED_AT = AttachmentRegistry.create(Stellarity.id("holy_protection_dodged_at"), builder -> builder.persistent(Codec.LONG).syncWith(ByteBufCodecs.VAR_LONG, AttachmentSyncPredicate.all()));

	AttachmentType<Double> ARROW_DAMAGE_MULTIPLIER = AttachmentRegistry.create(Stellarity.id("arrow_damage_multiplier"), builder -> builder.persistent(Codec.DOUBLE));
	AttachmentType<FloralBloom> FLORAL_BLOOM = AttachmentRegistry.create(Stellarity.id("floral_bloom"), builder -> builder.persistent(FloralBloom.CODEC).syncWith(FloralBloom.STREAM_CODEC, AttachmentSyncPredicate.all()));
	AttachmentType<FloralBloom.Applier> FLORAL_BLOOM_APPLIER = AttachmentRegistry.create(Stellarity.id("floral_bloom_applier"), builder -> builder.persistent(FloralBloom.Applier.CODEC));

	AttachmentType<Long> LIFE_CRYSTAL_HELD_AT = AttachmentRegistry.create(Stellarity.id("life_crystal_held_at"), builder -> builder.persistent(Codec.LONG));



	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Data Attachments");

	}
}
