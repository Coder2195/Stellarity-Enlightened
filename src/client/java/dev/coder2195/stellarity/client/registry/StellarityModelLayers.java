package dev.coder2195.stellarity.client.registry;

import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.client.model.entity.PixieModel;
import dev.coder2195.stellarity.client.model.entity.SatchelSigilModel;

@SuppressWarnings("ConfusingMainMethod")
public interface StellarityModelLayers {
	ModelLayerLocation SATCHEL_SIGIL = main("satchel_sigil");
	ModelLayerLocation PIXIE = main("pixie");

	ModelLayerLocation AMETHYII_BOAT = main("boat/amethyii");
	ModelLayerLocation HALLOWED_BOAT = main("boat/hallowed");
	ModelLayerLocation SHRUBBED_BOAT = main("boat/shrubbed");
	ModelLayerLocation PRISMATIC_BOAT = main("boat/prismatic");
	ModelLayerLocation ASHEN_BOAT = main("boat/ashen");
	ModelLayerLocation INFERNO_BOAT = main("boat/inferno");

	ModelLayerLocation AMETHYII_CHEST_BOAT = main("chest_boat/amethyii");
	ModelLayerLocation HALLOWED_CHEST_BOAT = main("chest_boat/hallowed");
	ModelLayerLocation SHRUBBED_CHEST_BOAT = main("chest_boat/shrubbed");
	ModelLayerLocation PRISMATIC_CHEST_BOAT = main("chest_boat/prismatic");
	ModelLayerLocation ASHEN_CHEST_BOAT = main("chest_boat/ashen");
	ModelLayerLocation INFERNO_CHEST_BOAT = main("chest_boat/inferno");


	static ModelLayerLocation main(String id) {
		return new ModelLayerLocation(Stellarity.id(id), "main");
	}

	static void init() {
		ModelLayerRegistry.registerModelLayer(SATCHEL_SIGIL, SatchelSigilModel::getTexturedModelData);
		ModelLayerRegistry.registerModelLayer(PIXIE, PixieModel::getTexturedModelData);
	}
}
