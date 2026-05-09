package prismatic.shards.stellarity.registry.entity.variant;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.ClientAsset;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.variant.PriorityProvider;
import net.minecraft.world.entity.variant.SpawnCondition;
import net.minecraft.world.entity.variant.SpawnContext;
import net.minecraft.world.entity.variant.SpawnPrioritySelectors;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.key.StellarityMobVariants;
import prismatic.shards.stellarity.registry.StellarityRegistries;

import java.util.List;

public record VoidedSkeletonVariant(ClientAsset.ResourceTexture assetInfo,
                                    SpawnPrioritySelectors spawnConditions) implements PriorityProvider<SpawnContext, SpawnCondition> {
  public VoidedSkeletonVariant(ClientAsset.ResourceTexture texture) {
    this(texture, SpawnPrioritySelectors.EMPTY);
  }

  public static final Codec<VoidedSkeletonVariant> DIRECT_CODEC = RecordCodecBuilder.create((i) -> i.group(ClientAsset.ResourceTexture.DEFAULT_FIELD_CODEC.forGetter(VoidedSkeletonVariant::assetInfo), SpawnPrioritySelectors.CODEC.fieldOf("spawn_conditions").forGetter(VoidedSkeletonVariant::spawnConditions)).apply(i, VoidedSkeletonVariant::new));

  public static final Codec<VoidedSkeletonVariant> NETWORK_CODEC = RecordCodecBuilder.create((i) -> i.group(ClientAsset.ResourceTexture.DEFAULT_FIELD_CODEC.forGetter(VoidedSkeletonVariant::assetInfo)).apply(i, VoidedSkeletonVariant::new));

  public static final ResourceKey<VoidedSkeletonVariant> DEFAULT_VARIANT = StellarityMobVariants.NORMAL_VOIDED_SKELETON;

  public static final Codec<Holder<VoidedSkeletonVariant>> REFERENCE_CODEC = RegistryFixedCodec.create(StellarityRegistries.VOIDED_SKELETON_VARIANT);
  public static final StreamCodec<RegistryFriendlyByteBuf, Holder<VoidedSkeletonVariant>> REFERENCE_STREAM_CODEC = ByteBufCodecs.holderRegistry(StellarityRegistries.VOIDED_SKELETON_VARIANT);

  @Override
  public @NonNull List<PriorityProvider.Selector<SpawnContext, SpawnCondition>> selectors() {
    return this.spawnConditions.selectors();
  }
}
