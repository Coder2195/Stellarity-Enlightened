package dev.coder2195.stellarity.client.registry;

import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.client.registry.model.entity.PixieModel;
import dev.coder2195.stellarity.client.registry.model.entity.SatchelSigilModel;

@SuppressWarnings("ConfusingMainMethod")
public interface StellarityEntityModelLayers {
	ModelLayerLocation SATCHEL_SIGIL = main("satchel_sigil");
	ModelLayerLocation PIXIE = main("pixie");

	static ModelLayerLocation main(String id) {
		return new ModelLayerLocation(Stellarity.id(id), "main");
	}

	static void init() {
		ModelLayerRegistry.registerModelLayer(SATCHEL_SIGIL, SatchelSigilModel::getTexturedModelData);
		ModelLayerRegistry.registerModelLayer(PIXIE, PixieModel::getTexturedModelData);
	}
}
