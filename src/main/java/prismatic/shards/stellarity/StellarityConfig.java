package prismatic.shards.stellarity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.attachment.v1.GlobalAttachmentsProvider;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import prismatic.shards.stellarity.registry.StellarityDataAttachments;
import prismatic.shards.stellarity.util.tuple.Tuple2;

import java.util.List;
import java.util.function.Function;

public record StellarityConfig(
	boolean joinMessage, boolean alwaysGenerateEgg, boolean enableEndCrystalDrop, boolean enableTotemVoidSaving,
	boolean allowDisenchanting,
	boolean enableCreativeShock, boolean nerfElytra, boolean bossStatusMessages, int dragonHealth,
	int empressOfLightHealth, int shulkingHealth
) {

	public static final StellarityConfig DEFAULT = new StellarityConfig(
		true, false, false, true, true, true, false, true, 500, 750, 900
	);

	public static final List<Tuple2<String, Function<StellarityConfig, Boolean>>> BOOLEAN_INPUTS = List.of(
		new Tuple2<>("join_message", StellarityConfig::joinMessage),
		new Tuple2<>("always_generate_egg", StellarityConfig::alwaysGenerateEgg),
		new Tuple2<>("enable_end_crystal_drop", StellarityConfig::enableEndCrystalDrop),
		new Tuple2<>("enable_totem_void_saving", StellarityConfig::enableTotemVoidSaving),
		new Tuple2<>("allow_disenchanting", StellarityConfig::allowDisenchanting),
		new Tuple2<>("enable_creative_shock", StellarityConfig::enableCreativeShock),
		new Tuple2<>("nerf_elytra", StellarityConfig::nerfElytra),
		new Tuple2<>("boss_status_messages", StellarityConfig::bossStatusMessages)
	);

	public static final List<Tuple2<String, Function<StellarityConfig, Integer>>> HEALTH_INPUTS = List.of(
		new Tuple2<>("dragon_health", StellarityConfig::dragonHealth),
		new Tuple2<>("empress_of_light_health", StellarityConfig::empressOfLightHealth),
		new Tuple2<>("shulking_health", StellarityConfig::shulkingHealth)
	);

	public static Codec<StellarityConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
		Codec.BOOL.optionalFieldOf("join_message", DEFAULT.joinMessage).forGetter(StellarityConfig::joinMessage),
		Codec.BOOL.optionalFieldOf("always_generate_egg", DEFAULT.alwaysGenerateEgg).forGetter(StellarityConfig::alwaysGenerateEgg),
		Codec.BOOL.optionalFieldOf("enable_end_crystal_drop", DEFAULT.enableEndCrystalDrop).forGetter(StellarityConfig::enableEndCrystalDrop),
		Codec.BOOL.optionalFieldOf("enable_totem_void_saving", DEFAULT.enableTotemVoidSaving).forGetter(StellarityConfig::enableTotemVoidSaving),
		Codec.BOOL.optionalFieldOf("allow_disenchanting", DEFAULT.allowDisenchanting).forGetter(StellarityConfig::allowDisenchanting),
		Codec.BOOL.optionalFieldOf("enable_creative_shock", DEFAULT.enableCreativeShock).forGetter(StellarityConfig::enableCreativeShock),
		Codec.BOOL.optionalFieldOf("nerf_elytra", DEFAULT.nerfElytra).forGetter(StellarityConfig::nerfElytra),
		Codec.BOOL.optionalFieldOf("boss_status_messages", DEFAULT.bossStatusMessages).forGetter(StellarityConfig::bossStatusMessages),
		Codec.INT.optionalFieldOf("dragon_health", DEFAULT.dragonHealth).forGetter(StellarityConfig::dragonHealth),
		Codec.INT.optionalFieldOf("empress_of_light_health", DEFAULT.empressOfLightHealth).forGetter(StellarityConfig::empressOfLightHealth),
		Codec.INT.optionalFieldOf("shulking_health", DEFAULT.shulkingHealth).forGetter(StellarityConfig::shulkingHealth)
	).apply(instance, StellarityConfig::new));

	public static StreamCodec<RegistryFriendlyByteBuf, StellarityConfig> STREAM_CODEC = StreamCodec.composite(
		ByteBufCodecs.BOOL, StellarityConfig::joinMessage,
		ByteBufCodecs.BOOL, StellarityConfig::alwaysGenerateEgg,
		ByteBufCodecs.BOOL, StellarityConfig::enableEndCrystalDrop,
		ByteBufCodecs.BOOL, StellarityConfig::enableTotemVoidSaving,
		ByteBufCodecs.BOOL, StellarityConfig::allowDisenchanting,
		ByteBufCodecs.BOOL, StellarityConfig::enableCreativeShock,
		ByteBufCodecs.BOOL, StellarityConfig::nerfElytra,
		ByteBufCodecs.BOOL, StellarityConfig::bossStatusMessages,
		ByteBufCodecs.INT, StellarityConfig::dragonHealth,
		ByteBufCodecs.INT, StellarityConfig::empressOfLightHealth,
		ByteBufCodecs.INT, StellarityConfig::shulkingHealth,
		StellarityConfig::new
	);

	public static StellarityConfig get(GlobalAttachmentsProvider provider) {
		return provider.globalAttachments().getAttachedOrElse(StellarityDataAttachments.CONFIG, DEFAULT);
	}

	public void set(GlobalAttachmentsProvider provider) {
		provider.globalAttachments().setAttached(StellarityDataAttachments.CONFIG, this);
	}
}
