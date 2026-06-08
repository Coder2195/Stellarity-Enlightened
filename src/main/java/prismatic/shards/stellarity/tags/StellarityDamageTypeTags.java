package prismatic.shards.stellarity.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityDamageTypeTags {
	TagKey<DamageType> MELEE = id("melee");
	TagKey<DamageType> RANGED = id("ranged");
	TagKey<DamageType> TRIGGERS_FLESH_SPEED_BOOST = id("triggers_flesh_speed_boost");
	TagKey<DamageType> BLOOD_FOR_BLOOD = id("blood_for_blood");

	static TagKey<DamageType> id(String id) {
		return TagKey.create(Registries.DAMAGE_TYPE, Stellarity.id(id));
	}
}
