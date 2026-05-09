package prismatic.shards.stellarity.datagen.dynamic;

import net.minecraft.core.ClientAsset;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.animal.chicken.ChickenVariant;
import net.minecraft.world.entity.animal.cow.CowVariant;
import net.minecraft.world.entity.animal.feline.CatVariant;
import net.minecraft.world.entity.animal.frog.FrogVariant;
import net.minecraft.world.entity.animal.pig.PigVariant;
import net.minecraft.world.entity.animal.wolf.WolfVariant;
import net.minecraft.world.entity.variant.BiomeCheck;
import net.minecraft.world.entity.variant.ModelAndTexture;
import net.minecraft.world.entity.variant.SpawnPrioritySelectors;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.key.StellarityMobVariants;
import prismatic.shards.stellarity.registry.entity.variant.VoidedSkeletonVariant;
import prismatic.shards.stellarity.tags.StellarityBiomeTags;

public interface MobVariantProvider {
  static ClientAsset.ResourceTexture texture(String string) {
    return new ClientAsset.ResourceTexture(Stellarity.id("entity/" + string));
  }

  static <T> ModelAndTexture<T> texture(T model, String string) {
    return new ModelAndTexture<>(model, Stellarity.id("entity/" + string));
  }

  static void bootstrapChicken(BootstrapContext<ChickenVariant> context) {
    var biomes = context.lookup(Registries.BIOME);

    context.register(StellarityMobVariants.CHICKEN_END, new ChickenVariant(
      texture(ChickenVariant.ModelType.NORMAL, "chicken/ender_chicken"),
      texture("chicken/ender_chicken_baby"),
      SpawnPrioritySelectors.single(new BiomeCheck(biomes.getOrThrow(BiomeTags.IS_END)), 5)
    ));
  }

  static void bootstrapCow(BootstrapContext<CowVariant> context) {
    var biomes = context.lookup(Registries.BIOME);

    context.register(StellarityMobVariants.COW_END, new CowVariant(
      texture(CowVariant.ModelType.NORMAL, "cow/cow_end"),
      texture("cow/cow_end_baby"),
      SpawnPrioritySelectors.single(new BiomeCheck(biomes.getOrThrow(BiomeTags.IS_END)), 5)
    ));
  }

  static void bootstrapPig(BootstrapContext<PigVariant> context) {
    var biomes = context.lookup(Registries.BIOME);

    context.register(StellarityMobVariants.PIG_END, new PigVariant(
      texture(PigVariant.ModelType.NORMAL, "pig/pig_end"),
      texture("pig/pig_end"),
      SpawnPrioritySelectors.single(new BiomeCheck(biomes.getOrThrow(BiomeTags.IS_END)), 5)
    ));
  }

  static void bootstrapFrog(BootstrapContext<FrogVariant> context) {
    var biomes = context.lookup(Registries.BIOME);

    context.register(StellarityMobVariants.ENDER_FROG, new FrogVariant(
      texture("frog/frog_end"),
      SpawnPrioritySelectors.single(new BiomeCheck(biomes.getOrThrow(BiomeTags.IS_END)), 5)
    ));
  }

  static void bootstrapWolf(BootstrapContext<WolfVariant> context) {
    var biomes = context.lookup(Registries.BIOME);

    context.register(StellarityMobVariants.ENDER_WOLF, new WolfVariant(
      wolfTexture("wolf_end"),
      wolfBabyTexture("wolf_end"),
      SpawnPrioritySelectors.single(new BiomeCheck(biomes.getOrThrow(BiomeTags.IS_END)), 5)
    ));
  }

  static WolfVariant.AssetInfo wolfTexture(String name) {
    return new WolfVariant.AssetInfo(
      texture("wolf/" + name),
      texture("wolf/" + name + "_tame"),
      texture("wolf/" + name + "_angry")
    );
  }

  static WolfVariant.AssetInfo wolfBabyTexture(String name) {
    return new WolfVariant.AssetInfo(
      texture("wolf/" + name + "_baby"),
      texture("wolf/" + name + "_tame_baby"),
      texture("wolf/" + name + "_angry_baby")
    );
  }

  static void bootstrapCat(BootstrapContext<CatVariant> context) {
    var biomes = context.lookup(Registries.BIOME);

    context.register(StellarityMobVariants.ENDER_CAT, new CatVariant(
      texture("cat/cat_end"),
      texture("cat/cat_end_baby"),
      SpawnPrioritySelectors.single(new BiomeCheck(biomes.getOrThrow(BiomeTags.IS_END)), 5)
    ));
  }

  static void bootstrapVoidedSkeleton(BootstrapContext<VoidedSkeletonVariant> context) {
    var biomes = context.lookup(Registries.BIOME);

    context.register(StellarityMobVariants.NORMAL_VOIDED_SKELETON, new VoidedSkeletonVariant(texture("voided_skeleton/voided_skeleton_normal"), SpawnPrioritySelectors.fallback(0)));
    context.register(StellarityMobVariants.ASH_VOIDED_SKELETON, new VoidedSkeletonVariant(texture("voided_skeleton/voided_skeleton_ash"), SpawnPrioritySelectors.single(new BiomeCheck(biomes.getOrThrow(StellarityBiomeTags.SPAWNS_ASH_VOIDED_SKELETON)), 5)));
    context.register(StellarityMobVariants.COLD_VOIDED_SKELETON, new VoidedSkeletonVariant(texture("voided_skeleton/voided_skeleton_cold"), SpawnPrioritySelectors.single(new BiomeCheck(biomes.getOrThrow(StellarityBiomeTags.SPAWNS_COLD_VOIDED_SKELETON)), 5)));
    context.register(StellarityMobVariants.FLESH_VOIDED_SKELETON, new VoidedSkeletonVariant(texture("voided_skeleton/voided_skeleton_flesh"), SpawnPrioritySelectors.single(new BiomeCheck(biomes.getOrThrow(StellarityBiomeTags.SPAWNS_FLESH_VOIDED_SKELETON)), 5)));
  }


}
