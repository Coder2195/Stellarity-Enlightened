package prismatic.shards.stellarity.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityVillagerProfessionTags {
	TagKey<VillagerProfession> END_PROFESSION = id("end_profession");
	TagKey<VillagerProfession> ALL = id("all");

	static TagKey<VillagerProfession> id(String id) {
		return TagKey.create(Registries.VILLAGER_PROFESSION, Stellarity.id(id));
	}
}
