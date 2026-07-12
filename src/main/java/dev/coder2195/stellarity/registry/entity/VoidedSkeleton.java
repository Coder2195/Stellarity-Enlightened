package dev.coder2195.stellarity.registry.entity;

import com.mojang.serialization.Codec;
import dev.coder2195.stellarity.registry.StellarityEntityDataSerializers;
import dev.coder2195.stellarity.registry.StellarityItems;
import dev.coder2195.stellarity.registry.StellarityMobEffects;
import dev.coder2195.stellarity.registry.StellarityRegistries;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.entity.variant.SpawnContext;
import net.minecraft.world.entity.variant.VariantUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.equipment.trim.ArmorTrim;
import net.minecraft.world.item.equipment.trim.TrimMaterials;
import net.minecraft.world.item.equipment.trim.TrimPatterns;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.*;
import dev.coder2195.stellarity.registry.entity.variant.VoidedSkeletonVariant;
import dev.coder2195.stellarity.util.tuple.Tuple2;
import dev.coder2195.stellarity.util.tuple.Tuple3;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class VoidedSkeleton extends Skeleton {
	public VoidedSkeleton(EntityType<? extends VoidedSkeleton> type, Level level) {
		super(type, level);
	}

	private static final EntityDataAccessor<Boolean> DATA_MINIBOSS = SynchedEntityData.defineId(VoidedSkeleton.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Holder<VoidedSkeletonVariant>> DATA_VARIANT = SynchedEntityData.defineId(VoidedSkeleton.class, StellarityEntityDataSerializers.VOIDED_SKELETON_VARIANT);
	
	public boolean isMiniboss() {
		return this.entityData.get(DATA_MINIBOSS);
	}

	public void setMiniboss(boolean miniboss) {
		this.entityData.set(DATA_MINIBOSS, miniboss);
	}


	public Holder<VoidedSkeletonVariant> getVariant() {
		return this.entityData.get(DATA_VARIANT);
	}

	public void setVariant(Holder<VoidedSkeletonVariant> variant) {
		this.entityData.set(DATA_VARIANT, variant);
	}

	public void setVariant(ResourceKey<VoidedSkeletonVariant> variant) {
		setVariant(level().registryAccess().getOrThrow(variant));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NonNull Builder entityData) {
		super.defineSynchedData(entityData);
		entityData.define(DATA_MINIBOSS, false);
		entityData.define(DATA_VARIANT, VariantUtils.getDefaultOrAny(this.registryAccess(), VoidedSkeletonVariant.DEFAULT_VARIANT));
	}

	@Override
	protected void readAdditionalSaveData(@NonNull ValueInput input) {
		super.readAdditionalSaveData(input);
		input.read("miniboss", Codec.BOOL).ifPresent(this::setMiniboss);
		input.read("variant", VoidedSkeletonVariant.CODEC).ifPresent(this::setVariant);
	}

	@Override
	protected void addAdditionalSaveData(@NonNull ValueOutput output) {
		super.addAdditionalSaveData(output);

		VariantUtils.writeVariant(output, getVariant());
		output.putBoolean("miniboss", isMiniboss());
	}

	@Override
	public @Nullable SpawnGroupData finalizeSpawn(@NonNull ServerLevelAccessor level, @NonNull DifficultyInstance difficulty, @NonNull EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {
		groupData = super.finalizeSpawn(level, difficulty, spawnReason, groupData);

		setVariant(VariantUtils.selectVariantToSpawn(SpawnContext.create(level, blockPosition()), StellarityRegistries.VOIDED_SKELETON_VARIANT).orElse(null));

		var miniboss = random.nextDouble() <= 0.01;
		var registry = registryAccess();
		if (miniboss) {
			setMiniboss(true);
			// TODO: replace with actual harvester
			setItemSlot(EquipmentSlot.MAINHAND, EnchantmentHelper.enchantItem(random, new ItemStack(Items.NETHERITE_SWORD), 60, registry, Optional.empty()));

			var armor = Stream.of(new Tuple2<>(Items.DIAMOND_HELMET, TrimPatterns.FLOW), new Tuple2<>(Items.DIAMOND_CHESTPLATE, TrimPatterns.EYE)).map(piece -> {
				var item = new ItemStack(piece._1());
				item.set(DataComponents.TRIM, new ArmorTrim(registry.getOrThrow(TrimMaterials.AMETHYST), registry.getOrThrow(piece._2())));
				item.set(DataComponents.ENCHANTMENTS, EnchantmentHelper.updateEnchantments(item, enchants -> enchants.set(registry.getOrThrow(Enchantments.PROTECTION), 4)));
				return item;
			}).toArray(ItemStack[]::new);

			setItemSlot(EquipmentSlot.HEAD, armor[0]);
			setItemSlot(EquipmentSlot.CHEST, armor[1]);

			var id = Stellarity.id("harvester_miniboss");
			for (var modifier : List.of(
				new Tuple3<>(Attributes.ATTACK_DAMAGE, 0.25, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
				new Tuple3<>(Attributes.MAX_HEALTH, 26d, AttributeModifier.Operation.ADD_VALUE),
				new Tuple3<>(Attributes.ARMOR, 2d, AttributeModifier.Operation.ADD_VALUE),
				new Tuple3<>(Attributes.ARMOR_TOUGHNESS, 1d, AttributeModifier.Operation.ADD_VALUE),
				new Tuple3<>(Attributes.MOVEMENT_SPEED, -0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
				new Tuple3<>(Attributes.MOVEMENT_EFFICIENCY, 1d, AttributeModifier.Operation.ADD_VALUE),
				new Tuple3<>(Attributes.KNOCKBACK_RESISTANCE, 0.2, AttributeModifier.Operation.ADD_VALUE)
			)) {
				var attr = getAttribute(modifier._1());
				if (attr != null) attr.addOrReplacePermanentModifier(new AttributeModifier(id, modifier._2(), modifier._3()));
			}

			setHealth(getMaxHealth());

			setDropChance(EquipmentSlot.MAINHAND, 1f);
		}


		return groupData;
	}


	@Override
	public void populateDefaultEquipmentSlots(@NonNull RandomSource random, @NonNull DifficultyInstance difficulty) {
		setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(StellarityItems.CALL_OF_THE_VOID));
		setDropChance(EquipmentSlot.MAINHAND, 0.004f);
		setDropChance(EquipmentSlot.OFFHAND, 0);
	}

	public

	static AttributeSupplier.@NonNull Builder createAttributes() {
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


}
