package dev.coder2195.stellarity.datagen.loot_table;

import com.mojang.serialization.Lifecycle;
import dev.coder2195.stellarity.mixin.accessor.HolderReferenceMixin;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;

public class CachedLootTableLookupProvider implements HolderLookup.Provider {
	private HolderLookup.@Nullable RegistryLookup<LootTable> lootTable = null;
	private final HolderLookup.Provider parentProvider;
	public final HashMap<ResourceKey<LootTable>, LootTable> lootTableCache = new HashMap<>();

	public CachedLootTableLookupProvider(HolderLookup.Provider parentProvider) {
		this.parentProvider = parentProvider;
	}

	@Override
	public Stream<ResourceKey<? extends Registry<?>>> listRegistryKeys() {
		return Stream.empty();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Optional<? extends HolderLookup.RegistryLookup<T>> lookup(ResourceKey<? extends Registry<? extends T>> key) {
		if (!key.equals(Registries.LOOT_TABLE)) return parentProvider.lookup(key);
		if (lootTable == null) lootTable = new HolderLookup.RegistryLookup<>() {
			private final RegistryLookup<LootTable> parent = parentProvider.lookupOrThrow(Registries.LOOT_TABLE);

			@Override
			public ResourceKey<? extends Registry<? extends LootTable>> key() {
				return parent.key();
			}

			@Override
			public Lifecycle registryLifecycle() {
				return parent.registryLifecycle();
			}

			@Override
			public Stream<Holder.Reference<LootTable>> listElements() {
				return Stream.concat(
					parent.listElements(),
					lootTableCache.entrySet().stream().map(entrySet -> HolderReferenceMixin.create(Holder.Reference.Type.STAND_ALONE, this, entrySet.getKey(), entrySet.getValue()))
				);
			}

			@Override
			public Stream<HolderSet.Named<LootTable>> listTags() {
				return parent.listTags();
			}


			@Override
			public Optional<Holder.Reference<LootTable>> get(ResourceKey<LootTable> id) {
				var parentHolder = parent.get(id);
				var cacheValue = lootTableCache.get(id);
				if (cacheValue != null) return Optional.of(HolderReferenceMixin.create(Holder.Reference.Type.STAND_ALONE, this, id, cacheValue));
				return parentHolder;
			}

			@Override
			public Optional<HolderSet.Named<LootTable>> get(TagKey<LootTable> id) {
				return parent.get(id);
			}
		};
		return Optional.of((HolderLookup.RegistryLookup<T>) lootTable);
	}
}
