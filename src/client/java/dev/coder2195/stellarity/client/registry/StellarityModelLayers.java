package dev.coder2195.stellarity.client.registry;

import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.client.model.entity.PixieModel;
import dev.coder2195.stellarity.client.model.entity.SatchelSigilModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.object.boat.BoatModel;

public interface StellarityModelLayers {
	LayerDefinition BOAT_MODEL = BoatModel.createBoatModel();
	LayerDefinition CHEST_BOAT_MODEL = BoatModel.createChestBoatModel();

	ModelLayerLocation SATCHEL_SIGIL = layer("satchel_sigil", SatchelSigilModel::getTexturedModelData);
	ModelLayerLocation PIXIE = layer("pixie",PixieModel::getTexturedModelData);

	ModelLayerLocation AMETHYII_BOAT = boat("boat/amethyii");
	ModelLayerLocation HALLOWED_BOAT = boat("boat/hallowed");
	ModelLayerLocation SHRUBBED_BOAT = boat("boat/shrubbed");
	ModelLayerLocation PRISMATIC_BOAT = boat("boat/prismatic");
	ModelLayerLocation ASHEN_BOAT = boat("boat/ashen");
	ModelLayerLocation INFERNO_BOAT = boat("boat/inferno");

	ModelLayerLocation AMETHYII_CHEST_BOAT = chestBoat("chest_boat/amethyii");
	ModelLayerLocation HALLOWED_CHEST_BOAT = chestBoat("chest_boat/hallowed");
	ModelLayerLocation SHRUBBED_CHEST_BOAT = chestBoat("chest_boat/shrubbed");
	ModelLayerLocation PRISMATIC_CHEST_BOAT = chestBoat("chest_boat/prismatic");
	ModelLayerLocation ASHEN_CHEST_BOAT = chestBoat("chest_boat/ashen");
	ModelLayerLocation INFERNO_CHEST_BOAT = chestBoat("chest_boat/inferno");


	static ModelLayerLocation layer(String id, ModelLayerRegistry.TexturedLayerDefinitionProvider provider) {
		var location = new ModelLayerLocation(Stellarity.id(id), "main");
		ModelLayerRegistry.registerModelLayer(location, provider);
		return location;
	}

	static ModelLayerLocation boat(String id) {
		return layer(id, () -> BOAT_MODEL);
	}

	static ModelLayerLocation chestBoat(String id) {
		return layer(id, () -> CHEST_BOAT_MODEL);
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Model Layers");
	}
}
