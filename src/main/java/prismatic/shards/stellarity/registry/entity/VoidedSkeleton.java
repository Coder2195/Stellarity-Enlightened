package prismatic.shards.stellarity.registry.entity;

import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryDataLoader;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.entity.variant.SpawnContext;
import net.minecraft.world.entity.variant.VariantUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import prismatic.shards.stellarity.registry.*;
import prismatic.shards.stellarity.registry.entity.variant.VoidedSkeletonVariant;

public class VoidedSkeleton extends Skeleton {
  public VoidedSkeleton(EntityType<? extends VoidedSkeleton> type, Level level) {
    super(type, level);
  }

  public VoidedSkeleton(Level level) {
    this(StellarityEntities.VOIDED_SKELETON, level);
  }


  @Override
  public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {
    groupData = super.finalizeSpawn(level, difficulty, spawnReason, groupData);

    setVariant(VariantUtils.selectVariantToSpawn(SpawnContext.create(level, this.blockPosition()), StellarityRegistries.VOIDED_SKELETON_VARIANT).orElseGet(() -> registryAccess().getOrThrow(VoidedSkeletonVariant.DEFAULT_VARIANT)));
    
    return groupData;
  }


  @Override
  protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
    super.populateDefaultEquipmentSlots(random, difficulty);
    this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(StellarityItems.CALL_OF_THE_VOID));
  }

  public static AttributeSupplier.@NonNull Builder createAttributes() {
    return Skeleton.createAttributes();
  }

  @Override
  public boolean doHurtTarget(@NonNull ServerLevel level, @NonNull Entity target) {
    if (!super.doHurtTarget(level, target)) return false;

    if (target instanceof LivingEntity entity) {
      entity.addEffect(new MobEffectInstance(StellarityMobEffects.VOIDED, 10 * 20));
    }

    return true;
  }

  public Holder<VoidedSkeletonVariant> getVariant() {
    return this.getAttached(StellarityDataAttachments.VOIDED_SKELETON_VARIANT);
  }

  public void setVariant(Holder<VoidedSkeletonVariant> variant) {
    this.setAttached(StellarityDataAttachments.VOIDED_SKELETON_VARIANT, variant);
  }

  public void setVariant(ResourceKey<VoidedSkeletonVariant> variant) {
    this.setVariant(this.level().registryAccess().getOrThrow(variant));
  }
}
