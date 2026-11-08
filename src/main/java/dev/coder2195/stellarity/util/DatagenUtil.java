package dev.coder2195.stellarity.util;

import dev.coder2195.stellarity.Stellarity;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunctions;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Optional;

import static dev.coder2195.stellarity.util.LootUtil.lootTable;

public interface DatagenUtil {
	FakeHolderGetter<LootTable> FAKE_LOOT_TABLES = new FakeHolderGetter<>(Registries.LOOT_TABLE, lootTable().build());
	FakeHolderGetter<DensityFunction> FAKE_DENSITY_FUNCTIONS = new FakeHolderGetter<>(Registries.DENSITY_FUNCTION, DensityFunctions.constant(0));

	class FakeHolderGetter<T> implements HolderGetter<T> {
		private final ResourceKey<? extends Registry<T>> registryKey;
		private final T instance;

		public FakeHolderGetter(ResourceKey<? extends Registry<T>> registryKey, T instance) {
			this.registryKey = registryKey;
			this.instance = instance;
		}

		@Override
		public Optional<Holder.Reference<T>> get(ResourceKey<T> id) {
			return Optional.of(new Holder.Reference<>(Holder.Reference.Type.STAND_ALONE, this, id, instance) {
				@Override
				public boolean canSerializeIn(HolderOwner<T> context) {
					return true;
				}
			});
		}

		@Override
		public boolean canSerialize(HolderOwner<T> owner) {
			return true;
		}

		@SuppressWarnings("deprecation")
		@Override
		public Optional<HolderSet.Named<T>> get(TagKey<T> id) {
			return Optional.of(HolderSet.emptyNamed(this, id));
		}

		public Optional<Holder.Reference<T>> get(String namespace, String path) {
			return get(Stellarity.key(registryKey, namespace, path));
		}

		public Holder.Reference<T> getOrThrow(String namespace, String path) {
			return getOrThrow(Stellarity.key(registryKey, namespace, path));
		}

	}
}
