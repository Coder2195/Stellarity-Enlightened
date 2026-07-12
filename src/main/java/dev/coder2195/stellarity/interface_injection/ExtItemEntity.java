package dev.coder2195.stellarity.interface_injection;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.world.item.ItemStack;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;
import dev.coder2195.stellarity.util.CustomCodecs;

import java.util.HashMap;
import java.util.function.IntFunction;

@SuppressWarnings("NonExtendableApiUsage")
public interface ExtItemEntity extends AttachmentTarget {
	enum ItemMode {
		DEFAULT(0),
		CRAFTING(1),
		RESULT(2);

		private final int id;

		ItemMode(int id) {
			this.id = id;
		}

		public int id() {
			return id;
		}

		public static final IntFunction<ItemMode> BY_ID =
			ByIdMap.continuous(
				ItemMode::id,
				ItemMode.values(),
				ByIdMap.OutOfBoundsStrategy.ZERO
			);

		public static final StreamCodec<ByteBuf, ItemMode> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, ItemMode::id);
		public static final Codec<ItemMode> CODEC = CustomCodecs.enumName(ItemMode.class, DEFAULT);
	}

	default ItemMode stellarity$getItemMode() {
		return this.getAttachedOrElse(StellarityDataAttachments.ITEM_MODE, ItemMode.DEFAULT);
	}

	default void stellarity$setItemMode(ItemMode mode) {
		this.setAttached(StellarityDataAttachments.ITEM_MODE, mode);
	}

	default void stellarity$updateResults(HashMap<ItemStack, Integer> results) {
		throw new AssertionError("Not transformed!");
	}
}
