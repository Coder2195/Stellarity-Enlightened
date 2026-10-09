package dev.coder2195.stellarity.util;

import dev.coder2195.stellarity.mixin.accessor.HolderReferenceAccessor;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;

import java.util.List;
import java.util.Optional;

import static dev.coder2195.stellarity.util.LootUtil.sequence;

public interface DatagenUtil {

	HolderGetter<LootItemFunction> FAKE_ITEM_MODIFIERS = new HolderGetter<>() {
		private static final LootItemFunction INSTANCE = sequence(new LootItemFunction[0]).value();
		@Override
		public Optional<Holder.Reference<LootItemFunction>> get(ResourceKey<LootItemFunction> id) {
			return Optional.of(new Holder.Reference<>(Holder.Reference.Type.STAND_ALONE, this, id, INSTANCE) {
				@Override
				public boolean canSerializeIn(HolderOwner<LootItemFunction> context) {
					return true;
				}
			});
		}

		@Override
		public boolean canSerialize(HolderOwner<LootItemFunction> owner) {
			return true;
		}

		@SuppressWarnings("deprecation")
		@Override
		public Optional<HolderSet.Named<LootItemFunction>> get(TagKey<LootItemFunction> id) {
			return Optional.of(HolderSet.emptyNamed(this, id));
		}
	};
}
