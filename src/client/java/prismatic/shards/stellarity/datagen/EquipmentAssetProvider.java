package prismatic.shards.stellarity.datagen;

import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.key.StellarityEquipmentAssets;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class EquipmentAssetProvider extends net.minecraft.client.data.models.EquipmentAssetProvider {
	private final PackOutput.PathProvider pathProvider;

	public EquipmentAssetProvider(final PackOutput output) {
		super(output);
		this.pathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
	}

	private static void bootstrap(final BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> consumer) {
		consumer.accept(StellarityEquipmentAssets.SHULKER, onlyHumanoid("shulker"));
	}

	private static EquipmentClientInfo onlyHumanoid(final String name) {
		return EquipmentClientInfo.builder().addHumanoidLayers(Stellarity.id(name)).build();
	}

	private static EquipmentClientInfo humanoidAndMountArmor(final String name) {
		return EquipmentClientInfo.builder()
			.addHumanoidLayers(Identifier.withDefaultNamespace(name))
			.addLayers(EquipmentClientInfo.LayerType.HORSE_BODY, EquipmentClientInfo.Layer.leatherDyeable(Identifier.withDefaultNamespace(name), false))
			.addLayers(EquipmentClientInfo.LayerType.NAUTILUS_BODY, EquipmentClientInfo.Layer.leatherDyeable(Identifier.withDefaultNamespace(name), false))
			.build();
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
