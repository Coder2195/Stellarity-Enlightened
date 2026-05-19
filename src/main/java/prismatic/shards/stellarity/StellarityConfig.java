package prismatic.shards.stellarity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.attachment.v1.GlobalAttachmentsProvider;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.MinecraftServer;
import prismatic.shards.stellarity.registry.StellarityDataAttachments;

public record StellarityConfig(
	boolean joinMessage, boolean enableEndCrystalDrop, boolean enableTotemVoidSaving, boolean alwaysGenerateEgg,
	int dragonHealth, int empressOfLightHealth, int shulkingHealth
) {

	public static final StellarityConfig DEFAULT = new StellarityConfig(true, false, true, false, 500, 750, 900);

	public static Codec<StellarityConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
		Codec.BOOL.optionalFieldOf("join_message", true).forGetter(StellarityConfig::joinMessage),
		Codec.BOOL.optionalFieldOf("enable_end_crystal_drop", true).forGetter(StellarityConfig::enableEndCrystalDrop),
		Codec.BOOL.optionalFieldOf("enable_totem_void_saving", true).forGetter(StellarityConfig::enableTotemVoidSaving),
		Codec.BOOL.optionalFieldOf("always_generate_egg", true).forGetter(StellarityConfig::alwaysGenerateEgg),
		Codec.INT.optionalFieldOf("dragon_health", 300).forGetter(StellarityConfig::dragonHealth),
		Codec.INT.optionalFieldOf("empress_of_light_health", 750).forGetter(StellarityConfig::empressOfLightHealth),
		Codec.INT.optionalFieldOf("shulking_health", 900).forGetter(StellarityConfig::shulkingHealth)
	).apply(instance, StellarityConfig::new));

	public static StreamCodec<RegistryFriendlyByteBuf, StellarityConfig> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.BOOL, StellarityConfig::joinMessage, ByteBufCodecs.BOOL, StellarityConfig::enableEndCrystalDrop, ByteBufCodecs.BOOL, StellarityConfig::enableTotemVoidSaving, ByteBufCodecs.BOOL, StellarityConfig::alwaysGenerateEgg, ByteBufCodecs.INT, StellarityConfig::dragonHealth, ByteBufCodecs.INT, StellarityConfig::empressOfLightHealth, ByteBufCodecs.INT, StellarityConfig::shulkingHealth, StellarityConfig::new);

	public static StellarityConfig get(GlobalAttachmentsProvider provider) {
		return provider.globalAttachments().getAttachedOrElse(StellarityDataAttachments.CONFIG, DEFAULT);
	}
}
