package prismatic.shards.stellarity.registry.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.zombie.ZombifiedPiglin;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class FleshPiglin extends ZombifiedPiglin {
	public FleshPiglin(EntityType<? extends ZombifiedPiglin> type, Level level) {
		super(type, level);
	}

	public static AttributeSupplier.@NonNull Builder createAttributes() {
		return ZombifiedPiglin.createAttributes();
	}

	@Override
	public boolean isAngryAt(@NonNull LivingEntity entity, @NonNull ServerLevel level) {
		return canAttack(entity) && entity.is(EntityTypes.PLAYER) || super.isAngryAt(entity, level);
	}

	@Override
	public void populateDefaultEquipmentSlots(@NonNull RandomSource random, @NonNull DifficultyInstance difficulty) {
		// nothing lol
	}

	@Override
	public @Nullable SpawnGroupData finalizeSpawn(@NonNull ServerLevelAccessor level, @NonNull DifficultyInstance difficulty, @NonNull EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {
		super.finalizeSpawn(level, difficulty, spawnReason, groupData);


		var attack = getAttribute(Attributes.ATTACK_DAMAGE);
		if (attack != null) attack.setBaseValue(attack.getBaseValue() + 3);
		var armor = getAttribute(Attributes.ARMOR);
		if (armor != null) armor.setBaseValue(armor.getBaseValue() + 4);
		var knockbackRes = getAttribute(Attributes.KNOCKBACK_RESISTANCE);
		if (knockbackRes != null) knockbackRes.setBaseValue(knockbackRes.getBaseValue() + 0.15);
		var speed = getAttribute(Attributes.MOVEMENT_SPEED);
		if (speed != null) speed.setBaseValue(speed.getBaseValue() * 0.85);
		var follow = getAttribute(Attributes.FOLLOW_RANGE);
		if (follow != null) follow.setBaseValue(follow.getBaseValue() * 0.3);

		setDropChance(EquipmentSlot.MAINHAND, 1);
		setDropChance(EquipmentSlot.OFFHAND, 0);

		return groupData;
	}
}
