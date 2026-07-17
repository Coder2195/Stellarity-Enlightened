package dev.coder2195.stellarity.datagen;

import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import org.jspecify.annotations.NonNull;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.StellarityEquipmentAssets;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class EquipmentAssetProvider extends net.minecraft.client.data.models.EquipmentAssetProvider {
	private final PackOutput.PathProvider pathProvider;

	public EquipmentAssetProvider(final PackOutput output) {
		super(output);
		this.pathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
	}

	private static void bootstrap(final BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> consumer) {
		consumer.accept(StellarityEquipmentAssets.SHULKER, armor("shulker"));
		consumer.accept(StellarityEquipmentAssets.PHANTOM_WINGS, wings("phantom"));
		consumer.accept(StellarityEquipmentAssets.DRAGON_WINGS, wings("dragon"));
		consumer.accept(StellarityEquipmentAssets.EMPRESS_WINGS, wings("empress"));
		consumer.accept(StellarityEquipmentAssets.FLORAL, armor("floral"));
		consumer.accept(StellarityEquipmentAssets.CHAMPION, armor("champion"));
		consumer.accept(StellarityEquipmentAssets.HALLOWED, armor("hallowed"));
		consumer.accept(StellarityEquipmentAssets.REINFORCED, horseArmor("reinforced"));
	}

	private static EquipmentClientInfo armor(String name) {
		return EquipmentClientInfo.builder().addHumanoidLayers(Stellarity.id(name)).build();
	}

	private static EquipmentClientInfo horseArmor(String name) {
		return EquipmentClientInfo.builder().addLayers(EquipmentClientInfo.LayerType.HORSE_BODY, new EquipmentClientInfo.Layer(Stellarity.id(name))).build();
	}


	private static EquipmentClientInfo wings(final String name) {
		return EquipmentClientInfo.builder().addLayers(EquipmentClientInfo.LayerType.WINGS, new EquipmentClientInfo.Layer[]{new EquipmentClientInfo.Layer(Stellarity.id(name), Optional.empty(), false)}).build();
	}


	@Override
	public @NonNull CompletableFuture<?> run(final @NonNull CachedOutput cache) {
		Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> equipmentAssets = new HashMap<>();
		bootstrap((id, asset) -> {
			if (equipmentAssets.putIfAbsent(id, asset) != null) {
				throw new IllegalStateException("Tried to register equipment asset twice for id: " + id);
			}
		});
		return DataProvider.saveAll(cache, EquipmentClientInfo.CODEC, this.pathProvider::json, equipmentAssets);
	}
}
