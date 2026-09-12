package dev.coder2195.stellarity.interface_injection;

import com.mojang.serialization.Codec;
import dev.coder2195.stellarity.recipe.ConsecrationRecipe;
import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.world.item.ItemStack;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;
import dev.coder2195.stellarity.util.CustomCodecs;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.function.IntFunction;

@SuppressWarnings("NonExtendableApiUsage")
public interface ExtItemEntity extends AttachmentTarget, ExtEntity {
	enum ItemMode {
		DEFAULT(0, (short) 5, -1),
		ALTAR_CRAFTING(1, Short.MAX_VALUE, 0xaa00aa),
		CONSECRATING(2, null, 0xd556ef),
		RESULT(3, (short) 5, 0xffffff);

		private final int id;
		private final @Nullable Short pickupDelay;
		private final @Nullable Integer color;

		ItemMode(int id, @Nullable Short pickupDelay, @Nullable Integer color) {
			this.id = id;
			this.pickupDelay = pickupDelay;
			this.color = color;
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

		public @Nullable Short getPickupDelay() {
			return pickupDelay;
		}

		public boolean isCrafting() {
			return !(this == RESULT || this == DEFAULT);
		}
	}

	default ItemMode stellarity$getItemMode() {
		return this.getAttachedOrElse(StellarityDataAttachments.ITEM_MODE, ItemMode.DEFAULT);
	}

	default void stellarity$setItemMode(ItemMode mode, @Nullable Integer color) {
		this.setAttached(StellarityDataAttachments.ITEM_MODE, mode);
		if (color != null) this.stellarity$setGlowColor(color);
	}

	default void stellarity$setItemMode(ItemMode mode) {
		stellarity$setItemMode(mode, mode.color);
	}

	default void stellarity$removeItemMode() {
		this.removeAttached(StellarityDataAttachments.ITEM_MODE);
		this.removeAttached(StellarityDataAttachments.GLOW_COLOR);
	}

	default void stellarity$updateResults(HashMap<ItemStack, Integer> results) {
		throw new AssertionError("Not transformed!");
	}

	default void stellarity$consecrationCraft(boolean inWater) {
		throw new AssertionError("Not transformed!");
	}

	default ConsecrationRecipe.@Nullable ConsecrationData stellarity$getConsecrationData() {
		return this.getAttached(StellarityDataAttachments.CONSECRATION_DATA);
	}

	default void stellarity$setConsecrationData(ConsecrationRecipe.ConsecrationData data) {
		this.setAttached(StellarityDataAttachments.CONSECRATION_DATA, data);
	}

	default void stellarity$removeConsecrationData() {
		this.removeAttached(StellarityDataAttachments.CONSECRATION_DATA);
	}
}
