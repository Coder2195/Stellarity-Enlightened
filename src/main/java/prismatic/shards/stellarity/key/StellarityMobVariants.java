package prismatic.shards.stellarity.key;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.animal.chicken.ChickenVariant;
import net.minecraft.world.entity.animal.cow.CowVariant;
import net.minecraft.world.entity.animal.feline.CatVariant;
import net.minecraft.world.entity.animal.frog.FrogVariant;
import net.minecraft.world.entity.animal.pig.PigVariant;
import net.minecraft.world.entity.animal.wolf.WolfVariant;
import prismatic.shards.stellarity.registry.entity.variant.VoidedSkeletonVariant;

import static net.minecraft.core.registries.Registries.*;
import static prismatic.shards.stellarity.Stellarity.key;
import static prismatic.shards.stellarity.registry.StellarityRegistries.VOIDED_SKELETON_VARIANT;

public interface StellarityMobVariants {
  ResourceKey<ChickenVariant> CHICKEN_END = key(CHICKEN_VARIANT, "end");
  ResourceKey<PigVariant> PIG_END = key(PIG_VARIANT, "end");
  ResourceKey<CowVariant> COW_END = key(COW_VARIANT, "end");
  ResourceKey<WolfVariant> ENDER_WOLF = key(WOLF_VARIANT, "end");
  ResourceKey<FrogVariant> ENDER_FROG = key(FROG_VARIANT, "end");
  ResourceKey<CatVariant> ENDER_CAT = key(CAT_VARIANT, "end");

  ResourceKey<VoidedSkeletonVariant> NORMAL_VOIDED_SKELETON = voidedSkeleton("normal");
  ResourceKey<VoidedSkeletonVariant> FLESH_VOIDED_SKELETON = voidedSkeleton("flesh");
  ResourceKey<VoidedSkeletonVariant> COLD_VOIDED_SKELETON = voidedSkeleton("cold");
  ResourceKey<VoidedSkeletonVariant> ASH_VOIDED_SKELETON = voidedSkeleton("ash");

  static ResourceKey<VoidedSkeletonVariant> voidedSkeleton(String path) {
    return key(VOIDED_SKELETON_VARIANT, path);
  }

}
