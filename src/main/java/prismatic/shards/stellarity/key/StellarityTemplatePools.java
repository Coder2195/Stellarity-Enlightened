package prismatic.shards.stellarity.key;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import prismatic.shards.stellarity.Stellarity;

public interface StellarityTemplatePools {
	ResourceKey<StructureTemplatePool> CAMPSITE = id("campsite");

	static ResourceKey<StructureTemplatePool> id(String id) {
		return Stellarity.key(Registries.TEMPLATE_POOL, id);
	}
}
