package dev.coder2195.stellarity.util;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Optional;

import static dev.coder2195.stellarity.util.LootUtil.lootTable;

public interface DatagenUtil {
	HolderGetter<LootTable> FAKE_LOOT_TABLES = fakeLootTable(lootTable().build());

	static <T> HolderGetter<T> fakeLootTable(T instance) {
		return new HolderGetter<>() {
			private final T INSTANCE = instance;

			@Override
			public Optional<Holder.Reference<T>> get(ResourceKey<T> id) {
				return Optional.of(new Holder.Reference<>(Holder.Reference.Type.STAND_ALONE, this, id, INSTANCE) {
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
		};
	}
}
