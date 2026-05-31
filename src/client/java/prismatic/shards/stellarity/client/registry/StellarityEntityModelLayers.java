package prismatic.shards.stellarity.client.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.client.registry.model.entity.SatchelSigilModel;

@Environment(EnvType.CLIENT)
public interface StellarityEntityModelLayers {
	ModelLayerLocation SATCHEL_SIGIL = main("satchel_sigil");

	static ModelLayerLocation main(String id) {
		return new ModelLayerLocation(Stellarity.id(id), "main");
	}

	static void init() {
		ModelLayerRegistry.registerModelLayer(SATCHEL_SIGIL, SatchelSigilModel::getTexturedModelData);
	}
}
